package Typing;

/**
 * Created by christophe on 4/10/14.
 */
public class ConstantTypeInfo extends TypeInfo {

    public ConstantTypeInfo(Types type) {
        super(type);
    }
    @Override
    public Types ActualType() {
        return type;
    }
}
