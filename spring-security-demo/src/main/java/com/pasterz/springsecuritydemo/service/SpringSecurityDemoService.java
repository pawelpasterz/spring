package com.pasterz.springsecuritydemo.service;

import org.springframework.stereotype.Service;

@Service
public class SpringSecurityDemoService implements DemoService {

  @Override
  public String getLoginPage() {
    return "fancy-login";
  }

  @Override
  public String getHomePage() {
    return "home";
  }

  @Override
  public String getLeadersPage() {
    return "leaders";
  }

  @Override
  public String getSystemsPage() {
    return "systems";
  }

  @Override
  public String getAccessDeniedPage() {
    return "access-denied";
  }
}
