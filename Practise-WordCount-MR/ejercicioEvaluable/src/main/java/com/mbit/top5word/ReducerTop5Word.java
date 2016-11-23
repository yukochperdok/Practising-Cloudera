package com.mbit.top5word;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.mbit.top5word.PalabraOcurr.PalabraOcurrComparator;

public class ReducerTop5Word extends
    Reducer<Text, IntWritable, Text, LongWritable> {

  private static PalabraOcurrComparator comparador =
      new PalabraOcurrComparator();

  private LongWritable countAllWords = new LongWritable();
  private List<PalabraOcurr> listaPalabrasOcurr = new ArrayList<PalabraOcurr>();

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

    listaPalabrasOcurr.add(new PalabraOcurr(count1s, new Text(WordKey)));
  }

  @Override
  protected void cleanup(
      Reducer<Text, IntWritable, Text, LongWritable>.Context context)
      throws IOException, InterruptedException {

    Collections.sort(listaPalabrasOcurr, comparador);

    for (int i = 0; i < listaPalabrasOcurr.size() && i < 5; i++) {
      countAllWords.set(((PalabraOcurr) listaPalabrasOcurr.get(i)).getOcurr());
      context.write(((PalabraOcurr) listaPalabrasOcurr.get(i)).getPalabra(),
          countAllWords);
    }
    

  }

}


