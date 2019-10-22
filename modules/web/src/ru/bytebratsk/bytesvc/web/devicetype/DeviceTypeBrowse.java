package ru.bytebratsk.bytesvc.web.devicetype;

import com.haulmont.cuba.gui.screen.*;
import ru.bytebratsk.bytesvc.entity.DeviceType;

@UiController("bytesvc_DeviceType.browse")
@UiDescriptor("device-type-browse.xml")
@LookupComponent("deviceTypesTable")
@LoadDataBeforeShow
public class DeviceTypeBrowse extends StandardLookup<DeviceType> {
}