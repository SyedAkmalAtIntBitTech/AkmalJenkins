/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social.controller;

import com.controller.IConstants;
import com.divtohtml.StringUtil;
import com.intbit.AppConstants;
import com.intbit.marketing.model.TblScheduledEntityList;
import com.intbit.marketing.model.TblScheduledSocialpostList;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import util.DateTimeUtil;

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
            List<TblScheduledEntityList> scheduledFacebookPost = getLatestApprovedFacebookPost();

            for (TblScheduledEntityList currentScheduledFacebookPost : scheduledFacebookPost) {
                if (scheduledFacebookPost != null) {

                    //The time zone of the saved date should be extracted.
                    //This time zone should be applied to the current time and then this comparison needs to be made.
                    Logger.getLogger(ScheduleFacebookPost.class.getName()).log(Level.SEVERE, "Message to display entity id " + currentScheduledFacebookPost.getEntityId() + " and schedule time", currentScheduledFacebookPost.getScheduleTime());

                    Logger.getLogger(ScheduleFacebookPost.class.getName()).log(Level.SEVERE, "Current time:" + new Date());
                    boolean shouldPostNow = DateTimeUtil.timeEqualsCurrentTime(currentScheduledFacebookPost.getScheduleTime());
//                    boolean shouldPostNow = true;

                    if (shouldPostNow) {
                        TblScheduledSocialpostList facebookPost = getFacebookPost(currentScheduledFacebookPost);
                        String jsonString = facebookPost.getMetadata();
                        JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
                        String description = jsonObject.get(IConstants.kFacebookDescriptionKey).toString();
                        String postText = jsonObject.get(IConstants.kFacebookPostTextKey).toString();
                        String url = jsonObject.get(IConstants.kFacebookUrlKey).toString();
                        String linkTitle = jsonObject.get(IConstants.kFacebookLinkTitleKey).toString();
                        String managedPage = jsonObject.get(IConstants.kFacebookManagedPageKey).toString();
                        Integer userId = currentScheduledFacebookPost.getUserId();
                        PostToFacebook postToFacebook = new PostToFacebook();
                        String accessToken = postToFacebook.getFacebookAccessToken(userId);
                        String file_image_path = "";
                        if (facebookPost.getImageType().equals("layout")) {
                            file_image_path = AppConstants.LAYOUT_IMAGES_HOME + File.separator + facebookPost.getImageName();
                        } else if (facebookPost.getImageType().equals("gallery")) {
                            file_image_path = AppConstants.USER_IMAGE_HOME + File.separator + userId + File.separator + facebookPost.getImageName();
                        } else if (facebookPost.getImageType().equals("url")) {
                            file_image_path = facebookPost.getImageName();
                        }

                        facebookPost.getImageType();
                        Logger.getLogger(PostToSocial.class.getName()).log(Level.SEVERE, "Message while scheduling the post", file_image_path);

                        String message = PostToFacebook.postStatus(accessToken, linkTitle, file_image_path, postText, url, facebookPost.getImageName(), url, description, facebookPost.getImageType(), userId, null);
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

    private void updateStatusScheduledFacebook(TblScheduledEntityList scheduledFacebookPost) throws Throwable {
        //Call the DAO here
        scheduledFacebookPost.setStatus(IConstants.kSocialPostCommpleteStatus);
        SchedulerUtilityMethods.updateScheduledEntityListEntity(scheduledFacebookPost);
    }

    private TblScheduledSocialpostList getFacebookPost(TblScheduledEntityList scheduledFacebookPost) throws Throwable {
        TblScheduledSocialpostList scheduledSocialpostList = SchedulerUtilityMethods.getSocialPostEntityById(scheduledFacebookPost.getEntityId());
        return scheduledSocialpostList;
    }

    private List<TblScheduledEntityList> getLatestApprovedFacebookPost() throws Throwable {
        ArrayList<String> entityId = SchedulerUtilityMethods.getLatestApprovedPost(IConstants.kSocialPostapprovedStatus, IConstants.kFacebookKey, IConstants.kUserMarketingProgramOpenStatus);
        List<TblScheduledEntityList> scheduledEntityList = new ArrayList<TblScheduledEntityList>();;
        if (entityId.size() > 0) {
            for (String currentEntityId : entityId) {
                TblScheduledEntityList tblScheduledEntityList = SchedulerUtilityMethods.getEntityById(Integer.parseInt(currentEntityId), IConstants.kFacebookKey);
                scheduledEntityList.add(tblScheduledEntityList);
            }
        }
        return scheduledEntityList;
    }

}
