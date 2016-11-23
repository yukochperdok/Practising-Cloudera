data2 = LOAD '/tmp/Dataset2/ad_data2.txt' USING PigStorage(',') AS (campaign_id:chararray, date:chararray, time:chararray, display_site:chararray, placement:chararray, was_cliked:int, cpc:int, keyword:chararray);

uniqueData2 = DISTINCT data2;

formateadoData2 = FOREACH uniqueData2 GENERATE campaign_id,REPLACE(date,'-','/'),time,keyword,display_site,placement,was_cliked,cpc;

STORE formateadoData2 INTO '/tmp/Dataset2/data_procesado2';
DESCRIBE formateadoData2;
