import java.util.Random;

public class Lab1 {
    public static void main(String[] args) {
        Random rnd = new Random();
        int[] w = {19, 17, 15, 13, 11, 9, 7, 5, 3};
        double[] x = new double[10];
        for (int j = 0; j < x.length; j++) x[j] = rnd.nextDouble() * 27 - 13;

        double[][] s = new double[9][10];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 10; j++) {
                switch (w[i]) {
                    case 3, 7, 15, 19:
                        s[i][j] = Math.asin(0.2 * ((x[j] + 0.5) / 27));
                        break;
                    case 13:
                        s[i][j] = Math.atan(Math.cos(Math.tan(Math.cbrt(x[j]))));
                        break;
                    default:
                        s[i][j] = Math.pow(Math.pow(Math.pow(4 / (Math.atan((x[j] + 0.5) / 27) - 0.5), Math.asin((x[j] + 0.5) / 27)) / 0.25, 3), (1 / 4.) / Math.pow(.5 / (Math.cbrt(x[j]) - 1), 3));
                }
            }
        }

        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s[i].length; j++) {
                System.out.printf("%10.2f ", s[i][j]);
            }
            System.out.println();
        }
    }
}