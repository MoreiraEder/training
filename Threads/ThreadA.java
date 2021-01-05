public class ThreadA extends MyThread {

    public void run() {
        try {
            sleep(5000);
            wait();
            System.out.println("Valor da sharedProperty: " + sharedProperty.toString());
        } catch (InterruptedException interruptedException) {
            System.out.println("Deu pau na thread A!");
        }       
        
    }
}
