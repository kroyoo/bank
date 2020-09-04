package com.kenyon.banksys.web.admin.service.fallback;

import com.google.common.collect.Lists;
import com.kenyon.banksys.common.dto.BaseResult;
import com.kenyon.banksys.common.utils.MapperUtils;
import com.kenyon.banksys.web.admin.service.IAdminService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author Kenyon
 * @title: AdminServiceFallback
 * @projectName bank
 * @description: TODO
 * @date 9/9/20191:24 AM
 */

@Component
public class AdminServiceFallback implements IAdminService {
    @Override
    public Map<String, String> isExit(String identify) {
        return null;
    }

    @Override
    public List<String> getAllGuests() {
        return null;
    }

    @Override
    public Map<String, String> isHavaBanlance(String destroyAccount) {
        return null;
    }

    @Override
    public Map<String, String> withdrawalMoney(String qukuangjine, String destroyAccount, String password) {
        return null;
    }

    @Override
    public String haveDestroyAccount(String destroyAccount, String password) {
        return null;
    }
/*    @Override
    public String login(String name, String pass) {
        BaseResult baseResult = BaseResult.notOk(Lists.newArrayList(new BaseResult.Error("502", "从上游服务器接收无效响应")));
        try {
            return MapperUtils.obj2json(baseResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/
}
