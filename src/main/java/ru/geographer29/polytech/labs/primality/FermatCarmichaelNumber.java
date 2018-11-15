package ru.geographer29.polytech.labs.primality;

import java.math.BigInteger;

public class FermatCarmichaelNumber extends AbstractPrimalityTest {

    public FermatCarmichaelNumber(int carmichaelNumbers) {
        super(carmichaelNumbers);
    }

    @Override
    public String call() throws Exception {
        generateCarmichaelNumbers();
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

            if (!r.equals(one))
                return false;

            if (i < STOP_PRINT)
                printBase(a);
        }

        return true;
    }

}
