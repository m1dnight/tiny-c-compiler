package Main;

import java.util.ArrayList;

public class FunctionTypeInfo extends TypeInfo {

    private ArrayList<TypeInfo> parameters;

    private TypeInfo returnType;
    /**
     * Public construct that takes a typename.
     * These are defined in the enum 'Types'.
     * @param typename
     */
    public FunctionTypeInfo(TypeInfo returnType, ArrayList<TypeInfo> paramlist) {
        super(Types.FUNCTION);
        this.returnType = returnType;
        this.parameters = paramlist;
    }
}
