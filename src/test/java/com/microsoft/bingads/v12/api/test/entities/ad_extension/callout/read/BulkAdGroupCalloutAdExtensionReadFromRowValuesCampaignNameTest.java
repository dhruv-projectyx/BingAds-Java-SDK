package com.microsoft.bingads.v12.api.test.entities.ad_extension.callout.read;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runners.Parameterized;

import com.microsoft.bingads.internal.functionalinterfaces.Function;
import com.microsoft.bingads.v12.api.test.entities.ad_extension.callout.BulkAdGroupCalloutAdExtensionTest;
import com.microsoft.bingads.v12.bulk.entities.BulkAdGroupCalloutAdExtension;

public class BulkAdGroupCalloutAdExtensionReadFromRowValuesCampaignNameTest extends BulkAdGroupCalloutAdExtensionTest {
    @Parameterized.Parameter(value = 1)
    public String expectedResult;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Campaign Name String", "Campaign Name String"},
                {"", ""},
                {null, null}
        });
    }

    @Test
    public void testRead() {
        this.testReadProperty("Campaign", this.datum, this.expectedResult, new Function<BulkAdGroupCalloutAdExtension, String>() {
            @Override
            public String apply(BulkAdGroupCalloutAdExtension c) {
                return c.getCampaignName();
            }
        });
    }
}
