package com.lcaohoanq.Spring_Snake_Game.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AvatarConverter {

    public static byte[] convertAvatarUrlToByteArray(String avatarUrl) throws IOException {
        if (avatarUrl == null || avatarUrl.isEmpty()) {
            throw new IllegalArgumentException("Avatar URL cannot be null or empty");
        }

        URL url;
        try {
            url = new URL(avatarUrl);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid URL format: " + avatarUrl, e);
        }

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000); // Set a timeout for connection
        connection.setReadTimeout(5000);    // Set a timeout for reading

        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new IOException("Failed to fetch avatar, HTTP response code: " + responseCode);
        }

        try (InputStream inputStream = connection.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }

            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new IOException("Error reading the avatar image data", e);
        }
    }


    public static void main(String[] args) {
        try {
            String avatarUrl = "https://s3.amazonaws.com/uifaces/faces/twitter/ivanfilipovbg/128.jpg";
            byte[] avatarBytes = convertAvatarUrlToByteArray(avatarUrl);
            System.out.println("Avatar downloaded and converted to byte array successfully.");
            // You can now use avatarBytes as needed, for example, saving it to the User object
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to convert avatar URL to byte array.");
        }
    }
}