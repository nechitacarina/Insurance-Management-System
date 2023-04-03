package com.project.insurancems.service;

import com.project.insurancems.entity.Coverage;
import com.project.insurancems.repository.CoverageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoverageService {
    @Autowired
    private CoverageRepository coverageRepository;

    public List<Coverage> getCoverageByPolicy(int id){
        return coverageRepository.getCoverageByPolicy(id);
    }
}
