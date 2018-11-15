package ru.geographer29.polytech.labs;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.geographer29.polytech.labs.gcd.ExtendedEuclidean;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExtendedEuclideanTest {
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Before
    public void prepare(){

    }

    @Test
    public void test() throws InterruptedException, ExecutionException{
        ExtendedEuclidean ee = new ExtendedEuclidean(new BigInteger("98"), new BigInteger("21"));

        Future<String> future = executorService.submit(ee);
        String actualRes = future.get();
        String expectedRes = "Roots  x : -1 y : 2";

        System.out.println(actualRes);
        System.out.println();
        Assert.assertTrue(actualRes.equals(expectedRes));
    }

    @After
    public void finish(){
        executorService.shutdown();
    }

}
