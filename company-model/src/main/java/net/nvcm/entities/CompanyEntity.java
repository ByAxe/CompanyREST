package net.nvcm.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by byaxe on 4/8/16.
 */

@Entity
@Table(name = "companies")
public class CompanyEntity extends AbstractEntity implements Serializable {

    private String title;
    private String slogan;
    private Set<EmployeeEntity> employees = new HashSet<>();

    private CompanyEntity parent;
    private Set<CompanyEntity> children = new HashSet<>();

    public CompanyEntity() {
    }

    public CompanyEntity(String title, String slogan) {
        this.title = title;
        this.slogan = slogan;
    }

/*    @Override
    @Id
    @SequenceGenerator(name = "company_seq", sequenceName = "companies_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_seq")
    public Integer getId() {
        return super.getId();
    }*/

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "slogan")
    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "companies_employees", joinColumns = {
            @JoinColumn(name = "company_id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "employee_id")
    })
    public Set<EmployeeEntity> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<EmployeeEntity> employees) {
        this.employees = employees;
    }

    @ManyToOne(targetEntity = CompanyEntity.class)
    @JoinColumn(name = "parent")
    public CompanyEntity getParent() {
        return parent;
    }

    public void setParent(CompanyEntity parent) {
        this.parent = parent;
    }

    @OneToMany(mappedBy = "parent")
    public Set<CompanyEntity> getChildren() {
        return children;
    }

    public void setChildren(Set<CompanyEntity> children) {
        this.children = children;
    }
}
