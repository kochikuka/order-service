package ru.bytebratsk.bytesvc.web.vendor;

import com.haulmont.cuba.gui.screen.*;
import ru.bytebratsk.bytesvc.entity.Vendor;

@UiController("bytesvc_Vendor.browse")
@UiDescriptor("vendor-browse.xml")
@LookupComponent("vendorsTable")
@LoadDataBeforeShow
public class VendorBrowse extends StandardLookup<Vendor> {
}