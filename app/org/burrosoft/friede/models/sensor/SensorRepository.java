package org.burrosoft.friede.models.sensor;

import com.google.inject.ImplementedBy;
import org.burrosoft.friede.models.generic.EntityRepository;

@ImplementedBy(JPASensorRepository.class)
public interface SensorRepository<Sensor> extends EntityRepository<Sensor> {
}
