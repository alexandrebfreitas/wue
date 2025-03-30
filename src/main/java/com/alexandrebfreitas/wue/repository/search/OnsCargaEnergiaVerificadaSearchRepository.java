package com.alexandrebfreitas.wue.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.alexandrebfreitas.wue.domain.OnsCargaEnergiaVerificadaEntity;
import com.alexandrebfreitas.wue.repository.OnsCargaEnergiaVerificadaRepository;
import java.util.stream.Stream;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * Spring Data Elasticsearch repository for the {@link OnsCargaEnergiaVerificadaEntity} entity.
 */
public interface OnsCargaEnergiaVerificadaSearchRepository
    extends ElasticsearchRepository<OnsCargaEnergiaVerificadaEntity, Long>, OnsCargaEnergiaVerificadaSearchRepositoryInternal {}

interface OnsCargaEnergiaVerificadaSearchRepositoryInternal {
    Stream<OnsCargaEnergiaVerificadaEntity> search(String query);

    Stream<OnsCargaEnergiaVerificadaEntity> search(Query query);

    @Async
    void index(OnsCargaEnergiaVerificadaEntity entity);

    @Async
    void deleteFromIndexById(Long id);
}

class OnsCargaEnergiaVerificadaSearchRepositoryInternalImpl implements OnsCargaEnergiaVerificadaSearchRepositoryInternal {

    private final ElasticsearchTemplate elasticsearchTemplate;
    private final OnsCargaEnergiaVerificadaRepository repository;

    OnsCargaEnergiaVerificadaSearchRepositoryInternalImpl(
        ElasticsearchTemplate elasticsearchTemplate,
        OnsCargaEnergiaVerificadaRepository repository
    ) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Stream<OnsCargaEnergiaVerificadaEntity> search(String query) {
        NativeQuery nativeQuery = new NativeQuery(QueryStringQuery.of(qs -> qs.query(query))._toQuery());
        return search(nativeQuery);
    }

    @Override
    public Stream<OnsCargaEnergiaVerificadaEntity> search(Query query) {
        return elasticsearchTemplate.search(query, OnsCargaEnergiaVerificadaEntity.class).map(SearchHit::getContent).stream();
    }

    @Override
    public void index(OnsCargaEnergiaVerificadaEntity entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }

    @Override
    public void deleteFromIndexById(Long id) {
        elasticsearchTemplate.delete(String.valueOf(id), OnsCargaEnergiaVerificadaEntity.class);
    }
}
