package IntegrationTests.ObjectUtils;

import org.apache.commons.lang3.builder.EqualsBuilder;

import java.util.List;

/**
 * @author Solomiia Riznychok
 * @version 1.0
 * @since 2016-17-08
 * The Entities class represents table 'dbo.entities' in 'Customer' DB.
 */

public class Entities {

    private String id;
    private String name;
    private String schemaName;
    private String tableName;
    private List<Fields> fieldList;

    public void setId(String Id) {
        this.id = Id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setFieldList(List<Fields> fieldList) {
        this.fieldList = fieldList;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public String getTableName() {
        return tableName;
    }

    public List<Fields> getFieldList() {
        return fieldList;
    }

    /**
     * The equals(Object) method was intended for equal two objects.
     * @param 'o' a variable of type Object. 'o' is object which comparing with other object.
     * @return return boolean value true or false.If objects are equal - return true, else return false.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entities other = (Entities) o;

        return new EqualsBuilder().append(getId(), other.getId())
                .append(getName(), other.getName())
                .append(getSchemaName(), other.getSchemaName())
                .append(getTableName(), other.getTableName())
                .append(getFieldList(), other.getFieldList())
                .isEquals();
    }

    @Override
    public String toString() {
        return "Entities{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", schemaName='" + schemaName + '\'' +
                ", tableName='" + tableName + '\'' +
                ", fieldList=" + fieldList +
                '}';
    }
}

