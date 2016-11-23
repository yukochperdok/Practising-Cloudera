package com.mbit.countNumPubliEquipo;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ReducerCountNumPubliEquipo extends
    Reducer<Text, IntWritable, Text, LongWritable> {

  private LongWritable countAllPubli = new LongWritable();
  private static String EQUIPO = "#equipo";
  private static String SOLITARIO = "#solitario";

  @Override
  protected void reduce(Text AutorKey, Iterable<IntWritable> ListOfOnes,
      Context context) throws IOException, InterruptedException {
    // Inicializamos el conteo
    long count1sEquipo = 0;
    // long count1sSolitario = 0;
    String autor = AutorKey.toString();

    if (AutorKey.toString().contains(EQUIPO)) {
      // Contabilizamos todas las apariciones de la publicacion para el autor en
      // concreto, siempre que sean en equipo: #equipo
      for (@SuppressWarnings("unused")
      IntWritable one : ListOfOnes) {
        count1sEquipo++;
      }
    }
    /*if (AutorKey.toString().contains(SOLITARIO)) {
      // Contabilizamos todas las apariciones de la publicacion para el autor en
      // concreto, siempre que sean en solitario: #solitario
      for (@SuppressWarnings("unused")
      IntWritable one : ListOfOnes) {
        count1sSolitario++;
      }
    }*/

    // Convertimos el long en un LongWritable y lo añadimos al contexto junto
    // con el autor en modo Text (le quitamos #equipo)
    countAllPubli.set(count1sEquipo);

    autor = autor.substring(0, autor.indexOf("#"));
    AutorKey.set(autor);
    context.write(AutorKey, countAllPubli);
  }

}


