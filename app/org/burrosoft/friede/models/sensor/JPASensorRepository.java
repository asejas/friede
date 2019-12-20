package org.burrosoft.friede.models.sensor;

import org.burrosoft.friede.models.DatabaseExecutionContext;
import org.burrosoft.friede.models.generic.JPAEntityRepository;
import org.burrosoft.friede.models.section.Section;
import play.db.jpa.JPAApi;

import javax.inject.Inject;

public class JPASensorRepository extends JPAEntityRepository<Sensor> implements SensorRepository<Sensor> {

    @Inject
    public JPASensorRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        super(jpaApi, executionContext);
    }

    @Override
    public Class getEntityClass() {
        return Sensor.class;
    }
}