package UISuiteTest.HeleHelpUtil;

import org.apache.commons.lang3.builder.EqualsBuilder;

/**
 * @author Solomiia Riznychok
 * @version 1.0
 * @since 2016-17-08
 * The Fields.java class created for display table 'dbo.fields' in 'Customer' DB.
 */

public class Fields {

    private String id;
    private String name;
    private String ColumnName;
    private DataType type;
    private Integer length;

    public void setName(String name) {
        this.name = name;
    }

    public void setColumnName(String columnName) {
        ColumnName = columnName;
    }

    public void setType(DataType type) {
        this.type = type;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public void setId(String id) { this.id = id;}

    public String getName() {
        return name;
    }

    public String getColumnName() {
        return ColumnName;
    }

    public DataType getType() {
        return type;
    }

    public Integer getLength() {
        return length;
    }

    public String getId() { return id; }

    /**Creates a new Fields object.
     *@param  'id_Internal' display id field in 'dbo.fields', 'id_Internal' - a variable of type String;
     *@param  name_Internal display name field in 'dbo.fields', 'name_Internal' - a variable of type String;
     *@param  ColumnName_Internal display ColumnName field in 'dbo.fields', 'ColumnName_Internal' - a variable of type String;
     *@param  type_Internal display type field in 'dbo.fields', 'type_Internal'- a variable of type DataType;
     *@param  length_Internal display length field in 'dbo.fields', 'length_Internal' - a variable of type Integer; ;
     */

    public Fields(String id_Internal, String name_Internal, String ColumnName_Internal, DataType type_Internal, Integer length_Internal) {
        this.id = id_Internal;
        this.name = name_Internal;
        this.ColumnName = ColumnName_Internal;
        this.type = type_Internal;
        this.length = length_Internal;
    }

    /**
     * This method compares two objects.
     * @param 'o' a variable of type Object. 'o' is object which comparing with other object
     * @return return boolean value true or false.If objects are equal - return true, else return false.
     * */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fields other = (Fields) o;
        return new EqualsBuilder().append(getId(), other.getId()).
                append(getName(), other.getName()).append(getColumnName(), other.getColumnName()).
                append(getType(), other.getType()).append(getLength(), other.getLength()).build();

    }

    @Override
    public String toString() {
        return "Fields{" +
                "Id_Internal='" + id + '\'' +
                ", name='" + name + '\'' +
                ", ColumnName='" + ColumnName + '\'' +
                ", type='" + type + '\'' +
                ", length=" + length +
                '}';
    }

}
