package org.burrosoft.friede.models.section;

import org.burrosoft.friede.models.garden.Garden;
import org.burrosoft.friede.models.generic.IdentifiableEntity;
import org.burrosoft.friede.models.sensor.Sensor;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Section implements IdentifiableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "section_generator")
    @SequenceGenerator(name="section_generator", sequenceName = "section_seq", allocationSize = 50)
    private Long id;
    private String name;

    @ManyToOne()
    @JoinColumn(name = "garden_id")
    private Garden garden;

    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL)
    public Set<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(Set<Sensor> sensors) {
        this.sensors = sensors;
    }

    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL)
    private Set<Sensor> sensors;

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

    public Garden getGarden() {
        return garden;
    }

    public void setGarden(Garden garden) {
        this.garden = garden;
    }
}
