package com.example.demo.Repositories;

import com.example.demo.Model.Job;
import com.example.demo.Model.Skill;
import org.springframework.data.repository.CrudRepository;

public interface JobRepo extends CrudRepository<Job, Long> {
    Iterable <Job> findAllBySkillsIsIn(Iterable<Skill> skills);
}
