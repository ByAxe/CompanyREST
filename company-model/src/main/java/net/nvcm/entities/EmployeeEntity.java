package net.nvcm.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by byaxe on 4/8/16.
 */
@Entity
@Table(name = "employees")
public class EmployeeEntity extends AbstractEntity implements Serializable {

    private String name;
    private String position;
    private String sex;
    private int age;
    private Set<CompanyEntity> companies = new HashSet<>();

    public EmployeeEntity() {
    }

    public EmployeeEntity(String name, String position, String sex, int age) {
        this.name = name;
        this.position = position;
        this.sex = sex;
        this.age = age;
    }
/*    @Override
    @Id
    @SequenceGenerator(name = "employee_seq", sequenceName = "employees_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
    public Integer getId() {
        return super.getId();
    }*/

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "position")
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Column(name = "sex")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Column(name = "age")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "employees")
    public Set<CompanyEntity> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<CompanyEntity> companies) {
        this.companies = companies;
    }
}
