package com.kenyon.banksys.service.admin.controller;

import com.github.pagehelper.PageInfo;
import com.kenyon.banksys.common.dto.BaseResult;
import com.kenyon.banksys.common.utils.MapperUtils;
import com.kenyon.banksys.service.admin.domain.CardInfo;
import com.kenyon.banksys.service.admin.service.ICardInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.Bidi;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kenyon
 * @title: CardInfoController
 * @projectName bank
 * @description: TODO
 * @date 9/11/20197:21 AM
 */


@RestController
public class CardInfoController {

    @Autowired
    private ICardInfoService cardInfoService;



    @RequestMapping(value = "destroyAccount", method = RequestMethod.POST)
    public Map<String, String> isHavaBanlance(String destroyAccount ) {
        Map<String, String> result = new HashMap<>();
        CardInfo cardInfo = cardInfoService.queryById(Long.parseLong(destroyAccount));
        if(cardInfo != null) {

            if ( cardInfo.getCardStatus() != 1 ) {
                System.out.println("cardinfo status = " + cardInfo.getCardStatus());
                return null;
            }

            result.put("banlance", cardInfo.getCardBalance().toString());
            if (cardInfo.getCardBalance().compareTo(BigDecimal.ZERO) == 0) {
                result.put("havaBanlance", "not_banlance");
                result.put("msg" , "该账户没有存款可以销户销户");
            } else {
                result.put("havaBanlance", "have_banlance");
                result.put("msg" , "该账户还有余额，请取完之后再销户");
            }
            return result;
        }
        return null;
    }


    @RequestMapping(value = "haveDestroyAccount", method = RequestMethod.POST)
    public String haveDestroyAccount(String destroyAccount,  String password) {
        CardInfo cardInfo = cardInfoService.queryById(Long.parseLong(destroyAccount));
        String passwd = DigestUtils.md5DigestAsHex(password.getBytes());
        int flag = cardInfo.getCardBalance().compareTo(BigDecimal.ZERO);
        if(cardInfo != null && passwd.equals(cardInfo.getCardPassword())) {
            if (flag < 0) {
                return null;
            } else {
                cardInfo.setCardStatus(new Byte("3"));
                int res = cardInfoService.update(cardInfo);
                System.out.println("res = " + res);
                return "ok";
            }

        }
        return null;

    }


    @RequestMapping(value = "withdrawalMoney", method = RequestMethod.POST)
    public Map<String, String> withdrawalMoney(String qukuangjine, String destroyAccount,  String password) {
        Map<String, String> result = new HashMap<>();
        CardInfo cardInfo = cardInfoService.queryById(Long.parseLong(destroyAccount));
        String passwd = DigestUtils.md5DigestAsHex(password.getBytes());
        BigDecimal withdrawMoney = new BigDecimal(Integer.parseInt(qukuangjine));
        BigDecimal keepMoney;
        int flag = cardInfo.getCardBalance().compareTo(withdrawMoney);
        if(cardInfo != null && passwd.equals(cardInfo.getCardPassword())) {
            if (flag < 0){
                return null;
            } else {
                keepMoney = cardInfo.getCardBalance().subtract(withdrawMoney);
                System.out.println("keepMoney = " + keepMoney);
                if (keepMoney.compareTo(BigDecimal.ZERO) != -1) {
                    cardInfo.setCardBalance(keepMoney);
                    int res = cardInfoService.update(cardInfo);
                    result.put("banlance","取款成功，祝你生活愉快！你的账户余额为:" + keepMoney);
                    return result;
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    @RequestMapping(value = "/{cardNumber}", method = RequestMethod.GET)
    public BaseResult cardget(@PathVariable(value = "cardNumber") String cardNumber) {
        CardInfo  cardInfo = new CardInfo();
        cardInfo.setCardNumber(Long.parseLong(cardNumber));
        CardInfo cardInfo1 = cardInfoService.selectOne(cardInfo);
        return BaseResult.Ok(cardInfo1);
    }

    /**
     * 服务之间通信问题。通过json传送。
     * @param cardInfoJson
     * @return
     */

    @RequestMapping(value = "/cardSave",method = RequestMethod.POST)
    public BaseResult cardsave(@RequestParam(required = true) String cardInfoJson) {
        // 初始化结果
        int result = 0;
        CardInfo cardInfo = null;
        try {
            cardInfo = MapperUtils.json2pojo(cardInfoJson, CardInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(cardInfo != null) {

            String cardPassword = DigestUtils.md5DigestAsHex(cardInfo.getCardPassword().getBytes());
            String loginPassword = DigestUtils.md5DigestAsHex(cardInfo.getCardLoginPassword().getBytes());
            cardInfo.setCardPassword(cardPassword);
            cardInfo.setCardLoginPassword(loginPassword);
            //isBank is new cardInfo
            if(StringUtils.isBlank(cardInfo.getCardNumber().toString())) {

                cardInfoService.insert(cardInfo);
            } else {
                cardInfoService.update(cardInfo);
            }

        }
        if(result > 0) {
            return BaseResult.Ok("Save Success!");
        }
        return BaseResult.Ok("Save Failed");
    }

    @RequestMapping(value = "Cardpage/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public BaseResult cardpage(
            @PathVariable(required = true) int pageNum,
            @PathVariable(required = true) int pageSize,
            @RequestParam(required = false) String cardInfoJson) {

        CardInfo cardInfo = null;

        if(StringUtils.isNotBlank(cardInfoJson)) {
            try {
                cardInfo = MapperUtils.json2pojo(cardInfoJson, CardInfo.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        PageInfo pageInfo = cardInfoService.page(pageNum, pageSize, cardInfo);

        // pagehlper result
        List<CardInfo> infoList = pageInfo.getList();
        BaseResult.Cursor cursor = new BaseResult.Cursor();
        cursor.setTotal(new Long(pageInfo.getTotal()).intValue());
        cursor.setOffset(pageInfo.getPageNum());
        cursor.setLimit(pageInfo.getPageSize());
        return BaseResult.Ok(infoList, cursor);

    }
}
