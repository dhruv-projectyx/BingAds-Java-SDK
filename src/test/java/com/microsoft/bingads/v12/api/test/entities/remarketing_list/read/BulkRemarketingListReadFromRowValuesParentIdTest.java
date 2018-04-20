package com.microsoft.bingads.v12.api.test.entities.remarketing_list.read;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.microsoft.bingads.internal.functionalinterfaces.Function;
import com.microsoft.bingads.v12.api.test.entities.remarketing_list.BulkRemarketingListTest;
import com.microsoft.bingads.v12.bulk.entities.BulkRemarketingList;

@RunWith(Parameterized.class)
public class BulkRemarketingListReadFromRowValuesParentIdTest extends BulkRemarketingListTest {

    @Parameter(value = 1)
    public Long expectedResult;

    @Parameters
    public static Collection<Object[]> data() {
        // In this example, the parameter generator returns a List of
        // arrays.  Each array has two elements: { datum, expected }.
        // These data are hard-coded into the class, but they could be
        // generated or loaded in any way you like.
        return Arrays.asList(new Object[][]{
            {"123", 123L},
            {"9223372036854775807", 9223372036854775807L}
        });
    }

    @Test
    public void testRead() {
        this.<Long>testReadProperty("Parent Id", this.datum, this.expectedResult, new Function<BulkRemarketingList, Long>() {
            @Override
            public Long apply(BulkRemarketingList c) {
                return c.getRemarketingList().getParentId();
            }
        });
    }
}
