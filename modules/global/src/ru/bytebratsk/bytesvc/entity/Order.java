package ru.bytebratsk.bytesvc.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.chile.core.annotations.NumberFormat;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.Listeners;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import com.haulmont.cuba.security.entity.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@NamePattern("%s|id")
@Table(name = "BYTESVC_ORDER")
@Entity(name = "bytesvc_Order")
@Listeners("bytesvc_OrderEntityListener")
public class Order extends StandardEntity {

    @Column(name = "VIEWABLE_ID", unique = true)
    protected Long viewable_id;

    @Column(name = "STATUS")
    protected Integer status;

    @Temporal(TemporalType.DATE)
    @Column(name = "IN_DATE")
    protected Date in_date;

    @Column(name = "DEVICE_TYPE")
    protected String device_type;

    @Column(name = "VENDOR")
    protected String vendor;

    @Column(name = "MODEL")
    protected String model;

    @Column(name = "SERIAL_NUMBER")
    protected String serial_number;

    @Column(name = "IN_SET")
    protected String in_set;

    @Column(name = "MALFUNCTION")
    protected String malfunction;

    @Column(name = "ACCEPTANCE_PERSON", length = 100)
    protected String acceptance_person;

    @Column(name = "CUSTOMER_PERSON", length = 100)
    protected String customer_person;

    @Column(name = "CUSTOMER_ORGNAME")
    protected String customer_orgname;

    @Column(name = "CUSTOMER_PHONE")
    protected String customer_phone;

    @Column(name = "CUSTOMER_ADDRESS")
    protected String customer_address;

    @Column(name = "CUSTOMER_EMAIL")
    protected String customer_email;

    @Column(name = "REPAIR_TYPE")
    protected Integer repair_type;

    @NumberFormat(pattern = "#,##0.00")
    @Column(name = "AGREED_VALUE")
    protected BigDecimal agreed_value;

    @NumberFormat(pattern = "#,##0.00")
    @Column(name = "DIAG_VALUE")
    protected BigDecimal diag_value;

    @Lookup(type = LookupType.DROPDOWN, actions = {"lookup", "clear"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SERVICE_PERSON_ID")
    protected User service_person;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "READY_DATE")
    protected Date ready_date;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "NOTIFY_DATE")
    protected Date notify_date;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "OUT_DATE")
    protected Date out_date;

    @Lookup(type = LookupType.DROPDOWN, actions = {"lookup", "clear"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OUTBACK_PERSON_ID")
    protected User outback_person;

    @OneToMany(mappedBy = "order")
    protected List<OrderWork> work;

    @NumberFormat(pattern = "#,##0.00")
    @Column(name = "WORK_VALUE")
    protected BigDecimal work_value;

    @OneToMany(mappedBy = "order")
    protected List<OrderSpare> sparepart;

    @NumberFormat(pattern = "#,##0.00")
    @Column(name = "SPAREPART_VALUE")
    protected BigDecimal sparepart_value;

    @OrderBy("createTs DESC")
    @OneToMany(mappedBy = "order")
    protected List<OrderEvent> event;

    @Column(name = "BILL_ID")
    protected Integer bill_id;

    @Column(name = "CLAIM")
    protected String claim;

    @Lob
    @Column(name = "REMARK")
    protected String remark;

    @Lob
    @Column(name = "INTERNAL_REMARK")
    protected String internal_remark;

    public User getOutback_person() {
        return outback_person;
    }

    public void setOutback_person(User outback_person) {
        this.outback_person = outback_person;
    }

    public List<OrderEvent> getEvent() {
        return event;
    }

    public void setEvent(List<OrderEvent> event) {
        this.event = event;
    }

    public List<OrderSpare> getSparepart() {
        return sparepart;
    }

    public void setSparepart(List<OrderSpare> sparepart) {
        this.sparepart = sparepart;
    }

    public List<OrderWork> getWork() {
        return work;
    }

    public void setWork(List<OrderWork> work) {
        this.work = work;
    }

    public OrderRepairType getRepair_type() {
        return repair_type == null ? null : OrderRepairType.fromId(repair_type);
    }

    public void setRepair_type(OrderRepairType repair_type) {
        this.repair_type = repair_type == null ? null : repair_type.getId();
    }


    public User getService_person() {
        return service_person;
    }

    public void setService_person(User service_person) {
        this.service_person = service_person;
    }




    public void setCustomer_person(String customer_person) {
        this.customer_person = customer_person;
    }

    public String getCustomer_person() {
        return customer_person;
    }

    public void setCustomer_orgname(String customer_orgname) {
        this.customer_orgname = customer_orgname;
    }

    public String getCustomer_orgname() {
        return customer_orgname;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setAgreed_value(BigDecimal agreed_value) {
        this.agreed_value = agreed_value;
    }

    public BigDecimal getAgreed_value() {
        return agreed_value;
    }

    public void setDiag_value(BigDecimal diag_value) {
        this.diag_value = diag_value;
    }

    public BigDecimal getDiag_value() {
        return diag_value;
    }

    public void setReady_date(Date ready_date) {
        this.ready_date = ready_date;
    }

    public Date getReady_date() {
        return ready_date;
    }

    public void setNotify_date(Date notify_date) {
        this.notify_date = notify_date;
    }

    public Date getNotify_date() {
        return notify_date;
    }

    public void setOut_date(Date out_date) {
        this.out_date = out_date;
    }

    public Date getOut_date() {
        return out_date;
    }

    public void setWork_value(BigDecimal work_value) {
        this.work_value = work_value;
    }

    public BigDecimal getWork_value() {
        return work_value;
    }

    public void setSparepart_value(BigDecimal sparepart_value) {
        this.sparepart_value = sparepart_value;
    }

    public BigDecimal getSparepart_value() {
        return sparepart_value;
    }

    public void setBill_id(Integer bill_id) {
        this.bill_id = bill_id;
    }

    public Integer getBill_id() {
        return bill_id;
    }

    public void setClaim(String claim) {
        this.claim = claim;
    }

    public String getClaim() {
        return claim;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public void setInternal_remark(String internal_remark) {
        this.internal_remark = internal_remark;
    }

    public String getInternal_remark() {
        return internal_remark;
    }


    public void setViewable_id(Long viewable_id) {
        this.viewable_id = viewable_id;
    }

    public Long getViewable_id() {
        return viewable_id;
    }

    public void setStatus(OrderStatus status) {
        this.status = status == null ? null : status.getId();
    }

    public OrderStatus getStatus() {
        return status == null ? null : OrderStatus.fromId(status);
    }

    public void setIn_date(Date in_date) {
        this.in_date = in_date;
    }

    public Date getIn_date() {
        return in_date;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getVendor() {
        return vendor;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setIn_set(String in_set) {
        this.in_set = in_set;
    }

    public String getIn_set() {
        return in_set;
    }

    public void setMalfunction(String malfunction) {
        this.malfunction = malfunction;
    }

    public String getMalfunction() {
        return malfunction;
    }

    public void setAcceptance_person(String acceptance_person) {
        this.acceptance_person = acceptance_person;
    }

    public String getAcceptance_person() {
        return acceptance_person;
    }


}
