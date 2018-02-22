package com.example.demo.Controller;

import com.example.demo.Model.*;
import com.example.demo.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HomeController {
    //@Autowired used to make all Repos avaiable in Controller
    @Autowired
    PersonRepo personRepo;


    @Autowired
    EducationRepo educationRepo;

    @Autowired
    ExperienceRepo experienceRepo;

    @Autowired
    SkillRepo skillRepo;

    @Autowired
    JobRepo jobRepo;


    //Below will be map of pathing for web app
    @GetMapping("/")
    public String showIndex() {
        return "index";
    }

    @GetMapping("/addPerson")
    public String personForm(Model model) {

        Person person = new Person();

        model.addAttribute("person", person);

        return "personForm";
    }

    @PostMapping("/addPerson")
    public String addPerson(@Valid @ModelAttribute("person") Person person, Model model, BindingResult result) {
        if (result.hasErrors()) {
            return "personForm";
        }
        personRepo.save(person);

        return "index";
    }

    @GetMapping("/addEducation")
    public String educationForm(Model model) {

        Education education = new Education();

        model.addAttribute("education", education);

        return "educationForm";
    }

    @PostMapping("/addEducation")
    public String addEducation(@Valid @ModelAttribute("education") Education education, Model model, BindingResult result) {
        if (result.hasErrors()) {
            return "educationForm";
        }
        educationRepo.save(education);

        return "index";
    }

    @GetMapping("/addExperience")
    public String experienceForm(Model model) {

        Experience experience = new Experience();

        model.addAttribute("experience", experience);

        return "experienceForm";
    }

    @PostMapping("/addExperience")
    public String addExperience(@Valid @ModelAttribute("experience") Experience experience, Model model, BindingResult result) {
        if (result.hasErrors()) {
            return "experienceForm";
        }
        experienceRepo.save(experience);

        return "completedResume";
    }

    @GetMapping("/addSkill")
    public String skillForm(Model model){

        Skill skill = new Skill();

        model.addAttribute("skill", skill);

        return "skillsForm";
    }

    @PostMapping("/addSkill")
    public String addSkill(@Valid @ModelAttribute("skill") Skill skill,Model model, BindingResult result) {
        if (result.hasErrors()) {
            return "skillsForm";
        }
        skillRepo.save(skill);

        return "completedResume";
    }


//    When compolete with resume create getmsp class for all models from repo and print our theirs list
//    kinda like this

    @GetMapping("/completedResume")
    public String completeresume(Model model) {
        model.addAttribute("person", personRepo.findAll());
        model.addAttribute("education", educationRepo.findAll());
        model.addAttribute("experience", experienceRepo.findAll());
        model.addAttribute("skill", skillRepo.findAll());

        return "completedResume";
    }

    @GetMapping("/addJob")
    public String recruiterForm(Model model){
        Job job = new Job();

        model.addAttribute("job", job);

        return "jobForm";

    }

    @PostMapping("/addJob")
    public String addJob(@Valid @ModelAttribute("job") Job job, Model model, BindingResult result){

        if (result.hasErrors()) {
            return "jobForm";
        }

        jobRepo.save(job);

        return "availableJobs";
    }

    /*Mapping for all rest of NavBar*/

    @GetMapping("/summary")
    public String showSummary() {
        return "summary";
    }

    @GetMapping("/logo")
    public String showLogo() {
        return "logo";
    }

    @GetMapping("/contactInfo")
    public String showContactInfo() {
        return "contactInfo";
    }

    @GetMapping("/education")
    public String showEducation() {
        return "educationForm";
    }

    @GetMapping("/skills")
    public String showSkills() {
        return "skillsForm";
    }

    @GetMapping("/experience")
    public String showExperience() {
        return "experienceForm";
    }

    @GetMapping("/view")
    public String showView() {
        return "view";
    }

    @GetMapping("/coverLetter")
    public String showCoverLetter() {
        return "coverLetter";
    }

    /*Update Pathing*/
    @RequestMapping("/updatePerson/{id}")
    public String updatePerson(@PathVariable("id") long id, Model model) {
        model.addAttribute("person", personRepo.findOne(id));
        return "personForm";
    }

    @RequestMapping("/updateEducation/{id}")
    public String updateEducation(@PathVariable("id") long id, Model model) {
        model.addAttribute("education", educationRepo.findOne(id));
        return "educationForm";
    }

    @RequestMapping("/updateExperience/{id}")
    public String updateExperience(@PathVariable("id") long id, Model model) {
        model.addAttribute("experience", experienceRepo.findOne(id));
        return "experienceForm";
    }

    @RequestMapping("/updateSkill/{id}")
    public String updateSkill(@PathVariable("id") long id, Model model) {
        model.addAttribute("skill", skillRepo.findOne(id));
        return "skillsForm";
    }

    @RequestMapping("/updateJob/{id}")
    public String updateJob(@PathVariable("id") long id, Model model) {
        model.addAttribute("job", jobRepo.findOne(id));
        return "jobForm";
    }


}


