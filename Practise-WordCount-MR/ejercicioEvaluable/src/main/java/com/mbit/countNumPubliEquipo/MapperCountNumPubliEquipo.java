package com.mbit.countNumPubliEquipo;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MapperCountNumPubliEquipo extends
    Mapper<LongWritable, Text, Text, IntWritable> {

  private static IntWritable IntWritable_1 = new IntWritable(1);
  private static String EQUIPO = "#equipo";
  private static String SOLITARIO = "#solitario";
  private Text AutorAsText = new Text();
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
    // Si la cadena de autores no es vacia la separamos en caracteres :: para
    // tener una lista de autores
    if (!linelist.isEmpty() && linelist.get(1) != null) {
      String[] autores = linelist.get(1).split("::");
      // Si tenemos mas de un autor, son coautores y la publicacion es en equipo
      if (autores.length > 1) {
        // Se añade al contexto "autor#equipo" para cada autor.
        for (int i = 0; i < autores.length; i++) {
          // El String word lo pasamos a Text
          AutorAsText.set(autores[i].toLowerCase() + EQUIPO);
          // y el Text lo enviamos al contexto junto con un IntWritable que es 1
          context.write(AutorAsText, IntWritable_1);
        }
        // Si solo tenemos un autor añadimos al contexto "autor#solitario"
      } else {
        // El String word lo pasamos a Text
        AutorAsText.set(autores[0].toLowerCase() + SOLITARIO);
        // y el Text lo enviamos al contexto junto con un IntWritable que es 1
        context.write(AutorAsText, IntWritable_1);
      }
    }

  }

}

