package com.alexandrebfreitas.wue.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.alexandrebfreitas.wue.domain.OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity;
import com.alexandrebfreitas.wue.repository.OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRepository;
import java.util.stream.Stream;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * Spring Data Elasticsearch repository for the {@link OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity} entity.
 */
public interface OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchRepository
    extends
        ElasticsearchRepository<OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity, Long>,
        OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchRepositoryInternal {}

interface OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchRepositoryInternal {
    Stream<OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity> search(String query);

    Stream<OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity> search(Query query);

    @Async
    void index(OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity entity);

    @Async
    void deleteFromIndexById(Long id);
}

class OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchRepositoryInternalImpl
    implements OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchRepositoryInternal {

    private final ElasticsearchTemplate elasticsearchTemplate;
    private final OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRepository repository;

    OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchRepositoryInternalImpl(
        ElasticsearchTemplate elasticsearchTemplate,
        OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRepository repository
    ) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Stream<OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity> search(String query) {
        NativeQuery nativeQuery = new NativeQuery(QueryStringQuery.of(qs -> qs.query(query))._toQuery());
        return search(nativeQuery);
    }

    @Override
    public Stream<OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity> search(Query query) {
        return elasticsearchTemplate
            .search(query, OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.class)
            .map(SearchHit::getContent)
            .stream();
    }

    @Override
    public void index(OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }

    @Override
    public void deleteFromIndexById(Long id) {
        elasticsearchTemplate.delete(String.valueOf(id), OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.class);
    }
}
