package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.Model.User;
import web.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin/**")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "admin/user-list")
    public ModelAndView findAllAdmin() {
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = userService.findAll();
        modelAndView.setViewName("admin/user-list");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping(value = "user/user-list")
    public ModelAndView findAllUser() {
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = userService.findAll();
        modelAndView.setViewName("user/user-list");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

   /* @GetMapping(value = "login")
    public String loginPage() {
        return "login";
    }*/

    @GetMapping("/user-create")
    public ModelAndView createUserForm(User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/user-create");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("/user-create")
    public ModelAndView createUser(User user) {
        ModelAndView modelAndView = new ModelAndView();
        userService.saveUser(user);
        modelAndView.setViewName("redirect:/admin/user-list");
        return modelAndView;
    }

    @GetMapping("user-delete/{id}")
    public ModelAndView deleteUser(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        userService.deleteById(id);
        modelAndView.setViewName("redirect:/admin/user-list");
        return modelAndView;
    }

    @GetMapping("user-update/{id}")
    public ModelAndView updateUserForm(@PathVariable("id") Long id, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.findById(id);
        model.addAttribute("user", user);
        modelAndView.setViewName("admin/user-update");
        modelAndView.addObject("id", id);
        return modelAndView;
    }

    @PostMapping("/user-update")
    public ModelAndView updateUser(User user) {
        ModelAndView modelAndView = new ModelAndView();
        userService.updateUser(user);
        modelAndView.setViewName("redirect:/admin/user-list");
        modelAndView.addObject("user", user);
        return modelAndView;
    }
}