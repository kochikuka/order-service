alter table BYTESVC_ORDER alter column DEVICE_TYPE_ID rename to DEVICE_TYPE_ID__U39289 ^
drop index IDX_BYTESVC_ORDER_ON_DEVICE_TYPE ;
alter table BYTESVC_ORDER drop constraint FK_BYTESVC_ORDER_ON_DEVICE_TYPE ;
alter table BYTESVC_ORDER add column DEVICE_TYPE varchar(255) ;
