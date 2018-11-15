package ru.geographer29.polytech.labs.primality;

import java.math.BigInteger;

public class MillerRabinCarmichaelNumber extends AbstractPrimalityTest{

    public MillerRabinCarmichaelNumber(int carmichaelNumbers) {
        super(carmichaelNumbers);
    }

    @Override
    public String call() throws Exception {
        generateCarmichaelNumbers();
        return sb.toString();
    }

    boolean isPrime(BigInteger val){
        BigInteger a, r, y, j, s = zero;

        if (isEven(val))
            return false;

        r = val.subtract(one);
        while (r.and(one).equals(zero)) {
            s = s.add(one);
            r = r.shiftRight(1);
        }

        for (int i = 0; i < iterations; i++){

            a = getRandomBase();
            y = a.modPow(r, val);


            while (!y.equals(one) && !y.equals(val.subtract(one))){
                j = one;

                int compareJ = j.compareTo(s.subtract(one));
                if (compareJ != 1 && !y.equals(val.subtract(one))){
                    y = y.multiply(y).mod(val);

                    if (y.compareTo(one) == 0)
                        return false;

                    j = j.add(one);
                }

                if (!y.equals(val.subtract(one)))

                    return false;
            }
        }

        return true;
    }

}
