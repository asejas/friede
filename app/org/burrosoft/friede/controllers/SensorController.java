package org.burrosoft.friede.controllers;

import com.google.inject.Inject;
import org.burrosoft.friede.dto.SensorDTO;
import org.burrosoft.friede.service.SensorService;
import org.burrosoft.friede.util.ResponseUtil;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;

import java.util.concurrent.CompletionStage;

import static play.libs.Json.toJson;
import static play.mvc.Results.internalServerError;
import static play.mvc.Results.ok;

public class SensorController {
    private SensorService sensorService;

    @Inject
    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    public CompletionStage<Result> create(Http.Request request) {
        SensorDTO sensorDTO = Json.fromJson(request.body().asJson(), SensorDTO.class);
        return sensorService
                .insert(sensorDTO)
                .thenApplyAsync(p -> ok(toJson(p)))
                .exceptionally(throwable ->
                        internalServerError(
                                ResponseUtil.createResponse("Sensor could not be created", false)));
    }

    public CompletionStage<Result> update(Http.Request request) {
        SensorDTO sensorDTO = Json.fromJson(request.body().asJson(), SensorDTO.class);
        return sensorService
                .update(sensorDTO)
                .thenApplyAsync(p -> ok(toJson(sensorDTO)))
                .exceptionally(throwable ->
                        internalServerError(
                                ResponseUtil.createResponse("Sensor could not be updated", false)));
    }

    public CompletionStage<Result> delete(Long id) {
        return sensorService.delete(id)
                .thenApplyAsync(o -> ok())
                .exceptionally(throwable ->
                        internalServerError(
                                ResponseUtil.createResponse("Sensor could not be deleted", false)));
    }

    public CompletionStage<Result> get(Long id) {
        return sensorService.get(id)
                .thenApplyAsync(p -> ok(toJson(p)))
                .exceptionally(throwable ->
                        internalServerError(
                                ResponseUtil.createResponse("Sensor could not be loaded", false)));
    }

    public CompletionStage<Result> listSensors() {
        return sensorService
                .list()
                .thenApplyAsync(p -> ok(toJson(p)))
                .exceptionally(throwable ->
                        internalServerError(
                                ResponseUtil.createResponse("Sensor list could not be retrieved",false)));
    }
}