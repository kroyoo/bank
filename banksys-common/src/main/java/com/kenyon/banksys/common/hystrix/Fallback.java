package com.kenyon.banksys.common.hystrix;

import com.google.common.collect.Lists;
import com.kenyon.banksys.common.dto.BaseResult;
import com.kenyon.banksys.common.utils.MapperUtils;

/**
 * @author Kenyon
 * @title: Fallback
 * @projectName bank
 * @description: TODO
 * @date 9/9/20197:51 PM
 */
public class Fallback {

    public static String badGateway() {
        BaseResult baseResult = BaseResult.notOk(Lists.newArrayList(new BaseResult.Error("502", "从上游服务器接收无效响应")));
        try {
            return MapperUtils.obj2json(baseResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
