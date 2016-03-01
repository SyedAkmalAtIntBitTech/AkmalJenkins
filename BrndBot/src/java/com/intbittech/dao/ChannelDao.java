/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Channel;
import java.util.List;

/**
 * <code> {@link ChannelDao} </code> Interface to get Channel details from
 * Channel table
 *
 * @author Ajit
 */
public interface ChannelDao {

    /**
     * This method retrieves the list of {@link Channel} from DAO layer.
     *
     * @return {@link Channel}
     * @throws ProcessFailed the process failed
     */
    public List<Channel> getAllChannels() throws ProcessFailed;

}
