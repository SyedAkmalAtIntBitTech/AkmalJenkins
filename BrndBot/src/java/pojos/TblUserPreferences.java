package pojos;
// Generated May 28, 2015 12:35:50 PM by Hibernate Tools 4.3.1



/**
 * TblUserPreferences generated by hbm2java
 */
public class TblUserPreferences  implements java.io.Serializable {


     private int id;
     private TblBrandColorTheme tblBrandColorTheme;
     private TblBrandFontFamily tblBrandFontFamily;
     private TblBrandPersonality tblBrandPersonality;
     private TblLook tblLook;
     private TblUserLoginDetails tblUserLoginDetails;
     private String location;

    public TblUserPreferences() {
    }

    public TblUserPreferences(int id, TblBrandColorTheme tblBrandColorTheme, TblBrandFontFamily tblBrandFontFamily, TblBrandPersonality tblBrandPersonality, TblLook tblLook, TblUserLoginDetails tblUserLoginDetails, String location) {
       this.id = id;
       this.tblBrandColorTheme = tblBrandColorTheme;
       this.tblBrandFontFamily = tblBrandFontFamily;
       this.tblBrandPersonality = tblBrandPersonality;
       this.tblLook = tblLook;
       this.tblUserLoginDetails = tblUserLoginDetails;
       this.location = location;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public TblBrandColorTheme getTblBrandColorTheme() {
        return this.tblBrandColorTheme;
    }
    
    public void setTblBrandColorTheme(TblBrandColorTheme tblBrandColorTheme) {
        this.tblBrandColorTheme = tblBrandColorTheme;
    }
    public TblBrandFontFamily getTblBrandFontFamily() {
        return this.tblBrandFontFamily;
    }
    
    public void setTblBrandFontFamily(TblBrandFontFamily tblBrandFontFamily) {
        this.tblBrandFontFamily = tblBrandFontFamily;
    }
    public TblBrandPersonality getTblBrandPersonality() {
        return this.tblBrandPersonality;
    }
    
    public void setTblBrandPersonality(TblBrandPersonality tblBrandPersonality) {
        this.tblBrandPersonality = tblBrandPersonality;
    }
    public TblLook getTblLook() {
        return this.tblLook;
    }
    
    public void setTblLook(TblLook tblLook) {
        this.tblLook = tblLook;
    }
    public TblUserLoginDetails getTblUserLoginDetails() {
        return this.tblUserLoginDetails;
    }
    
    public void setTblUserLoginDetails(TblUserLoginDetails tblUserLoginDetails) {
        this.tblUserLoginDetails = tblUserLoginDetails;
    }
    public String getLocation() {
        return this.location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }




}


