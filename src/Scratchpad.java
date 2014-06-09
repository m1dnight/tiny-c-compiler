import java.util.LinkedHashMap;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultimap;

/**
 * Created by christophe on 07.06.14.
 */
public class Scratchpad {
    public static void main(String[] args) {
        BiMap<String,String> m = new HashBiMap<String, String>();

        m.put("k1", "v1");
        m.put("k2", "v2");

        System.out.println("k1 = " + m.get("k1"));
        System.out.println("v2 = " + m.inverse().get("v2"));
    }
}
