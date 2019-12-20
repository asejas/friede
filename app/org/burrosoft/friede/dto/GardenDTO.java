package org.burrosoft.friede.dto;

import java.util.ArrayList;
import java.util.List;

public class GardenDTO {
    private Long id;
    private String name;
    private List<SectionDTO> sections;

    public GardenDTO() {
        sections = new ArrayList<>();
    }

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

    public List<SectionDTO> getSections() {
        return sections;
    }

    public void setSections(List<SectionDTO> sections) {
        this.sections = sections;
    }
}
