IF NOT EXISTS (SELECT * FROM [EntityMetadata].[sys].[objects] WHERE name='entities')
     CREATE TABLE [EntityMetadata].[dbo].[entities]
     (
         id VARCHAR(256) NOT NULL Primary Key,
         name VARCHAR(128),
         schema_name VARCHAR(128),
         table_name VARCHAR(128),
         full_upload_data BIT,
         created_date datetime2,
         last_modifier datetime2
    )


