package com.alexandrebfreitas.wue.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.alexandrebfreitas.wue.domain.OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity;
import com.alexandrebfreitas.wue.repository.OnsEnaDiarioReeReservatorioEquivalenteEnergiaRepository;
import java.util.stream.Stream;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * Spring Data Elasticsearch repository for the {@link OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity} entity.
 */
public interface OnsEnaDiarioReeReservatorioEquivalenteEnergiaSearchRepository
    extends
        ElasticsearchRepository<OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity, Long>,
        OnsEnaDiarioReeReservatorioEquivalenteEnergiaSearchRepositoryInternal {}

interface OnsEnaDiarioReeReservatorioEquivalenteEnergiaSearchRepositoryInternal {
    Stream<OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity> search(String query);

    Stream<OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity> search(Query query);

    @Async
    void index(OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity entity);

    @Async
    void deleteFromIndexById(Long id);
}

class OnsEnaDiarioReeReservatorioEquivalenteEnergiaSearchRepositoryInternalImpl
    implements OnsEnaDiarioReeReservatorioEquivalenteEnergiaSearchRepositoryInternal {

    private final ElasticsearchTemplate elasticsearchTemplate;
    private final OnsEnaDiarioReeReservatorioEquivalenteEnergiaRepository repository;

    OnsEnaDiarioReeReservatorioEquivalenteEnergiaSearchRepositoryInternalImpl(
        ElasticsearchTemplate elasticsearchTemplate,
        OnsEnaDiarioReeReservatorioEquivalenteEnergiaRepository repository
    ) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Stream<OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity> search(String query) {
        NativeQuery nativeQuery = new NativeQuery(QueryStringQuery.of(qs -> qs.query(query))._toQuery());
        return search(nativeQuery);
    }

    @Override
    public Stream<OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity> search(Query query) {
        return elasticsearchTemplate
            .search(query, OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity.class)
            .map(SearchHit::getContent)
            .stream();
    }

    @Override
    public void index(OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }

    @Override
    public void deleteFromIndexById(Long id) {
        elasticsearchTemplate.delete(String.valueOf(id), OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity.class);
    }
}
