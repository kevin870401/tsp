package com.tsp;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tsp.domain.JiveEvent;
import com.tsp.service.JiveEventService;

@Controller
public class JiveDirectMessageController {
  private final JiveEventService jivePEventService;


  public JiveDirectMessageController(JiveEventService jivePeopleService) {
    this.jivePEventService = jivePeopleService;
  }
  


  @RequestMapping(value = "/event", method = GET, headers = "Accept=application/json")
  public @ResponseBody JiveEvent getJiveEventById(@RequestParam("id") long id) {
    JiveEvent event = jivePEventService.getEvent(id);
    return event;

  }

}
