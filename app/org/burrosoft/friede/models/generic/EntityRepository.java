package org.burrosoft.friede.models.generic;

import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

public interface EntityRepository<T> {

    CompletionStage<Stream<T>> list();

    CompletionStage<T> insert(T entity);

    CompletionStage<T> update(T entity);

    CompletionStage delete(Long id);

    CompletionStage<T> get(Long id);
}
