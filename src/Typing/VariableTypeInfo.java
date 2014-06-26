package Typing;

/**
 * Created by christophe on 4/10/14.
 */
public class VariableTypeInfo extends TypeInfo {
    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/
    public VariableTypeInfo(Types type) {
        super(type);
    }
    /******************************************************************************************************************/
    /************************************ LOGIC ***********************************************************************/
    /******************************************************************************************************************/
    @Override
    public Types ActualType() {
        return this.getType();
    }
    public boolean equals(Object object)
    {
        boolean sameSame = false;

        if (object != null && object instanceof VariableTypeInfo)
        {
            VariableTypeInfo obj = (VariableTypeInfo) object;
            sameSame = this.getType() == obj.getType();
        }

        return sameSame;
    }
}
