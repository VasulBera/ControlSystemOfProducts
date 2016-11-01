package UISuiteTest.LogInData;

import UISuiteTest.SelectDB;

/**
 * Created by sriznych on 30.08.2016.
 */
public class CreateFieldDataRepository {
    private static volatile CreateFieldDataRepository instance = null;

    private CreateFieldDataRepository() {
    }

    public static CreateFieldDataRepository get() {
        if (instance == null) {
            synchronized (CreateFieldData.class) {
                if (instance == null) {
                    instance = new CreateFieldDataRepository();
                }
            }
        }
        return instance;
    }

    public CreateFieldData getDataCreateField() {
        return CreateFieldData.get().
                setFieldName("TestFieldName").
                setFieldColumnName("TestFieldColumnName").
                setFieldDataType("NVARCHAR").
                setFieldLengthValue("10").build();
    }

    public CreateFieldData getDataForCreateCyrillicField() {
        return CreateFieldData.get().
                setFieldName("ТестоваНазва").
                setFieldColumnName("ТестовеЗначенняКолонки").
                setFieldDataType("NVARCHAR").
                setFieldLengthValue("10").build();
    }


    public CreateFieldData getDataForCreateSpecialSymbolsField() {
        return CreateFieldData.get().
                setFieldName("***").
                setFieldColumnName("+++").
                setFieldDataType("NVARCHAR").
                setFieldLengthValue("7").build();
    }

    public CreateFieldData getDataForCreateNumericField() {
        return CreateFieldData.get().
                setFieldName("444").
                setFieldColumnName("555").
                setFieldDataType("NVARCHAR").
                setFieldLengthValue("666").build();
    }

    public CreateFieldData getDataForCreateEmptyField() {
        return CreateFieldData.get().
                setFieldName("").
                setFieldColumnName("").
                setFieldDataType("NVARCHAR").
                setFieldLengthValue("").build();
    }

    public CreateFieldData createFieldForEditVerify() {
        return CreateFieldData.get().
                setFieldName("ChangeFieldName").
                setFieldColumnName("ChangeColumnName").
                setFieldDataType("NVARCHAR").
                setFieldLengthValue("101").build();
    }

    public CreateFieldData getDataCreateFieldFully() {

        return CreateFieldData.get().
                setFieldName("FullRecordField").
                setFieldColumnName("FullRecordColumn").
                setFieldDataType("NVARCHAR").
                setFieldLengthValue("500").build();
    }

    public CreateFieldData verifySimiliarRecord() {
        return CreateFieldData.get().
                setFieldName("FieldNameDouble").
                setFieldColumnName("FieldColumnName").
               setFieldDataType("NVARCHAR").
                setFieldLengthValue("200").
                build();
    }

    public CreateFieldData getDataForCreateFieldInvalidLength() {
        return CreateFieldData.get().
                setFieldName("ghhhhhhhhhhhgfyhfygtffffffffffffffffffhjhhhhhhhhhhhhhhhhhhhhhhhhffffffffffffffffffffffffffffffffffffffffffffffffffffffff1234567898ghhhhhhhhhhhgfyhfygtffffffffffffffffffhjhhhhhhhhhhhhhhhhhhhhhhhhffffffffffffffffffffffffffffffffffffffffffffffffffffffff1234567898").
                setFieldColumnName("ghhhhhhhhhhhgfyhfygtffffffffffffffffffhjhhhhhhhhhhhhhhhhhhhhhhhhffffffffffffffffffffffffffffffffffffffffffffffffffffffff1234567898ghhhhhhhhhhhgfyhfygtffffffffffffffffffhjhhhhhhhhhhhhhhhhhhhhhhhhffffffffffffffffffffffffffffffffffffffffffffffffffffffff1234567898").
                setFieldDataType("NVARCHAR").
                setFieldLengthValue("786754").
                build();
    }

    public CreateFieldData getDataCreateFullRecordForEdit() {
        return CreateFieldData.get().
                setFieldName("FullFieldNameForEdit").
                setFieldColumnName("FullFieldColumnForEdit").
                setFieldDataType("NVARCHAR").
                setFieldLengthValue("1700").build();
    }

    public CreateFieldData dataFo() {
        return CreateFieldData.get().
                setFieldName("FullFieldNameForEdit").
                setFieldColumnName("FullFieldColumnForEdit").
                setFieldDataType("NVARCHAR").
                setFieldLengthValue("1700").build();
    }

    public CreateFieldData getDataForIncorrectEdit() {
        return CreateFieldData.get().
                setFieldName("NameForIncorrectEditing").
                setFieldColumnName("ColumnNameForIncorrectEditing").
                setFieldDataType("NVARCHAR").
                setFieldLengthValue("1700").build();
    }
}
