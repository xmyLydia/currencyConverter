package com.example.demo.common;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/**
 * @author mingyux
 */
@Component
public class HTTPRequest {
    public String httpURLFetching(String fetchingUrl) throws IOException {
        URL url = new URL(fetchingUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        setParameters(con);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));

        StringBuffer content = new StringBuffer();
        String result = processUserJson(content, in);
        in.close();
        con.disconnect();
        return result;
    }

    public String httpRepositoryFetching(String fetchingUrl, int page, int size) throws IOException {
        fetchingUrl += "?page=" + page + "&per_page=" + size;
        URL url = new URL(fetchingUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        setParameters(con);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));

        StringBuffer content = new StringBuffer();
        String result = processReposJson(content, in);
        in.close();
        con.disconnect();
        return result;
    }

    private String processReposJson(StringBuffer content, BufferedReader in) throws IOException {
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        return content.toString();
    }

    private String processUserJson(StringBuffer content, BufferedReader in) throws IOException {
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            if (inputLine.contains("\"")) {
                String removeQuote = inputLine.replaceFirst("\"", "");
                if (removeQuote.contains("\"")) {
                    removeQuote = removeQuote.replaceFirst("\"", "");
                    content.append(removeQuote);
                } else {
                    content.append(inputLine);
                }
            } else {
                content.append(inputLine);
            }
        }
        return content.toString();
    }

    private void setParameters(HttpURLConnection con) throws ProtocolException {
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);
    }
}
