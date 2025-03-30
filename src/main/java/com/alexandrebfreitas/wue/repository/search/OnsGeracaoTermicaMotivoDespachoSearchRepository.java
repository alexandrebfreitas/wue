package com.alexandrebfreitas.wue.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.alexandrebfreitas.wue.domain.OnsGeracaoTermicaMotivoDespachoEntity;
import com.alexandrebfreitas.wue.repository.OnsGeracaoTermicaMotivoDespachoRepository;
import java.util.stream.Stream;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * Spring Data Elasticsearch repository for the {@link OnsGeracaoTermicaMotivoDespachoEntity} entity.
 */
public interface OnsGeracaoTermicaMotivoDespachoSearchRepository
    extends ElasticsearchRepository<OnsGeracaoTermicaMotivoDespachoEntity, Long>, OnsGeracaoTermicaMotivoDespachoSearchRepositoryInternal {}

interface OnsGeracaoTermicaMotivoDespachoSearchRepositoryInternal {
    Stream<OnsGeracaoTermicaMotivoDespachoEntity> search(String query);

    Stream<OnsGeracaoTermicaMotivoDespachoEntity> search(Query query);

    @Async
    void index(OnsGeracaoTermicaMotivoDespachoEntity entity);

    @Async
    void deleteFromIndexById(Long id);
}

class OnsGeracaoTermicaMotivoDespachoSearchRepositoryInternalImpl implements OnsGeracaoTermicaMotivoDespachoSearchRepositoryInternal {

    private final ElasticsearchTemplate elasticsearchTemplate;
    private final OnsGeracaoTermicaMotivoDespachoRepository repository;

    OnsGeracaoTermicaMotivoDespachoSearchRepositoryInternalImpl(
        ElasticsearchTemplate elasticsearchTemplate,
        OnsGeracaoTermicaMotivoDespachoRepository repository
    ) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Stream<OnsGeracaoTermicaMotivoDespachoEntity> search(String query) {
        NativeQuery nativeQuery = new NativeQuery(QueryStringQuery.of(qs -> qs.query(query))._toQuery());
        return search(nativeQuery);
    }

    @Override
    public Stream<OnsGeracaoTermicaMotivoDespachoEntity> search(Query query) {
        return elasticsearchTemplate.search(query, OnsGeracaoTermicaMotivoDespachoEntity.class).map(SearchHit::getContent).stream();
    }

    @Override
    public void index(OnsGeracaoTermicaMotivoDespachoEntity entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }

    @Override
    public void deleteFromIndexById(Long id) {
        elasticsearchTemplate.delete(String.valueOf(id), OnsGeracaoTermicaMotivoDespachoEntity.class);
    }
}
