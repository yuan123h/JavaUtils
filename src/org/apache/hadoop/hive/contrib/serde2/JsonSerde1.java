package org.apache.hadoop.hive.contrib.serde2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.serde2.SerDe;
import org.apache.hadoop.hive.serde2.SerDeException;
import org.apache.hadoop.hive.serde2.SerDeStats;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonSerde1 implements SerDe {
	private static final Log LOG = LogFactory.getLog(JsonSerde1.class.getName());
	private int numColumns;
	private List<String> columnNames;
	private StructObjectInspector rowOI;
	private ArrayList<Object> row;
	private List<TypeInfo> columnTypes;

	public void initialize(Configuration sysProps, Properties tblProps)
			throws SerDeException {
		LOG.debug("Initializing JsonSerde");

		String columnNameProperty = tblProps.getProperty("columns");

		this.columnNames = Arrays.asList(columnNameProperty.split(","));

		String columnTypeProperty = tblProps.getProperty("columns.types");

		this.columnTypes = TypeInfoUtils
				.getTypeInfosFromTypeString(columnTypeProperty);

		assert (this.columnNames.size() == this.columnTypes.size());
		this.numColumns = this.columnNames.size();

		List columnOIs = new ArrayList(this.columnNames.size());

		for (int c = 0; c < this.numColumns; c++) {
			ObjectInspector oi = TypeInfoUtils
					.getStandardJavaObjectInspectorFromTypeInfo((TypeInfo) this.columnTypes
							.get(c));

			columnOIs.add(oi);
		}
		this.rowOI = ObjectInspectorFactory.getStandardStructObjectInspector(
				this.columnNames, columnOIs);

		this.row = new ArrayList(this.numColumns);
		for (int c = 0; c < this.numColumns; c++) {
			this.row.add(null);
		}

		LOG.debug("JsonSerde initialization complete");
	}

	public ObjectInspector getObjectInspector() throws SerDeException {
		return this.rowOI;
	}

	public Object deserialize(Writable blob) throws SerDeException {
		Text rowText = (Text) blob;
		LOG.debug("Deserialize row: " + rowText.toString());
		JSONObject jObj;
		try {
			jObj = new JSONObject(rowText.toString()) {
				public JSONObject put(String key, Object value)
						throws JSONException {
					return super.put(key.toLowerCase(), value);
				}
			};
		} catch (JSONException e) {
			LOG.error("Row is not a valid JSON Object - JSONException: "
					+ e.getMessage());

			return null;
		}

		for (int c = 0; c < this.numColumns; c++) {
			String colName = (String) this.columnNames.get(c);
			TypeInfo ti = (TypeInfo) this.columnTypes.get(c);
			Object value;
			try {
				if (ti.getTypeName().equalsIgnoreCase("double")) {
					value = Double.valueOf(jObj.getDouble(colName));
				} else {
					if (ti.getTypeName().equalsIgnoreCase("bigint")) {
						value = Long.valueOf(jObj.getLong(colName));
					} else {
						if (ti.getTypeName().equalsIgnoreCase("int")) {
							value = Integer.valueOf(jObj.getInt(colName));
						} else {
							if (ti.getTypeName().equalsIgnoreCase("tinyint")) {
								value = Byte.valueOf(jObj.getString(colName));
							} else {
								if (ti.getTypeName().equalsIgnoreCase("float")) {
									value = Float.valueOf(jObj
											.getString(colName));
								} else {
									if (ti.getTypeName().equalsIgnoreCase(
											"boolean")) {
										value = Boolean.valueOf(jObj
												.getBoolean(colName));
									} else {
										value = jObj.get(colName);
										String[] sList = value.toString().split("\t", -1);
										if (sList.length>1) {
											String tem="";
											for (String s : sList) {
												tem += s;
											}
											value = tem;
										}
											
									}
								}
							}
						}
					}
				}
			} catch (JSONException e) {
				LOG.warn("Column '" + colName + "' not found in row: "
						+ rowText.toString() + " - JSONException: "
						+ e.getMessage());

				value = null;
			}
			this.row.set(c, value);
		}

		return this.row;
	}

	public Class<? extends Writable> getSerializedClass() {
		return Text.class;
	}

	public Writable serialize(Object obj, ObjectInspector objInspector)
			throws SerDeException {
		LOG.info("-----------------------------");
		LOG.info("--------- serialize ---------");
		LOG.info("-----------------------------");
		LOG.info(obj.toString());
		LOG.info(objInspector.toString());

		return null;
	}

	@Override
	public SerDeStats getSerDeStats() {
		// TODO Auto-generated method stub
		return null;
	}
}