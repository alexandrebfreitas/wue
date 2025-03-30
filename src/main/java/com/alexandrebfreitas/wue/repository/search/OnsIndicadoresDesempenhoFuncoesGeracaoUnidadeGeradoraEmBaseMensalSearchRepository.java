package com.alexandrebfreitas.wue.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.alexandrebfreitas.wue.domain.OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity;
import com.alexandrebfreitas.wue.repository.OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRepository;
import java.util.stream.Stream;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * Spring Data Elasticsearch repository for the {@link OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity} entity.
 */
public interface OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchRepository
    extends
        ElasticsearchRepository<OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity, Long>,
        OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchRepositoryInternal {}

interface OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchRepositoryInternal {
    Stream<OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity> search(String query);

    Stream<OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity> search(Query query);

    @Async
    void index(OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity entity);

    @Async
    void deleteFromIndexById(Long id);
}

class OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchRepositoryInternalImpl
    implements OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchRepositoryInternal {

    private final ElasticsearchTemplate elasticsearchTemplate;
    private final OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRepository repository;

    OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchRepositoryInternalImpl(
        ElasticsearchTemplate elasticsearchTemplate,
        OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRepository repository
    ) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Stream<OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity> search(String query) {
        NativeQuery nativeQuery = new NativeQuery(QueryStringQuery.of(qs -> qs.query(query))._toQuery());
        return search(nativeQuery);
    }

    @Override
    public Stream<OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity> search(Query query) {
        return elasticsearchTemplate
            .search(query, OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.class)
            .map(SearchHit::getContent)
            .stream();
    }

    @Override
    public void index(OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }

    @Override
    public void deleteFromIndexById(Long id) {
        elasticsearchTemplate.delete(String.valueOf(id), OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.class);
    }
}
