package com.alexandrebfreitas.wue.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.alexandrebfreitas.wue.domain.OnsCargaEnergiaProgramadaEntity;
import com.alexandrebfreitas.wue.repository.OnsCargaEnergiaProgramadaRepository;
import java.util.stream.Stream;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * Spring Data Elasticsearch repository for the {@link OnsCargaEnergiaProgramadaEntity} entity.
 */
public interface OnsCargaEnergiaProgramadaSearchRepository
    extends ElasticsearchRepository<OnsCargaEnergiaProgramadaEntity, Long>, OnsCargaEnergiaProgramadaSearchRepositoryInternal {}

interface OnsCargaEnergiaProgramadaSearchRepositoryInternal {
    Stream<OnsCargaEnergiaProgramadaEntity> search(String query);

    Stream<OnsCargaEnergiaProgramadaEntity> search(Query query);

    @Async
    void index(OnsCargaEnergiaProgramadaEntity entity);

    @Async
    void deleteFromIndexById(Long id);
}

class OnsCargaEnergiaProgramadaSearchRepositoryInternalImpl implements OnsCargaEnergiaProgramadaSearchRepositoryInternal {

    private final ElasticsearchTemplate elasticsearchTemplate;
    private final OnsCargaEnergiaProgramadaRepository repository;

    OnsCargaEnergiaProgramadaSearchRepositoryInternalImpl(
        ElasticsearchTemplate elasticsearchTemplate,
        OnsCargaEnergiaProgramadaRepository repository
    ) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Stream<OnsCargaEnergiaProgramadaEntity> search(String query) {
        NativeQuery nativeQuery = new NativeQuery(QueryStringQuery.of(qs -> qs.query(query))._toQuery());
        return search(nativeQuery);
    }

    @Override
    public Stream<OnsCargaEnergiaProgramadaEntity> search(Query query) {
        return elasticsearchTemplate.search(query, OnsCargaEnergiaProgramadaEntity.class).map(SearchHit::getContent).stream();
    }

    @Override
    public void index(OnsCargaEnergiaProgramadaEntity entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }

    @Override
    public void deleteFromIndexById(Long id) {
        elasticsearchTemplate.delete(String.valueOf(id), OnsCargaEnergiaProgramadaEntity.class);
    }
}
