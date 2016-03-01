/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.dao.ChannelDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Channel;
import com.intbittech.model.Company;
import com.intbittech.services.ChannelService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ajit
 */
@Service
@Transactional(rollbackFor = ProcessFailed.class)
public class ChannelServiceImpl implements ChannelService{
    
    @Autowired
    private ChannelDao channelDao;

    /**
     * {@inheritDoc}
     */
    public List<Channel> getAllChannels() throws ProcessFailed {
            List<Channel> channelList = channelDao.getAllChannels();
        if(channelList == null)
        {
             throw new ProcessFailed("No channel found.");
        }
              return  channelList;
    }
    
}
