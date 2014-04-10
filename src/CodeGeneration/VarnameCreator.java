package CodeGeneration;

/**
 * Created by christophe on 4/9/14.
 */
public class VarnameCreator {
    private static VarnameCreator instance = null;
    private int    counter = 0;
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

    public String CreateName()
    {
        this.counter++;
        return String.format("_var%d", counter);

    }
}
