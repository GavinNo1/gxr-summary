package other;

import java.util.Collections;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String[] args) {
//        CompletableFuture<Void> future = CompletableFuture
//                .supplyAsync(() -> cale(50))
//                .thenApply(i -> Integer.toString(i))
////                .thenApply(str -> "\"" + str + "\"")
//                .thenAccept(System.out::println);
//        try {
//            future.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

//        CompletableFuture<Void> future = CompletableFuture
//                .supplyAsync(() -> cale(50))
//                .exceptionally(ex -> {
//                    System.out.println("ex.toString() = " + ex.toString());
//                    return 0;
//                })
//                .thenApply(i -> Integer.toString(i))
//                .thenApply(str -> "\"" + str + "\"")
//                .thenAccept(System.out::println);

        CompletableFuture<Void> future = CompletableFuture
                .supplyAsync(() -> cale(50))
                .thenCompose(i -> CompletableFuture
                        .supplyAsync(() -> cale(i)))
                .thenApply(i -> Integer.toString(i))
                .thenApply(str -> "\"" + str + "\"")
                .thenAccept(System.out::println);

        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    public static Integer cale(Integer para) {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return para * para;
    }

}
