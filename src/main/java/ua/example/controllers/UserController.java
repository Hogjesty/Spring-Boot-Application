package ua.example.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.example.dtos.UserDTO;
import ua.example.services.UserService;

import javax.annotation.Resource;
import javax.validation.Valid;

import java.util.List;

// /user/all
// /user/create
// /user/update
// /user/delete
// /user/find-by/phone-number

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    UserService userService;

    @GetMapping(value = "/show")
    public String showUsersPage(@PageableDefault(size = 5, sort = {"id"}) Pageable pageable,
                                      Model model) {

        Page<UserDTO> page = userService.getPage(pageable);

        List<Integer> paginationNumbers = PaginationUtils.getNumbers(page);

        model.addAttribute("page", page);
        model.addAttribute("paginationNumbers", paginationNumbers);

        return "Users";
    }

    @GetMapping(value = "/create")
    public String showCreatePage() {
        return "CreateUser";
    }

    @PostMapping(value = "/creating")
    public String createUsers(@Valid @ModelAttribute(name = "user") UserDTO user,
                              BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "CreateUser";
        }

        userService.addUser(user);

        return "redirect:/user/show";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable(value = "id") Integer userId) {

        userService.deleteUser(userId);

        return "redirect:/user/show";
    }

    @GetMapping(value = "/edit/{id}")
    public String showEditPage(@PathVariable(value = "id") Integer id,
                               Model model) {

        UserDTO dto = userService.getById(id);

        model.addAttribute("user", dto);

        return "EditUser";
    }

    @PostMapping(value = "/editing")
    public String editUser(@Valid @ModelAttribute(name = "user") UserDTO user,
                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "EditUser";
        }

        userService.update(user);

        return "redirect:/user/show";
    }

}
