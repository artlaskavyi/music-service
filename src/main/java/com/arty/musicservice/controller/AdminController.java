package com.arty.musicservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/add")
    public String addEntity() {
        return "Add new entity page";
    }

    @GetMapping("/edit")
    public String editEntity() {
        return "Edit entity page";
    }

    @GetMapping("/delete")
    public String deleteEntity() {
        return "Delete entity page";
    }
}