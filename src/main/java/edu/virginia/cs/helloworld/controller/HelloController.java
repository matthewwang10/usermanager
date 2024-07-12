package edu.virginia.cs.helloworld.controller;

import edu.virginia.cs.helloworld.service.CheckServiceWithDatabase;
import edu.virginia.cs.helloworld.service.UserCheckService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
User Management System
1. List of Users
2. Edit User Information
3. Add New User
4. Delete User
User - User ID, First Name, Last Name, Age, Gender
Display Table of Users
Button for Edit & Delete on side
Button for Add on top
 */

@RestController
public class HelloController {
    @Autowired
    CheckServiceWithDatabase check;

    @GetMapping("/hello")
    public String hello(@RequestParam("name") String name){
        return "Hello " + name + "!";
    }

    @GetMapping("/hellowithvar")
    public String hello(HttpServletRequest request){
        String name = request.getParameter("name");
        return "Hello " + name + "!";
    }

    @GetMapping("/hello2")
    public String goodMorning() {
        return "Good Morning!";
    }

    @GetMapping("/hello/{name}")
    public String helloName(@PathVariable String name) {
        return "Hello " + name + "!";
    }

    @PostMapping("/helloPost")
    public String helloPost(HttpServletRequest request) {
        String name = request.getParameter("name");
        return "Hello " + name + "!";
    }

    @PostMapping("/login")
    public String getLogin(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (check.checkUserName(username, password)) {
            return "Access Granted";
        }
        else {
            return "Access Denied";
        }
    }


}
