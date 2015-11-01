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
public class ScheduleTwitterPost implements Callable {

    @Override
    public Date call() throws Exception {
        TblScheduledTwitter scheduledTwitterPost = getLatestApprovedTwitterPost();
        
        //The time zone of the saved date should be extracted. 
        //This time zone should be applied to the current time and then this comparison needs to be made.
        boolean shouldPostNow = DateTimeUtil.timeEqualsCurrentTime(scheduledTwitterPost.getDatetime());
        
        if (shouldPostNow) {
            TblScheduledSocialpostList twitterPost = getTwitterPost(scheduledTwitterPost);
            String message = PostToTwitter.postStatus(null, null, null, null, null, Integer.SIZE, null, null);
            if (message.equalsIgnoreCase("success")) {
                deleteScheduledTwitter(scheduledTwitterPost);
                //Get the next in line
                scheduledTwitterPost = getLatestApprovedTwitterPost();
            }
        }
        
        return scheduledTwitterPost.getDatetime();
    }

    private void deleteScheduledTwitter(TblScheduledTwitter scheduledTwitterPost) {
        //Call the DAO here
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private TblScheduledSocialpostList getTwitterPost(TblScheduledTwitter scheduledTwitterPost) {
        //Call the DAO here
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private static TblScheduledTwitter getLatestApprovedTwitterPost() {
        //Call the DAO here
        return new TblScheduledTwitter();
    }
    
}
