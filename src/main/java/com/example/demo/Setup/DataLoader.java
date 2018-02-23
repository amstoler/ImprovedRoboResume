package com.example.demo.Setup;

import com.example.demo.Model.*;
import com.example.demo.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    EducationRepo educationRepo;

    @Autowired
    SkillRepo skillRepo;

    @Autowired
    ExperienceRepo experienceRepo;

    @Override
    public void run(String... strings) throws Exception {

         AppRole role = new AppRole();
        role.setRoleName("EMPLOYER");
        roleRepo.save(role);

        role = new AppRole();
        role.setRoleName("APPLICANT");
        roleRepo.save(role);

        role = new AppRole();
        role.setRoleName("RECRUITER");
        roleRepo.save(role);


        //Add test data for users

        //Sample User data
        AppUser user = new AppUser();
        user.setEmail("ariel@gmail.com");
        user.setPassword("password");
        user.setFirstName("Ariel");
        user.setLastName("Stoler");
        user.setUsername("applicant");
        user.addRole(roleRepo.findAppRoleByRoleName("APPLICANT"));
        userRepo.save(user);

        user = new AppUser();
        user.setEmail("ashu@yahoo.com");
        user.setPassword("password");
        user.setFirstName("Ashu");
        user.setLastName("Jaguar2018");
        user.setUsername("employer");
        user.addRole(roleRepo.findAppRoleByRoleName("EMPLOYER"));
        userRepo.save(user);

        user = new AppUser();
        user.setEmail("yoolbin@microsoft.com");
        user.setPassword("password");
        user.setFirstName("Yoolbin");
        user.setLastName("$$$");
        user.setUsername("recruiter");
        user.addRole(roleRepo.findAppRoleByRoleName("RECRUITER"));
        userRepo.save(user);

        //Sample Skill Data
        Skill aSkill = new Skill();
        aSkill.setRating("proficient");
        aSkill.setSkillName("skill");
        skillRepo.save(aSkill);

        aSkill = new Skill();
        aSkill.setRating("very proficient");
        aSkill.setSkillName("new skill");
        skillRepo.save(aSkill);

        aSkill = new Skill();
        aSkill.setRating("ever more proficient");
        aSkill.setSkillName("newer skill");
        skillRepo.save(aSkill);

        //Sample Education Data
        Education e = new Education();
        e.setDegree("A course");
        e.setUnivName("Institution");
        e.setGradYear(2018);
        educationRepo.save(e);

        e = new Education();
        e.setDegree("Another course");
        e.setUnivName("Another Institution");
        e.setGradYear(2015);
        educationRepo.save(e);

        Experience workexp = new Experience();
        workexp.setDuties("Duty 1");
        workexp.setCompanyName("An organization");
        workexp.setDates("1000 BCE - 800 BCE");
        workexp.setJobTitle("A Position");
        experienceRepo.save(workexp);

        //Sample Experience Data
        workexp = new Experience();
        workexp.setDuties("Duty 2");
        workexp.setCompanyName("Another organization");
        workexp.setDates("1990- Present");
        workexp.setJobTitle("Anoter Position");
        experienceRepo.save(workexp);

        workexp = new Experience();
        workexp.setDuties("Duty 3");
        workexp.setCompanyName("Yet Another organization");
        workexp.setDates("2016-2018");
        workexp.setJobTitle("Yet Anoter Position");
        experienceRepo.save(workexp);

        // Sample Job Data
        Job job= new Job();
        job.setOrgination("Microsoft");
        job.setTitle("Web Developer");
        job.setJobDescription("Hard Work");
        job.setSkillsRequired("Java");

        job = new Job();
        job.setOrgination("Google");
        job.setTitle("Database Admin");
        job.setJobDescription("Manage data");
        job.setSkillsRequired("MySQL");


    }



}
