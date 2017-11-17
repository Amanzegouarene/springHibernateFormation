package fr.amanzegouarene.common.basic.manipulations;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by amanzego on 12/09/2017.
 */
public class SimpleDateFormatExample {


    // issues in a mluti-threads environment
    //  private static final DateFormat format = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");

    // Solution 1: Use ThreadLocal
    private static final ThreadLocal<SimpleDateFormat> format = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
        }
    };

    // Solution 4: user DateTimeFormat dof java.time.format
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public static void main(String[] args) throws InterruptedException, ExecutionException {

        // final DateFormatWrapper format = new DateFormatWrapper();

        for (int ind = 0; ind < 50; ind++) {
            System.out.println("ind = " + ind);
            // Define a Callable building dates
            Callable<LocalDateTime> task = new Callable<LocalDateTime>() {
                @Override
                public LocalDateTime call() throws Exception {
                    // Thread.sleep(1000);
                    // return format.get().parse("2017-09-14 09:41:52");
                    // return format.parse("2017-09-14 09:41:52");
                    return LocalDateTime.parse("2017-09-14 09:41:52", dateTimeFormatter);
                    // (Date) dateTimeFormatter.parse("2017-09-14 09:41:52");
                }
            };

            // Define a 5 tasks pool
            ExecutorService executorService = Executors.newFixedThreadPool(5);

            // Building a 10 dates list
            List<Future<LocalDateTime>> list = new ArrayList<>(10);

            for (int i = 0; i < 10; i++) {
                list.add(executorService.submit(task));
            }

            executorService.shutdown();

            // Output results
            list.stream().forEach(f -> {
                try {
                    System.out.println(f.get());
                } catch (InterruptedException | ExecutionException e) {
                    System.out.println(e.getMessage());
                }
            });
        }

    }

    // Solution 2-3: wrape SimpleDateFormat and synchronized pare() method with synchronized key word or usgin locks
    private static class DateFormatWrapper {
        final DateFormat format = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");

        // User a lock
        Lock lock = new ReentrantLock();

        public Date parse(String s) throws ParseException {
            lock.lock();
            try {
                return format.parse(s);
            } finally {
                lock.unlock();
            }

        }
    }

}