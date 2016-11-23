package com.mbit.countNumPubliSolitario;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class DriverCountNumPubliSolitario extends Configured implements Tool {

  @Override
  public int run(String[] args) throws Exception {
    // Recogemos configuracion
    Configuration conf = getConf();
    // Instanciamos el Job
    Job job = Job.getInstance(conf);
    // Nuestra clase
    job.setJarByClass(DriverCountNumPubliSolitario.class);
    // Nuestro Mapper
    job.setMapperClass(MapperCountNumPubliSolitario.class);
    // Nuestro Reducer
    job.setReducerClass(ReducerCountNumPubliSolitario.class);
    // job.setNumReduceTasks(0);

    // El formato del fichero de entrada
    job.setInputFormatClass(TextInputFormat.class);

    // Suponemos que el path del fichero de entrada entrara como parametro 1
    FileInputFormat.addInputPath(job, new Path(args[0]));

    // Definimos salida del Mapper
    job.setMapOutputKeyClass(Text.class);
    job.setMapOutputValueClass(IntWritable.class);

    // El formato del fichero de salida
    job.setOutputFormatClass(TextOutputFormat.class);

    // Suponemos que el path del fichero de salida entrara como parametro 2
    FileOutputFormat.setOutputPath(job, new Path(args[1]));

    // Definimos salida del Reducer
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(LongWritable.class);

    return job.waitForCompletion(true) ? 0 : 1;
  }

  /**
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    // Inicializamos configuracion
    Configuration conf = new Configuration(true);
    ToolRunner.run(conf, new DriverCountNumPubliSolitario(), args);

  }

}
