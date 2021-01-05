import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class MyFactory
{
    private static MyFactory sInstance = null;
    private static Map<FactoryKeyInterface, String> sImplementations = new HashMap<>();
    
    private static interface FactoryKeyInterface {}
    
    public static enum FactoryKeys implements FactoryKeyInterface
    {
        TCP_SERVER,
        TCP_CLIENT,
        UDP_SERVER,
        UDP_CLIENT,
        RMI_SERVER,
        RMI_CLIENT;
    }

    private MyFactory() {}

    public static synchronized MyFactory getInstance()
    {
        if (sInstance == null)
        {
            sInstance = new MyFactory();
        }
        return sInstance;
    }

    static
    {
        registerImplementation(MyFactory.FactoryKeys.TCP_SERVER, MyTcp.class.getName(), true);
        registerImplementation(MyFactory.FactoryKeys.TCP_CLIENT, MyTcp.class.getName(), true);
        registerImplementation(MyFactory.FactoryKeys.UDP_SERVER, MyUdp.class.getName(), true);
        registerImplementation(MyFactory.FactoryKeys.UDP_CLIENT, MyUdp.class.getName(), true);
        registerImplementation(MyFactory.FactoryKeys.RMI_SERVER, MyRmi.class.getName(), true);
        registerImplementation(MyFactory.FactoryKeys.RMI_CLIENT, MyRmi.class.getName(), true);        
    }

    public static void registerImplementation(FactoryKeyInterface key,
                                              String implementationName,
                                              boolean override)
    {
        if (!override)
        {
            String oldValue = sImplementations.get(key);
            if (oldValue != null)
            {
                System.out.println("The object is already registered. " + 
                                   "Override not allowed.");
            }
        }
        if ((implementationName == null) || implementationName.equals(""))
        {
            System.out.println("A implementation name is needed " +
                               "to register the object.");
        }
        sImplementations.put(key, implementationName);
    }
    
    @SuppressWarnings({"rawtypes", "unchecked"})
    public MySocket getObject(FactoryKeyInterface key)
    {
        MySocket result = null;

        if (!sImplementations.containsKey(key))
        {
            System.out.println("Implementation for '" + key.toString() + "' not found.");
        }
        String implementationName = sImplementations.get(key);        
        
        try
        {            
            Class implementationClass = Class.forName(implementationName);
            Constructor<MySocket> implementationConstructor = implementationClass.getConstructor();
            result = implementationConstructor.newInstance();
        }
        catch (ClassNotFoundException |
               NoSuchMethodException |
               InstantiationException |
               IllegalAccessException |
               InvocationTargetException e)
        {
            System.out.println("Something is wrong...");
        }

        return result;
    }

}