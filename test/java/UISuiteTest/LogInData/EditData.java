package UISuiteTest.LogInData;

interface IEntityNameEdit {
    IEntitySchemaNameEdit setEntityNameEdit(String entityNameEdit);
}

interface IEntitySchemaNameEdit {
    IEntityTableNameEdit setEntitySchemaNameEdit(String entitySchemaNameEdit);
}

interface IEntityTableNameEdit {
    IFieldNameEdit setEntityTableNameEdit(String entityTableNameEdit);
}

interface IFieldNameEdit {
    IFieldColumnNameEdit setFieldNameEdit(String fieldNameEdit);
}

interface IFieldColumnNameEdit {
    IFieldDataTypeEdit setFieldColumnNameEdit(String fieldColumnNameEdit);
}

interface IFieldDataTypeEdit {
    IFieldLengthValueEdit setFieldDataTypeEdit(String fieldDataTypeEdit);
}

interface IFieldLengthValueEdit {
    IEditDataBuild setLengthValueEdit(String fieldLengthValueEdit);
}

interface IEditDataBuild {
    EditData build();
}

public class EditData implements IEntityNameEdit, IEntitySchemaNameEdit, IEntityTableNameEdit, IFieldNameEdit, IFieldColumnNameEdit, IFieldDataTypeEdit, IFieldLengthValueEdit, IEditDataBuild, IEditData {
    private String entityNameEdit;
    private String entitySchemaNameEdit;
    private String entityTableNameEdit;
    private String fieldNameEdit;
    private String fieldColumnNameEdit;
    private String fieldDataTypeEdit;
    private String fieldLengthValueEdit;

    private EditData() {
    }

    public static IEntityNameEdit get() {
        return new EditData();
    }

    public IEntitySchemaNameEdit setEntityNameEdit(String entityNameEdit) {
        this.entityNameEdit = entityNameEdit;
        return this;
    }

    public IEntityTableNameEdit setEntitySchemaNameEdit(String entitySchemaNameEdit) {
        this.entitySchemaNameEdit = entitySchemaNameEdit;
        return this;
    }

    public IFieldNameEdit setEntityTableNameEdit(String entityTableNameEdit) {
        this.entityTableNameEdit = entityTableNameEdit;
        return this;
    }

    public IFieldColumnNameEdit setFieldNameEdit(String fieldNameEdit) {
        this.fieldNameEdit = fieldNameEdit;
        return this;
    }

    public IFieldDataTypeEdit setFieldColumnNameEdit(String fieldColumnNameEdit) {
        this.fieldColumnNameEdit = fieldColumnNameEdit;
        return this;
    }

    public IFieldLengthValueEdit setFieldDataTypeEdit(String fieldDataTypeEdit) {
        this.fieldDataTypeEdit = fieldDataTypeEdit;
        return this;
    }

    public IEditDataBuild setLengthValueEdit(String fieldLengthValueEdit) {
        this.fieldLengthValueEdit = fieldLengthValueEdit;
        return this;
    }

    public EditData build() {
        return this;
    }

    public String getEntityNameEdit() {
        return entityNameEdit;
    }

    public String getEntitySchemaNameEdit() {
        return entitySchemaNameEdit;
    }

    public String getEntityTableNameEdit() {
        return entityTableNameEdit;
    }

    public String getFieldNameEdit() {
        return fieldNameEdit;
    }

    public String getFieldColumnNameEdit() {
        return fieldColumnNameEdit;
    }

    public String getFieldDataTypeEdit() {
        return fieldDataTypeEdit;
    }

    public String getFieldLengthValueEdit() {
        return fieldLengthValueEdit;
    }

    @Override
    public String toString() {
        return "EditData{" +
                "entityNameEdit='" + entityNameEdit + '\'' +
                ", entitySchemaNameEdit='" + entitySchemaNameEdit + '\'' +
                ", entityTableNameEdit='" + entityTableNameEdit + '\'' +
                ", fieldNameEdit='" + fieldNameEdit + '\'' +
                ", fieldColumnNameEdit='" + fieldColumnNameEdit + '\'' +
                ", fieldDataTypeEdit='" + fieldDataTypeEdit + '\'' +
                ", fieldLengthValueEdit='" + fieldLengthValueEdit + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EditData editData = (EditData) o;
        if (entityNameEdit != null ? !entityNameEdit.equals(editData.entityNameEdit) : editData.entityNameEdit != null)
            return false;
        if (entitySchemaNameEdit != null ? !entitySchemaNameEdit.equals(editData.entitySchemaNameEdit) : editData.entitySchemaNameEdit != null)
            return false;
        if (entityTableNameEdit != null ? !entityTableNameEdit.equals(editData.entityTableNameEdit) : editData.entityTableNameEdit != null)
            return false;
        if (fieldNameEdit != null ? !fieldNameEdit.equals(editData.fieldNameEdit) : editData.fieldNameEdit != null)
            return false;
        if (fieldColumnNameEdit != null ? !fieldColumnNameEdit.equals(editData.fieldColumnNameEdit) : editData.fieldColumnNameEdit != null)
            return false;
        if (fieldDataTypeEdit != null ? !fieldDataTypeEdit.equals(editData.fieldDataTypeEdit) : editData.fieldDataTypeEdit != null)
            return false;
        return fieldLengthValueEdit != null ? fieldLengthValueEdit.equals(editData.fieldLengthValueEdit) : editData.fieldLengthValueEdit == null;

    }
}
