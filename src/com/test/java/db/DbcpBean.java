package com.test.java.db;

import java.awt.image.VolatileImage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;

public final class DbcpBean {
	/** 数据源，static */
	private static DataSource DS;
	static volatile boolean waight = true;
	public static boolean isWaight() {
		return waight;
	}

	public static void setWaight(boolean waight) {
		DbcpBean.waight = waight;
	}

	/** 从数据源获得一个连接 */
	public Connection getConn() {
		Connection con = null;
		if (DS != null) {
			try {
				con = DS.getConnection();
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}

			try {
				con.setAutoCommit(false);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return con;
		}
		return con;
	}

	/** 默认的构造函数 */
	public DbcpBean() {
	}

	/** 构造函数，初始化了 DS ，指定 数据库 */
	public DbcpBean(String connectURI) {
		initDS(connectURI);
	}

	/** 构造函数，初始化了 DS ，指定 所有参数 */
	public DbcpBean(String connectURI, String username, String pswd,
			String driverClass, int initialSize, int maxActive, int maxIdle,
			int maxWait) {
		initDS(connectURI, username, pswd, driverClass, initialSize, maxActive,
				maxIdle, maxWait);
	}

	/**
	 * 创建数据源，除了数据库外，都使用硬编码默认参数；
	 * 
	 * @param connectURI
	 *            数据库
	 * @return
	 */
	public static void initDS(String connectURI) {
		initDS(connectURI, "root", "knowmi@i708", "com.mysql.jdbc.Driver", 3, 10, 5, 10000);
	}

	/**
	 * 指定所有参数连接数据源
	 * 
	 * @param connectURI
	 *            数据库
	 * @param username
	 *            用户名
	 * @param pswd
	 *            密码
	 * @param driverClass
	 *            数据库连接驱动名
	 * @param initialSize
	 *            初始连接池连接个数
	 * @param maxActive
	 *            最大激活连接数
	 * @param maxIdle
	 *            最大闲置连接数
	 * @param maxWait
	 *            获得连接的最大等待毫秒数
	 * @return
	 */
	public static void initDS(String connectURI, String username, String pswd,
			String driverClass, int initialSize, int maxActive, int maxIdle,
			int maxWait) {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(driverClass);
		ds.setUsername(username);
		ds.setPassword(pswd);
		ds.setUrl(connectURI);
		ds.setInitialSize(initialSize); // 初始的连接数；
		ds.setMaxActive(maxActive);
		ds.setMaxIdle(maxIdle);
		ds.setMaxWait(maxWait);
		DS = ds;
	}

	/** 获得数据源连接状态 */
	public static Map<String, Integer> getDataSourceStats() throws SQLException {
		BasicDataSource bds = (BasicDataSource) DS;
		Map<String, Integer> map = new HashMap<String, Integer>(2);
		map.put("active_number", bds.getNumActive());
		map.put("idle_number", bds.getNumIdle());
		return map;
	}

	/** 关闭数据源 */
	protected static void shutdownDataSource() throws SQLException {
		BasicDataSource bds = (BasicDataSource) DS;
		bds.close();
	}

	static class TestThread extends Thread {
		public TestThread() {
		}

		@Override
		public void run() {
			this.test();
		}

		void test(){
			while (DbcpBean.isWaight()){
			}
			DbcpBean db = new DbcpBean("jdbc:mysql://10.124.60.250:3306/goods_adapter");
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			try {
				conn = db.getConn();
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select * from goodsAdapter_1_86 limit 5");
				System.out.println("Results:");
				int numcols = rs.getMetaData().getColumnCount();
				while (rs.next()) {
					for (int i = 1; i <= numcols; i++) {
						System.out.print("\t" + rs.getString(i) + "\t");
					}
					System.out.println("");
				}
				System.out.println(getDataSourceStats());
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (stmt != null)
						stmt.close();
					if (conn != null)
						conn.close();
					if (db != null)
						shutdownDataSource();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		
		for (int i = 0; i < 20; i++) {
			TestThread t = new TestThread();
			t.start();
			System.out.println(i);
		}
		System.out.println("change");
		waight = false;
	}

}
