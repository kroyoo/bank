package com.kenyon.banksys.service.admin.service.impl;

import com.kenyon.banksys.common.domain.BankEmployee;
import com.kenyon.banksys.service.admin.service.IAdminService;
import com.kenyon.banksys.service.admin.mapper.BankEmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

/**
 * @author Kenyon
 * @title: AdminServiceImpl
 * @projectName bank
 * @description: TODO
 * @date 9/7/20197:42 AM
 */

@Service
@Transactional(readOnly = true)
public class AdminServiceImpl implements IAdminService{
    @Autowired
    private BankEmployeeMapper bankEmployeeMapper;



}
