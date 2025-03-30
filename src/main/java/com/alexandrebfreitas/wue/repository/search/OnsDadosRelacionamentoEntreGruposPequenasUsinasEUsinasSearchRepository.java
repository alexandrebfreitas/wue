package com.alexandrebfreitas.wue.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.alexandrebfreitas.wue.domain.OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRepository;
import java.util.stream.Stream;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * Spring Data Elasticsearch repository for the {@link OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity} entity.
 */
public interface OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchRepository
    extends
        ElasticsearchRepository<OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity, Long>,
        OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchRepositoryInternal {}

interface OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchRepositoryInternal {
    Stream<OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity> search(String query);

    Stream<OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity> search(Query query);

    @Async
    void index(OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity entity);

    @Async
    void deleteFromIndexById(Long id);
}

class OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchRepositoryInternalImpl
    implements OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchRepositoryInternal {

    private final ElasticsearchTemplate elasticsearchTemplate;
    private final OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRepository repository;

    OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchRepositoryInternalImpl(
        ElasticsearchTemplate elasticsearchTemplate,
        OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRepository repository
    ) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Stream<OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity> search(String query) {
        NativeQuery nativeQuery = new NativeQuery(QueryStringQuery.of(qs -> qs.query(query))._toQuery());
        return search(nativeQuery);
    }

    @Override
    public Stream<OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity> search(Query query) {
        return elasticsearchTemplate
            .search(query, OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.class)
            .map(SearchHit::getContent)
            .stream();
    }

    @Override
    public void index(OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }

    @Override
    public void deleteFromIndexById(Long id) {
        elasticsearchTemplate.delete(String.valueOf(id), OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.class);
    }
}
