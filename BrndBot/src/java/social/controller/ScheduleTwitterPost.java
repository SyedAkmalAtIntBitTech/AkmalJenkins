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
import com.intbit.marketing.model.TblScheduledEntityList;
import com.intbit.marketing.model.TblScheduledSocialpostList;
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
public class ScheduleTwitterPost implements Callable {

  
    @Override
    public Date call() throws Exception {

        //Adding tens mins if there are no latest approved posts
        Date nextPostTime = DateTimeUtil.getDatePlusMins(SocialPostScheduler.DefaultPollingInterval);
        try {
            TblScheduledEntityList scheduledTwitterPost = getLatestApprovedTwitterPost();
            if (scheduledTwitterPost != null) {
                //The time zone of the saved date should be extracted.
                //This time zone should be applied to the current time and then this comparison needs to be made.

                boolean shouldPostNow = DateTimeUtil.timeEqualsCurrentTime(scheduledTwitterPost.getScheduleTime());

                if (shouldPostNow) {
                    TblScheduledSocialpostList twitterPost = getTwitterPost(scheduledTwitterPost);
                    String jsonString = twitterPost.getMetadata();
                    JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonString);
                    String text = jsonObject.get(IConstants.kTwitterTextKey).toString();
                    PostToTwitter postToTwitter = new PostToTwitter();
                    Integer userId = scheduledTwitterPost.getUserId();
                    String twitterAccessToken = postToTwitter.getTwitterAccessToken(userId);
                    String twitterTokenSecret = postToTwitter.getTwitterAccessTokenSecret(userId);

                    String message = PostToTwitter.postStatus(twitterAccessToken, twitterTokenSecret, text, null, null, userId, null, null);
                    if (message.equalsIgnoreCase("success")) {
                        updateStatusScheduledTwitter(scheduledTwitterPost);
                        //Get the next in line
                        scheduledTwitterPost = getLatestApprovedTwitterPost();
                    }
                }
                nextPostTime = scheduledTwitterPost.getScheduleTime();
            }
        } catch (Throwable ex) {
            Logger.getLogger(ScheduleTwitterPost.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nextPostTime;
    }

    private void updateStatusScheduledTwitter(TblScheduledEntityList scheduledTwitterPost) throws Throwable {
        //Call the DAO here
        scheduledTwitterPost.setStatus(IConstants.kSocialPostCommpleteStatus);
        SchedulerUtilityMethods.updateScheduledEntityListEntity(scheduledTwitterPost);
        ApplicationContextListener.refreshTwitterScheduler();
    }

    private TblScheduledSocialpostList getTwitterPost(TblScheduledEntityList scheduledTwitterPost) throws Throwable {
        //Call the DAO here
        TblScheduledSocialpostList scheduledSocialpostList = SchedulerUtilityMethods.getSocialPostEntityById(scheduledTwitterPost.getId());
        return scheduledSocialpostList;
    }

    private TblScheduledEntityList getLatestApprovedTwitterPost() throws Throwable {
        TblScheduledEntityList scheduledEntityList = null;
        String entityId = SchedulerUtilityMethods.getLatestApprovedPost(IConstants.kSocialPostapprovedStatus, IConstants.kTwitterKey, IConstants.kUserMarketingProgramOpenStatus);
        if (!StringUtil.isEmpty(entityId)) {
            scheduledEntityList = SchedulerUtilityMethods.getEntityById(Integer.parseInt(entityId));
        }
        return scheduledEntityList;
    }

    public void terminateThread() {
        Thread.currentThread().interrupt();
    }
}
