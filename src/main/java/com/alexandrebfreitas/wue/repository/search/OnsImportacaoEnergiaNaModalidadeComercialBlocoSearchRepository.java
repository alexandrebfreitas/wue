package com.alexandrebfreitas.wue.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.alexandrebfreitas.wue.domain.OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity;
import com.alexandrebfreitas.wue.repository.OnsImportacaoEnergiaNaModalidadeComercialBlocoRepository;
import java.util.stream.Stream;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * Spring Data Elasticsearch repository for the {@link OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity} entity.
 */
public interface OnsImportacaoEnergiaNaModalidadeComercialBlocoSearchRepository
    extends
        ElasticsearchRepository<OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity, Long>,
        OnsImportacaoEnergiaNaModalidadeComercialBlocoSearchRepositoryInternal {}

interface OnsImportacaoEnergiaNaModalidadeComercialBlocoSearchRepositoryInternal {
    Stream<OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity> search(String query);

    Stream<OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity> search(Query query);

    @Async
    void index(OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity entity);

    @Async
    void deleteFromIndexById(Long id);
}

class OnsImportacaoEnergiaNaModalidadeComercialBlocoSearchRepositoryInternalImpl
    implements OnsImportacaoEnergiaNaModalidadeComercialBlocoSearchRepositoryInternal {

    private final ElasticsearchTemplate elasticsearchTemplate;
    private final OnsImportacaoEnergiaNaModalidadeComercialBlocoRepository repository;

    OnsImportacaoEnergiaNaModalidadeComercialBlocoSearchRepositoryInternalImpl(
        ElasticsearchTemplate elasticsearchTemplate,
        OnsImportacaoEnergiaNaModalidadeComercialBlocoRepository repository
    ) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Stream<OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity> search(String query) {
        NativeQuery nativeQuery = new NativeQuery(QueryStringQuery.of(qs -> qs.query(query))._toQuery());
        return search(nativeQuery);
    }

    @Override
    public Stream<OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity> search(Query query) {
        return elasticsearchTemplate
            .search(query, OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity.class)
            .map(SearchHit::getContent)
            .stream();
    }

    @Override
    public void index(OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }

    @Override
    public void deleteFromIndexById(Long id) {
        elasticsearchTemplate.delete(String.valueOf(id), OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity.class);
    }
}
