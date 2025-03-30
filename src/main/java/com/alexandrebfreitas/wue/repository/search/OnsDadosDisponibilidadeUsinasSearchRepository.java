package com.alexandrebfreitas.wue.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.alexandrebfreitas.wue.domain.OnsDadosDisponibilidadeUsinasEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosDisponibilidadeUsinasRepository;
import java.util.stream.Stream;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * Spring Data Elasticsearch repository for the {@link OnsDadosDisponibilidadeUsinasEntity} entity.
 */
public interface OnsDadosDisponibilidadeUsinasSearchRepository
    extends ElasticsearchRepository<OnsDadosDisponibilidadeUsinasEntity, Long>, OnsDadosDisponibilidadeUsinasSearchRepositoryInternal {}

interface OnsDadosDisponibilidadeUsinasSearchRepositoryInternal {
    Stream<OnsDadosDisponibilidadeUsinasEntity> search(String query);

    Stream<OnsDadosDisponibilidadeUsinasEntity> search(Query query);

    @Async
    void index(OnsDadosDisponibilidadeUsinasEntity entity);

    @Async
    void deleteFromIndexById(Long id);
}

class OnsDadosDisponibilidadeUsinasSearchRepositoryInternalImpl implements OnsDadosDisponibilidadeUsinasSearchRepositoryInternal {

    private final ElasticsearchTemplate elasticsearchTemplate;
    private final OnsDadosDisponibilidadeUsinasRepository repository;

    OnsDadosDisponibilidadeUsinasSearchRepositoryInternalImpl(
        ElasticsearchTemplate elasticsearchTemplate,
        OnsDadosDisponibilidadeUsinasRepository repository
    ) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Stream<OnsDadosDisponibilidadeUsinasEntity> search(String query) {
        NativeQuery nativeQuery = new NativeQuery(QueryStringQuery.of(qs -> qs.query(query))._toQuery());
        return search(nativeQuery);
    }

    @Override
    public Stream<OnsDadosDisponibilidadeUsinasEntity> search(Query query) {
        return elasticsearchTemplate.search(query, OnsDadosDisponibilidadeUsinasEntity.class).map(SearchHit::getContent).stream();
    }

    @Override
    public void index(OnsDadosDisponibilidadeUsinasEntity entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }

    @Override
    public void deleteFromIndexById(Long id) {
        elasticsearchTemplate.delete(String.valueOf(id), OnsDadosDisponibilidadeUsinasEntity.class);
    }
}
