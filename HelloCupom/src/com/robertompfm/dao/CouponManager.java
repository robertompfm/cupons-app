package com.robertompfm.dao;

import com.robertompfm.model.Coupon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class CouponManager {

    // ATTRIBUTES
    private static CouponManager instance = new CouponManager();
    private CouponData data;
    private ObservableList<Coupon> coupons;
    private Coupon currentCoupon;

    // CONSTRUCTOR
    public CouponManager() {
        data = CouponData.getInstance();
        coupons = FXCollections.observableArrayList();
        currentCoupon = null;
    }

    // GETTERS
    public static CouponManager getInstance() { return instance; }
    public ObservableList<Coupon> getCoupons() { return coupons; }
    public Coupon getCurrentCoupon() { return currentCoupon; }

    // SETTERS
    public void setCurrentCoupon(Coupon newCoupon) { currentCoupon = newCoupon; }

    // UPDATE COUPONS
    public void updateCoupons() {
        data.open();
        coupons.setAll(data.queryAllCoupons());
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
}

