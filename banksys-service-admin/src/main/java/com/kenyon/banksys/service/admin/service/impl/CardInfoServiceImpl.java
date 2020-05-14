package com.kenyon.banksys.service.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kenyon.banksys.service.admin.domain.CardInfo;
import com.kenyon.banksys.service.admin.mapper.CardInfoMapper;
import com.kenyon.banksys.service.admin.service.ICardInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Map;

/**
 * @author Kenyon
 * @title: CardInfoServiceImpl
 * @projectName bank
 * @description: TODO
 * @date 9/11/20197:10 AM
 */

@Service
@Transactional(readOnly = false)
public class CardInfoServiceImpl implements ICardInfoService{

    @Autowired
    private CardInfoMapper cardInfoMapper;

    @Override
    public int insert(CardInfo cardInfo) {
        return 0;
    }

    @Override
    public int update(CardInfo cardInfo) {
        Example example = new Example(CardInfo.class);
        return cardInfoMapper.updateByPrimaryKey(cardInfo);
    }

    @Override
    public int delete(CardInfo cardInfo) {
        return 0;
    }

    @Override
    public CardInfo selectOne(CardInfo cardInfo) {
        return cardInfoMapper.selectOne(cardInfo);
    }

    @Override
    public PageInfo<CardInfo> page(int pageNum, int pageSize, CardInfo cardInfo) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<CardInfo> pageInfo = new PageInfo<>(cardInfoMapper.select(cardInfo));
        return pageInfo;
    }

    @Override
    public Map<String, String> account(Integer guestId) {
        System.out.println("guestId = " + guestId);

        Example example = new Example(CardInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("guestId",guestId);
        criteria.andEqualTo("cardStatus", new Byte("1"));
        int amount = cardInfoMapper.selectCountByExample(example);

        return null;
    }

    @Override
    public CardInfo queryById(Long cardNumber) {
        Example example = new Example(CardInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("cardNumber", cardNumber);
        return cardInfoMapper.selectOneByExample(example);
    }
}
