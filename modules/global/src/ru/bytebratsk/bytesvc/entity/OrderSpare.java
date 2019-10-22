package ru.bytebratsk.bytesvc.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.chile.core.annotations.NumberFormat;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@NamePattern("%s|id")
@Table(name = "BYTESVC_ORDER_SPARE")
@Entity(name = "bytesvc_OrderSpare")
public class OrderSpare extends StandardEntity {
    @Lob
    @Column(name = "SPARE_NAME")
    protected String spare_name;

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


    public String getSpare_name() {
        return spare_name;
    }

    public void setSpare_name(String spare_name) {
        this.spare_name = spare_name;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getQty() {
        return qty;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getTotal() {
        return total;
    }

}
