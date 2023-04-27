package com.github.budwing.java11;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class HttpClientExamples {
    @Test
    public void syncHttpClient() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://docs.orable.com"))
                .build();
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(20))
                //.proxy(ProxySelector.of(new InetSocketAddress("proxy.example.com", 80)))
                //.authenticator(Authenticator.getDefault())
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());
    }

    @Test
    public void asyncHttpClient() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://docs.orable.com"))
                .timeout(Duration.ofMinutes(2))
                .build();
        HttpClient client = HttpClient.newHttpClient();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();
    }

    /**
     * TODO: try java.net.http to search from baidu
     * TODO: enhancement -> extract search result link
     */
    public void trySearchByNewHTTP() {
        var keyword = "Java new feature";
        Assert.fail("try java.net.http to search from baidu");
    }
}
