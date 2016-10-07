IF NOT EXISTS (SELECT * FROM [Administration].[sys].[objects] WHERE name='request_job')
     CREATE TABLE [Administration].[dbo].[request_job]
     (
         id int NOT NULL Primary Key IDENTITY(1,1),
         owner VARCHAR(255),
         aim VARCHAR(255),
         description VARCHAR(255),
         status VARCHAR(255),
         created_date datetime2,
         last_modifier datetime2
    )