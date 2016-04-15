package net.nvcm.jdbc;

/**
 * Created by byaxe on 4/15/16.
 */
public class Operations {

    private Integer id;
    private String title;
    private Integer cost;

    public Operations() {
    }

    public Operations(String title, Integer cost) {
        this.title = title;
        this.cost = cost;
    }

    public Operations(Integer id, String title, Integer cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
