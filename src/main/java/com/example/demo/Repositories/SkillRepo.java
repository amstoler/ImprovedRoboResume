package com.example.demo.Repositories;

import com.example.demo.Model.Job;
import com.example.demo.Model.Skill;
import org.springframework.data.repository.CrudRepository;

public interface SkillRepo extends CrudRepository<Skill,Long> {
    Iterable<Skill> findAllBySkillNameContainingIgnoreCase(String s);
    Iterable<Skill> findAllByJobsContaining(Job thisJob);
}
