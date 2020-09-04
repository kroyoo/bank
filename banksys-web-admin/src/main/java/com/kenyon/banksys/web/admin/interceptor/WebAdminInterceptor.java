package com.kenyon.banksys.web.admin.interceptor;

import com.kenyon.banksys.common.constans.WebConstans;
import com.kenyon.banksys.common.domain.BankEmployee;
import com.kenyon.banksys.common.utils.CookieUtils;
import com.kenyon.banksys.common.utils.MapperUtils;
import com.kenyon.banksys.web.admin.service.RedisService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.WebConnection;
import java.io.IOException;

/**
 * @author Kenyon
 * @title: WebAdminInterceptor
 * @projectName bank
 * @description: TODO
 * @date 9/10/20198:06 PM
 */

@Component
public class WebAdminInterceptor implements HandlerInterceptor{

    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = CookieUtils.getCookieValue(request, WebConstans.SEESION_TOKEN);
        // 判断是否存在token
        if(StringUtils.isBlank(token)) {
            try {
                response.sendRedirect("http://localhost:8807/login?url=http://localhost:8901");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        HttpSession session = request.getSession();
        BankEmployee bankEmployee = (BankEmployee)session.getAttribute(WebConstans.SEESION_UER);

        if(bankEmployee != null) {
            if(modelAndView != null) {
                modelAndView.addObject(WebConstans.SEESION_UER, bankEmployee);
            }
        } else {
            String token = CookieUtils.getCookieValue(request,WebConstans.SEESION_TOKEN);
            if(StringUtils.isNotBlank(token)) {
                String loginCode = redisService.get(token);
                if(StringUtils.isNotBlank(loginCode)) {
                    String json = redisService.get(loginCode);
                    if(StringUtils.isNotBlank(json)) {
                        try {
                            bankEmployee = MapperUtils.json2pojo(json, BankEmployee.class);
                            if(modelAndView != null) {
                                modelAndView.addObject(WebConstans.SEESION_UER, bankEmployee);
                            }
                            request.getSession().setAttribute(WebConstans.SEESION_UER, bankEmployee);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        // Second comfirm login
        if(bankEmployee ==null) {
            try {
                response.sendRedirect("http://localhost:8807/login?url=http://localhost:8901");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
