package Expressions;

import CodeGeneration.OpCodes;
import CodeGeneration.ThreeAddressCode;
import SymbolTable.SymTabInfo;

/**
 * Created by christophe on 06.06.14.
 */
public class AssignmentExpression extends Expressions {


    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/
    public AssignmentExpression(SymTabInfo identifier) {
        super(identifier, identifier.typeInfo.ActualType());
    }
    /******************************************************************************************************************/
    /************************************ LOGIC ***********************************************************************/
    /******************************************************************************************************************/
    public ThreeAddressCode ToThreeAddressCode() {
        return new ThreeAddressCode(OpCodes.A0, this.identifier);
    }
}

