package com.alexandrebfreitas.wue.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.alexandrebfreitas.wue.domain.OnsGeracaoUsinaEmBaseHorariaEntity;
import com.alexandrebfreitas.wue.repository.OnsGeracaoUsinaEmBaseHorariaRepository;
import java.util.stream.Stream;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * Spring Data Elasticsearch repository for the {@link OnsGeracaoUsinaEmBaseHorariaEntity} entity.
 */
public interface OnsGeracaoUsinaEmBaseHorariaSearchRepository
    extends ElasticsearchRepository<OnsGeracaoUsinaEmBaseHorariaEntity, Long>, OnsGeracaoUsinaEmBaseHorariaSearchRepositoryInternal {}

interface OnsGeracaoUsinaEmBaseHorariaSearchRepositoryInternal {
    Stream<OnsGeracaoUsinaEmBaseHorariaEntity> search(String query);

    Stream<OnsGeracaoUsinaEmBaseHorariaEntity> search(Query query);

    @Async
    void index(OnsGeracaoUsinaEmBaseHorariaEntity entity);

    @Async
    void deleteFromIndexById(Long id);
}

class OnsGeracaoUsinaEmBaseHorariaSearchRepositoryInternalImpl implements OnsGeracaoUsinaEmBaseHorariaSearchRepositoryInternal {

    private final ElasticsearchTemplate elasticsearchTemplate;
    private final OnsGeracaoUsinaEmBaseHorariaRepository repository;

    OnsGeracaoUsinaEmBaseHorariaSearchRepositoryInternalImpl(
        ElasticsearchTemplate elasticsearchTemplate,
        OnsGeracaoUsinaEmBaseHorariaRepository repository
    ) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Stream<OnsGeracaoUsinaEmBaseHorariaEntity> search(String query) {
        NativeQuery nativeQuery = new NativeQuery(QueryStringQuery.of(qs -> qs.query(query))._toQuery());
        return search(nativeQuery);
    }

    @Override
    public Stream<OnsGeracaoUsinaEmBaseHorariaEntity> search(Query query) {
        return elasticsearchTemplate.search(query, OnsGeracaoUsinaEmBaseHorariaEntity.class).map(SearchHit::getContent).stream();
    }

    @Override
    public void index(OnsGeracaoUsinaEmBaseHorariaEntity entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }

    @Override
    public void deleteFromIndexById(Long id) {
        elasticsearchTemplate.delete(String.valueOf(id), OnsGeracaoUsinaEmBaseHorariaEntity.class);
    }
}
