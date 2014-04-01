package com.hadoop.test;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.util.bloom.BloomFilter;
import org.apache.hadoop.util.bloom.Key;
import org.apache.hadoop.util.hash.Hash;

public class HadoopSevenMapperDemo extends Configured implements Tool {

	public static class MapClass extends Mapper<Object, Text, Text, BloomFilter> {

		BloomFilter bloomFilter = new BloomFilter(1600000000, 10, Hash.JENKINS_HASH);

		public void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {
			String tmp = value.toString().trim();
			if (tmp.length()>0)
			bloomFilter.add(new Key(tmp.getBytes()));
		}

		@Override
		protected void cleanup(Context context) throws IOException,
				InterruptedException {
			context.write(new Text("testKey"), bloomFilter);
		}

	}

	public static class ReduceClass extends
			Reducer<Text, BloomFilter, Text, Text> {
		BloomFilter bf = new BloomFilter(1600000000, 10, Hash.JENKINS_HASH);
		private Configuration conf;

		@Override
		protected void setup(Context context) throws IOException,
		InterruptedException {
			conf = context.getConfiguration();
		}

		@Override
		protected void reduce(Text key, Iterable<BloomFilter> values,
				Context context) throws IOException, InterruptedException {
			Iterator<BloomFilter> iter = values.iterator();
			while (iter.hasNext()) {
				bf.or(iter.next());
			}
		}

		@Override
		protected void cleanup(Context context) throws IOException,
				InterruptedException {
			Path file = new Path(conf.get("mapred.output.dir") + "/bloomfilter");
			FSDataOutputStream out = file.getFileSystem(conf).create(file);
			bf.write(out);
			out.close();
		}

	}

	@Override
	public int run(String[] args) throws Exception {

		Configuration conf = getConf();
		Job job = new Job(conf, "BloomFilter");

		Path in = new Path(args[0]);
		Path out = new Path(args[1]);
		FileInputFormat.setInputPaths(job, in);
		FileSystem fs = FileSystem.get(conf);
		fs.delete(out, true);
		FileOutputFormat.setOutputPath(job, out);

		job.setJarByClass(HadoopSevenMapperDemo.class);
		job.setMapperClass(MapClass.class);
		job.setReducerClass(ReduceClass.class);
		job.setNumReduceTasks(1);

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(BloomFilter.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(BloomFilter.class);

		return job.waitForCompletion(true) ? 0 : 1;
	}

	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("wrong parameters");
			System.exit(0);
		}
		int res;
		try {
			res = ToolRunner.run(new Configuration(),
					new HadoopSevenMapperDemo(), args);
			System.exit(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}