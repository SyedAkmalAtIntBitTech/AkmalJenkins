/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social.controller;

import com.controller.ApplicationContextListener;
import com.controller.IConstants;
import com.controller.SocialPostScheduler;
import com.divtohtml.StringUtil;
import com.intbit.AppConstants;
import com.intbit.marketing.model.TblScheduledEntityList;
import com.intbit.marketing.model.TblScheduledSocialpostList;
import java.io.File;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.concurrent.Callable;
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
        Thread.currentThread().interrupt();
    }

    @Override
    public void run() {
        //Adding tens mins if there are no latest approved posts
        logger.log(Level.SEVERE, "In FB Schedule CallBlock");

//        Date nextPostTime = DateTimeUtil.getDatePlusMins(SocialPostScheduler.DefaultPollingInterval);
        try {
            TblScheduledEntityList scheduledFacebookPost = getLatestApprovedFacebookPost();
            if (scheduledFacebookPost != null) {
                //The time zone of the saved date should be extracted.
                //This time zone should be applied to the current time and then this comparison needs to be made.
                Logger.getLogger(ScheduleFacebookPost.class.getName()).log(Level.SEVERE, "Message to display entity id " + scheduledFacebookPost.getEntityId() + " and schedule time", scheduledFacebookPost.getScheduleTime());

                Logger.getLogger(ScheduleFacebookPost.class.getName()).log(Level.SEVERE, "Current time:" + new Date());
                boolean shouldPostNow = DateTimeUtil.timeEqualsCurrentTime(scheduledFacebookPost.getScheduleTime());
//                boolean shouldPostNow = true;

                if (shouldPostNow) {
                    TblScheduledSocialpostList facebookPost = getFacebookPost(scheduledFacebookPost);
                    String jsonString = facebookPost.getMetadata();
                    JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
                    String description = jsonObject.get(IConstants.kFacebookDescriptionKey).toString();
                    String postText = jsonObject.get(IConstants.kFacebookPostTextKey).toString();
                    String url = jsonObject.get(IConstants.kFacebookUrlKey).toString();
                    String linkTitle = jsonObject.get(IConstants.kFacebookLinkTitleKey).toString();
                    String managedPage = jsonObject.get(IConstants.kFacebookManagedPageKey).toString();
                    Integer userId = scheduledFacebookPost.getUserId();
                    PostToFacebook postToFacebook = new PostToFacebook();
                    String accessToken = postToFacebook.getFacebookAccessToken(userId);
                    String file_image_path = AppConstants.LAYOUT_IMAGES_HOME + File.separator + facebookPost.getImageName();
                    Logger.getLogger(PostToSocial.class.getName()).log(Level.SEVERE, "Message while scheduling the post", file_image_path);

                    String message = PostToFacebook.postStatus(accessToken, linkTitle, file_image_path, postText, url, facebookPost.getImageName(), url, description, userId, null);
                    if (message.equalsIgnoreCase("success")) {
                        updateStatusScheduledFacebook(scheduledFacebookPost);
                        //Get the next in line
//                        scheduledFacebookPost = getLatestApprovedFacebookPost();
                    }
                }
//                if (scheduledFacebookPost != null) {
//                    nextPostTime = scheduledFacebookPost.getScheduleTime();
//                }
            }

        } catch (Throwable ex) {
            Logger.getLogger(ScheduleFacebookPost.class.getName()).log(Level.SEVERE, null, ex);
        }
        logger.log(Level.SEVERE, "In FB Schedule Call End Block" + new Date());
//        return nextPostTime;
    }

    private void updateStatusScheduledFacebook(TblScheduledEntityList scheduledFacebookPost) throws Throwable {
        //Call the DAO here
        scheduledFacebookPost.setStatus(IConstants.kSocialPostCommpleteStatus);
        SchedulerUtilityMethods.updateScheduledEntityListEntity(scheduledFacebookPost);
//        ApplicationContextListener.refreshFacebookScheduler();
    }

    private TblScheduledSocialpostList getFacebookPost(TblScheduledEntityList scheduledFacebookPost) throws Throwable {
        TblScheduledSocialpostList scheduledSocialpostList = SchedulerUtilityMethods.getSocialPostEntityById(scheduledFacebookPost.getEntityId());
        return scheduledSocialpostList;
    }

    private TblScheduledEntityList getLatestApprovedFacebookPost() throws Throwable {
        String entityId = SchedulerUtilityMethods.getLatestApprovedPost(IConstants.kSocialPostapprovedStatus, IConstants.kFacebookKey, IConstants.kUserMarketingProgramOpenStatus);
        TblScheduledEntityList scheduledEntityList = null;
        if (!StringUtil.isEmpty(entityId)) {
            scheduledEntityList = SchedulerUtilityMethods.getEntityById(Integer.parseInt(entityId), IConstants.kFacebookKey);
        }
        return scheduledEntityList;
    }

    

}
