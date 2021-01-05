import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class FactoryTest
{
  public static void main(String[] args)
  {
    System.out.println("*** Initializing tests ***");

    /*************************************************************************/
    System.out.println();
    System.out.println("1 - Get default for K1");
    try
    {
      MyObjectInterface obj =
                            MyFactory.getInstance().getObject(MyFactoryKey.K1);
      System.out.println("OK - " + obj.myName());
    }
    catch (FactoryException e)
    {
      fail("Failed to get default K1: " + e.getMessage());
    }

    /*************************************************************************/
    System.out.println();
    System.out.println("2 - Get default for K2");
    try
    {
      MyObjectInterface obj =
                            MyFactory.getInstance().getObject(MyFactoryKey.K2);
      System.out.println("OK - " + obj.myName());
    }
    catch (FactoryException e)
    {
      fail("Failed to get default K2: " + e.getMessage());
    }

    /*************************************************************************/
    System.out.println();
    System.out.println("3 - Override default for K2 - not allowed");
    try
    {
      MyFactory.registerImplementation(MyFactoryKey.K2, MyCustomObj.class.getName(), false);
      fail("We should not be allowed to override");
    }
    catch (FactoryException e)
    {
      System.out.println("OK - failed to override: " + e.getMessage());
    }

    /*************************************************************************/
    System.out.println();
    System.out.println("4 - Override default for K2 - allowed");
    try
    {
      MyFactory.registerImplementation(MyFactoryKey.K2, MyCustomObj.class.getName(), true);
      MyObjectInterface obj =
          MyFactory.getInstance().getObject(MyFactoryKey.K2);
      System.out.println("OK - " + obj.myName());
      System.out.println("OK - Override succeded");
    }
    catch (FactoryException e)
    {
      fail("We should be allowed to override: "  + e.getMessage());
    }

    /*************************************************************************/
    System.out.println();
    System.out.println("5 - Register class for K3");
    try
    {
      MyFactory.registerImplementation(MyCustomFactoryKey.K3, MyCustomObj2.class.getName(), true);
      MyObjectInterface obj =
                      MyFactory.getInstance().getObject(MyCustomFactoryKey.K3);
      System.out.println("OK - " + obj.myName());
    }
    catch (FactoryException e)
    {
      fail("Could not get object for K3");
    }

    /*************************************************************************/
    System.out.println();
    System.out.println("6 - K4 not registered");
    try
    {
      MyObjectInterface obj =
                      MyFactory.getInstance().getObject(MyCustomFactoryKey.K4);
      System.out.println("OK - " + obj.myName());
      fail("Got object for K4");
    }
    catch (FactoryException e)
    {
      System.out.println("OK - K4 not registered: " + e.getMessage());
    }

    /*************************************************************************/
    System.out.println();
    System.out.println("7 - Register null for K4");
    try
    {
      MyFactory.registerImplementation(MyCustomFactoryKey.K4, null, true);
      fail("Registered object for K4");
    }
    catch (FactoryException e)
    {
      System.out.println("OK - K4 not registered: " + e.getMessage());
    }

    /*************************************************************************/
    System.out.println();
    System.out.println("8 - Register empty for K4");
    try
    {
      MyFactory.registerImplementation(MyCustomFactoryKey.K4, "", true);
      fail("Registered object for K4");
    }
    catch (FactoryException e)
    {
      System.out.println("OK - K4 not registered: " + e.getMessage());
    }

    /*************************************************************************/
    System.out.println();
    System.out.println("9 - Register invalid class for K4");
    try
    {
      MyFactory.registerImplementation(MyCustomFactoryKey.K4, "InvalidClass", true);
      MyObjectInterface obj =
                      MyFactory.getInstance().getObject(MyCustomFactoryKey.K4);
      System.out.println("OK - " + obj.myName());
      fail("Registered object for K4");
    }
    catch (FactoryException e)
    {
      System.out.println("OK - K4 invalid class: " + e.getMessage());
    }


    /*************************************************************************/
    System.out.println();
    System.out.println("10 - Private constructor for K4");
    try
    {
      MyFactory.registerImplementation(MyCustomFactoryKey.K4, MyCustomObj3.class.getName(), true);
      MyObjectInterface obj =
                      MyFactory.getInstance().getObject(MyCustomFactoryKey.K4);
      System.out.println("OK - " + obj.myName());
      fail("Could get object for K4.");
    }
    catch (FactoryException e)
    {
      System.out.println("OK - Failed to get object for K4" + e.getMessage());
    }

    /*************************************************************************/
    System.out.println();
    System.out.println("*** Tests completed ***");
    System.exit(0);
  }

  /**
   * Reports the error and exits the application.
   * @param error  The error cause.
   */
  private static void fail(String error)
  {
    System.out.println("Failure: " + error);
    System.out.println("*** Tests failed ***");
    System.exit(-1);
  }

  //---------------------------------------------------------------------------
  //
  // MY CUSTOM METHODS.
  //
  //---------------------------------------------------------------------------

  public static enum MyCustomFactoryKey implements MyFactoryKeyInterface
  {
    K3,
    K4;
  }

  public static class MyCustomObj implements MyObjectInterface
  {
    @Override
    public String myName()
    {
      return "custom";
    }
  }

  public static class MyCustomObj2 implements MyObjectInterface
  {
    @Override
    public String myName()
    {
      return "custom 2";
    }
  }


  public static class MyCustomObj3 implements MyObjectInterface
  {
    /**
     * Private constructor
     */
    private MyCustomObj3() {}

    @Override
    public String myName()
    {
      return "custom 3";
    }
  }

  //---------------------------------------------------------------------------
  //
  // LIBRARY METHODS.
  //
  //---------------------------------------------------------------------------

  /**
   * This is your factory, using singleton pattern and reflection to create
   * objects.
   */
  public static class MyFactory
  {
    /**
     * This map holds the supported implementations.
     */
    private static final Map<MyFactoryKeyInterface, String> sImplementations =
                                                               new HashMap<>();

    /**
     * This holds reference to the singleton instance.
     */
    private static MyFactory sInstance = null;

    /**
     * Register the supported implementations.
     */
    static
    {
      try
      {
        registerImplementation(MyFactoryKey.K1,
                               MyObject1.class.getName(),
                               true);
        registerImplementation(MyFactoryKey.K2,
                               MyObject2.class.getName(),
                               true);
      }
      catch (FactoryException e)
      {
        // Ignore it. Usually we should assert this block of code.
      }
    }

    /**
     * Get the singleton instance.
     * @return
     */
    public static synchronized MyFactory getInstance()
    {
      if (sInstance == null)
      {
        sInstance = new MyFactory();
      }

      return sInstance;
    }

    /**
     * Registers a new implementation in this factory.
     *
     * @param key  The implementation key.
     * @param implementationName
     *             The implementation name
     * @param override
     *             Whether it should override any registration.
     *
     * @throws FactoryException
     *             Only thrown when we find a previously registered
     *             implementation but it should not be overridden.
     */
    public static void registerImplementation(MyFactoryKeyInterface key,
                                              String implementationName,
                                              boolean override)
      throws FactoryException
    {
      if (!override)
      {
        String oldValue = sImplementations.get(key);

        if (oldValue != null)
        {
          /*
           * Found an implementation name previously registered and it should
           * not be overridden.
           */
           throw new FactoryException(
                                   "Registerind a new implementation '" +
                                   implementationName +
                                   "' for a not overridable implementation '" +
                                   oldValue + "'.");
        }
      }

      if ((implementationName == null) || implementationName.equals(""))
      {
        throw new FactoryException("Undefined implementation: " +
                                   implementationName + ".");
      }

      sImplementations.put(key, implementationName);
    }

    /**
     * Get an object instance.
     * @param key  The object key.
     *
     * @return  The object for the passed in key.
     * @throws FactoryException
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public MyObjectInterface getObject(MyFactoryKeyInterface key)
      throws FactoryException
    {
      MyObjectInterface result = null;

      if (!sImplementations.containsKey(key))
      {
        new FactoryException("Object not registered for: " + key.name());
      }

      String implementationName = sImplementations.get(key);

      try
      {
        Class implClass = Class.forName(implementationName);
        Constructor<MyObjectInterface> implConst = implClass.getConstructor();
        result = implConst.newInstance();
      }
      catch (ClassNotFoundException e)
      {
        throw new FactoryException("Unknown implementation: " +
                                   implementationName);
      }
      catch (NullPointerException |
             NoSuchMethodException |
             SecurityException |
             InstantiationException |
             IllegalAccessException |
             IllegalArgumentException |
             InvocationTargetException e)
      {
        throw new FactoryException("Failed to create: " + implementationName);
      }

      return result;
    }
  }

  /**
   * Dummy interface to allow us to extends the enumeration MyFactoryKey.
   *
   */
  public static interface MyFactoryKeyInterface
  {
    public String name();
  }

  /**
   * Keys to be used in your factory.
   */
  public static enum MyFactoryKey implements MyFactoryKeyInterface
  {
    K1,
    K2;
  }

  /**
   * This is the return type of your factory.
   */
  public static interface MyObjectInterface
  {
    /**
     * Gets my name.
     * @return  My name.
     */
    public String myName();
  }

  /**
   * Implementation 1.
   */
  public static class MyObject1 implements MyObjectInterface
  {
    @Override
    public String myName()
    {
      return "type 1";
    }
  }

  /**
   * Implementation 2.
   */
  public static class MyObject2 implements MyObjectInterface
  {
    @Override
    public String myName()
    {
      return "type 2";
    }
  }

  /**
   * The custom parser exception.
   */
  public static class FactoryException extends Exception
  {
    /**
     * The serial version UID. Not mandatory.
     */
    private static final long serialVersionUID = 3150590567681337936L;

    /**
     * Constructor.
     * @param message  The exception message.
     */
    public FactoryException(String message)
    {
      /*
       * Call the super constructor.
       */
      super(message);
    }
  }
}

