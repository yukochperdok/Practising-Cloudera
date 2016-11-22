package indexfiles;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;


public class FileMapper extends Mapper<LongWritable, Text, Text, Text> {

  /*
   * Recoge las lineas las separa en palabras y a cada palabra le asocia el
   * fichero al que pertenece
   */
  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {

    String line = value.toString();
    String nombreFile = null;

    for (String word : line.split("\\W+")) {
      if (word.length() > 0) {
        nombreFile = ((FileSplit) context.getInputSplit()).getPath().getName();
        context.write(new Text(word), new Text(nombreFile));
      }
    }
  }
}