package com.mbit.wordCountPubli;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MapperWordCountPubli extends
    Mapper<LongWritable, Text, Text, IntWritable> {

  private static IntWritable IntWritable_1 = new IntWritable(1);
  private Text WordAsText = new Text();
  private List<String> linelist;
  /*
   * (non-Javadoc)
   * 
   * @see org.apache.hadoop.mapreduce.Mapper#map(java.lang.Object,
   * java.lang.Object, org.apache.hadoop.mapreduce.Mapper.Context)
   */
  @Override
  protected void map(LongWritable key, Text textLine, Context context)
      throws IOException, InterruptedException {
    // TODO Auto-generated method stub
    // Nos llegan separadas las lineas y tenemos que separar cada linea
    // Ruta:::Autores:::Publicacion
    linelist = Arrays.asList(textLine.toString().split(":::"));
    // Si la publicacion no es vacia la separamos en caracteres blancos:\b
    if (!linelist.isEmpty() && linelist.get(2) != null) {
      // Cada palabra de la publicacion la reducimos a minus y la machamos para
      // ver que sea como tal una palabra
      for (String word : linelist.get(2).split("\\b+")) {
        word = word.toLowerCase();
        if (word.matches("[a-záéíóúñç]+")) {
          // El String word lo pasamos a Text
          WordAsText.set(word);
          // y el Text lo enviamos al contexto junto con un IntWritable que es 1
          context.write(WordAsText, IntWritable_1);
        }
      }
    }

  }

}

