package ru.geographer29.polytech.labs.gcd;

import java.math.BigInteger;
import java.util.concurrent.Callable;

abstract class AbstractEuclidean implements Callable<String>{
    StringBuilder sb = new StringBuilder();
    final long startTime = System.currentTimeMillis();

    BigInteger
            val1,
            val2,
            zero = BigInteger.ZERO,
            one = BigInteger.ONE;

    int iteration = 1;

    AbstractEuclidean(BigInteger val1, BigInteger val2) {
        int check = val1.compareTo(val2);

        if (check == 0)
            throw new RuntimeException("Values are equal");

        if (check < 0){
            this.val1 = val2;
            this.val2 = val1;
        }

        if (check > 0){
            this.val1 = val1;
            this.val2 = val2;
        }

        sb.append("Computation using ")
                .append(getClass().getSimpleName())
                .append(" algorithm\n")
                .append("Linear representation formula: Ax + By = GCD(A, B)\n")
                .append("Values: A = " )
                .append(val1)
                .append(" B = ")
                .append(val2)
                .append("\n\n");
    }

    void appendRoots(BigInteger a, BigInteger b){
        sb.append("Iteration ")
                .append(iteration++)
                .append(": Roots are x = ")
                .append(a)
                .append(" y = ")
                .append(b)
                .append("\n");
    }

    boolean isEven(BigInteger a) {
        return a.and(one).equals(zero);
    }

}
