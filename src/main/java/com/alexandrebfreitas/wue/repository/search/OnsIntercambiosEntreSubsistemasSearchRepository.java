package com.alexandrebfreitas.wue.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.alexandrebfreitas.wue.domain.OnsIntercambiosEntreSubsistemasEntity;
import com.alexandrebfreitas.wue.repository.OnsIntercambiosEntreSubsistemasRepository;
import java.util.stream.Stream;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * Spring Data Elasticsearch repository for the {@link OnsIntercambiosEntreSubsistemasEntity} entity.
 */
public interface OnsIntercambiosEntreSubsistemasSearchRepository
    extends ElasticsearchRepository<OnsIntercambiosEntreSubsistemasEntity, Long>, OnsIntercambiosEntreSubsistemasSearchRepositoryInternal {}

interface OnsIntercambiosEntreSubsistemasSearchRepositoryInternal {
    Stream<OnsIntercambiosEntreSubsistemasEntity> search(String query);

    Stream<OnsIntercambiosEntreSubsistemasEntity> search(Query query);

    @Async
    void index(OnsIntercambiosEntreSubsistemasEntity entity);

    @Async
    void deleteFromIndexById(Long id);
}

class OnsIntercambiosEntreSubsistemasSearchRepositoryInternalImpl implements OnsIntercambiosEntreSubsistemasSearchRepositoryInternal {

    private final ElasticsearchTemplate elasticsearchTemplate;
    private final OnsIntercambiosEntreSubsistemasRepository repository;

    OnsIntercambiosEntreSubsistemasSearchRepositoryInternalImpl(
        ElasticsearchTemplate elasticsearchTemplate,
        OnsIntercambiosEntreSubsistemasRepository repository
    ) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Stream<OnsIntercambiosEntreSubsistemasEntity> search(String query) {
        NativeQuery nativeQuery = new NativeQuery(QueryStringQuery.of(qs -> qs.query(query))._toQuery());
        return search(nativeQuery);
    }

    @Override
    public Stream<OnsIntercambiosEntreSubsistemasEntity> search(Query query) {
        return elasticsearchTemplate.search(query, OnsIntercambiosEntreSubsistemasEntity.class).map(SearchHit::getContent).stream();
    }

    @Override
    public void index(OnsIntercambiosEntreSubsistemasEntity entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }

    @Override
    public void deleteFromIndexById(Long id) {
        elasticsearchTemplate.delete(String.valueOf(id), OnsIntercambiosEntreSubsistemasEntity.class);
    }
}
