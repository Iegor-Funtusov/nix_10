package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.dto.department.DepartmentRequestDto;
import ua.com.alevel.dto.department.DepartmentResponseDto;
import ua.com.alevel.facade.DepartmentFacade;
import ua.com.alevel.type.DepartmentType;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentFacade departmentFacade;

    public DepartmentController(DepartmentFacade departmentFacade) {
        this.departmentFacade = departmentFacade;
    }

    @GetMapping
    public String findAll(Model model) {
        List<DepartmentResponseDto> departments = departmentFacade.findAll();
        model.addAttribute("departments", departments);
        return "pages/departments/departments_all";
    }

    @GetMapping("/new")
    public String redirectToNewDepartmentPage(Model model) {
        model.addAttribute("department", new DepartmentRequestDto());
        model.addAttribute("types", DepartmentType.values());
        return "pages/departments/departments_new";
    }

    @PostMapping("/new")
    public String createNewDepartment(@ModelAttribute("department") DepartmentRequestDto dto) {
        departmentFacade.create(dto);
        return "redirect:/departments";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        departmentFacade.delete(id);
        return "redirect:/departments";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable @Valid @Min(value = 1, message = "idiot!!!") @NotNull(message = "double idiot!!!") Long id, Model model) {
        System.out.println("DepartmentController.details");
        DepartmentResponseDto dto = departmentFacade.findById(id);
        model.addAttribute("department", dto);
        return "pages/departments/departments_details";
    }
}
