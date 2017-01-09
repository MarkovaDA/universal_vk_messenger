
package com.websystique.springsecurity.controller;

import com.websystique.springsecurity.model.User;
import com.websystique.springsecurity.service.AuthService;
import com.websystique.springsecurity.service.UserService;
import com.websystique.springsecurity.service.VKApiService;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class MainController {
    @Autowired
    VKApiService vkService;
    
    @Autowired
    UserService userService;
     
    private final String clientId = "5801227";
    private final String clientSecret = "kzErha5eVdhBsKWJMcJ1";
    private final String redirectUri = "http://localhost:8080/vk_messanger/tools";
    
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView adminPage(ModelMap model) {
        User currentUser = AuthService.getCurrentUser(userService);
        String accessToken = currentUser.getAccess_token();  
        long difference = (((new Date()).getTime() - currentUser.getLast_date().getTime()) 
                 / (1000 * 60 * 60 * 24));
        if (accessToken != null && difference < 1)//ключ доступа активный
            return new ModelAndView("redirect:/tools_options");
        return new ModelAndView("admin");
    }
    @RequestMapping(value = "/auth_vk", method = RequestMethod.GET)
    public ModelAndView registPage(){
        String redirectUrl = "redirect:https://oauth.vk.com/authorize?client_id=%s&display=page&redirect_uri=%s&scope=friends&response_type=code&v=5.60";
        redirectUrl = String.format(redirectUrl, clientId, redirectUri);
        return new ModelAndView(redirectUrl);
    }

    @GetMapping(value = "/tools")
    public ModelAndView getAccessTokenPage(@RequestParam("code")String code,
			final RedirectAttributes redirectAttributes){
        User currentUser = AuthService.getCurrentUser(userService);
        String accessToken = "";
        try {
            accessToken = vkService.getAccessToken(clientId, clientSecret, redirectUri, code);
            currentUser.setAccess_token(accessToken);
            currentUser.setLast_date(new Date());
            userService.updateAccessToken(currentUser);
        } catch (ProtocolException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        redirectAttributes.addFlashAttribute("token", accessToken);
        return new ModelAndView("redirect:/tools_options");
    }
    
    @GetMapping(value = "/tools_options")
    public ModelAndView getToolPage(@ModelAttribute("token")String token, Model model){
        //когда будут запросы различные к городам и т.п. 
        //если истек ключ доступа,редеректить на страницу входа
        model.addAttribute("accessToken", token);
        try {
            model.addAttribute("cities", vkService.getCities(token));
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ModelAndView("tools");
    }  
}
