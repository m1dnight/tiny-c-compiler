package MyGCC;

import java.util.HashMap;

/**
 * Class designed to manage strings from printf functions
 **/
public class StringManager {
  private HashMap<String, String> contents;
  
  public StringManager() {
		this.contents = new HashMap<String, String>();
  }

  public String addString(String string) {
    if(string != null)
      if(this.contents.containsKey(string))
        return this.contents.get(string);
      else {
        String label = LabelManager.getStringLabel();
        this.contents.put(string, label);
        return label;
      }
    return null;
  }
  
  public HashMap<String, String> getContents() {
    return (HashMap<String, String>)this.contents.clone();
  }
}
