package CodeGeneration;

/**
 * Created by christophe on 4/9/14.
 */
public class VarnameCreator {
    private static VarnameCreator instance = null;
    protected VarnameCreator()
    {
        //Singleton constructor
    }

    public static VarnameCreator getInstance()
    {
        if(instance == null)
            instance =new VarnameCreator();
        return instance;
    }
}
