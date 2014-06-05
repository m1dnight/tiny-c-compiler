package Expressions;

import CodeGeneration.OpCodes;
import CodeGeneration.ThreeAddressCode;
import SymbolTable.SymTabInfo;
import Typing.Types;

/**
 * Created by christophe on 06.06.14.
 */
public class ReturnExpression extends Expression {

    /******************************************************************************************************************/
    /************************************ CONSTRUCTORS  ***************************************************************/
    /******************************************************************************************************************/
    public ReturnExpression(SymTabInfo identifier) {
        super(identifier, identifier.typeInfo.ActualType());
    }
    /******************************************************************************************************************/
    /************************************ LOGIC ***********************************************************************/
    /******************************************************************************************************************/
    public ThreeAddressCode ToThreeAddressCode() {
        return new ThreeAddressCode(OpCodes.RETURN, this.identifier);
    }
}
