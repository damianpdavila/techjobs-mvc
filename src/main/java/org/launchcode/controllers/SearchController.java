package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping(value = "search", method = RequestMethod.GET)
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "results", method = RequestMethod.POST)
    public String search(Model model, @RequestParam String searchType, @RequestParam String searchTerm){
        // get data using parms
        ArrayList<HashMap<String, String>> jobs;

        if ( searchType.toLowerCase().equals("all") ){
            jobs = JobData.findByValue(searchTerm);
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }
        // set up the output page
        model.addAttribute("jobs", jobs);
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }
}
