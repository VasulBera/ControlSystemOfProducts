USE Request_job
 IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='request_tasks' AND xtype='U')
 CREATE TABLE request_tasks (
 id int NOT NULL Primary Key IDENTITY(1,1),
 owner VARCHAR(256),
 aim VARCHAR(256),
 description VARCHAR(1000),
 status VARCHAR(1000),
 create_time datetime,
 update_time datetime

)