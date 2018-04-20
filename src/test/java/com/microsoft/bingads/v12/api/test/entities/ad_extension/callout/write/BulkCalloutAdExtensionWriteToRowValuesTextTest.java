package com.microsoft.bingads.v12.api.test.entities.ad_extension.callout.write;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runners.Parameterized;

import com.microsoft.bingads.internal.functionalinterfaces.BiConsumer;
import com.microsoft.bingads.v12.api.test.entities.ad_extension.callout.BulkCalloutAdExtensionTest;
import com.microsoft.bingads.v12.bulk.entities.BulkCalloutAdExtension;

public class BulkCalloutAdExtensionWriteToRowValuesTextTest extends BulkCalloutAdExtensionTest {

    @Parameterized.Parameter(value = 1)
    public String expectedResult;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Display Text string", "Display Text string"},
                {null, null}
        });
    }

    @Test
    public void testWrite() {
        this.testWriteProperty("Callout Text", this.datum, this.expectedResult, new BiConsumer<BulkCalloutAdExtension, String>() {
            @Override
            public void accept(BulkCalloutAdExtension c, String v) {
                c.getCalloutAdExtension().setText(v);
            }
        });
    }
}
