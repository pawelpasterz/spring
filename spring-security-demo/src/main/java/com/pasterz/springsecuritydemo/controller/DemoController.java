package com.pasterz.springsecuritydemo.controller;

import com.pasterz.springsecuritydemo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

  private DemoService demoService;

  @Autowired
  public DemoController(DemoService demoService) {
    this.demoService = demoService;
  }

  @GetMapping("/")
  public String showHome() {
    return demoService.getHomePage();
  }

  @GetMapping("/login")
  public String showMyLoginPage() {
    return demoService.getLoginPage();
  }

  @GetMapping("/leaders")
  public String showLeaders() {
    return demoService.getLeadersPage();
  }

  @GetMapping("/systems")
  public String showSystems() {
    return demoService.getSystemsPage();
  }

  @GetMapping("/access-denied")
  public String accessDenied() {
    return demoService.getAccessDeniedPage();
  }
}
