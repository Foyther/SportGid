package android.kfu.controller;

import android.kfu.entities.User;
import android.kfu.form.LoginForm;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

/**
 * Created by Nurislam on 22.05.2018.
 */
@Controller
@RequestMapping(path = "")
public class AdminController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @PreAuthorize("isAnonymous()")
    public String checkingUserData(@RequestParam(required = false) String error,
                                   @ModelAttribute("loginForm") LoginForm loginForm,
                                   BindingResult result,
                                   ModelMap map) {
        User user = new User();
        map.put("user", user);
        return "login";
    }

}
