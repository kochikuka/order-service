package ru.bytebratsk.bytesvc.core;

import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.core.listener.BeforeInsertEntityListener;
import com.haulmont.cuba.core.listener.BeforeUpdateEntityListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.bytebratsk.bytesvc.entity.Order;
import ru.bytebratsk.bytesvc.entity.OrderEvent;
import ru.bytebratsk.bytesvc.service.CurrentUserFullNameService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Component(OrderEntityListener.NAME)
public class OrderEntityListener implements
        BeforeInsertEntityListener<Order>,
        BeforeUpdateEntityListener<Order> {
    public static final String NAME = "bytesvc_OrderEntityListener";

    @Inject
    private Persistence persistence;

    @Inject
    private CurrentUserFullNameService currentUserService;

    @Inject
    private Messages messages;

    // create logger
    private Logger log = LoggerFactory.getLogger(OrderEntityListener.class);

    @Override
    public void onBeforeInsert(Order entity, EntityManager entityManager) {
        // Create new Event
        Metadata metadata = AppBeans.get(Metadata.class);
        OrderEvent orderEvent = metadata.create(OrderEvent.class);
        // register acceptance of device with this new event
        orderEvent.setOrder(entity);
        orderEvent.setDescription("Устройство принято в ремонт");
        // add new event to order collection
        if (entity.getEvent() != null) {
            entity.getEvent().add(orderEvent);
        } else {
            List<OrderEvent> orderEventCollection = new ArrayList<>();
            orderEventCollection.add(orderEvent);
            entity.setEvent(orderEventCollection);
        }
        entityManager.persist(orderEvent);
    }

    @Override
    public void onBeforeUpdate(Order entity, EntityManager entityManager) {

        // output message with DEBUG level
        log.debug("OrderEntityListener-onBeforeUpdate-DirtyFields:" +
                persistence.getTools().getDirtyFields(entity));

        // If the service person attribute has been changed
        if (persistence.getTools().getDirtyFields(entity).contains("service_person")) {
            // Create new Event
            Metadata metadata = AppBeans.get(Metadata.class);
            OrderEvent orderEvent = metadata.create(OrderEvent.class);

            if (orderEvent != null) {
                // register change of service person with this event
                orderEvent.setOrder(entity);
                if (entity.getService_person() != null) {
                    orderEvent.setDescription("Назначен инженер: " + entity.getService_person().getName());
                } else {
                    orderEvent.setDescription("Инженер снят с заказа");
                }
                // add new event to order collection
                if (entity.getEvent() != null) {
                    entity.getEvent().add(orderEvent);
                } else {
                    List<OrderEvent> orderEventCollection = new ArrayList<>();
                    orderEventCollection.add(orderEvent);
                    entity.setEvent(orderEventCollection);
                }
                entityManager.persist(orderEvent);
            }

        }
        // If the status attribute has been changed
        if (persistence.getTools().getDirtyFields(entity).contains("status")) {
            // Create new Event
            Metadata metadata = AppBeans.get(Metadata.class);
            OrderEvent orderEvent = metadata.create(OrderEvent.class);

            if (orderEvent != null) {
                // register change of status with this event
                orderEvent.setOrder(entity);
                if (entity.getStatus() != null) {
                    orderEvent.setDescription(currentUserService.getCurrentUserFullName() +
                            " изменил статус заказа на: "  +
                            messages.getMessage(entity.getStatus()));
                }
                // add new event to order collection
                if (entity.getEvent() != null) {
                    entity.getEvent().add(orderEvent);
                } else {
                    List<OrderEvent> orderEventCollection = new ArrayList<>();
                    orderEventCollection.add(orderEvent);
                    entity.setEvent(orderEventCollection);
                }
                entityManager.persist(orderEvent);
            }

        }

    }
}