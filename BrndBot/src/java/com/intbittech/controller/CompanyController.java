/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.controller;

import com.intbittech.model.Channel;
import com.intbittech.model.Company;
import com.intbittech.model.EmailBlockExternalSource;
import com.intbittech.model.EmailBlockModelLookup;
import com.intbittech.model.Organization;
import com.intbittech.model.OrganizationCategoryLookup;
import com.intbittech.model.OrganizationCompanyLookup;
import com.intbittech.model.OrganizationEmailBlockLookup;
import com.intbittech.model.OrganizationMarketingCategoryLookup;
import com.intbittech.model.UserCompanyIds;
import com.intbittech.model.UserProfile;
import com.intbittech.model.UsersRoleCompanyLookup;
import com.intbittech.modelmappers.CategoryDetails;
import com.intbittech.modelmappers.ChannelDetails;
import com.intbittech.modelmappers.CompanyAllDetails;
import com.intbittech.modelmappers.CompanyDetails;
import com.intbittech.modelmappers.EmailBlockDetails;
import com.intbittech.modelmappers.MarketingCategoryDetails;
import com.intbittech.modelmappers.OrganizationCompanyDetails;
import com.intbittech.modelmappers.OrganizationDetails;
import com.intbittech.responsemappers.ContainerResponse;
import com.intbittech.responsemappers.GenericResponse;
import com.intbittech.responsemappers.TransactionResponse;
import com.intbittech.services.ChannelService;
import com.intbittech.services.CompanyService;
import com.intbittech.services.EmailBlockExternalSourceService;
import com.intbittech.services.EmailBlockModelLookupService;
import com.intbittech.services.EmailBlockService;
import com.intbittech.services.MarketingCategoryService;
import com.intbittech.services.OrganizationCategoryLookupService;
import com.intbittech.services.UserRoleCompanyLookUpService;
import com.intbittech.utility.ErrorHandlingUtil;
import com.intbittech.utility.IConstants;
import com.intbittech.utility.UserSessionUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ilyas
 */
@RestController
public class CompanyController {

    private Logger logger = Logger.getLogger(CompanyController.class);

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ChannelService channelService;

    @Autowired
    private OrganizationCategoryLookupService organizationCategoryLookupService;

    @Autowired
    private EmailBlockService emailBlockService;

    @Autowired
    private MarketingCategoryService marketingCategoryService;

    @Autowired
    private EmailBlockModelLookupService emailBlockModelLookupService;
    @Autowired
    private EmailBlockExternalSourceService emailBlockExternalSourceService;

    @Autowired
    private MessageSource messageSource;
    @Autowired
    private UserRoleCompanyLookUpService userRoleCompanyLookUpService;
    
    @RequestMapping(value = "getCurrentCompany",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getCurrentCompany(@RequestParam("companyId") Integer companyId) {
        GenericResponse<String> genericResponse = new GenericResponse<String>();
        try {
            List<String> currentCompany = new ArrayList<>();
            
            currentCompany.add(companyId.toString());
            genericResponse.setDetails(currentCompany);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("company_get_all", new String[]{}, Locale.US)));

        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "getAllCompanies", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllCompanies() {
        GenericResponse<CompanyDetails> genericResponse = new GenericResponse<CompanyDetails>();
        try {
            List<OrganizationCompanyLookup> organizationCompaniesList = companyService.getAllOrganizationCompanies();
            List<CompanyDetails> companyDetailsList = new ArrayList<>();
            for (OrganizationCompanyLookup organizationCompanyObject : organizationCompaniesList) {
                CompanyDetails companyDetails = new CompanyDetails();
                companyDetails.setCompanyId(organizationCompanyObject.getFkCompanyId().getCompanyId());
                companyDetails.setCompanyName(organizationCompanyObject.getFkCompanyId().getCompanyName());
                companyDetails.setOrganizationName(organizationCompanyObject.getFkOrganizationId().getOrganizationName());
                companyDetails.setOrganizationId(organizationCompanyObject.getFkOrganizationId().getOrganizationId());
                companyDetailsList.add(companyDetails);
            }
            genericResponse.setDetails(companyDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("company_get_all", new String[]{}, Locale.US)));

        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "getNonAddedGroups", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getNonAddedGroups(@RequestParam("companyId") Integer companyId) {
        GenericResponse<OrganizationDetails> genericResponse = new GenericResponse<OrganizationDetails>();
        try {
            List<OrganizationCompanyLookup> organizationCompanyDetail = new ArrayList<>();
            organizationCompanyDetail = companyService.getAllOrganizationCompanyById(companyId);

            Integer organizationCompanySize = 1;
            if (organizationCompanyDetail != null) {
                organizationCompanySize = organizationCompanyDetail.size() + 1;
            }
            Integer[] organizationIds = new Integer[organizationCompanySize];
            Integer i = 0;
            organizationIds[i++] = 0;

            if (organizationCompanyDetail != null) {
                for (OrganizationCompanyLookup organizationObject : organizationCompanyDetail) {
                    organizationIds[i++] = organizationObject.getFkOrganizationId().getOrganizationId();
                }
            }

            List<Organization> organizationList = companyService.getNonAddedGroups(organizationIds);
            List<OrganizationDetails> organizationDetailsList = new ArrayList<>();
            for (Organization organizationCompanyObject : organizationList) {
                OrganizationDetails organizationDetails = new OrganizationDetails();
                organizationDetails.setOrganizationId(organizationCompanyObject.getOrganizationId());
                organizationDetails.setOrganizationName(organizationCompanyObject.getOrganizationName());
                organizationDetailsList.add(organizationDetails);

            }

            genericResponse.setDetails(organizationDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("group_get_all", new String[]{}, Locale.US)));

        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "getCompanyDetailsById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getCompanyDetailsById(@RequestParam("companyId") Integer companyId) {
        GenericResponse<CompanyAllDetails> genericResponse = new GenericResponse<CompanyAllDetails>();
        try {
            OrganizationCompanyLookup organizationCompany = companyService.getOrganizationCompanyById(companyId);
            List<CompanyAllDetails> companyDetailsList = new ArrayList<>();

            CompanyAllDetails companyAllDetails = new CompanyAllDetails();
            companyAllDetails.setCompanyId(organizationCompany.getFkCompanyId().getCompanyId());
            companyAllDetails.setCompanyName(organizationCompany.getFkCompanyId().getCompanyName());
            companyAllDetails.setOrganizationId(organizationCompany.getFkOrganizationId().getOrganizationId());
            companyAllDetails.setOrganizationName(organizationCompany.getFkOrganizationId().getOrganizationName());

            List<OrganizationCompanyLookup> organizationCompanyDetail = new ArrayList<>();
            organizationCompanyDetail = companyService.getAllOrganizationCompanyById(companyId);
            List<OrganizationCompanyDetails> organizationCompanyDetailsList = new ArrayList<>();
            Integer organizationCompanySize = 1;
            if (organizationCompanyDetail != null) {
                organizationCompanySize = organizationCompanyDetail.size() + 1;
            }
            Integer[] organizationIds = new Integer[organizationCompanySize];
            Integer i = 0;
            organizationIds[i++] = organizationCompany.getFkOrganizationId().getOrganizationId();
            if (organizationCompanyDetail != null) {
                for (OrganizationCompanyLookup organizationObject : organizationCompanyDetail) {
                    OrganizationCompanyDetails organizationCompanyDetailsObject = new OrganizationCompanyDetails();
                    organizationCompanyDetailsObject.setOrganizationId(organizationObject.getFkOrganizationId().getOrganizationId());
                    organizationCompanyDetailsObject.setOrganizationName(organizationObject.getFkOrganizationId().getOrganizationName());
                    organizationCompanyDetailsObject.setOrganizationCompanyLookupId(organizationObject.getOrganizationCompanyLookupId());
                    organizationCompanyDetailsList.add(organizationCompanyDetailsObject);
                    organizationIds[i++] = organizationObject.getFkOrganizationId().getOrganizationId();
                }
            }
            companyAllDetails.setGroupDetails(organizationCompanyDetailsList);

            List<ChannelDetails> channelDetailsList = new ArrayList<>();

            List<Channel> channelList = channelService.getAllChannels();
            for (Channel channel : channelList) {
                ChannelDetails channelDetails = new ChannelDetails();
                channelDetails.setChannelName(channel.getChannelName());

                List<OrganizationCategoryLookup> OrganizationCategoryList = organizationCategoryLookupService.getAllOrganizationCategoryLookupByIds(organizationIds, channel.getChannelId());
                List<CategoryDetails> categoryDetailList = new ArrayList<>();
                if (OrganizationCategoryList != null) {
                    for (OrganizationCategoryLookup organizationCategoryLookup : OrganizationCategoryList) {

                        CategoryDetails categoryDetails = new CategoryDetails();
                        categoryDetails.setCategoryId(organizationCategoryLookup.getFkCategoryId().getCategoryId());
                        categoryDetails.setCategoryName(organizationCategoryLookup.getFkCategoryId().getCategoryName());
                        categoryDetails.setOrganizationId(organizationCategoryLookup.getFkOrganizationId().getOrganizationId());
                        categoryDetails.setOrganizationName(organizationCategoryLookup.getFkOrganizationId().getOrganizationName());
                        categoryDetailList.add(categoryDetails);
                    }
                }
                channelDetails.setCategoryDetailsList(categoryDetailList);
                channelDetailsList.add(channelDetails);

            }
            companyAllDetails.setChannelDetailsList(channelDetailsList);

            List<EmailBlockDetails> emailBlockDetailsList = new ArrayList<>();
            List<OrganizationEmailBlockLookup> organizationEmailBlockList = emailBlockService.getAllEmailBlockByOrganizationIds(organizationIds);
            for (OrganizationEmailBlockLookup organizationEmailBlockObject : organizationEmailBlockList) {
                EmailBlockDetails emailBlockDetails = new EmailBlockDetails();
                emailBlockDetails.setEmailBlockId(organizationEmailBlockObject.getFkEmailBlockId().getEmailBlockId());
                emailBlockDetails.setEmailBlockName(organizationEmailBlockObject.getFkEmailBlockId().getEmailBlockName());
                emailBlockDetails.setOrganizationId(organizationEmailBlockObject.getFkOrganizationId().getOrganizationId());
                List<EmailBlockExternalSource> emailBlockExternalSourceList = emailBlockService.getAllEmailBlockExternalSource(organizationEmailBlockObject.getFkEmailBlockId().getEmailBlockId());
                if (emailBlockExternalSourceList != null) {
                    EmailBlockExternalSource emailBlockExternalSourceObject = emailBlockExternalSourceList.get(0);
                    emailBlockDetails.setExternalSourceName(emailBlockExternalSourceObject.getFkExternalSourceKeywordLookupId().getFkExternalSourceId().getExternalSourceName());
                    emailBlockDetails.setExternalSourceKeywordName(emailBlockExternalSourceObject.getFkExternalSourceKeywordLookupId().getFkExternalSourceKeywordId().getExternalSourceKeywordName());
                }
                emailBlockDetailsList.add(emailBlockDetails);
            }
            companyAllDetails.setEmailBlockDetailsList(emailBlockDetailsList);

            List<OrganizationMarketingCategoryLookup> organizationMarketingCategoryList = marketingCategoryService.getByOrganizationIds(organizationIds);
            List<MarketingCategoryDetails> marketingCategoryDetailsList = new ArrayList<>();
            if (organizationMarketingCategoryList != null) {
                for (OrganizationMarketingCategoryLookup organizationMarketingCategoryObject : organizationMarketingCategoryList) {
                    MarketingCategoryDetails marketingCategoryDetails = new MarketingCategoryDetails();
                    marketingCategoryDetails.setMarketingCategoryId(organizationMarketingCategoryObject.getFkMarketingCategoryId().getMarketingCategoryId());
                    marketingCategoryDetails.setMarketingCategoryName(organizationMarketingCategoryObject.getFkMarketingCategoryId().getMarketingCategoryName());
                    marketingCategoryDetails.setOrganizationId(organizationMarketingCategoryObject.getFkOrganizationId().getOrganizationId());
                    marketingCategoryDetailsList.add(marketingCategoryDetails);
                }
            }
            companyAllDetails.setMarketingCategoryDetailsList(marketingCategoryDetailsList);

            companyDetailsList.add(companyAllDetails);
            genericResponse.setDetails(companyDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("companyDetails_get", new String[]{}, Locale.US)));

        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "getAllBlocksForCompany",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllBlocksForCompany(@RequestParam("companyId") Integer companyId) {
        GenericResponse<EmailBlockDetails> genericResponse = new GenericResponse<EmailBlockDetails>();
        try {

            List<OrganizationCompanyLookup> organizationCompanyDetail = new ArrayList<>();
            organizationCompanyDetail = companyService.getAllOrganizationsByCompanyId(companyId);
            List<OrganizationCompanyDetails> organizationCompanyDetailsList = new ArrayList<>();
            Integer organizationCompanySize = 1;
            if (organizationCompanyDetail != null) {
                organizationCompanySize = organizationCompanyDetail.size() + 1;
            }
            Integer[] organizationIds = new Integer[organizationCompanySize];
            Integer i = 0;
            organizationIds[i++] = 0;
            if (organizationCompanyDetail != null) {
                for (OrganizationCompanyLookup organizationObject : organizationCompanyDetail) {
                    organizationIds[i++] = organizationObject.getFkOrganizationId().getOrganizationId();
                }
            }
            List<EmailBlockDetails> emailBlockDetailsList = new ArrayList<>();
            List<OrganizationEmailBlockLookup> organizationEmailBlockList = emailBlockService.getAllEmailBlockByOrganizationIds(organizationIds);
            for (OrganizationEmailBlockLookup organizationEmailBlockObject : organizationEmailBlockList) {
                EmailBlockDetails emailBlockDetails = new EmailBlockDetails();
                emailBlockDetails.setEmailBlockId(organizationEmailBlockObject.getFkEmailBlockId().getEmailBlockId());
                emailBlockDetails.setEmailBlockName(organizationEmailBlockObject.getFkEmailBlockId().getEmailBlockName());
                emailBlockDetails.setOrganizationId(organizationEmailBlockObject.getFkOrganizationId().getOrganizationId());
                //This was wrong edit redoing to previous
//                List<EmailBlockModelLookup> emailBlockModelLookupList = emailBlockModelLookupService.getAllEmailBlockModel(organizationEmailBlockObject.getFkEmailBlockId().getEmailBlockId());
//
//                if(emailBlockModelLookupList != null){
//                    EmailBlockModelLookup emailBlockModelLookup = emailBlockModelLookupList.get(0);
//                    emailBlockDetails.setEmailBlockModelLookupId(emailBlockModelLookup.getEmailBlockModelLookupId());
//                }
                List<EmailBlockExternalSource> emailBlockExternalSourceList = emailBlockService.getAllEmailBlockExternalSource(organizationEmailBlockObject.getFkEmailBlockId().getEmailBlockId());
                if (emailBlockExternalSourceList != null) {
                    EmailBlockExternalSource emailBlockExternalSourceObject = emailBlockExternalSourceList.get(0);
                    emailBlockDetails.setExternalSourceName(emailBlockExternalSourceObject.getFkExternalSourceKeywordLookupId().getFkExternalSourceId().getExternalSourceName());
                    emailBlockDetails.setExternalSourceKeywordName(emailBlockExternalSourceObject.getFkExternalSourceKeywordLookupId().getFkExternalSourceKeywordId().getExternalSourceKeywordName());
                    emailBlockDetails.setExternalSourceKeywordLookupId(emailBlockExternalSourceObject.getFkExternalSourceKeywordLookupId().getExternalSourceKeywordLookupId());
                }
                emailBlockDetailsList.add(emailBlockDetails);
            }

            genericResponse.setDetails(emailBlockDetailsList);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("companyCategories_get", new String[]{}, Locale.US)));

        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "saveGroup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> saveGroup(@RequestBody CompanyDetails companyDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            OrganizationCompanyLookup organizationCompanyLookup = new OrganizationCompanyLookup();
            Company company = new Company();
            company.setCompanyId(companyDetails.getCompanyId());
            Organization organization = new Organization();
            organization.setOrganizationId(companyDetails.getOrganizationId());
            organizationCompanyLookup.setFkCompanyId(company);
            organizationCompanyLookup.setFkOrganizationId(organization);
            companyService.save(organizationCompanyLookup);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("group_save", new String[]{}, Locale.US)));

        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "changeOrganization", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> changeOrganization(@RequestBody CompanyDetails companyDetails) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            OrganizationCompanyLookup organizationCompanyLookup = companyService.getOrganizationCompanyById(companyDetails.getCompanyId());
            Company company = new Company();
            company.setCompanyId(companyDetails.getCompanyId());
            Organization organization = new Organization();
            organization.setOrganizationId(companyDetails.getOrganizationId());
            organizationCompanyLookup.setFkCompanyId(company);
            organizationCompanyLookup.setFkOrganizationId(organization);
            companyService.updateOrganizationCompanyLookUp(organizationCompanyLookup);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("group_save", new String[]{}, Locale.US)));

        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "deleteGroup", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> deleteGroup(@RequestParam("organizationCompanyLookupId") Integer organizationCompanyLookupId) {
        TransactionResponse transactionResponse = new TransactionResponse();
        try {
            companyService.delete(organizationCompanyLookupId);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("group_delete", new String[]{}, Locale.US)));

        } catch (Throwable throwable) {
            logger.error(throwable);
            transactionResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }

        return new ResponseEntity<>(new ContainerResponse(transactionResponse), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "getAllNonMindBodyBlocksForCompany", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getAllNonMindBodyBlocksForCompany(@RequestParam("companyId") Integer companyId) {
        GenericResponse<EmailBlockDetails> genericResponse = new GenericResponse<EmailBlockDetails>();
        try {
            List<OrganizationCompanyLookup> organizationCompanyDetail = new ArrayList<>();
            organizationCompanyDetail = companyService.getAllOrganizationsByCompanyId(companyId);
            Integer organizationCompanySize = 1;
            if (organizationCompanyDetail != null) {
                organizationCompanySize = organizationCompanyDetail.size() + 1;
            }
            Integer[] organizationIds = new Integer[organizationCompanySize];
            Integer i = 0;
            organizationIds[i++] = 0;
            if (organizationCompanyDetail != null) {
                for (OrganizationCompanyLookup organizationObject : organizationCompanyDetail) {
                    organizationIds[i++] = organizationObject.getFkOrganizationId().getOrganizationId();
                }
            }
            List<EmailBlockDetails> emailBlockDetailsList = new ArrayList<>();
            List<OrganizationEmailBlockLookup> organizationEmailBlockList = emailBlockService.getAllEmailBlockByOrganizationIds(organizationIds);
            for (OrganizationEmailBlockLookup organizationEmailBlockObject : organizationEmailBlockList) {
                EmailBlockDetails emailBlockDetails = new EmailBlockDetails();
                emailBlockDetails.setEmailBlockId(organizationEmailBlockObject.getFkEmailBlockId().getEmailBlockId());
                emailBlockDetails.setEmailBlockName(organizationEmailBlockObject.getFkEmailBlockId().getEmailBlockName());
                emailBlockDetails.setOrganizationId(organizationEmailBlockObject.getFkOrganizationId().getOrganizationId());

                List<EmailBlockExternalSource> emailBlockExternalSourceList = emailBlockExternalSourceService.getAllEmailBlockExternalSourceByEmailBlockIdAndExternalSourceId(organizationEmailBlockObject.getFkEmailBlockId().getEmailBlockId(), IConstants.EXTERNAL_SOURCE_NON_MINDBODY);
                if (emailBlockExternalSourceList != null) {
                    EmailBlockExternalSource emailBlockExternalSourceObject = emailBlockExternalSourceList.get(0);
                    emailBlockDetails.setExternalSourceName(emailBlockExternalSourceObject.getFkExternalSourceKeywordLookupId().getFkExternalSourceId().getExternalSourceName());
                    emailBlockDetails.setExternalSourceKeywordName(emailBlockExternalSourceObject.getFkExternalSourceKeywordLookupId().getFkExternalSourceKeywordId().getExternalSourceKeywordName());
                    emailBlockDetails.setExternalSourceKeywordLookupId(emailBlockExternalSourceObject.getFkExternalSourceKeywordLookupId().getExternalSourceKeywordLookupId());
                    emailBlockDetailsList.add(emailBlockDetails);
                }

            }
            genericResponse.setDetails(emailBlockDetailsList);
            System.out.println(emailBlockDetailsList.size() + emailBlockDetailsList.get(0).getEmailBlockName());
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation("Email blocks retrieved successfully."));

        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }
    @RequestMapping(value = "getNumberOfUsersInCompany",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContainerResponse> getNumberOfUsersInCompany(@RequestParam("companyId") Integer companyId) {
        GenericResponse<Integer> genericResponse = new GenericResponse<Integer>();
        try {
            List<Integer> noOfUsersInCompany = new ArrayList<>();
            List<UsersRoleCompanyLookup> UsersRoleCompanyLookupList = userRoleCompanyLookUpService.getAllUsersRoleCompanyLookupByCompanyId(companyId);
            
            noOfUsersInCompany.add(UsersRoleCompanyLookupList.size());
            genericResponse.setDetails(noOfUsersInCompany);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataNoErrorValidation(messageSource.getMessage("no_user_company_get_all", new String[]{}, Locale.US)));

        } catch (Throwable throwable) {
            logger.error(throwable);
            genericResponse.setOperationStatus(ErrorHandlingUtil.dataErrorValidation(throwable.getMessage()));
        }
        return new ResponseEntity<>(new ContainerResponse(genericResponse), HttpStatus.ACCEPTED);
    }

}
