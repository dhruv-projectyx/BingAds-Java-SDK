package com.microsoft.bingads.v12.api.test.entities.keyword.write;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.microsoft.bingads.internal.functionalinterfaces.BiConsumer;
import com.microsoft.bingads.v12.api.test.entities.keyword.BulkKeywordTest;
import com.microsoft.bingads.v12.bulk.entities.BulkKeyword;

@RunWith(Parameterized.class)
public class BulkKeywordWriteToRowValuesParam2Test extends BulkKeywordTest {

    @Parameter(value = 1)
    public String propertyValue;

    @Parameters
    public static Collection<Object[]> data() {
        // In this example, the parameter generator returns a List of
        // arrays.  Each array has two elements: { datum, expected }.
        // These data are hard-coded into the class, but they could be
        // generated or loaded in any way you like.
        return Arrays.asList(new Object[][]{
            {"Test Param 2", "Test Param 2"},
            {"delete_value", ""},
            {null, null}
        });
    }

    @Test
    public void testWrite() {
        this.<String>testWriteProperty("Param2", this.datum, this.propertyValue, new BiConsumer<BulkKeyword, String>() {
            @Override
            public void accept(BulkKeyword c, String v) {
                c.getKeyword().setParam2(v);
            }
        });
    }
}
