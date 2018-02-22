package com.example.demo.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String skillName;
    private String rating;

    public Set<Job> getJobs() {
        return jobs;
    }

    public void setJobs(Set<Job> jobs) {
        this.jobs = jobs;
    }

    @ManyToMany(mappedBy = "skills", fetch = FetchType.LAZY)
    private Set<Job> jobs;

    public Skill() {this.jobs = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
