package IntegrationTests.ConstantsUtils;


import IntegrationTests.ObjectUtils.DataType;

import static IntegrationTests.ObjectUtils.DataType.*;

/**
 * Created by sriznych on 10.08.2016.
 */

/**
 * @author Solomiia Riznychok
 * @version 1.0
 * @since 2016-18-08
 * The ConstantValues.java class created for definition constant values.
 * @see IntegrationTests.TestsSuite.TestDeleteMethod;
 * @see IntegrationTests.TestsSuite.TestGetMethod;
 * @see IntegrationTests.TestsSuite.TestPatchMethod;
 * @see IntegrationTests.TestsSuite.TestPostMethod;
 * @see IntegrationTests.TestsSuite.TestPutMethod;
 */

public abstract class ConstantValues {
    public static final String ID_ENTITIES = "ENTITIESID";
    public static final String NAME_ENTITIES = "NameEntities";
    public static final String SHEMA_NAME_ENTITIES = "SchemaNameEntities";
    public static final String TABLE_NAME_ENTITIES = "TableNameEntities";
    public static final String ID_FIELDS = "FIELDSID";
    public static final String NAME_FIELDS = "NameFields";
    public static final String COLUMN_NAME_FIELDS = "Column_NameFields";
    public static final DataType TYPE_FIELDS = INT;
    public static final Integer LENGTH_FIELDS = 100;

    public static final String ID_FIELDS_S = "FIELDSID_Price";
    public static final String NAME_FIELDS_S = "NameFields_Price";
    public static final String COLUMN_NAME_FIELDS_S = "Column_NameFields_Price";
    public static final DataType TYPE_FIELDS_S = INT;
    public static final Integer LENGTH_FIELDS_S = 100;




    public static final String ID_ENTITIES_ONLY = "ENTITIESID_ONLY";
    public static final String NAME_ENTITIES_ONLY = "NameEntities_ONLY";
    public static final String SHEMA_NAME_ENTITIES_ONLY = "SchemaNameEntities_ONLY";
    public static final String TABLE_NAME_ENTITIES_ONLY = "TableNameEntities_ONLY";

    public static final String ID_ENTITIES_EDITED_ONLY = "ENTITIESID_ONLY";
    public static final String NAME_ENTITIES_EDITED_ONLY = "NameEntities_ONLY";
    public static final String SHEMA_NAME_ENTITIES_EDITED_ONLY = "SchemaNameEntities_ONLY";
    public static final String TABLE_NAME_ENTITIES_EDITED_ONLY = "TableNameEntities_ONLY";


    public static final String NAME_ENTITIES_EDITED = "NameEdited";
    public static final String SHEMA_NAME_ENTITIES_EDITED = "SchemaNameEdited";
    public static final String TABLE_NAME_ENTITIES_EDITED = "TableNameEdited";
    public static final String ID_FIELDS_EDITED = "FIELDIdEdited";
    public static final String NAME_FIELDS_EDITED = "FIELDNameEdited";
    public static final String COLUMN_NAME_FIELDS_EDITED = "FIELDColumnNameEdited";
    public static final DataType TYPE_FIELDS_EDITED = NVARCHAR;
    public static final Integer LENGTH_FIELDS_EDITED = 46;

    public static final String ID_ENTITIES_SC = "***";
    public static final String NAME_ENTITIES_SC = "@@@";
    public static final String SHEMA_NAME_ENTITIES_SC = "$$$";
    public static final String TABLE_NAME_ENTITIES_SC = "^^^";
    public static final String ID_FIELDS_SC = "%%%";
    public static final String NAME_FIELDS_SC = ";;;";
    public static final String COLUMN_NAME_FIELDS_SC = "{}{}{}";
    public static final DataType TYPE_FIELDS_SC = BIT;
    public static final Integer LENGTH_FIELDS_SC = 3;

    public static final String NAME_ENTITIES_PATCH = "NamePatch";
    public static final String SHEMA_NAME_ENTITIES_PATCH = "SchemaPatchName";
    public static final String TABLE_NAME_ENTITIES_PATCH = "TablePatchName";
    public static final String NAME_FIELDS_PATCH = "FieldNamePatch";
    public static final String COLUMN_NAME_FIELDS_PATCH = "FieldColumnNamePatch";
    public static final DataType TYPE_FIELDS_PATCH = DATE;
    public static final Integer LENGTH_FIELDS_PATCH = 300;

    public static final String NAME_ENTITIES_PATCH_SC = "@@@";
    public static final String SHEMA_NAME_ENTITIES_PATCH_SC = "$$$";
    public static final String TABLE_NAME_ENTITIES_PATCH_SC = "^^^";
    public static final String ID_FIELDS_PATCH_SC = "%%%";
    public static final String NAME_FIELDS_PATCH_SC = ";;;";
    public static final String COLUMN_NAME_FIELDS_PATCH_SC = "{}{}{}";
    public static final DataType TYPE_FIELDS_PATCH_SC = BIT;
    public static final Integer LENGTH_FIELDS_PATCH_SC = 3;

    public static final String NAME_ENTITIES_PUT_SC = "@@@";
    public static final String SHEMA_NAME_ENTITIES_PUT_SC = "$$$";
    public static final String TABLE_NAME_ENTITIES_PUT_SC = "^^^";
    public static final String ID_FIELDS_PUT_SC = "%%%";
    public static final String NAME_FIELDS_PUT_SC = ";;;";
    public static final String COLUMN_NAME_FIELDS_PUT_SC = "{}{}{}";
    public static final DataType TYPE_FIELDS_PUT_SC = BIT;
    public static final Integer LENGTH_FIELDS_PUT_SC = 3;

    public static final String ID_ETITIES_INVALID = "ghhjkkhg55564tbgyhhhhnjh";
    public static final String NAME_ETITIES_INVALID = "ghhhhhhhhhhhgfyhfygtffffffffffffffffffhjhhhhhhhhhhhhhhhhhhhhhhhhffffffffffffffffffffffffffffffffffffffffffffffffffffffff1234567898";
    public static final String SHEMA_NAME_ETITIES_INVALID = "ghhhhhhhhhhhgfyhfygtffffffffffffffffffhjhhhhhhhhhhhhhhhhhhhhhhhhffffffffffffffffffffffffffffffffffffffffffffffffffffffff1234567898ghhhhhhhhhhhgfyhfygtffffffffffffffffffhjhhhhhhhhhhhhhhhhhhhhhhhhffffffffffffffffffffffffffffffffffffffffffffffffffffffff1234567898";
    public static final String TABLE_NAME_ETITIES_INVALID = "ghhhhhhhhhhhgfyhfygtffffffffffffffffffhjhhhhhhhhhhhhhhhhhhhhhhhhffffffffffffffffffffffffffffffffffffffffffffffffffffffff1234567898ghhhhhhhhhhhgfyhfygtffffffffffffffffffhjhhhhhhhhhhhhhhhhhhhhhhhhffffffffffffffffffffffffffffffffffffffffffffffffffffffff1234567898";
    public static final String ID_FIELDS_INVALID = "ghhhhhhhhhhhgfyhfygtffffffffffffffffffhjhhhhhhhhhhhhhhhhhhhhhhhhffffffffffffffffffffffffffffffffffffffffffffffffffffffff1234567898ghhhhhhhhhhhgfyhfygtffffffffffffffffffhjhhhhhhhhhhhhhhhhhhhhhhhhffffffffffffffffffffffffffffffffffffffffffffffffffffffff1234567898";
    public static final String NAME_FIELDS_INVALID = "ghhhhhhhhhhhgfyhfygtffffffffffffffffffhjhhhhhhhhhhhhhhhhhhhhhhhhffffffffffffffffffffffffffffffffffffffffffffffffffffffff1234567898ghhhhhhhhhhhgfyhfygtffffffffffffffffffhjhhhhhhhhhhhhhhhhhhhhhhhhffffffffffffffffffffffffffffffffffffffffffffffffffffffff1234567898";
    public static final String COLUMN_NAME_FIELDS_INVALID = "ghhhhhhhhhhhgfyhfygtffffffffffffffffffhjhhhhhhhhhhhhhhhhhhhhhhhhffffffffffffffffffffffffffffffffffffffffffffffffffffffff1234567898ghhhhhhhhhhhgfyhfygtffffffffffffffffffhjhhhhhhhhhhhhhhhhhhhhhhhhffffffffffffffffffffffffffffffffffffffffffffffffffffffff1234567898";
    public static final Integer LENGTH_FIELDS_NVALID = 2034567891;


}
