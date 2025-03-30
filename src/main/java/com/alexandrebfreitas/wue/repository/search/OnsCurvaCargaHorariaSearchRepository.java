package com.alexandrebfreitas.wue.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.alexandrebfreitas.wue.domain.OnsCurvaCargaHorariaEntity;
import com.alexandrebfreitas.wue.repository.OnsCurvaCargaHorariaRepository;
import java.util.stream.Stream;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * Spring Data Elasticsearch repository for the {@link OnsCurvaCargaHorariaEntity} entity.
 */
public interface OnsCurvaCargaHorariaSearchRepository
    extends ElasticsearchRepository<OnsCurvaCargaHorariaEntity, Long>, OnsCurvaCargaHorariaSearchRepositoryInternal {}

interface OnsCurvaCargaHorariaSearchRepositoryInternal {
    Stream<OnsCurvaCargaHorariaEntity> search(String query);

    Stream<OnsCurvaCargaHorariaEntity> search(Query query);

    @Async
    void index(OnsCurvaCargaHorariaEntity entity);

    @Async
    void deleteFromIndexById(Long id);
}

class OnsCurvaCargaHorariaSearchRepositoryInternalImpl implements OnsCurvaCargaHorariaSearchRepositoryInternal {

    private final ElasticsearchTemplate elasticsearchTemplate;
    private final OnsCurvaCargaHorariaRepository repository;

    OnsCurvaCargaHorariaSearchRepositoryInternalImpl(
        ElasticsearchTemplate elasticsearchTemplate,
        OnsCurvaCargaHorariaRepository repository
    ) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Stream<OnsCurvaCargaHorariaEntity> search(String query) {
        NativeQuery nativeQuery = new NativeQuery(QueryStringQuery.of(qs -> qs.query(query))._toQuery());
        return search(nativeQuery);
    }

    @Override
    public Stream<OnsCurvaCargaHorariaEntity> search(Query query) {
        return elasticsearchTemplate.search(query, OnsCurvaCargaHorariaEntity.class).map(SearchHit::getContent).stream();
    }

    @Override
    public void index(OnsCurvaCargaHorariaEntity entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }

    @Override
    public void deleteFromIndexById(Long id) {
        elasticsearchTemplate.delete(String.valueOf(id), OnsCurvaCargaHorariaEntity.class);
    }
}
