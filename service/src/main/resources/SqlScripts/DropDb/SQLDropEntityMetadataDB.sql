use master
IF EXISTS (SELECT name FROM master.dbo.sysdatabases WHERE name = N'EntityMetadata') DROP DATABASE [EntityMetadata]