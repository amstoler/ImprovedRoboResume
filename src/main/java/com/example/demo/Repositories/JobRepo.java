package com.example.demo.Repositories;

import com.example.demo.Model.Job;
import org.springframework.data.repository.CrudRepository;

public interface JobRepo extends CrudRepository<Job, Long> {
}
