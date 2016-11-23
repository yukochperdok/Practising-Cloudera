package com.mbit.wordCountPubli;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ReducerWordCountPubli extends
    Reducer<Text, IntWritable, Text, LongWritable> {

  private LongWritable countAllWords = new LongWritable();

  @Override
  protected void reduce(Text WordKey, Iterable<IntWritable> ListOfOnes,
      Context context) throws IOException, InterruptedException {
    // Inicializamos el conteo
    long count1s = 0;

    // Contabilizamos todas las apariciones de la palabra y las vamos sumando
    for (@SuppressWarnings("unused")
    IntWritable one : ListOfOnes) {
      count1s++;
    }

    // Convertimos el long en un LongWritable y loañadimos al contexto junto con
    // la palabra
    countAllWords.set(count1s);
    context.write(WordKey, countAllWords);
  }

}


