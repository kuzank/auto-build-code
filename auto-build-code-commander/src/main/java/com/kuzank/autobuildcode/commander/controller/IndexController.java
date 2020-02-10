package com.kuzank.autobuildcode.commander.controller;

import com.kuzank.autobuildcode.commander.helper.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>Description: </p>
 *
 * @author kuzank
 */
@Controller
public class IndexController {

    @RequestMapping(value = {"/"}, method = {RequestMethod.POST, RequestMethod.GET})
    public String index() {

        return Constants.INDEX_VIEW;
    }

    @RequestMapping(value = {"/login"}, method = {RequestMethod.POST, RequestMethod.GET})
    public String login() {

        return Constants.LOGIN_VIEW;
    }
}
