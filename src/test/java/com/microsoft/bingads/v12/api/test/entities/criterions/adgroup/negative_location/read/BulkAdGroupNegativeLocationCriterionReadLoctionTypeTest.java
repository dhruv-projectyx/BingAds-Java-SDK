package com.microsoft.bingads.v12.api.test.entities.criterions.adgroup.negative_location.read;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.microsoft.bingads.internal.functionalinterfaces.Function;
import com.microsoft.bingads.v12.api.test.entities.criterions.adgroup.negative_location.BulkAdGroupNegativeLocationCriterionTest;
import com.microsoft.bingads.v12.bulk.entities.BulkAdGroupNegativeLocationCriterion;
import com.microsoft.bingads.v12.campaignmanagement.LocationCriterion;

@RunWith(Parameterized.class)
public class BulkAdGroupNegativeLocationCriterionReadLoctionTypeTest extends BulkAdGroupNegativeLocationCriterionTest {

    @Parameter(value = 1)
    public String expectedResult;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[][]{
                        {"Test", "Test"},
                        {"", ""},
                        {null, null}
                }
        );
    }

    @Test
    public void testRead() {
        testReadProperty(
                "Sub Type",
                datum,
                expectedResult,
                new Function<BulkAdGroupNegativeLocationCriterion, String>() {
                    @Override
                    public String apply(BulkAdGroupNegativeLocationCriterion c) {
                        return ((LocationCriterion)c.getNegativeAdGroupCriterion().getCriterion()).getLocationType();
                    }
                }
        );
    }
}
