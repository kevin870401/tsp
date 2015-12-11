package com.tsp;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.tsp.domain.JivePeople;
import com.tsp.service.JiveInboxService;
import com.tsp.service.JivePeopleService;

@Controller
public class LoginController {
	private final JivePeopleService jivePeopleService;
	private final JiveInboxService jiveInboxService;

	/*
	 * @RequestMapping(value = "/login", method = GET, headers =
	 * "Accept=application/json") public View jiveAuthorize() { RedirectView
	 * redirect = new RedirectView(projectUrl);
	 * redirect.setExposeModelAttributes(false); return redirect; }
	 * 
	 * @RequestMapping(value = "/authorization_code", method = GET, headers =
	 * "Accept=application/json") public String
	 * jiveAccessCode(@RequestParam("code") String code){ return code;
	 * 
	 * }
	 */

	public LoginController(JivePeopleService jivePeopleService,
			JiveInboxService jiveInboxService) {
		this.jivePeopleService = jivePeopleService;
		this.jiveInboxService = jiveInboxService;
	}

	// this must be called as the first jive rest request, since at jive oauth
	// server end it declare the redirec_url for oauth process
	@RequestMapping(value = "/authorization_code", method = GET, headers = "Accept=application/json")
	public @ResponseBody String authorizationCode() {
		String inbox = jiveInboxService.getInbox();
		// this is necessary otherwise it won't keep the access token for some
		// reason and we need to know why?
		return "oauth access token successfully granted\n".concat(inbox);
	}

	@RequestMapping(value = "/people", method = GET, headers = "Accept=application/json")
	public @ResponseBody JivePeople getJivePeopleById(
			@RequestParam("id") long id) {
		JivePeople people = jivePeopleService.getPeople(id);
		return people;

	}

}
