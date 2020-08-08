package com.robertompfm.tests;

import com.robertompfm.model.Coupon;
import com.robertompfm.model.CouponStatus;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class CouponTest {

    private Coupon coupon;

    private DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

    @org.junit.Before
    public void setUp() throws Exception {
        coupon = new Coupon("ABC1234", 100.00, LocalDate.now(),
                LocalDate.parse("2020-10-15", formatter), "test coupon");
    }

    @Test
    public void testToString() {
        System.out.println(coupon);
    }
}