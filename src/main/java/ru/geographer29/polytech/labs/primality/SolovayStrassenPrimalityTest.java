package ru.geographer29.polytech.labs.primality;

import java.math.BigInteger;

public class SolovayStrassenPrimalityTest extends AbstractPrimalityTest {
    private BigInteger
            three = BigInteger.valueOf(3),
            four = BigInteger.valueOf(4),
            five = BigInteger.valueOf(5),
            eight = BigInteger.valueOf(8);

    public SolovayStrassenPrimalityTest(BigInteger val, int iterations) {
        super(val, iterations);
    }

    @Override
    public String call() throws Exception {
        printPesult();
        return sb.toString();
    }

    public boolean isPrime(BigInteger val) {

        BigInteger a, r ,s;

        if (isEven(val))
            return false;

        for (int i = 0; i < iterations; i++){

            a = getRandomBase();
            r = a.modPow(val.subtract(one).divide(two), val);

            if (i < STOP_PRINT)
                printBase(a);

            if (!r.equals(one) && !r.equals(val.subtract(one))){
                printCompositeBase(a);
                return false;
            }

            s = jacobi(a, val);

            if (s.equals(zero) || !r.equals(s.mod(val))) {
                printCompositeBase(a);
                return false;
            }
        }

        return true;

    }

    private BigInteger jacobi(BigInteger a, BigInteger b){
        int compB = b.compareTo(one);

        if (compB == -1 || isEven(b))
            return zero;

        int j = 1;
        int compA = a.compareTo(zero);

        if (compA == -1){
            a = a.negate();

            if (b.remainder(four).equals(three))
                j = -j;
        }

        while (!a.equals(zero)){

            while (isEven(a)){
                a = a .divide(two);

                if (b.remainder(eight).equals(three) || b.remainder(eight).equals(five))
                    j = -j;
            }

            BigInteger temp = a;
            a = b;
            b = temp;

            if (a.remainder(four).equals(three) && b.remainder(four).equals(three))
                j = -j;

            a = a.remainder(b);
        }

        if (b.equals(one))
            return BigInteger.valueOf(j);

        return zero;
    }

}
