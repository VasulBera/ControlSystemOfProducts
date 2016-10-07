IF NOT EXISTS (SELECT * FROM [Administration].[sys].[objects] WHERE name='previous_state_entities')
     CREATE TABLE [Administration].[dbo].[previous_state_entities]
     (
         id VARCHAR(256) NOT NULL Primary Key,
         name VARCHAR(128),
         schema_name VARCHAR(128),
         table_name VARCHAR(128),
         full_upload_data BIT,
         created_date datetime2,
         last_modifier datetime2
     )