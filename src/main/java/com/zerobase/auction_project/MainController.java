package com.zerobase.auction_project;

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
