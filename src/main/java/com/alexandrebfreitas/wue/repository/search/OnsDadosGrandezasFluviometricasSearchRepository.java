package com.alexandrebfreitas.wue.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.alexandrebfreitas.wue.domain.OnsDadosGrandezasFluviometricasEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosGrandezasFluviometricasRepository;
import java.util.stream.Stream;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * Spring Data Elasticsearch repository for the {@link OnsDadosGrandezasFluviometricasEntity} entity.
 */
public interface OnsDadosGrandezasFluviometricasSearchRepository
    extends ElasticsearchRepository<OnsDadosGrandezasFluviometricasEntity, Long>, OnsDadosGrandezasFluviometricasSearchRepositoryInternal {}

interface OnsDadosGrandezasFluviometricasSearchRepositoryInternal {
    Stream<OnsDadosGrandezasFluviometricasEntity> search(String query);

    Stream<OnsDadosGrandezasFluviometricasEntity> search(Query query);

    @Async
    void index(OnsDadosGrandezasFluviometricasEntity entity);

    @Async
    void deleteFromIndexById(Long id);
}

class OnsDadosGrandezasFluviometricasSearchRepositoryInternalImpl implements OnsDadosGrandezasFluviometricasSearchRepositoryInternal {

    private final ElasticsearchTemplate elasticsearchTemplate;
    private final OnsDadosGrandezasFluviometricasRepository repository;

    OnsDadosGrandezasFluviometricasSearchRepositoryInternalImpl(
        ElasticsearchTemplate elasticsearchTemplate,
        OnsDadosGrandezasFluviometricasRepository repository
    ) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Stream<OnsDadosGrandezasFluviometricasEntity> search(String query) {
        NativeQuery nativeQuery = new NativeQuery(QueryStringQuery.of(qs -> qs.query(query))._toQuery());
        return search(nativeQuery);
    }

    @Override
    public Stream<OnsDadosGrandezasFluviometricasEntity> search(Query query) {
        return elasticsearchTemplate.search(query, OnsDadosGrandezasFluviometricasEntity.class).map(SearchHit::getContent).stream();
    }

    @Override
    public void index(OnsDadosGrandezasFluviometricasEntity entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }

    @Override
    public void deleteFromIndexById(Long id) {
        elasticsearchTemplate.delete(String.valueOf(id), OnsDadosGrandezasFluviometricasEntity.class);
    }
}
