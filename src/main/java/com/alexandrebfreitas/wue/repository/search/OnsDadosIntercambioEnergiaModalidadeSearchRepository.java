package com.alexandrebfreitas.wue.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.alexandrebfreitas.wue.domain.OnsDadosIntercambioEnergiaModalidadeEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosIntercambioEnergiaModalidadeRepository;
import java.util.stream.Stream;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * Spring Data Elasticsearch repository for the {@link OnsDadosIntercambioEnergiaModalidadeEntity} entity.
 */
public interface OnsDadosIntercambioEnergiaModalidadeSearchRepository
    extends
        ElasticsearchRepository<OnsDadosIntercambioEnergiaModalidadeEntity, Long>,
        OnsDadosIntercambioEnergiaModalidadeSearchRepositoryInternal {}

interface OnsDadosIntercambioEnergiaModalidadeSearchRepositoryInternal {
    Stream<OnsDadosIntercambioEnergiaModalidadeEntity> search(String query);

    Stream<OnsDadosIntercambioEnergiaModalidadeEntity> search(Query query);

    @Async
    void index(OnsDadosIntercambioEnergiaModalidadeEntity entity);

    @Async
    void deleteFromIndexById(Long id);
}

class OnsDadosIntercambioEnergiaModalidadeSearchRepositoryInternalImpl
    implements OnsDadosIntercambioEnergiaModalidadeSearchRepositoryInternal {

    private final ElasticsearchTemplate elasticsearchTemplate;
    private final OnsDadosIntercambioEnergiaModalidadeRepository repository;

    OnsDadosIntercambioEnergiaModalidadeSearchRepositoryInternalImpl(
        ElasticsearchTemplate elasticsearchTemplate,
        OnsDadosIntercambioEnergiaModalidadeRepository repository
    ) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Stream<OnsDadosIntercambioEnergiaModalidadeEntity> search(String query) {
        NativeQuery nativeQuery = new NativeQuery(QueryStringQuery.of(qs -> qs.query(query))._toQuery());
        return search(nativeQuery);
    }

    @Override
    public Stream<OnsDadosIntercambioEnergiaModalidadeEntity> search(Query query) {
        return elasticsearchTemplate.search(query, OnsDadosIntercambioEnergiaModalidadeEntity.class).map(SearchHit::getContent).stream();
    }

    @Override
    public void index(OnsDadosIntercambioEnergiaModalidadeEntity entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }

    @Override
    public void deleteFromIndexById(Long id) {
        elasticsearchTemplate.delete(String.valueOf(id), OnsDadosIntercambioEnergiaModalidadeEntity.class);
    }
}
