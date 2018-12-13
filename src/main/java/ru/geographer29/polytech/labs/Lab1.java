package ru.geographer29.polytech.labs;

import ru.geographer29.polytech.labs.gcd.CroppedReminderExtendedEuclidean;
import ru.geographer29.polytech.labs.gcd.ExtendedBinaryEuclidean;
import ru.geographer29.polytech.labs.gcd.ExtendedEuclidean;
import ru.geographer29.polytech.labs.performance.PerformanceTest;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by Ivan Krasnikov
 *
 * 19.11.2018
 *
 */
public class Lab1 {
    private static final BigInteger VAL1 = new BigInteger("31572927126415096277");
    private static final BigInteger VAL2 = new BigInteger("49127443580945833399");
    private static final BigInteger VAL3 = new BigInteger("475367965451193363119213254586106343411");
    private static final BigInteger VAL4 = new BigInteger("1561070370189811785279120670681971715933");
    private static final BigInteger VAL5 = new BigInteger("15266301640598830744513188433961607049111101514491443661508782660030152432096659");
    private static final BigInteger VAL6 = new BigInteger("44186753191204969498879038096178385468042091377851956154253740899404203018504019");

    public static void main( String[] args ) {
        List<Callable<String>> list = new ArrayList<>();
        /*
        list.add(new ExtendedEuclidean(VAL1, VAL2));
        list.add(new ExtendedEuclidean(VAL3, VAL4));
        list.add(new ExtendedEuclidean(VAL5, VAL6));
        list.add(new ExtendedBinaryEuclidean(VAL1, VAL2));
        list.add(new ExtendedBinaryEuclidean(VAL3, VAL4));
        list.add(new ExtendedBinaryEuclidean(VAL5, VAL6));
        */
        list.add(new CroppedReminderExtendedEuclidean(VAL1, VAL2));
        list.add(new CroppedReminderExtendedEuclidean(VAL3, VAL4));
        list.add(new CroppedReminderExtendedEuclidean(VAL5, VAL6));

        /*
        list.add(new ExtendedEuclidean(new BigInteger("98"), new BigInteger("21")));
        list.add(new ExtendedEuclidean(new BigInteger("42"), new BigInteger("12")));
        list.add(new ExtendedEuclidean(new BigInteger("112"), new BigInteger("21")));

        list.add(new CroppedReminderExtendedEuclidean(new BigInteger("98"), new BigInteger("21")));
        list.add(new CroppedReminderExtendedEuclidean(new BigInteger("42"), new BigInteger("12")));
        list.add(new CroppedReminderExtendedEuclidean(new BigInteger("112"), new BigInteger("21")));
        */

        //list.add(new ExtendedBinaryEuclidean(new BigInteger("98"), new BigInteger("21")));
        //list.add(new ExtendedBinaryEuclidean(new BigInteger("42"), new BigInteger("12")));
        //list.add(new ExtendedBinaryEuclidean(new BigInteger("112"), new BigInteger("21")));

        //list.add(new CroppedReminderExtendedEuclidean(new BigInteger("698431"), new BigInteger("453899")));


        PerformanceTest pf = new PerformanceTest();
        pf.setData(list);
        pf.setFileName("Lab1Report");
        pf.run();
    }

}
