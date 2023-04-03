package com.project.insurancems.controller;

import com.project.insurancems.entity.Coverage;
import com.project.insurancems.entity.Policy;
import com.project.insurancems.security.UserDetailsImpl;
import com.project.insurancems.service.CoverageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/coverages")
public class CoveragesController {

    @Autowired
    private CoverageService coverageService;
    @GetMapping("/loggedInClient-coverageByPolicy")
    public List<Coverage> getLoggedInClientCoverageByPolicy(@RequestParam int id){
        return coverageService.getCoverageByPolicy(id);
    }
}
