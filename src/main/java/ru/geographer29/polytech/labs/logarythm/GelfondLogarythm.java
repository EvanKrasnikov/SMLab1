package ru.geographer29.polytech.labs.logarythm;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class GelfondLogarythm extends AbsractLogarythm {

    public GelfondLogarythm(BigInteger a, BigInteger b, BigInteger p, BigInteger r) {
        super(a, b, p, r);
    }

    @Override
    public String call() throws Exception {
        BigInteger g = gelfond(a, b ,p);
        if (g.compareTo(minusOne) == 0)
            sb.append(" ");
        else
            sb.append("Can't solve logarithm\n");


        return null;
    }

    BigInteger gelfond(BigInteger a, BigInteger b, BigInteger n) {
        //BigInteger m = new BigDecimal(Math.ceil()).toBigInteger();
        final int ROOT_PRECISION = 12;
        BigDecimal nDec = new BigDecimal(n);
        //BigDecimal nDec1 = nDec.sqrt(new MathContext(ROOT_PRECISION, RoundingMode.CEILING));
        //BigDecimal nDec1 = nDec.sqrt(new MathContext(12));
        //MathContext
        //BigDecimal rounded = nDec.round(new MathContext(1, RoundingMode.CEILING)); // check math context
        BigInteger m = nDec.toBigInteger();

        BigInteger y = a.modPow(r.subtract(m), p);

        HashMap<BigInteger, BigInteger> map = new HashMap<>();
        BigInteger i = one;
        for (;;) {
            if (i.compareTo(m) != -1)
                break;

            map.put(i, y);
            y = y.multiply(a.modPow(i, p)).mod(p);
            i = i.add(one);
        }

        BigInteger j = zero;
        BigInteger compA;
        for (;;) {
            if (j.compareTo(m) != -1)
                break;

            compA = a.modPow(j, p);

            if (map.containsKey(compA)) {
                return map.get(compA).multiply(m).add(i).mod(p);
            }
        }

        return zero;
    }
}
