package org.burrosoft.friede.service;

import com.google.inject.Inject;
import org.burrosoft.friede.dto.GardenDTO;
import org.burrosoft.friede.models.garden.Garden;
import org.burrosoft.friede.models.garden.GardenRepository;
import org.springframework.beans.BeanUtils;

import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

public class GardenService {
    private GardenRepository<Garden> gardenRepository;

    @Inject
    public GardenService(GardenRepository<Garden> gardenRepository) {
        this.gardenRepository = gardenRepository;
    }

    public CompletionStage<GardenDTO> insert(GardenDTO gardenDTO) {
        Garden garden = new Garden();
        BeanUtils.copyProperties(gardenDTO, garden);
        return gardenRepository.insert(garden)
                .thenApplyAsync(gardenResponse -> {
                    GardenDTO gardenDTOResponse = new GardenDTO();
                    BeanUtils.copyProperties(gardenResponse, gardenDTOResponse);
                    return gardenDTOResponse;
                });
    }

    public CompletionStage<GardenDTO> update(GardenDTO gardenDTO) {
        Garden garden=new Garden();
        BeanUtils.copyProperties(gardenDTO, garden);
        return gardenRepository.update(garden)
                .thenApplyAsync(gardenResponse -> {
                    GardenDTO gardenDTOResponse = new GardenDTO();
                    BeanUtils.copyProperties(gardenResponse, gardenDTOResponse);
                    return gardenDTOResponse;
                });
    }

    public CompletionStage<GardenDTO> get(Long id) {
        return gardenRepository.get(id)
                .thenApplyAsync(gardenResponse -> {
                    GardenDTO gardenDTOResponse = new GardenDTO();
                    BeanUtils.copyProperties(gardenResponse, gardenDTOResponse);
                    return gardenDTOResponse;
                });
    }

    public CompletionStage delete(Long id) {
        return gardenRepository.delete(id);
    }

    public CompletionStage<Stream<GardenDTO>> list() {
        return gardenRepository.list()
                .thenApplyAsync(gardenStream -> gardenStream.map(garden -> {
                    GardenDTO gardenDTO = new GardenDTO();
                    BeanUtils.copyProperties(garden, gardenDTO);
                    return gardenDTO;
                }));
    }
}
