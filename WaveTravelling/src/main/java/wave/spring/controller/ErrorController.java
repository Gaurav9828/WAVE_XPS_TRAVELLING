package wave.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {
 
    @RequestMapping(value = "/errors", method = RequestMethod.GET)
    public ModelAndView renderErrorPage(HttpServletRequest httpRequest) {
         
        ModelAndView errorPage = new ModelAndView("errorPage");
        return errorPage;
    }

}
