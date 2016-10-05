package IntegrationTests.TestData;

import IntegrationTests.ObjectUtils.BaseBuilder;
import IntegrationTests.ObjectUtils.Entities;

import static IntegrationTests.ConstantsUtils.RandomConstantValues.*;
import static IntegrationTests.ObjectUtils.DataType.*;

/**
 * @author Solomiia Riznychok
 * @version 1.0
 * @since 2016-22-07
 * The TestTestDAta interface provides static methods for generation test data.
 */

public interface TestDataForTests {

    /**
     * The testData_Id_BarvinokGoods() generate object with test data for 'BARVINOKGOODS' id value
     * @see Entities
     * @return object of type Entities
     * */

    public static Entities testData_Id_BarvinokGoods() {
        Entities base = new BaseBuilder().
                BuildId("BARVINOKGOODS").
                BuildName("Good_shop").
                BuildSchemaName("Barvinok").
                BuildTableName("goods").
                BuildFieldList("BARVINOKGOODSBARCODE", "bar_code", "BAR_CODE", NVARCHAR, 45).
                BuildFieldList("BARVINOKGOODSPRICE", "price", "PRICE", NVARCHAR, 45).
                BuildFieldList("BARVINOKGOODSPROVIDER", "provider", "PROVIDER", NVARCHAR, 45).
                BuildFieldList("BARVINOKGOODSQUANTITY", "quantity", "QUANTITY", INT, 0).
                BuildFieldList("BARVINOKGOODSTYPE", "type", "TYPE", NVARCHAR, 45).
                build();
        return base;
    }

    /**
     * The testData_Id_AdidasShoes() generate object with test data for 'ADIDASSHOES' id value
     * @see Entities
     * @return object of type Entities
     * */

    public static Entities testData_Id_AdidasShoes() {
        Entities base = new BaseBuilder().
                BuildId("ADIDASSHOES").
                BuildName("Shoes_male").
                BuildSchemaName("Adidas").
                BuildTableName("shoes").
                BuildFieldList("ADIDASSHOESCOLOR", "color", "COLOR", NVARCHAR, 45).
                BuildFieldList("ADIDASSHOESDATA", "date", "DATE", DATE, 0).
                BuildFieldList("ADIDASSHOESPRICE", "price", "PRICE", NVARCHAR, 45).
                BuildFieldList("ADIDASSHOESTYPE", "type", "TYPE", NVARCHAR, 45).
                build();
        return base;
    }

    /**
     * The getValuesForCheckPutMethod() generate object with test data for verify HTTP PUT method option
     * @see Entities
     * @return object of type Entities
     * */

    public static Entities getValuesForCheckPutMethod() {
        Entities base = new BaseBuilder().
                BuildId("ENTITIESID").
                BuildName("NameEdited").
                BuildSchemaName("SchemaNameEdited").
                BuildTableName("TableNameEdited").
                BuildFieldList("FIELDSID", "FIELDNameEdited", "FIELDColumnNameEdited", NVARCHAR, 46).build();
        return base;
    }

    /**
     * The getValuesForCheckPostMethod() generate object with test data for verify HTTP POST method option
     * @see Entities
     * @return object of type Entities
     * */

    public static Entities getValuesForCheckPostMethod() {
        Entities base = new BaseBuilder().
                BuildId("ENTITIESID").
                BuildName("NameEntities").
                BuildSchemaName("SchemaNameEntities").
                BuildTableName("TableNameEntities").
                BuildFieldList("FIELDSID", "NameFields", "ColumnNameFields", INT, 100).
                BuildFieldList("FIELDSID_Price","NameFields_Price", "ColumnNameFieldsPrice", FLOAT, 200 ).build();
        return base;
    }

    /**
     * The getValuesFromEntitiesForPutMethod() generate object with test data for verify HTTP PUT method option
     * @see Entities
     * @return object of type Entities
     * */

    public static Entities getValuesFromEntitiesForPutMethod() {
        Entities base = new BaseBuilder().
                BuildId("ENTITIESID").
                BuildName("NameEntities_ONLY").
                BuildSchemaName("SchemaNameEntities_ONLY").
                BuildTableName("TableNameEntities_ONLY").
                build();
        return base;
    }

    /**
     * The getValuesForCheckDeleteOption() generate object with test data for verify HTTP DELETE method option
     * @see Entities
     * @return object of type Entities
     * */

    public static Entities getValuesForCheckDeleteOption() {
        Entities base = new BaseBuilder().
                BuildId("TESTID").
                BuildName("TestName").
                BuildSchemaName("TestSchemaName").
                BuildTableName("TestTableName").
                BuildFieldList("InternalId", "Internal_Name", "InternalColumnName", INT, 50).build();
        return base;
    }

    /**
     * The getValuesForCheckPatchMethodE() generate object with test data for verify HTTP PATCH method option
     * @see Entities
     * @return object of type Entities
     * */

    public static Entities getValuesForCheckPatchMethodE() {
        Entities base = new BaseBuilder().
                BuildId("ENTITIESID").
                BuildName("NamePatch").
                BuildSchemaName("SchemaPatchName").
                BuildTableName("TablePatchName").
                BuildFieldList("FIELDSID", "NameFields", "Column_NameFields", INT, 100).build();
        return base;
    }

    /**
     * The getValuesForCheckPatchMethodF() generate object with test data for verify HTTP PATCH method option
     * @see Entities
     * @return object of type Entities
     * */

    public static Entities getValuesForCheckPatchMethodF() {
        Entities base = new BaseBuilder().
                BuildId("ENTITIESID").
                BuildName("NameEntities").
                BuildSchemaName("SchemaNameEntities").
                BuildTableName("TableNameEntities").
                BuildFieldList("FIELDSID", "FieldNamePatch", "FieldColumnNamePatch", DATE, 300).build();
        return base;
    }

    /**
     * The getValuesForCheckPatchMethodFSC() generate object with test data for verify HTTP PATCH method option
     * @see Entities
     * @return object of type Entities
     * */

    public static Entities getValuesForCheckPatchMethodFSC() {
        Entities base = new BaseBuilder().
                BuildId("ENTITIESID").
                BuildName("@@@").
                BuildSchemaName("$$$").
                BuildTableName("^^^").
                BuildFieldList("%%%", ";;;", "{}{}{}", BIT, 3).build();
        return base;
    }

    /**
     * The getValuesForVerifyMaxFieldLength() generate object with test data for verify
     * max valid length of fields
     * @see Entities
     * @return object of type Entities
     * */

    public static Entities getValuesForVerifyMaxFieldLength() {
        Entities base = new BaseBuilder().
                BuildId(ID_ETITIES_RANDOM_VALID).
                BuildName(NAME_ETITIES_RANDOM_VALID).
                BuildSchemaName(SHEMA_NAME_ETITIES_RANDOM_VALID).
                BuildTableName(TABLE_NAME_ETITIES_RANDOM_VALID).
                BuildFieldList((ID_ETITIES_RANDOM_VALID + NAME_FIELDS_RANDOM_VALID).toUpperCase(), NAME_FIELDS_RANDOM_VALID, COLUMN_NAME_FIELDS_RANDOM_VALID, INT, LENGTH_FIELDS_RANDOM_VALID).build();
        return base;
    }

}




