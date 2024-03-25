package com.cyl.mybatisplus.controller;

import com.cyl.mybatisplus.pojo.result.Result;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
public class CodeController {
    //验证码的有效时间:ms
    private static final Long SESSION_TIMEOUT = 60 * 1000L;


    @Autowired
    private Producer captchaProducer ;

    /**
    * 验证码请求接口
     */
    @RequestMapping("/code/get")
    public void getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        //生成验证码
        String capText = captchaProducer.createText();
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);               //写入验证码
        session.setAttribute(Constants.KAPTCHA_SESSION_DATE, LocalDateTime.now());  //写入时间
        //向客户端写出
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }

    /**
     * 验证码验证接口
     */
    @GetMapping("/code/check")
    public Boolean checkCode(@RequestParam(value = "code", required = true) String code,
                                     HttpSession session){

        String trueCode = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        Date firstTime = (Date) session.getAttribute(Constants.KAPTCHA_SESSION_DATE);
        //验证码长度不相等 或者 超时
        if(trueCode.length() != code.length() || System.currentTimeMillis() - firstTime.getTime() > SESSION_TIMEOUT)
            return false;

        //验证码不同
        if(!trueCode.equals(code))
            return false;
        return true;
    }
}
