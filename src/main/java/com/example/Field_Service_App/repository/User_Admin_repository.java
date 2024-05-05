package com.example.Field_Service_App.repository;

import com.example.Field_Service_App.model.User_Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface User_Admin_repository extends JpaRepository<User_Admin,Integer> {
     User_Admin findByAdresse(String adresse);

}
