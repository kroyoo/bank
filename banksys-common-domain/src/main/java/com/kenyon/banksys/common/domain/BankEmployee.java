package com.kenyon.banksys.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Kenyon
 */

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bank_employee")
public class BankEmployee {
    @Id
    @Column(name = "employee_job_number")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeJobNumber;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "employee_gender")
    private String employeeGender;

    @Column(name = "employee_password")
    private String employeePassword;

    @Column(name = "employee_identify")
    private String employeeIdentify;

    @Column(name = "employee_address")
    private String employeeAddress;

    @Column(name = "employee_status")
    private String employeeStatus;

    @Column(name = "employee_phone")
    private String employeePhone;

    @Column(name = "employee_process_result")
    private String employeeProcessResult;

    /**
     * @return employee_job_number
     */
    public Integer getEmployeeJobNumber() {
        return employeeJobNumber;
    }

    /**
     * @param employeeJobNumber
     */
    public void setEmployeeJobNumber(Integer employeeJobNumber) {
        this.employeeJobNumber = employeeJobNumber;
    }

    /**
     * @return employee_name
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * @param employeeName
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    /**
     * @return employee_gender
     */
    public String getEmployeeGender() {
        return employeeGender;
    }

    /**
     * @param employeeGender
     */
    public void setEmployeeGender(String employeeGender) {
        this.employeeGender = employeeGender;
    }

    /**
     * @return employee_password
     */
    public String getEmployeePassword() {
        return employeePassword;
    }

    /**
     * @param employeePassword
     */
    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }

    /**
     * @return employee_identify
     */
    public String getEmployeeIdentify() {
        return employeeIdentify;
    }

    /**
     * @param employeeIdentify
     */
    public void setEmployeeIdentify(String employeeIdentify) {
        this.employeeIdentify = employeeIdentify;
    }

    /**
     * @return employee_address
     */
    public String getEmployeeAddress() {
        return employeeAddress;
    }

    /**
     * @param employeeAddress
     */
    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    /**
     * @return employee_status
     */
    public String getEmployeeStatus() {
        return employeeStatus;
    }

    /**
     * @param employeeStatus
     */
    public void setEmployeeStatus(String employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    /**
     * @return employee_phone
     */
    public String getEmployeePhone() {
        return employeePhone;
    }

    /**
     * @param employeePhone
     */
    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }

    /**
     * @return employee_process_result
     */
    public String getEmployeeProcessResult() {
        return employeeProcessResult;
    }

    /**
     * @param employeeProcessResult
     */
    public void setEmployeeProcessResult(String employeeProcessResult) {
        this.employeeProcessResult = employeeProcessResult;
    }
}