package com.microsoft.bingads.v12.api.test.entities.ad_extension.app.write;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runners.Parameterized;

import com.microsoft.bingads.internal.functionalinterfaces.BiConsumer;
import com.microsoft.bingads.v12.api.test.entities.ad_extension.app.BulkAppAdExtensionTest;
import com.microsoft.bingads.v12.bulk.entities.BulkAppAdExtension;

public class BulkAppAdExtensionWriteFromRowValuesDisplayTextTest extends BulkAppAdExtensionTest {

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
        this.testWriteProperty("Text", this.datum, this.expectedResult, new BiConsumer<BulkAppAdExtension, String>() {
            @Override
            public void accept(BulkAppAdExtension c, String v) {
                c.getAppAdExtension().setDisplayText(v);
            }
        });
    }
}
