package com.alexandrebfreitas.wue.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.alexandrebfreitas.wue.domain.OnsGeracaoComercialParaExportacaoInternacionalEntity;
import com.alexandrebfreitas.wue.repository.OnsGeracaoComercialParaExportacaoInternacionalRepository;
import java.util.stream.Stream;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * Spring Data Elasticsearch repository for the {@link OnsGeracaoComercialParaExportacaoInternacionalEntity} entity.
 */
public interface OnsGeracaoComercialParaExportacaoInternacionalSearchRepository
    extends
        ElasticsearchRepository<OnsGeracaoComercialParaExportacaoInternacionalEntity, Long>,
        OnsGeracaoComercialParaExportacaoInternacionalSearchRepositoryInternal {}

interface OnsGeracaoComercialParaExportacaoInternacionalSearchRepositoryInternal {
    Stream<OnsGeracaoComercialParaExportacaoInternacionalEntity> search(String query);

    Stream<OnsGeracaoComercialParaExportacaoInternacionalEntity> search(Query query);

    @Async
    void index(OnsGeracaoComercialParaExportacaoInternacionalEntity entity);

    @Async
    void deleteFromIndexById(Long id);
}

class OnsGeracaoComercialParaExportacaoInternacionalSearchRepositoryInternalImpl
    implements OnsGeracaoComercialParaExportacaoInternacionalSearchRepositoryInternal {

    private final ElasticsearchTemplate elasticsearchTemplate;
    private final OnsGeracaoComercialParaExportacaoInternacionalRepository repository;

    OnsGeracaoComercialParaExportacaoInternacionalSearchRepositoryInternalImpl(
        ElasticsearchTemplate elasticsearchTemplate,
        OnsGeracaoComercialParaExportacaoInternacionalRepository repository
    ) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Stream<OnsGeracaoComercialParaExportacaoInternacionalEntity> search(String query) {
        NativeQuery nativeQuery = new NativeQuery(QueryStringQuery.of(qs -> qs.query(query))._toQuery());
        return search(nativeQuery);
    }

    @Override
    public Stream<OnsGeracaoComercialParaExportacaoInternacionalEntity> search(Query query) {
        return elasticsearchTemplate
            .search(query, OnsGeracaoComercialParaExportacaoInternacionalEntity.class)
            .map(SearchHit::getContent)
            .stream();
    }

    @Override
    public void index(OnsGeracaoComercialParaExportacaoInternacionalEntity entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }

    @Override
    public void deleteFromIndexById(Long id) {
        elasticsearchTemplate.delete(String.valueOf(id), OnsGeracaoComercialParaExportacaoInternacionalEntity.class);
    }
}
