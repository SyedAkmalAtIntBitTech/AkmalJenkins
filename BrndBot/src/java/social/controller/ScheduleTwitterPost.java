/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social.controller;

import com.controller.IConstants;
import com.intbit.marketing.model.TblScheduledEntityList;
import com.intbit.marketing.model.TblScheduledSocialpostList;
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
public class ScheduleTwitterPost implements Callable {

    @Autowired
    ScheduledEntityListService scheduledEntityListService;
    @Autowired
    ScheduledSocialpostListService scheduledSocialpostListService;

    @Override
    public Date call() throws Exception {
        TblScheduledEntityList scheduledTwitterPost = null;
        try {
            scheduledTwitterPost = getLatestApprovedTwitterPost();

            //The time zone of the saved date should be extracted.
            //This time zone should be applied to the current time and then this comparison needs to be made.
            boolean shouldPostNow = DateTimeUtil.timeEqualsCurrentTime(scheduledTwitterPost.getScheduleTime());

            if (shouldPostNow) {
                TblScheduledSocialpostList twitterPost = getTwitterPost(scheduledTwitterPost);
                String jsonString = twitterPost.getMetadata();
                JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
                String text = jsonObject.get(IConstants.kTwitterTextKey).toString();
                PostToTwitter postToTwitter = new PostToTwitter();
                Integer userId = getLatestApprovedTwitterPost().getUserId();
                String twitterAccessToken = postToTwitter.getTwitterAccessToken(userId);
                String twitterTokenSecret = postToTwitter.getTwitterAccessTokenSecret(userId);

                String message = PostToTwitter.postStatus(twitterAccessToken, twitterTokenSecret, text, null, null, userId, null, null);
                if (message.equalsIgnoreCase("success")) {
                    updateStatusScheduledTwitter(scheduledTwitterPost);
                    //Get the next in line
                    scheduledTwitterPost = getLatestApprovedTwitterPost();
                }
            }

        } catch (Throwable ex) {
            Logger.getLogger(ScheduleTwitterPost.class.getName()).log(Level.SEVERE, null, ex);
        }
        return scheduledTwitterPost.getScheduleTime();
    }

    private void updateStatusScheduledTwitter(TblScheduledEntityList scheduledTwitterPost) throws Throwable {
        //Call the DAO here
        scheduledTwitterPost.setStatus(IConstants.kSocialPostCommpleteStatus);
        scheduledEntityListService.update(scheduledTwitterPost);
    }

    private TblScheduledSocialpostList getTwitterPost(TblScheduledEntityList scheduledTwitterPost) throws Throwable {
        //Call the DAO here
        TblScheduledSocialpostList scheduledSocialpostList = scheduledSocialpostListService.getByEntityId(scheduledTwitterPost.getId());
        return scheduledSocialpostList;
    }

    private TblScheduledEntityList getLatestApprovedTwitterPost() throws Throwable {
        String entityId = scheduledEntityListService.getLatestApprovedPost(IConstants.kSocialPostapprovedStatus, IConstants.kTwitterKey, IConstants.kUserMarketingProgramOpenStatus);
        TblScheduledEntityList scheduledEntityList = scheduledEntityListService.getScheduledEntityListByEntityId(Integer.parseInt(entityId));
        return scheduledEntityList;
    }

    public void terminateThread() {
        Thread.currentThread().interrupt();
    }

}
