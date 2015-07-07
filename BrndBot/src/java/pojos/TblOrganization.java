package pojos;
// Generated May 28, 2015 12:35:50 PM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;

/**
 * TblOrganization generated by hbm2java
 */
public class TblOrganization implements java.io.Serializable {

    private int id;
    private String organizationName;
    private Set tblModels = new HashSet(0);

    public TblOrganization() {
    }

    public TblOrganization(int id, String organizationName) {
        this.id = id;
        this.organizationName = organizationName;
    }

    public TblOrganization(int id, String organizationName, Set tblModels) {
        this.id = id;
        this.organizationName = organizationName;
        this.tblModels = tblModels;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrganizationName() {
        return this.organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Set getTblModels() {
        return this.tblModels;
    }

    public void setTblModels(Set tblModels) {
        this.tblModels = tblModels;
    }

}
