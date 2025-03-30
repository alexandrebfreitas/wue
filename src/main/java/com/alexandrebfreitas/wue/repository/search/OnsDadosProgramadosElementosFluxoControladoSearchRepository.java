package com.alexandrebfreitas.wue.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.alexandrebfreitas.wue.domain.OnsDadosProgramadosElementosFluxoControladoEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosProgramadosElementosFluxoControladoRepository;
import java.util.stream.Stream;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * Spring Data Elasticsearch repository for the {@link OnsDadosProgramadosElementosFluxoControladoEntity} entity.
 */
public interface OnsDadosProgramadosElementosFluxoControladoSearchRepository
    extends
        ElasticsearchRepository<OnsDadosProgramadosElementosFluxoControladoEntity, Long>,
        OnsDadosProgramadosElementosFluxoControladoSearchRepositoryInternal {}

interface OnsDadosProgramadosElementosFluxoControladoSearchRepositoryInternal {
    Stream<OnsDadosProgramadosElementosFluxoControladoEntity> search(String query);

    Stream<OnsDadosProgramadosElementosFluxoControladoEntity> search(Query query);

    @Async
    void index(OnsDadosProgramadosElementosFluxoControladoEntity entity);

    @Async
    void deleteFromIndexById(Long id);
}

class OnsDadosProgramadosElementosFluxoControladoSearchRepositoryInternalImpl
    implements OnsDadosProgramadosElementosFluxoControladoSearchRepositoryInternal {

    private final ElasticsearchTemplate elasticsearchTemplate;
    private final OnsDadosProgramadosElementosFluxoControladoRepository repository;

    OnsDadosProgramadosElementosFluxoControladoSearchRepositoryInternalImpl(
        ElasticsearchTemplate elasticsearchTemplate,
        OnsDadosProgramadosElementosFluxoControladoRepository repository
    ) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Stream<OnsDadosProgramadosElementosFluxoControladoEntity> search(String query) {
        NativeQuery nativeQuery = new NativeQuery(QueryStringQuery.of(qs -> qs.query(query))._toQuery());
        return search(nativeQuery);
    }

    @Override
    public Stream<OnsDadosProgramadosElementosFluxoControladoEntity> search(Query query) {
        return elasticsearchTemplate
            .search(query, OnsDadosProgramadosElementosFluxoControladoEntity.class)
            .map(SearchHit::getContent)
            .stream();
    }

    @Override
    public void index(OnsDadosProgramadosElementosFluxoControladoEntity entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }

    @Override
    public void deleteFromIndexById(Long id) {
        elasticsearchTemplate.delete(String.valueOf(id), OnsDadosProgramadosElementosFluxoControladoEntity.class);
    }
}
