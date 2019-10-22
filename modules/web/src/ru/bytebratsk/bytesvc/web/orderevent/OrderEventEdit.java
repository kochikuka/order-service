package ru.bytebratsk.bytesvc.web.orderevent;

import com.haulmont.cuba.gui.screen.*;
import ru.bytebratsk.bytesvc.entity.OrderEvent;

@UiController("bytesvc_OrderEvent.edit")
@UiDescriptor("order-event-edit.xml")
@EditedEntityContainer("orderEventDc")
@LoadDataBeforeShow
public class OrderEventEdit extends StandardEditor<OrderEvent> {
}