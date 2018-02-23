package com.example.demo.Controller;

import com.example.demo.Model.*;
import com.example.demo.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
//This proves this is the most recent push to github
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

    //Week 5 Start Relationship between Job and skills
    @GetMapping("/addskillstojob/{id}")
    public String addSkill(@PathVariable("id") long jobID, Model model){
        Job thisJob = jobRepo.findOne(new Long(jobID));
        Iterable skillsInJob = thisJob.getSkills();

        model.addAttribute("job", thisJob);
        model.addAttribute("skillList", skillRepo.findAllByJobsContaining(thisJob));
        return "jobaddskill";
    }

    @GetMapping("/addjobstoactor/{id}")
    public String addJob(@PathVariable("id") long skillID, Model model){
        model.addAttribute("skill", skillRepo.findOne(new Long(skillID)));
        model.addAttribute("jobList", jobRepo.findAll());
        return "jobaddskill";
    }

    @PostMapping("/addjobstoskill/{jobid}")
    public String addJobsToSkill(@RequestParam("skills") String skillID, @PathVariable("jobid") long jobID, @ModelAttribute("aSkill") Skill s, Model model) {

        Job j =jobRepo.findOne(new Long(jobID));
        j.addSkill(skillRepo.findOne(new Long(jobID)));
        jobRepo.save(j);
        model.addAttribute("skillList", skillRepo.findAll());
        model.addAttribute("jobList", jobRepo.findAll());
        return "redirect:/";
    }

    @RequestMapping("/search")
    public String SearchResult() {
        //Get skills matching a string
        Iterable<Skill> skills = skillRepo.findAllBySkillNameContainingIgnoreCase("Java");

        for (Skill s : skills) {
            System.out.println(s.getSkillName());
        }
//Show the Jobs the skills were in
        for (Job j : jobRepo.findAllBySkillsIsIn(skills)){
            System.out.println(j.getTitle());
        }
        return "redirect:/";

    }

//Week 5 End of Relationship between Job and skills
//    When complete with resume create getmap class for all models from repo and print our theirs list

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

    @GetMapping("/availableJobs")
    public String showAvailableJobs() {
        return "availableJobs";
    }

    @GetMapping("/matchedJobs")
    public String showMatchedJobs() {
        return "matchedJobs";
    }

    @GetMapping("/references")
    public String showReferences() {
        return "references";
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


