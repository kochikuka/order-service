package ru.bytebratsk.bytesvc.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum OrderStatus implements EnumClass<Integer> {

    NEW(10),
    IN_DIAG(20),
    IN_SPARE_DELIVERY(30),
    IN_AGREEMENT(40),
    REPAIR(50),
    READY(60),
    OUTBACK(65),
    COMPLETED(70);

    private Integer id;

    OrderStatus(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static OrderStatus fromId(Integer id) {
        for (OrderStatus at : OrderStatus.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}