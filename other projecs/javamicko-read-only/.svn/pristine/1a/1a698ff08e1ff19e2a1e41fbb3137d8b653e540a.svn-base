package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Stack;
import java.util.Vector;

import com.sun.org.apache.xpath.internal.operations.Equals;

public class SymbolTable implements Constants {

	HashMap<Keys, Values> table = new HashMap<Keys, Values>();
	
	public class Keys implements Constants
	{
		private String name;
		private sym_type kind;
		
		public Keys(String name, sym_type kind)
		{
			this.name = name;
			this.kind = kind;	//sym_type
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public sym_type getKind() {
			return kind;
		}

		public void setKind(sym_type kind) {
			this.kind = kind;
		}
		
		public String toString()
		{
			return "("+name+", "+kind.kind()+")";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((kind == null) ? 0 : kind.hashCode());
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof Keys))
				return false;
			Keys other = (Keys) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (kind == null) {
				if (other.kind != null)
					return false;
			} else if (!kind.equals(other.kind))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}

		private SymbolTable getOuterType() {
			return SymbolTable.this;
		}
	}
	
	protected class Values implements Constants
	{
//		private Vector<var_type> param_types;
		
		private HashMap<value_elements, Object> values;
		
		public Values()
		{
			values = new HashMap<value_elements, Object>();
			values.put(value_elements.SCOPE, 0);
			values.put(value_elements.PARAM_TYPES, new Vector<var_type>());
			values.put(value_elements.VAR_TYPE, var_type.NO_TYPE);
			values.put(value_elements.SHADOWED_BY, new Stack<Shadow>());
		}

		public Values(var_type type)
		{
			values = new HashMap<value_elements, Object>();
			values.put(value_elements.SCOPE, 0);
			values.put(value_elements.PARAM_TYPES, new Vector<var_type>());
			values.put(value_elements.VAR_TYPE, type);
			values.put(value_elements.SHADOWED_BY, new Stack<Shadow>());
		}
		
		public class Shadow
		{
			//TBD
			int k;
			public Shadow()
			{
				k = 0;
			}
		}
		
		public int getScope()
		{
			return (Integer)values.get(value_elements.SCOPE);
		}

		public Vector<var_type> getParamTypes() {
			return (Vector<var_type>)values.get(value_elements.PARAM_TYPES);
		}
		
		public int getParamCount()
		{
			return ((Vector<var_type>)values.get(value_elements.PARAM_TYPES)).size();
		}
		
		public var_type getParamType(int paramNo)
		{
			if(paramNo <= getParamCount())
				return ((Vector<var_type>)values.get(value_elements.PARAM_TYPES)).get(paramNo);
			else
				return null;
		}
		
		public var_type getType()
		{
			return (var_type)values.get(value_elements.VAR_TYPE);
		}
		
		public void setParamTypes(Vector<var_type> param_types)
		{
			values.remove(value_elements.PARAM_TYPES);
			values.put(value_elements.PARAM_TYPES, param_types);
		}
		
		public void setParamType(int index, var_type type)
		{
			if(index < getParamCount());
			((Vector<var_type>)values.get(value_elements.PARAM_TYPES)).get(index);
		}
		
		public void addParamType(var_type type)
		{
			((Vector<var_type>)values.get(value_elements.PARAM_TYPES)).add(type);
		}
		
		public void increaseScope()
		{
			int scope = getScope();
			values.remove(value_elements.SCOPE);
			values.put(value_elements.SCOPE, scope);
		}
		
		public void setScope(int scope)
		{
			values.remove(value_elements.SCOPE);
			values.put(value_elements.SCOPE, scope);			
		}
		
		public void setType(var_type type)
		{
			values.remove(value_elements.VAR_TYPE);
			values.put(value_elements.VAR_TYPE, type);						
		}
				
		public String toString()
		{
			String s = "";
			int scope = getScope();
			var_type type = getType();
			Vector<var_type> param_types = getParamTypes();
//			s+="TYPE = "+type+"  ";
			s+=type+"  ";
			s+="SCOPE = "+scope+"  ";
			s+="PARAMS = [";
			if(!param_types.isEmpty())
			{
				for(var_type v : param_types)
				{
					s+=v.name()+", ";
				}
				//should be "[x, y, z, ..., a, " at this point, delete the last two chars
				return s.substring(0, s.length()-2)+"]";
			}
			return s+"]";
		}
	}
	
	public Keys try_to_insert_id(String name, sym_type kind)
	{
		Keys k = new Keys(name, kind);
		if(table.containsKey(k))
		{
			//it is already in the table
			System.err.println("ERROR: redefinition of '"+name+"'");
			return null;
			
			/** OVDE CE BITI IMPLEMENTIRAN SHADOWING **/
		}
		table.put(k, new Values());
		return k;
	}
	
	public Keys try_to_insert_constant(Number value, var_type type)
	{
		String broj = value.toString();
		Keys k = new Keys(broj, sym_type.CONSTANT);
		if(table.containsKey(k))
			//found it
			return null;
		else
		{
			switch(type)
			{
				case SIGNED_TYPE:
					if(Long.parseLong(broj) < -(1L<<TYPE_BIT_SIZE-1) || Long.parseLong(broj) > (1L << TYPE_BIT_SIZE-1))
					{
						System.err.println("Signed constant "+value+" out of range!");
						return null;
					}
					break;
				case UNSIGNED_TYPE:
					if(Long.parseLong(broj) > (1L << TYPE_BIT_SIZE))
					{
						System.err.println("Unsigned constant "+value+" out of range!");
						return null;
					}
					break;
				case FLOAT_TYPE:
					if(Float.parseFloat(broj) > Float.MAX_VALUE || Float.parseFloat(broj) < Float.MIN_VALUE)
					{
						System.err.println("Float constant "+value+" out of range!");
						return null;
					}
					break;
			}
			table.put(k, new Values(type));
		}
		return k;
	}
	
	public Keys lookup_symbol(String name, sym_type kind)
	{
		Keys k;
/*
		for(var_type vt : var_type.values())
		{
			k = new Keys(name, kind, vt); //pick a var_type
			if(table.containsKey(k))
				//contains the key with the given var_type
				return k;
		}
		//all var_types have been iterated through and we haven't returned
		//so it's definatelly not in the table
*/		
		k = new Keys(name, kind);
		if(table.containsKey(k))
			return k;
		else
			return null;
	}
		
	public Keys lookup_constant(String name)
	{
		Keys k = new Keys(name, sym_type.CONSTANT);
		if(table.containsKey(k))
			return k;
		return null;
	}
	
	public String getName(Keys k)
	{
		if(table.containsKey(k))
			return k.getName();
		return null;
	}
	
	public sym_type getKind(Keys k)
	{
		if(table.containsKey(k))
			return k.getKind();
		return null;
	}
	
	public var_type getType(Keys k)
	{
		if(table.containsKey(k))
			return table.get(k).getType();
		return null;
	}
	
	public var_type getParamType(Keys k, int index)
	{
		if(table.containsKey(k))
		{	//safety-check
			Values v = table.get(k);
			if(index <=v.getParamCount())
				return v.getParamType(index);
			else
				System.err.println("Index out of range in getParamType: "+index);
			return null;
		}
		else
			System.err.println("Key "+k+" not contained in the getParamType call.");
		return null;
	}
	
	public Vector<var_type> getParamTypes(Keys k)
	{
		if(table.containsKey(k))
		{	//safety-check
			Values v = table.get(k);
			return v.getParamTypes();
		}
		else
			System.err.println("Key "+k+" not contained in the getParamTypes call.");
		return null;		
	}
	
	public void setType(Keys k, var_type type)
	{
		if(table.containsKey(k))
		{
			Values v = table.get(k);
			v.setType(type);
		}
		else
			System.err.println("Key "+k+" not contained in the setType call.");
	}
	
	public void setParamType(Keys k, int index, var_type type)
	{
		if(table.containsKey(k))
		{	//sadrzhi kluch.
			Values v = table.get(k);
			if(index<=v.getParamCount())	//bound-check
				v.setParamType(index, type);
			else
				System.err.println("Index out of range in setParamType: "+index);
		}
		else
			System.err.println("Key "+k+" not contained in the setParamType call.");
	}
	
	public void setParamTypes(Keys k, Vector<var_type> param_types)
	{
		if(table.containsKey(k))
		{	//sadrzhi kluch.
			Values v = table.get(k);
			v.setParamTypes(param_types);
		}
		else
			System.err.println("Key "+k+" not contained in the setParamTypes call.");		
	}
	
	public void addParamType(Keys k, var_type type)
	{
		if(table.containsKey(k))
		{
			Values v = table.get(k);
			v.addParamType(type);
		}
	}
	
	public SymbolTable()
	{	//initialize the symtable.
		table = new HashMap<Keys, Values>();
		
		for(int i = 0; i<=Constants.WORKING_REG_NUMBER+1; i++)
		{
			Keys k = new Keys("%"+i, sym_type.WORKING_REGISTER);
			table.put(k, new Values());
		}
	}
	
	public String toString()
	{
		String s="";
/*		System.out.println("\nSYMBOL TABLE");
		System.out.println("      name             kind             type       attr   param_list");
		System.out.println("---------------- ---------------- ---------------- ---- -- -- -- -- --");
*/		
		s+=("\nSYMBOL TABLE\n");
		s+=("      name              kind                   additional info\n");
		s+=("----------------  ----------------  ---------------------------------\n");
		ArrayList<Keys> keys = new ArrayList<Keys>(table.keySet());

		for(Keys k : keys)
		{
			Values v = table.get(k);
			s+= String.format("%16s  %16s  ", k.name, k.kind.kind());
//			System.out.format("%16s %16s %16s %4d", k.name, k.kind.kind(), k.type.name(), v.attribute);
//			System.out.println(v.printParams());
			s+=v+"\n";
		}
		return s;
	}
	
}

/*
typedef struct sym_entry {
char *name;               // ime simbola
unsigned kind;            // vrsta simbola
unsigned type;            // tip vrednosti simbola
int attribute;            // dodatni attribut
unsigned *param_types;    // tipovi parametara
} SYMBOL_ENTRY;

int  get_next_empty_element(void);

unsigned get_attribute(int index);
void     set_param_type(int index, unsigned number, unsigned type);
unsigned get_param_type(int index, unsigned number);
void     set_register_type(int register_index, unsigned type);

void     clear_symbols(unsigned begin_index, unsigned end_index);
void     clear_symtab(void);
void     print_symtab(void);
unsigned logarithm2(unsigned value);
void     init_symtab(void);
*/