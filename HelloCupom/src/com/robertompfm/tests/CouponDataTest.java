package com.robertompfm.tests;

import com.robertompfm.dao.CouponData;
import com.robertompfm.model.Coupon;
import com.robertompfm.model.CouponStatus;
import org.junit.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CouponDataTest {

    private static CouponData instance;

    private DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

    @BeforeClass
    public static void beforeClass() throws Exception {
        System.out.println("STARTING TESTS...");
        instance = CouponData.getInstance();
    }

    @Before
    public void setUp() throws Exception {
        instance.open();
    }

    @Ignore
    @Test
    public void insertCouponTest() throws Exception {
        System.out.println("FIRST");
        Coupon coupon = new Coupon("ABC1234", 100.00, LocalDate.now(),
                LocalDate.parse("2020-10-15", formatter), "test coupon");
        System.out.println(instance.insertCoupon(coupon));
    }

    @Ignore
    @Test
    public void removeCoupon() throws Exception {
        System.out.println("SECOND");
        Coupon coupon = new Coupon("ABC1234", 100.00, LocalDate.now(),
                LocalDate.parse("2020-10-15", formatter), "test coupon");
        System.out.println(instance.insertCoupon(coupon));
        System.out.println(instance.removeCoupon(coupon));
    }

    @Ignore
    @Test
    public void queryCoupon() {
        Coupon couponA = new Coupon("ABC1234", 100.00, LocalDate.now(),
                LocalDate.parse("2020-10-15", formatter), "test coupon");
        System.out.println(instance.insertCoupon(couponA));
        System.out.println(instance.queryCouponByCode(couponA.getCode()));

        Coupon couponB = new Coupon("XXX0000", 100.00, LocalDate.now(),
                LocalDate.parse("2020-10-15", formatter), "test coupon");
        couponB.setUsageDate(LocalDate.parse("2020-09-15"));
        couponB.setStatus(CouponStatus.EXPIRED);
        System.out.println(instance.insertCoupon(couponB));
        System.out.println(instance.queryCouponByCode(couponB.getCode()));

    }

    @Ignore
    @Test
    public void queryAllCoupons() {
        Coupon couponA = new Coupon("ABC1234", 100.00, LocalDate.now(),
                LocalDate.parse("2020-10-15", formatter), "test coupon");
        System.out.println(instance.insertCoupon(couponA));

        Coupon couponB = new Coupon("XXX0000", 150.00, LocalDate.now(),
                LocalDate.parse("2020-10-15", formatter), "test coupon");
        couponB.setUsageDate(LocalDate.parse("2020-09-15"));
        couponB.setStatus(CouponStatus.USED);
        System.out.println(instance.insertCoupon(couponB));

        Coupon couponC = new Coupon("XYZ6789", 50.00, LocalDate.now(),
                LocalDate.parse("2020-10-15", formatter), "test coupon");
        couponC.setStatus(CouponStatus.EXPIRED);
        System.out.println(instance.insertCoupon(couponC));

        ArrayList<Coupon> coupons = instance.queryAllCoupons();
        Assert.assertEquals(3, coupons.size());

    }

    @Ignore
    @Test
    public void updateCoupon() {
        Coupon couponOld = new Coupon("ABC1234", 100.00, LocalDate.now(),
                LocalDate.parse("2020-10-15", formatter), "old");
        System.out.println(instance.insertCoupon(couponOld));

        Coupon couponNew = new Coupon("XXX0000", 150.00, LocalDate.now(),
                LocalDate.parse("2020-11-12", formatter), "new");
        couponNew.setUsageDate(LocalDate.parse("2020-09-15"));
        couponNew.setStatus(CouponStatus.EXPIRED);
        System.out.println(instance.updateCoupon(couponOld, couponNew));
    }

    @Test
    public void updateCouponStatus() {
        Coupon coupon = new Coupon("ABC1234", 100.00, LocalDate.now(),
                LocalDate.parse("2020-10-15", formatter), "old");
        System.out.println(instance.insertCoupon(coupon));
        instance.updateCouponStatus(coupon, CouponStatus.EXPIRED);
    }

    @After
    public void tearDown() throws Exception {
        instance.close();
    }

    @AfterClass
    public static void afterClass() throws Exception {
        System.out.println("FINISHED");
    }
}