package com.kenyon.banksys.service.admin.service.impl;

import com.github.pagehelper.PageInfo;
import com.kenyon.banksys.service.admin.domain.CardInfo;
import com.kenyon.banksys.service.admin.domain.GuestInfo;
import com.kenyon.banksys.service.admin.mapper.CardInfoMapper;
import com.kenyon.banksys.service.admin.mapper.GuestInfoMapper;
import com.kenyon.banksys.service.admin.service.IGuestInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kenyon
 * @title: GuestInfoServiceImpl
 * @projectName bank
 * @description: TODO
 * @date 9/11/20197:25 AM
 */

@Service
@Transactional(readOnly = true)
public class GuestInfoServiceImpl implements IGuestInfoService{

    @Autowired
    private GuestInfoMapper guestInfoMapper;

    @Autowired
    private CardInfoMapper cardInfoMapper;

    @Override
    public int insert(GuestInfo guestInfo) {
        return 0;
    }

    @Override
    public int update(GuestInfo guestInfo) {
        return 0;
    }

    @Override
    public int delete(GuestInfo guestInfo) {
        return 0;
    }

    @Override
    public GuestInfo selectOne(GuestInfo guestInfo) {
        return null;
    }

    @Override
    public PageInfo<GuestInfo> page(int pageNum, int pageSize, GuestInfo guestInfo) {
        return null;
    }

    @Override
    public  Map<String, String>  isExit(String identify) {
        GuestInfo guestInfo = null;
        Map<String, String> result = new HashMap<>();

        if(StringUtils.isNotBlank(identify)) {
            Example example = new Example(GuestInfo.class);
            example.createCriteria().andEqualTo("guestIdentify", identify);
            guestInfo = guestInfoMapper.selectOneByExample(example);
            if(guestInfo != null) {
                Example example1 = new Example(CardInfo.class);
                Example.Criteria criteria = example1.createCriteria();
                criteria.andEqualTo("guestId", guestInfo.getGuestId());
                criteria.andEqualTo("cardType", new Byte("1"));
                int amount = cardInfoMapper.selectCountByExample(example1);
                result.put("ICard", Integer.toString(amount));
                result.put("cardNumber", Integer.toString(guestInfo.getGuestCardNumber()));
                return result;
            }
    }
        return null;
    }

    @Override
    public List<GuestInfo> selectAllGuest() {
        List<GuestInfo> guestInfoList = null;
        guestInfoList = guestInfoMapper.selectAll();
        System.out.println("guestInfoList = " + guestInfoList);
        return guestInfoList;
    }


}
