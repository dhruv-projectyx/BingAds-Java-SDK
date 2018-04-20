
package com.microsoft.bingads.v12.campaignmanagement;

import java.util.Collection;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class Adapter5
    extends XmlAdapter<String, Collection<MediaEnabledEntityFilter>>
{


    public Collection<MediaEnabledEntityFilter> unmarshal(String value) {
        return (com.microsoft.bingads.v12.campaignmanagement.MediaEnabledEntityFilterConverter.convertToList(value));
    }

    public String marshal(Collection<MediaEnabledEntityFilter> value) {
        return (com.microsoft.bingads.v12.campaignmanagement.MediaEnabledEntityFilterConverter.convertToString(value));
    }

}
