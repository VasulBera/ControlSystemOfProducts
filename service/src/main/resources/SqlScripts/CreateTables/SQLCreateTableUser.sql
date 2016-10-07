IF NOT EXISTS (SELECT * FROM [Administration].[sys].[objects] WHERE name='user_detail')
     CREATE TABLE [Administration].[dbo].[user_detail]
     (
         id int NOT NULL Primary Key IDENTITY(1,1),
         username VARCHAR(255),
         password VARCHAR(255),
         security_token VARCHAR(255),
         client_id VARCHAR(255),
         client_secret VARCHAR(255),
         grant_type VARCHAR(255)
    )