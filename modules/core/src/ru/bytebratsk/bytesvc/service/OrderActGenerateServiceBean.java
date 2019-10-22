package ru.bytebratsk.bytesvc.service;

import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.security.global.UserSession;
import org.springframework.stereotype.Service;
import ru.bytebratsk.bytesvc.entity.Order;


@Service(OrderActGenerateService.NAME)
public class OrderActGenerateServiceBean implements OrderActGenerateService {

    @Override
    public String generateAcceptanceAct(Order order) {
        String localCustomer_orgname;
        String localCustomer_address;
        String localIn_set;

        if (order.getCustomer_orgname() == null) {
            localCustomer_orgname = "";
        } else {
            localCustomer_orgname = order.getCustomer_orgname();
        }

        if (order.getCustomer_address() == null) {
            localCustomer_address = "Не указан";
        } else {
            localCustomer_address = order.getCustomer_address();
        }

        if (order.getIn_set() == null) {
            localIn_set = "";
        } else {
            localIn_set = order.getIn_set();
        }

        return "<html><meta charset=utf-8><head title=\'Квитанция о приеме\'></head><body><p><span style=\"font-family: Tahoma;\"><span style=\"font-size: small;\">Сервисный центр ООО \"БАЙТ\", 665708, РФ, Иркутская обл., г.Братск, ул. Кирова, 10<br>" +
                "Телефон (3953) 41-38-34, 41-11-21, e-mail: support@bytebratsk.ru<br>" +
                "Прием/выдача оборудования понедельник-пятница: с 10:00 до 19:00, перерыв 14:00 до 15:00, суббота: с 10:00 до 17:00 без перерыва</span></span></p>" +
                "<p></p><p><span style=\"font-family: Tahoma;\"><span style=\"font-size: small;\"><strong>Квитанция о приеме в ремонт/диагностику № " +
                order.getViewable_id() + " от " + String.format("%1$td.%1$tm.%1$tY", order.getIn_date()) + " </strong></span></span></p>" +
                "<table border=\"1\" cellspacing=\"0\" cellpadding=\"0\" width=\"652\">" +
                "<tbody>" +
                "<tr>" +
                "<td><span style=\"font-family: Tahoma;\"><span style=\"font-size: small;\"><strong>Заказчик</strong></span></span></td>" +
                "<td><span style=\"font-family: Tahoma;\"><span style=\"font-size: small;\">" + order.getCustomer_person() + "<br>" +
                localCustomer_orgname + " Адрес: " + localCustomer_address + " Телефон: " + order.getCustomer_phone() +
                " email: " + order.getCustomer_email() + "</span></span></td>" +
                "</tr>" +
                "<tr>" +
                "<td><span style=\"font-family: Tahoma;\"><span style=\"font-size: small;\"><strong>Устройство</strong></span></span></td>" +
                "<td><span style=\"font-family: Tahoma;\"><span style=\"font-size: small;\">" + order.getDevice_type() + " " +
                order.getVendor() + " " + order.getModel() + "<br>" +
                "Серийный номер: " + order.getSerial_number() + "</span></span></td>" +
                "</tr>" +
                "<tr>" +
                "<td><span style=\"font-family: Tahoma;\"><span style=\"font-size: small;\"><strong>В комплекте</strong></span></span></td>" +
                "<td><span style=\"font-family: Tahoma;\"><span style=\"font-size: small;\">" + localIn_set + "</span></span></td>" +
                "</tr>" +
                "<tr>" +
                "<td><span style=\"font-family: Tahoma;\"><span style=\"font-size: small;\"><strong>Особые замечания (описание неисправности со слов заказчика)</strong></span></span></td>" +
                "<td><span style=\"font-family: Tahoma;\"><span style=\"font-size: small;\">" + order.getMalfunction() + "</span></span></td>" +
                "</tr>" +
                "<tr>" +
                "<td><span style=\"font-family: Tahoma;\"><span style=\"font-size: small;\"><strong>Тип ремонта:<br><br>Диагностика:</strong></span></span></td>" +
                "<td><span style=\"font-family: Tahoma;\"><span style=\"font-size: small;\">" + order.getRepair_type() + " Максимальный срок диагностики устройства составляет 20 дней.<br><br>" +
                order.getDiag_value() + "</span></span></td>" +
                "</tr>" +
                "</tbody>" +
                "</table>" +
                "<p><span style=\"font-family: Tahoma;\"><span style=\"font-size: small;\">Внимание!!! Устройство, с согласия клиента, принято без разборки и проверки неисправностей, без проверки внутренних повреждений. Заказчик согласен, что " +
                "все неисправности и внутренние повреждения, которые могут быть обнаружены при диагностике, возникли до приема устройства по данной квитанции. " +
                "Данная квитанция является основанием для выдачи устройства заказчику. В случае утери квитанции ООО \"БАЙТ\" не несет ответственность за сохранность " +
                "устройства. В этом случае выдача устройства производится при предъявлении паспорта лица сдавшего устройство. " +
                "По истечении 2-х месяцев с даты завершения работ ООО \"БАЙТ\" не несет ответственность за сохранность устройства. " +
                "Заказчик согласен на обработку персональных данных, указанных в данной квитанции, и несет ответственность за достоверность предоставленной " +
                "информации.<br>" +
                "С комплектацией, описанием неисправностей и повреждений, условиями хранения устройства ознакомлен и согласен." +
                "</span></span></p>" +
                "<p></p>" +
                "<p><span style=\"font-family: Tahoma;\"><span style=\"font-size: small;\">Устройство сдал ______________________________ " + order.getCustomer_person() + "</span></span></p>" +
                "<p><span style=\"font-family: Tahoma;\"><span style=\"font-size: small;\">Устройство принял представитель сервисного центра ООО \"БАЙТ\" ___________________________ " + order.getAcceptance_person() + "</span></span></p>" +
                "</body></html>";

    }


    @Override
    public String generateOutbackAct(Order order) {
        String localCustomer_orgname;
        String localCustomer_address;
        String localIn_set;

        UserSession us = AppBeans.get(UserSessionSource.class).getUserSession();
        String  userName = us.getCurrentOrSubstitutedUser().getName();


        if (order.getCustomer_orgname() == null) {
            localCustomer_orgname = "";
        } else {
            localCustomer_orgname = order.getCustomer_orgname();
        }

        if (order.getCustomer_address() == null) {
            localCustomer_address = "Не указан";
        } else {
            localCustomer_address = order.getCustomer_address();
        }

        if (order.getIn_set() == null) {
            localIn_set = "";
        } else {
            localIn_set = order.getIn_set();
        }

        return "<html><meta charset=utf-8><head title=\'АКТ о передаче устройства заказчику\'></head><body>" +
                "<table width=\"800\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "<tbody>\n" +
                "<tr><td>\n" +
                "<p><span style=\"font-family: Tahoma;\"><span style=\"font-size: small;\">Сервисный центр ООО \"БАЙТ\", 665708, РФ, Иркутская обл., г.Братск, ул. Кирова, 10<br>Телефон (3953) 41-38-34, 41-11-21, e-mail: support@bytebratsk.ru<br>Прием/выдача оборудования понедельник-пятница: с 10:00 до 19:00, перерыв 14:00 до 15:00, суббота: с 10:00 до 17:00 без перерыва</span></span>\n" +
                "</p>\n" +
                "<p></p>\n" +
                "<p><span style=\"font-family: Tahoma;\"><span style=\"font-size: small;\"><strong>АКТ о передаче устройства заказчику № " +
                order.getViewable_id() + " от " + String.format("%1$td.%1$tm.%1$tY", order.getOut_date()) + " </strong></span></span></p>\n" +
                "<table border=\"1\" cellspacing=\"0\" cellpadding=\"3\" width=\"800\" bordercolor=\"#ffffff\">\n" +
                "<tbody>\n" +
                "<tr\n>" +
                "<td width=\"150\"><span style=\"font-family: Tahoma;\"><span style=\"font-size: small;\"><strong>Заказчик</strong></span></span></td>\n" +
                "<td><span style=\"font-family: Tahoma;\"><span style=\"font-size: small;\">" + order.getCustomer_person() + "<br>" +
                localCustomer_orgname + " Адрес: " + localCustomer_address + " Телефон: " + order.getCustomer_phone() +
                " email: " + order.getCustomer_email() + "</span></span></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td width=\"150\"><span style=\"font-family: Tahoma;\"><span style=\"font-size: small;\"><strong>Устройство</strong></span></span></td>\n" +
                "<td><span style=\"font-family: Tahoma;\"><span style=\"font-size: small;\">" + order.getDevice_type() + " " +
                order.getVendor() + " " + order.getModel() + "<br>" +
                "Серийный номер: " + order.getSerial_number() + "</span></span></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td width=\"150\"><span style=\"font-family: Tahoma;\"><span style=\"font-size: small;\"><strong>В комплекте</strong></span></span></td>\n" +
                "<td><span style=\"font-family: Tahoma;\"><span style=\"font-size: small;\">" + localIn_set + "</span></span></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td width=\"150\"><span style=\"font-family: Tahoma;\"><span style=\"font-size: small;\"><strong>Особые замечания (описание неисправности со слов заказчика)</strong></span></span></td>\n" +
                "<td><span style=\"font-family: Tahoma;\"><span style=\"font-size: small;\">" + order.getMalfunction() + "</span></span></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td width=\"150\"><span style=\"font-family: Tahoma;\"><span style=\"font-size: small;\"><strong>Тип ремонта:<br><br>Диагностика:</strong></span></span></td>\n" +
                "<td><span style=\"font-family: Tahoma;\"><span style=\"font-size: small;\">" + order.getRepair_type() + " Максимальный срок диагностики устройства составляет 20 дней.<br><br>" +
                order.getDiag_value() + "</span></span></td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "<p><span style=\"font-family: Tahoma;\"><span style=\"font-size: small;\">Сервисный центр ООО \"БАЙТ\" по поручению и с согласия Заказчика выполнил следующие работы:</span></span></p>\n" +
                "<table border=\"1\" cellspacing=\"0\" cellpadding=\"3\" width=\"800\" bordercolor=\"#ffffff\">\n" +
                "<tbody>\n" +
                "\t<tr>\n" +
                "\t\t<td><span style=\"font-family: Tahoma;\"><span style=\"font-size: small;\">Диагностика неисправности устройства</span></span></td>\n" +
                "\t\t<td width=\"150\" align=\"right\"><span style=\"font-family: Tahoma;\"><span style=\"font-size: small;\">0.00 руб.</span></span></td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td><span style=\"font-family: Tahoma;\"><span style=\"font-size: small;\">Замена матрицы</span></span></td>\n" +
                "\t\t<td width=\"150\" align=\"right\"><span style=\"font-family: Tahoma;\"><span style=\"font-size: small;\">1 947.00 руб.</span></span></td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td><span style=\"font-family: Tahoma;\"><span style=\"font-size: small;\"><strong>Итого за работы:</strong></span></span></td>\n" +
                "\t\t<td width=\"150\" align=\"right\"><span style=\"font-family: Tahoma;\"><span style=\"font-size: small;\">1 947.00 руб.</span></span></td>\n" +
                "\t</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "<p><span style=\"font-family: Tahoma;\"><span style=\"font-size: small;\">Сервисный центр ООО \"БАЙТ\" по поручению и с согласия Заказчика выполнил установил следующие детали/запчасти:</span></span></p>\n" +
                "<table border=\"1\" cellspacing=\"0\" cellpadding=\"3\" width=\"800\" bordercolor=\"#ffffff\">\n" +
                "<tbody>\n" +
                "\t<tr>\n" +
                "\t\t<td><span style=\"font-family: Tahoma;\"><span style=\"font-size: small;\">Матрица ChiMei 8943775 ekjfjldu </span></span></td>\n" +
                "\t\t<td width=\"150\" align=\"right\"><span style=\"font-family: Tahoma;\"><span style=\"font-size: small;\">1900.00 руб.</span></span></td>\n" +
                "\t</tr>\n" +
                "\t<tr>\n" +
                "\t\t<td><span style=\"font-family: Tahoma;\"><span style=\"font-size: small;\"><strong>Итого за детали/запчасти:</strong></span></span></td>\n" +
                "\t\t<td width=\"150\" align=\"right\"><span style=\"font-family: Tahoma;\"><span style=\"font-size: small;\">1 947.00 руб.</span></span></td>\n" +
                "\t</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "<p><span style=\"font-family: Tahoma;\"><span style=\"font-size: small;\">Сервисный инженер: " + userName + "<br>" +
                "Дата готовности устройства: " + String.format("%1$td.%1$tm.%1$tY", order.getReady_date()) + "</span></span></p>\n" +
                "<table border=\"1\" bordercolor=\"#ffffff\" cellspacing=\"0\" cellpadding=\"3\" width=\"800\" >\n" +
                "<tbody>\n" +
                "\t<tr>\n" +
                "\t\t<td><span style=\"font-family: Tahoma;\"><span style=\"font-size: small;\"><strong>Итого к оплате:</strong></span></span></td>\n" +
                "\t\t<td width=\"150\" align=\"right\"><span style=\"font-family: Tahoma;\"><span style=\"font-size: small;\"><strong>3 847.00 руб.</strong></span></span></td>\n" +
                "\t</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "\n" +
                "<p><span style=\"font-family: Tahoma;\"><span style=\"font-size: small;\">Представитель сервисного центра передал заказчику устройство, в комплекте соответствующем принятому по квитанции о\n" +
                "приеме в ремонт/диагностику № " + order.getViewable_id() + " от " + String.format("%1$td.%1$tm.%1$tY", order.getOut_date()) + ". Заказчик к качеству выполненных работ и комплектности претензий не\n" +
                "имеет. Заказчик к срокам выполнения работ претензий не имеет.\n" +
                "<br>Устройство выдано по предъявлению квитанции о приеме 034062 от 5.04.2017.</span></span></p>\n" +
                "<p></p>\n" +
                "<p><span style=\"font-family: Tahoma;\"><span style=\"font-size: small;\">Устройство выдал представитель сервисного центра ООО \"БАЙТ\" ___________________________ " + order.getAcceptance_person() + "</span></span></p>\n" +
                "<p><span style=\"font-family: Tahoma;\"><span style=\"font-size: small;\">Устройство принял ______________________________ " + order.getCustomer_person() + "</span></span></p>\n" +
                "\n" +
                "</td></tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "\n" +
                "</body></html>";
    }

}