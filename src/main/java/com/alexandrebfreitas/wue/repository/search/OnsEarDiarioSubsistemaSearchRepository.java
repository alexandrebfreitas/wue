package com.alexandrebfreitas.wue.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.alexandrebfreitas.wue.domain.OnsEarDiarioSubsistemaEntity;
import com.alexandrebfreitas.wue.repository.OnsEarDiarioSubsistemaRepository;
import java.util.stream.Stream;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * Spring Data Elasticsearch repository for the {@link OnsEarDiarioSubsistemaEntity} entity.
 */
public interface OnsEarDiarioSubsistemaSearchRepository
    extends ElasticsearchRepository<OnsEarDiarioSubsistemaEntity, Long>, OnsEarDiarioSubsistemaSearchRepositoryInternal {}

interface OnsEarDiarioSubsistemaSearchRepositoryInternal {
    Stream<OnsEarDiarioSubsistemaEntity> search(String query);

    Stream<OnsEarDiarioSubsistemaEntity> search(Query query);

    @Async
    void index(OnsEarDiarioSubsistemaEntity entity);

    @Async
    void deleteFromIndexById(Long id);
}

class OnsEarDiarioSubsistemaSearchRepositoryInternalImpl implements OnsEarDiarioSubsistemaSearchRepositoryInternal {

    private final ElasticsearchTemplate elasticsearchTemplate;
    private final OnsEarDiarioSubsistemaRepository repository;

    OnsEarDiarioSubsistemaSearchRepositoryInternalImpl(
        ElasticsearchTemplate elasticsearchTemplate,
        OnsEarDiarioSubsistemaRepository repository
    ) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Stream<OnsEarDiarioSubsistemaEntity> search(String query) {
        NativeQuery nativeQuery = new NativeQuery(QueryStringQuery.of(qs -> qs.query(query))._toQuery());
        return search(nativeQuery);
    }

    @Override
    public Stream<OnsEarDiarioSubsistemaEntity> search(Query query) {
        return elasticsearchTemplate.search(query, OnsEarDiarioSubsistemaEntity.class).map(SearchHit::getContent).stream();
    }

    @Override
    public void index(OnsEarDiarioSubsistemaEntity entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }

    @Override
    public void deleteFromIndexById(Long id) {
        elasticsearchTemplate.delete(String.valueOf(id), OnsEarDiarioSubsistemaEntity.class);
    }
}
