package org.burrosoft.models;

import org.burrosoft.friede.models.garden.Garden;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import play.libs.concurrent.HttpExecutionContext;
import play.test.WithApplication;

import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

public class GardenRepositoryTest extends WithApplication {
    private HttpExecutionContext ec;
    private GardenRepository gardenRepository;

    @Before
    public void setup(){
        this.gardenRepository = app.injector().instanceOf(GardenRepository.class);
    }

    @Test
    public void testListGardenSuccess(){
        System.out.println("AaaaAAAAAAAAAAAAAAAAAAA*******************");
        CompletionStage<Stream<Garden>> res=this.gardenRepository.list();
        Assert.assertNotNull(res);
        Assert.assertTrue(false);
    }
}
