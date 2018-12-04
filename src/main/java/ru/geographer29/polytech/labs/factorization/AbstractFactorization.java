package ru.geographer29.polytech.labs.factorization;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.Callable;

public abstract class AbstractFactorization implements Callable<String> {

    final BigInteger zero = BigInteger.ZERO;
    final BigInteger one = BigInteger.ONE;
    final BigInteger two = BigInteger.valueOf(2);
    final BigInteger minusOne = one.negate();
    final private Random random = new Random();

    private BigInteger q, r;

    int iteration = 1;
    BigInteger val;
    StringBuilder sb = new StringBuilder();

    public AbstractFactorization(BigInteger val) {
        this.val = val;
    }

    BigInteger getRandomBase() {
        while (true) {
            final BigInteger a = new BigInteger (val.bitLength(), random);

            //System.out.println("Random generation bounds " + two + " - " + val.subtract(two));

            if (two.compareTo(a) <= 0 && a.compareTo(val.subtract(two)) < 0)
                return a;

        }
    }

    BigInteger gcd(BigInteger val1, BigInteger val2) {
        BigInteger
                lilr = val1,
                midr = val2,
                bigr = one,
                lilx = one,
                midx = zero,
                bigx = zero,
                lily = zero,
                midy = one,
                bigy = one,
                q = zero;

        for (;;) {
            q = lilr.divide(midr);
            bigr = lilr.remainder(midr);

            if (bigr.equals(zero))
                return midr.abs();

            bigx = lilx.subtract(q.multiply(midx));
            lilx = midx;
            midx = bigx;

            bigy = lily.subtract(q.multiply(midy));
            lily = midy;
            midy = bigy;

            lilr = midr;
            midr = bigr;
        }
    }


}
