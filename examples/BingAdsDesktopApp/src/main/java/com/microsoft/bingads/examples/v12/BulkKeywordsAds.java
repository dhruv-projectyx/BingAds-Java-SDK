package com.microsoft.bingads.examples.v12;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Calendar;

import com.microsoft.bingads.v12.campaignmanagement.*;
import static com.microsoft.bingads.examples.v12.BulkExampleBase.FileDirectory;
import com.microsoft.bingads.v12.bulk.entities.*;
import com.microsoft.bingads.v12.bulk.BulkServiceManager;
import com.microsoft.bingads.v12.bulk.DownloadParameters;
import com.microsoft.bingads.v12.bulk.BulkEntityIterable;
import com.microsoft.bingads.v12.bulk.DownloadEntity;
import com.microsoft.bingads.v12.bulk.ArrayOfDownloadEntity;
import com.microsoft.bingads.v12.bulk.BulkFileReader;
import com.microsoft.bingads.v12.bulk.DownloadFileType;
import com.microsoft.bingads.v12.bulk.ResultFileType;
import static com.microsoft.bingads.examples.v12.BulkExampleBase.BulkServiceManager;


public class BulkKeywordsAds extends BulkExampleBase {
	                
    public static void main(String[] args) {
		
        BulkEntityIterable downloadEntities = null;

        try {
            authorizationData = getAuthorizationData(null,null);

            BulkServiceManager = new BulkServiceManager(authorizationData, API_ENVIRONMENT);
            BulkServiceManager.setStatusPollIntervalInMilliseconds(5000);
            
            List<BulkEntity> uploadEntities = new ArrayList<BulkEntity>();
                                 
            // Let's create a new budget and share it with a new campaign.
            
            BulkBudget bulkBudget = new BulkBudget();
            bulkBudget.setClientId("YourClientIdGoesHere");
            Budget budget = new Budget();
            budget.setId(budgetIdKey);
            budget.setAmount(new java.math.BigDecimal(50));
            budget.setBudgetType(BudgetLimitType.DAILY_BUDGET_STANDARD);
            budget.setName("My Shared Budget " + System.currentTimeMillis());
            bulkBudget.setBudget(budget);
            uploadEntities.add(bulkBudget);
            

            BulkCampaign bulkCampaign = new BulkCampaign();
            // ClientId may be used to associate records in the bulk upload file with records in the results file. The value of this field  
            // is not used or stored by the server; it is simply copied from the uploaded record to the corresponding result record. 
            // Note: This bulk file Client Id is not related to an application Client Id for OAuth. 
            bulkCampaign.setClientId("YourClientIdGoesHere");
            Campaign campaign = new Campaign();
            // When using the Campaign Management service, the Id cannot be set. In the context of a BulkCampaign, the Id is optional  
            // and may be used as a negative reference key during bulk upload. For example the same negative reference key for the campaign Id  
            // will be used when adding new ad groups to this new campaign, or when associating ad extensions with the campaign. 
            campaign.setId(campaignIdKey);
            campaign.setName("Summer Shoes " + System.currentTimeMillis());
            campaign.setDescription("Summer shoes line.");
            // You must choose to set either the shared  budget ID or daily amount.
            // You can set one or the other, but you may not set both.
            campaign.setBudgetId(budgetIdKey);
            campaign.setDailyBudget(null);
            campaign.setBudgetType(null);
            campaign.setTimeZone("PacificTimeUSCanadaTijuana");
            campaign.setStatus(CampaignStatus.PAUSED);
            
            // You can set your campaign bid strategy to Enhanced CPC (EnhancedCpcBiddingScheme) 
            // and then, at any time, set an individual ad group or keyword bid strategy to 
            // Manual CPC (ManualCpcBiddingScheme).
            // For campaigns you can use either of the EnhancedCpcBiddingScheme or ManualCpcBiddingScheme objects. 
            // If you do not set this element, then ManualCpcBiddingScheme is used by default.
            campaign.setBiddingScheme(new EnhancedCpcBiddingScheme());

            // Used with FinalUrls shown in the expanded text ads that we will add below.
            campaign.setTrackingUrlTemplate(
                "http://tracker.example.com/?season={_season}&promocode={_promocode}&u={lpurl}");

            EnhancedCpcBiddingScheme enhancedCpcBiddingScheme = new EnhancedCpcBiddingScheme();
            enhancedCpcBiddingScheme.setType("EnhancedCpcBiddingScheme");
            campaign.setBiddingScheme(enhancedCpcBiddingScheme);
            bulkCampaign.setCampaign(campaign);

            BulkAdGroup bulkAdGroup = new BulkAdGroup();
            bulkAdGroup.setCampaignId(campaignIdKey);
            AdGroup adGroup = new AdGroup();
            adGroup.setId(adGroupIdKey);
            adGroup.setName("Women's Red Shoes");
            adGroup.setStartDate(null);
            Calendar calendar = Calendar.getInstance();
            adGroup.setEndDate(new com.microsoft.bingads.v12.campaignmanagement.Date());
            adGroup.getEndDate().setDay(31);
            adGroup.getEndDate().setMonth(12);
            adGroup.getEndDate().setYear(calendar.get(Calendar.YEAR));
            Bid CpcBid = new Bid();
            CpcBid.setAmount(0.09);
            adGroup.setCpcBid(CpcBid);
            adGroup.setLanguage("English");

            // For ad groups you can use either of the InheritFromParentBiddingScheme or ManualCpcBiddingScheme objects. 
            // If you do not set this element, then InheritFromParentBiddingScheme is used by default.
            adGroup.setBiddingScheme(new ManualCpcBiddingScheme());
            
            // Applicable for all audiences that are associated with this ad group. Set TargetAndBid to True 
            // if you want to show ads only to people included in the remarketing list, with the option to change
            // the bid amount. 
            ArrayOfSetting settings = new ArrayOfSetting();
            TargetSetting targetSetting = new TargetSetting();
            ArrayOfTargetSettingDetail targetSettingDetails = new ArrayOfTargetSettingDetail();
            TargetSettingDetail adGroupAudienceTargetSettingDetail = new TargetSettingDetail();
            adGroupAudienceTargetSettingDetail.setCriterionTypeGroup(CriterionTypeGroup.AUDIENCE);
            adGroupAudienceTargetSettingDetail.setTargetAndBid(Boolean.TRUE);
            targetSettingDetails.getTargetSettingDetails().add(adGroupAudienceTargetSettingDetail);
            targetSetting.setDetails(targetSettingDetails);
            settings.getSettings().add(targetSetting);
            adGroup.setSettings(settings);

            bulkAdGroup.setAdGroup(adGroup);

            // In this example only the first 3 ads should succeed. 
            // The TitlePart2 of the fourth ad is empty and not valid,
            // and the fifth ad is a duplicate of the second ad. 

            ArrayList<BulkExpandedTextAd> bulkExpandedTextAds = new ArrayList<BulkExpandedTextAd>();
            
            for(int i=0; i < 5; i++){
                BulkExpandedTextAd bulkExpandedTextAd = new BulkExpandedTextAd();
                bulkExpandedTextAd.setAdGroupId(adGroupIdKey);
                ExpandedTextAd expandedTextAd = new ExpandedTextAd();
                expandedTextAd.setTitlePart1("Contoso");
                expandedTextAd.setTitlePart2("Fast & Easy Setup");
                expandedTextAd.setText("Find New Customers & Increase Sales! Start Advertising on Contoso Today.");
                expandedTextAd.setPath1("seattle");
                expandedTextAd.setPath2("shoe sale");

                // With FinalUrls you can separate the tracking template, custom parameters, and 
                // landing page URLs. 
                com.microsoft.bingads.v12.campaignmanagement.ArrayOfstring finalUrls = new com.microsoft.bingads.v12.campaignmanagement.ArrayOfstring();
                finalUrls.getStrings().add("http://www.contoso.com/womenshoesale");
                expandedTextAd.setFinalUrls(finalUrls);

                // Final Mobile URLs can also be used if you want to direct the user to a different page 
                // for mobile devices.
                com.microsoft.bingads.v12.campaignmanagement.ArrayOfstring finalMobileUrls = new com.microsoft.bingads.v12.campaignmanagement.ArrayOfstring();
                finalMobileUrls.getStrings().add("http://mobile.contoso.com/womenshoesale");
                expandedTextAd.setFinalMobileUrls(finalMobileUrls);

                // You could use a tracking template which would override the campaign level
                // tracking template. Tracking templates defined for lower level entities 
                // override those set for higher level entities.
                // In this example we are using the campaign level tracking template.
                expandedTextAd.setTrackingUrlTemplate(null);

                // Set custom parameters that are specific to this ad, 
                // and can be used by the ad, ad group, campaign, or account level tracking template. 
                // In this example we are using the campaign level tracking template.
                CustomParameters urlCustomParameters = new CustomParameters();
                CustomParameter customParameter1 = new CustomParameter();
                customParameter1.setKey("promoCode");
                customParameter1.setValue("PROMO" + (i+1));
                ArrayOfCustomParameter customParameters = new ArrayOfCustomParameter();
                customParameters.getCustomParameters().add(customParameter1);
                CustomParameter customParameter2 = new CustomParameter();
                customParameter2.setKey("season");
                customParameter2.setValue("summer");
                customParameters.getCustomParameters().add(customParameter2);
                urlCustomParameters.setParameters(customParameters);
                expandedTextAd.setUrlCustomParameters(urlCustomParameters);

                bulkExpandedTextAd.setExpandedTextAd(expandedTextAd);
                bulkExpandedTextAds.add(bulkExpandedTextAd);
            }
            
            // This example sets ad format preference to Native only for the first ad.
            com.microsoft.bingads.v12.campaignmanagement.ArrayOfKeyValuePairOfstringstring expandedTextAdFCM = new com.microsoft.bingads.v12.campaignmanagement.ArrayOfKeyValuePairOfstringstring();
            com.microsoft.bingads.v12.campaignmanagement.KeyValuePairOfstringstring adFormatPreference = new com.microsoft.bingads.v12.campaignmanagement.KeyValuePairOfstringstring();
            adFormatPreference.setKey("NativePreference");
            adFormatPreference.setValue("True");
            expandedTextAdFCM.getKeyValuePairOfstringstrings().add(adFormatPreference);
            bulkExpandedTextAds.get(0).getAd().setForwardCompatibilityMap(expandedTextAdFCM);
            
            bulkExpandedTextAds.get(1).getAd().setTitlePart2("Quick & Easy Setu");
            bulkExpandedTextAds.get(2).getAd().setTitlePart2("Fast & Simple Setup");
            bulkExpandedTextAds.get(3).getAd().setTitlePart2("");
            bulkExpandedTextAds.get(4).getAd().setTitlePart2("Quick & Easy Setup");
            
            // In this example only the second keyword should succeed. The Text of the first keyword exceeds the limit,
            // and the third keyword is a duplicate of the second keyword. 

            BulkKeyword[] bulkKeywords = new BulkKeyword[3];
            Keyword[] keywords = new Keyword[3];

            bulkKeywords[0] = new BulkKeyword();
            bulkKeywords[0].setAdGroupId(adGroupIdKey);
            keywords[0] = new Keyword(); 
            keywords[0].setBid(new Bid());
            keywords[0].getBid().setAmount(0.47);
            keywords[0].setParam2("10% Off");
            keywords[0].setMatchType(MatchType.BROAD);
            keywords[0].setText("Brand-A Shoes Brand-A Shoes Brand-A Shoes Brand-A Shoes Brand-A Shoes " +
                    "Brand-A Shoes Brand-A Shoes Brand-A Shoes Brand-A Shoes Brand-A Shoes " +
                    "Brand-A Shoes Brand-A Shoes Brand-A Shoes Brand-A Shoes Brand-A Shoes");
            // For keywords you can use either of the InheritFromParentBiddingScheme or ManualCpcBiddingScheme objects. 
            // If you do not set this element, then InheritFromParentBiddingScheme is used by default.
            keywords[0].setBiddingScheme(new InheritFromParentBiddingScheme());
            bulkKeywords[0].setKeyword(keywords[0]);

            bulkKeywords[1] = new BulkKeyword();
            bulkKeywords[1].setAdGroupId(adGroupIdKey);
            keywords[1] = new Keyword(); 
            keywords[1].setBid(new Bid());
            keywords[1].getBid().setAmount(0.47);
            keywords[1].setParam2("10% Off");
            keywords[1].setMatchType(MatchType.PHRASE);
            keywords[1].setText("Brand-A Shoes");
            // For keywords you can use either of the InheritFromParentBiddingScheme or ManualCpcBiddingScheme objects. 
            // If you do not set this element, then InheritFromParentBiddingScheme is used by default.
            keywords[1].setBiddingScheme(new InheritFromParentBiddingScheme());
            bulkKeywords[1].setKeyword(keywords[1]);

            bulkKeywords[2] = new BulkKeyword();
            bulkKeywords[2].setAdGroupId(adGroupIdKey);
            keywords[2] = new Keyword(); 
            keywords[2].setBid(new Bid());
            keywords[2].getBid().setAmount(0.47);
            keywords[2].setParam2("10% Off");
            keywords[2].setMatchType(MatchType.PHRASE);
            keywords[2].setText("Brand-A Shoes");
            // For keywords you can use either of the InheritFromParentBiddingScheme or ManualCpcBiddingScheme objects. 
            // If you do not set this element, then InheritFromParentBiddingScheme is used by default.
            keywords[2].setBiddingScheme(new InheritFromParentBiddingScheme());
            bulkKeywords[2].setKeyword(keywords[2]);


            // Upload the entities created above.
            // Dependent entities such as BulkKeyword must be written after any dependencies,   
            // for example the BulkCampaign and BulkAdGroup.  

            uploadEntities.add(bulkCampaign);
            uploadEntities.add(bulkAdGroup);

            for (BulkKeyword bulkKeyword : bulkKeywords) {
                    uploadEntities.add(bulkKeyword);
            }

            for (BulkExpandedTextAd bulkExpandedTextAd : bulkExpandedTextAds) {
                    uploadEntities.add(bulkExpandedTextAd);
            }

            outputStatusMessage("\nAdding campaign, ad group, ads, and keywords...\n");

            // Upload and write the output

            Reader = writeEntitiesAndUploadFile(uploadEntities);
            downloadEntities = Reader.getEntities();

            List<BulkCampaign> campaignResults = new ArrayList<BulkCampaign>();
            List<BulkBudget> budgetResults = new ArrayList<BulkBudget>();

            for (BulkEntity entity : downloadEntities) {
                if (entity instanceof BulkBudget) {
                        budgetResults.add((BulkBudget) entity);
                        outputBulkBudgets(Arrays.asList((BulkBudget) entity) );
                }
                else if (entity instanceof BulkCampaign) {
                        campaignResults.add((BulkCampaign) entity);
                        outputBulkCampaigns(Arrays.asList((BulkCampaign) entity) );
                }
                else if (entity instanceof BulkAdGroup) {
                        outputBulkAdGroups(Arrays.asList((BulkAdGroup) entity) );
                }
                else if (entity instanceof BulkExpandedTextAd) {
                        outputBulkExpandedTextAds(Arrays.asList((BulkExpandedTextAd) entity) );
                }
                else if (entity instanceof BulkKeyword) {
                        outputBulkKeywords(Arrays.asList((BulkKeyword) entity) );
                }
            }
            downloadEntities.close();
            Reader.close();
            
            
            // Here is a simple example that updates the campaign budget.
                
            ArrayOfDownloadEntity entities = new ArrayOfDownloadEntity();
            entities.getDownloadEntities().add(DownloadEntity.BUDGETS);
            entities.getDownloadEntities().add(DownloadEntity.CAMPAIGNS);

            DownloadParameters downloadParameters = new DownloadParameters();
            downloadParameters.setDownloadEntities(entities);
            downloadParameters.setFileType(DownloadFileType.CSV);
            downloadParameters.setResultFileDirectory(new File(FileDirectory));
            downloadParameters.setResultFileName(DownloadFileName);
            downloadParameters.setOverwriteResultFile(true);
            downloadParameters.setLastSyncTimeInUTC(null);
            
            // Download all campaigns and shared budgets in the account.
            File bulkFilePath = BulkServiceManager.downloadFileAsync(downloadParameters, null, null).get();
            outputStatusMessage("Downloaded all campaigns and shared budgets in the account.\n"); 
            Reader = new BulkFileReader(bulkFilePath, ResultFileType.FULL_DOWNLOAD, BulkDownloadFileType);
            downloadEntities = Reader.getEntities();

            uploadEntities = new ArrayList<BulkEntity>();

            // If the campaign has a shared budget you cannot update the Campaign budget amount,
            // and you must instead update the amount in the Budget record. If you try to update 
            // the budget amount of a Campaign that has a shared budget, the service will return 
            // the CampaignServiceCannotUpdateSharedBudget error code.
            
            for (BulkEntity entity : downloadEntities) {
                if (entity instanceof BulkBudget && ((BulkBudget)entity).getBudget().getId() > 0) {
                    // Increase budget by 20 %
                    ((BulkBudget)entity).getBudget().setAmount(new java.math.BigDecimal(((BulkBudget)entity).getBudget().getAmount().doubleValue() * 1.2));
                    uploadEntities.add(entity);
                }
                else if (entity instanceof BulkCampaign && 
                    (((BulkCampaign)entity).getCampaign().getBudgetId() == null || (((BulkCampaign)entity).getCampaign().getBudgetId() <= 0))) {
                    
                    // Increase budget by 20 %
                    ((BulkCampaign)entity).getCampaign().setDailyBudget(((BulkCampaign)entity).getCampaign().getDailyBudget() * 1.2);
                        
                    uploadEntities.add(entity);
                }
            }
            downloadEntities.close();
            Reader.close(); 

            if (!uploadEntities.isEmpty()){
                outputStatusMessage("Changed local campaign budget amounts. Starting upload.\n"); 

                Reader = writeEntitiesAndUploadFile(uploadEntities);
                downloadEntities = Reader.getEntities();
                for (BulkEntity entity : downloadEntities) {
                    if (entity instanceof BulkBudget) {
                        outputBulkBudgets(Arrays.asList((BulkBudget) entity) );
                    }
                    else if (entity instanceof BulkCampaign) {
                        outputBulkCampaigns(Arrays.asList((BulkCampaign) entity) );
                    }
                }
                downloadEntities.close();
                Reader.close();
            }
            else
            {
                outputStatusMessage("No campaigns or shared budgets in account. \n");
            }                        

            //Delete the campaign, ad group, ads, and keywords that were previously added. 
            //You should remove this region if you want to view the added entities in the 
            //Bing Ads web application or another tool.

            //You must set the Id field to the corresponding entity identifier, and the Status field to Deleted. 

            //When you delete a BulkCampaign, the dependent entities such as BulkAdGroup, BulkKeyword, and BulkExpandedTextAd 
            //are deleted without being specified explicitly.   

            uploadEntities = new ArrayList<BulkEntity>();

            for (BulkBudget budgetResult : budgetResults){
                budgetResult.setStatus(Status.DELETED);
                uploadEntities.add(budgetResult);
            }
            
            for (BulkCampaign campaignResult : campaignResults){
                campaignResult.getCampaign().setStatus(CampaignStatus.DELETED);
                uploadEntities.add(campaignResult);
            }

            // Upload and write the output

            Reader = writeEntitiesAndUploadFile(uploadEntities);
            downloadEntities = Reader.getEntities();

            for (BulkEntity entity : downloadEntities) {
                if (entity instanceof BulkBudget) {
                    outputBulkBudgets(Arrays.asList((BulkBudget) entity) );
                }
                else if (entity instanceof BulkCampaign) {
                    outputBulkCampaigns(Arrays.asList((BulkCampaign) entity) );
                }
            }
            downloadEntities.close();
            Reader.close();

            outputStatusMessage("Program execution completed\n"); 
        }
        catch (Exception ex) {
            String faultXml = BingAdsExceptionHelper.getBingAdsExceptionFaultXml(ex, System.out);
            String message = BingAdsExceptionHelper.handleBingAdsSDKException(ex, System.out);
            ex.printStackTrace();
        } 
        finally {
            if (downloadEntities != null){
                try {
                    downloadEntities.close();
                } 
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        System.exit(0);
    }
}
