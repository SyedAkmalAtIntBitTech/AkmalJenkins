package pojos;
// Generated May 28, 2015 12:35:50 PM by Hibernate Tools 4.3.1



/**
 * TblFontFamily generated by hbm2java
 */
public class TblFontFamily  implements java.io.Serializable {


     private int id;
     private String fontName;

    public TblFontFamily() {
    }

    public TblFontFamily(int id, String fontName) {
       this.id = id;
       this.fontName = fontName;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getFontName() {
        return this.fontName;
    }
    
    public void setFontName(String fontName) {
        this.fontName = fontName;
    }




}


