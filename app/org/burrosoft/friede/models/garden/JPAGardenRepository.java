package org.burrosoft.friede.models.garden;

import org.burrosoft.friede.models.DatabaseExecutionContext;
import org.burrosoft.friede.models.generic.JPAEntityRepository;
import play.db.jpa.JPAApi;

import javax.inject.Inject;

public class JPAGardenRepository extends JPAEntityRepository<Garden> implements GardenRepository<Garden>{

    @Inject
    public JPAGardenRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        super(jpaApi, executionContext);
    }

    @Override
    public Class getEntityClass(){
        return Garden.class;
    }
}
