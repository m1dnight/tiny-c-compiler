package Typing;

/**
 * Created by christophe on 4/10/14.
 */
public class ConstantTypeInfo extends TypeInfo {

    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/
    public ConstantTypeInfo(Types type) {
        super(type);
    }

    /******************************************************************************************************************/
    /************************************ LOGIC ***********************************************************************/
    /******************************************************************************************************************/
    @Override
    public Types ActualType() {
        return getType();
    }

    public boolean equals(Object object)
    {
        boolean sameSame = false;

        if (object != null && object instanceof ConstantTypeInfo)
        {
            ConstantTypeInfo obj = (ConstantTypeInfo) object;
            sameSame = this.getType() == obj.getType();
        }

        return sameSame;
    }
}
