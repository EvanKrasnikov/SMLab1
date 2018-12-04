package ru.geographer29.polytech.labs.logarythm;

import java.math.BigInteger;

public class PollardRhoLogarythm extends AbsractLogarythm {
    private long launchTime = System.currentTimeMillis();
    private BigInteger five = BigInteger.valueOf(5);

    public PollardRhoLogarythm(BigInteger a, BigInteger b, BigInteger p, BigInteger q) {
        super(a, b, p, q);
    }

    @Override
    public String call() throws Exception {
        printHeader();
        return sb.toString();
    }

    private BigInteger f(BigInteger x) {
        if (x.compareTo(p.shiftRight(1)) == -1)
            return x.multiply(a).mod(p);
        else
            return x.multiply(b).mod(p);
    }

    private BigInteger rhoLogarythm() {
        BigInteger
                u = two,
                v = two,
                c, d;

        c = a.pow(u.intValue()).multiply(b.pow(v.intValue()));
        c = c.mod(p);
        d = c;

        //System.out.println(" a = " + a + " b = " + b);
        //System.out.println(" p = " + p + " q = " + r);
        //System.out.println(" c = " + c + " d = " + d + "\n");

        for (;;) {
            saveLine(c, d);

            c = f(c).mod(p);
            d = f(f(d)).mod(p);

            //System.out.println(" C = " + c + " D = " + d);

            iteration++;

            if (System.currentTimeMillis() - launchTime > 3600000 ||
                    c.subtract(d).mod(p).equals(zero))
                break;
        }

        return one;
    }

    private void printHeader() {
        sb.append("Computation using ").append(getClass().getSimpleName())
                .append("\nCompression function = x*x + 5\n");

        BigInteger r = rhoLogarythm();

        if (r.compareTo(one) == 0)
            sb.append("Logarythm has not computed, planned termination\n");
        if (r.compareTo(minusOne) == 0)
            sb.append("Logarythm has not found\n");

        sb
                .append("Total iterations = ")
                .append(iteration)
                .append("\nComputation time = ")
                .append(System.currentTimeMillis() - launchTime)
                .append(" ms\n");
    }

    private void saveLine(BigInteger c, BigInteger d) {
        sb
                .append(" C = ")
                .append(c)
                .append(" D = ")
                .append(d)
                .append("\n");
    }


}
