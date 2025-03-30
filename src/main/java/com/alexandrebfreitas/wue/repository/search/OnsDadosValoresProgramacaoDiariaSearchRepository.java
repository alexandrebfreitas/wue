package com.alexandrebfreitas.wue.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.alexandrebfreitas.wue.domain.OnsDadosValoresProgramacaoDiariaEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosValoresProgramacaoDiariaRepository;
import java.util.stream.Stream;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * Spring Data Elasticsearch repository for the {@link OnsDadosValoresProgramacaoDiariaEntity} entity.
 */
public interface OnsDadosValoresProgramacaoDiariaSearchRepository
    extends
        ElasticsearchRepository<OnsDadosValoresProgramacaoDiariaEntity, Long>, OnsDadosValoresProgramacaoDiariaSearchRepositoryInternal {}

interface OnsDadosValoresProgramacaoDiariaSearchRepositoryInternal {
    Stream<OnsDadosValoresProgramacaoDiariaEntity> search(String query);

    Stream<OnsDadosValoresProgramacaoDiariaEntity> search(Query query);

    @Async
    void index(OnsDadosValoresProgramacaoDiariaEntity entity);

    @Async
    void deleteFromIndexById(Long id);
}

class OnsDadosValoresProgramacaoDiariaSearchRepositoryInternalImpl implements OnsDadosValoresProgramacaoDiariaSearchRepositoryInternal {

    private final ElasticsearchTemplate elasticsearchTemplate;
    private final OnsDadosValoresProgramacaoDiariaRepository repository;

    OnsDadosValoresProgramacaoDiariaSearchRepositoryInternalImpl(
        ElasticsearchTemplate elasticsearchTemplate,
        OnsDadosValoresProgramacaoDiariaRepository repository
    ) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Stream<OnsDadosValoresProgramacaoDiariaEntity> search(String query) {
        NativeQuery nativeQuery = new NativeQuery(QueryStringQuery.of(qs -> qs.query(query))._toQuery());
        return search(nativeQuery);
    }

    @Override
    public Stream<OnsDadosValoresProgramacaoDiariaEntity> search(Query query) {
        return elasticsearchTemplate.search(query, OnsDadosValoresProgramacaoDiariaEntity.class).map(SearchHit::getContent).stream();
    }

    @Override
    public void index(OnsDadosValoresProgramacaoDiariaEntity entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }

    @Override
    public void deleteFromIndexById(Long id) {
        elasticsearchTemplate.delete(String.valueOf(id), OnsDadosValoresProgramacaoDiariaEntity.class);
    }
}
