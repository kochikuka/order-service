package ru.bytebratsk.bytesvc.web.screens;

import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;
import ru.bytebratsk.bytesvc.service.CurrentCurrencyRateService;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Date;


@UiController("bytesvc_CurrencyScreen")
@UiDescriptor("currency-screen.xml")
public class CurrencyScreen extends Screen {

    @Inject
    private CurrentCurrencyRateService currencyRateService;

    @Inject
    private Label dynamicLabelDate;

    @Inject
    private Label dynamicLabelUSRate;

    @Inject
    private Label dynamicLabelEuroRate;


    @Subscribe
    protected void onInit(InitEvent event) {
        dynamicLabelDate.setValue(new Date());
        BigDecimal usdollarRate = currencyRateService.getUsdollarRate();
        if (usdollarRate != null) {
            dynamicLabelUSRate.setValue(usdollarRate + " Ruble");
        } else {
            dynamicLabelUSRate.setValue("Not available");
        }
        BigDecimal euroRate = currencyRateService.getEuroRate();
        if (euroRate != null) {
            dynamicLabelEuroRate.setValue(euroRate + " Ruble");
        } else {
            dynamicLabelEuroRate.setValue("Not available");
        }
    }
}