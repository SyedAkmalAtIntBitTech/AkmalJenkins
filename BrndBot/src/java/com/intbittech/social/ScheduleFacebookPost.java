/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intbittech.social;

import com.intbittech.utility.IConstants;
import com.intbittech.AppConstants;
import com.intbittech.model.ScheduledEntityList;
import com.intbittech.model.ScheduledSocialpostList;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.intbittech.utility.DateTimeUtil;
import static org.apache.commons.lang.StringEscapeUtils.escapeHtml;

/**
 *
 * @author AR
 */
public class ScheduleFacebookPost implements Runnable {

    private static final Logger logger = Logger.getLogger(ScheduleFacebookPost.class.getName());

    public void terminateThread() {
        try {
            Thread.currentThread().interrupt();
        } catch (Exception ex) {
            Logger.getLogger(ScheduleFacebookPost.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override   
    public void run() {

        logger.log(Level.SEVERE, "In FB Schedule CallBlock");

        try {
            List<ScheduledEntityList> scheduledFacebookPost = getLatestApprovedFacebookPost();

            for (ScheduledEntityList currentScheduledFacebookPost : scheduledFacebookPost) {
                if (scheduledFacebookPost != null) {

                    //The time zone of the saved date should be extracted.
                    //This time zone should be applied to the current time and then this comparison needs to be made.
                    Logger.getLogger(ScheduleFacebookPost.class.getName()).log(Level.SEVERE, "Message to display entity id " + currentScheduledFacebookPost.getEntityId() + " and schedule time", currentScheduledFacebookPost.getScheduleTime());

                    Logger.getLogger(ScheduleFacebookPost.class.getName()).log(Level.SEVERE, "Current time:" + new Date());
                    boolean shouldPostNow = DateTimeUtil.timeEqualsCurrentTime(currentScheduledFacebookPost.getScheduleTime(),currentScheduledFacebookPost.getFkCompanyMarketingProgramId().getCompanyMarketingProgramId());
//                    boolean shouldPostNow = true;

                    if (shouldPostNow) {
                        ScheduledSocialpostList facebookPost = getFacebookPost(currentScheduledFacebookPost);
                        String jsonString = facebookPost.getMetaData();
                        JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
                        String description = escapeHtml(jsonObject.get(IConstants.kFacebookDescriptionKey).toString());
                        String postText = escapeHtml(jsonObject.get(IConstants.kFacebookPostTextKey).toString());
                        String url = jsonObject.get(IConstants.kFacebookUrlKey).toString();
                        String linkTitle = jsonObject.get(IConstants.kFacebookLinkTitleKey).toString();
                        String managedPage = jsonObject.get(IConstants.kFacebookManagedPageKey).toString();
                        Integer companyId = currentScheduledFacebookPost.getFkCompanyId().getCompanyId();
                        String file_image_path = "";
                        // To-do Ajit/Ilyas refactor AppConstants..
                        if (facebookPost.getImageType().equals("layout")) {
//                            file_image_path = AppConstants.LAYOUT_IMAGES_HOME + File.separator + facebookPost.getImageName();
                        } else if (facebookPost.getImageType().equals("gallery")) {
                            file_image_path = AppConstants.BASE_IMAGE_COMPANY_UPLOAD_PATH + File.separator + companyId + File.separator + com.intbittech.AppConstants.GALLERY_FOLDERNAME + File.separator + facebookPost.getImageName();
                        } else if (facebookPost.getImageType().equals("url")) {
                            file_image_path = facebookPost.getImageName();
                        }

                        facebookPost.getImageType();
                        Logger.getLogger(ScheduleFacebookPost.class.getName()).log(Level.SEVERE, "Message while scheduling the post", file_image_path);
                        PostToFacebook postToFacebook = new PostToFacebook();
                        String message = postToFacebook.postStatus(linkTitle, file_image_path, postText, url, facebookPost.getImageName(), url, description, facebookPost.getImageType(), companyId, null);
                        if (message.equalsIgnoreCase("success")) {
                            updateStatusScheduledFacebook(currentScheduledFacebookPost);
                        }
                    }
                }
            }

        } catch (Throwable ex) {
            System.out.println(ex);
            Logger.getLogger(ScheduleFacebookPost.class.getName()).log(Level.SEVERE, null, ex);
        }
        logger.log(Level.SEVERE, "In FB Schedule Call End Block" + new Date());
    }

    private void updateStatusScheduledFacebook(ScheduledEntityList scheduledFacebookPost) throws Throwable {
        //Call the DAO here
        scheduledFacebookPost.setStatus(IConstants.kSocialPostCommpleteStatus);
        SchedulerUtilityMethods.updateScheduledEntityListEntity(scheduledFacebookPost);
    }

    private ScheduledSocialpostList getFacebookPost(ScheduledEntityList scheduledFacebookPost) throws Throwable {
        ScheduledSocialpostList scheduledSocialpostList = SchedulerUtilityMethods.getSocialPostEntityById(scheduledFacebookPost.getEntityId());
        return scheduledSocialpostList;
    }

    private List<ScheduledEntityList> getLatestApprovedFacebookPost() throws Throwable {
        ArrayList<String> entityId = SchedulerUtilityMethods.getLatestApprovedPost(IConstants.kSocialPostapprovedStatus, IConstants.kFacebookKey, IConstants.kUserMarketingProgramOpenStatus);
        List<ScheduledEntityList> scheduledEntityList = new ArrayList<ScheduledEntityList>();;
        if (entityId.size() > 0) {
            for (String currentEntityId : entityId) {
                ScheduledEntityList tblScheduledEntityList = SchedulerUtilityMethods.getEntityById(Integer.parseInt(currentEntityId), IConstants.kFacebookKey);
                scheduledEntityList.add(tblScheduledEntityList);
            }
        }
        return scheduledEntityList;
    }

}
