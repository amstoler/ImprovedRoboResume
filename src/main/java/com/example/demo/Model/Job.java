package com.example.demo.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String Orginization;
    private String Title;
    private String jobDescription;
    private String skillsRequired;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    //This needs to be instantiated in the construtor so you can use it to add and remove individual skills
    private Set<Skill> skills;

    public Job() { this.skills = new HashSet<>(); }

    public String getOrginization() {
        return Orginization;
    }

    public void setOrginization(String orginization) {
        Orginization = orginization;
    }

    //Create an add method
    public void addSkill(Skill skill )
    {
        this.skills.add(skill);
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrgination() {
        return Orginization;
    }

    public void setOrgination(String orgination) {
        Orginization = orgination;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getSkillsRequired() {
        return skillsRequired;
    }

    public void setSkillsRequired(String skillsRequired) {
        this.skillsRequired = skillsRequired;
    }
}
