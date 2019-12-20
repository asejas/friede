package org.burrosoft.friede.models.garden;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.burrosoft.friede.models.generic.IdentifiableEntity;
import org.burrosoft.friede.models.section.Section;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Garden implements IdentifiableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "garden_generator")
    @SequenceGenerator(name="garden_generator", sequenceName = "garden_seq", allocationSize = 50)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "garden", cascade = CascadeType.ALL)
    private Set<Section> sections;

    public Garden() {
        sections = new HashSet<>();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Section> getSections() {
        return sections;
    }

    public void setSections(Set<Section> sections) {
        this.sections = sections;
    }
}
