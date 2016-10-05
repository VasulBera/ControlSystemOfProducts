package UISuiteTest.HeleHelpUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by sriznych on 17.08.2016.
 */

public class BaseBuilder {

    private String Id_B;
    private String name_B;
    private String schemaName_B;
    private String tableName_B;
    private List<Fields> fieldList_B = new ArrayList<>();

    public BaseBuilder BuildId(String Id_B) {
        this.Id_B = Id_B;
        return this;
    }

    public BaseBuilder BuildName(String name_B) {
        this.name_B = name_B;
        return this;
    }

    public BaseBuilder BuildSchemaName(String schemaName_B) {
        this.schemaName_B = schemaName_B;
        return this;
    }

    public BaseBuilder BuildTableName(String tableName_B) {
        this.tableName_B = tableName_B;
        return this;
    }

   /* public BaseBuilder BuildFieldList(String id_fields, String name_fields, String columnName_fields, DataType type_fields, Integer length_fields) {
        this.fieldList_B.add(new Fields(id_fields, name_fields, columnName_fields, type_fields, length_fields));
        return this;
    }*/

       public BaseBuilder BuildFieldList(Fields fields) {
        this.fieldList_B.add(fields);
        return this;
    }

    public Entities build() {
        Entities base = new Entities();
        base.setId(Id_B);
        base.setName(name_B);
        base.setSchemaName(schemaName_B);
        base.setTableName(tableName_B);
        base.setFieldList(fieldList_B);
        return base;
    }
}
