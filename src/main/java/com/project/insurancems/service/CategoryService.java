package com.project.insurancems.service;

import com.project.insurancems.entity.Category;
import com.project.insurancems.entity.Client;
import com.project.insurancems.repository.CategoryRepository;
import com.project.insurancems.repository.ClientRepository;
import com.project.insurancems.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ClientRepository clientRepository;

    public int getTotalNumberOfCategories(){
        return categoryRepository.countCategory();
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public List<Category> getLoggedInClientCategories(UserDetailsImpl user){
        int idUser = user.getId();
        Client client = clientRepository.getClientByUserId(idUser);
        int idClient = client.getId();
        return categoryRepository.findInsuranceCategoriesByClientPolicy(idClient);
    }
}
