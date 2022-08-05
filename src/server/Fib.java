package server;

import java.math.BigDecimal;

/**
 * @author Aleksandr Polochkin
 * 05.08.2022
 */

public class Fib {

    static public BigDecimal get(int n) {

        if (n < 0) {
            return null;
        }

        if (n == 0 || n == 1) {
            return new BigDecimal(n);
        }

        BigDecimal fibNPrevious = new BigDecimal(0);
        BigDecimal fibN = new BigDecimal(1);
        BigDecimal temp;


        for (int i = 2; i <= n; i++) {
            temp = fibN;
            fibN = fibNPrevious.add(fibN);
            fibNPrevious = temp;
        }

        return fibN;
    }

}
