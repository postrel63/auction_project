package com.zerobase.auction_project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
public class MainController {


    @RequestMapping("/")
    public String index() {

        return "index";
    }


}
