package com.kenyon.banksys.service.admin.controller;


import com.kenyon.banksys.service.admin.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Kenyon
 * @title: AdminController
 * @projectName bank
 * @description: TODO
 * @date 9/4/20193:54 AM
 */

@RestController
public class AdminController {

    @Autowired
    private IAdminService adminService;

}
