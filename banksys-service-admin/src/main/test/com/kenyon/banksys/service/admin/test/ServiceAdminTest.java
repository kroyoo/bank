package com.kenyon.banksys.service.admin.test;

/**
 * @author Kenyon
 * @title: ServiceAdminTest
 * @projectName bank
 * @description: TODO
 * @date 9/8/201910:57 AM
 */

import ch.qos.logback.core.net.SyslogOutputStream;
import com.kenyon.banksys.common.utils.BankNumberUtils;
import com.kenyon.banksys.common.utils.MapperUtils;
import com.kenyon.banksys.service.admin.ServiceAdminApplication;
import com.kenyon.banksys.service.admin.domain.CardInfo;
import com.kenyon.banksys.service.admin.domain.GuestInfo;
import com.kenyon.banksys.service.admin.service.IAdminService;
import com.kenyon.banksys.service.admin.service.ICardInfoService;
import com.kenyon.banksys.service.admin.service.IGuestInfoService;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceAdminApplication.class)
public class ServiceAdminTest {
    @Autowired
    private IAdminService adminService;

    @Autowired
    private IGuestInfoService guestInfoService;

    @Autowired
    private ICardInfoService cardInfoService;

 /*   @Test
    public void register() {
        BankEmployee bankEmployee = new BankEmployee();
//        bankEmployee.setEmployeeJobNumber(3451471);
        bankEmployee.setEmployeeName("admin");
        bankEmployee.setEmployeeGender("1");
        bankEmployee.setEmployeePassword("123456");
        bankEmployee.setEmployeeIdentify("441224200101104313");
        bankEmployee.setEmployeeAddress("GuangDong GuangZhou");
        bankEmployee.setEmployeeStatus("1");
        bankEmployee.setEmployeePhone("13800000000");
        bankEmployee.setEmployeeProcessResult("0");
        adminService.registerEmp(bankEmployee);
    }

    @Test
    public void login() {
        BankEmployee bankEmployee = adminService.loginEmp("3451473", "123456");
        Assert.assertNotNull(bankEmployee);
    }*/


    @Test
    public void testExit() {
        guestInfoService.isExit("442247199805244443");
    }

    @Test
    public void testQueryById() {
        CardInfo cardInfo = cardInfoService.queryById(Long.parseLong("444444444444444"));
        System.out.println(cardInfo);
    }

    @Test
    public void testSelectAll() {
        List<String> jsonList = new ArrayList<String>();

        String json = "";

        for(GuestInfo guestInfo : guestInfoService.selectAllGuest()) {

            try {
                json = (MapperUtils.obj2json(guestInfo));
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("json = " + json);
            System.out.println("----------------------------------");
            System.out.println(guestInfo);
        }

        System.out.println( guestInfoService.selectAllGuest());
    }
}
