import java.util.LinkedHashMap;

/**
 * Created by christophe on 07.06.14.
 */
public class Scratchpad {
    public static void main(String[] args) {
        LinkedHashMap<Integer, String> hm = new LinkedHashMap<Integer, String>();
        hm.put(1, "een");
        hm.put(2, "twee");
        hm.put(3, "drie");
        hm.put(1, "one");

        System.out.println(hm);
    }
}
