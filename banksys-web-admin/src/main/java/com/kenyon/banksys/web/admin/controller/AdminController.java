package com.kenyon.banksys.web.admin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenyon.banksys.web.admin.service.IAdminService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kenyon
 * @title: AdminController
 * @projectName bank
 * @description: TODO
 * @date 9/9/20191:16 AM
 */

@Controller
public class AdminController {

    @Autowired
    private IAdminService adminService;

    // 定义全局banlance判断用户是否有余额
    private boolean haveBanlance = true;


    @RequestMapping(value = {"", "index", "index_backup.html"}, method = RequestMethod.GET)
    public String loginI() {
        return "index";
    }

    @RequestMapping(value = {"demo.html", "demo"}, method = RequestMethod.GET)
    public String redirectTest() {
        return "demo";
    }

    @RequestMapping(value = {"transfer.html", "transfer"}, method = RequestMethod.GET)
    public String transfer() {
        return "transfer";
    }

    @RequestMapping(value = {"destroyAccount.html", "destroyAccount"}, method = RequestMethod.GET)
    public String destroyAccount() {
        return "destroyAccount";
    }


    @RequestMapping(value = {"list.html", "list"}, method = RequestMethod.GET)
    public String list() {
        return "list";
    }

    @RequestMapping(value = {"home.html", "home"}, method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    @RequestMapping(value = {"mathme.html", "mathme"}, method = RequestMethod.GET)
    public String mathme() {
        return "mathme";
    }

    @RequestMapping(value = {"usercontrol.html", "usercontrol"}, method = RequestMethod.GET)
    public String usercontrol() {
        return "usercontrol";
    }

    @RequestMapping(value = {"userlist.html", "userlist"}, method = RequestMethod.GET)
    public String userlist() {
        return "userlist";
    }

    @RequestMapping(value = {"openAccount.html", "openAccount"}, method = RequestMethod.GET)
    public String openAccount() {
        return "openAccount";
    }

    @RequestMapping(value = {"qukuang.html", "qukuang"}, method = RequestMethod.GET)
    public String qukuang() {
        return "qukuang";
    }

    @RequestMapping(value = {"openSuccessful.html", "openSuccessful"}, method = RequestMethod.GET)
    public String openSuccessful() {
        return "openSuccessful";
    }

    /**
     * 判断用户是否已经开了户
     *
     * @param identify
     * @return
     */

    @ResponseBody
    @RequestMapping(value = "checked", method = RequestMethod.POST)
    public String isExit(@RequestParam(value = "identify") String identify) {
        Map<String, String> result = adminService.isExit(identify);
        Map<String, String> jsonMap = new HashMap<>();
        String json = null;
        if (result != null) {
            for (Object o : result.keySet()) {
                if ("1".equals(result.get(o)) && "ICard".equals(o)) {
                    jsonMap.put("ICard", "该用户已经开通I类账户");
                }
                if ("cardNumber".equals(o)) {
                    jsonMap.put("num", result.get(o));
                    jsonMap.put("cardNumber", "该用户已经开通" + result.get(o) + "张实体卡");
                }
            }
            if (jsonMap != null) {
                try {
                    json = new ObjectMapper().writeValueAsString(jsonMap);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
            return json;
        }
        return "not_ok";
    }


    /**
     * 返回用户列表
     * produces = "application/json;charset=UTF-8"
     *
     * @param
     * @return
     */

    @ResponseBody
    @RequestMapping(value = "getList", method = RequestMethod.POST)
    public List<String> getList() {
        List<String> jsonList = adminService.getAllGuests();
        return jsonList;
    }


    /**
     * 销户中判断是否有余额
     *
     * @param destroyAccount
     * @return
     */

    @ResponseBody
    @RequestMapping(value = "destroyAccount", method = RequestMethod.POST)
    public String isHavaBanlance(@RequestParam(value = "destroyAccount") String destroyAccount) {

        System.out.println(destroyAccount);
        if (StringUtils.isBlank(destroyAccount)) {
            return "not_ok";
        }
        Map<String, String> result = adminService.isHavaBanlance(destroyAccount);
        String json = "";
        if (result != null) {
            if ("have_banlance".equals(result.get("havaBanlance"))) {
                haveBanlance = true;
            } else {
                haveBanlance = false;
            }
            try {
                json = new ObjectMapper().writeValueAsString(result);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return json;
        }
        return "not_ok";

    }

    @ResponseBody
    @RequestMapping(value = "haveDestroyAccount", method = RequestMethod.POST)
    public String haveDestroyAccount(@RequestParam(value = "destroyAccount") String destroyAccount, @RequestParam(value = "password") String password) {
        if (StringUtils.isBlank(destroyAccount) || StringUtils.isBlank(password)) {
            return "not_ok";
        }
        if (haveBanlance) {
            return "error";
        }
        String json = adminService.haveDestroyAccount(destroyAccount, password);
        String js = null;
        if(StringUtils.isNotBlank(json)) {
            try {
                js = new ObjectMapper().writeValueAsString(json);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return js;
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "openAccount", method = RequestMethod.POST)
    public String openAccountHandler(@RequestParam(value = "identify") String identify) {
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "withdrawal", method = RequestMethod.POST)
    public String withdrawal(@RequestParam(value = "qukuangjine") String qukuangjine, @RequestParam(value = "destroyAccount") String destroyAccount, @RequestParam(value = "password") String password) {

        if (StringUtils.isBlank(destroyAccount) || StringUtils.isBlank(qukuangjine) || StringUtils.isBlank(password)) {
            return "not_ok";
        }
        Map<String, String> ree = adminService.withdrawalMoney(qukuangjine, destroyAccount, password);
        if (ree != null) {
            String json = "";
            try {
                json = new ObjectMapper().writeValueAsString(ree);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return json;
        }
        return null;
    }
}
