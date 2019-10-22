package ru.bytebratsk.bytesvc.web.screens.currencyrate;

import com.haulmont.cuba.gui.screen.*;
import ru.bytebratsk.bytesvc.entity.CurrencyRate;

@UiController("bytesvc_CurrencyRate.browse")
@UiDescriptor("currency-rate-browse.xml")
@LookupComponent("currencyRatesTable")
@LoadDataBeforeShow
public class CurrencyRateBrowse extends StandardLookup<CurrencyRate> {
}