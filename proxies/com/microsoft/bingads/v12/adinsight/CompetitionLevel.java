
package com.microsoft.bingads.v12.adinsight;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CompetitionLevel.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CompetitionLevel">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Low"/>
 *     &lt;enumeration value="Medium"/>
 *     &lt;enumeration value="High"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CompetitionLevel", namespace = "http://schemas.datacontract.org/2004/07/Microsoft.BingAds.Advertiser.AdInsight.Api.DataContract.V12.Entity.Common")
@XmlEnum
public enum CompetitionLevel {

    @XmlEnumValue("Low")
    LOW("Low"),
    @XmlEnumValue("Medium")
    MEDIUM("Medium"),
    @XmlEnumValue("High")
    HIGH("High");
    private final String value;

    CompetitionLevel(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CompetitionLevel fromValue(String v) {
        for (CompetitionLevel c: CompetitionLevel.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
