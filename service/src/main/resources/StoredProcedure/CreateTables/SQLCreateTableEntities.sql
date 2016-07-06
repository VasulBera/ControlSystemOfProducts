USE Customer
 IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='entities' AND xtype='U')
 CREATE TABLE entities (
 id VARCHAR(45) NOT NULL Primary Key,
 schema_name VARCHAR(45)  NOT NULL,
 table_name VARCHAR(45)  NOT NULL
)
 SET IDENTITY_INSERT dbo.entities ON


