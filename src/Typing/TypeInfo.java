package Typing;
/**
 * This class will be created for each declaration we encounter during lexing.
 * Each declaration has a Types variable, which points us to which type we are
 * currently talking about.
 */

public class TypeInfo {
    public Types type;

    /**
     * Public construct that takes a typename.
     * These are defined in the enum 'Types'.
     * @param typename
     */
    public TypeInfo(Types type){
        this.type = type;
    }
    /**
     * This method is used to compare two TypeInfo's.
     * They are equal if their declared type is the same.
     */
    public boolean equals(TypeInfo other)
    {
        if(other == null)
            return false;
        if(other.type == this.type)
            return true;
        return false;
    }
}
