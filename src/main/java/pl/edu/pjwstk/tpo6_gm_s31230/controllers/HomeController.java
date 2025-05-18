package pl.edu.pjwstk.tpo6_gm_s31230.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController
{
    @GetMapping("/")
    public String home()
    {return "index";}
}
