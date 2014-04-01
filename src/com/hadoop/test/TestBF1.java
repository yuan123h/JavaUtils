package com.hadoop.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.util.bloom.BloomFilter;
import org.apache.hadoop.util.bloom.Key;
import org.apache.hadoop.util.hash.Hash;

public class TestBF1 extends Configured {
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

		String str;
		BufferedReader reader = new BufferedReader(new FileReader(new File(
				args[1])));
		boolean update = false;
		while ((str = reader.readLine()) != null) {
			boolean exit = bf.membershipTest(new Key(str.getBytes()));
			System.out.println(exit);
			if (!exit) {
				update = true;
				System.out.println("add");
				bf.add(new Key((str.getBytes())));
			}
		}
		if (update) {
			Date date = new Date();
			Path file = new Path(args[0] + "_" + date.getTime());
			FSDataOutputStream out = file.getFileSystem(conf).create(file);
			bf.write(out);
			out.close();
		}
	}
}
