package com.hadoop.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.util.bloom.BloomFilter;
import org.apache.hadoop.util.bloom.Key;
import org.apache.hadoop.util.hash.Hash;

public class TestBF extends Configured{
	public static void main(String[] args) throws IOException {
		if (args.length < 2) {
			System.out.println("wrong parameters");
			System.out.println("usage: <path_bloomfilter> <test_path>");
			System.exit(0);
		}
		
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(conf);
		FSDataInputStream in = fs.open(new Path(args[0]));
		
		BloomFilter bf = new BloomFilter(1600000000, 10, Hash.JENKINS_HASH);
		bf.readFields(in);
		
		FSDataInputStream data = fs.open(new Path(args[1]));
		BufferedReader reader
        = new BufferedReader(new InputStreamReader(data));

		int i=0;
		String str;
		while (i<10) {
			i++;
			str = reader.readLine();
			System.out.println(bf.membershipTest(new Key(str.getBytes())));
		}
		
		/*BufferedReader reader = new BufferedReader(new FileReader(new File(args[1])));
		while ((str = reader.readLine()) != null) {
			System.out.println(bf.membershipTest(new Key(str.getBytes())));
		}		*/
	}
}
