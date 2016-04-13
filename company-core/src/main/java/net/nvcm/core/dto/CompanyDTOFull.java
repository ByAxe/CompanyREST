package net.nvcm.core.dto;

/**
 * Created by byaxe on 4/12/16.
 */
public class CompanyDTOFull {

    private int id;
    private String title;
    private String slogan;

    public CompanyDTOFull() {

    }

    public CompanyDTOFull(int id, String title, String slogan) {
        this.id = id;
        this.title = title;
        this.slogan = slogan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }
}
