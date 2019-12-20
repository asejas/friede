package org.burrosoft.friede.controllers;

import org.burrosoft.friede.dto.GardenDTO;
import org.burrosoft.friede.service.GardenService;
import org.burrosoft.friede.util.ResponseUtil;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

import static play.libs.Json.toJson;
import static play.mvc.Results.internalServerError;
import static play.mvc.Results.ok;

public class GardenController {
    private GardenService gardenService;

    @Inject
    public GardenController(GardenService gardenService) {
        this.gardenService = gardenService;
    }

    public CompletionStage<Result> create(Http.Request request) {
        GardenDTO gardenDTO = Json.fromJson(request.body().asJson(),GardenDTO.class);
        return gardenService
                .insert(gardenDTO)
                .thenApplyAsync(p -> ok(toJson(p)))
                .exceptionally(throwable ->
                        internalServerError(
                                ResponseUtil.createResponse("Garden could not be created",false)));
    }

    public CompletionStage<Result> update(Http.Request request) {
        GardenDTO gardenDTO = Json.fromJson(request.body().asJson(),GardenDTO.class);
        return gardenService
                .update(gardenDTO)
                .thenApplyAsync(p -> ok(toJson(gardenDTO)))
                .exceptionally(throwable ->
                        internalServerError(
                                ResponseUtil.createResponse("Garden could not be updated",false)));
    }

    public CompletionStage<Result> delete(Long id) {
        return gardenService.delete(id)
                    .thenApplyAsync(o -> ok())
                    .exceptionally(throwable ->
                            internalServerError(
                                    ResponseUtil.createResponse("Garden could not be deleted",false)));
    }

    public CompletionStage<Result> get(Long id) {
        return gardenService.get(id)
                .thenApplyAsync(p -> ok(toJson(p)))
                .exceptionally(throwable ->
                        internalServerError(
                                ResponseUtil.createResponse("Garden could not be loaded",false)));
    }

    public CompletionStage<Result> listGardens() {
        return gardenService
                .list()
                .thenApplyAsync(p -> ok(toJson(p)))
                .exceptionally(throwable ->
                        internalServerError(
                                ResponseUtil.createResponse("Garden list could not be retrieved",false)));
    }
}
