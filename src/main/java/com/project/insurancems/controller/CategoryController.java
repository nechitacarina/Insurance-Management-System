package com.project.insurancems.controller;

import com.project.insurancems.entity.Category;
import com.project.insurancems.security.UserDetailsImpl;
import com.project.insurancems.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/count")
    @PreAuthorize("hasRole('AGENT')")
    public int getTotalNumberOfCategories() {
        return categoryService.getTotalNumberOfCategories();
    }

    @GetMapping
    @PreAuthorize("hasRole('AGENT')")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/loggedInClient")
    public List<Category> getThisClientCategories(@AuthenticationPrincipal UserDetailsImpl user){
        return categoryService.getLoggedInClientCategories(user);
    }
}
