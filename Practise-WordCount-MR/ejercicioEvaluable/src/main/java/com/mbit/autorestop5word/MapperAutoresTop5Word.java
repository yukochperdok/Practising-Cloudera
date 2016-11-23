package com.mbit.autorestop5word;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MapperAutoresTop5Word extends
    Mapper<LongWritable, Text, Text, IntWritable> {

  private Text AutorYPalabraAsText = new Text();
  private List<String> linelist;
  private List<String> stopWordList;
  private List<String> top5;

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
    // Si no esta cargada la lista aun la cargamos
    if (top5 == null) {
      top5 = new ArrayList<String>();
      // Recogemos de la cache distribuida la URI del fichero
      if (context.getCacheFiles() != null && context.getCacheFiles().length > 0) {
        URI mappingFile = context.getCacheFiles()[1];
        if (mappingFile != null) {
          // Lo leemos
          BufferedReader fis =
              new BufferedReader(new FileReader(
                  new File(mappingFile.getPath()).getName()));
          String pattern;
          int countTop5 = 0;
          // Para cada fila tenemos una stopword la añadimos a nuestra lista.
          while ((pattern = fis.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(pattern);
            int i = 0;
            while (st.hasMoreTokens()) {
              String palabra = st.nextToken();
              if (i == 0)
                top5.add(palabra);
              i++;
            }
          }
          // Cerramos el fichero
          fis.close();
          System.out.println(top5);
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

    // Si la publicacion no es vacia y tampoco los autores
    if (!linelist.isEmpty() && linelist.get(2) != null
        && linelist.get(1) != null) {

      for (int i = 0; i < top5.size(); i++) {
        if (top5.get(i).matches("[a-záéíóúñç]+") && !stopWord(top5.get(i))) {
          int index = 0, count = 0;
          while (index < linelist.get(2).length()
              && linelist.get(2).toLowerCase()
                  .indexOf(top5.get(i).toLowerCase(), index) != -1) {
            count++;
            index =
                linelist.get(2).toLowerCase()
                    .indexOf(top5.get(i).toLowerCase(), index) + 1;
          }
          if (count > 0) {
            for (String autor : linelist.get(1).split("::")) {
              autor = autor.toLowerCase();
              AutorYPalabraAsText.set(autor + "--" + top5.get(i));
              context.write(AutorYPalabraAsText, new IntWritable(count));
            }
          }
        }
      }

    }

  }

  // Metodo que dada una word devuelve true si es una stopWord.
  private boolean stopWord(String word) {
    return stopWordList.contains(word);
  }

}

