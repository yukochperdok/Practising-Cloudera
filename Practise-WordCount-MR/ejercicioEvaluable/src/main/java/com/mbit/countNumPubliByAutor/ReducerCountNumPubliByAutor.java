package com.mbit.countNumPubliByAutor;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ReducerCountNumPubliByAutor extends
    Reducer<Text, IntWritable, Text, LongWritable> {

  private LongWritable countAllPubli = new LongWritable();

  @Override
  protected void reduce(Text AutorKey, Iterable<IntWritable> ListOfOnes,
      Context context) throws IOException, InterruptedException {
    // Inicializamos el conteo
    long count1s = 0;

    // Contabilizamos todas las apariciones de la publicacion para el autor en
    // concreto
    for (@SuppressWarnings("unused")
    IntWritable one : ListOfOnes) {
      count1s++;
    }

    // Convertimos el long en un LongWritable y loañadimos al contexto junto con
    // el autor
    countAllPubli.set(count1s);
    context.write(AutorKey, countAllPubli);
  }

}


