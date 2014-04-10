package Typing;

/**
 * Created by christophe on 4/10/14.
 */
public class VariableTypeInfo extends TypeInfo {
    /**
     * Public construct that takes a typename.
     * These are defined in the enum 'Types'.
     *
     * @param type
     */
    public VariableTypeInfo(Types type) {
        super(type);
    }

    @Override
    public Types ActualType() {
        return this.type;
    }
}
