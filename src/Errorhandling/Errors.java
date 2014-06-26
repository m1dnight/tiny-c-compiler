package Errorhandling;

/**
 * Created by christophe on 26.06.14.
 */
public class Errors {
    public static void Throw(String message)
    {
        System.err.println(message);
        System.exit(1);
    }
}
