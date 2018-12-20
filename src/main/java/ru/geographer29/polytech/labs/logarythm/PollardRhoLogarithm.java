package ru.geographer29.polytech.labs.logarythm;

import java.math.BigInteger;

public class PollardRhoLogarithm extends AbsractLogarythm {
    private long launchTime = System.currentTimeMillis();

    private BigInteger
            five = BigInteger.valueOf(5),
            ten = BigInteger.valueOf(10),
            masC[] = new BigInteger[10],
            masD[] = new BigInteger[10],
            masLC[] = new BigInteger[10],
            masLCX[] = new BigInteger[10],
            masLD[] = new BigInteger[10],
            masLDX[] = new BigInteger[10],
            iters[] = new BigInteger[10],
            u = two,
            v = two,
            nxc = u,
            xc = v,
            nxd = u,
            xd = v ,
            c = (a.modPow(u, p).multiply(b.modPow(v, p))).mod(p),
            d = c,
            iter = one;

    public PollardRhoLogarithm(BigInteger a, BigInteger b, BigInteger p, BigInteger q) {
        super(a, b, p, q);
    }

    @Override
    public String call() throws Exception {
        sb.append("Computation using ").append(getClass().getSimpleName())
                .append("\nA = ").append(a)
                .append("\nB = ").append(b)
                .append("\nP = ").append(p)
                .append("\nQ = ").append(r)
                .append("\n");

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
                    .append("Values:\n");

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
                .append(iter)
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

            if (iter.compareTo(ten) == -1) {
                masC[iter.intValue()] = c;
                masD[iter.intValue()] = d;
                masLC[iter.intValue()] = nxc;
                masLCX[iter.intValue()] = xc;
                masLD[iter.intValue()] = nxd;
                masLDX[iter.intValue()] = xd;
                iters[iter.intValue()] = iter;
            } else {
                masC[iter.remainder(five).add(five).intValue()] = c;
                masD[iter.remainder(five).add(five).intValue()] = d;
                masLC[iter.remainder(five).add(five).intValue()] = nxc;
                masLCX[iter.remainder(five).add(five).intValue()] = xc;
                masLD[iter.remainder(five).add(five).intValue()] = nxd;
                masLDX[iter.remainder(five).add(five).intValue()] = xd;
                iters[iter.remainder(five).add(five).intValue()] = iter;
            }
            iter = iter.add(one);

            if (System.currentTimeMillis() - launchTime > 7200000)
                return false;
            if (c.subtract(d).mod(p).equals(zero))
                return true;
        }
    }

}
