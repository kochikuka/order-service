package ru.bytebratsk.bytesvc.web.order;

import com.haulmont.cuba.core.app.UniqueNumbersService;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.LookupField;
import com.haulmont.cuba.gui.components.SuggestionField;
import com.haulmont.cuba.gui.export.ByteArrayDataProvider;
import com.haulmont.cuba.gui.export.ExportDisplay;
import com.haulmont.cuba.gui.export.ExportFormat;
import com.haulmont.cuba.gui.model.CollectionChangeType;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.security.global.UserSession;
import com.haulmont.reports.gui.actions.EditorPrintFormAction;
import org.apache.commons.lang3.StringUtils;
import ru.bytebratsk.bytesvc.entity.*;
import ru.bytebratsk.bytesvc.service.OrderActGenerateService;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@UiController("bytesvc_Order.edit")
@UiDescriptor("order-edit.xml")
@EditedEntityContainer("orderDc")
@LoadDataBeforeShow
public class OrderEdit extends StandardEditor<Order> {

    @Inject
    private ExportDisplay exportDisplay;

    @Inject
    private OrderActGenerateService orderActGenerateService;

    @Inject
    private UniqueNumbersService unService;

    @Inject
    private Button buttonReportAccept;

    @Inject
    private Notifications notifications;

    @Subscribe
    private void onBeforeShow(BeforeShowEvent event) {
        buttonReportAccept.setAction(new EditorPrintFormAction("report", this, null));
    }

    @Subscribe
    protected void onInitEntity(InitEntityEvent<Order> event) {
        event.getEntity().setStatus(OrderStatus.NEW);
        event.getEntity().setRepair_type(OrderRepairType.UNKNOWN);
        event.getEntity().setViewable_id(unService.getNextNumber("OrderSequence"));
        event.getEntity().setIn_date(new Date());
        event.getEntity().setAgreed_value(BigDecimal.ZERO);
        event.getEntity().setDiag_value(BigDecimal.ZERO);
        event.getEntity().setWork_value(BigDecimal.ZERO);
        event.getEntity().setSparepart_value(BigDecimal.ZERO);

        UserSession us = AppBeans.get(UserSessionSource.class).getUserSession();
        String uname = us.getCurrentOrSubstitutedUser().getName();

        event.getEntity().setAcceptance_person(uname);
    }

    @Inject
    private LookupField<OrderStatus> statusField;

    @Subscribe
    protected void onAfterShow(AfterShowEvent event) {
        statusField.addValueChangeListener(e -> {
            /* notifications.create()
                    .withCaption("Before: " + e.getPrevValue() + ". After: " + e.getValue())
                    .show(); */
            if (e.getValue() != null) //Тут внезапно может оказаться Null, если разрешен пустой статус в списке
                switch (e.getValue()) {
                    case READY:
                        // При смене статуса заказа на Ready:
                        // - установить дату готовности заказа
                        getEditedEntity().setReady_date(new Date());
                        break;
                    case COMPLETED:
                        // При смене статуса заказа на Completed:
                        // - установить дату выдачи заказа
                        getEditedEntity().setOut_date(new Date());
                        // установить персону выдавшего
                        UserSession us = AppBeans.get(UserSessionSource.class).getUserSession();
                        getEditedEntity().setOutback_person(us.getCurrentOrSubstitutedUser());
                        break;
                }
        });
    }

    @Inject
    private CollectionContainer<OrderWork> orderWorksDc;

    @Subscribe(id = "orderWorksDc", target = Target.DATA_CONTAINER)
    protected void onOrderWorkDcCollectionChange(CollectionContainer.CollectionChangeEvent<OrderWork> event) {
        if (event.getChangeType() != CollectionChangeType.REFRESH) {
            calculateWorkTotal();
        }
    }

    private void calculateWorkTotal() {
        BigDecimal total = BigDecimal.ZERO;
        // подсчитать сумму за работы и вписать ее в стоимость работ по заказу
        for (OrderWork work : orderWorksDc.getItems()) {
            total = total.add(work.getTotal());
        }
        getEditedEntity().setWork_value(total);
    }

    @Inject
    private CollectionContainer<OrderSpare> orderSparesDc;

    @Subscribe(id = "orderSparesDc", target = Target.DATA_CONTAINER)
    protected void onOrderSpareDcCollectionChange(CollectionContainer.CollectionChangeEvent<OrderSpare> event) {
        if (event.getChangeType() != CollectionChangeType.REFRESH) {
            calculateSpareTotal();
        }
    }

    private void calculateSpareTotal() {
        BigDecimal total = BigDecimal.ZERO;
        // подсчитать сумму за запчасти и вписать ее в стоимость запчастей по заказу
        for (OrderSpare spare : orderSparesDc.getItems()) {
            total = total.add(spare.getTotal());
        }
        getEditedEntity().setSparepart_value(total);
    }

    public void onButtonGenerateAcceptanceActClick() {
        byte[] bytes;

        bytes = orderActGenerateService.generateAcceptanceAct(getEditedEntity()).getBytes(StandardCharsets.UTF_8);
        exportDisplay.show(new ByteArrayDataProvider(bytes), "acceptance-act.html", ExportFormat.HTML);
    }

    public void onButtonGenerateOutbackActClick() {
        byte[] bytes;

        bytes = orderActGenerateService.generateOutbackAct(getEditedEntity()).getBytes(StandardCharsets.UTF_8);
        exportDisplay.show(new ByteArrayDataProvider(bytes), "outback-act.html", ExportFormat.HTML);
    }

    //Set suggestion Fields supporting code
    // for device type
    @Inject
    private SuggestionField<String> deviceTypeSuggestionField;
    @Inject
    private CollectionContainer<DeviceType> deviceTypesDc;
    @Inject
    private CollectionLoader<DeviceType> deviceTypesLoader;
    // for vendor
    @Inject
    private SuggestionField<String> vendorSuggestionField;
    @Inject
    private CollectionContainer<Vendor> vendorsDc;
    @Inject
    private CollectionLoader<Vendor> vendorsLoader;


    @Subscribe
    protected void onInit(InitEvent event) {
        //make list of device types from deviceTypesDc container for make suggestion by it's content
        deviceTypesLoader.load();
        List<String> deviceTypeNames = new ArrayList<>();
        for (DeviceType deviceType : deviceTypesDc.getItems()) {
            String name = deviceType.getName();
            deviceTypeNames.add(name);
        }
        deviceTypeSuggestionField.setSearchExecutor((searchString, searchParams) ->
                deviceTypeNames.stream()
                        .filter(device_type_name ->
                                StringUtils.containsIgnoreCase(device_type_name,searchString))
                        .collect(Collectors.toList()));

        // set action to push user-entered (which is not present in suggested list) value in suggestion field
        deviceTypeSuggestionField.setEnterActionHandler(currentSearchString -> {
            deviceTypeSuggestionField.setValue(currentSearchString);
        });

        //make list of vendors from vendorsDc container for make suggestion by it's content
        vendorsLoader.load();
        List<String> vendorNames = new ArrayList<>();
        for (Vendor vendors : vendorsDc.getItems()) {
            String name = vendors.getName();
            vendorNames.add(name);
        }
        vendorSuggestionField.setSearchExecutor((searchString, searchParams) ->
                vendorNames.stream()
                        .filter(vendor_name ->
                                StringUtils.containsIgnoreCase(vendor_name,searchString))
                        .collect(Collectors.toList()));

        // set action to push user-entered (which is not present in suggested list) value in suggestion field
        vendorSuggestionField.setEnterActionHandler(currentSearchString -> {
            vendorSuggestionField.setValue(currentSearchString);
        });

    }
}