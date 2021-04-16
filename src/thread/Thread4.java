package thread;

public class Thread4 implements Runnable {


    @Override
    public void run() {
        int b=3;
        for (int a = 0; a < 50; a++) {
            if (a % 2 == 0) {
                b++;
            } else {
                b--;
            }
            System.out.println(b);
        }
    }
}
