USE Customer
 IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='entities' AND xtype='U')
 CREATE TABLE entities (
 id int NOT NULL Primary Key IDENTITY(1,1),
 schema_name VARCHAR(45)  NOT NULL,
 table_name VARCHAR(45)  NOT NULL
)
 SET IDENTITY_INSERT dbo.entities ON


