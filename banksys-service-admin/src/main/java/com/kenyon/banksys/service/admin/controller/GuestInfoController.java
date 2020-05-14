package com.kenyon.banksys.service.admin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.kenyon.banksys.common.dto.BaseResult;
import com.kenyon.banksys.common.utils.MapperUtils;
import com.kenyon.banksys.service.admin.domain.CardInfo;
import com.kenyon.banksys.service.admin.domain.GuestInfo;
import com.kenyon.banksys.service.admin.service.IGuestInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Kenyon
 * @title: GuestInfoController
 * @projectName bank
 * @description: TODO
 * @date 9/22/20196:44 AM
 */

@RestController
public class GuestInfoController {

    @Autowired
    private IGuestInfoService guestInfoService;

    @RequestMapping(value = "guestId", method = RequestMethod.GET)
    public BaseResult get(@PathVariable(value = "guestId") String guestId) {
        GuestInfo guestInfo = new GuestInfo();
        guestInfo.setGuestId(Integer.parseInt(guestId));
        GuestInfo guestInfo1 = guestInfoService.selectOne(guestInfo);
        return BaseResult.Ok(guestInfo1);
    }

    @RequestMapping(value = "identify", method = RequestMethod.POST)
    public Map<String, String> isExit(String identify ) {
        System.out.println("identify = " + identify);
        Map<String, String> result = guestInfoService.isExit(identify);
        if(result != null) {

            return result;
        }
        return null;
    }


    @RequestMapping(value = "getAllList", method = RequestMethod.POST)
    public List<String> getAllGuests() {

        List<String> jsonList = new ArrayList<String>();
        List<GuestInfo> guestInfoList = guestInfoService.selectAllGuest();
        for(GuestInfo guestInfo : guestInfoList) {
            try {
                jsonList.add(new ObjectMapper().writeValueAsString(guestInfo));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return jsonList;
    }




    @RequestMapping(method = RequestMethod.POST)
    public BaseResult save(@RequestParam(required = true) String guestInfoJson) {
        // 初始化结果
        int result = 0;
        GuestInfo guestInfo = null;
        try {
            guestInfo = MapperUtils.json2pojo(guestInfoJson, GuestInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(guestInfo != null) {
            //isBank is new cardInfo
            if(StringUtils.isBlank(guestInfo.getGuestId().toString())) {
                guestInfoService.insert(guestInfo);
            } else {
                guestInfoService.update(guestInfo);
            }
        }
        if(result > 0) {
            return BaseResult.Ok("Save Success!");
        }
        return BaseResult.Ok("Save Failed");
    }


    @RequestMapping(value = "page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public BaseResult page(
            @PathVariable(required = true) int pageNum,
            @PathVariable(required = true) int pageSize,
            @RequestParam(required = false) String guestInfoJson) {

        GuestInfo guestInfo = null;

        if(StringUtils.isNotBlank(guestInfoJson)) {
            try {
                guestInfo = MapperUtils.json2pojo(guestInfoJson, GuestInfo.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        PageInfo pageInfo = guestInfoService.page(pageNum, pageSize, guestInfo);

        // pagehlper result
        List<CardInfo> infoList = pageInfo.getList();
        BaseResult.Cursor cursor = new BaseResult.Cursor();
        cursor.setTotal(new Long(pageInfo.getTotal()).intValue());
        cursor.setOffset(pageInfo.getPageNum());
        cursor.setLimit(pageInfo.getPageSize());
        return BaseResult.Ok(infoList, cursor);

    }
}
