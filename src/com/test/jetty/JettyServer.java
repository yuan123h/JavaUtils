package com.test.jetty;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.xml.XmlConfiguration;
import org.xml.sax.SAXException;

//1. 需要用到的jar包：jetty-6.1.26.jar， jetty-util-6.1.26.jar， servlet-api.jar
// servlet-api.jar 选定版本有点高，会报错。

public class JettyServer {
	
	public static void main(String[] args) {
		
        Server server = new Server(8080);
        //server.setHandler(new DefaultHandler());
        server.setHandler(new HelloHandler());
        XmlConfiguration configuration = null;
        try {
        	
            configuration = new XmlConfiguration(
                new FileInputStream("D:/tech/jetty/jetty-distribution-9.1.0.v20131115/etc/jetty.xml"));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (SAXException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        
        try {
        	//加载配置文件
            //configuration.configure(server);
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
