package com.example.advertising_site;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CampaignController {

    static int balance = 1_000_000;
    static String[] towns = {"Cracow", "Warsaw"};
    static String[] statuses = {"on", "off"};
    static String[] keywords = {"amazing", "sport", "cooking", "web"};

    @Autowired
    private CampaignRepository campaignRepository;

    @GetMapping("/add_campaign")
    public String showForm(Model model){
        model.addAttribute("balance",balance);
        model.addAttribute("campaign", new Campaign());
        model.addAttribute("towns",towns);
        model.addAttribute("statuses", statuses);
        model.addAttribute("selectedTown", towns[0]);
        model.addAttribute("selectedStatus", statuses[0]);
        model.addAttribute("keywords", keywords);
        return "add_campaign";
    }

    @PostMapping("/add_campaign")
    public String createCampaign(@ModelAttribute Campaign campaign, Model model){
        //System.out.println("post");
        if(balance>=campaign.getFund()) {
            balance-=campaign.getFund();
            campaignRepository.save(campaign);
            model.addAttribute("message", "Campaign added");
            return "redirect:/";
        }
        else{
            System.out.println("not enough funds");
            model.addAttribute("message","not enough funds");
            return "redirect:/";
        }
    }

    @GetMapping("/campaigns")
    @ResponseBody
    public List<Campaign> getCampaigns(){
        return campaignRepository.findAll();
    }

    @GetMapping("/campaigns/view")
    public String showCampaigns(Model model){
        model.addAttribute("campaigns", campaignRepository.findAll());
        return "campaign_list.html";
    }

    @GetMapping("/campaigns/view/{id}")
    public String showEditCampaignForm(Model model, @PathVariable Long id){
        model.addAttribute("towns",towns);
        model.addAttribute("statuses", statuses);
        model.addAttribute("selectedTown", towns[0]);
        model.addAttribute("selectedStatus", statuses[0]);
        model.addAttribute("balance",balance);
        model.addAttribute("campaign", campaignRepository.getReferenceById(id));
        model.addAttribute("keywords", keywords);
        return "edit_campaign.html";
    }
    @PostMapping("/campaigns/view/{id}")
    public String editCampaign(Model model, @PathVariable Long id, Campaign campaign){
        var old_campaign = campaignRepository.findById(id).get();
        int difference = old_campaign.getFund() - campaign.getFund();
        String message;
        if(balance>=Math.abs(difference)){
            balance += difference;
            message = "Campaign changed";
            old_campaign.setFund(campaign.getFund());
        }
        else{
            message = "Not enough funds";
        }
        model.addAttribute("message",message);
        return "redirect:/";
    }

    @GetMapping("/campaigns/delete/{id}")
    public String deleteCampaign(Model model, @PathVariable Long id){
        var campaign = campaignRepository.findById(id).get();
        balance += campaign.getFund();
        campaignRepository.deleteById(id);
        return "redirect:/campaigns/view";
    }
}
