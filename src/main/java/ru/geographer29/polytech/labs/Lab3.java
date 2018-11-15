package ru.geographer29.polytech.labs;

import ru.geographer29.polytech.labs.factorization.PollardRhoMethod;
import ru.geographer29.polytech.labs.factorization.PollardRhoOneMethod;
import ru.geographer29.polytech.labs.factorization.SquareSieveMethod;
import ru.geographer29.polytech.labs.performance.PerformanceTest;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class Lab3 {

    private static final BigInteger VAL1 = new BigInteger("26041024739356951487");
    private static final BigInteger VAL2 = new BigInteger("439674356327397358360577222611037436463");
    private static final BigInteger VAL3 = new BigInteger("3105009434349089210070525493333333333336967173595920875125441983440733610209841");

    public static void main(String[] args) {
        List<Callable<String>> list = new ArrayList<>();
        /*
        list.add(new PollardRhoMethod(VAL1));
        list.add(new PollardRhoMethod(VAL2));
        list.add(new PollardRhoMethod(VAL3));
        list.add(new PollardRhoOneMethod(VAL1));
        list.add(new PollardRhoOneMethod(VAL2));
        list.add(new PollardRhoOneMethod(VAL3));
        list.add(new SquareSieveMethod(VAL1));
        list.add(new SquareSieveMethod(VAL2));
        list.add(new SquareSieveMethod(VAL3));
        */

        //list.add(new PollardRhoMethod(new BigInteger("334")));
        //list.add(new PollardRhoMethod(new BigInteger("511")));
        //list.add(new PollardRhoMethod(new BigInteger("1359331")));

        list.add(new PollardRhoOneMethod(new BigInteger("1728239")));
        list.add(new PollardRhoOneMethod(new BigInteger("1557697")));

        PerformanceTest pf = new PerformanceTest();
        pf.setData(list);
        pf.setFileName("Lab3Report");
        pf.run();

    }

}
