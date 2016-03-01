/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Channel;
import java.util.List;

/**
 * <code>{@link ChannelService}</code> is service layer interface for communicating
 * between Controller and DAO classes
 *
 * @author Ajit
 */
public interface ChannelService {
    
       /**
     * This method retrieves the list of {@link Channel} from DAO layer.
     *
     * @return {@link Channel}
     * @throws ProcessFailed the process failed
     */
    public List<Channel> getAllChannels() throws ProcessFailed;
    
}
