package jdk11;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class HttpClientDemo {


    public static void main(String[] args) {
        try {
            System.out.println("--------------------------synchronousGet-----------------------");
            synchronousGet();
            System.out.println("--------------------------asynchronousGet-----------------------");
            asynchronousGet();
            System.out.println("--------------------------multipleConcurrentAsynchronously-----------------------");
            multipleConcurrentAsynchronously();

        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }


    static void synchronousGet() throws Exception {
        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://httpbin.org/get"))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // print response headers
        HttpHeaders headers = response.headers();
        headers.map().forEach((k, v) -> System.out.println(k + ":" + v));

        // print status code
        System.out.println(response.statusCode());

        // print response body
        System.out.println(response.body());
    }

    static void asynchronousGet() throws Exception {
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .build();


        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://httpbin.org/get"))
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .build();

        CompletableFuture<HttpResponse<String>> response =
                httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        String result = response.thenApply(HttpResponse::body).get(5, TimeUnit.SECONDS);

        System.out.println(result);


    }


    static void multipleConcurrentAsynchronously() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        HttpClient httpClient = HttpClient.newBuilder()
                .executor(executorService)
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        List<URI> targets = Arrays.asList(
                new URI("https://httpbin.org/get?name=mkyong1"),
                new URI("https://httpbin.org/get?name=mkyong2"),
                new URI("https://httpbin.org/get?name=mkyong3"));

        List<CompletableFuture<String>> result = targets.stream()
                .map(url -> httpClient.sendAsync(
                        HttpRequest.newBuilder(url)
                                .GET()
                                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                                .build(),
                        HttpResponse.BodyHandlers.ofString())
                        .thenApply(response -> response.body()))
                .collect(Collectors.toList());

        for (CompletableFuture<String> future : result) {
            System.out.println(future.get());
        }
    }


}
