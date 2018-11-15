package ru.geographer29.polytech.labs.gcd;

import java.math.BigInteger;

public class CroppedReminderExtendedEuclidean extends AbstractEuclidean {

    public CroppedReminderExtendedEuclidean(BigInteger val1, BigInteger val2) {
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
                .append(" ms\n");;

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
                q = zero,
                c1 = zero,
                c2 = zero;

        for (;;) {
            appendRoots(midx, midy);
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


            c1 = bigr.abs();
            c2 = lilr.abs().shiftRight(1);

            if (c2.compareTo(c1) == 1) {
                bigr = bigr.subtract(midr);
                bigx = bigx.subtract(midx);
                bigy = bigy.subtract(midy);
            }

        }

    }

}
