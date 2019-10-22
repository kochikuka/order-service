package ru.bytebratsk.bytesvc.web.devicetype;

import com.haulmont.cuba.gui.screen.*;
import ru.bytebratsk.bytesvc.entity.DeviceType;

@UiController("bytesvc_DeviceType.edit")
@UiDescriptor("device-type-edit.xml")
@EditedEntityContainer("deviceTypeDc")
@LoadDataBeforeShow
public class DeviceTypeEdit extends StandardEditor<DeviceType> {
}