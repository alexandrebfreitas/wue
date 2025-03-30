package com.alexandrebfreitas.wue.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.alexandrebfreitas.wue.domain.OnsTaxasTeifaETeipEntity;
import com.alexandrebfreitas.wue.repository.OnsTaxasTeifaETeipRepository;
import java.util.stream.Stream;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * Spring Data Elasticsearch repository for the {@link OnsTaxasTeifaETeipEntity} entity.
 */
public interface OnsTaxasTeifaETeipSearchRepository
    extends ElasticsearchRepository<OnsTaxasTeifaETeipEntity, Long>, OnsTaxasTeifaETeipSearchRepositoryInternal {}

interface OnsTaxasTeifaETeipSearchRepositoryInternal {
    Stream<OnsTaxasTeifaETeipEntity> search(String query);

    Stream<OnsTaxasTeifaETeipEntity> search(Query query);

    @Async
    void index(OnsTaxasTeifaETeipEntity entity);

    @Async
    void deleteFromIndexById(Long id);
}

class OnsTaxasTeifaETeipSearchRepositoryInternalImpl implements OnsTaxasTeifaETeipSearchRepositoryInternal {

    private final ElasticsearchTemplate elasticsearchTemplate;
    private final OnsTaxasTeifaETeipRepository repository;

    OnsTaxasTeifaETeipSearchRepositoryInternalImpl(ElasticsearchTemplate elasticsearchTemplate, OnsTaxasTeifaETeipRepository repository) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Stream<OnsTaxasTeifaETeipEntity> search(String query) {
        NativeQuery nativeQuery = new NativeQuery(QueryStringQuery.of(qs -> qs.query(query))._toQuery());
        return search(nativeQuery);
    }

    @Override
    public Stream<OnsTaxasTeifaETeipEntity> search(Query query) {
        return elasticsearchTemplate.search(query, OnsTaxasTeifaETeipEntity.class).map(SearchHit::getContent).stream();
    }

    @Override
    public void index(OnsTaxasTeifaETeipEntity entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }

    @Override
    public void deleteFromIndexById(Long id) {
        elasticsearchTemplate.delete(String.valueOf(id), OnsTaxasTeifaETeipEntity.class);
    }
}
