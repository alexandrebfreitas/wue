package com.alexandrebfreitas.wue.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.alexandrebfreitas.wue.domain.OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRepository;
import java.util.stream.Stream;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * Spring Data Elasticsearch repository for the {@link OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity} entity.
 */
public interface OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchRepository
    extends
        ElasticsearchRepository<OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity, Long>,
        OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchRepositoryInternal {}

interface OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchRepositoryInternal {
    Stream<OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity> search(String query);

    Stream<OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity> search(Query query);

    @Async
    void index(OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity entity);

    @Async
    void deleteFromIndexById(Long id);
}

class OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchRepositoryInternalImpl
    implements OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchRepositoryInternal {

    private final ElasticsearchTemplate elasticsearchTemplate;
    private final OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRepository repository;

    OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchRepositoryInternalImpl(
        ElasticsearchTemplate elasticsearchTemplate,
        OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRepository repository
    ) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Stream<OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity> search(String query) {
        NativeQuery nativeQuery = new NativeQuery(QueryStringQuery.of(qs -> qs.query(query))._toQuery());
        return search(nativeQuery);
    }

    @Override
    public Stream<OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity> search(Query query) {
        return elasticsearchTemplate
            .search(query, OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.class)
            .map(SearchHit::getContent)
            .stream();
    }

    @Override
    public void index(OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }

    @Override
    public void deleteFromIndexById(Long id) {
        elasticsearchTemplate.delete(
            String.valueOf(id),
            OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.class
        );
    }
}
