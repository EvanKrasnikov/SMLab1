package ru.geographer29.polytech.labs.logarythm;

import java.math.BigInteger;

public class PollardRhoLogarithm extends AbsractLogarythm {
    private long launchTime = System.currentTimeMillis();
    private int[] iters = new int[10];

    private BigInteger
            ten = new BigInteger("10"),
            masC[] = new BigInteger[10],
            masD[] = new BigInteger[10],
            masLC[] = new BigInteger[10],
            masLCX[] = new BigInteger[10],
            masLD[] = new BigInteger[10],
            masLDX[] = new BigInteger[10],
            u = two,
            v = two,
            nxc = u,
            xc = v,
            nxd = u,
            xd = v ,
            c = (a.modPow(u, p).multiply(b.modPow(v, p))).mod(p),
            d = c;

    public PollardRhoLogarithm(BigInteger a, BigInteger b, BigInteger p, BigInteger q) {
        super(a, b, p, q);
    }

    @Override
    public String call() throws Exception {
        sb.append("Computation using ").append(getClass().getSimpleName())
                .append("\nA = ").append(a)
                .append("\nB = ").append(b)
                .append("\nP = ").append(p)
                .append("\nQ = ").append(r);

        boolean result = rhoLogarithm();
        if (result){
            sb.append("\n\nValues:\n");

            for (int i = 0; i < 10; i++)
                sb
                        .append("Iteration #").append(iters[i])
                        .append(" c = ").append(masC[i])
                        .append(" d = ").append(masD[i])
                        .append(" log(c) = ").append(masLC[i])
                        .append("+").append(masLCX[i]).append("x")
                        .append(" log(d) = ").append(masLD[i])
                        .append("+").append(masLDX[i]).append("x")
                        .append("\n");

            sb
                    .append("\nFinal c = ").append(c)
                    .append(" Final d = ").append(d)
                    .append("\nEquation -> ").append(xc.subtract(xd))
                    .append("*x ~ ").append(nxd.subtract(nxc))
                    .append("(mod ").append(r).append(")")
                    .append("\nx = ").append(modRes(xc.subtract(xd), nxd.subtract(nxc), r))
                    .append("\nChecking = ").append(a.modPow(modRes(xc.subtract(xd), nxd.subtract(nxc), r), p))
                    .append("\n");

        } else {

            sb
                    .append("Can't solve the logarithm\n")
                    .append("Values:");

            for (int i = 0; i < 10; i++)
                sb
                        .append("Iteration #").append(iters[i])
                        .append(" c = ").append(masC[i])
                        .append(" d = ").append(masD[i])
                        .append(" log(c) = ").append(masLC[i])
                        .append("+").append(masLCX[i]).append("x")
                        .append(" log(d) = ").append(masLD[i])
                        .append("+").append(masLDX[i]).append("x")
                        .append("\n");
        }

        sb
                .append("Total iterations = ")
                .append(iteration)
                .append("\nComputation time = ")
                .append(System.currentTimeMillis() - launchTime)
                .append(" ms\n");

        return sb.toString();
    }

    private BigInteger modRes(BigInteger ax, BigInteger a, BigInteger r) {
        while (ax.compareTo(zero) == -1)
            ax = ax.add(r);

        while (a.compareTo(zero) == -1)
            a = a.add(r);

        while (!(a.mod(ax).equals(zero)))
            a = a.add(r);

        return a.divide(ax).mod(r);
    }

    private BigInteger f(BigInteger x, int n ) {
        if (x.compareTo(p.shiftRight(1)) == -1) {
            if (n == 1) {
                nxc = nxc.add(one);
                return x.multiply(a).mod(p);
            } else {
                nxd = nxd.add(one);
                return x.multiply(a).mod(p);
            }
        } else {
            if (n == 1) {
                xc = xc.add(one);
                return x.multiply(b).mod(p);
            } else {
                xd = xd.add(one);
                return x.multiply(b).mod(p);
            }
        }
    }

    private boolean rhoLogarithm() {
        masC[0] = c;
        masD[0] = d;
        masLC[0] = nxc;
        masLCX[0] = xc;
        masLD[0] = nxd;
        masLDX[0] = xd;

        c = f(c, 1);
        d = f(d, 2);
        d = f(d,  2);

        masC[0] = c;
        masD[0] = d;
        masLC[0] = nxc;
        masLCX[0] = xc;
        masLD[0] = nxd;
        masLDX[0] = xd;

        for (;;) {
            c = f(c, 1);
            d = f(d, 2);
            d = f(d, 2);

            if (iteration < 10) {
                masC[iteration] = c;
                masD[iteration] = d;
                masLC[iteration] = nxc;
                masLCX[iteration] = xc;
                masLD[iteration] = nxd;
                masLDX[iteration] = xd;
                iters[iteration] = iteration;
            } else {
                masC[iteration % 5 + 5] = c;
                masD[iteration % 5 + 5] = d;
                masLC[iteration % 5 + 5] = nxc;
                masLCX[iteration % 5 + 5] = xc;
                masLD[iteration % 5 + 5] = nxd;
                masLDX[iteration % 5 + 5] = xd;
                iters[iteration % 5 + 5] = iteration;
            }
            iteration++;

            if (System.currentTimeMillis() - launchTime > 7200000)
                return false;
            if (c.subtract(d).mod(p).equals(zero))
                return true;
        }
    }

}
