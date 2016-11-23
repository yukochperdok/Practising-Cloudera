package com.mbit.test;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.junit.Before;
import org.junit.Test;

import com.mbit.wordCountStopWord.MapperWordCountStopWord;
import com.mbit.wordCountStopWord.ReducerWordCountStopWord;

public class TestWordCountStopWord {
  // private final String PATH_STOP_WORDS =
  // "/home/cloudera/workspace/ejercicioEvaluable/inputStopWords/stopwords_en.txt";
  private final String PATH_STOP_WORDS = "stopwords_en.txt";

  private MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, LongWritable> myMapReduceDriver;

  @Before
  public void configTest() {
    MapperWordCountStopWord mapper = new MapperWordCountStopWord();
    ReducerWordCountStopWord reducer = new ReducerWordCountStopWord();
    myMapReduceDriver = MapReduceDriver.newMapReduceDriver(mapper, reducer);
    // URI[] archives = new URI[] { new Path(PATH_STOP_WORDS).toUri() };
    // myMapReduceDriver.setCacheFiles(archives);
    // myMapReduceDriver.addCacheFile(new Path(PATH_STOP_WORDS).toUri());

  }

  @Test
  public void runMapReduceTest() throws IOException {
    myMapReduceDriver.withCacheFile(PATH_STOP_WORDS);

    myMapReduceDriver
        .withInput(
            new LongWritable(0),
            new Text(
                "books/bc/tanselCGSS93/Tuzhilin93:::Alexander Tuzhilin:::Applications of temporal Databases to Knowledge-based Simulations."));
    
    myMapReduceDriver.addOutput(new Text("applications"), new LongWritable(1));
    myMapReduceDriver.addOutput(new Text("based"), new LongWritable(1));
    myMapReduceDriver.addOutput(new Text("databases"), new LongWritable(1));
    myMapReduceDriver.addOutput(new Text("knowledge"), new LongWritable(1));
    myMapReduceDriver.addOutput(new Text("temporal"), new LongWritable(1));
    myMapReduceDriver.addOutput(new Text("simulations"), new LongWritable(1));
    // myMapReduceDriver.addOutput(new Text("of"), new LongWritable(1));
    // myMapReduceDriver.addOutput(new Text("to"), new LongWritable(1));

    // myMapReduceDriver.runTest(false);
  }

}
