package coocurrencias;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

/* 
 * Recoge la lista de ficheros y la concatana en una cadena de Texto
 */   
public class SumCoocurReducer extends Reducer<TextPair, IntWritable, TextPair, IntWritable> {

  @Override
  public void reduce(TextPair key, Iterable<IntWritable> count,
      Context context)
			throws IOException, InterruptedException {

    int countCoocurs = 0;

    for (IntWritable coocur : count) {
      countCoocurs += coocur.get();
    }

    context.write(key, new IntWritable(countCoocurs));

  }
}