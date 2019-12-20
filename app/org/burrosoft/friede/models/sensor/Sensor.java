package org.burrosoft.friede.models.sensor;

import org.burrosoft.friede.models.garden.Garden;
import org.burrosoft.friede.models.generic.IdentifiableEntity;
import org.burrosoft.friede.models.section.Section;

import javax.persistence.*;

@Entity
public class Sensor implements IdentifiableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sensor_generator")
    @SequenceGenerator(name="sensor_generator", sequenceName = "sensor_seq", allocationSize = 50)
    public Long id;

    public String name;

    @ManyToOne()
    @JoinColumn(name = "section_id")
    private Section section;

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
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
}
