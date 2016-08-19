USE EntityMetadata
 IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='entities' AND xtype='U')
 CREATE TABLE entities (
 id VARCHAR(256) NOT NULL Primary Key,
 name VARCHAR(128),
 schema_name VARCHAR(128),
 table_name VARCHAR(128)
)


