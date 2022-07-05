package utilities;

import beans.PsiClassBean;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import testSmellInfo.methodSmellInfo.MethodWithEagerTest;
import testSmellInfo.methodSmellInfo.MethodWithGeneralFixture;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public abstract class HostConnection {
    public static void sendClassToHost(String jsonString) throws IOException {
        URL url = new URL("http://localhost:8080/api/v1/psi");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        try (OutputStream os = con.getOutputStream()) {
            byte[] input = jsonString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        int code = con.getResponseCode();
        //System.out.println(code);
    }

    public static void cleanHostDB() throws IOException {
        URL url = new URL("http://localhost:8080/api/v1/psi/deleteAllClasses");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        }
    }

    public static List<PsiClassBean> getAllClassesFromHost() throws IOException {
        URL url = new URL("http://localhost:8080/api/v1/psi/getAllClasses");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");

        List<PsiClassBean> psiClassBeanList = null;
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            Gson gson = new Gson();
            psiClassBeanList = gson.fromJson(String.valueOf(response), new TypeToken<List<PsiClassBean>>(){}.getType());
        }

        return psiClassBeanList;
    }

    public static List<PsiClassBean> getClassesWithEagerTest() throws IOException {
        URL url = new URL("http://localhost:8080/api/v1/psi/getClassesWithEagerTest");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");

        List<PsiClassBean> psiClassBeanList = null;
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            Gson gson = new Gson();
            psiClassBeanList = gson.fromJson(String.valueOf(response), new TypeToken<List<PsiClassBean>>(){}.getType());
        }

        return psiClassBeanList;
    }

    public static List<MethodWithEagerTest> getMethodsWithEagerTest() throws IOException {
        URL url = new URL("http://localhost:8080/api/v1/psi/getMethodsWithEagerTest");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");

        List<MethodWithEagerTest> methodsWithEagerTestList = null;
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            Gson gson = new Gson();
            methodsWithEagerTestList = gson.fromJson(String.valueOf(response), new TypeToken<List<MethodWithEagerTest>>(){}.getType());
        }

        return methodsWithEagerTestList;
    }

    public static List<PsiClassBean> getClassesWithGeneralFixture() throws IOException {
        URL url = new URL("http://localhost:8080/api/v1/psi/getClassesWithGeneralFixture");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");

        List<PsiClassBean> psiClassBeanList = null;
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            Gson gson = new Gson();
            psiClassBeanList = gson.fromJson(String.valueOf(response), new TypeToken<List<PsiClassBean>>(){}.getType());
        }

        return psiClassBeanList;
    }

    public static List<MethodWithGeneralFixture> getMethodsWithGeneralFixture() throws IOException {
        URL url = new URL("http://localhost:8080/api/v1/psi/getMethodsWithGeneralFixture");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");

        List<MethodWithGeneralFixture> methodWithGeneralFixtureList = null;
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            Gson gson = new Gson();
            methodWithGeneralFixtureList = gson.fromJson(String.valueOf(response), new TypeToken<List<MethodWithGeneralFixture>>(){}.getType());
        }

        return methodWithGeneralFixtureList;
    }

    public static List<PsiClassBean> getClassesWithLackOfCohesion() throws IOException {
        URL url = new URL("http://localhost:8080/api/v1/psi/getClassesWithLackOfCohesion");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");

        List<PsiClassBean> psiClassBeanList = null;
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            Gson gson = new Gson();
            psiClassBeanList = gson.fromJson(String.valueOf(response), new TypeToken<List<PsiClassBean>>(){}.getType());
        }

        return psiClassBeanList;
    }
}
