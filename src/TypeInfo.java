/*
 * This class will be created for each declaration we encounter during lexing.
 * Each declaration has a Types variable, which points us to which type we are
 * currently talking about.
 */

public class TypeInfo {
    Types type;

    public TypeInfo(Types typename){
        this.type = typename;
    }
}
