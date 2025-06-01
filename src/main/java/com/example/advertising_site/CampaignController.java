package com.example.advertising_site;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
//@RequestMapping("/campaigns")
public class CampaignController {

    static String[] towns = {"Cracow", "Warsaw"};
    static String[] statuses = {"on", "off"};

    @Autowired
    private CampaignRepository campaignRepository;

    @GetMapping("/add_campaign")
    public String showForm(Model model){
        model.addAttribute("campaign", new Campaign());
        model.addAttribute("towns",towns);
        model.addAttribute("statuses", statuses);
        model.addAttribute("selectedTown", towns[0]);
        model.addAttribute("selectedStatus", statuses[0]);
        return "add_campaign";
    }

    @PostMapping("/add_campaign")
    public String createCampaign(@ModelAttribute Campaign campaign){
        System.out.println("post");
        campaignRepository.save(campaign);
        return "redirect:/";
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
    public String editCampaign(Model model, @PathVariable Long id){
        System.out.println(id);
        ArrayList<Long> a = new ArrayList<>();
        a.add(id);
        campaignRepository.getReferenceById(id);
//        model.addAttribute("campaign", campaignRepository.findAllById(a).getFirst());
        model.addAttribute("campaign", campaignRepository.getReferenceById(id));
        System.out.println(campaignRepository.findAllById(a));
        return "edit_campaign.html";
    }
}
