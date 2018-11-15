package ru.geographer29.polytech.labs.logarythm;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

public class PollardRhoLogarythm extends AbsractLogarythm {
    long launchTime = System.currentTimeMillis();
    long stopLimit = 3600000; // 1 hour
    //List<BigInteger> cs = new LinkedList<>();
    //List<BigInteger> ds = new LinkedList<>();
    BigInteger[] cs = new BigInteger[10];
    BigInteger[] ds = new BigInteger[10];
    BigInteger[] cLogs = new BigInteger[10];
    BigInteger[] dLogs = new BigInteger[10];

    public PollardRhoLogarythm(BigInteger a, BigInteger b, BigInteger p, BigInteger q) {
        super(a, b, p, q);
    }

    @Override
    public String call() throws Exception {
        BigInteger r = rhoLogorythm();
        print();
        if (r.compareTo(one) == 0)
            sb.append("Logarythm has not computed, planned termination\n");
        if (r.compareTo(minusOne) == 0)
            sb.append("Logarythm has not found\n");
        return sb.toString();
    }

    private BigInteger f(BigInteger x) {
        return x.multiply(x).add(one);
    }

    private BigInteger rhoLogorythm() {
        BigInteger
                u = two,
                v = two,
                c = a.modPow(u, p).multiply(b.modPow(v, p)),  //check it!
                d = c;

        for (;;) {
            if (System.currentTimeMillis() - launchTime > 3600000 ||
            c.compareTo(d) != 0)
                break;

            c = f(c).mod(p);
            d = f(f(d)).mod(p);

            if (iteration < 5) {
                cs[iteration] = c;
                ds[iteration] = d;
                cLogs[iteration] = c;
                dLogs[iteration] = d;
            } else {
                int pos = (iteration % 5) + 5;
                cs[pos] = c;
                ds[pos] = d;
                cLogs[pos] = c;
                dLogs[pos] = d;
            }

            System.out.println(" C=" + c + " D=" + d);


            iteration++;
        }

        return one;
    }

    private void print() {
        sb.append("Computation using ").append(getClass().getSimpleName())
                .append("\nCompression function = x*x + 5\n");

        for (int i = 1; i < 10; i++) {
            if (cs[i] == null)
                break;

            sb
                    .append("C = ")
                    .append(cs[i])
                    .append(" D = ")
                    .append(ds[i])
                    .append("\n");
        }

        sb
                .append("Total iterations = ")
                .append(iteration)
                .append("\nComputation time = ")
                .append(System.currentTimeMillis() - launchTime)
                .append(" ms\n");
    }


}
