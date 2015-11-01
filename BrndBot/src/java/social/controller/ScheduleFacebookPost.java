/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package social.controller;

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
        TblScheduledTwitter scheduledFacebookPost = getLatestApprovedFacebookPost();

        //The time zone of the saved date should be extracted. 
        //This time zone should be applied to the current time and then this comparison needs to be made.
        boolean shouldPostNow = DateTimeUtil.timeEqualsCurrentTime(scheduledFacebookPost.getDatetime());

        if (shouldPostNow) {
            TblScheduledSocialpostList facebookPost = getFacebookPost(scheduledFacebookPost);
            String message = "";
//            String message = PostToFacebook.postStatus(null, null, null, null, null, Integer.SIZE, null, null);
            if (message.equalsIgnoreCase("success")) {
                deleteScheduledFacebook(scheduledFacebookPost);
                //Get the next in line
                scheduledFacebookPost = getLatestApprovedFacebookPost();
            }
        }

        return scheduledFacebookPost.getDatetime();
    }

    private void deleteScheduledFacebook(TblScheduledTwitter scheduledFacebookPost) {
        //Call the DAO here
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private TblScheduledSocialpostList getFacebookPost(TblScheduledTwitter scheduledFacebookPost) {
        //Call the DAO here
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static TblScheduledTwitter getLatestApprovedFacebookPost() {
        //Call the DAO here
        return new TblScheduledTwitter();
    }

}
