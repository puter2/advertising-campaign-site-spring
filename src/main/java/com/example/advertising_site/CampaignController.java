package com.example.advertising_site;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campaigns")
public class CampaignController {

    @Autowired
    private CampaignRepository campaignRepository;

    @PostMapping
    public Campaign createCampaign(@RequestBody Campaign campaign){
        return campaignRepository.save(campaign);
    }

    @GetMapping
    public List<Campaign> getCampaigns(){
        return campaignRepository.findAll();
    }
}
