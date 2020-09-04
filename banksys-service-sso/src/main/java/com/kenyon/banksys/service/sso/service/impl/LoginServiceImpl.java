package com.kenyon.banksys.service.sso.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenyon.banksys.common.domain.BankEmployee;
import com.kenyon.banksys.common.utils.MapperUtils;
import com.kenyon.banksys.service.sso.mapper.BankEmployeeMapper;
import com.kenyon.banksys.service.sso.service.LoginService;
import com.kenyon.banksys.service.sso.service.consumer.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

/**
 * @author Kenyon
 * @title: LoginServiceImpl
 * @projectName bank
 * @description: TODO
 * @date 9/9/20197:35 PM
 */

@Service
public class LoginServiceImpl implements LoginService{

    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private RedisService redisService;

    @Autowired
    private BankEmployeeMapper bankEmployeeMapper;

    @Override
    public BankEmployee login(String loginCode, String planPassword) {

        BankEmployee bankEmployee = null;
        String json = redisService.get(loginCode);
        System.out.println("json = " + json);
        if(json == null) {
            Example example = new Example(BankEmployee.class);
            example.createCriteria().andEqualTo("employeeName", loginCode);
            bankEmployee = bankEmployeeMapper.selectOneByExample(example);
            String password = DigestUtils.md5DigestAsHex(planPassword.getBytes());
            if(bankEmployee != null && password.equals(bankEmployee.getEmployeePassword())){
                try {
                    redisService.put(loginCode, MapperUtils.obj2json(bankEmployee), 60*60*24);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return bankEmployee;
            }else {
                return null;
            }
        } else {
                try {
                    bankEmployee = new ObjectMapper().readValue(json, BankEmployee.class);
                } catch(Exception e) {
                    logger.warn("熔断触发:{}" + e.getMessage());
                }
        }

        return bankEmployee;
    }
}
