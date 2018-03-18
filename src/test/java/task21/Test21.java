package task21;

import java.net.PasswordAuthentication;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Test21 {
    public static <T> CompletableFuture<T> repeat(Supplier<T> action, Predicate<T> until) {
        return CompletableFuture.supplyAsync(action).thenApplyAsync(r -> {
            if (until.test(r)) {
                return r;
            } else {
                return repeat(action, until).join();
            }
        });
    }

    public static void main(String[] args) {
        repeat(() -> {
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("Login: ");
                    String user = scanner.nextLine();
                    System.out.print("Password: ");
                    char[] password = scanner.nextLine().toCharArray();
                    return new PasswordAuthentication(user, password);
                },
                (a) -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ignored) {
                    }
                    return new String(a.getPassword()).equals("secret");
                }
        ).thenAccept((a) -> System.out.printf("Logged in: %s %s%n", a.getUserName(), new String(a.getPassword())));
        ForkJoinPool.commonPool().awaitQuiescence(3, TimeUnit.MINUTES);
    }
}
