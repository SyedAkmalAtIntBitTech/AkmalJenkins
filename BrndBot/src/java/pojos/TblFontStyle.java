package pojos;
// Generated May 28, 2015 12:35:50 PM by Hibernate Tools 4.3.1

/**
 * TblFontStyle generated by hbm2java
 */
public class TblFontStyle implements java.io.Serializable {

    private int id;
    private String fontStyle;

    public TblFontStyle() {
    }

    public TblFontStyle(int id, String fontStyle) {
        this.id = id;
        this.fontStyle = fontStyle;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFontStyle() {
        return this.fontStyle;
    }

    public void setFontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
    }

}
