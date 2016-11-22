package coocurrencias;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class CountCoocurMapper extends
    Mapper<LongWritable, Text, TextPair, IntWritable> {

  private static TextPair textPair = new TextPair();
  private static Text first = new Text();
  private static Text second = new Text();
  private static IntWritable one = new IntWritable(1);

  /*
   * Recoge las lineas las separa en palabras y para cada palabra busca sus
   * contiguas para no contabilizar dos veces un par se procesa linealmente
   * desde 0 a length -1 haciendo pares i,i+1
   */
  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {

    String line = value.toString();
    line = line.replace(",", "");
    line = line.replace(".", "");
    line = line.replace(";", "");
    line = line.replace(":", "");
    line = line.replace("!", "");
    line = line.replace("?", "");
    line = line.replace("\\s", "");

    String[] words = line.split("\\W+");

    for (int i = 0; i < words.length - 1; i++) {
      if (!words[i].equals("")) {
        first.set(words[i]);
        second.set(words[i + 1]);
        textPair.set(first, second);
        context.write(textPair, one);
      }
    }
  }
}