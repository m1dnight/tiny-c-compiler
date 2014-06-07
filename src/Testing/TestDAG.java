package Testing;

import Assembly.BasicBlock;
import CodeGeneration.OpCodes;
import CodeGeneration.ThreeAddressCode;
import Optimisations.DAGraph;
import SymbolTable.IntegerSymTabInfo;
import SymbolTable.SymTabInfo;
import SymbolTable.VariableSymTabInfo;
import Typing.ConstantTypeInfo;
import Typing.TypeInfo;
import Typing.Types;
import Typing.VariableTypeInfo;
import com.sun.org.apache.xpath.internal.operations.Variable;

import java.util.ArrayList;

/**
 * Created by christophe on 07.06.14.
 */
public class TestDAG {

    public static void main(String[] args) {
        ArrayList<ThreeAddressCode> code = new ArrayList<ThreeAddressCode>();
        ThreeAddressCode t1 = new ThreeAddressCode(OpCodes.A2TIMES, GenerateVariable("x"), GenerateConstant(4), GenerateVariable("t1"));
        ThreeAddressCode t2 = new ThreeAddressCode(OpCodes.A1MINUS, GenerateVariable("a"), null, GenerateVariable("t2"));
        ThreeAddressCode t3 = new ThreeAddressCode(OpCodes.A2MINUS, GenerateVariable("t2"), GenerateConstant(4), GenerateVariable("t3"));
        ThreeAddressCode t4 = new ThreeAddressCode(OpCodes.A2PLUS, GenerateVariable("t3"), GenerateVariable("t1"), GenerateVariable("t4"));
        ThreeAddressCode t5 = new ThreeAddressCode(OpCodes.A1MINUS, GenerateVariable("b"), null, GenerateVariable("t5"));
        code.add(t1);
        code.add(t2);
        code.add(t3);
        code.add(t4);
        code.add(t5);
        BasicBlock block = new BasicBlock();
        block.setTacs(code);
        DAGraph.GenerateGraph(block);
    }


    private static VariableSymTabInfo GenerateVariable(String name) {
        TypeInfo type = new VariableTypeInfo(Types.INTEGER);
        VariableSymTabInfo v = new VariableSymTabInfo(type, name);
        return v;
    }

    private static IntegerSymTabInfo GenerateConstant(int val)
    {
        return new IntegerSymTabInfo(val);
    }
}
