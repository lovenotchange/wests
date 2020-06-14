package com.example.west.callable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;
import java.util.concurrent.*;

/**
 * @author: chenxu
 * @date: 2020/6/14 8:46
 */
@RestController
@RequestMapping("/async")
public class AsyncController {

    private static Callable<String> callable;

    private static FutureTask<String> futureTask;

    @RequestMapping("/a")
    public String getData() throws Exception {
        callable = (() -> {
            Thread.sleep(5000);
            System.out.println("------------emmmmmmmmmmmmmmmmmmmmmm");
            return "love";
        });


        futureTask = new FutureTask<>(() -> {
            Thread.sleep(6000);
            System.out.println("正在ing");
            return "dddddd";
        });

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(futureTask);

        //futureTask.get();


        System.out.println("-----------------之宣称");

        // return callable.call();
        return "vvv";
    }

    @RequestMapping("/b")
    public String getAsync() throws Exception {
        if (callable != null) {
            System.out.println(callable.call());
        }
        callable = null;
        return "GET RESULT";
    }

    @RequestMapping("/c")
    public String getFuture() throws Exception {
        if (futureTask != null) {
            System.out.println(futureTask.get());
        }
        futureTask = null;
        return "GET RESULT";
    }
}
