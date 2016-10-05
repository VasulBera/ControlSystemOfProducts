package UISuiteTest.LogInData;

/**
 * Created by Salome on 28.08.2016.
 */


interface ICreateFieldName {
    ICreateFieldColumnName setFieldName(String fieldName);
}

interface ICreateFieldColumnName {
    ICreateFieldDataType setFieldColumnName(String fieldColumnName);
}

interface ICreateFieldDataType {
    ICreateFieldLengthValue setFieldDataType(String fieldDataType);
}

interface ICreateFieldLengthValue {
    ICreateFieldDataBuild setFieldLengthValue(String fieldLengthValue);
}

interface ICreateFieldDataBuild {
    CreateFieldData build();
}

public class CreateFieldData implements ICreateFieldName, ICreateFieldColumnName, ICreateFieldDataType, ICreateFieldLengthValue, ICreateFieldDataBuild {

    private String fieldName;
    private String fieldColumnName;
    private String fieldDataType;
    private String fieldLengthValue;

    public CreateFieldData() {
    }

    // static factory - - - - - - - - - -

    public static ICreateFieldName get() {
        return new CreateFieldData();
    }

    // set - - - - - - - - - -

    public ICreateFieldColumnName setFieldName(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }

    public ICreateFieldDataType setFieldColumnName(String fieldColumnName) {
        this.fieldColumnName = fieldColumnName;
        return this;
    }

   public ICreateFieldLengthValue setFieldDataType(String fieldDataType) {
        this.fieldDataType = fieldDataType;
        return this;
    }

    public ICreateFieldDataBuild setFieldLengthValue(String fieldLengthValue) {
        this.fieldLengthValue = fieldLengthValue;
        return this;
    }

    public CreateFieldData build() {
        return this;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldColumnName() {
        return fieldColumnName;
    }

  /*  public DataType getFieldDataType() {
        return fieldDataType;
    }*/

    public String getFieldLengthValue() {
        return fieldLengthValue;
    }

    @Override
    public String toString() {
        return "CreateFieldData{" +
                "fieldName='" + fieldName + '\'' +
                ", fieldColumnName='" + fieldColumnName + '\'' +
              // ", fieldDataType=" + fieldDataType +
                ", fieldLengthValue='" + fieldLengthValue + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreateFieldData that = (CreateFieldData) o;

        if (fieldName != null ? !fieldName.equals(that.fieldName) : that.fieldName != null) return false;
        if (fieldColumnName != null ? !fieldColumnName.equals(that.fieldColumnName) : that.fieldColumnName != null)
            return false;
        if (fieldDataType != that.fieldDataType) return false;
        return fieldLengthValue != null ? fieldLengthValue.equals(that.fieldLengthValue) : that.fieldLengthValue == null;

    }

}
