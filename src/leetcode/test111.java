package leetcode;


public class test111 {
    public static void main(String[] args) {
        int arg[] = {1, 2, 3, 8, 5, 6};
        int max = 0;
        int tmp = 0;
        for (int a = 0; a < arg.length; a++) {
            for (int b = 1; b <= arg.length; b++) {
                max = a * b;
                if (max < tmp) {
                    max = tmp;
                }
            }
        }
        System.out.println(max);
    }
}
