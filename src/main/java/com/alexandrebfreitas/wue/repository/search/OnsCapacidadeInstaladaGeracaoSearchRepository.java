package com.alexandrebfreitas.wue.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.alexandrebfreitas.wue.domain.OnsCapacidadeInstaladaGeracaoEntity;
import com.alexandrebfreitas.wue.repository.OnsCapacidadeInstaladaGeracaoRepository;
import java.util.stream.Stream;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * Spring Data Elasticsearch repository for the {@link OnsCapacidadeInstaladaGeracaoEntity} entity.
 */
public interface OnsCapacidadeInstaladaGeracaoSearchRepository
    extends ElasticsearchRepository<OnsCapacidadeInstaladaGeracaoEntity, Long>, OnsCapacidadeInstaladaGeracaoSearchRepositoryInternal {}

interface OnsCapacidadeInstaladaGeracaoSearchRepositoryInternal {
    Stream<OnsCapacidadeInstaladaGeracaoEntity> search(String query);

    Stream<OnsCapacidadeInstaladaGeracaoEntity> search(Query query);

    @Async
    void index(OnsCapacidadeInstaladaGeracaoEntity entity);

    @Async
    void deleteFromIndexById(Long id);
}

class OnsCapacidadeInstaladaGeracaoSearchRepositoryInternalImpl implements OnsCapacidadeInstaladaGeracaoSearchRepositoryInternal {

    private final ElasticsearchTemplate elasticsearchTemplate;
    private final OnsCapacidadeInstaladaGeracaoRepository repository;

    OnsCapacidadeInstaladaGeracaoSearchRepositoryInternalImpl(
        ElasticsearchTemplate elasticsearchTemplate,
        OnsCapacidadeInstaladaGeracaoRepository repository
    ) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Stream<OnsCapacidadeInstaladaGeracaoEntity> search(String query) {
        NativeQuery nativeQuery = new NativeQuery(QueryStringQuery.of(qs -> qs.query(query))._toQuery());
        return search(nativeQuery);
    }

    @Override
    public Stream<OnsCapacidadeInstaladaGeracaoEntity> search(Query query) {
        return elasticsearchTemplate.search(query, OnsCapacidadeInstaladaGeracaoEntity.class).map(SearchHit::getContent).stream();
    }

    @Override
    public void index(OnsCapacidadeInstaladaGeracaoEntity entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }

    @Override
    public void deleteFromIndexById(Long id) {
        elasticsearchTemplate.delete(String.valueOf(id), OnsCapacidadeInstaladaGeracaoEntity.class);
    }
}
