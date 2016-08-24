/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Invite;
import com.intbittech.model.InvitedUsers;
import com.intbittech.model.Users;
import com.intbittech.modelmappers.InviteDetails;
import java.util.List;

/**
 *
 * @author Syed Muzamil at IntBit Technologies.
 */
public interface UsersInviteService {
    
    /**
     * This method pass companyInvite as input and get the {@link String} from DAO layer.
     *
     * @param companyInvite
     * @return {@link String}
     * @throws ProcessFailed the process failed
     */
    public String save(Invite companyInvite) throws ProcessFailed;
    
    /**
     * This method pass user as input and updates the {@link Invite} from DAO layer.
     *
     * @param companyInvite
     * @throws ProcessFailed the process failed
     */
    public void update(Invite companyInvite) throws ProcessFailed;
    /**
     * This method pass id as input and deletes the {@link Invite} from DAO layer.
     *
     * @param id
     * @throws ProcessFailed the process failed
     */
    public void delete(Integer id) throws ProcessFailed;

    /**
     * This method pass inviteId as input and deletes the {@link Invite} from DAO layer.
     *
     * @param inviteId
     * @throws ProcessFailed the process failed
     */
    public boolean removeUsersByInviteId(Integer inviteId)throws ProcessFailed;
    
    /**
     * This method pass id as input and returns the list of {@link InviteUsers} from DAO layer.
     *
     * @param id
     * @return {@link InvitedUsers}
     * @throws ProcessFailed the process failed
     */
    public List<InvitedUsers> getInvitedUsers(Users userFrom)throws ProcessFailed;
    /**
     * This method pass userTo as input and returns the {@link Invite} from DAO layer.
     *
     * @param userTo
     * @return {@link InvitedUsers}
     * @throws ProcessFailed the process failed
     */
    public Invite getInvitedUserByUserTo(Users userTo) throws ProcessFailed;    
    /**
     * This method pass Id as input and get the {@link Invite} from DAO layer
     *
     * @param Id
     * @return {@link Invite}
     * @throws ProcessFailed the process failed
     */
    public List<Invite> getAllInvitedUsers(Users userFrom) throws ProcessFailed;    
    
    /**
     * This method pass Id as input and get the {@link Invite} from DAO layer
     *
     * @param Id
     * @return {@link Invite}
     * @throws ProcessFailed the process failed
     */
    public Invite getInvitedUserById(Integer Id) throws ProcessFailed;
    
    /**
     * This method pass inviteCode as input and get the {@link Invite} from DAO layer
     *
     * @param inviteCode
     * @return {@link Invite}
     * @throws ProcessFailed the process failed
     */
    public Invite getInvitedUserByInviteCode(String inviteCode) throws ProcessFailed;

    /**
     * This method pass fromEmailId,imageContextPath,inviteDetails as input and sends mail from Service layer
     *
     * @param fromEmailId
     * @param imageContextPath
     * @param inviteDetails
     * @throws ProcessFailed the process failed
     */
    public void sendMail(String fromEmailId, String imageContextPath, InviteDetails inviteDetails) throws ProcessFailed;   
    
    /**
     * This method pass fromEmailId,inviteId as input and sends mail from Service layer
     *
     * @param fromEmailId
     * @param inviteId
     * @throws ProcessFailed the process failed
     */
    public boolean reSendInvitation(Integer inviteId)throws ProcessFailed;
    /**
     * This method pass fromEmailId,imageContextPath,inviteDetails as input and sends mail from Service layer
     *
     * @param fromEmailId
     * @param imageContextPath
     * @param inviteDetails
     * @throws ProcessFailed the process failed
     */
    public void reSendMail(String from_email_id, String imageContextPath, Integer inviteId)throws ProcessFailed;

}
