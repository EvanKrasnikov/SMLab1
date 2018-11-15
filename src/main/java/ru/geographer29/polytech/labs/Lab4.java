package ru.geographer29.polytech.labs;

import ru.geographer29.polytech.labs.logarythm.GelfondLogarythm;
import ru.geographer29.polytech.labs.logarythm.PollardRhoLogarythm;
import ru.geographer29.polytech.labs.performance.PerformanceTest;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class Lab4 {

    private static final BigInteger A = new BigInteger("17");
    private static final BigInteger B = new BigInteger("19");
    private static final BigInteger P = new BigInteger("50091896122438801343");
    private static final BigInteger R = new BigInteger("25045948061219400671");

    public static void main(String[] args) {
        List<Callable<String>> list = new ArrayList<>();

        //list.add(new PollardRhoLogarythm(A, B, P, R));
        //list.add(new GelfondLogarythm(A, B, P, R));

        list.add(new PollardRhoLogarythm(
                new BigInteger("10"),
                new BigInteger("64"),
                new BigInteger("107"),
                new BigInteger("53"))
        );




        PerformanceTest pf = new PerformanceTest();
        pf.setData(list);
        pf.setFileName("Lab4Report");
        pf.run();

    }


}
