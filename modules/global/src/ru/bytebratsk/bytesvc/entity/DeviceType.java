package ru.bytebratsk.bytesvc.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@NamePattern("%s %s|id,name")
@Table(name = "BYTESVC_DEVICE_TYPE")
@Entity(name = "bytesvc_DeviceType")
public class DeviceType extends StandardEntity {
    @NotNull(message = "{msg://Name_of_type_must_not_be_empty}")
    @Column(name = "NAME")
    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}