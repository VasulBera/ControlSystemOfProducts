USE Administration
 IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='request_job' AND xtype='U')
 CREATE TABLE request_job (
 id int NOT NULL Primary Key IDENTITY(1,1),
 owner VARCHAR(255),
 aim VARCHAR(255),
 description VARCHAR(255),
 status VARCHAR(255),
 created_date datetime,
 last_modifier datetime
)