package com.robertompfm.dao;

import com.robertompfm.model.Coupon;
import com.robertompfm.model.CouponStatus;
import com.robertompfm.tests.CouponTest;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class CouponData implements DataClass {

    // ATTRIBUTES
    private static Connection conn;

    private static CouponData instance = new CouponData();

    private PreparedStatement dropCouponTableStatement;
    private PreparedStatement createCouponTableStatement;

    private PreparedStatement queryAllCouponsStatement;
    private PreparedStatement queryCouponByCodeStatement;
    private PreparedStatement insertCouponStatement;
    private PreparedStatement removeCouponStatement;
    private PreparedStatement updateCouponStatement;
    private PreparedStatement updateCouponStatusStatement;

    // CONSTRUCTOR
    public static CouponData getInstance() {
        return instance;
    }

    // OPEN
    @Override
    public boolean open() {
        try {
            conn = DriverManager.getConnection(ConstantsDB.CONNECTION_STR);

            dropCouponTableStatement = conn.prepareStatement(ConstantsDB.DROP_COUPONS_TABLE);
            createCouponTableStatement = conn.prepareStatement(ConstantsDB.CREATE_COUPONS_TABLE);
            dropCouponTableStatement.execute();
            createCouponTableStatement.execute();

            queryAllCouponsStatement = conn.prepareStatement(ConstantsDB.QUERY_ALL_COUPONS);
            queryCouponByCodeStatement = conn.prepareStatement(ConstantsDB.QUERY_COUPONS_BY_CODE);
            insertCouponStatement = conn.prepareStatement(ConstantsDB.INSERT_COUPON);
            removeCouponStatement = conn.prepareStatement(ConstantsDB.DELETE_COUPON);
            updateCouponStatement = conn.prepareStatement(ConstantsDB.UPDATE_COUPON);
            updateCouponStatusStatement = conn.prepareStatement(ConstantsDB.UPDATE_COUPON_STATUS);

            return true;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            return false;
        }
    }

    // CLOSE
    @Override
    public boolean close() {
        try {
            if (dropCouponTableStatement != null) {
                dropCouponTableStatement.close();
            }
            if (createCouponTableStatement != null) {
                createCouponTableStatement.close();
            }
            if (queryAllCouponsStatement != null) {
                queryAllCouponsStatement.close();
            }
            if (queryCouponByCodeStatement != null) {
                queryCouponByCodeStatement.close();
            }
            if (insertCouponStatement != null) {
                insertCouponStatement.close();
            }
            if (removeCouponStatement != null) {
                removeCouponStatement.close();
            }
            if (updateCouponStatement != null) {
                updateCouponStatement.close();
            }
            if (updateCouponStatusStatement != null) {
                updateCouponStatusStatement.close();
            }

            return true;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            return false;
        }
    }

    // INSERT COUPON
    public boolean insertCoupon(Coupon coupon) {
        try {
            LocalDate usageDate = coupon.getUsageDate();
            String usageDateStr;
            if (usageDate == null) {
                usageDateStr = null;
            } else {
                usageDateStr = usageDate.toString();
            }
            insertCouponStatement.setString(1, coupon.getCode());
            insertCouponStatement.setDouble(2, coupon.getValue());
            insertCouponStatement.setString(3, coupon.getRegistrationDate().toString());
            insertCouponStatement.setString(4, coupon.getExpirationDate().toString());
            insertCouponStatement.setString(5, usageDateStr);
            insertCouponStatement.setString(6, coupon.getStatus().toString());
            insertCouponStatement.setString(7, coupon.getDescription());
            insertCouponStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            return false;
        }
    }

    // REMOVE COUPON
    public boolean removeCoupon(Coupon coupon) {
        try {
            removeCouponStatement.setString(1, coupon.getCode());
            removeCouponStatement.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            return false;
        }
    }

    // REMOVE COUPON
    public Coupon queryCouponByCode(String code) {
        try {
            queryCouponByCodeStatement.setString(1, code);
            ResultSet results = queryCouponByCodeStatement.executeQuery();
            Coupon coupon;
            if (results.next()) {
                int id = results.getInt(1);
                double value = results.getDouble(3);
                LocalDate registrationDate = LocalDate.parse(results.getString(4));
                LocalDate expirationDate = LocalDate.parse(results.getString(5));
                String usageDateStr = results.getString(6);
                LocalDate usageDate;
                if (usageDateStr != null) {
                    usageDate = LocalDate.parse(usageDateStr);
                } else {
                    usageDate = null;
                }
                CouponStatus status = CouponStatus.getEnum(results.getString(7));
                String description = results.getString(8);

                coupon = new Coupon(id, code, value, registrationDate, expirationDate, usageDate, status, description);

                return coupon;
            }
            return null;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            return null;
        }
    }

    // QUERY ALL COUPONS
    public ArrayList<Coupon> queryAllCoupons() {
        try {
            ArrayList<Coupon> coupons = new ArrayList<>();
            ResultSet results = queryAllCouponsStatement.executeQuery();
            while (results.next()) {
                Coupon coupon;
                int id = results.getInt(1);
                String code = results.getString(2);
                double value = results.getDouble(3);
                LocalDate registrationDate = LocalDate.parse(results.getString(4));
                LocalDate expirationDate = LocalDate.parse(results.getString(5));
                String usageDateStr = results.getString(6);
                LocalDate usageDate;
                if (usageDateStr != null) {
                    usageDate = LocalDate.parse(usageDateStr);
                } else {
                    usageDate = null;
                }
                CouponStatus status = CouponStatus.getEnum(results.getString(7));
                String description = results.getString(8);

                coupon = new Coupon(id, code, value, registrationDate, expirationDate, usageDate, status, description);
                coupons.add(coupon);
            }
            return coupons;

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            return new ArrayList<>();
        }
    }


    // UPDATE COUPON
    public boolean updateCoupon(Coupon oldCoupon, Coupon newCoupon) {
        try {
            updateCouponStatement.setString(1, newCoupon.getCode());
            updateCouponStatement.setDouble(2, newCoupon.getValue());
            updateCouponStatement.setString(3, newCoupon.getExpirationDate().toString());
            updateCouponStatement.setString(4, newCoupon.getUsageDate().toString());
            updateCouponStatement.setString(5, newCoupon.getStatus().toString());
            updateCouponStatement.setString(6, newCoupon.getDescription());
            updateCouponStatement.setString(7, oldCoupon.getCode());
            updateCouponStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            return false;
        }
    }

    // UPDATE STATUS OF ALL COUPONS
    public boolean updateCouponStatus(Coupon coupon, CouponStatus status) {
        try {
            updateCouponStatusStatement.setString(1, coupon.getStatus().toString());
            updateCouponStatusStatement.setString(2, coupon.getCode());
            updateCouponStatusStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            return false;
        }
    }
}
