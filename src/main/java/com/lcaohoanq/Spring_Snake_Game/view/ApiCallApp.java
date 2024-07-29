package com.lcaohoanq.Spring_Snake_Game.view;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.lcaohoanq.Spring_Snake_Game.model.User;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;

public class ApiCallApp extends JFrame {
    private JTextArea textArea;
    private JButton fetchButton;
    private HttpClient httpClient;
    private ObjectMapper objectMapper;

    public ApiCallApp() {
        httpClient = HttpClient.newHttpClient();
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule()).enable(SerializationFeature.INDENT_OUTPUT);

        setTitle("API Call Example");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        fetchButton = new JButton("Fetch Data");
        fetchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fetchData();
            }
        });
        add(fetchButton, BorderLayout.SOUTH);
    }

    private void fetchData() {
        // Create a new thread to avoid blocking the Swing event dispatch thread
        new Thread(() -> {
            try {
                // Replace with your API URL
                //https://jsonplaceholder.typicode.com/posts/1
                String apiUrl = "http://localhost:8080/users";
                HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .GET()
                    .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200) {
                    String responseBody = response.body();

                    //Object json = objectMapper.readValue(responseBody, Object.class);
                    List<User> usersList = objectMapper.readValue(responseBody, new TypeReference<>() {
                    }); //deserializing JSON array to a list of User objects

                    String formattedJson = objectMapper.writeValueAsString(usersList);

                    SwingUtilities.invokeLater(() -> textArea.setText(formattedJson));
                } else {
                    SwingUtilities.invokeLater(() -> textArea.setText("GET request failed: " + response.statusCode()));
                }
            } catch (IOException | InterruptedException ex) {
                SwingUtilities.invokeLater(() -> textArea.setText("Exception: " + ex.getMessage()));
            }
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ApiCallApp().setVisible(true);
        });
    }
}

