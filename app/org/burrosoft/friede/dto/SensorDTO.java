package org.burrosoft.friede.dto;

import org.burrosoft.friede.models.section.Section;

public class SensorDTO {
    public Long id;

    public String name;

    private SectionDTO section;

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

    public SectionDTO getSection() {
        return section;
    }

    public void setSection(SectionDTO section) {
        this.section = section;
    }
}
