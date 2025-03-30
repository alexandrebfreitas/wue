package com.alexandrebfreitas.wue.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.alexandrebfreitas.wue.domain.OnsBalancoEnergiaDessemEntity;
import com.alexandrebfreitas.wue.repository.OnsBalancoEnergiaDessemRepository;
import java.util.stream.Stream;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * Spring Data Elasticsearch repository for the {@link OnsBalancoEnergiaDessemEntity} entity.
 */
public interface OnsBalancoEnergiaDessemSearchRepository
    extends ElasticsearchRepository<OnsBalancoEnergiaDessemEntity, Long>, OnsBalancoEnergiaDessemSearchRepositoryInternal {}

interface OnsBalancoEnergiaDessemSearchRepositoryInternal {
    Stream<OnsBalancoEnergiaDessemEntity> search(String query);

    Stream<OnsBalancoEnergiaDessemEntity> search(Query query);

    @Async
    void index(OnsBalancoEnergiaDessemEntity entity);

    @Async
    void deleteFromIndexById(Long id);
}

class OnsBalancoEnergiaDessemSearchRepositoryInternalImpl implements OnsBalancoEnergiaDessemSearchRepositoryInternal {

    private final ElasticsearchTemplate elasticsearchTemplate;
    private final OnsBalancoEnergiaDessemRepository repository;

    OnsBalancoEnergiaDessemSearchRepositoryInternalImpl(
        ElasticsearchTemplate elasticsearchTemplate,
        OnsBalancoEnergiaDessemRepository repository
    ) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Stream<OnsBalancoEnergiaDessemEntity> search(String query) {
        NativeQuery nativeQuery = new NativeQuery(QueryStringQuery.of(qs -> qs.query(query))._toQuery());
        return search(nativeQuery);
    }

    @Override
    public Stream<OnsBalancoEnergiaDessemEntity> search(Query query) {
        return elasticsearchTemplate.search(query, OnsBalancoEnergiaDessemEntity.class).map(SearchHit::getContent).stream();
    }

    @Override
    public void index(OnsBalancoEnergiaDessemEntity entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }

    @Override
    public void deleteFromIndexById(Long id) {
        elasticsearchTemplate.delete(String.valueOf(id), OnsBalancoEnergiaDessemEntity.class);
    }
}
