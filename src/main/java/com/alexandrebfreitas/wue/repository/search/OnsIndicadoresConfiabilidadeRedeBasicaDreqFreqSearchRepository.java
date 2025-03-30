package com.alexandrebfreitas.wue.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.alexandrebfreitas.wue.domain.OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity;
import com.alexandrebfreitas.wue.repository.OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqRepository;
import java.util.stream.Stream;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * Spring Data Elasticsearch repository for the {@link OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity} entity.
 */
public interface OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchRepository
    extends
        ElasticsearchRepository<OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity, Long>,
        OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchRepositoryInternal {}

interface OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchRepositoryInternal {
    Stream<OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity> search(String query);

    Stream<OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity> search(Query query);

    @Async
    void index(OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity entity);

    @Async
    void deleteFromIndexById(Long id);
}

class OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchRepositoryInternalImpl
    implements OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchRepositoryInternal {

    private final ElasticsearchTemplate elasticsearchTemplate;
    private final OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqRepository repository;

    OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchRepositoryInternalImpl(
        ElasticsearchTemplate elasticsearchTemplate,
        OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqRepository repository
    ) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Stream<OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity> search(String query) {
        NativeQuery nativeQuery = new NativeQuery(QueryStringQuery.of(qs -> qs.query(query))._toQuery());
        return search(nativeQuery);
    }

    @Override
    public Stream<OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity> search(Query query) {
        return elasticsearchTemplate
            .search(query, OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.class)
            .map(SearchHit::getContent)
            .stream();
    }

    @Override
    public void index(OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }

    @Override
    public void deleteFromIndexById(Long id) {
        elasticsearchTemplate.delete(String.valueOf(id), OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.class);
    }
}
