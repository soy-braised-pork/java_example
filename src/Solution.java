public class Solution {

    public static String multiply(String num1, String num2) {
        if (isZero(num1) || isZero(num2)) {
            return "0";
        }

        int[] a1 = new int[num1.length()];
        int[] a2 = new int[num2.length()];
        int[] product = new int[num1.length() + num2.length()];

        for (int i = a1.length - 1; i >= 0; i--) {
            for (int j = a2.length - 1; j >= 0; j--) {
                int thisProduct = Character.getNumericValue(num1.charAt(i)) * Character.getNumericValue(num2.charAt(j));
                product[i + j + 1] += thisProduct % 10;
                if (product[i + j + 1] >= 10) {
                    product[i + j + 1] %= 10;
                    product[i + j]++;
                }
                product[i + j] += thisProduct / 10;
                if (product[i + j] >= 10) {
                    product[i + j] %= 10;
                    product[i + j - 1]++;
                }
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < product.length; i++) {
            if (i == 0 && product[i] == 0) {
                continue;
            }
            stringBuilder.append(product[i]);
        }
        return stringBuilder.toString();
    }


    private static boolean isZero(String num) {
        for (char c : num.toCharArray()) {
            if (c != '0') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(multiply("99", "99"));
    }
}
