package UISuiteTest.LogInData;


/**
 * Created by Salome on 28.08.2016.
 */

interface ICreateEntityName {
    ICreateEntitySchemaName setEntityName(String entityName);
}

interface ICreateEntitySchemaName {
    ICreateEntityTableName setEntitySchemaName(String entitySchemaName);
}

interface ICreateEntityTableName {
    ICreateEntityDataBuild setEntityTableName(String entityTableName);
}

interface ICreateEntityDataBuild{
    CreateEntityData build();
}
public class CreateEntityData implements ICreateEntityName, ICreateEntitySchemaName, ICreateEntityTableName, ICreateEntityData, ICreateEntityDataBuild {

    private String entityName;
    private String entitySchemaName;
    private String entityTableName;

    @Override
    public String toString() {
        return "CreateEntityData{" +
                "entityName='" + entityName + '\'' +
                ", entitySchemaName='" + entitySchemaName + '\'' +
                ", entityTableName='" + entityTableName + '\'' +
                '}';
    }

    public CreateEntityData() {
    }

    // static factory - - - - - - - - - -

    public static ICreateEntityName get() {
        return new CreateEntityData();
    }

    // set - - - - - - - - - -

    public ICreateEntitySchemaName setEntityName(String entityName) {
        this.entityName = entityName;
        return this;
    }

    public ICreateEntityTableName setEntitySchemaName(String entitySchemaName) {
        this.entitySchemaName = entitySchemaName;
        return this;
    }

    public ICreateEntityDataBuild setEntityTableName(String entityTableName) {
        this.entityTableName = entityTableName;
        return this;
    }

    public CreateEntityData build() {
        return this;
    }

    public String getEntityName() { return entityName; }

    public String getEntitySchemaName() {
        return entitySchemaName;
    }

    public String getEntityTableName() {
        return entityTableName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreateEntityData that = (CreateEntityData) o;

        if (entityName != null ? !entityName.equals(that.entityName) : that.entityName != null) return false;
        if (entitySchemaName != null ? !entitySchemaName.equals(that.entitySchemaName) : that.entitySchemaName != null)
            return false;
        return entityTableName != null ? entityTableName.equals(that.entityTableName) : that.entityTableName == null;

    }

    @Override
    public int hashCode() {
        int result = entityName != null ? entityName.hashCode() : 0;
        result = 31 * result + (entitySchemaName != null ? entitySchemaName.hashCode() : 0);
        result = 31 * result + (entityTableName != null ? entityTableName.hashCode() : 0);
        return result;
    }
}
