package com.hadoop.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class TestMR {
	public static class MRMapper extends
			Mapper<BytesWritable, Text, Text, Text> {
		Connection conn = null;

		@Override
		protected void setup(Context context) throws IOException,
				InterruptedException {
			conn =  getConnection();
		}

		private Connection getConnection() {
			// TODO Auto-generated method stub
			return null;
		}

		public void map(BytesWritable key, Text value, Context context)
				throws IOException, InterruptedException {

		}

		@Override
		protected void cleanup(Context context) throws IOException,
				InterruptedException {
			super.cleanup(context);
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
