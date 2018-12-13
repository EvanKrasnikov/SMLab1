package ru.geographer29.polytech.labs.gcd;

import java.math.BigInteger;

public class CroppedReminderExtendedEuclidean extends AbstractEuclidean {

    private BigInteger r[] = new BigInteger[1000];
    private BigInteger x[] = new BigInteger[1000];
    private BigInteger y[] = new BigInteger[1000];
    private int i = 1;

    public CroppedReminderExtendedEuclidean(BigInteger val1, BigInteger val2) {
        super(val1, val2);
    }

    @Override
    public String call() throws Exception {
        Thread.currentThread().setName(getClass().getSimpleName());

        BigInteger result = gcd();
        printRoots();

        sb
                .append("GCD is ")
                .append(result)
                .append("\n")
                .append("Execution time is ")
                .append(System.currentTimeMillis() - startTime)
                .append(" ms\n");;

        return sb.toString();
    }

    private BigInteger gcd() {

        BigInteger d, q;
        r[0] = val1;
        r[1] = val2;
        x[0] = one;
        x[1] = zero;
        y[0] = zero;
        y[1] = one;

        while (!(r[i - 1].mod(r[i]).equals(zero))) {

            q = r[i - 1].divide(r[i]);
            r[i + 1] = r[i - 1].mod(r[i]);

            if (r[i + 1].abs().compareTo(r[i].abs().shiftRight(1)) == 1 ||
                    (r[i + 1].abs().equals(r[i].abs().shiftRight(1)))) {

                r[i + 1] = r[i].subtract(r[i + 1]);
                x[i + 1] = q.add(one).multiply(x[i]).subtract(x[i - 1]);
                y[i + 1] = q.add(one).multiply(y[i]).subtract(y[i - 1]);

            } else {

                x[i + 1] = x[i - 1].subtract(q.multiply(x[i]));
                y[i + 1] = y[i - 1].subtract(q.multiply(y[i]));

            }

            i++;
        }

        return r[i];
    }

    private void printRoots() {
        for (int j = 0; j < i + 1; j++)
            sb
                    .append("Iteration #").append(j)
                    .append(" r = ").append(r[j])
                    .append(" x = ").append(x[j])
                    .append(" y = ").append(y[j])
                    .append("\n");

    }

}
