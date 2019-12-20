package org.burrosoft.friede.controllers;

import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Result;
import org.burrosoft.friede.util.ResponseUtil;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import static play.mvc.Results.ok;

public class HealthController {
    private HttpExecutionContext ec;

    @Inject
    public HealthController(HttpExecutionContext ec) {
        this.ec = ec;
    }

    public CompletionStage<Result> health(){
        return supplyAsync(() -> ok(ResponseUtil.createResponse("I'm alive!",true)));
    }
}
