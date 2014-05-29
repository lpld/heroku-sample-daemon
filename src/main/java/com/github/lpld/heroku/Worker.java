package com.github.lpld.heroku;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * @author leopold
 * @since 5/30/14
 */
public class Worker implements Runnable {
    @Override
    public void run() {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet("http://google.com");

        InputStream stream;
        try {
            HttpResponse response = client.execute(get);
            stream = response.getEntity().getContent();

        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }

        Scanner scanner = new Scanner(stream);

        // printing first line
        if (scanner.hasNextLine()) {
            System.out.println(" --- Content: " + scanner.nextLine());
        } else {
            System.out.println(" --- Empty content");
        }

        scanner.close();

    }
}
