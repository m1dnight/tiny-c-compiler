package MyGCC;

public class ContextEntry {
  public Type type;
  public int arraySize;
	public boolean isStatic = false;

  public ContextEntry(Type t, int as, boolean is) {
    this.type = t;
    this.arraySize = as;
		this.isStatic = is;
  }
}


