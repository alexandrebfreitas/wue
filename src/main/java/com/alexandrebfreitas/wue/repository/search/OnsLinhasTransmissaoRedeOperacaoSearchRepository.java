package com.alexandrebfreitas.wue.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.alexandrebfreitas.wue.domain.OnsLinhasTransmissaoRedeOperacaoEntity;
import com.alexandrebfreitas.wue.repository.OnsLinhasTransmissaoRedeOperacaoRepository;
import java.util.stream.Stream;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * Spring Data Elasticsearch repository for the {@link OnsLinhasTransmissaoRedeOperacaoEntity} entity.
 */
public interface OnsLinhasTransmissaoRedeOperacaoSearchRepository
    extends
        ElasticsearchRepository<OnsLinhasTransmissaoRedeOperacaoEntity, Long>, OnsLinhasTransmissaoRedeOperacaoSearchRepositoryInternal {}

interface OnsLinhasTransmissaoRedeOperacaoSearchRepositoryInternal {
    Stream<OnsLinhasTransmissaoRedeOperacaoEntity> search(String query);

    Stream<OnsLinhasTransmissaoRedeOperacaoEntity> search(Query query);

    @Async
    void index(OnsLinhasTransmissaoRedeOperacaoEntity entity);

    @Async
    void deleteFromIndexById(Long id);
}

class OnsLinhasTransmissaoRedeOperacaoSearchRepositoryInternalImpl implements OnsLinhasTransmissaoRedeOperacaoSearchRepositoryInternal {

    private final ElasticsearchTemplate elasticsearchTemplate;
    private final OnsLinhasTransmissaoRedeOperacaoRepository repository;

    OnsLinhasTransmissaoRedeOperacaoSearchRepositoryInternalImpl(
        ElasticsearchTemplate elasticsearchTemplate,
        OnsLinhasTransmissaoRedeOperacaoRepository repository
    ) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Stream<OnsLinhasTransmissaoRedeOperacaoEntity> search(String query) {
        NativeQuery nativeQuery = new NativeQuery(QueryStringQuery.of(qs -> qs.query(query))._toQuery());
        return search(nativeQuery);
    }

    @Override
    public Stream<OnsLinhasTransmissaoRedeOperacaoEntity> search(Query query) {
        return elasticsearchTemplate.search(query, OnsLinhasTransmissaoRedeOperacaoEntity.class).map(SearchHit::getContent).stream();
    }

    @Override
    public void index(OnsLinhasTransmissaoRedeOperacaoEntity entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }

    @Override
    public void deleteFromIndexById(Long id) {
        elasticsearchTemplate.delete(String.valueOf(id), OnsLinhasTransmissaoRedeOperacaoEntity.class);
    }
}
