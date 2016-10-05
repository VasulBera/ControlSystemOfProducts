package UISuiteTest.LogInData;

interface IentityNameDB {
    IentitySchemaNameDB setEntityNameDB(String entityNameDB);
}

interface IentitySchemaNameDB {
    IentityTableNameDB setEntitySchemaNameDB(String entitySchemaNameDB);
}

interface IentityTableNameDB {
    IfieldNameDB setEntityTableNameDB(String entityTableNameDB);
}

interface IfieldNameDB {
    IfieldColumnNameDB setFieldNameDB(String fieldNameDB);
}

interface IfieldColumnNameDB {
    IfieldDataType setFieldColumnNameDB(String fieldColumnNameDB);
}

interface IfieldDataType{
    IfieldLengthValueDB setDataTypeDB(String fieldDataTypeDB);
}

interface IfieldLengthValueDB {
    IEditDataBuildDB setLengthValueDB(String fieldLengthValueDB);
}
interface IEditDataBuildDB{
    DataForDB build();
}

public class DataForDB implements IentityNameDB, IentitySchemaNameDB, IentityTableNameDB, IfieldNameDB, IfieldColumnNameDB, IfieldDataType, IfieldLengthValueDB, IDataDB, IEditDataBuildDB{
    private String entityNameDB;
    private String entitySchemaNameDB;
    private String entityTableNameDB;
    private String fieldNameDB;
    private String fieldColumnNameDB;
    private String fieldDataTypeDB;
    private String fieldLengthValueDB;

    public DataForDB() {

    }

    public static IentityNameDB get() {
        return new DataForDB();
    }

    public IentitySchemaNameDB setEntityNameDB(String entityNameDB) {
        this.entityNameDB = entityNameDB;
        return this;
    }

    public IentityTableNameDB setEntitySchemaNameDB(String entitySchemaNameDB) {
        this.entitySchemaNameDB = entitySchemaNameDB;
        return this;
    }

    public IfieldNameDB setEntityTableNameDB(String entityTableNameDB) {
        this.entityTableNameDB = entityTableNameDB;
        return this;
    }

    public IfieldColumnNameDB setFieldNameDB(String fieldNameDB) {
        this.fieldNameDB = fieldNameDB;
        return this;
    }

     public IfieldDataType setFieldColumnNameDB(String fieldColumnNameDB){
        this.fieldColumnNameDB = fieldColumnNameDB;
        return this;
    }

   public IfieldLengthValueDB setDataTypeDB(String fieldDataTypeDB) {
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

    // get - - - - - - - - - -
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
    public int hashCode() {
        int result = entityNameDB != null ? entityNameDB.hashCode() : 0;
        result = 31 * result + (entitySchemaNameDB != null ? entitySchemaNameDB.hashCode() : 0);
        result = 31 * result + (entityTableNameDB != null ? entityTableNameDB.hashCode() : 0);
        result = 31 * result + (fieldNameDB != null ? fieldNameDB.hashCode() : 0);
        result = 31 * result + (fieldColumnNameDB != null ? fieldColumnNameDB.hashCode() : 0);
        result = 31 * result + (fieldDataTypeDB != null ? fieldDataTypeDB.hashCode() : 0);
        result = 31 * result + (fieldLengthValueDB != null ? fieldLengthValueDB.hashCode() : 0);
        return result;
    }
}
