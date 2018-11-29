import com.jpcami.tads.xsearchpro.api.entities.Mutant;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    private static final String domain = "localhost:8080";

    public static void main(String[] args) throws IOException {
//        System.out.println(newMutant("Metamorfose"));
    }
//
//    public List<String> newMutant(Mutant mutant) throws IOException {
//        URL url;
//        try {
//            url = new URL(String.format("http://%s/mutants/", domain));
//        }
//        catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        }
//
//        HttpURLConnection conn = null;
//        try {
//            conn = (HttpURLConnection) url.openConnection();
//        }
//        catch (IOException e) {
//            throw new IOException("Ops! Não foi possível conectar com o servidor :(", e);
//        }
//
//        try {
//            conn.setRequestMethod("POST");
//        }
//        catch (ProtocolException e) {
//            throw new IOException("Ops! Houve um erro ao montar a requisição :(", e);
//        }
//        conn.setRequestProperty("Content-Type", "application/json");
//        conn.setDoInput(true);
//        conn.setDoOutput(true);
//
//        try {
//            conn.getOutputStream().write(String.format("{\"name\": \"%s\"}", name).getBytes());
//        }
//        catch (IOException e) {
//            throw new IOException("Ops! Houve um erro ao montar o corpo da requisição :(", e);
//        }
//
//        try (BufferedReader in = new BufferedReader(new InputStreamReader(new BufferedInputStream(conn.getInputStream())))) {
//            StringBuilder sb = new StringBuilder();
//
//            String line;
//            while ((line = in.readLine()) != null) {
//                sb.append(line).append("\n");
//            }
//
//            String response = sb.toString() != null && !sb.toString().isEmpty() ? sb.toString() : null;
//            return Arrays.asList(response);
//        }
//        catch (IOException e) {
//            if (conn.getResponseCode() == 412) {
//                try (BufferedReader in = new BufferedReader(new InputStreamReader(new BufferedInputStream(conn.getErrorStream())))) {
//                    StringBuilder sb = new StringBuilder();
//
//                    String line;
//                    while ((line = in.readLine()) != null) {
//                        sb.append(line).append("\n");
//                    }
//
//                    String response = sb.toString() != null && !sb.toString().isEmpty() ? sb.toString() : null;
//                    return Arrays.asList(response);
//                }
//            }
//            throw new IOException("Ops! Houve um erro ler o retorno da requisição :(", e);
//        }
//    }

    public static String newSkill(String name) throws IOException {
        URL url;
        try {
            url = new URL(String.format("http://%s/skills/", domain));
        }
        catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
        }
        catch (IOException e) {
            throw new IOException("Ops! Não foi possível conectar com o servidor :(", e);
        }

        try {
            conn.setRequestMethod("POST");
        }
        catch (ProtocolException e) {
            throw new IOException("Ops! Houve um erro ao montar a requisição :(", e);
        }
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoInput(true);
        conn.setDoOutput(true);

        try {
            conn.getOutputStream().write(String.format("{\"name\": \"%s\"}", name).getBytes());
        }
        catch (IOException e) {
            throw new IOException("Ops! Houve um erro ao montar o corpo da requisição :(", e);
        }

        try (BufferedReader in = new BufferedReader(new InputStreamReader(new BufferedInputStream(conn.getInputStream())))) {
            StringBuilder sb = new StringBuilder();

            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line).append("\n");
            }

            String response = sb.toString() != null && !sb.toString().isEmpty() ? sb.toString() : null;
            return response;
        }
        catch (IOException e) {
            if (conn.getResponseCode() == 412) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(new BufferedInputStream(conn.getErrorStream())))) {
                    StringBuilder sb = new StringBuilder();

                    String line;
                    while ((line = in.readLine()) != null) {
                        sb.append(line).append("\n");
                    }

                    String response = sb.toString() != null && !sb.toString().isEmpty() ? sb.toString() : null;
                    return response;
                }
            }
            throw new IOException("Ops! Houve um erro ler o retorno da requisição :(", e);
        }
    }



    public static String getSkills() throws IOException {
        URL url;
        try {
            url = new URL(String.format("http://%s/skills", domain));
        }
        catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
        }
        catch (IOException e) {
            throw new IOException("Ops! Não foi possível conectar com o servidor :(", e);
        }

        try {
            conn.setRequestMethod("GET");
        }
        catch (ProtocolException e) {
            throw new IOException("Ops! Houve um erro ao montar a requisição :(", e);
        }
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoInput(true);
        conn.setDoOutput(false);

        try (BufferedReader in = new BufferedReader(new InputStreamReader(new BufferedInputStream(conn.getInputStream())))) {
            StringBuilder sb = new StringBuilder();

            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line).append("\n");
            }

            String response = sb.toString() != null && !sb.toString().isEmpty() ? sb.toString() : null;
            return response;
        }
        catch (IOException e) {
            throw new IOException("Ops! Houve um erro ler o retorno da requisição :(", e);
        }
    }

    public static String getMutants(String filter) throws IOException {
        URL url;
        try {
            url = new URL(String.format("http://%s/mutants", domain));
        }
        catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
        }
        catch (IOException e) {
            throw new IOException("Ops! Não foi possível conectar com o servidor :(", e);
        }

        try {
            conn.setRequestMethod("GET");
        }
        catch (ProtocolException e) {
            throw new IOException("Ops! Houve um erro ao montar a requisição :(", e);
        }
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoInput(true);
        conn.setDoOutput(true);

        try (BufferedReader in = new BufferedReader(new InputStreamReader(new BufferedInputStream(conn.getInputStream())))) {
            StringBuilder sb = new StringBuilder();

            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line).append("\n");
            }

            String response = sb.toString() != null && !sb.toString().isEmpty() ? sb.toString() : null;
            System.out.println(response);
            return response;
        }
        catch (IOException e) {
            throw new IOException("Ops! Houve um erro ler o retorno da requisição :(", e);
        }
    }

    private static String autenticate(String user, String password) throws IOException {
        URL url = new URL("http://localhost:8080/users/");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoInput(true);
        conn.setDoOutput(true);

        String body = String.format("{\"password\":\"%s\",\"login\":\"%s\"}", password, user);

        System.out.println(body);

        conn.getOutputStream().write(body.getBytes());

        try (BufferedReader in = new BufferedReader(new InputStreamReader(new BufferedInputStream(conn.getInputStream())))) {
            StringBuilder sb = new StringBuilder();

            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line).append("\n");
            }

            return sb.toString() != null && !sb.toString().isEmpty() ? sb.toString() : null;
        }
    }
}
