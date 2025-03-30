package com.alexandrebfreitas.wue.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.alexandrebfreitas.wue.domain.OnsCvuUsinaTermicasEntity;
import com.alexandrebfreitas.wue.repository.OnsCvuUsinaTermicasRepository;
import java.util.stream.Stream;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * Spring Data Elasticsearch repository for the {@link OnsCvuUsinaTermicasEntity} entity.
 */
public interface OnsCvuUsinaTermicasSearchRepository
    extends ElasticsearchRepository<OnsCvuUsinaTermicasEntity, Long>, OnsCvuUsinaTermicasSearchRepositoryInternal {}

interface OnsCvuUsinaTermicasSearchRepositoryInternal {
    Stream<OnsCvuUsinaTermicasEntity> search(String query);

    Stream<OnsCvuUsinaTermicasEntity> search(Query query);

    @Async
    void index(OnsCvuUsinaTermicasEntity entity);

    @Async
    void deleteFromIndexById(Long id);
}

class OnsCvuUsinaTermicasSearchRepositoryInternalImpl implements OnsCvuUsinaTermicasSearchRepositoryInternal {

    private final ElasticsearchTemplate elasticsearchTemplate;
    private final OnsCvuUsinaTermicasRepository repository;

    OnsCvuUsinaTermicasSearchRepositoryInternalImpl(ElasticsearchTemplate elasticsearchTemplate, OnsCvuUsinaTermicasRepository repository) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Stream<OnsCvuUsinaTermicasEntity> search(String query) {
        NativeQuery nativeQuery = new NativeQuery(QueryStringQuery.of(qs -> qs.query(query))._toQuery());
        return search(nativeQuery);
    }

    @Override
    public Stream<OnsCvuUsinaTermicasEntity> search(Query query) {
        return elasticsearchTemplate.search(query, OnsCvuUsinaTermicasEntity.class).map(SearchHit::getContent).stream();
    }

    @Override
    public void index(OnsCvuUsinaTermicasEntity entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }

    @Override
    public void deleteFromIndexById(Long id) {
        elasticsearchTemplate.delete(String.valueOf(id), OnsCvuUsinaTermicasEntity.class);
    }
}
