package thread.lock;

public class DeadLock extends Thread {

    protected Object tool;
    static Object fork1 = new Object();
    static Object fork2 = new Object();

    public DeadLock(Object object) {
        this.tool = object;
        if (tool == fork1) {
            this.setName("test1");
        }
        if (tool == fork2) {
            this.setName("test2");
        }
    }

    @Override
    public void run() {
        if (tool == fork1) {
            synchronized (fork1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (fork2) {
                    System.out.println("test2 start");
                }
            }
        }

        if (tool == fork2) {
            synchronized (fork2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (fork1) {
                    System.out.println("test1 start");
                }
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        DeadLock test1 = new DeadLock(fork1);
        DeadLock test2 = new DeadLock(fork2);

        test1.start();
        test2.start();
        Thread.sleep(1000);
    }
}
