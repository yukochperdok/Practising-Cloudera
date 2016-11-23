data = LOAD '/tmp/Dataset2/data_procesado2/part-r-00000' AS (campaign_id:chararray,
             date:chararray, time:chararray,
             keyword:chararray, display_site:chararray, 
             placement:chararray, was_clicked:int, cpc:int);


-- TODO (B): Include only records where was_clicked has a value of 1
filter_data = FILTER data BY was_clicked == 1;

-- TODO (C): Group the data by the appropriate field
group_data = GROUP filter_data BY display_site;


/* TODO (D): Create a new relation which includes only the 
 *           display site and the total cost of all clicks 
 *           on that site
 */
new_relation = FOREACH group_data GENERATE group, SUM(filter_data.cpc) AS suma;

-- TODO (E): Sort that new relation by cost (ascending)
sorted_data = ORDER new_relation BY suma ASC;

-- TODO (F): Display just the first three records to the screen
top_three = LIMIT sorted_data 3;
STORE top_three INTO '/tmp/Dataset2/top_three/';
