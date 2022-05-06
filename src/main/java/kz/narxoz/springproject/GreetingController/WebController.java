package kz.narxoz.springproject.GreetingController;


import kz.narxoz.springproject.repository.UserRepository;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class WebController {

    @Autowired
    UserRepository repository;

    @GetMapping("/")
    public String showUserList(@RequestParam(name="name",required=false, defaultValue="")String name,

                               Model model
    ) {
        List<User> users = repository.findAll();
        if(!name.isEmpty()){
            users = repository.findTop2ByNameStartsWith(name);}
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/1")
    public String emailContain(@RequestParam(name = "email", required = false, defaultValue = "") String email,
                               Model model){
        List<User> users = repository.findAll();
        if(!email.isEmpty()){
            users = repository.findByEmailEndsWith(email);}
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/3")
    public String surname(@RequestParam(name = "surname", required = false, defaultValue = "") String surname,
                          Model model){
        List<User> users = repository.findAll();
        if(!surname.isEmpty()){
            users = repository.findBySurnameContaining(surname);}
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/4")
    public String orderBy(@RequestParam(name = "id", required = false, defaultValue = "") Long id,
                          Model model){
        List<User> users = repository.orderByIdAsc();
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/5")
    public String lastUsers(@RequestParam(name = "id", required = false, defaultValue = "") Long id,
                            Model model){
        List<User> users = repository.showlastUsers();
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/6")
    public String sortedByname(@RequestParam(name = "name", required = false, defaultValue = "") String name,
                               Model model){
        List<User> users = repository.sortByName();
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/7")
    public String email(@RequestParam(name = "email", required = false, defaultValue = "") String email,
                        Model model){
        List<User> users = repository.findAll();
        if(!email.isEmpty()){
            users = repository.findByEmailNotContaining(email);}
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/8")
    public String NameEqualSurname(@RequestParam(name = "name", required = false, defaultValue = "") String name,
                                   Model model){
        List<User> users = repository.findByNameEqualsSurname();

        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/9")
    public String email1(@RequestParam(name = "email", required = false, defaultValue = "") String email,
                         Model model){
        List<User> users = repository.emailLike();

        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/10")
    public String distinct(@RequestParam(name = "name", required = false, defaultValue = "") String name,
                           Model model){
        List<User> users = repository.findDistinctByName();
        model.addAttribute("users", users);
        return "index";
    }



    @PostMapping("/adduser")
    public String createUser(@ModelAttribute User user){
        addUser(user);
        return "redirect:/";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, User user) {
        updateUser(user);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "add-user";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = repository.getById(id);
        model.addAttribute("user", user);
        return "update-user";
    }

    private void deleteById(long id) {
        repository.deleteById(id);
    }

    private void addUser(User newUser) {
        repository.save(newUser);
    }

    private void updateUser(User updateUser) {
        User oldUser = repository.getById(updateUser.getId());

        oldUser.setName(updateUser.getName());
        oldUser.setSurname(updateUser.getSurname());
        oldUser.setEmail(updateUser.getEmail());

        repository.save(oldUser);
    }
}