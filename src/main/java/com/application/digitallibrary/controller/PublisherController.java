package com.application.digitallibrary.controller;

import com.application.digitallibrary.entity.Publisher;
import com.application.digitallibrary.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

//PRESENTATION LAYER FOR PUBLISHER:
@Controller
public class PublisherController {
    @Autowired
    private PublisherService publisherService;

    //GETTING LIST OF PUBLISHERS AND DISPLAYING IT ON UI:
    @GetMapping("/publishers")
    public String findAllPublishers(Model model){
        model.addAttribute("publishers", publisherService.findAllPublishers());
        return "publishers";
    }

    // DELETE A CATEGORY:
    @GetMapping("/remove-publisher/{id}")
    public String deletePublisher(@PathVariable Long id, Model model){
        publisherService.deletePublisher(id);
        model.addAttribute("publishers", publisherService.findAllPublishers());
        return "publishers";
    }

    //  EDIT/UPDATE PUBLISHER:
    @GetMapping("update-publisher/{id}")
    public String updatePublisher (@PathVariable Long id, Model model){
        model.addAttribute("publisher", publisherService.findPublisherById(id));
        return "update-publisher";
    }

    // SUBMIT/SAVE CHANGES (of the updated publisher):
    @PostMapping("/update-publisher/{id}")
    public String saveUpdatePublisher(@PathVariable Long id, Publisher publisher, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors())
            return "update-publisher";
            publisherService.updatePublisher(publisher);
        model.addAttribute("publisher", publisherService.findAllPublishers() );
        return "redirect:/publishers";
    }

    // SELF-EXPLANATORY:
    @GetMapping("/add-publisher")
    public String showCreatePage(Publisher publisher){
        return "add-publisher";
    }

    //SUBMIT/SAVE ADDED PUBLISHER:
    @PostMapping("/save-publisher")
    public String createPublisher(Publisher publisher, BindingResult result, Model model){
        if(result.hasErrors())
            return "add-publisher";
        publisherService.createPublisher(publisher);
        model.addAttribute("publishers", publisherService.findAllPublishers());
        return "redirect:/publishers";
    }

}
