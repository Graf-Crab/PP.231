package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;


@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getAllUsers(Model model) {
        model.addAttribute("allusers", userService.getAllUsers());
        return "hello";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model,@PathVariable("id") int id) {
        model.addAttribute("myuser", userService.getUser(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") User user, @PathVariable("id") int id) {
        userService.updateUser(id,user);
        return "redirect:/";
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}
