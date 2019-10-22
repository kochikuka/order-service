create table BYTESVC_CURRENCY_RATE (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    RATE_DATE date,
    USDOLLAR_VALUE decimal(19, 4),
    EURO_VALUE decimal(19, 4),
    --
    primary key (ID)
);