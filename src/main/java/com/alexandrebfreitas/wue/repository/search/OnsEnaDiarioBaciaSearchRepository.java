package com.alexandrebfreitas.wue.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.alexandrebfreitas.wue.domain.OnsEnaDiarioBaciaEntity;
import com.alexandrebfreitas.wue.repository.OnsEnaDiarioBaciaRepository;
import java.util.stream.Stream;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * Spring Data Elasticsearch repository for the {@link OnsEnaDiarioBaciaEntity} entity.
 */
public interface OnsEnaDiarioBaciaSearchRepository
    extends ElasticsearchRepository<OnsEnaDiarioBaciaEntity, Long>, OnsEnaDiarioBaciaSearchRepositoryInternal {}

interface OnsEnaDiarioBaciaSearchRepositoryInternal {
    Stream<OnsEnaDiarioBaciaEntity> search(String query);

    Stream<OnsEnaDiarioBaciaEntity> search(Query query);

    @Async
    void index(OnsEnaDiarioBaciaEntity entity);

    @Async
    void deleteFromIndexById(Long id);
}

class OnsEnaDiarioBaciaSearchRepositoryInternalImpl implements OnsEnaDiarioBaciaSearchRepositoryInternal {

    private final ElasticsearchTemplate elasticsearchTemplate;
    private final OnsEnaDiarioBaciaRepository repository;

    OnsEnaDiarioBaciaSearchRepositoryInternalImpl(ElasticsearchTemplate elasticsearchTemplate, OnsEnaDiarioBaciaRepository repository) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Stream<OnsEnaDiarioBaciaEntity> search(String query) {
        NativeQuery nativeQuery = new NativeQuery(QueryStringQuery.of(qs -> qs.query(query))._toQuery());
        return search(nativeQuery);
    }

    @Override
    public Stream<OnsEnaDiarioBaciaEntity> search(Query query) {
        return elasticsearchTemplate.search(query, OnsEnaDiarioBaciaEntity.class).map(SearchHit::getContent).stream();
    }

    @Override
    public void index(OnsEnaDiarioBaciaEntity entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }

    @Override
    public void deleteFromIndexById(Long id) {
        elasticsearchTemplate.delete(String.valueOf(id), OnsEnaDiarioBaciaEntity.class);
    }
}
