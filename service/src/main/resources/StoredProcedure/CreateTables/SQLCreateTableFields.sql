
USE Customer
 IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='fields' AND xtype='U')
 CREATE TABLE fields (
 id VARCHAR(256) PRIMARY KEY NOT NULL,
 name VARCHAR(128) NOT NULL,
 type VARCHAR(128) NOT NULL,
 length int NOT NULL,
 entity_id VARCHAR(384)
)