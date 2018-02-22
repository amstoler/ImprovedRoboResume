package com.example.demo.Repositories;

import com.example.demo.Model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepo extends CrudRepository<Person,Long> {
}
