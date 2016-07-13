USE Customer
 IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='entities' AND xtype='U')
 CREATE TABLE entities (
 id VARCHAR(256) NOT NULL Primary Key,
 schema_name VARCHAR(128)  NOT NULL,
 table_name VARCHAR(128)  NOT NULL
)
 SET IDENTITY_INSERT dbo.entities ON


