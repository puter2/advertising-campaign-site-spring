package com.example.advertising_site;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomeController {

    @RequestMapping("/")
    public String index(){
        return "index.html";
    }

//    @GetMapping(
//            value="/add_campaign"
//    )
//    public String add(){
//        System.out.println('a');
//        return "add_campaign.html";
//    }
//
//    @PostMapping(
//            value="/add_campaign"
//    )
//    public String try_to_add(final HttpServletRequest request){
//
//        return "done";
//    }
}
