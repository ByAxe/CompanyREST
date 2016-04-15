package net.nvcm.core.dto;

/**
 * Created by byaxe on 4/15/16.
 */
public class OperationJdbcDTO {
    private int id;
    private String title;
    private int cost;

    public OperationJdbcDTO() {
    }

    public OperationJdbcDTO(int id, String title, int cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
