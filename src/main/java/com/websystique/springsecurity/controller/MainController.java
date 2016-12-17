
package com.websystique.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView adminPage(ModelMap model) {            
            return new ModelAndView("admin");
    }
    
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String testPage(){
        return "test page";
    }
    
}
