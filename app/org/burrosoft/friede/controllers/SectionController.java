package org.burrosoft.friede.controllers;

import org.burrosoft.friede.dto.SectionDTO;
import org.burrosoft.friede.models.section.Section;
import org.burrosoft.friede.service.SectionService;
import org.burrosoft.friede.util.ResponseUtil;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

import static play.libs.Json.toJson;
import static play.mvc.Results.internalServerError;
import static play.mvc.Results.ok;

public class SectionController {
    private SectionService sectionService;

    @Inject
    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    public CompletionStage<Result> create(Http.Request request) {
        SectionDTO sectionDTO = Json.fromJson(request.body().asJson(), SectionDTO.class);
        return sectionService
                .insert(sectionDTO)
                .thenApplyAsync(p -> ok(toJson(p)))
                .exceptionally(throwable ->
                        internalServerError(
                                ResponseUtil.createResponse("Section could not be created", false)));
    }

    public CompletionStage<Result> update(Http.Request request) {
        SectionDTO sectionDTO = Json.fromJson(request.body().asJson(), SectionDTO.class);
        return sectionService
                .update(sectionDTO)
                .thenApplyAsync(p -> ok(toJson(sectionDTO)))
                .exceptionally(throwable ->
                        internalServerError(
                                ResponseUtil.createResponse("Section could not be updated", false)));
    }

    public CompletionStage<Result> delete(Long id) {
        return sectionService.delete(id)
                .thenApplyAsync(o -> ok())
                .exceptionally(throwable ->
                        internalServerError(
                                ResponseUtil.createResponse("Section could not be deleted", false)));
    }

    public CompletionStage<Result> get(Long id) {
        return sectionService.get(id)
                .thenApplyAsync(p -> ok(toJson(p)))
                .exceptionally(throwable ->
                        internalServerError(
                                ResponseUtil.createResponse("Section could not be loaded", false)));
    }

    public CompletionStage<Result> listSections() {
        return sectionService
                .list()
                .thenApplyAsync(p -> ok(toJson(p)))
                .exceptionally(throwable ->
                        internalServerError(
                                ResponseUtil.createResponse("Section list could not be retrieved",false)));
    }
}
