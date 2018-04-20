package com.microsoft.bingads.v12.api.test.entities.ads.appInstall.write;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.microsoft.bingads.internal.functionalinterfaces.BiConsumer;
import com.microsoft.bingads.v12.api.test.entities.ads.appInstall.BulkAppInstallAdTest;
import com.microsoft.bingads.v12.bulk.entities.BulkAppInstallAd;

@RunWith(Parameterized.class)
public class BulkAppInstallAdWriteToRowValuesTextTest extends BulkAppInstallAdTest {

    @Parameter(value = 1)
    public String propertyValue;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {"Test ad text", "Test ad text"},
            {"", ""},
            {null, null},});
    }

    @Test
    public void testWrite() {
        this.<String>testWriteProperty("Text", this.datum, this.propertyValue, new BiConsumer<BulkAppInstallAd, String>() {
            @Override
            public void accept(BulkAppInstallAd c, String v) {
                c.getAppInstallAd().setText(v);
            }
        });
    }
}
