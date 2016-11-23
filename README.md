# Practising-Cloudera
## Practicas con diferentes tecnologias y herramientas instaladas en un Cloudera 5.7
### 1. Practises-MapReduce: 
* Conjunto de Map-Reduce's con diferentes aplicaciones. Puedes encontrar una guia (Training.doc/pdf) donde te explica paso a paso como compilar y ejecutar cada uno, y te ayuda a interpretarlo.
* NOTA: En la carpeta ECLIPSE tienes el proyecto entero (hecho con LUNA, version que distribuye Cloudera 5.7 pero se puede instalar en cualquiera)
* NOTA 2: En la carpeta HDFS: Tienes los ficheros de entrada que tendras que copiar al HDFS montado en Cloudera, y los ficheros de salida que generan estos Map Reduce
* NOTA 3: En la carpeta fich_entrada tienes los ficheros en bruto comprimidos (de entrada)
* REQUISITOS: Solo es necesario tener montado CDH 5.7, descargarte las librerias Maven (MVN) desde el Eclipse. Si tienes otra version inferior de Cloudera es probable que tengas que adaptar el pom.xml

### 2. Practise-WordCount-MR
+ De la misma forma que el anterior son practicas que se hicieron en Cloudera 5.7, con variaciones sobre el tipico ejemplo del wordcount.
+ Aplican los mismos requisitos que en el proyecto anterior y se tiene una guia (Guia_Ejercicios.odt) donde explican paso a paso como ejecutar los MR, e interpretarlos.

### 3. Practises-FLUME, Practises-PIG, Practises-HIVE y Practises-SQOOP  
* En estas carpetas encontraras guias para trabajar con las diferentes herramientas. En la mayoria tendras unos ejercicios basicos y unos ejercicios avanzados (los cuales recomiendo seguir). Solo necesitas una distribucion de cloudera en la cual tendras instaladas por defecto estas herramientas. Para FLUME(Interceptores) y HIVE(UDFs) tendras tambien proyectos JAVA.

### 4. Practises-AVRO  
* Algunos ejercicios faciles para manejar avro y conversiones avro-json, no necesitas Cloudera te vale con cualquier distribucion Linux o UNIX que tenga una JDK.
