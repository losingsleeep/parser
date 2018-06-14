-- see the content of batch table
SELECT * FROM batch;


-- see the total record count of blocked table related to a specific batch 
SELECT count(*) FROM blocked where batch_id = 34;
-- see the content of blocked table related to a specific batch 
SELECT * FROM blocked where batch_id = 34;


-- see the total record count of request table related to a specific batch 
SELECT count(*) FROM request where batch_id = 34;
-- see the content of request table related to a specific batch 
SELECT * FROM request where batch_id = 34;
