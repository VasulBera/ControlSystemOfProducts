package UISuiteTest.LogInData;

/**
 * Created by sriznych on 30.08.2016.
 */

public class CreateEntityDataRepository {

    private static volatile CreateEntityDataRepository instance = null;

    private CreateEntityDataRepository() {
    }

    public static CreateEntityDataRepository get() {
        if (instance == null) {
            synchronized (CreateEntityData.class) {
                if (instance == null) {
                    instance = new CreateEntityDataRepository();
                }
            }
        }
        return instance;
    }

    public CreateEntityData getDataCreateEntity() {
        return CreateEntityData.get().setEntityName("EntityName").
                setEntitySchemaName("EntitySchemaName").
                setEntityTableName("EntityTableName").
                build();
    }

    public CreateEntityData getDataCreateEntityFully() {
        return CreateEntityData.get().setEntityName("FullRecordName").
                setEntitySchemaName("FullRecordSchema").
                setEntityTableName("FullRecordTable").
                build();
    }

    public CreateEntityData getDataForCyrillicEntity() {
        return CreateEntityData.get().
                setEntityName("ТестоваНазва").
                setEntitySchemaName("ТестоваСхема").
                setEntityTableName("ТестовТаблиця").
                build();
    }

    public CreateEntityData getDataForSpecialEntity() {
        return CreateEntityData.get().
                setEntityName("@@@").
                setEntitySchemaName("%%%").
                setEntityTableName("$$$").
                build();
    }

    public CreateEntityData getDataForNumericEntity() {
        return CreateEntityData.get().
                setEntityName("111").
                setEntitySchemaName("222").
                setEntityTableName("333").
                build();
    }

    public CreateEntityData getDataForEmptyEntity() {
        return CreateEntityData.get().
                setEntityName("").
                setEntitySchemaName("").
                setEntityTableName("").
                build();
    }

    public CreateEntityData createEntityForEditVerify() {
        return CreateEntityData.get().
                setEntityName("ChangeName").
                setEntitySchemaName("ChangeSchemaName").
                setEntityTableName("ChangeTableName").
                build();
    }

    public CreateEntityData getDataForCreateFieldInvalidLength() {
        return CreateEntityData.get().
                setEntityName("ghhhhhhhhhhhgfyhfygtffffffffffffffffffhjhhhhhhhhhhhhhhhhhhhhhhhhffffffffffffffffffffffffffffffffffffffffffffffffffffffff1234567898").
                setEntitySchemaName("ghhhhhhhhhhhgfyhfygtffffffffffffffffffhjhhhhhhhhhhhhhhhhhhhhhhhhffffffffffffffffffffffffffffffffffffffffffffffffffffffff1234567898ghhhhhhhhhhhgfyhfygtffffffffffffffffffhjhhhhhhhhhhhhhhhhhhhhhhhhffffffffffffffffffffffffffffffffffffffffffffffffffffffff1234567898").
                setEntityTableName("ghhhhhhhhhhhgfyhfygtffffffffffffffffffhjhhhhhhhhhhhhhhhhhhhhhhhhffffffffffffffffffffffffffffffffffffffffffffffffffffffff1234567898ghhhhhhhhhhhgfyhfygtffffffffffffffffffhjhhhhhhhhhhhhhhhhhhhhhhhhffffffffffffffffffffffffffffffffffffffffffffffffffffffff1234567898").
                build();
    }

    public CreateEntityData getDataCreateEntityForEdit() {
        return CreateEntityData.get().setEntityName("EntityNameForEdit").
                setEntitySchemaName("EntitySchemaNameForEdit").
                setEntityTableName("EntityTableNameForEdit").
                build();
    }

    public CreateEntityData dataForIncorrectEntityRecordEdit() {
        return CreateEntityData.get().setEntityName("EntityNameForIncorrectEdit").
                setEntitySchemaName("EntitySchemaNameForIncorrectEdit").
                setEntityTableName("EntityTableNameForIncorrectEdit").
                build();
    }

    public CreateEntityData dataForIncorrectFullRecordEdit() {
        return CreateEntityData.get().setEntityName("FullNameForIncorrectEdit").
                setEntitySchemaName("FullEntitySchemaNameForIncorrectEdit").
                setEntityTableName("FullEntityTableNameForIncorrectEdit").
                build();
    }

    public CreateEntityData getDataCreateFullRecordForDelete() {
        return CreateEntityData.get().setEntityName("FullEntityNameForEdit").
                setEntitySchemaName("FullEntitySchemaNameForEdit").
                setEntityTableName("FullTableNameForEdit").
                build();
    }
}
