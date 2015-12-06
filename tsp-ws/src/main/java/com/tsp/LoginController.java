package com.tsp;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginController {
  
  private String projectUrl="https://otpp-2.jiveon.com/oauth2/authorize/?client_id=qin3ughkcg540aj47hjp590qg5g2g2g4.i&response_type=code";
  private String tokenUrl="https://qin3ughkcg540aj47hjp590qg5g2g2g4.i:po8uy2jik8uusoxde4icidgn0wqjjnrx.s@otpp-2.jiveon.com/oauth2/token";
  @RequestMapping(value = "/login", method = GET, headers = "Accept=application/json")
  public View jiveAuthorize() {
    RedirectView redirect = new RedirectView(projectUrl);
    redirect.setExposeModelAttributes(false);
    return redirect;
  }
  @RequestMapping(value = "/authorization_code", method = GET, headers = "Accept=application/json")
  public String jiveAccessCode(@RequestParam("code") String code){
    tokenUrl
    return code;
    
  }
  
}
