package com.mbit.wordCountStopWord;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MapperWordCountStopWord extends
    Mapper<LongWritable, Text, Text, IntWritable> {

  private static IntWritable IntWritable_1 = new IntWritable(1);
  private Text WordAsText = new Text();
  private List<String> linelist;
  private List<String> stopWordList;

  @Override
  protected void setup(
      Mapper<LongWritable, Text, Text, IntWritable>.Context context)
      throws IOException, InterruptedException {
    // Si no esta cargada la lista aun la cargamos
    if (stopWordList == null) {
      stopWordList = new ArrayList<String>();
      // Recogemos de la cache distribuida la URI del fichero
      if (context.getCacheFiles() != null && context.getCacheFiles().length > 0) {
        URI mappingFile = context.getCacheFiles()[0];
        if (mappingFile != null) {
          // Lo leemos
          BufferedReader fis =
              new BufferedReader(new FileReader(
                  new File(mappingFile.getPath()).getName()));
          String pattern;
          // Para cada fila tenemos una stopword la añadimos a nuestra lista.
          while ((pattern = fis.readLine()) != null) {
            stopWordList.add(pattern);
          }
          // Cerramos el fichero
          fis.close();
        }
      }

    }

  }
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
        if (word.matches("[a-záéíóúñç]+") && !stopWord(word)) {
          // El String word lo pasamos a Text
          WordAsText.set(word);
          // y el Text lo enviamos al contexto junto con un IntWritable que es 1
          context.write(WordAsText, IntWritable_1);
        }
      }
    }

  }

  // Metodo que dada una word devuelve true si es una stopWord.
  private boolean stopWord(String word) {
    return stopWordList.contains(word);
  }

}

