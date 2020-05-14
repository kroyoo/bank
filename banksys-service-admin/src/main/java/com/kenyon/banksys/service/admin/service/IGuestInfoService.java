package com.kenyon.banksys.service.admin.service;

import com.github.pagehelper.PageInfo;
import com.kenyon.banksys.service.admin.domain.GuestInfo;

import java.util.List;
import java.util.Map;

/**
 * @author Kenyon
 * @title: IGuestInfoService
 * @projectName bank
 * @description: TODO
 * @date 9/11/20197:23 AM
 */
public interface IGuestInfoService {

    int insert(GuestInfo guestInfo);

    int update(GuestInfo guestInfo);

    int delete(GuestInfo guestInfo);

    GuestInfo selectOne(GuestInfo guestInfo);

    PageInfo<GuestInfo> page(int pageNum, int pageSize, GuestInfo guestInfo);

    Map<String, String> isExit(String identify);

    List<GuestInfo> selectAllGuest();


}
