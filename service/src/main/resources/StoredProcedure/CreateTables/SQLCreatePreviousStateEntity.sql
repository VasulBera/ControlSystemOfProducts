USE Administration
 IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='previous_state_entities' AND xtype='U')
 CREATE TABLE previous_state_entities (
 id VARCHAR(256) NOT NULL Primary Key,
 name VARCHAR(128),
 schema_name VARCHAR(128),
 table_name VARCHAR(128),
 create_time datetime,
 update_time datetime
)