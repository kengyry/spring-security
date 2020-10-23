package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.Model.Role;
import web.Model.User;
import web.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller

public class UserController {

    @Autowired
    private UserService userService;

    // после логина попадаем сюда
    @GetMapping(value = "admin/user-list")
    public ModelAndView findAllAdmin() {
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = userService.findAll();
        modelAndView.setViewName("admin/user-list");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping(value = "user/user-list_")
    public ModelAndView findAllUser(Principal currentUser) {
        User user = userService.findByLastName(currentUser.getName());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/user-list_");
        modelAndView.addObject("users", user);
        return modelAndView;
    }

    @GetMapping("admin/user-create")
    public ModelAndView createUserForm(User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/user-create");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("admin/user-create")
    public ModelAndView createUser(User user) {
        ModelAndView modelAndView = new ModelAndView();
        userService.saveUser(user);
        modelAndView.setViewName("redirect:/admin/user-list");
        return modelAndView;
    }

    @GetMapping("admin/user-delete/{id}")
    public ModelAndView deleteUser(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        userService.deleteById(id);
        modelAndView.setViewName("redirect:/admin/user-list");
        return modelAndView;
    }

    @GetMapping("admin/user-update/{id}")
    public ModelAndView updateUserForm(@PathVariable("id") Long id, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("зашли в гет");
        User user = userService.findById(id);
        model.addAttribute("user", user);
        modelAndView.setViewName("admin/user-update");
        //modelAndView.addObject("id", id);
        return modelAndView;
    }

    @PostMapping("admin/user-update")
    public ModelAndView updateUser(User user) {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("зашли в пост");
        userService.updateUser(user);
        modelAndView.setViewName("redirect:/admin/user-list");
        modelAndView.addObject("user", user);
        return modelAndView;
    }
}