USE Administration
 IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='previous_state_fields' AND xtype='U')
 CREATE TABLE previous_state_fields (
 id VARCHAR(384) PRIMARY KEY NOT NULL,
 name VARCHAR(128) NOT NULL,
 column_name VARCHAR(128) NOT NULL,
 type VARCHAR(128) NOT NULL,
 length int NOT NULL,
 entity_id VARCHAR(256),
 created_date datetime,
 last_modifier datetime
)