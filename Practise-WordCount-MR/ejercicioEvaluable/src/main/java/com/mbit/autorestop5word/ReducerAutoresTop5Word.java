package com.mbit.autorestop5word;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.mbit.autorestop5word.AutorPalabraOcurr.AutorPalabraOcurrComparator;
import com.mbit.top5word.PalabraOcurr;

public class ReducerAutoresTop5Word extends
    Reducer<Text, IntWritable, Text, LongWritable> {

  private static AutorPalabraOcurrComparator comparador =
      new AutorPalabraOcurrComparator();

  private LongWritable countAll = new LongWritable();
  private List<AutorPalabraOcurr> listaAutorPalabrasOcurr =
      new ArrayList<AutorPalabraOcurr>();

  @Override
  protected void reduce(Text AutorYPalabraAsText,
      Iterable<IntWritable> Apariciones,
      Context context) throws IOException, InterruptedException {
    // Inicializamos el conteo
    long count = 0;

    // Contabilizamos todas las apariciones de la palabra y las vamos sumando
    for (@SuppressWarnings("unused")
    IntWritable one : Apariciones) {
      count += one.get();
    }

    listaAutorPalabrasOcurr.add(new AutorPalabraOcurr(count, new Text(
        AutorYPalabraAsText)));
  }

  @Override
  protected void cleanup(
      Reducer<Text, IntWritable, Text, LongWritable>.Context context)
      throws IOException, InterruptedException {

    Collections.sort(listaAutorPalabrasOcurr, comparador);

    for (int i = 0; i < listaAutorPalabrasOcurr.size() && i < 10; i++) {
      countAll.set(((AutorPalabraOcurr) listaAutorPalabrasOcurr.get(i))
          .getOcurr());
      context.write(((AutorPalabraOcurr) listaAutorPalabrasOcurr.get(i))
          .getAutorPalabra(), countAll);
    }

  }

}


