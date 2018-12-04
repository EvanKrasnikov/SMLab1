package ru.geographer29.polytech.labs.factorization;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

public class SquareSieveMethod extends AbstractFactorization {
    BigInteger four = BigInteger.valueOf(4);
    BigInteger eight = BigInteger.valueOf(8);

    public SquareSieveMethod(BigInteger val) {
        super(val);
    }

    @Override
    public String call() throws Exception {
        return sb.toString();
    }

    private List<BigInteger> fillInFactorBase(BigInteger bound) {
        BigInteger sqrtBound = bound;
        BigInteger i = two;
        List<BigInteger> primes = new LinkedList<>();

        System.out.println("Building factor base");

        for (; i.compareTo(sqrtBound) != 1;) {
            if (i.compareTo(sqrtBound) == 1)
                break;

            primes.add(i);
            i = i.nextProbablePrime();
        }

        return primes;
    }

    void quadraticSieve() {
        List<BigInteger> primes = fillInFactorBase(new BigInteger("111"));
        List<BigInteger> checkedPrimes = new LinkedList<>();
        //BigInteger p , n = val;

        // check legendre symbol
        for (BigInteger prime : primes) {
            if (legendre(val, prime).equals(one))
                checkedPrimes.add(prime);
        }


    }

    private BigInteger legendre(BigInteger a, BigInteger p) {
    BigInteger result;

        if (a.equals(zero))
            return zero;

        if (a.remainder(two).equals(zero)) {
            result = legendre(a.shiftRight(1), p);

            if (!p.multiply(p).subtract(one).and(eight).equals(zero))
                result = result.negate();
        } else {
            result = legendre(p.remainder(a), a);

            if (!a.subtract(one).multiply(p.subtract(one)).and(four).equals(zero))
                result = result.negate();
        }

        return result;
    }


}
