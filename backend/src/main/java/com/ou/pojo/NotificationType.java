package com.ou.pojo;

public enum NotificationType {

    MONTHLY_BILL("Hoá đơn hàng tháng",1),
    MONTHLY_EVALUATION("Đánh giá hàng tháng",2)
    ;

    NotificationType(String type, int code) {
        this.type = type;
        this.code = code;
    }

    private String type;
    private int code;
}
