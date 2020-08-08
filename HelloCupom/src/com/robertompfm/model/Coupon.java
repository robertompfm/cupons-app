package com.robertompfm.model;

import java.time.LocalDate;

public class Coupon {

    // ATTRIBUTES
    private int id;
    private String code;
    private double value;
    private LocalDate registrationDate;
    private LocalDate expirationDate;
    private LocalDate usageDate;
    private CouponStatus status;
    private String description;


    // CONSTRUCTORS
    public Coupon(String code, double value, LocalDate registrationDate,
                  LocalDate expirationDate, String description) {
        this(-1, code, value, registrationDate, expirationDate, description);
    }

    public Coupon(int id, String code, double value, LocalDate registrationDate,
                  LocalDate expirationDate, String description) {
        this(id, code, value, registrationDate, expirationDate, null, CouponStatus.ACTIVE, description);
    }

    public Coupon(int id, String code, double value, LocalDate registrationDate, LocalDate expirationDate,
                  LocalDate usageDate, CouponStatus status, String description) {
        this.id = id;
        this.code = code;
        this.value = value;
        this.registrationDate = registrationDate;
        this.expirationDate = expirationDate;
        this.usageDate = usageDate;
        this.status = status;
        this.description = description;
    }

    // GETTERS AND SETTERS
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public LocalDate getUsageDate() {
        return usageDate;
    }

    public void setUsageDate(LocalDate usageDate) {
        this.usageDate = usageDate;
    }

    public CouponStatus getStatus() {
        return status;
    }

    public void setStatus(CouponStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    // TO_STRING
    @Override
    public String toString() {
        return "code: " + code +
                "; value: " + value +
                "; registrationDate: " + registrationDate +
                "; expirationDate: " + expirationDate +
                "; usageDate: " + usageDate +
                "; status: " + status +
                "; description:" + description;
    }
}
