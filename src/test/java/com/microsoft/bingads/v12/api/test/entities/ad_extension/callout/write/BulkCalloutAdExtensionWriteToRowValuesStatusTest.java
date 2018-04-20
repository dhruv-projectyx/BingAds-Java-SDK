package com.microsoft.bingads.v12.api.test.entities.ad_extension.callout.write;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.microsoft.bingads.internal.functionalinterfaces.BiConsumer;
import com.microsoft.bingads.v12.api.test.entities.ad_extension.callout.BulkCalloutAdExtensionTest;
import com.microsoft.bingads.v12.bulk.entities.BulkCalloutAdExtension;
import com.microsoft.bingads.v12.campaignmanagement.AdExtensionStatus;

public class BulkCalloutAdExtensionWriteToRowValuesStatusTest extends BulkCalloutAdExtensionTest {

    @Parameter(value = 1)
    public AdExtensionStatus expectedResult;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Active", AdExtensionStatus.ACTIVE},
                {"Deleted", AdExtensionStatus.DELETED},
                {null, null}
        });
    }

    @Test
    public void testWrite() {
        this.testWriteProperty("Status", this.datum, this.expectedResult, new BiConsumer<BulkCalloutAdExtension, AdExtensionStatus>() {
            @Override
            public void accept(BulkCalloutAdExtension c, AdExtensionStatus v) {
                c.getCalloutAdExtension().setStatus(v);
            }
        });
    }
}
