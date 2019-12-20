package org.burrosoft.friede.models.section;

import org.burrosoft.friede.models.DatabaseExecutionContext;
import org.burrosoft.friede.models.generic.JPAEntityRepository;
import play.db.jpa.JPAApi;

import javax.inject.Inject;

public class JPASectionRepository extends JPAEntityRepository<Section> implements SectionRepository<Section>{

    @Inject
    public JPASectionRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        super(jpaApi, executionContext);
    }

    @Override
    public Class getEntityClass(){
        return Section.class;
    }
}
