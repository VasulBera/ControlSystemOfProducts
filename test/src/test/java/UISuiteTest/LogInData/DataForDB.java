package UISuiteTest.LogInData;

interface IEntityNameDB {
    IEntitySchemaNameDB setEntityNameDB(String entityNameDB);
}

interface IEntitySchemaNameDB {
    IEntityTableNameDB setEntitySchemaNameDB(String entitySchemaNameDB);
}

interface IEntityTableNameDB {
    IFieldNameDB setEntityTableNameDB(String entityTableNameDB);
}

interface IFieldNameDB {
    IFieldColumnNameDB setFieldNameDB(String fieldNameDB);
}

interface IFieldColumnNameDB {
    IFieldDataType setFieldColumnNameDB(String fieldColumnNameDB);
}

interface IFieldDataType {
    IFieldLengthValueDB setDataTypeDB(String fieldDataTypeDB);
}

interface IFieldLengthValueDB {
    IEditDataBuildDB setLengthValueDB(String fieldLengthValueDB);
}
interface IEditDataBuildDB{
    DataForDB build();
}

public class DataForDB implements IEntityNameDB, IEntitySchemaNameDB, IEntityTableNameDB, IFieldNameDB, IFieldColumnNameDB, IFieldDataType, IFieldLengthValueDB, IDataDB, IEditDataBuildDB{
    private String entityNameDB;
    private String entitySchemaNameDB;
    private String entityTableNameDB;
    private String fieldNameDB;
    private String fieldColumnNameDB;
    private String fieldDataTypeDB;
    private String fieldLengthValueDB;

    public DataForDB() {
    }

    public static IEntityNameDB get() {
        return new DataForDB();
    }

    public IEntitySchemaNameDB setEntityNameDB(String entityNameDB) {
        this.entityNameDB = entityNameDB;
        return this;
    }

    public IEntityTableNameDB setEntitySchemaNameDB(String entitySchemaNameDB) {
        this.entitySchemaNameDB = entitySchemaNameDB;
        return this;
    }

    public IFieldNameDB setEntityTableNameDB(String entityTableNameDB) {
        this.entityTableNameDB = entityTableNameDB;
        return this;
    }

    public IFieldColumnNameDB setFieldNameDB(String fieldNameDB) {
        this.fieldNameDB = fieldNameDB;
        return this;
    }

     public IFieldDataType setFieldColumnNameDB(String fieldColumnNameDB){
        this.fieldColumnNameDB = fieldColumnNameDB;
        return this;
    }

   public IFieldLengthValueDB setDataTypeDB(String fieldDataTypeDB) {
       this.fieldDataTypeDB = fieldDataTypeDB;
       return this;
   }
   public IEditDataBuildDB setLengthValueDB(String fieldLengthValueDB) {
       this.fieldLengthValueDB = fieldLengthValueDB;
       return this;
    }

    public DataForDB build() {
        return this;
    }

    public String getEntityNameEdit() {
        return entityNameDB;
    }

    public String getEntitySchemaNameEdit() {
        return entitySchemaNameDB;
    }

    public String getEntityTableNameEdit() {
        return entityTableNameDB;
    }

    public String getFieldNameEdit() {
        return fieldNameDB;
    }

    public String getFieldColumnNameEdit() {
        return fieldColumnNameDB;
    }

    public String getFieldLengthValueEdit() {
        return fieldLengthValueDB;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataForDB dataForDB = (DataForDB) o;
        if (entityNameDB != null ? !entityNameDB.equals(dataForDB.entityNameDB) : dataForDB.entityNameDB != null)
            return false;
        if (entitySchemaNameDB != null ? !entitySchemaNameDB.equals(dataForDB.entitySchemaNameDB) : dataForDB.entitySchemaNameDB != null)
            return false;
        if (entityTableNameDB != null ? !entityTableNameDB.equals(dataForDB.entityTableNameDB) : dataForDB.entityTableNameDB != null)
            return false;
        if (fieldNameDB != null ? !fieldNameDB.equals(dataForDB.fieldNameDB) : dataForDB.fieldNameDB != null)
            return false;
        if (fieldColumnNameDB != null ? !fieldColumnNameDB.equals(dataForDB.fieldColumnNameDB) : dataForDB.fieldColumnNameDB != null)
            return false;
        if (fieldDataTypeDB != null ? !fieldDataTypeDB.equals(dataForDB.fieldDataTypeDB) : dataForDB.fieldDataTypeDB != null)
            return false;
        return fieldLengthValueDB != null ? fieldLengthValueDB.equals(dataForDB.fieldLengthValueDB) : dataForDB.fieldLengthValueDB == null;
    }

    @Override
    public String toString() {
        return "DataForDB{" +
                "entityNameDB='" + entityNameDB + '\'' +
                ", entitySchemaNameDB='" + entitySchemaNameDB + '\'' +
                ", entityTableNameDB='" + entityTableNameDB + '\'' +
                ", fieldNameDB='" + fieldNameDB + '\'' +
                ", fieldColumnNameDB='" + fieldColumnNameDB + '\'' +
                ", fieldDataTypeDB=" + fieldDataTypeDB +
                ", fieldLengthValueDB='" + fieldLengthValueDB + '\'' +
                '}';
    }
}
