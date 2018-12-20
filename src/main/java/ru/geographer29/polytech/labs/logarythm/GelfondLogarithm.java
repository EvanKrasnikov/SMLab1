package ru.geographer29.polytech.labs.logarythm;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class GelfondLogarithm extends AbsractLogarythm {

    private BigInteger bases[] = new BigInteger[10];
    private BigInteger indexes[] = new BigInteger[10];
    private long launchTime = System.currentTimeMillis();
    private Map<BigInteger, BigInteger> map = new HashMap<>();
    private BigInteger
            s = new BigDecimal(Math.floor(Math.sqrt(r.doubleValue()))).toBigInteger().add(one),
            s1 = a.modPow(r.subtract(s), p),
            s2,
            step = zero,
            x,
            iTemp,
            five = BigInteger.valueOf(5);

    public GelfondLogarithm(BigInteger a, BigInteger b, BigInteger p, BigInteger r) {
        super(a, b, p, r);
    }

    @Override
    public String call() throws Exception {
        sb
                .append("Computation using ").append(getClass().getSimpleName())
                .append("\nA = ").append(a)
                .append("\nB = ").append(b)
                .append("\nP = ").append(p)
                .append("\nQ = ").append(r)
                .append("\n")
                .append("Number of values in sequence = ").append(s).append("\n");

        boolean result = gelfond();

        for (int i = 0; i < 10; i++) {
            sb
                    .append("s[").append(indexes[i]).append("] = ")
                    .append(bases[i]).append("\n");
        }

        if (gelfond())
            sb
                    .append("Logarithm computed\n")
                    .append("\ni1 = ").append(iTemp)
                    .append(" i2 = ").append(map.get(s2))
                    .append("\nx = ").append(map.get(s2)).append("*").append(s).append("+").append(iTemp)
                    .append("\nx = ").append(x)
                    .append("\nChecking = ").append(a.modPow(x, p)).append("\n");
        else
            sb
                    .append("Can't solve logarithm\n")
                    .append("x = ").append(x).append("\n");

        sb
                .append("\nComputation time = ")
                .append(System.currentTimeMillis() - launchTime)
                .append(" ms\n");

        return sb.toString();
    }

    private boolean gelfond() {
        while (step.compareTo(s) == -1 && System.currentTimeMillis() - launchTime < 7200000) {
            map.put(b.multiply(s1.modPow(step, p)).mod(p), step);

            if (step.compareTo(five) == -1) {
                bases[step.intValue()] = b.multiply(s1.modPow(step, p)).mod(p);
                indexes[step.intValue()] = step;
            } else {
                bases[step.intValue() % 5 + 5] = b.multiply(s1.modPow(step, p)).mod(p);
                indexes[step.intValue() % 5 + 5] = step;
            }

            step = step.add(one);
        }

        if (System.currentTimeMillis() - launchTime > 7200000)
            return false;

        for (BigInteger i = zero; i.compareTo(s) == -1; i = i.add(one)) {
            s2 = a.modPow(i, p);
            iTemp = i;
            x = map.get(s2).multiply(s).add(i).mod(p);

            if (map.containsKey(s2))
                return true;
        }

        return false;
    }

}
