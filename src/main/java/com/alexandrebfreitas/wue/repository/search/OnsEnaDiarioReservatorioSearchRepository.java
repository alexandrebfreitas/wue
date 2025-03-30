package com.alexandrebfreitas.wue.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.alexandrebfreitas.wue.domain.OnsEnaDiarioReservatorioEntity;
import com.alexandrebfreitas.wue.repository.OnsEnaDiarioReservatorioRepository;
import java.util.stream.Stream;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * Spring Data Elasticsearch repository for the {@link OnsEnaDiarioReservatorioEntity} entity.
 */
public interface OnsEnaDiarioReservatorioSearchRepository
    extends ElasticsearchRepository<OnsEnaDiarioReservatorioEntity, Long>, OnsEnaDiarioReservatorioSearchRepositoryInternal {}

interface OnsEnaDiarioReservatorioSearchRepositoryInternal {
    Stream<OnsEnaDiarioReservatorioEntity> search(String query);

    Stream<OnsEnaDiarioReservatorioEntity> search(Query query);

    @Async
    void index(OnsEnaDiarioReservatorioEntity entity);

    @Async
    void deleteFromIndexById(Long id);
}

class OnsEnaDiarioReservatorioSearchRepositoryInternalImpl implements OnsEnaDiarioReservatorioSearchRepositoryInternal {

    private final ElasticsearchTemplate elasticsearchTemplate;
    private final OnsEnaDiarioReservatorioRepository repository;

    OnsEnaDiarioReservatorioSearchRepositoryInternalImpl(
        ElasticsearchTemplate elasticsearchTemplate,
        OnsEnaDiarioReservatorioRepository repository
    ) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Stream<OnsEnaDiarioReservatorioEntity> search(String query) {
        NativeQuery nativeQuery = new NativeQuery(QueryStringQuery.of(qs -> qs.query(query))._toQuery());
        return search(nativeQuery);
    }

    @Override
    public Stream<OnsEnaDiarioReservatorioEntity> search(Query query) {
        return elasticsearchTemplate.search(query, OnsEnaDiarioReservatorioEntity.class).map(SearchHit::getContent).stream();
    }

    @Override
    public void index(OnsEnaDiarioReservatorioEntity entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }

    @Override
    public void deleteFromIndexById(Long id) {
        elasticsearchTemplate.delete(String.valueOf(id), OnsEnaDiarioReservatorioEntity.class);
    }
}
