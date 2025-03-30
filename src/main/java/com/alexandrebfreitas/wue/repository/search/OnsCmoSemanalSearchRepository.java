package com.alexandrebfreitas.wue.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.alexandrebfreitas.wue.domain.OnsCmoSemanalEntity;
import com.alexandrebfreitas.wue.repository.OnsCmoSemanalRepository;
import java.util.stream.Stream;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * Spring Data Elasticsearch repository for the {@link OnsCmoSemanalEntity} entity.
 */
public interface OnsCmoSemanalSearchRepository
    extends ElasticsearchRepository<OnsCmoSemanalEntity, Long>, OnsCmoSemanalSearchRepositoryInternal {}

interface OnsCmoSemanalSearchRepositoryInternal {
    Stream<OnsCmoSemanalEntity> search(String query);

    Stream<OnsCmoSemanalEntity> search(Query query);

    @Async
    void index(OnsCmoSemanalEntity entity);

    @Async
    void deleteFromIndexById(Long id);
}

class OnsCmoSemanalSearchRepositoryInternalImpl implements OnsCmoSemanalSearchRepositoryInternal {

    private final ElasticsearchTemplate elasticsearchTemplate;
    private final OnsCmoSemanalRepository repository;

    OnsCmoSemanalSearchRepositoryInternalImpl(ElasticsearchTemplate elasticsearchTemplate, OnsCmoSemanalRepository repository) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Stream<OnsCmoSemanalEntity> search(String query) {
        NativeQuery nativeQuery = new NativeQuery(QueryStringQuery.of(qs -> qs.query(query))._toQuery());
        return search(nativeQuery);
    }

    @Override
    public Stream<OnsCmoSemanalEntity> search(Query query) {
        return elasticsearchTemplate.search(query, OnsCmoSemanalEntity.class).map(SearchHit::getContent).stream();
    }

    @Override
    public void index(OnsCmoSemanalEntity entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }

    @Override
    public void deleteFromIndexById(Long id) {
        elasticsearchTemplate.delete(String.valueOf(id), OnsCmoSemanalEntity.class);
    }
}
