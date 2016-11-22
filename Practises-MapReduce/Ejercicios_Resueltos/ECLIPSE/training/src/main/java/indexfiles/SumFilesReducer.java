package indexfiles;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/* 
 * Recoge la lista de ficheros y la concatana en una cadena de Texto
 */   
public class SumFilesReducer extends Reducer<Text, Text, Text, Text> {

  @Override
  public void reduce(Text key, Iterable<Text> ficheros, Context context)
			throws IOException, InterruptedException {
    /*
     * Si no te importa que los ficheros se repitan puedes hacer esto:
     * 
     * String cad_ficheros = "";
     * 
     * 
     * 
     * for (Text fichero : ficheros) { cad_ficheros += "|" + fichero.toString();
     * }
     * 
     * context.write(key, new Text(cad_ficheros));
     */

    // Si lo quieres sin repeticiones la mejor opcion es esta

    Set<String> setFiles = new HashSet<String>();

    for (Text fichero : ficheros) {
      setFiles.add(fichero.toString());
    }

    context.write(key, new Text(setFiles.toString()));

  }
}