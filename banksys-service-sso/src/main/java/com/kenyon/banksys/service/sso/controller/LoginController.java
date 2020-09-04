package com.kenyon.banksys.service.sso.controller;

import com.kenyon.banksys.common.constans.WebConstans;
import com.kenyon.banksys.common.domain.BankEmployee;
import com.kenyon.banksys.common.utils.CookieUtils;
import com.kenyon.banksys.common.utils.MapperUtils;
import com.kenyon.banksys.service.sso.service.LoginService;
import com.kenyon.banksys.service.sso.service.consumer.RedisService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author Kenyon
 * @title: LoginController
 * @projectName bank
 * @description: TODO
 * @date 9/9/20198:04 PM
 */

@Controller
public class LoginController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(
            @RequestParam(required = false) String url,
            HttpServletRequest request, Model model) {
        String token = CookieUtils.getCookieValue(request, WebConstans.SEESION_TOKEN);

        if(StringUtils.isNotBlank(token)) {
            String loginCode = redisService.get(token);
            if(StringUtils.isNotBlank(loginCode)) {
                String json = redisService.get(loginCode);
                if(StringUtils.isNotBlank(json)) {
                    try {
                        BankEmployee bankEmployee = MapperUtils.json2pojo(json, BankEmployee.class);
                        if(bankEmployee != null) {
                            if(StringUtils.isNotBlank(url)) {
                                return "redirect:" + url;
                            }
                        }
                        return "login";
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        if(StringUtils.isNotBlank(url)) {
            model.addAttribute("url", url);
        }

        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(
            @RequestParam(required = true) String loginCode,
            @RequestParam(required = true) String planPassword,
            @RequestParam(required = false) String url,
            HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {

        if(StringUtils.isBlank(loginCode) || StringUtils.isBlank(planPassword)) {
            System.out.println("非法操作");
            redirectAttributes.addFlashAttribute("message", "警告！非法操作！");
            if (StringUtils.isNotBlank(url)) {
                return "redirect:/login?url=" + url;
            }
            return "redirect:/login";
        }
        BankEmployee bankEmployee = loginService.login(loginCode, planPassword);

        if (bankEmployee == null) {
            redirectAttributes.addFlashAttribute("message", "用户名或密码错误");
        } else {
            String token = UUID.randomUUID().toString();
            String result = redisService.put(token, loginCode, 60 * 60 * 24);
            if (result != null && "ok".equals(result)) {
                CookieUtils.setCookie(request, response, WebConstans.SEESION_TOKEN, token);
                if (StringUtils.isNotBlank(url)) {
                    return "redirect:" + url;
                }
                redirectAttributes.addFlashAttribute("message", "欢迎登录！");
            } else {
                redirectAttributes.addFlashAttribute("message", "服务器问题，请稍后再试");
            }
        }

        if (StringUtils.isNotBlank(url)) {
            return "redirect:/login?url=" + url;
        }

        return "redirect:/login";
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request,
                       HttpServletResponse response,
                       @RequestParam(required = false) String url,
                       Model model) {
        try {
            String token = CookieUtils.getCookieValue(request, WebConstans.SEESION_TOKEN);
            if(StringUtils.isNotBlank(token)) {
                String loginCode = redisService.get(token);
                redisService.remove(token);
                if(StringUtils.isNotBlank(loginCode)) {
                    redisService.remove(loginCode);
                }
            }
            CookieUtils.deleteCookie(request, response, WebConstans.SEESION_TOKEN);
        } catch (Exception e) {
            e.getMessage();
        }
        return login(url, request, model);
    }
}
