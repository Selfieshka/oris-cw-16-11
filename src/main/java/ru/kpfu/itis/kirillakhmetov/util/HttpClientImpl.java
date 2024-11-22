package ru.kpfu.itis.kirillakhmetov.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpClientImpl implements HttpClient {
    private final int TIMEOUT = 5000;

    @Override
    public String get(String url, Map<String, String> headers, Map<String, String> params) {
        HttpURLConnection connection = null;
        String result;

        try {
            URL myUrl = new URL(concatenateUrl(url, params));
            connection = (HttpURLConnection) myUrl.openConnection();

            connection.setRequestMethod("GET");
            addHeadersToConnection(connection, headers);
            connection.setConnectTimeout(TIMEOUT);
            connection.setReadTimeout(TIMEOUT);

            result = readResponse(connection);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) connection.disconnect();
        }

        return result;
    }

    @Override
    public String post(String url, Map<String, String> headers, Map<String, String> data) {
        HttpURLConnection connection = null;
        String result;

        try {
            URL myUrl = new URL(url);
            connection = (HttpURLConnection) myUrl.openConnection();

            connection.setRequestMethod("POST");
            addHeadersToConnection(connection, headers);
            connection.setDoOutput(true);

            writeJsonToOutputStream(dataToJson(data), connection);

            result = readResponse(connection);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) connection.disconnect();
        }

        return result;
    }

    @Override
    public String put(String url, Map<String, String> headers, Map<String, String> data) {
        HttpURLConnection connection = null;
        String result;
        try {
            URL myUrl = new URL(url);
            connection = (HttpURLConnection) myUrl.openConnection();

            connection.setRequestMethod("PUT");
            connection.setDoOutput(true);

            addHeadersToConnection(connection, headers);

            writeJsonToOutputStream(dataToJson(data), connection);

            result = readResponse(connection);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) connection.disconnect();
        }

        return result;
    }

    @Override
    public String delete(String url, Map<String, String> headers, Map<String, String> data) {
        HttpURLConnection connection = null;
        String result = null;

        try {
            URL deleteURL = new URL(url);
            connection = (HttpURLConnection) deleteURL.openConnection();

            connection.setRequestMethod("DELETE");
            addHeadersToConnection(connection, headers);

            result = readResponse(connection);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) connection.disconnect();
        }

        return result;
    }

    private String readResponse(HttpURLConnection connection) throws IOException {
        if (connection != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder content = new StringBuilder();
                String input;
                while ((input = reader.readLine()) != null) {
                    content.append(input);
                }
                return content.toString();
            }
        }
        return null;
    }

    private void writeJsonToOutputStream(String json, HttpURLConnection connection) throws IOException {
        try (OutputStream outputStream = connection.getOutputStream()) {
            byte[] input = json.getBytes(StandardCharsets.UTF_8);
            outputStream.write(input, 0, input.length);
        }
    }

    private String concatenateUrl(String url, Map<String, String> params) {
        StringBuilder newUrl = new StringBuilder(url);
        if (!params.isEmpty()) {
            newUrl = new StringBuilder(url + "?" + params.keySet().stream()
                    .map(key -> key + "=" + params.get(key))
                    .collect(Collectors.joining("&")));
            return newUrl.toString();
        }
        return url;
    }

    private void addHeadersToConnection(HttpURLConnection connection, Map<String, String> headers) {
        for (String key : headers.keySet()) {
            connection.setRequestProperty(key, headers.get(key));
        }
    }

    private String dataToJson(Map<String, String> data) throws JsonProcessingException {
        return (new ObjectMapper()).writeValueAsString(data);
    }
}