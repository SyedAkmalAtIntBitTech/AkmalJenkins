/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social.controller;

import com.controller.ApplicationContextListener;
import com.intbit.marketing.model.TblScheduledEntityList;
import com.intbit.marketing.model.TblScheduledSocialpostList;
import com.intbit.marketing.model.TblScheduledTwitter;
import java.util.Date;
import java.util.concurrent.Callable;
import util.DateTimeUtil;

/**
 *
 * @author AR
 */
public class ScheduleFacebookPost implements Callable {

    public void terminateThread() {
        Thread.currentThread().interrupt();
    }

    @Override
    public Date call() throws Exception {
        //The below table should be reused or needs a new table specifically for FB.
        TblScheduledEntityList scheduledFacebookPost = getLatestApprovedFacebookPost();

        //The time zone of the saved date should be extracted. 
        //This time zone should be applied to the current time and then this comparison needs to be made.
        boolean shouldPostNow = DateTimeUtil.timeEqualsCurrentTime(scheduledFacebookPost.getScheduleTime());

        if (shouldPostNow) {
            TblScheduledSocialpostList facebookPost = getFacebookPost(scheduledFacebookPost);
            String message = "";
//            String message = PostToFacebook.postStatus(null, null, null, null, null, Integer.SIZE, null, null);
            if (message.equalsIgnoreCase("success")) {
                updateStatusScheduledFacebook(scheduledFacebookPost);
                //Get the next in line
                scheduledFacebookPost = getLatestApprovedFacebookPost();
            }
        }

        return scheduledFacebookPost.getScheduleTime();
    }

    private void updateStatusScheduledFacebook(TblScheduledEntityList scheduledFacebookPost) {
        //Call the DAO here
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private TblScheduledSocialpostList getFacebookPost(TblScheduledEntityList scheduledFacebookPost) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static TblScheduledEntityList getLatestApprovedFacebookPost() {
        //Call the DAO here
        return new TblScheduledEntityList();
    }

}
