package ru.bytebratsk.bytesvc.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum OrderRepairType implements EnumClass<Integer> {

    UNKNOWN(0),
    PAID(10),
    WARRANTY(20),
    INTERNAL(30);

    private Integer id;

    OrderRepairType(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static OrderRepairType fromId(Integer id) {
        for (OrderRepairType at : OrderRepairType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}