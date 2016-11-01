package UISuiteTest.LogInData;

/**
 * Created by sriznych on 29.08.2016.
 */

public class EditDataRepository {
    private static volatile EditDataRepository instance = null;

    private EditDataRepository() {
    }

    public static EditDataRepository get() {
        if (instance == null) {
            synchronized (EditData.class) {
                if (instance == null) {
                    instance = new EditDataRepository();
                }
            }
        }
        return instance;
    }

    public EditData getDataForEditFullRecord() {
        return EditData.get().setEntityNameEdit("EditedEntityName")
                .setEntitySchemaNameEdit("EditedSchemaName").
                        setEntityTableNameEdit("EditedTableName").setFieldNameEdit("EditedField").setFieldColumnNameEdit("EditedColumn").setFieldDataTypeEdit("NVARCHAR").setLengthValueEdit("1800").build();
    }
}
