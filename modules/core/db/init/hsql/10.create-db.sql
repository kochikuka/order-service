-- begin BYTESVC_ORDER
create table BYTESVC_ORDER (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    VIEWABLE_ID bigint,
    STATUS integer,
    IN_DATE date,
    DEVICE_TYPE varchar(255),
    VENDOR varchar(255),
    MODEL varchar(255),
    SERIAL_NUMBER varchar(255),
    IN_SET varchar(255),
    MALFUNCTION varchar(255),
    ACCEPTANCE_PERSON varchar(100),
    CUSTOMER_PERSON varchar(100),
    CUSTOMER_ORGNAME varchar(255),
    CUSTOMER_PHONE varchar(255),
    CUSTOMER_ADDRESS varchar(255),
    CUSTOMER_EMAIL varchar(255),
    REPAIR_TYPE integer,
    AGREED_VALUE decimal(19, 2),
    DIAG_VALUE decimal(19, 2),
    SERVICE_PERSON_ID varchar(36),
    READY_DATE timestamp,
    NOTIFY_DATE timestamp,
    OUT_DATE timestamp,
    OUTBACK_PERSON_ID varchar(36),
    WORK_VALUE decimal(19, 2),
    SPAREPART_VALUE decimal(19, 2),
    BILL_ID integer,
    CLAIM varchar(255),
    REMARK longvarchar,
    INTERNAL_REMARK longvarchar,
    --
    primary key (ID)
)^
-- end BYTESVC_ORDER
-- begin BYTESVC_ORDER_WORK
create table BYTESVC_ORDER_WORK (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    WORK_NAME longvarchar,
    SERVICE_PERSON varchar(100),
    QTY integer,
    PRICE decimal(19, 2),
    TOTAL decimal(19, 2),
    ORDER_ID varchar(36),
    --
    primary key (ID)
)^
-- end BYTESVC_ORDER_WORK
-- begin BYTESVC_ORDER_SPARE
create table BYTESVC_ORDER_SPARE (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    SPARE_NAME longvarchar,
    QTY integer,
    PRICE decimal(19, 2),
    TOTAL decimal(19, 2),
    ORDER_ID varchar(36),
    --
    primary key (ID)
)^
-- end BYTESVC_ORDER_SPARE
-- begin BYTESVC_ORDER_EVENT
create table BYTESVC_ORDER_EVENT (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    DESCRIPTION longvarchar,
    ORDER_ID varchar(36),
    --
    primary key (ID)
)^
-- end BYTESVC_ORDER_EVENT
-- begin BYTESVC_DEVICE_TYPE
create table BYTESVC_DEVICE_TYPE (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255),
    --
    primary key (ID)
)^
-- end BYTESVC_DEVICE_TYPE
-- begin BYTESVC_VENDOR
create table BYTESVC_VENDOR (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255),
    --
    primary key (ID)
)^
-- end BYTESVC_VENDOR
