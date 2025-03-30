package com.alexandrebfreitas.wue.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.alexandrebfreitas.wue.domain.OnsDadosRelacionamentoEntreConjuntosEUsinasEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosRelacionamentoEntreConjuntosEUsinasRepository;
import java.util.stream.Stream;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * Spring Data Elasticsearch repository for the {@link OnsDadosRelacionamentoEntreConjuntosEUsinasEntity} entity.
 */
public interface OnsDadosRelacionamentoEntreConjuntosEUsinasSearchRepository
    extends
        ElasticsearchRepository<OnsDadosRelacionamentoEntreConjuntosEUsinasEntity, Long>,
        OnsDadosRelacionamentoEntreConjuntosEUsinasSearchRepositoryInternal {}

interface OnsDadosRelacionamentoEntreConjuntosEUsinasSearchRepositoryInternal {
    Stream<OnsDadosRelacionamentoEntreConjuntosEUsinasEntity> search(String query);

    Stream<OnsDadosRelacionamentoEntreConjuntosEUsinasEntity> search(Query query);

    @Async
    void index(OnsDadosRelacionamentoEntreConjuntosEUsinasEntity entity);

    @Async
    void deleteFromIndexById(Long id);
}

class OnsDadosRelacionamentoEntreConjuntosEUsinasSearchRepositoryInternalImpl
    implements OnsDadosRelacionamentoEntreConjuntosEUsinasSearchRepositoryInternal {

    private final ElasticsearchTemplate elasticsearchTemplate;
    private final OnsDadosRelacionamentoEntreConjuntosEUsinasRepository repository;

    OnsDadosRelacionamentoEntreConjuntosEUsinasSearchRepositoryInternalImpl(
        ElasticsearchTemplate elasticsearchTemplate,
        OnsDadosRelacionamentoEntreConjuntosEUsinasRepository repository
    ) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Stream<OnsDadosRelacionamentoEntreConjuntosEUsinasEntity> search(String query) {
        NativeQuery nativeQuery = new NativeQuery(QueryStringQuery.of(qs -> qs.query(query))._toQuery());
        return search(nativeQuery);
    }

    @Override
    public Stream<OnsDadosRelacionamentoEntreConjuntosEUsinasEntity> search(Query query) {
        return elasticsearchTemplate
            .search(query, OnsDadosRelacionamentoEntreConjuntosEUsinasEntity.class)
            .map(SearchHit::getContent)
            .stream();
    }

    @Override
    public void index(OnsDadosRelacionamentoEntreConjuntosEUsinasEntity entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }

    @Override
    public void deleteFromIndexById(Long id) {
        elasticsearchTemplate.delete(String.valueOf(id), OnsDadosRelacionamentoEntreConjuntosEUsinasEntity.class);
    }
}
