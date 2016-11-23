data1 = LOAD '/tmp/Dataset1/ad_data1.txt' AS (keyword:chararray, campaign_id:chararray, date:chararray, time:chararray, display_site:chararray, was_cliked:int, cpc:int, country:chararray, placement:chararray);

filterData = FILTER data1 BY country=='USA';

formateadoData = FOREACH filterData GENERATE campaign_id,date,time,TRIM(UPPER(keyword)),display_site,placement,was_cliked,cpc;

STORE formateadoData INTO '/tmp/Dataset1/data_procesado';
DESCRIBE formateadoData;
