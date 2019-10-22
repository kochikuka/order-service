package ru.bytebratsk.bytesvc.service;

import ru.bytebratsk.bytesvc.entity.Order;

public interface OrderActGenerateService {
    String NAME = "bytesvc_OrderActGenerateService";

    String generateAcceptanceAct(Order order);
    String generateOutbackAct(Order order);

}