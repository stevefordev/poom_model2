package com.coddington.poom.controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.coddington.poom.service.UsersService;
import com.coddington.poom.vo.User;

@Controller
public class UsersController {
  public static final String LOGIN = "loginUser";
  public static final String LOGIN_FAIL = "loginFail";
  private UsersService usersService;

  public void setUsersService(UsersService usersService) {
    this.usersService = usersService;
  }

  @RequestMapping(value = "/login.poom")
  public String login(@ModelAttribute User user, RedirectAttributes ra, HttpSession session,
      @RequestHeader String referer) {

    User loginUser = usersService.login(user);

    if (loginUser == null) {
      ra.addFlashAttribute(this.LOGIN_FAIL, true);
    } else {
      session.setAttribute(this.LOGIN, loginUser);
    }

    return "redirect:" + referer;
  }
  
  @RequestMapping(value ="/logout.poom")
  public String logout(@RequestHeader String referer,HttpSession session) {
	  
	  session.invalidate();
	  return "redirect:"+referer;
  }
  
  
  @RequestMapping(value = "/signup.poom")
  public String join(User user, RedirectAttributes ra, @RequestHeader String referer) {
	  
//	  System.out.println(user.getName());
//	  System.out.println(user.getEmail());
//	  System.out.println(user.getNickName());
//	  System.out.println(user.getPassword());
//	  System.out.println(user.getPhone());
//	  
 
	  boolean result = usersService.join(user);
	  
	  if(result==true) {
		  ra.addFlashAttribute("joinSucc",true);
	  }else { 
		  ra.addFlashAttribute("joinFail",false);
	  }
	  
	  return "redirect:" + referer;
  }

}
