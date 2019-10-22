package ru.bytebratsk.bytesvc.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.chile.core.annotations.NumberFormat;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@NamePattern("%s|id")
@Table(name = "BYTESVC_CURRENCY_RATE")
@Entity(name = "bytesvc_CurrencyRate")
public class CurrencyRate extends StandardEntity {
    private static final long serialVersionUID = -2212321057915793630L;

    @Temporal(TemporalType.DATE)
    @Column(name = "RATE_DATE")
    protected Date rate_date;

    @NumberFormat(pattern = "#,##0.0000")
    @Column(name = "USDOLLAR_VALUE")
    protected BigDecimal usdollar_value;

    @NumberFormat(pattern = "#,##0.0000")
    @Column(name = "EURO_VALUE")
    protected BigDecimal euro_value;

    public void setRate_date(Date rate_date) {
        this.rate_date = rate_date;
    }

    public void setUsdollar_value(BigDecimal usdollar_value) {
        this.usdollar_value = usdollar_value;
    }

    public void setEuro_value(BigDecimal euro_value) {
        this.euro_value = euro_value;
    }

    public Date getRate_date() {
        return rate_date;
    }

    public BigDecimal getUsdollar_value() {
        return usdollar_value;
    }

    public BigDecimal getEuro_value() {
        return euro_value;
    }
}