package ua.com.alevel;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class CustomHttpHandler implements HttpHandler {

    private List<Department> generateDepartments() {
        List<Department> departments = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            departments.add(new Department(UUID.randomUUID().toString(), "name" + i));
        }
        return departments;
    }

    private void generateMainPage(StringBuilder res) {
        res.append("<!DOCTYPE html>");
        res.append("<html lang=\"en\">");
        res.append("<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <title>http</title>\n" +
//                "    <link rel=\"stylesheet\" href=\"style.css\">\n" +
//                "    <script src=\"script.js\"></script>\n" +
                "  </head>");
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        StringBuilder res = new StringBuilder();
        generateDep(res, exchange);
//        generateMainPage(res);
//        generateBody(res, exchange);
//        res.append("</html>");
        System.out.println("res = " + res);
        byte[] bytes = res.toString().getBytes();
        exchange.sendResponseHeaders(200, bytes.length);

        OutputStream os = exchange.getResponseBody();
        os.write(bytes);
        os.close();
    }

    private void generateBody(StringBuilder res, HttpExchange exchange) {
        res.append("<body>");
        res.append("<h1>URI: ");
        res.append(exchange.getRequestURI());
        res.append("</h1>");

        Set<String> strings = exchange.getRequestHeaders().keySet();
        if (strings.stream().anyMatch(s -> s.equals("Version"))) {
            Headers headers = exchange.getRequestHeaders();
            res.append("<table>");
            for (String header : headers.keySet()) {
                res.append("<tr>");
                res.append("<td>");
                res.append(header);
                res.append("</td>");
                res.append("<td>");
                res.append(headers.getFirst(header));
                res.append("</td>");
                res.append("</tr>");
            }
            res.append("</table>");
        }

        res.append("</body>");
    }

    private void generateDep(StringBuilder res, HttpExchange exchange) {
        if (exchange.getRequestURI().toString().contains("departments")) {
            exchange.setAttribute("departments", generateDepartments());
            System.out.println("departments block");
        }

        try(FileReader fileReader = new FileReader("departments.html");
            BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            Object departments = exchange.getAttribute("departments");
            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                if (line.contains("${departments}") && line.contains("th:each")) {
                    List<Department> list = (List<Department>) departments;
                    Field[] declaredFields = Department.class.getDeclaredFields();
                    String td = "";
                    List<String> tds = new ArrayList<>();
                    while (!td.contains("</tr>")) {
                        if (!td.equals("")) {
                            tds.add(td);
                        }
                        td = bufferedReader.readLine();
                    }
                    if (tds.size() != 0) {
                        for (Department department : list) {
                            res.append("<tr>");
                            for (String s : tds) {
                                res.append("<td>");
                                for (Field declaredField : declaredFields) {
                                    if (s.contains(declaredField.getName())) {
                                        declaredField.setAccessible(true);
                                        res.append(declaredField.get(department));
                                    }
                                }
                                res.append("</td>");
                            }

                            res.append("</tr>");
                        }
                    }
                } else {
                    res.append(line);
                }
            }
        } catch (IOException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
