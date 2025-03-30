package com.alexandrebfreitas.wue.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.alexandrebfreitas.wue.domain.OnsModalidadeOperacaoUsinasEntity;
import com.alexandrebfreitas.wue.repository.OnsModalidadeOperacaoUsinasRepository;
import java.util.stream.Stream;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * Spring Data Elasticsearch repository for the {@link OnsModalidadeOperacaoUsinasEntity} entity.
 */
public interface OnsModalidadeOperacaoUsinasSearchRepository
    extends ElasticsearchRepository<OnsModalidadeOperacaoUsinasEntity, Long>, OnsModalidadeOperacaoUsinasSearchRepositoryInternal {}

interface OnsModalidadeOperacaoUsinasSearchRepositoryInternal {
    Stream<OnsModalidadeOperacaoUsinasEntity> search(String query);

    Stream<OnsModalidadeOperacaoUsinasEntity> search(Query query);

    @Async
    void index(OnsModalidadeOperacaoUsinasEntity entity);

    @Async
    void deleteFromIndexById(Long id);
}

class OnsModalidadeOperacaoUsinasSearchRepositoryInternalImpl implements OnsModalidadeOperacaoUsinasSearchRepositoryInternal {

    private final ElasticsearchTemplate elasticsearchTemplate;
    private final OnsModalidadeOperacaoUsinasRepository repository;

    OnsModalidadeOperacaoUsinasSearchRepositoryInternalImpl(
        ElasticsearchTemplate elasticsearchTemplate,
        OnsModalidadeOperacaoUsinasRepository repository
    ) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Stream<OnsModalidadeOperacaoUsinasEntity> search(String query) {
        NativeQuery nativeQuery = new NativeQuery(QueryStringQuery.of(qs -> qs.query(query))._toQuery());
        return search(nativeQuery);
    }

    @Override
    public Stream<OnsModalidadeOperacaoUsinasEntity> search(Query query) {
        return elasticsearchTemplate.search(query, OnsModalidadeOperacaoUsinasEntity.class).map(SearchHit::getContent).stream();
    }

    @Override
    public void index(OnsModalidadeOperacaoUsinasEntity entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }

    @Override
    public void deleteFromIndexById(Long id) {
        elasticsearchTemplate.delete(String.valueOf(id), OnsModalidadeOperacaoUsinasEntity.class);
    }
}
