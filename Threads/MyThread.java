import java.util.Properties;

public abstract class MyThread extends Thread {
    protected Properties sharedProperty;

    public MyThread(Properties sharedProperty) {
        super();
        this.sharedProperty = sharedProperty;
    }

    public MyThread() {
        super();
        sharedProperty = null;
    }
}
