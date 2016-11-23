#!/bin/bash

sqoop import --table categories --connect jdbc:mysql://localhost/retail_db --username root --password cloudera
sqoop import --table customers --connect jdbc:mysql://localhost/retail_db --username root --password cloudera --warehouse-dir /tmp/sqoop/ --columns "customer_fname,customer_lname,customer_street"
sqoop import --table orders --connect jdbc:mysql://localhost/retail_db --username root --password cloudera --incremental append --check-column order_id --last-value 5000
