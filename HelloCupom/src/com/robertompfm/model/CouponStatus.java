package com.robertompfm.model;

public enum CouponStatus {
    ACTIVE ("Ativo"),
    EXPIRED ("Expirado"),
    USED ("Usado");

    private final String status;

    private CouponStatus(String status) {
        this.status = status;
    }

    public boolean equalsName(String otherName) {
        return this.status.equals(otherName);
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return this.status;
    }

    public static CouponStatus getEnum(String value) {
        for (CouponStatus s : values()) {
            if (s.getStatus().equalsIgnoreCase(value)) return s;
        }
        throw new IllegalArgumentException();
    }
}
