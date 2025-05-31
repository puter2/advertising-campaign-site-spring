package com.example.advertising_site;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RequestMapping("/campaigns")
public class CampaignController {

    @Autowired
    private CampaignRepository campaignRepository;

    @GetMapping("/add_campaign")
    public String showForm(Model model){
        model.addAttribute("campaign", new Campaign());
        return "add_campaign.html";
    }

    @PostMapping("/add_campaign")
    public String createCampaign(@ModelAttribute Campaign campaign){
        System.out.println("post");
        campaignRepository.save(campaign);
        return "succes";
    }

    @GetMapping("/campaigns")
    @ResponseBody
    public List<Campaign> getCampaigns(){
        return campaignRepository.findAll();
    }
}
