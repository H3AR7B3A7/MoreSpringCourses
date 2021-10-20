package be.dog.d.steven.conferenceapp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "REGISTRATION")
@NamedQueries({
        @NamedQuery(name = Registration.REGISTRATION_REPORT, query = Registration.REGISTRATION_REPORT_JPQL)
})
public class Registration {
    
    public static final String REGISTRATION_REPORT = "registrationReport";
    public static final String REGISTRATION_REPORT_JPQL = 
            "SELECT new be.dog.d.steven.conferenceapp.model.RegistrationReport(r.name, c.name, c.description) " +
            "FROM Registration r, Course c " + 
            "WHERE r.id = c.registration.id";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    
    @NotEmpty
    @Column(name = "NAME")
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "registration", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Course> courses = new ArrayList<>();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}