package org.burrosoft.friede.models.generic;

import org.burrosoft.friede.models.DatabaseExecutionContext;
import play.db.jpa.JPAApi;

import javax.persistence.EntityManager;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.runAsync;
import static java.util.concurrent.CompletableFuture.supplyAsync;

public abstract class JPAEntityRepository<T extends IdentifiableEntity> implements EntityRepository<T> {

    private final JPAApi jpaApi;
    private final DatabaseExecutionContext executionContext;

    public JPAEntityRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        this.jpaApi = jpaApi;
        this.executionContext = executionContext;
    }

    public abstract Class getEntityClass();

    @Override
    public CompletionStage<T> insert(T entity) {
        return supplyAsync(() -> this.wrapFunction(em -> this.insertEntity(em, entity)), executionContext);
    }

    @Override
    public CompletionStage<T> update(T entity) {
        return supplyAsync(() -> this.wrapFunction(em -> this.updateEntity(em, entity)), executionContext);
    }

    @Override
    public CompletionStage delete(Long id) {
        return runAsync(() -> this.wrapConsumer(em -> this.deleteEntity(em, id)), executionContext);
    }

    @Override
    public CompletionStage<Stream<T>> list() {
        return supplyAsync(() -> this.wrapFunction(this::listEntity), executionContext);
    }

    @Override
    public CompletionStage<T> get(Long id) {
        return supplyAsync(() -> this.wrapFunction(em -> this.getEntity(em, id)), executionContext);
    }

    private <S> S wrapFunction(Function<EntityManager, S> function) {
        return jpaApi.withTransaction(function);
    }

    private void wrapConsumer(Consumer<EntityManager> consumer) {
        jpaApi.withTransaction(consumer);
    }

    private T insertEntity(EntityManager em, T section) {
        em.persist(section);
        return section;
    }

    private Stream<T> listEntity(EntityManager em) {
        String query = String.format("select e from %s e", getEntityClass().getSimpleName());
        return em.createQuery(
                query,
                getEntityClass())
                .getResultList().stream();
    }

    private T updateEntity(EntityManager em, T entity) {
        return em.merge(entity);
    }

    private void deleteEntity(EntityManager em, Long id) {
        T objToRemove=getEntity(em, id);
        if(objToRemove!=null) {
            em.remove(objToRemove);
        }
    }

    private T getEntity(EntityManager em, Long id) {
        return em.find((Class<T>) this.getEntityClass(), id);
    }

    protected JPAApi getJpaApi(){
        return jpaApi;
    }
}
