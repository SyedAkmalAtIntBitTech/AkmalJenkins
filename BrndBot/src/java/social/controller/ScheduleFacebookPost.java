/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social.controller;

import com.controller.ApplicationContextListener;
import com.controller.IConstants;
import com.intbit.marketing.model.TblScheduledEntityList;
import com.intbit.marketing.model.TblScheduledSocialpostList;
import com.intbit.marketing.model.TblScheduledTwitter;
import com.intbit.marketing.service.ScheduledEntityListService;
import com.intbit.marketing.service.ScheduledSocialpostListService;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import util.DateTimeUtil;

/**
 *
 * @author AR
 */
public class ScheduleFacebookPost implements Callable {
    @Autowired
    ScheduledEntityListService scheduledEntityListService;
    @Autowired
    ScheduledSocialpostListService scheduledSocialpostListService;

    public void terminateThread() {
        Thread.currentThread().interrupt();
    }

    @Override
    public Date call() throws Exception {
        TblScheduledEntityList scheduledFacebookPost = null ;
        try {
            //The below table should be reused or needs a new table specifically for FB.
             scheduledFacebookPost = getLatestApprovedFacebookPost();
            
            //The time zone of the saved date should be extracted.
            //This time zone should be applied to the current time and then this comparison needs to be made.
            boolean shouldPostNow = DateTimeUtil.timeEqualsCurrentTime(scheduledFacebookPost.getScheduleTime());
            
            if (shouldPostNow) {
                TblScheduledSocialpostList facebookPost = getFacebookPost(scheduledFacebookPost);
                String jsonString= facebookPost.getMetadata();
                JSONObject jsonObject = (JSONObject)new JSONParser().parse(jsonString);
                String description = jsonObject.get(IConstants.kFacebookDescriptionKey).toString();
                String postText = jsonObject.get(IConstants.kFacebookPostTextKey).toString();
                String url= jsonObject.get(IConstants.kFacebookUrlKey).toString();
                Integer userId =getLatestApprovedFacebookPost().getUserId();
                PostToFacebook postToFacebook = new PostToFacebook();
                String accessToken= postToFacebook.getFacebookAccessToken(userId);
                
//                String message = "";
            String message = PostToFacebook.postStatus(accessToken, null, null, postText, null, null, url, description,userId,null);
                if (message.equalsIgnoreCase("success")) {
                    updateStatusScheduledFacebook(scheduledFacebookPost);
                    //Get the next in line
                    scheduledFacebookPost = getLatestApprovedFacebookPost();
                }
            }
            
           
        } catch (Throwable ex) {
            Logger.getLogger(ScheduleFacebookPost.class.getName()).log(Level.SEVERE, null, ex);
        }
         return scheduledFacebookPost.getScheduleTime();
    }

    private void updateStatusScheduledFacebook(TblScheduledEntityList scheduledFacebookPost) throws Throwable {
        //Call the DAO here
        scheduledFacebookPost.setStatus(IConstants.kSocialPostCommpleteStatus);
        scheduledEntityListService.update(scheduledFacebookPost);
    }

    private TblScheduledSocialpostList getFacebookPost(TblScheduledEntityList scheduledFacebookPost) throws Throwable {
        TblScheduledSocialpostList  scheduledSocialpostList = scheduledSocialpostListService.getByEntityId(scheduledFacebookPost.getId());
        return  scheduledSocialpostList;
    }

    private  TblScheduledEntityList getLatestApprovedFacebookPost() throws Throwable {
         TblScheduledEntityList  scheduledEntityList = scheduledEntityListService.getLatestApprovedSocialPost(IConstants.kSocialPostTemplateSavedStatus, IConstants.kFacebookKey,IConstants.kUserMarketingProgramOpenStatus);
       
        return scheduledEntityList;
    }

}
