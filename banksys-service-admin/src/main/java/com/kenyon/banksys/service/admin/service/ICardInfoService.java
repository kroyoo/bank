package com.kenyon.banksys.service.admin.service;

import com.github.pagehelper.PageInfo;
import com.kenyon.banksys.service.admin.domain.CardInfo;
import java.util.Map;

/**
 * @author Kenyon
 * @title: ICardInfoService
 * @projectName bank
 * @description: TODO
 * @date 9/11/20197:10 AM
 */
public interface ICardInfoService {

    int insert(CardInfo cardInfo);

    int update(CardInfo cardInfo);

    int delete(CardInfo cardInfo);

    CardInfo selectOne(CardInfo cardInfo);

    PageInfo<CardInfo> page(int pageNum, int pageSize, CardInfo cardInfo);

    Map<String, String> account(Integer guestId);

    CardInfo queryById(Long cardNumber) ;
}
