package UISuiteTest.ConstantUtils;

import UISuiteTest.LogInData.CreateEntityData;
import UISuiteTest.LogInData.CreateEntityDataRepository;
import UISuiteTest.LogInData.CreateFieldDataRepository;
import org.junit.Test;

import static UISuiteTest.LogInData.DataType.NVARCHAR;

/**
 * Created by sriznych on 29.08.2016.
 */
public interface constatntValues {
    //Create

    String ENTITY_NAME = "EntityName";
    String ENTITY_SCHEMA_NAME = "EntityTableName";
    String ENTITY_TABLE_NAME = "TestEntityTableName";


    String FIELD_NAME = "TestFieldNameTestEntity";
    String FIELD_COLUMN_NAME = "TestFieldColumnName";
    String FIELD_LENGTH_VALUE = "10";

    //Edit
    String ENTITY_NAME_EDITED = "EditedEntityName";
    String ENTITY_SCHEMA_NAME_EDITED = "EditedEntitySchemaName";
    String ENTITY_TABLE_NAME_EDITED = "EditedEntityTable";
    String FIELD_NAME_EDITED = "EditedFieldName";
    String FIELD_COLUMN_NAME_EDITED = "EditedColumnName";
    String FIELD_LENGTH_VALUE_EDITED = "200";

    String FULL_RECORD__SCHEMA_NAME = "TESTENTITYSCHEMA";
    String FULL_RECORD_TABLE_NAME = "TESTENTITYTABLE";
    String FULL_RECORD_FIELD_NAME = "TestFieldNameFully";
    String FULL_RECORD__COLUMN_NAME = "TestFieldColumnNameFully";
    String FULL_RECORD__LENGTH_VALUE = "10";

    String CYRILLIC_SYMBOLS = "ТестоваНазва";
    String NUMERIC_SYMBOLS = "111";
    String EMPTY_FIELDS = " ";
    String SPECIAL_SYMBOLS = "@@@";
    //String TOO_MUCH_LONG_RECORD = "ghhhhhhhhhhhgfyhfygtffffffffffffffffffhjhhhhhhhhhhhhhhhhhhhhhhhhffffffffffffffffffffffffffffffffffffffffffffffffffffffff12345678";
    String TOO_MUCH = "ghhhhhhhhhhhgfyhfygtffffffffffffffffffhjhhhhhhhhhhhhhhhhhhhhhhhhffffffffffffffffffffffffffffffffffffffffffffffffffffffff12345678";

    String ID_FOR_DELETE = "TESTENTITYSCHEMANAMETESTENTITYTABLENAME";

    String ID_FULL_DELETE = "FULLYSCHEMAFULLYTABLE";

    String ERROR_MESSAGE_FOR_TOO_MUCH_LENGTH = "Error:\n" +
            "Entity schema name length can be less then 128 symbols. Pleas check your data! \n" +
            "Field " + "\"" + TOO_MUCH + "\"" + " with type " + "\"" + NVARCHAR + "\" " + " has to be no more then 4000 (786754).Pleas check your data!";

    String ERROR_MESSAGE_FOR_EMPTY_VALIDATION = "Errors\n" +
            "\n" +
            "Name: You must enter a value\n" +
            "Schema Name: You must enter a value\n" +
            "Table Name: You must enter a value\n" +
            "Column Name: You must enter a value";

    String ERROR_MESSAGE_FOR_CYRILLIC_VALIDATION = "Error:\n" +
            "Entity name can contain only symbols, numbers and underscore. Pleas check your data!\n" +
            "Entity Schema name must begin with a letter or underscore and contain only symbols, numbers and underscore. Pleas check your data!\n" +
            "Entity Table name must begin with a letter or underscore and contain only symbols, numbers and underscore. Pleas check your data!\n" +
            "Field Column name of " + "\"" + CreateFieldDataRepository.get().getDataForCreateCyrillicField().getFieldName() + "\"" + " must begin with a letter or underscore and contain only symbols, numbers and underscore. Pleas check your data!\n" +
            "Field name of " + "\"" + CreateFieldDataRepository.get().getDataForCreateCyrillicField().getFieldName() + "\"" + " can contain only symbols, numbers and underscore. Pleas check your data!";

    String ERROR_MESSAGE_FOR_RESERVED_SYMBOLS = "Error:\n" +
            "Entity name can contain only symbols, numbers and underscore. Pleas check your data!\n" +
            "Entity Schema name must begin with a letter or underscore and contain only symbols, numbers and underscore. Pleas check your data!\n" +
            "Entity Table name must begin with a letter or underscore and contain only symbols, numbers and underscore. Pleas check your data!\n" +
            "Field Column name of " + "\"" + CreateFieldDataRepository.get().getDataForCreateSpecialSymbolsField().getFieldName() + "\"" + " must begin with a letter or underscore and contain only symbols, numbers and underscore. Pleas check your data!\n" +
            "Field name of " + "\"" + CreateFieldDataRepository.get().getDataForCreateSpecialSymbolsField().getFieldName() + "\"" + " can contain only symbols, numbers and underscore. Pleas check your data!";

    String ERROR_MESSAGE_FOR_NUMERIC_SYMBOLS = "Error:\n" +
            "Entity Schema name must begin with a letter or underscore and contain only symbols, numbers and underscore. Pleas check your data!\n" +
            "Entity Table name must begin with a letter or underscore and contain only symbols, numbers and underscore. Pleas check your data!\n" +
            "Field Column name of " + "\"" + CreateFieldDataRepository.get().getDataForCreateNumericField().getFieldName() + "\"" + " must begin with a letter or underscore and contain only symbols, numbers and underscore. Pleas check your data!";

    String ERROR_DUPLICATE_MESSAGE = "Error:\n" +
            "Entity with schema name " + "\"" + CreateEntityDataRepository.get().getDataCreateEntityFully().getEntitySchemaName() + "\"" + " and table name " + "\"" + CreateEntityDataRepository.get().getDataCreateEntityFully().getEntityTableName() + "\"" + " is exists. Pleas check your data!";

    String idForEntityTable = "ENTITYSCHEMANAMEENTITYTABLENAME";
    String idForFullRecord = "FULLENTITYSCHEMANAMEFOREDITFULLTABLENAMEFOREDIT";

    String idForFieldTable = "TESTENTITYSCHEMATESTENTITYTABLETESTFIELDCOLUMN";

    String idForEntityTableForEdit = "ENTITYSCHEMANAMEFOREDITENTITYTABLENAMEFOREDIT";

    String idEntityFully = "FULLYSCHEMAFULLYTABLE";
    String idFieldFully = "FULLYSCHEMAFULLYTABLE";

    String FULL_RECORD_NAME = "fullyName";
    String FULL_RECORD_SCHEMA = "fullySchema";
    String FULL_RECORD_TABLE = "fullyTable";
}
