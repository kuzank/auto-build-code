package com.kuzank.autobuildcode.commander.controller;

import com.kuzank.autobuildcode.commander.helper.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>Description: </p>
 *
 * @author kuzank  2018/8/21
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping(value = {"/authentication"}, method = {RequestMethod.POST})
    public String authentication() {
        return Constants.LOGIN_VIEW;
    }


}
