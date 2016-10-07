IF NOT EXISTS (SELECT * FROM [EntityMetadata].[sys].[objects] WHERE name='fields')
     CREATE TABLE [EntityMetadata].[dbo].[fields]
     (
         id VARCHAR(384) PRIMARY KEY NOT NULL,
         name VARCHAR(128) NOT NULL,
         column_name VARCHAR(128) NOT NULL,
         type VARCHAR(128) NOT NULL,
         length int NOT NULL,
         is_unique BIT,
         entity_id VARCHAR(256),
         created_date datetime2,
         last_modifier datetime2
    )