package com.tsp;

import com.tsp.domain.JiveDirectMessage;
import com.tsp.service.JiveDirectMessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class JiveDirectMessageController {
  private final JiveDirectMessageService jiveDirectMessageService;


  public JiveDirectMessageController(JiveDirectMessageService jiveDirectMessageService) {
    this.jiveDirectMessageService = jiveDirectMessageService;
  }
  


  @RequestMapping(value = "/message", method = GET, headers = "Accept=application/json")
  public @ResponseBody
  JiveDirectMessage getJiveEventById(@RequestParam("id") long id) {
    JiveDirectMessage jiveDirectMessage = jiveDirectMessageService.getDirectMessage(id);
    return jiveDirectMessage;

  }

}
