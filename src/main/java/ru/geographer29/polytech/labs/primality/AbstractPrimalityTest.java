package ru.geographer29.polytech.labs.primality;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.Callable;

abstract class AbstractPrimalityTest implements Callable<String> {

    final BigInteger
            zero = BigInteger.ZERO,
            one = BigInteger.ONE,
            two = BigInteger.valueOf(2);

    int iterations;
    private int carmichaelNumbers;
    final int STOP_PRINT = 5;
    private Random random = new Random();

    private final BigInteger
            six = BigInteger.valueOf(6),
            twelve = BigInteger.valueOf(12),
            eighteen = BigInteger.valueOf(18);

    StringBuilder sb = new StringBuilder();
    BigInteger val;

    AbstractPrimalityTest(BigInteger val, int iterations) {
        this.val = val;
        this.iterations = iterations;

        sb.append("Computation using ")
                .append(getClass().getSimpleName())
                .append(" algorithm\n")
                .append("Value = ")
                .append(val)
                .append("\n");
    }

    AbstractPrimalityTest(int carmichaelNumbers) {
        this.carmichaelNumbers = carmichaelNumbers;
    }

    boolean isEven(BigInteger val){
        return (val.remainder(two)
                .equals(zero));
    }

    void printPesult(){
        boolean prime = isPrime(val);

        sb.append("Number is ");

        if (prime)
            sb.append("probably prime\n");
        else
            sb.append("definitely composite\n");
    }

    void printBase(BigInteger a){
        sb.append("Base is ")
                .append(a)
                .append("\n");
    }

    void printCompositeBase(BigInteger a){
        sb.append("This base is actually a multiplier ")
                .append("\n");
    }

    BigInteger getRandomBase() {
        while (true) {
            final BigInteger a = new BigInteger (val.bitLength(), random);

            //System.out.println("Random generation bounds " + two + " - " + val.subtract(two));

            if (two.compareTo(a) <= 0 && a.compareTo(val.subtract(two)) < 0)
                return a;

        }
    }

    void generateCarmichaelNumbers(){
        int i = 0;

        BigInteger
                a = one,
                b = one,
                c = one,
                mul = one;

        while (i < carmichaelNumbers) {

            a = a.multiply(six).add(one);
            b = b.multiply(twelve).add(one);
            c = c.multiply(eighteen).add(one);

            if (isPrime(a) && isPrime(b) && isPrime(c)){
                mul = a.multiply(b).multiply(c);

                if (mul.toString(10).length() > 30) {

                    sb
                            .append("Carmichael number is ")
                            .append(mul)
                            .append("\nFactors are a =  ")
                            .append(a)
                            .append(" b = ")
                            .append(b)
                            .append(" c = ")
                            .append(c)
                            .append("\n");

                    i++;

                }

            }

        }

    }

    abstract boolean isPrime(BigInteger val);

}
