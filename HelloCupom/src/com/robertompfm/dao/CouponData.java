package com.robertompfm.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    public static CouponData getInstance() {
        return instance;
    }

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

            return true;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            return false;
        }
    }

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

            return true;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            return false;
        }
    }
}
