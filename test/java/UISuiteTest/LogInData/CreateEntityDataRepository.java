package UISuiteTest.LogInData;

import UISuiteTest.SelectDB;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Arrays.setAll;

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
        return CreateEntityData.get().setEntityName("fullyName").
                setEntitySchemaName("fullySchema").
                setEntityTableName("fullyTable").
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

    public CreateEntityData verifySimiliarRecord() {
        return CreateEntityData.get().
                setEntityName("EntityNameDouble").
                setEntitySchemaName("EntityShemaNameDouble").
                setEntityTableName("EntityTableNameDouble").
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

    public CreateEntityData getDataCreateFullRecordForDelete() {
        return CreateEntityData.get().setEntityName("FullEntityNameForEdit").
                setEntitySchemaName("FullEntitySchemaNameForEdit").
                setEntityTableName("FullTableNameForEdit").
                build();
    }
}
