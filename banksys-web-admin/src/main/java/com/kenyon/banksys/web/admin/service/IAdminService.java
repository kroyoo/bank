package com.kenyon.banksys.web.admin.service;

import com.kenyon.banksys.common.dto.BaseResult;
import com.kenyon.banksys.web.admin.service.fallback.AdminServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;
import java.util.Map;

/**
 * @author Kenyon
 * @title: IAdminService
 * @projectName bank
 * @description: TODO
 * @date 9/9/20191:20 AM
 */

@FeignClient(value = "banksys-service-admin", fallback = AdminServiceFallback.class)
public interface IAdminService {


    // 记得加上@RequestParam绑定参数
    @RequestMapping(value = "identify", method = RequestMethod.POST)
    public Map<String, String> isExit(@RequestParam(value = "identify") String identify );

    @RequestMapping(value = "getAllList", method = RequestMethod.POST)
    public List<String> getAllGuests();

    @RequestMapping(value = "destroyAccount", method = RequestMethod.POST)
    public Map<String, String> isHavaBanlance(@RequestParam(value = "destroyAccount") String destroyAccount ) ;

    @RequestMapping(value = "withdrawalMoney", method = RequestMethod.POST)
    public Map<String, String> withdrawalMoney(@RequestParam(value = "qukuangjine") String qukuangjine, @RequestParam(value = "destroyAccount") String destroyAccount, @RequestParam(value = "password") String password);

    @RequestMapping(value = "haveDestroyAccount", method = RequestMethod.POST)
    public String haveDestroyAccount(@RequestParam(value = "destroyAccount")String destroyAccount,  @RequestParam(value = "password")String password);
}
