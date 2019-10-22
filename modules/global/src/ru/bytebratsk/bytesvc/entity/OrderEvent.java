package ru.bytebratsk.bytesvc.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;

@NamePattern("%s|id")
@Table(name = "BYTESVC_ORDER_EVENT")
@Entity(name = "bytesvc_OrderEvent")
public class OrderEvent extends StandardEntity {
    @Lob
    @Column(name = "DESCRIPTION")
    protected String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    protected Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}