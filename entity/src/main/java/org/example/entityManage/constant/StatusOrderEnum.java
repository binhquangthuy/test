package org.example.entityManage.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusOrderEnum {

    PAID(1, "order is paid"),
    NOT_PAID(2, "order is not paid"),
    CANCEL(3, "order is cancel");

    private Integer value;
    private String description;

    public static StatusOrderEnum getValue(Integer value) {
        for (StatusOrderEnum e : values()) {
            if (e.value.equals(value)) {
                return e;
            }
        }
        return null;
    }
}
