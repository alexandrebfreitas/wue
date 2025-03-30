package com.alexandrebfreitas.wue.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.alexandrebfreitas.wue.domain.OnsFatorCapacidadeGeracaoEolicaESolarEntity;
import com.alexandrebfreitas.wue.repository.OnsFatorCapacidadeGeracaoEolicaESolarRepository;
import java.util.stream.Stream;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * Spring Data Elasticsearch repository for the {@link OnsFatorCapacidadeGeracaoEolicaESolarEntity} entity.
 */
public interface OnsFatorCapacidadeGeracaoEolicaESolarSearchRepository
    extends
        ElasticsearchRepository<OnsFatorCapacidadeGeracaoEolicaESolarEntity, Long>,
        OnsFatorCapacidadeGeracaoEolicaESolarSearchRepositoryInternal {}

interface OnsFatorCapacidadeGeracaoEolicaESolarSearchRepositoryInternal {
    Stream<OnsFatorCapacidadeGeracaoEolicaESolarEntity> search(String query);

    Stream<OnsFatorCapacidadeGeracaoEolicaESolarEntity> search(Query query);

    @Async
    void index(OnsFatorCapacidadeGeracaoEolicaESolarEntity entity);

    @Async
    void deleteFromIndexById(Long id);
}

class OnsFatorCapacidadeGeracaoEolicaESolarSearchRepositoryInternalImpl
    implements OnsFatorCapacidadeGeracaoEolicaESolarSearchRepositoryInternal {

    private final ElasticsearchTemplate elasticsearchTemplate;
    private final OnsFatorCapacidadeGeracaoEolicaESolarRepository repository;

    OnsFatorCapacidadeGeracaoEolicaESolarSearchRepositoryInternalImpl(
        ElasticsearchTemplate elasticsearchTemplate,
        OnsFatorCapacidadeGeracaoEolicaESolarRepository repository
    ) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Stream<OnsFatorCapacidadeGeracaoEolicaESolarEntity> search(String query) {
        NativeQuery nativeQuery = new NativeQuery(QueryStringQuery.of(qs -> qs.query(query))._toQuery());
        return search(nativeQuery);
    }

    @Override
    public Stream<OnsFatorCapacidadeGeracaoEolicaESolarEntity> search(Query query) {
        return elasticsearchTemplate.search(query, OnsFatorCapacidadeGeracaoEolicaESolarEntity.class).map(SearchHit::getContent).stream();
    }

    @Override
    public void index(OnsFatorCapacidadeGeracaoEolicaESolarEntity entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }

    @Override
    public void deleteFromIndexById(Long id) {
        elasticsearchTemplate.delete(String.valueOf(id), OnsFatorCapacidadeGeracaoEolicaESolarEntity.class);
    }
}
