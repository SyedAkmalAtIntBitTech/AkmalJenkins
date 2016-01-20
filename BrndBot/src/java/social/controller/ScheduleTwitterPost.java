/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social.controller;

import com.controller.IConstants;
import com.controller.SocialPostScheduler;
import com.divtohtml.StringUtil;
import com.intbit.AppConstants;
import com.intbit.marketing.model.TblScheduledEntityList;
import com.intbit.marketing.model.TblScheduledSocialpostList;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class ScheduleTwitterPost implements Runnable {

      public static final Logger logger = Logger.getLogger(util.Utility.getClassName(ScheduleTwitterPost.class));

    @Override
    public void run(){

        try {
            List<TblScheduledEntityList> scheduledTwitterPost = getLatestApprovedTwitterPost();
            for (TblScheduledEntityList currentScheduledTwitterPost : scheduledTwitterPost) {
            if (currentScheduledTwitterPost != null) {
                //The time zone of the saved date should be extracted.
                //This time zone should be applied to the current time and then this comparison needs to be made.
                
                boolean shouldPostNow = DateTimeUtil.timeEqualsCurrentTime(currentScheduledTwitterPost.getScheduleTime());
//                boolean shouldPostNow = true;

                if (shouldPostNow) {
                    TblScheduledSocialpostList twitterPost = getTwitterPost(currentScheduledTwitterPost);
                    String jsonString = twitterPost.getMetadata();
                    JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
                    String text = jsonObject.get(IConstants.kTwitterTextKey).toString();
                    String url = jsonObject.get(IConstants.kTwitterURLKey).toString();
                    PostToTwitter postToTwitter = new PostToTwitter();
                    Integer userId = currentScheduledTwitterPost.getUserId();
                    String twitterAccessToken = postToTwitter.getTwitterAccessToken(userId);
                    String twitterTokenSecret = postToTwitter.getTwitterAccessTokenSecret(userId);
                    
                    String file_image_path = "";
                    if (twitterPost.getImageType().equals("layout")){
                        file_image_path = AppConstants.LAYOUT_IMAGES_HOME + File.separator + twitterPost.getImageName();
                    }else if(twitterPost.getImageType().equals("gallery")){
                        file_image_path = AppConstants.USER_IMAGE_HOME + File.separator + userId + File.separator + twitterPost.getImageName();
                    }else if (twitterPost.getImageType().equals("url")){
                        file_image_path = twitterPost.getImageName();
                    }
                    
                    Logger.getLogger(ScheduleTwitterPost.class.getName()).log(Level.SEVERE, text +" Tweet @"+new Date());
                    String message = PostToTwitter.postStatus(twitterAccessToken, twitterTokenSecret, twitterPost.getImageType(),text, url, file_image_path, userId, null, twitterPost.getImageName());
                    if (message.equalsIgnoreCase("success")) {
                        updateStatusScheduledTwitter(currentScheduledTwitterPost);
                    }
                    }
                }
                
            }
        } catch (Throwable ex) {
            Logger.getLogger(ScheduleTwitterPost.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateStatusScheduledTwitter(TblScheduledEntityList scheduledTwitterPost) throws Throwable {
        //Call the DAO here
        logger.log(Level.INFO, "Twitter post:"+scheduledTwitterPost.getScheduleTitle() +"Id:"+ scheduledTwitterPost.getId() +" time:"+ scheduledTwitterPost.getScheduleTime().toString());
        scheduledTwitterPost.setStatus(IConstants.kSocialPostCommpleteStatus);
        SchedulerUtilityMethods.updateScheduledEntityListEntity(scheduledTwitterPost);
    }

    private TblScheduledSocialpostList getTwitterPost(TblScheduledEntityList scheduledTwitterPost) throws Throwable {
        //Call the DAO here
        TblScheduledSocialpostList scheduledSocialpostList = SchedulerUtilityMethods.getSocialPostEntityById(scheduledTwitterPost.getEntityId());
        return scheduledSocialpostList;
    }

    private List<TblScheduledEntityList> getLatestApprovedTwitterPost() throws Throwable {
        List<TblScheduledEntityList> scheduledEntityList = new ArrayList<TblScheduledEntityList>();
        ArrayList<String> entityId = SchedulerUtilityMethods.getLatestApprovedPost(IConstants.kSocialPostapprovedStatus, IConstants.kTwitterKey, IConstants.kUserMarketingProgramOpenStatus);
        if (entityId.size()>0) {
            for (String currentEntityId : entityId) {
                    TblScheduledEntityList tblScheduledEntityList = SchedulerUtilityMethods.getEntityById(Integer.parseInt(currentEntityId),IConstants.kTwitterKey);
                     scheduledEntityList.add(tblScheduledEntityList);
            }
        }
        return scheduledEntityList;
    }
    public void terminateThread() {
        Thread.currentThread().interrupt();
    }
}
