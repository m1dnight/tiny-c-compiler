package Utils;

/**
 * Created by christophe on 07.06.14.
 */
public class StringUtils {

    public static String repeat(String input, int repeat)
    {
        if(repeat == 0) return "";
        if(repeat == 1) return input;
        if(repeat < 0) throw new Error("StringUtils.repeat: repeat has to be 0 or higher!");

        StringBuilder sb = new StringBuilder();
        while(repeat > 0)
        {
            sb.append(input);
            repeat--;
        }
        return sb.toString();
    }
}
