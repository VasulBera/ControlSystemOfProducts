USE Customer
 IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='fields' AND xtype='U')
 CREATE TABLE fields (
 id int PRIMARY KEY IDENTITY(1,1) NOT NULL,
 name VARCHAR(45) NOT NULL,
 type VARCHAR(45) NOT NULL,
 length int NOT NULL,
 entity_id int NOT NULL
)