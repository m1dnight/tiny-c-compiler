package Main;

public class SymTabInfo {
    public String name;
    public TypeInfo typeInfo;

    public SymTabInfo(String name, TypeInfo typeInfo) {
        this.name = name;
        this.typeInfo = typeInfo;
    }

    public void Print()
    {
        System.out.printf("Name:%8s - %8s%n", name, typeInfo.type.name());
    }
}
