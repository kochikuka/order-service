package ru.bytebratsk.bytesvc.web.orderwork;

import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.cuba.gui.screen.*;
import ru.bytebratsk.bytesvc.entity.OrderWork;
import ru.bytebratsk.bytesvc.service.CurrentUserFullNameService;

import javax.inject.Inject;
import java.math.BigDecimal;

@UiController("bytesvc_OrderWork.edit")
@UiDescriptor("order-work-edit.xml")
@EditedEntityContainer("orderWorkDc")
@LoadDataBeforeShow
public class OrderWorkEdit extends StandardEditor<OrderWork> {

    @Inject
    private CurrentUserFullNameService currentUserService;

    @Subscribe
    public void onInitEntity (InitEntityEvent<OrderWork> event){
        // fill service person name with full name of current user
        event.getEntity().setService_person(currentUserService.getCurrentUserFullName());
        // fill quantity with one by default
        event.getEntity().setQty(1);
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