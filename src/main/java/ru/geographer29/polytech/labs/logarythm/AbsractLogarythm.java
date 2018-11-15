package ru.geographer29.polytech.labs.logarythm;

import java.math.BigInteger;
import java.util.concurrent.Callable;

public abstract class AbsractLogarythm implements Callable<String> {

    BigInteger a,b,p,r;

    public AbsractLogarythm(BigInteger a, BigInteger b, BigInteger p, BigInteger r) {
        this.a = a;
        this.b = b;
        this.p = p;
        this.r = r;
    }

    BigInteger zero = BigInteger.ZERO;
    BigInteger one = BigInteger.ONE;
    BigInteger minusOne = one.negate();
    BigInteger two = BigInteger.valueOf(2);
    StringBuilder sb = new StringBuilder();
    int iteration = 1;

}
