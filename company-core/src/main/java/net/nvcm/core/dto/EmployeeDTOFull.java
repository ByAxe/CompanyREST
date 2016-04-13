package net.nvcm.core.dto;

/**
 * Created by byaxe on 4/12/16.
 */
public class EmployeeDTOFull {

    private int id;
    private String name;
    private String position;
    private String sex;
    private int age;

    public EmployeeDTOFull() {

    }

    public EmployeeDTOFull(int id, String name, String position, String sex, int age) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.sex = sex;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
