package CodeGeneration;

/**
 * Expressions in CUP will be of this type.
 * They will contain the intermediate code that the parser creates.
 * It acts as a wrapper class because it still needs to contain
 * TypeInformation as well. (I suppose at this point).
 */
public class CodeContainer {
    private StringBuilder threeAddressCode;

    public String AppendCode(String code)
    {
        this.threeAddressCode.append(code);
        return threeAddressCode.toString();
    }
}
