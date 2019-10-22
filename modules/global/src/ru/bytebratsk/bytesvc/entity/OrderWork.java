package ru.bytebratsk.bytesvc.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.chile.core.annotations.NumberFormat;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@NamePattern("%s|id")
@Table(name = "BYTESVC_ORDER_WORK")
@Entity(name = "bytesvc_OrderWork")
public class OrderWork extends StandardEntity {
    @Lob
    @Column(name = "WORK_NAME")
    protected String work_name;

    @Column(name = "SERVICE_PERSON", length = 100)
    protected String service_person;

    @Column(name = "QTY")
    protected Integer qty;

    @NumberFormat(pattern = "#,##0.00")
    @Column(name = "PRICE")
    protected BigDecimal price;

    @NumberFormat(pattern = "#,##0.00")
    @Column(name = "TOTAL")
    protected BigDecimal total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    protected Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getWork_name() {
        return work_name;
    }

    public void setWork_name(String work_name) {
        this.work_name = work_name;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getService_person() {
        return service_person;
    }

    public void setService_person(String service_person) {
        this.service_person = service_person;
    }


}