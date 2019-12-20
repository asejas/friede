package org.burrosoft.friede.service;

import com.google.inject.Inject;
import org.burrosoft.friede.dto.GardenDTO;
import org.burrosoft.friede.dto.SectionDTO;
import org.burrosoft.friede.models.garden.Garden;
import org.burrosoft.friede.models.garden.GardenRepository;
import org.burrosoft.friede.models.section.Section;
import org.burrosoft.friede.models.section.SectionRepository;
import org.springframework.beans.BeanUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

public class SectionService {

    private SectionRepository<Section> sectionRepository;
    private GardenRepository<Garden> gardenRepository;

    @Inject
    public SectionService(SectionRepository<Section> sectionRepository, GardenRepository<Garden> gardenRepository) {
        this.sectionRepository = sectionRepository;
        this.gardenRepository = gardenRepository;
    }

    public CompletionStage<SectionDTO> insert(SectionDTO sectionDTO) {
        Section section = new Section();
        BeanUtils.copyProperties(sectionDTO, section);
        return gardenRepository.get(sectionDTO.getGarden().getId())
                .thenCompose(garden ->
                {
                    section.setGarden(garden);
                    return sectionRepository.insert(section);
                })
                .thenCompose(sectionInsertResponse -> {
                    SectionDTO sectionResponseDTO=new SectionDTO();
                    GardenDTO gardenDTO=new GardenDTO();
                    BeanUtils.copyProperties(sectionInsertResponse, sectionResponseDTO);
                    BeanUtils.copyProperties(sectionInsertResponse.getGarden(), gardenDTO);
                    sectionResponseDTO.setGarden(gardenDTO);
                    return CompletableFuture.completedStage(sectionResponseDTO);
                });
    }

    public CompletionStage<SectionDTO> update(SectionDTO sectionDTO) {
        Section section = new Section();
        BeanUtils.copyProperties(sectionDTO, section);
        return gardenRepository.get(sectionDTO.getGarden().getId())
                .thenCompose(garden ->
                {
                    section.setGarden(garden);
                    return sectionRepository.update(section);
                })
                .thenCompose(sectionUpdateResponse -> {
                    SectionDTO sectionResponseDTO=new SectionDTO();
                    GardenDTO gardenDTO=new GardenDTO();
                    BeanUtils.copyProperties(sectionUpdateResponse, sectionResponseDTO);
                    BeanUtils.copyProperties(sectionUpdateResponse.getGarden(), gardenDTO);
                    sectionResponseDTO.setGarden(gardenDTO);
                    return CompletableFuture.completedStage(sectionResponseDTO);
                });
    }

    public CompletionStage<SectionDTO> get(Long id){
        return sectionRepository.get(id).thenApplyAsync(sectionResponse -> {
            SectionDTO sectionDTOResponse = new SectionDTO();
            GardenDTO gardenDTO=new GardenDTO();
            BeanUtils.copyProperties(sectionResponse.getGarden(), gardenDTO);
            BeanUtils.copyProperties(sectionResponse, sectionDTOResponse);
            sectionDTOResponse.setGarden(gardenDTO);
            return sectionDTOResponse;
        });
    }

    public CompletionStage delete(Long id){
        return sectionRepository.delete(id);
    }

    public CompletionStage<Stream<SectionDTO>> list() {
        return sectionRepository.list()
                .thenApplyAsync(sectionStream -> sectionStream.map(section -> {
                    SectionDTO sectionDTOResponse=new SectionDTO();
                    GardenDTO gardenDTO = new GardenDTO();
                    BeanUtils.copyProperties(section, sectionDTOResponse);
                    BeanUtils.copyProperties(section.getGarden(), gardenDTO);
                    sectionDTOResponse.setGarden(gardenDTO);
                    return sectionDTOResponse;
                })).toCompletableFuture();
    }
}
