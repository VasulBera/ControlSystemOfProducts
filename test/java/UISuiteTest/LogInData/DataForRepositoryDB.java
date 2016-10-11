package UISuiteTest.LogInData;

/**
 * Created by sriznych on 29.08.2016.
 */

public class DataForRepositoryDB {
    private static volatile DataForRepositoryDB instance = null;

    private DataForRepositoryDB() {
    }

    public static DataForRepositoryDB get() {
        if (instance == null) {
            synchronized (DataForDB.class) {
                if (instance == null) {
                    instance = new DataForRepositoryDB();
                }
            }
        }
        return instance;
    }

    public DataForDB getDataForDBCheck() {
        return DataForDB.get().setEntityNameDB("FullRecordName").setEntitySchemaNameDB("FullRecordSchema").setEntityTableNameDB("FullRecordTable").setFieldNameDB("FullRecordField").
                setFieldColumnNameDB("FullRecordColumn").setDataTypeDB("NVARCHAR").setLengthValueDB("500").build();
    }
}
