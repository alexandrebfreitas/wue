package com.alexandrebfreitas.wue.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.alexandrebfreitas.wue.domain.OnsDadosHidraulicosReservatorioBaseHorariaEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosHidraulicosReservatorioBaseHorariaRepository;
import java.util.stream.Stream;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * Spring Data Elasticsearch repository for the {@link OnsDadosHidraulicosReservatorioBaseHorariaEntity} entity.
 */
public interface OnsDadosHidraulicosReservatorioBaseHorariaSearchRepository
    extends
        ElasticsearchRepository<OnsDadosHidraulicosReservatorioBaseHorariaEntity, Long>,
        OnsDadosHidraulicosReservatorioBaseHorariaSearchRepositoryInternal {}

interface OnsDadosHidraulicosReservatorioBaseHorariaSearchRepositoryInternal {
    Stream<OnsDadosHidraulicosReservatorioBaseHorariaEntity> search(String query);

    Stream<OnsDadosHidraulicosReservatorioBaseHorariaEntity> search(Query query);

    @Async
    void index(OnsDadosHidraulicosReservatorioBaseHorariaEntity entity);

    @Async
    void deleteFromIndexById(Long id);
}

class OnsDadosHidraulicosReservatorioBaseHorariaSearchRepositoryInternalImpl
    implements OnsDadosHidraulicosReservatorioBaseHorariaSearchRepositoryInternal {

    private final ElasticsearchTemplate elasticsearchTemplate;
    private final OnsDadosHidraulicosReservatorioBaseHorariaRepository repository;

    OnsDadosHidraulicosReservatorioBaseHorariaSearchRepositoryInternalImpl(
        ElasticsearchTemplate elasticsearchTemplate,
        OnsDadosHidraulicosReservatorioBaseHorariaRepository repository
    ) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Stream<OnsDadosHidraulicosReservatorioBaseHorariaEntity> search(String query) {
        NativeQuery nativeQuery = new NativeQuery(QueryStringQuery.of(qs -> qs.query(query))._toQuery());
        return search(nativeQuery);
    }

    @Override
    public Stream<OnsDadosHidraulicosReservatorioBaseHorariaEntity> search(Query query) {
        return elasticsearchTemplate
            .search(query, OnsDadosHidraulicosReservatorioBaseHorariaEntity.class)
            .map(SearchHit::getContent)
            .stream();
    }

    @Override
    public void index(OnsDadosHidraulicosReservatorioBaseHorariaEntity entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }

    @Override
    public void deleteFromIndexById(Long id) {
        elasticsearchTemplate.delete(String.valueOf(id), OnsDadosHidraulicosReservatorioBaseHorariaEntity.class);
    }
}
