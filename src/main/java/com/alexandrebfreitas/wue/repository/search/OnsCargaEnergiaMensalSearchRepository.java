package com.alexandrebfreitas.wue.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.alexandrebfreitas.wue.domain.OnsCargaEnergiaMensalEntity;
import com.alexandrebfreitas.wue.repository.OnsCargaEnergiaMensalRepository;
import java.util.stream.Stream;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * Spring Data Elasticsearch repository for the {@link OnsCargaEnergiaMensalEntity} entity.
 */
public interface OnsCargaEnergiaMensalSearchRepository
    extends ElasticsearchRepository<OnsCargaEnergiaMensalEntity, Long>, OnsCargaEnergiaMensalSearchRepositoryInternal {}

interface OnsCargaEnergiaMensalSearchRepositoryInternal {
    Stream<OnsCargaEnergiaMensalEntity> search(String query);

    Stream<OnsCargaEnergiaMensalEntity> search(Query query);

    @Async
    void index(OnsCargaEnergiaMensalEntity entity);

    @Async
    void deleteFromIndexById(Long id);
}

class OnsCargaEnergiaMensalSearchRepositoryInternalImpl implements OnsCargaEnergiaMensalSearchRepositoryInternal {

    private final ElasticsearchTemplate elasticsearchTemplate;
    private final OnsCargaEnergiaMensalRepository repository;

    OnsCargaEnergiaMensalSearchRepositoryInternalImpl(
        ElasticsearchTemplate elasticsearchTemplate,
        OnsCargaEnergiaMensalRepository repository
    ) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Stream<OnsCargaEnergiaMensalEntity> search(String query) {
        NativeQuery nativeQuery = new NativeQuery(QueryStringQuery.of(qs -> qs.query(query))._toQuery());
        return search(nativeQuery);
    }

    @Override
    public Stream<OnsCargaEnergiaMensalEntity> search(Query query) {
        return elasticsearchTemplate.search(query, OnsCargaEnergiaMensalEntity.class).map(SearchHit::getContent).stream();
    }

    @Override
    public void index(OnsCargaEnergiaMensalEntity entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }

    @Override
    public void deleteFromIndexById(Long id) {
        elasticsearchTemplate.delete(String.valueOf(id), OnsCargaEnergiaMensalEntity.class);
    }
}
