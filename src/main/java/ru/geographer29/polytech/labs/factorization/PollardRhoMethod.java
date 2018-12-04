package ru.geographer29.polytech.labs.factorization;

import java.math.BigInteger;

public class PollardRhoMethod extends AbstractFactorization{

    private BigInteger c = BigInteger.valueOf(1);
    private BigInteger five = BigInteger.valueOf(5);
    private BigInteger[] as = new BigInteger[10];
    private BigInteger[] bs = new BigInteger[10];
    private BigInteger[] gcds = new BigInteger[10];
    private long launchTime = System.currentTimeMillis();

    public PollardRhoMethod(BigInteger val) {
        super(val);
    }

    @Override
    public String call() throws Exception {
        BigInteger r = rho();
        print();
        if (r.compareTo(one) == 0)
            sb.append("Factor has not computed, planned termination\n");
        if (r.compareTo(minusOne) == 0)
            sb.append("Factor has not found\n");
        return sb.toString();
    }

    private BigInteger f(BigInteger x) {
        return x.multiply(x).add(five);
    }

    private BigInteger rho() {
        BigInteger
                a = c,
                b = c,
                d;

        for (;;) {
            if (System.currentTimeMillis() - launchTime > 3600000)
               break;

            a = f(a).mod(val);
            b = f(b).mod(val);
            b = f(b).mod(val);

            d = gcd(a.subtract(b), val);

            if (iteration < 5) {
                as[iteration] = a;
                bs[iteration] = b;
                gcds[iteration] = d;
            } else {
                as[iteration % 5 + 5] = a;
                bs[iteration % 5 + 5] = b;
                gcds[iteration % 5 + 5] = d;
            }

            //System.out.println("val=" + val + " A=" + a + " B=" + b + " GCD=" + d);

            if (d.compareTo(one) == 1 && d.compareTo(val) == -1)
                return d;
            if (d.compareTo(val) == 0)
                return minusOne;

            iteration++;
        }

        return one;
    }

    private void print() {
        sb.append("Computation using ").append(getClass().getSimpleName())
                .append("\nCompression function = x*x + 5\n")
                .append("\nValue = ").append(val)
                .append("C = ").append(c).append("\n");

        for (int i = 1; i < 10; i++) {
            if (as[i] == null)
                break;

            sb
                    .append("A = ")
                    .append(as[i])
                    .append(" B = ")
                    .append(bs[i])
                    .append(" GCD = ")
                    .append(gcds[i])
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
