package com.microsoft.bingads.v12.api.test.entities.criterions.campaign.radius.write;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.microsoft.bingads.internal.functionalinterfaces.BiConsumer;
import com.microsoft.bingads.v12.api.test.entities.criterions.campaign.radius.BulkCampaignRadiusCriterionTest;
import com.microsoft.bingads.v12.bulk.entities.BulkCampaignRadiusCriterion;
import com.microsoft.bingads.v12.campaignmanagement.RadiusCriterion;

@RunWith(Parameterized.class)
public class BulkCampaignRadiusCriterionWriteRadiusTest extends BulkCampaignRadiusCriterionTest {

    @Parameter(value = 1)
    public Long propertyValue;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[][]{
                        {"123", 123L},
                        {null, null}
                }
        );
    }

    @Test
    public void testWrite() {
        testWriteProperty(
                "Radius",
                datum,
                propertyValue,
                new BiConsumer<BulkCampaignRadiusCriterion, Long>() {
                    @Override
                    public void accept(BulkCampaignRadiusCriterion c, Long v) {
                    	((RadiusCriterion)c.getBiddableCampaignCriterion().getCriterion()).setRadius(v);;
                    }
                }
        );
    }
}
