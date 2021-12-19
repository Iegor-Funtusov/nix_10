package ua.com.alevel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Component;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Employee;
import ua.com.alevel.service.EmployeeService;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Component
public class DBTestFullDataTable {

    private static final String NAMES_PATH = "names.json";

    private final EmployeeService employeeService;
    private final DataTableRequest dataTableRequest;

    public DBTestFullDataTable(EmployeeService employeeService) {
        this.employeeService = employeeService;
        this.dataTableRequest = generateDataTableRequest();
    }

    public void init() {
        Gson gson = new Gson();
        try(FileReader fileReader = new FileReader(NAMES_PATH)) {
            List<String> names = gson.fromJson(
                    fileReader,
                    new TypeToken<List<String>>(){}.getType());
            System.out.println("names = " + names.size());
            Random random = new Random();
            for (int i = 0; i < 3551; i++) {
                if (i % 500 == 0) {
                    try {
                        Thread.sleep(3000);
                        System.out.println(new Date().getTime());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Employee employee = new Employee();
                int age = random.nextInt(18, 65);
                String firstName = names.get(random.nextInt(0, names.size() - 1));
                String lastName = names.get(random.nextInt(0, names.size() - 1));
                employee.setAge(age);
                employee.setFirstName(firstName);
                employee.setLastName(lastName);
                employeeService.create(employee);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchByParams() {
//        1638976669338
//        1638976673384
//        1638976677036
//        1638976680954
//        1638976685285
//        1638976689769
//        1638976694378
//        1638976698383

        Employee employee1 = employeeService.findById(1000L).get();
        Employee employee2 = employeeService.findById(3491L).get();

        Map<String, String[]> requestParamMap = dataTableRequest.getRequestParamMap();
//        requestParamMap.put("firstName", new String[]{ "hE%", "%y" });
        requestParamMap.put("firstName", new String[]{ "Belamy", "Cressy" });
//        requestParamMap.put("lastName", new String[]{ "%r%" });
//        requestParamMap.put("age", new String[]{ ">=30", "<=40" });
//        requestParamMap.put("created", new String[]{
//                String.valueOf(employee1.getCreated().getTime()),
//                String.valueOf(employee2.getCreated().getTime())
//        });

        DataTableResponse<Employee> tableResponse = employeeService.findAll(dataTableRequest);
        System.out.println("tableResponse = " + tableResponse.getItemsSize());
    }

    private DataTableRequest generateDataTableRequest() {
        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setPage(1);
        dataTableRequest.setSize(10);
        dataTableRequest.setSort("id");
        dataTableRequest.setOrder("desc");
        return dataTableRequest;
    }
}

