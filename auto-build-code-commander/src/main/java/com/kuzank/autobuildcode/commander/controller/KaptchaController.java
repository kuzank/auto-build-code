package com.kuzank.autobuildcode.commander.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * <p>Description: </p>
 *
 * @author kuzank  2018/8/22
 */
@Controller
@RequestMapping("/kaptcha")
public class KaptchaController {

    @Autowired
    private DefaultKaptcha captchaProducer;

    /**
     * 生成验证码
     */
    @RequestMapping(value = "/randCode")
    public void getRandCode(HttpServletRequest request, HttpServletResponse response) {

        response.setDateHeader("Expires", 0);
        // Set standard HTTP/1.0
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");

        // 设置返回文件类型
        response.setContentType("image/jpeg");
        // 获取验证码上的文字
        String capText = captchaProducer.createText();
        // 将验证码上的文字保存在session中
        request.getSession().setAttribute(captchaProducer.getConfig().getSessionKey(), capText);

        //  将文件渲染到图片上
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            ImageIO.write(bi, "jpeg", out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
