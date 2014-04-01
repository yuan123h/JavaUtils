package com.hive.test;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.ql.CommandNeedRetryException;
import org.apache.hadoop.hive.ql.Driver;
import org.apache.hadoop.hive.ql.session.SessionState;

public class Entrence {
	public static void main(String args[]) throws CommandNeedRetryException {
		ArrayList<String> res = new ArrayList<String>();
        String sql = "SELECT * from hive_yh";
        Driver driver = new Driver(new HiveConf(SessionState.class));
         driver.run(sql);
        try {
            driver.getResults(res);
        } catch(IOException e) {
            e.printStackTrace();
        }
        driver.close();
        System.out.println(driver.getMaxRows());
        try {
            System.out.println(driver.getSchema());
            //System.out.println(driver.getThriftSchema());
        } catch(Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }        
    }
}	
