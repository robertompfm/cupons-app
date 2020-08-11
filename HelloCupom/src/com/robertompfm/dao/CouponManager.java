package com.robertompfm.dao;

import com.robertompfm.model.Coupon;
import com.robertompfm.model.CouponStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class CouponManager {

    // ATTRIBUTES
    private static CouponManager instance = new CouponManager();
    private CouponData data;
    private ObservableList<Coupon> coupons;
    private Coupon currentCoupon;

    private boolean isExpirationDateFilterActive;
    private boolean isStatusFilterActive;
    private LocalDate initialDateFilter;
    private LocalDate finalDateFilter;
    private CouponStatus statusFilter;

    // CONSTRUCTOR
    public CouponManager() {
        data = CouponData.getInstance();
        coupons = FXCollections.observableArrayList();
        currentCoupon = null;

        isExpirationDateFilterActive = false;
        isStatusFilterActive = false;
        initialDateFilter = null;
        finalDateFilter = null;
        statusFilter = null;
    }

    // GETTERS
    public static CouponManager getInstance() { return instance; }
    public ObservableList<Coupon> getCoupons() { return coupons; }
    public Coupon getCurrentCoupon() { return currentCoupon; }

    public boolean isExpirationDateFilterActive() {
        return isExpirationDateFilterActive;
    }

    public boolean isStatusFilterActive() {
        return isStatusFilterActive;
    }

    public LocalDate getInitialDateFilter() {
        return initialDateFilter;
    }

    public LocalDate getFinalDateFilter() {
        return finalDateFilter;
    }

    public CouponStatus getStatusFilter() {
        return statusFilter;
    }

    // SETTERS
    public void setCurrentCoupon(Coupon newCoupon) { currentCoupon = newCoupon; }

    // UPDATE COUPONS
    public void updateCoupons() {
        data.open();
        ArrayList<Coupon> queriedCoupons = data.queryAllCoupons();
        if (isExpirationDateFilterActive) {
            queriedCoupons = filterByExpirationDate(queriedCoupons);
        }
        if (isStatusFilterActive) {
            queriedCoupons = filterByStatus(queriedCoupons);
        }
        coupons.setAll(queriedCoupons);
        data.close();
    }

    // ADD COUPON
    public void addCoupon(Coupon coupon) {
        data.open();
        data.insertCoupon(coupon);
        data.close();
        updateCoupons();
    }

    // EDIT COUPON
    public void editCoupon(Coupon oldCoupon, Coupon newCoupon) {
        data.open();
        data.updateCoupon(oldCoupon, newCoupon);
        data.close();
        updateCoupons();
    }

    // DELETE COUPON
    public void deleteCoupon(Coupon coupon) {
        data.open();
        data.removeCoupon(coupon);
        data.close();
        updateCoupons();
    }

    // SEARCH FOR COUPON
    public Coupon searchCoupon(String code) {
        data.open();
        Coupon coupon = data.queryCouponByCode(code);
        data.close();
        return coupon;
    }

    public void setExpirationDateFilter(boolean isActive, LocalDate initialDate, LocalDate finalDate) {
        this.isExpirationDateFilterActive = isActive;
        this.initialDateFilter = initialDate;
        this.finalDateFilter = finalDate;
    }

    public void setStatusFilter(boolean isActive, CouponStatus status) {
        this.isStatusFilterActive = isActive;
        this.statusFilter = status;
    }

    private ArrayList<Coupon> filterByExpirationDate(ArrayList<Coupon> queriedCoupons) {
        return queriedCoupons.stream().filter(
                c -> ((c.getExpirationDate().compareTo(initialDateFilter) >= 0) &&
                        (c.getExpirationDate().compareTo(finalDateFilter) <= 0)))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private ArrayList<Coupon> filterByStatus(ArrayList<Coupon> queriedCoupons) {
        return queriedCoupons.stream().filter(c -> c.getStatus() == statusFilter)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}

