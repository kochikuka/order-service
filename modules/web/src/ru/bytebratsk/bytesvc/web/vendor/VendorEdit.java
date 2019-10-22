package ru.bytebratsk.bytesvc.web.vendor;

import com.haulmont.cuba.gui.screen.*;
import ru.bytebratsk.bytesvc.entity.Vendor;

@UiController("bytesvc_Vendor.edit")
@UiDescriptor("vendor-edit.xml")
@EditedEntityContainer("vendorDc")
@LoadDataBeforeShow
public class VendorEdit extends StandardEditor<Vendor> {
}