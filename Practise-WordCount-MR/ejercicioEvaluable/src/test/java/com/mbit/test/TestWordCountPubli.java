package com.mbit.test;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

import com.mbit.wordCountPubli.MapperWordCountPubli;
import com.mbit.wordCountPubli.ReducerWordCountPubli;

public class TestWordCountPubli {
  private MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, LongWritable> myMapReduceDriver;

  private MapDriver<LongWritable, Text, Text, IntWritable> myMapDriver;

  private ReduceDriver<Text, IntWritable, Text, LongWritable> myReduceDriver;

  @Before
  public void configTest() {
    MapperWordCountPubli mapper = new MapperWordCountPubli();
    ReducerWordCountPubli reducer = new ReducerWordCountPubli();
    myMapDriver = MapDriver.newMapDriver(mapper);
    myReduceDriver = ReduceDriver.newReduceDriver(reducer);
    myMapReduceDriver = MapReduceDriver.newMapReduceDriver(mapper, reducer);
  }

  @Test
  public void runMapTest() throws IOException {
    myMapDriver
        .withInput(
            new LongWritable(0),
            new Text(
                "books/bc/tanselCGSS93/Tuzhilin93:::Alexander Tuzhilin:::Applications of temporal Databases to Knowledge-based Simulations."));

    myMapDriver.addOutput(new Text("applications"), new IntWritable(1));
    myMapDriver.addOutput(new Text("based"), new IntWritable(1));
    myMapDriver.addOutput(new Text("databases"), new IntWritable(1));
    myMapDriver.addOutput(new Text("knowledge"), new IntWritable(1));
    myMapDriver.addOutput(new Text("temporal"), new IntWritable(1));
    myMapDriver.addOutput(new Text("simulations"), new IntWritable(1));
    myMapDriver.addOutput(new Text("of"), new IntWritable(1));
    myMapDriver.addOutput(new Text("to"), new IntWritable(1));

    myMapDriver.runTest(false);
  }

  @Test
  public void runReduceTest() throws IOException {
    ArrayList<IntWritable> lista1 = new ArrayList<IntWritable>();
    lista1.add(new IntWritable(1));
    lista1.add(new IntWritable(1));
    lista1.add(new IntWritable(1));
    myReduceDriver.withInput(new Text("applications"), lista1);

    ArrayList<IntWritable> lista2 = new ArrayList<IntWritable>();
    lista2.add(new IntWritable(1));
    lista2.add(new IntWritable(1));
    myReduceDriver.withInput(new Text("based"), lista2);

    myReduceDriver.addOutput(new Text("applications"), new LongWritable(3));
    myReduceDriver.addOutput(new Text("based"), new LongWritable(2));

    myReduceDriver.runTest(false);
  }

  @Test
  public void runMapReduceTest() throws IOException {
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
    myMapReduceDriver.addOutput(new Text("of"), new LongWritable(1));
    myMapReduceDriver.addOutput(new Text("to"), new LongWritable(1));

    myMapReduceDriver.runTest(false);
  }

}
