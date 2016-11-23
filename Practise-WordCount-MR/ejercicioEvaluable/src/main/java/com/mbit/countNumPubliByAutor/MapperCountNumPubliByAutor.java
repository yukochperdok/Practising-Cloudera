package com.mbit.countNumPubliByAutor;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MapperCountNumPubliByAutor extends
    Mapper<LongWritable, Text, Text, IntWritable> {

  private static IntWritable IntWritable_1 = new IntWritable(1);
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
    // Si la cadena de autores no es vaciala separamos en caracteres :: para
    // tener una lista de autores
    if (!linelist.isEmpty() && linelist.get(1) != null) {
      // Cada autor se añade al contexto con la publicacion. Si la publicacion
      // tiene varios autores se tiene en cuenta.
      for (String autor : linelist.get(1).split("::")) {
        autor = autor.toLowerCase();
        // El String word lo pasamos a Text
        AutorAsText.set(autor);
        // y el Text lo enviamos al contexto junto con un IntWritable que es 1
        context.write(AutorAsText, IntWritable_1);
      }
    }

  }

}

