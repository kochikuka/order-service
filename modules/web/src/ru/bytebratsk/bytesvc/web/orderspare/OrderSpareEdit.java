package ru.bytebratsk.bytesvc.web.orderspare;

import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.cuba.gui.screen.*;
import ru.bytebratsk.bytesvc.entity.OrderSpare;

import java.math.BigDecimal;

@UiController("bytesvc_OrderSpare.edit")
@UiDescriptor("order-spare-edit.xml")
@EditedEntityContainer("orderSpareDc")
@LoadDataBeforeShow
public class OrderSpareEdit extends StandardEditor<OrderSpare> {

    @Subscribe
    protected void onInitEntity(InitEntityEvent<OrderSpare> event) {
        event.getEntity().setQty(0);
        event.getEntity().setPrice(BigDecimal.ZERO);
        event.getEntity().setTotal(BigDecimal.ZERO);
    }

    @Subscribe("qtyField")
    protected void onQtyFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        // Вычислить сумму
        if (event.getValue() != null)
            getEditedEntity().setTotal(getEditedEntity().getPrice().multiply(BigDecimal.valueOf(event.getValue())));
    }

    @Subscribe("priceField")
    protected void onPriceFieldValueChange(HasValue.ValueChangeEvent<BigDecimal> event) {
        // Вычислить сумму
        if (event.getValue() != null)
            getEditedEntity().setTotal(BigDecimal.valueOf(getEditedEntity().getQty()).multiply(event.getValue()));
    }

}
