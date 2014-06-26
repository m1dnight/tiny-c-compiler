package Typing;

public abstract class TypeInfo {
    private Types type;

    public TypeInfo(Types type) {
        this.type = type;
    }

    /**
     * This abstract method should return the actual type.
     * For functions this is their return type.
     * For variables simply their type.
     * For arrays the type of the array.
     * @return
     */
    public abstract Types ActualType();

    public Types getType() {
        return type;
    }

    public void setType(Types type) {
        this.type = type;
    }
}
