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

import com.tsp.domain.JiveActivity;
import com.tsp.domain.JivePeople;
import com.tsp.service.JiveRestService;

@Controller
public class LoginController {
	private JiveRestService jiveRestService;

	private AuthorizationCodeResourceDetails resource = new AuthorizationCodeResourceDetails();

	{
		resource.setAccessTokenUri("https://otpp-2.jiveon.com/oauth2/token");
		resource.setClientId("qin3ughkcg540aj47hjp590qg5g2g2g4.i");
		resource.setId("jiveRestResource");
		resource.setScope(Arrays.asList("trust"));
		resource.setUserAuthorizationUri("https://otpp-2.jiveon.com/oauth2/authorize");
	}
	private String projectUrl = "https://otpp-2.jiveon.com/oauth2/authorize/?client_id=qin3ughkcg540aj47hjp590qg5g2g2g4.i&response_type=code";
	// private String
	// tokenUrl="https://qin3ughkcg540aj47hjp590qg5g2g2g4.i:po8uy2jik8uusoxde4icidgn0wqjjnrx.s@otpp-2.jiveon.com/oauth2/token";
	private String tokenUrl = "http://otpp-2.jiveon.com/oauth2/token?client_id=qin3ughkcg540aj47hjp590qg5g2g2g4.i&client_secret=po8uy2jik8uusoxde4icidgn0wqjjnrx.s&grant_type=authorization_code&redirect_uri=http://localhost:8080/authorization_code";

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

	@RequestMapping(value = "/authorization_code", method = GET, headers = "Accept=application/json")
	public @ResponseBody JivePeople authorizationCode() {
		JivePeople people = jiveRestService.getPeople(52105);
		return people;
	}

	@RequestMapping(value = "/people", method = GET, headers = "Accept=application/json")
	public @ResponseBody JivePeople getJivePeopleById(
			@RequestParam("id") long id) {
		JivePeople people = jiveRestService.getPeople(id);
		return people;

	}

	@RequestMapping(value = "/authorization_code_working", method = GET, headers = "Accept=application/json")
	public View jiveAuth(@RequestParam("code") String code) {

		RedirectView redirect = new RedirectView(tokenUrl + "&code=" + code);
		redirect.setExposeModelAttributes(false);
		return redirect;

	}

	public void setJiveService(JiveRestService jiveRestService) {
		this.jiveRestService = jiveRestService;
	}

}
