package org.burrosoft.friede.service;

import com.google.inject.Inject;
import org.burrosoft.friede.dto.SectionDTO;
import org.burrosoft.friede.dto.SensorDTO;
import org.burrosoft.friede.models.section.Section;
import org.burrosoft.friede.models.section.SectionRepository;
import org.burrosoft.friede.models.sensor.Sensor;
import org.burrosoft.friede.models.sensor.SensorRepository;
import org.springframework.beans.BeanUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

public class SensorService {

    private SectionRepository<Section> sectionRepository;
    private SensorRepository<Sensor> sensorRepository;

    @Inject
    public SensorService(SensorRepository<Sensor> sensorRepository, SectionRepository<Section> sectionRepository) {
        this.sectionRepository = sectionRepository;
        this.sensorRepository = sensorRepository;
    }

    public CompletionStage<SensorDTO> insert(SensorDTO sensorDTO) {
        Sensor sensor = new Sensor();
        BeanUtils.copyProperties(sensorDTO, sensor);
        return sectionRepository.get(sensorDTO.getSection().getId())
                .thenCompose(section ->
                {
                    sensor.setSection(section);
                    return sensorRepository.insert(sensor);
                })
                .thenCompose(sensorInsertResponse -> {
                    SensorDTO sensorResponseDTO = new SensorDTO();
                    SectionDTO sectionDTO = new SectionDTO();
                    BeanUtils.copyProperties(sensorInsertResponse, sensorResponseDTO);
                    BeanUtils.copyProperties(sensorInsertResponse.getSection(), sectionDTO);
                    sensorResponseDTO.setSection(sectionDTO);
                    return CompletableFuture.completedStage(sensorResponseDTO);
                });
    }

    public CompletionStage<SensorDTO> update(SensorDTO sensorDTO) {
        Sensor sensor = new Sensor();
        BeanUtils.copyProperties(sensorDTO, sensor);
        return sectionRepository.get(sensorDTO.getSection().getId())
                .thenCompose(section ->
                {
                    sensor.setSection(section);
                    return sensorRepository.update(sensor);
                })
                .thenCompose(sensorUpdateResponse -> {
                    SensorDTO sensorResponseDTO = new SensorDTO();
                    SectionDTO sectionDTO = new SectionDTO();
                    BeanUtils.copyProperties(sensorUpdateResponse, sensorResponseDTO);
                    BeanUtils.copyProperties(sensorUpdateResponse.getSection(), sectionDTO);
                    sensorResponseDTO.setSection(sectionDTO);
                    return CompletableFuture.completedStage(sensorResponseDTO);
                });
    }

    public CompletionStage<SensorDTO> get(Long id) {
        return sensorRepository.get(id).thenApplyAsync(sensorResponse -> {
            SensorDTO sectionDTOResponse = new SensorDTO();
            SectionDTO sectionDTO = new SectionDTO();
            BeanUtils.copyProperties(sensorResponse.getSection(), sectionDTO);
            BeanUtils.copyProperties(sensorResponse, sectionDTOResponse);
            sectionDTOResponse.setSection(sectionDTO);
            return sectionDTOResponse;
        });
    }

    public CompletionStage delete(Long id) {
        return sensorRepository.delete(id);
    }

    public CompletionStage<Stream<SensorDTO>> list() {
        return sensorRepository.list()
                .thenApplyAsync(sensorStream -> sensorStream.map(sensor -> {
                    SensorDTO sensorDTOResponse = new SensorDTO();
                    SectionDTO sectionDTO = new SectionDTO();
                    BeanUtils.copyProperties(sensor, sensorDTOResponse);
                    BeanUtils.copyProperties(sensor.getSection(), sectionDTO);
                    sensorDTOResponse.setSection(sectionDTO);
                    return sensorDTOResponse;
                })).toCompletableFuture();
    }
}