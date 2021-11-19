package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.dto.employee.EmployeeRequestDto;
import ua.com.alevel.facade.EmployeeFacade;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeFacade employeeFacade;

    public EmployeeController(EmployeeFacade employeeFacade) {
        this.employeeFacade = employeeFacade;
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("employees", employeeFacade.findAll());
        return "/pages/employees/employees_all";
    }

    @GetMapping("/departments/{id}")
    public String findAllByDepartment(Model model, @PathVariable Long id) {
        model.addAttribute("employees", employeeFacade.findAllByDepartment(id));
        return "/pages/employees/employees_all";
    }

    @GetMapping("/new/{id}")
    public String redirectToNewEmployeePage(Model model, @PathVariable Long id) {
        EmployeeRequestDto requestDto = new EmployeeRequestDto();
        requestDto.setDepartmentId(id);
        model.addAttribute("employee", requestDto);
        model.addAttribute("departmentId", id);
        return "/pages/employees/employees_new";
    }

    @PostMapping("/new")
    public String createNewEmployee(@ModelAttribute("employee") EmployeeRequestDto employeeRequestDto) {
        employeeFacade.create(employeeRequestDto);
        return "redirect:/employees";
    }

    @GetMapping("/details/{id}")
    public String findById(Model model, @PathVariable Long id) {
        model.addAttribute("employee", employeeFacade.findById(id));
        return "/pages/employees/employees_details";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        employeeFacade.delete(id);
        return "redirect:/employees";
    }
}
