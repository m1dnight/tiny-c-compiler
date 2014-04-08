package Main;


import Typing.FunctionTypeInfo;
import Typing.TypeInfo;
import Typing.Types;

public class SymTabInfo {
    public String name;
    public TypeInfo typeInfo;

    public SymTabInfo(String name, TypeInfo typeInfo) {
        this.name = name;
        this.typeInfo = typeInfo;
    }

    public void Print() {
        System.out.printf("Name:%10s - %10s%n", name, typeInfo.type.name());

        // If the SymTabInfo is for a function we print all parameters as well
        if (typeInfo.type == Types.FUNCTION) {

            //We need to cast. But we are sure so no problem.
            FunctionTypeInfo fti = (FunctionTypeInfo) typeInfo;

            // The parameterlist can be null so we check
            if (fti.parameters != null) {
                System.out.printf("* Parameters:%n");
                for (SymTabInfo param : fti.parameters) {
                    System.out.print("   ");
                    param.Print();
                }
                System.out.printf("* End of parameters%n%n");
            }
        }
    }
}
