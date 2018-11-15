package ru.geographer29.polytech.labs.gcd;

import java.math.BigInteger;

public class ExtendedBinaryEuclidean extends AbstractEuclidean {
    final BigInteger two = BigInteger.valueOf(2);

    public ExtendedBinaryEuclidean(BigInteger val1, BigInteger val2) {
        super(val1, val2);
    }

    @Override
    public String call() throws Exception {
        BigInteger result = gcd();
        return sb.append("GSD is ")
                .append(result)
                .append("\n")
                .append("Execution time is ")
                .append(System.currentTimeMillis() - startTime)
                .append(" ms\n")
                .toString();
    }

    private BigInteger gcd() {

        BigInteger
                g = one,
                x = val1,
                y = val2,
                u,
                v,
                a = one,
                b = zero,
                c = zero,
                d = one;

        while (isEven(x) && isEven(y)) {
            x = x.shiftRight(1);
            y = y.shiftRight(1);
            g = g.shiftLeft(1);
        }

        u = x;
        v = y;

        while(!u.equals(zero)) {
            while (isEven(u)) {
                u = u.shiftRight(1);

                if (isEven(a) && isEven(b)) {
                    a = a.shiftRight(1);
                    b = b.shiftRight(1);
                } else {
                    a = a.add(y).shiftRight(1);
                    b = b.subtract(x).shiftRight(1);
                }
            }

            while(isEven(v)) {
                v=v.shiftRight(1);

                if (isEven(c) && isEven(d)) {
                    c = c.shiftRight(1);
                    d = d.shiftRight(1);
                } else {
                    c = c.add(y).shiftRight(1);
                    d = d.subtract(x).shiftRight(1);
                }
            }

            int comp = u.compareTo(v);
            if ((comp == 0)||(comp == 1)) {
                u = u.subtract(v);
                a = a.subtract(c);
                b = b.subtract(d);
            } else {
                v = v.subtract(u);
                c = c.subtract(a);
                d = d.subtract(b);
            }

            appendRoots(c, d);
        }

        BigInteger check = (val1.multiply(c).add(val2.multiply(d)));
        sb.append("Check Ax + By = ")
                .append(check).append("\n");

        return g.multiply(v);
    }

}
