package com.robertompfm.dao;

public final class ConstantsDB {

    // DATABASE
    public static final String DB_NAME = "coupons.db";
    public static final String CONNECTION_STR = "jdbc:sqlite:" + DB_NAME;


    // TABLES
    public static final String COUPONS_TABLE = "coupons";

    //COUPONS
    public static final String DROP_COUPONS_TABLE = "DROP TABLE IF EXISTS " + COUPONS_TABLE;
    public static final String CREATE_COUPONS_TABLE = "CREATE TABLE IF NOT EXISTS " + COUPONS_TABLE +
            " (_id INTEGER PRIMARY KEY, code TEXT NOT NULL UNIQUE, value INTEGER NOT NULL," +
            " registration_date TEXT NOT NULL, expiration_date TEXT NOT NULL," +
            " usage_date TEXT, status TEXT NOT NULL, description TEXT)";
    public static final String INSERT_COUPON = "INSERT INTO " + COUPONS_TABLE +
            " (code, value, registration_date, expiration_date, usage_date, status, description)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?)";
    public static final String DELETE_COUPON = "DELETE FROM " + COUPONS_TABLE +
            " WHERE code = ?";
    public static final String QUERY_ALL_COUPONS = "SELECT * FROM " + COUPONS_TABLE;
    public static final String QUERY_COUPONS_BY_STATUS = "SELECT * FROM " + COUPONS_TABLE +
            " WHERE status = ?";
    public static final String QUERY_COUPONS_BY_CODE = "SELECT * FROM " + COUPONS_TABLE +
            " WHERE code = ?";

}
