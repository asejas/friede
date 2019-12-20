package org.burrosoft.friede.models.garden;

import com.google.inject.ImplementedBy;
import org.burrosoft.friede.models.generic.EntityRepository;

@ImplementedBy(JPAGardenRepository.class)
public interface GardenRepository<Garden> extends EntityRepository<Garden> {
}
