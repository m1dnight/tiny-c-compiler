package MyGCC;

import java.util.HashMap;

/**
 * Manages labels specific to different needsm such as functions,
 * logical jumps, strings and static variables
 **/
public class LabelManager {
  
  static private int nbFunctions = 0;
  static private int labelNb = 0;

  static private int stringLabel = 0;

	static private int staticLabel = 0;
	static private HashMap<String, Integer> labels = new HashMap<String, Integer>();
  
  public static String getStringLabel() {
    return ".LC" + stringLabel++;
  }
  
  public static int getFunctionNumber(){
    labelNb = 0;
    return nbFunctions++;
  }
  
  public static String getLabel() {
    labelNb ++;
    return ".L" + nbFunctions + "." + labelNb;
  }
  
  public static String getBeginLabel(int i){
    return ".LFB" + i;
  }
  
  public static String getEndLabel(int i){
    return ".LFE" + i;
  }

	public static String getStaticLabel(String name) {
		if(labels.containsKey(name))
			return labels.get(name).toString();
		labels.put(name, new Integer(staticLabel));
		return labels.get(name).toString();
	}
}
