package com.alexandrebfreitas.wue.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.alexandrebfreitas.wue.domain.OnsCargaEnergiaDiariaEntity;
import com.alexandrebfreitas.wue.repository.OnsCargaEnergiaDiariaRepository;
import java.util.stream.Stream;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * Spring Data Elasticsearch repository for the {@link OnsCargaEnergiaDiariaEntity} entity.
 */
public interface OnsCargaEnergiaDiariaSearchRepository
    extends ElasticsearchRepository<OnsCargaEnergiaDiariaEntity, Long>, OnsCargaEnergiaDiariaSearchRepositoryInternal {}

interface OnsCargaEnergiaDiariaSearchRepositoryInternal {
    Stream<OnsCargaEnergiaDiariaEntity> search(String query);

    Stream<OnsCargaEnergiaDiariaEntity> search(Query query);

    @Async
    void index(OnsCargaEnergiaDiariaEntity entity);

    @Async
    void deleteFromIndexById(Long id);
}

class OnsCargaEnergiaDiariaSearchRepositoryInternalImpl implements OnsCargaEnergiaDiariaSearchRepositoryInternal {

    private final ElasticsearchTemplate elasticsearchTemplate;
    private final OnsCargaEnergiaDiariaRepository repository;

    OnsCargaEnergiaDiariaSearchRepositoryInternalImpl(
        ElasticsearchTemplate elasticsearchTemplate,
        OnsCargaEnergiaDiariaRepository repository
    ) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Stream<OnsCargaEnergiaDiariaEntity> search(String query) {
        NativeQuery nativeQuery = new NativeQuery(QueryStringQuery.of(qs -> qs.query(query))._toQuery());
        return search(nativeQuery);
    }

    @Override
    public Stream<OnsCargaEnergiaDiariaEntity> search(Query query) {
        return elasticsearchTemplate.search(query, OnsCargaEnergiaDiariaEntity.class).map(SearchHit::getContent).stream();
    }

    @Override
    public void index(OnsCargaEnergiaDiariaEntity entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }

    @Override
    public void deleteFromIndexById(Long id) {
        elasticsearchTemplate.delete(String.valueOf(id), OnsCargaEnergiaDiariaEntity.class);
    }
}
