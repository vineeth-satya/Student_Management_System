package com.example.record.controller;
import com.example.record.entity.Trainee;
import com.example.record.service.TraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller @RequestMapping("/trainees")
public class TraineeController {
    @Autowired private TraineeService traineeService;
    @GetMapping
    public String listTrainees(Model model, @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size,
                              @RequestParam(required = false) String search,
                              @RequestParam(required = false) String groupName) {
        Page<Trainee> traineePage;
        if (search != null && !search.isEmpty()) {
            traineePage = traineeService.searchByName(search, page, size);
        } else if (groupName != null && !groupName.isEmpty()) {
            traineePage = traineeService.searchByGroup(groupName, page, size);
        } else {
            traineePage = traineeService.getAllTrainees(page, size);
        }
        model.addAttribute("trainees", traineePage);
        return "trainee-list";
    }
    @GetMapping("/add")
    public String addTraineeForm(Model model) {
        model.addAttribute("trainee", new Trainee());
        return "trainee-form";
    }
    @PostMapping("/save")
    public String saveTrainee(@ModelAttribute("trainee") Trainee trainee, BindingResult result,
                            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) return "trainee-form";
        traineeService.addTrainee(trainee);
        redirectAttributes.addFlashAttribute("message", "Trainee saved successfully!");
        return "redirect:/trainees";
    }
    @GetMapping("/edit/{id}")
    public String editTraineeForm(@PathVariable Long id, Model model) {
        model.addAttribute("trainee", traineeService.getTraineeById(id));
        return "trainee-form";
    }
    @GetMapping("/delete/{id}")
    public String deleteTrainee(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        traineeService.deleteTrainee(id);
        redirectAttributes.addFlashAttribute("message", "Trainee deleted successfully!");
        return "redirect:/trainees";
    }
}
