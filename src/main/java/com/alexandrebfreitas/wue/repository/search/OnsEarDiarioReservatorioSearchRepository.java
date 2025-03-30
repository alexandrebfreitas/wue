package com.alexandrebfreitas.wue.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.alexandrebfreitas.wue.domain.OnsEarDiarioReservatorioEntity;
import com.alexandrebfreitas.wue.repository.OnsEarDiarioReservatorioRepository;
import java.util.stream.Stream;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * Spring Data Elasticsearch repository for the {@link OnsEarDiarioReservatorioEntity} entity.
 */
public interface OnsEarDiarioReservatorioSearchRepository
    extends ElasticsearchRepository<OnsEarDiarioReservatorioEntity, Long>, OnsEarDiarioReservatorioSearchRepositoryInternal {}

interface OnsEarDiarioReservatorioSearchRepositoryInternal {
    Stream<OnsEarDiarioReservatorioEntity> search(String query);

    Stream<OnsEarDiarioReservatorioEntity> search(Query query);

    @Async
    void index(OnsEarDiarioReservatorioEntity entity);

    @Async
    void deleteFromIndexById(Long id);
}

class OnsEarDiarioReservatorioSearchRepositoryInternalImpl implements OnsEarDiarioReservatorioSearchRepositoryInternal {

    private final ElasticsearchTemplate elasticsearchTemplate;
    private final OnsEarDiarioReservatorioRepository repository;

    OnsEarDiarioReservatorioSearchRepositoryInternalImpl(
        ElasticsearchTemplate elasticsearchTemplate,
        OnsEarDiarioReservatorioRepository repository
    ) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Stream<OnsEarDiarioReservatorioEntity> search(String query) {
        NativeQuery nativeQuery = new NativeQuery(QueryStringQuery.of(qs -> qs.query(query))._toQuery());
        return search(nativeQuery);
    }

    @Override
    public Stream<OnsEarDiarioReservatorioEntity> search(Query query) {
        return elasticsearchTemplate.search(query, OnsEarDiarioReservatorioEntity.class).map(SearchHit::getContent).stream();
    }

    @Override
    public void index(OnsEarDiarioReservatorioEntity entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }

    @Override
    public void deleteFromIndexById(Long id) {
        elasticsearchTemplate.delete(String.valueOf(id), OnsEarDiarioReservatorioEntity.class);
    }
}
