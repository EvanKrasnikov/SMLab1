package ru.geographer29.polytech.labs;

import ru.geographer29.polytech.labs.performance.PerformanceTest;
import ru.geographer29.polytech.labs.primality.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class Lab2 {
    private static final BigInteger VAL1 = new BigInteger("29510920059813135637");
    private static final BigInteger VAL2 = new BigInteger("6276308096736493660382803551923106172093");
    private static final BigInteger VAL3 = new BigInteger("1150184084366155715336364062573353632049");
    private static final BigInteger VAL4 = new BigInteger("11690697558296293425964217042131951829006657869208639744029092805052036761727731");

    public static void main(String[] args) {
        List<Callable<String>> list = new ArrayList<>();
        list.add(new FermatPrimalityTest(VAL1, 500));
        list.add(new FermatPrimalityTest(VAL2, 500));
        list.add(new FermatPrimalityTest(VAL3, 500));
        list.add(new FermatPrimalityTest(VAL4, 500));
        list.add(new FermatCarmichaelNumber(3));
        list.add(new SolovayStrassenPrimalityTest(VAL1, 500));
        list.add(new SolovayStrassenPrimalityTest(VAL2, 500));
        list.add(new SolovayStrassenPrimalityTest(VAL3, 500));
        list.add(new SolovayStrassenPrimalityTest(VAL4, 500));
        list.add(new SolovayStrassenCarmichaelNumber(3));
        list.add(new MillerRabinPrimalityTest(VAL1, 500));
        list.add(new MillerRabinPrimalityTest(VAL2, 500));
        list.add(new MillerRabinPrimalityTest(VAL3, 500));
        list.add(new MillerRabinPrimalityTest(VAL4, 500));
        list.add(new MillerRabinCarmichaelNumber(3));

        //list.add(new FermatPrimalityTest(BigInteger.valueOf(5), 500));
        //list.add(new FermatPrimalityTest(BigInteger.valueOf(71), 500));
        //list.add(new FermatPrimalityTest(BigInteger.valueOf(999), 500));
        //list.add(new SolovayStrassenPrimalityTest(BigInteger.valueOf(5), 500));
        //list.add(new SolovayStrassenPrimalityTest(BigInteger.valueOf(71), 500));
        //list.add(new SolovayStrassenPrimalityTest(BigInteger.valueOf(999), 500));
        //list.add(new MillerRabinPrimalityTest(BigInteger.valueOf(5), 500));
        //list.add(new MillerRabinPrimalityTest(BigInteger.valueOf(71), 500));
        //list.add(new MillerRabinPrimalityTest(BigInteger.valueOf(999), 500));

        PerformanceTest pf = new PerformanceTest();
        pf.setData(list);
        pf.setFileName("Lab2Report");
        pf.run();

    }


}


