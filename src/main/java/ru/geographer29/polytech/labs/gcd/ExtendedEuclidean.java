package ru.geographer29.polytech.labs.gcd;

import java.math.BigInteger;

public class ExtendedEuclidean extends AbstractEuclidean {

    public ExtendedEuclidean(BigInteger val1, BigInteger val2) {
        super(val1, val2);
    }

    @Override
    public String call() throws Exception {
        Thread.currentThread().setName(getClass().getSimpleName());

        BigInteger result = gcd();

        sb
                .append("GCD is ")
                .append(result)
                .append("\n")
                .append("Execution time is ")
                .append(System.currentTimeMillis() - startTime)
                .append(" ms\n");

        return sb.toString();
    }

    BigInteger gcd() {
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
            appendRoots(bigx, bigy);
            q = lilr.divide(midr);
            bigr = lilr.remainder(midr);

            if (bigr.equals(zero)) {
                BigInteger check = (val1.multiply(bigx).add(val2.multiply(bigy)));
                sb.append("Check Ax + By = ")
                        .append(check).append("\n");
                return midr;
            }

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
