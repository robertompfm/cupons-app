package com.robertompfm.tests;

import com.robertompfm.dao.CouponData;
import com.robertompfm.model.Coupon;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class CouponDataTest {

    private CouponData instance;

    @Before
    public void setUp() throws Exception {
        instance = CouponData.getInstance();
        instance.open();
    }

    @Test
    public void insertCouponTest() throws Exception {
        
    }

}