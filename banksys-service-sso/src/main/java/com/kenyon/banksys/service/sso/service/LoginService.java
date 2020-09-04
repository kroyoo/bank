package com.kenyon.banksys.service.sso.service;

import com.kenyon.banksys.common.domain.BankEmployee;
import org.springframework.stereotype.Service;

/**
 * @author Kenyon
 * @title: LoginService
 * @projectName bank
 * @description: TODO
 * @date 9/9/20197:34 PM
 */


public interface LoginService {

    public BankEmployee login(String loginCode, String plantPassword);
}
