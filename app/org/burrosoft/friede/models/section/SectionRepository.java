package org.burrosoft.friede.models.section;

import com.google.inject.ImplementedBy;
import org.burrosoft.friede.models.generic.EntityRepository;

@ImplementedBy(JPASectionRepository.class)
public interface SectionRepository<Section> extends EntityRepository<Section> {
}
