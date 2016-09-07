USE Administration
 IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='user_detail' AND xtype='U')
 CREATE TABLE user_detail (
 id int NOT NULL Primary Key IDENTITY(1,1),
 username VARCHAR(255),
 password VARCHAR(255),
 security_token VARCHAR(255),
 client_id VARCHAR(255),
 client_secret VARCHAR(255),
 grant_type VARCHAR(255)
)