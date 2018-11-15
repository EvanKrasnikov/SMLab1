package ru.geographer29.polytech.labs.primality;

import java.math.BigInteger;

public class FermatPrimalityTest extends AbstractPrimalityTest {

    public FermatPrimalityTest(BigInteger val, int iterations) {
        super(val, iterations);
    }

    @Override
    public String call() throws Exception {
        printPesult();
        return sb.toString();
    }

    @Override
    boolean isPrime(BigInteger val) {

        BigInteger a, r;

        if (isEven(val))
            return false;

        for (int i = 0; i < iterations; i++) {
            a = getRandomBase();
            r = a.modPow(val.subtract(one), val);

            if (i < STOP_PRINT)
                printBase(a);

            if (!r.equals(one)) {
                printCompositeBase(val);
                return false;
            }

        }

        return true;
    }

}
