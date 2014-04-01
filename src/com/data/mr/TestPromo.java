package com.data.mr;

	import java.io.IOException;
	import java.text.DateFormat;
	import java.text.ParseException;
	import java.text.SimpleDateFormat;
	import java.util.ArrayList;
	import java.util.Calendar;
	import java.util.Collections;
	import java.util.Date;
	import java.util.GregorianCalendar;
	import java.util.HashMap;
	import java.util.HashSet;
	import java.util.Iterator;
	import java.util.List;
	import java.util.Map;

	import org.apache.hadoop.conf.Configuration;
	import org.apache.hadoop.conf.Configured;
	import org.apache.hadoop.fs.FileSystem;
	import org.apache.hadoop.fs.Path;
	import org.apache.hadoop.io.BytesWritable;
	import org.apache.hadoop.io.Text;
	import org.apache.hadoop.mapreduce.Job;
	import org.apache.hadoop.mapreduce.Mapper;
	import org.apache.hadoop.mapreduce.Reducer;
	import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
	import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
	import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
	import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
	import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
	import org.apache.hadoop.util.Tool;
	import org.apache.hadoop.util.ToolRunner;

	/**
	 * 日销量计算
	 * 
	 * @author yuanhuan
	 * 
	 */
	public class TestPromo extends Configured implements Tool {

		@Override
		public int run(String[] args) throws Exception {
			Configuration conf = getConf();
			FileSystem fs = FileSystem.get(conf);
			fs.delete(new Path(args[1]), true);
			Job job = new Job(conf, "DaySales");
			job.setJarByClass(TestPromo.class);
			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(Text.class);
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(Text.class);

			job.setMapperClass(MRMapper.class);
			job.setReducerClass(MRReducer.class);
			job.setOutputFormatClass(TextOutputFormat.class);
			job.setNumReduceTasks(5);
			FileInputFormat.addInputPath(job, new Path(args[0]));
			job.setInputFormatClass(SequenceFileInputFormat.class);
			FileOutputFormat.setOutputPath(job, new Path(args[1]));
			return job.waitForCompletion(true) ? 0 : 1;
		}

		public static void main(String[] args) throws Exception {
			ToolRunner.run(new Configuration(), new TestPromo(), args);
		}

		/**
		 * websit != 1,2
		 * 
		 * @author yuanhuan
		 * 
		 */
		public static class MRMapper extends
				Mapper<Text, Text, Text, Text> {
			public void map(Text key, Text value, Context context)
					throws IOException, InterruptedException {
				String field[] = value.toString().split("\t", -1);
				for (String s : field) {
					System.out.println(s);
				}
			}
		}

		/**
		 * 
		 * @author yuanhuan
		 * 
		 */
		public static class MRReducer extends Reducer<Text, Text, Text, Text> {

			@SuppressWarnings({ "unchecked", "rawtypes" })
			public void reduce(Text key, Iterable<Text> values, Context context)
					throws IOException, InterruptedException {
			}
		}


	}

