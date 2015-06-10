package pojos;
// Generated May 28, 2015 12:35:50 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * TblUserLoginDetails generated by hbm2java
 */
public class TblUserLoginDetails  implements java.io.Serializable {


     private int id;
     private String userName;
     private String password;
     private int organizationid;
     private String logoName;
     private String companyName;
     private Set tblUserPreferenceses = new HashSet(0);

    public TblUserLoginDetails() {
    }

	
    public TblUserLoginDetails(int id, String userName, String password, int organizationid, String logoName) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.organizationid = organizationid;
        this.logoName = logoName;
    }
    public TblUserLoginDetails(int id, String userName, String password, int organizationid, String logoName, String companyName, Set tblUserPreferenceses) {
       this.id = id;
       this.userName = userName;
       this.password = password;
       this.organizationid = organizationid;
       this.logoName = logoName;
       this.companyName = companyName;
       this.tblUserPreferenceses = tblUserPreferenceses;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public int getOrganizationid() {
        return this.organizationid;
    }
    
    public void setOrganizationid(int organizationid) {
        this.organizationid = organizationid;
    }
    public String getLogoName() {
        return this.logoName;
    }
    
    public void setLogoName(String logoName) {
        this.logoName = logoName;
    }
    public String getCompanyName() {
        return this.companyName;
    }
    
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public Set getTblUserPreferenceses() {
        return this.tblUserPreferenceses;
    }
    
    public void setTblUserPreferenceses(Set tblUserPreferenceses) {
        this.tblUserPreferenceses = tblUserPreferenceses;
    }




}


