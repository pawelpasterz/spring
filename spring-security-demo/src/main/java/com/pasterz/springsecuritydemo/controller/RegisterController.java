package com.pasterz.springsecuritydemo.controller;

import com.pasterz.springsecuritydemo.dto.CRMUser;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {

  private UserDetailsManager userDetailsManager;
  private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  @Autowired
  public RegisterController(UserDetailsManager userDetailsManager) {
    this.userDetailsManager = userDetailsManager;
  }

  @InitBinder
  public void initBinder(WebDataBinder dataBinder) {
    StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
    dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
  }

  @GetMapping("/showRegistrationForm")
  public String showRegPage(Model model) {
    model.addAttribute("crmUser", new CRMUser());
    return "registration-form";
  }

  @PostMapping("/processRegistrationForm")
  public String processRegistrationForm(
      @Valid @ModelAttribute("crmUser") CRMUser theCrmUser,
      BindingResult theBindingResult,
      Model theModel) {

    String userName = theCrmUser.getUserName();

    if (theBindingResult.hasErrors()) {
      theModel.addAttribute("crmUser", new CRMUser());
      theModel.addAttribute("registrationError", "User name/password can not be empty.");
      return "registration-form";
    }

    boolean userExists = doesUserExist(userName);

    if (userExists) {
      theModel.addAttribute("crmUser", new CRMUser());
      theModel.addAttribute("registrationError", "User name already exists.");
      return "registration-form";
    }

    String encodedPassword = passwordEncoder.encode(theCrmUser.getPassword());
    encodedPassword = "{bcrypt}" + encodedPassword;

    List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_EMPLOYEE");

    User tempUser = new User(userName, encodedPassword, authorities);

    userDetailsManager.createUser(tempUser);

    return "registration-confirmation";
  }

  private boolean doesUserExist(String userName) {
    return userDetailsManager.userExists(userName);
  }
}
