package com.alexandrebfreitas.wue.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.alexandrebfreitas.wue.domain.OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRepository;
import java.util.stream.Stream;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * Spring Data Elasticsearch repository for the {@link OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity} entity.
 */
public interface OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchRepository
    extends
        ElasticsearchRepository<OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity, Long>,
        OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchRepositoryInternal {}

interface OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchRepositoryInternal {
    Stream<OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity> search(String query);

    Stream<OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity> search(Query query);

    @Async
    void index(OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity entity);

    @Async
    void deleteFromIndexById(Long id);
}

class OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchRepositoryInternalImpl
    implements OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchRepositoryInternal {

    private final ElasticsearchTemplate elasticsearchTemplate;
    private final OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRepository repository;

    OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchRepositoryInternalImpl(
        ElasticsearchTemplate elasticsearchTemplate,
        OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRepository repository
    ) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Stream<OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity> search(String query) {
        NativeQuery nativeQuery = new NativeQuery(QueryStringQuery.of(qs -> qs.query(query))._toQuery());
        return search(nativeQuery);
    }

    @Override
    public Stream<OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity> search(Query query) {
        return elasticsearchTemplate
            .search(query, OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.class)
            .map(SearchHit::getContent)
            .stream();
    }

    @Override
    public void index(OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }

    @Override
    public void deleteFromIndexById(Long id) {
        elasticsearchTemplate.delete(String.valueOf(id), OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.class);
    }
}
