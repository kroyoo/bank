package com.kenyon.banksys.service.admin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import javax.persistence.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "card_info")
public class CardInfo {
    @Id
    @Column(name = "card_number")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardNumber;

    @Column(name = "card_balance")
    private BigDecimal cardBalance;

    @Column(name = "card_open_oney")
    private BigDecimal cardOpenOney;

    @Column(name = "guest_id")
    private Integer guestId;

    @Column(name = "card_password")
    private String cardPassword;

    @Column(name = "card_status")
    private Byte cardStatus;

    @Column(name = "card_type")
    private Integer cardType;

    @Column(name = "card_login_password")
    private String cardLoginPassword;

    @Column(name = "card_phone")
    private String cardPhone;

    @Column(name = "card_remark")
    private String cardRemark;

    /**
     * @return card_number
     */
    public Long getCardNumber() {
        return cardNumber;
    }

    /**
     * @param cardNumber
     */
    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * @return card_balance
     */
    public BigDecimal getCardBalance() {
        return cardBalance;
    }

    /**
     * @param cardBalance
     */
    public void setCardBalance(BigDecimal cardBalance) {
        this.cardBalance = cardBalance;
    }

    /**
     * @return card_open_oney
     */
    public BigDecimal getCardOpenOney() {
        return cardOpenOney;
    }

    /**
     * @param cardOpenOney
     */
    public void setCardOpenOney(BigDecimal cardOpenOney) {
        this.cardOpenOney = cardOpenOney;
    }

    /**
     * @return guest_id
     */
    public Integer getGuestId() {
        return guestId;
    }

    /**
     * @param guestId
     */
    public void setGuestId(Integer guestId) {
        this.guestId = guestId;
    }

    /**
     * @return card_password
     */
    public String getCardPassword() {
        return cardPassword;
    }

    /**
     * @param cardPassword
     */
    public void setCardPassword(String cardPassword) {
        this.cardPassword = cardPassword;
    }

    /**
     * @return card_status
     */
    public Byte getCardStatus() {
        return cardStatus;
    }

    /**
     * @param cardStatus
     */
    public void setCardStatus(Byte cardStatus) {
        this.cardStatus = cardStatus;
    }

    /**
     * @return card_type
     */
    public Integer getCardType() {
        return cardType;
    }

    /**
     * @param cardType
     */
    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    /**
     * @return card_login_password
     */
    public String getCardLoginPassword() {
        return cardLoginPassword;
    }

    /**
     * @param cardLoginPassword
     */
    public void setCardLoginPassword(String cardLoginPassword) {
        this.cardLoginPassword = cardLoginPassword;
    }

    /**
     * @return card_phone
     */
    public String getCardPhone() {
        return cardPhone;
    }

    /**
     * @param cardPhone
     */
    public void setCardPhone(String cardPhone) {
        this.cardPhone = cardPhone;
    }

    /**
     * @return card_remark
     */
    public String getCardRemark() {
        return cardRemark;
    }

    /**
     * @param cardRemark
     */
    public void setCardRemark(String cardRemark) {
        this.cardRemark = cardRemark;
    }
}