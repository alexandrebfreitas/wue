package com.alexandrebfreitas.wue.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.alexandrebfreitas.wue.domain.OnsCmoSemihorarioEntity;
import com.alexandrebfreitas.wue.repository.OnsCmoSemihorarioRepository;
import java.util.stream.Stream;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * Spring Data Elasticsearch repository for the {@link OnsCmoSemihorarioEntity} entity.
 */
public interface OnsCmoSemihorarioSearchRepository
    extends ElasticsearchRepository<OnsCmoSemihorarioEntity, Long>, OnsCmoSemihorarioSearchRepositoryInternal {}

interface OnsCmoSemihorarioSearchRepositoryInternal {
    Stream<OnsCmoSemihorarioEntity> search(String query);

    Stream<OnsCmoSemihorarioEntity> search(Query query);

    @Async
    void index(OnsCmoSemihorarioEntity entity);

    @Async
    void deleteFromIndexById(Long id);
}

class OnsCmoSemihorarioSearchRepositoryInternalImpl implements OnsCmoSemihorarioSearchRepositoryInternal {

    private final ElasticsearchTemplate elasticsearchTemplate;
    private final OnsCmoSemihorarioRepository repository;

    OnsCmoSemihorarioSearchRepositoryInternalImpl(ElasticsearchTemplate elasticsearchTemplate, OnsCmoSemihorarioRepository repository) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Stream<OnsCmoSemihorarioEntity> search(String query) {
        NativeQuery nativeQuery = new NativeQuery(QueryStringQuery.of(qs -> qs.query(query))._toQuery());
        return search(nativeQuery);
    }

    @Override
    public Stream<OnsCmoSemihorarioEntity> search(Query query) {
        return elasticsearchTemplate.search(query, OnsCmoSemihorarioEntity.class).map(SearchHit::getContent).stream();
    }

    @Override
    public void index(OnsCmoSemihorarioEntity entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }

    @Override
    public void deleteFromIndexById(Long id) {
        elasticsearchTemplate.delete(String.valueOf(id), OnsCmoSemihorarioEntity.class);
    }
}
