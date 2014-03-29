package test;

import java.util.Vector;

import javax.swing.JComboBox.KeySelectionManager;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import main.SymbolTable;
import main.Constants.sym_type;
import main.Constants.var_type;
import main.SymbolTable.Keys;
import junit.framework.TestCase;

public class SymbolTableTest extends TestCase {
	
	int x;
	
	public void testTry_to_insert_id() {
		System.out.println("----------------------------------------------");
		System.out.println("------------- try_to_insert_id ---------------");
		System.out.println("----------------------------------------------");
		SymbolTable st = new SymbolTable();
		String name = "var1";
		sym_type kind = sym_type.GLOBAL_VAR;
		var_type type = var_type.FLOAT_TYPE;
		
		Keys k = st.try_to_insert_id(name, kind);
		st.setType(k, type);
		System.out.println("K: "+k+"\t"+k.hashCode());
		Keys k2 = st.try_to_insert_id(name, kind);
		assertNull(k2);
		System.out.println(st);
	}
		
	public void testWork() {
		System.out.println("----------------------------------------------");
		System.out.println("------------------ work ----------------------");
		System.out.println("----------------------------------------------");
		SymbolTable st = new SymbolTable();
		for(sym_type i : sym_type.values())
//			for(var_type j : var_type.values())
			{
				System.out.println("Adding "+i);
				System.out.println(st.try_to_insert_id("var"+i.ordinal(), i));
			}
		for(sym_type i : sym_type.values())
			{
				System.out.println("Re-Adding "+i);
				Keys k = st.try_to_insert_id("var"+i.ordinal(), i);
				System.out.println(k);
				assertNull(k);
			}
		System.out.println(st);
		assert(true);
	}
	

	public void testTry_to_insert_constant() {
		System.out.println("----------------------------------------------");
		System.out.println("----------- try_to_insert_constant------------");
		System.out.println("----------------------------------------------");
		
		SymbolTable st = new SymbolTable();
		Keys k;
		k = st.try_to_insert_constant(1, var_type.SIGNED_TYPE);
		assertNotNull(k);
		k = st.try_to_insert_constant(1, var_type.UNSIGNED_TYPE);
		assertNull(k);
		k = st.try_to_insert_constant(1, var_type.SIGNED_TYPE);
		assertNull(k);
		k = st.try_to_insert_constant(2494967296L, var_type.SIGNED_TYPE);
		assertNull(k);
		float x;
		
		System.out.println(1L<<32);
		
		k = st.try_to_insert_constant(4294967294L, var_type.UNSIGNED_TYPE);
		assertNotNull(k);
		k = st.try_to_insert_constant(1.04535355, var_type.FLOAT_TYPE);
		assertNotNull(k);
		k = st.try_to_insert_constant(1.04535355, var_type.FLOAT_TYPE);
		assertNull(k);
		k = st.try_to_insert_constant(1, var_type.FLOAT_TYPE);
		assertNull(k);
		System.out.println(st);
	}

	public void testLookup_symbol() {
		System.out.println("----------------------------------------------");
		System.out.println("---------------- lookup_symbol ---------------");
		System.out.println("----------------------------------------------");
		
		SymbolTable st = new SymbolTable();
		Keys k = st.try_to_insert_id("var", sym_type.GLOBAL_VAR);
		Keys k2 = st.try_to_insert_id("var", sym_type.GLOBAL_VAR);
		System.out.println("K: "+k+"\tK2: "+k2);
		System.out.println(st);
		assertEquals(k, st.lookup_symbol("var", sym_type.GLOBAL_VAR));
	}

	public void testLookup_constant() {
		System.out.println("----------------------------------------------");
		System.out.println("--------------- lookup_constant --------------");
		System.out.println("----------------------------------------------");
		
		SymbolTable st = new SymbolTable();
		Keys k = st.try_to_insert_constant(1, var_type.SIGNED_TYPE);
		assertNotNull(k);
		Keys k2 = st.try_to_insert_constant(1, var_type.UNSIGNED_TYPE);
		assertNull(k2);
		System.out.println("K: "+k+"\tK2: "+k2);
		System.out.println(st);
	}

	public void testGetName() {
		System.out.println("----------------------------------------------");
		System.out.println("------------------- getName ------------------");
		System.out.println("----------------------------------------------");
		
		SymbolTable st = new SymbolTable();
		st.try_to_insert_id("fdsg", sym_type.FUNCTION);
		st.try_to_insert_id("fsdfsd", sym_type.FUNCTION);
		st.try_to_insert_id("var", sym_type.GLOBAL_VAR);
		Keys k = st.lookup_symbol("var", sym_type.GLOBAL_VAR);
		assertEquals("var", st.getName(k));
		System.out.println(st);		
	}
	
	public void testGetKind() {
		System.out.println("----------------------------------------------");
		System.out.println("------------------- getKind ------------------");
		System.out.println("----------------------------------------------");
		
		SymbolTable st = new SymbolTable();
		st.try_to_insert_id("fdsg", sym_type.FUNCTION);
		st.try_to_insert_id("fsdfsd", sym_type.FUNCTION);
		st.try_to_insert_id("var", sym_type.GLOBAL_VAR);
		Keys k = st.lookup_symbol("var", sym_type.GLOBAL_VAR);
		assertEquals(sym_type.GLOBAL_VAR, st.getKind(k));
		System.out.println(st);
	}

	public void testGetType() {
		System.out.println("----------------------------------------------");
		System.out.println("------------------- getType ------------------");
		System.out.println("----------------------------------------------");
		
		SymbolTable st = new SymbolTable();
		st.try_to_insert_id("fdsg", sym_type.GLOBAL_VAR);
		st.setType(st.lookup_symbol("fdsg", sym_type.GLOBAL_VAR), var_type.SIGNED_TYPE);
		assertEquals(var_type.SIGNED_TYPE, st.getType(st.lookup_symbol("fdsg", sym_type.GLOBAL_VAR)));
		st.try_to_insert_id("fsdfsd", sym_type.LOCAL_VAR);
		st.setType(st.lookup_symbol("fsdfsd", sym_type.LOCAL_VAR), var_type.UNSIGNED_TYPE);
		st.try_to_insert_id("var", sym_type.GLOBAL_VAR);
		Keys k = st.lookup_symbol("var", sym_type.GLOBAL_VAR);
		st.setType(k, var_type.FLOAT_TYPE);
		assertEquals(var_type.FLOAT_TYPE, st.getType(k));
		System.out.println(st);
	}

	public void testAddParamType() {
		System.out.println("----------------------------------------------");
		System.out.println("----------------- addParamType ---------------");
		System.out.println("----------------------------------------------");
		
		SymbolTable st = new SymbolTable();
		Keys k = st.try_to_insert_id("var", sym_type.FUNCTION);
		Vector<var_type> ptypes = st.getParamTypes(k);
		assertEquals(true, ptypes.isEmpty());
		st.addParamType(k, var_type.SIGNED_TYPE);
		st.addParamType(k, var_type.UNSIGNED_TYPE);
		assertEquals(false, ptypes.isEmpty());
		System.out.println(st);
	}

	public void testGetParamTypes()
	{
		System.out.println("----------------------------------------------");
		System.out.println("----------------- getParamType ---------------");
		System.out.println("----------------------------------------------");

		SymbolTable st = new SymbolTable();
		Keys k = st.try_to_insert_id("var", sym_type.FUNCTION);
		Vector<var_type> ptypes = st.getParamTypes(k);
		assertEquals(true, ptypes.isEmpty());
		st.addParamType(k, var_type.SIGNED_TYPE);
		st.addParamType(k, var_type.UNSIGNED_TYPE);
		assertEquals(ptypes, st.getParamTypes(k));
		System.out.println(st);
	}
}
