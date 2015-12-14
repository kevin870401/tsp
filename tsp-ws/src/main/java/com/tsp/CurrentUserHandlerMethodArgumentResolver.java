package com.tsp;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.tsp.stereotypes.CurrentUser;

@Slf4j
@Component
public class CurrentUserHandlerMethodArgumentResolver implements
        HandlerMethodArgumentResolver {

    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterAnnotation(CurrentUser.class) != null
                && methodParameter.getParameterType().equals(User.class);
    }

    public Object resolveArgument(MethodParameter methodParameter,
            ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) throws Exception {
        if (this.supportsParameter(methodParameter)) {
            Principal principal = (Principal) webRequest.getUserPrincipal();
            String userString= (String) ((Authentication) principal).getPrincipal();
            //new User(userString, userString, null);
    		
    		log.info(userString + " is logged in");
    		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
    		authorities.add(authority);

    		// In a real scenario, this implementation has to locate user in a arbitrary
    		// dataStore based on information present in the SAMLCredential and
    		// returns such a date in a form of application specific UserDetails object.
    		return new User(userString, "<abc123>", true, true, true, true, authorities);
        } else {
            return WebArgumentResolver.UNRESOLVED;
        }
    }
}