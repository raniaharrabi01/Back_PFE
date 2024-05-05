package com.example.Field_Service_App.repository;

import com.example.Field_Service_App.model.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface TemplateRepository extends JpaRepository<Template,Integer> {
    Optional<Template> findByname(String name);
}
