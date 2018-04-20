package com.microsoft.bingads.v12.api.test.entities.ad_group_dsa_target.read;



import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.microsoft.bingads.internal.functionalinterfaces.Function;
import com.microsoft.bingads.internal.functionalinterfaces.Supplier;
import com.microsoft.bingads.v12.api.test.entities.ObjectComparer;
import com.microsoft.bingads.v12.api.test.entities.ad_group_dsa_target.BulkAdGroupDynamicSearchAdTargetTest;
import com.microsoft.bingads.v12.bulk.entities.BulkAdGroupDynamicSearchAdTarget;
import com.microsoft.bingads.v12.campaignmanagement.ArrayOfCustomParameter;
import com.microsoft.bingads.v12.campaignmanagement.BiddableAdGroupCriterion;
import com.microsoft.bingads.v12.campaignmanagement.CustomParameter;
import com.microsoft.bingads.v12.campaignmanagement.CustomParameters;

public class BulkAdGroupDynamicSearchAdTargetReadUrlCustomParametersTest extends BulkAdGroupDynamicSearchAdTargetTest{

	@Parameter(value = 1)
	public CustomParameters expectedResult;
	
	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
			{"", null},
		});
	}
	@Test
	public void testRead() {	
		Map<String, String> values = new HashMap<String, String>();
		 
        values.put("Custom Parameter", datum);

        testReadProperty(
                values,
                expectedResult,
                new Function<BulkAdGroupDynamicSearchAdTarget, CustomParameters>() {
                    @Override
                    public CustomParameters apply(BulkAdGroupDynamicSearchAdTarget c) {
                        return ((BiddableAdGroupCriterion) c.getBiddableAdGroupCriterion()).getUrlCustomParameters();
                    }
                }
        );
		
		
		this.datum = "{_key1}=value\\;1; {_key2}=value\\\\2";
		values.put("Custom Parameter", datum);
		CustomParameter paraTest1 = new CustomParameter();
		paraTest1.setKey("key1");
		paraTest1.setValue("value;1");
		CustomParameter paraTest2 = new CustomParameter();
		paraTest2.setKey("key2");
		paraTest2.setValue("value\\2");
		
		ArrayOfCustomParameter array = new ArrayOfCustomParameter();
		array.getCustomParameters().add(paraTest1);
		array.getCustomParameters().add(paraTest2);
		
		expectedResult = new CustomParameters();
		expectedResult.setParameters(array);
		
		testReadProperty(
        values,
        expectedResult,
        new Function<BulkAdGroupDynamicSearchAdTarget, CustomParameters>() {
            @Override
            public CustomParameters apply(BulkAdGroupDynamicSearchAdTarget c) {
                return ((BiddableAdGroupCriterion) c.getBiddableAdGroupCriterion()).getUrlCustomParameters();
            }
        }, new Supplier<BulkAdGroupDynamicSearchAdTarget>() {
            @Override
            public BulkAdGroupDynamicSearchAdTarget get() {
                return new BulkAdGroupDynamicSearchAdTarget();
            }
        }, new ObjectComparer<CustomParameters>());

	}
}
