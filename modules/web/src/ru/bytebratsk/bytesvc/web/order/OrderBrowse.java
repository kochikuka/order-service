package ru.bytebratsk.bytesvc.web.order;

import com.haulmont.cuba.core.app.DataService;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.reports.entity.Report;
import com.haulmont.reports.gui.ReportGuiManager;
import com.haulmont.reports.gui.actions.TablePrintFormAction;
import ru.bytebratsk.bytesvc.entity.Order;
import ru.bytebratsk.bytesvc.service.CurrentCurrencyRateService;

import javax.inject.Inject;
import java.util.HashMap;

import java.util.Map;

@UiController("bytesvc_Order.browse")
@UiDescriptor("order-browse.xml")
@LookupComponent("ordersTable")
@LoadDataBeforeShow
public class OrderBrowse extends StandardLookup<Order> {

    @Inject
    private Notifications notifications;

    @Inject
    private Button buttonRunReport;

    @Inject
    private GroupTable<Order> ordersTable;

    @Inject
    private CurrentCurrencyRateService rateService;

    @Subscribe
    private void onInit(InitEvent event) {
        TablePrintFormAction action = new TablePrintFormAction("run_report", ordersTable);
        ordersTable.addAction(action);
        buttonRunReport.setAction(action);
    }

    public void onButtonAcceptanceActClick() {
        runReportOrderAcceptanceAct();
    }

    public void onButtonOutbackActClick() {
        runReportOrderOutbackAct();
    }

    @Inject
    protected DataService dataService;

    protected ReportGuiManager reportGuiManager = AppBeans.get(ReportGuiManager.class);

    public void runReportOrderAcceptanceAct() {

        Map<String,Object> reportParams = new HashMap<>();
        reportParams.put("entity", ordersTable.getSingleSelected()); // "entity" в данном случае это алиас входного параметра Order в определении отчета

        LoadContext<Report> lContext = new LoadContext<>(Report.class);
        lContext.setQueryString("select r from report$Report r where r.code = 'OrderAcceptanceAct' ");
        /*
        notifications.create()
                .withCaption("Viewable Id of selection: " + ordersTable.getSingleSelected().getId())
                .show();
        */

        Report report = dataService.load(lContext);
        reportGuiManager.printReport(report, reportParams, "DEFAULT", "Accetance Act Report for selected Order");
    }


    public void runReportOrderOutbackAct() {

        Map<String,Object> reportParams = new HashMap<>();
        reportParams.put("entity", ordersTable.getSingleSelected()); // "entity" в данном случае это алиас входного параметра Order в определении отчета

        LoadContext<Report> lContext = new LoadContext<>(Report.class);
        lContext.setQueryString("select r from report$Report r where r.code = 'OrderOutbackAct' ");


        Report report = dataService.load(lContext);
        reportGuiManager.printReport(report, reportParams, "DEFAULT", "Outback Act Report for selected Order");
    }

}