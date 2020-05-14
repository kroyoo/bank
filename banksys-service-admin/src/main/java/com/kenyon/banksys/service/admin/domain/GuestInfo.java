package com.kenyon.banksys.service.admin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import javax.persistence.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "guest_info")
public class GuestInfo {
    @Id
    @Column(name = "guest_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer guestId;

    @Column(name = "guest_name")
    private String guestName;

    @Column(name = "guest_contact")
    private String guestContact;

    @Column(name = "guest_address")
    private String guestAddress;

    @Column(name = "guest_identify")
    private String guestIdentify;

    @Column(name = "guest_create_time")
    private Date guestCreateTime;

    @Column(name = "guest_status")
    private String guestStatus;

    @Column(name = "guest_remark")
    private String guestRemark;

    @Column(name = "guest_card_number")
    private Byte guestCardNumber;

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
     * @return guest_name
     */
    public String getGuestName() {
        return guestName;
    }

    /**
     * @param guestName
     */
    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    /**
     * @return guest_contact
     */
    public String getGuestContact() {
        return guestContact;
    }

    /**
     * @param guestContact
     */
    public void setGuestContact(String guestContact) {
        this.guestContact = guestContact;
    }

    /**
     * @return guest_address
     */
    public String getGuestAddress() {
        return guestAddress;
    }

    /**
     * @param guestAddress
     */
    public void setGuestAddress(String guestAddress) {
        this.guestAddress = guestAddress;
    }

    /**
     * @return guest_identify
     */
    public String getGuestIdentify() {
        return guestIdentify;
    }

    /**
     * @param guestIdentify
     */
    public void setGuestIdentify(String guestIdentify) {
        this.guestIdentify = guestIdentify;
    }

    /**
     * @return guest_create_time
     */
    public Date getGuestCreateTime() {
        return guestCreateTime;
    }

    /**
     * @param guestCreateTime
     */
    public void setGuestCreateTime(Date guestCreateTime) {
        this.guestCreateTime = guestCreateTime;
    }

    /**
     * @return guest_status
     */
    public String getGuestStatus() {
        return guestStatus;
    }

    /**
     * @param guestStatus
     */
    public void setGuestStatus(String guestStatus) {
        this.guestStatus = guestStatus;
    }

    /**
     * @return guest_remark
     */
    public String getGuestRemark() {
        return guestRemark;
    }

    /**
     * @param guestRemark
     */
    public void setGuestRemark(String guestRemark) {
        this.guestRemark = guestRemark;
    }

    /**
     * @return guest_card_number
     */
    public Byte getGuestCardNumber() {
        return guestCardNumber;
    }

    /**
     * @param guestCardNumber
     */
    public void setGuestCardNumber(Byte guestCardNumber) {
        this.guestCardNumber = guestCardNumber;
    }
}