package com.alexandrebfreitas.wue.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.alexandrebfreitas.wue.domain.OnsIntercambioSinComOutrosPaisesEntity;
import com.alexandrebfreitas.wue.repository.OnsIntercambioSinComOutrosPaisesRepository;
import java.util.stream.Stream;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * Spring Data Elasticsearch repository for the {@link OnsIntercambioSinComOutrosPaisesEntity} entity.
 */
public interface OnsIntercambioSinComOutrosPaisesSearchRepository
    extends
        ElasticsearchRepository<OnsIntercambioSinComOutrosPaisesEntity, Long>, OnsIntercambioSinComOutrosPaisesSearchRepositoryInternal {}

interface OnsIntercambioSinComOutrosPaisesSearchRepositoryInternal {
    Stream<OnsIntercambioSinComOutrosPaisesEntity> search(String query);

    Stream<OnsIntercambioSinComOutrosPaisesEntity> search(Query query);

    @Async
    void index(OnsIntercambioSinComOutrosPaisesEntity entity);

    @Async
    void deleteFromIndexById(Long id);
}

class OnsIntercambioSinComOutrosPaisesSearchRepositoryInternalImpl implements OnsIntercambioSinComOutrosPaisesSearchRepositoryInternal {

    private final ElasticsearchTemplate elasticsearchTemplate;
    private final OnsIntercambioSinComOutrosPaisesRepository repository;

    OnsIntercambioSinComOutrosPaisesSearchRepositoryInternalImpl(
        ElasticsearchTemplate elasticsearchTemplate,
        OnsIntercambioSinComOutrosPaisesRepository repository
    ) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Stream<OnsIntercambioSinComOutrosPaisesEntity> search(String query) {
        NativeQuery nativeQuery = new NativeQuery(QueryStringQuery.of(qs -> qs.query(query))._toQuery());
        return search(nativeQuery);
    }

    @Override
    public Stream<OnsIntercambioSinComOutrosPaisesEntity> search(Query query) {
        return elasticsearchTemplate.search(query, OnsIntercambioSinComOutrosPaisesEntity.class).map(SearchHit::getContent).stream();
    }

    @Override
    public void index(OnsIntercambioSinComOutrosPaisesEntity entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }

    @Override
    public void deleteFromIndexById(Long id) {
        elasticsearchTemplate.delete(String.valueOf(id), OnsIntercambioSinComOutrosPaisesEntity.class);
    }
}
