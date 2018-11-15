package ru.geographer29.polytech.labs.performance;

import ru.geographer29.polytech.labs.storage.Writer;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

public class PerformanceTest {
    private String fileName;
    private final Queue<Future<String>> futures = new LinkedList<>();
    private final ExecutorService exec = Executors.newFixedThreadPool(1);
    private final Writer writer = new Writer();
    private final static long workTime;

    static {
        workTime = System.nanoTime();
    }

    public void run(){
        //long curTime, opTime, allTime;
        String s;

        writer.createFile(fileName);

        while(!futures.isEmpty()){
            Future<String> future = futures.peek();
            long startTime = System.nanoTime();

            if (future == null)
                break;

            try {

                if(future.isDone()){
                    s = future.get();
                    //curTime = System.nanoTime();
                    //opTime = curTime - starTime;
                    //allTime = curTime - workTime;
                    //writer.write(s + "Execution time = " + opTime + " ns\nOverall working time = " + allTime + "\n\n\n");
                    //writer.write(s + "Execution time = " + (System.nanoTime()-startTime)  + " ns\n\n\n");
                    writer.write(s + "\n\n\n");
                    futures.poll();
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        shutdown();
    }

    private void shutdown(){
        writer.close();
        if (!exec.isShutdown())
            exec.shutdown();
    }

    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    public void setData(List<Callable<String>> data){
        for (Callable<String> callable: data){
            futures.add(exec.submit(callable));
        }
    }

}
