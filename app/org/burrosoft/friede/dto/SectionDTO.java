package org.burrosoft.friede.dto;

public class SectionDTO {
    private Long id;
    private String name;
    private GardenDTO garden;

    public SectionDTO() {
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

    public GardenDTO getGarden() {
        return garden;
    }

    public void setGarden(GardenDTO garden) {
        this.garden = garden;
    }
}
