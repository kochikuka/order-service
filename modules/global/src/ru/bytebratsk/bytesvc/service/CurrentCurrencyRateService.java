package ru.bytebratsk.bytesvc.service;

import java.math.BigDecimal;

public interface CurrentCurrencyRateService {
    String NAME = "bytesvc_CurrentCurrencyRateService";

    BigDecimal getUsdollarRate();

    BigDecimal getEuroRate();

    void getAndSaveRate();
}