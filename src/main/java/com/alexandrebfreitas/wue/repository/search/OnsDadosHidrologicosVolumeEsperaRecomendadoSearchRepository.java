package com.alexandrebfreitas.wue.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.alexandrebfreitas.wue.domain.OnsDadosHidrologicosVolumeEsperaRecomendadoEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosHidrologicosVolumeEsperaRecomendadoRepository;
import java.util.stream.Stream;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * Spring Data Elasticsearch repository for the {@link OnsDadosHidrologicosVolumeEsperaRecomendadoEntity} entity.
 */
public interface OnsDadosHidrologicosVolumeEsperaRecomendadoSearchRepository
    extends
        ElasticsearchRepository<OnsDadosHidrologicosVolumeEsperaRecomendadoEntity, Long>,
        OnsDadosHidrologicosVolumeEsperaRecomendadoSearchRepositoryInternal {}

interface OnsDadosHidrologicosVolumeEsperaRecomendadoSearchRepositoryInternal {
    Stream<OnsDadosHidrologicosVolumeEsperaRecomendadoEntity> search(String query);

    Stream<OnsDadosHidrologicosVolumeEsperaRecomendadoEntity> search(Query query);

    @Async
    void index(OnsDadosHidrologicosVolumeEsperaRecomendadoEntity entity);

    @Async
    void deleteFromIndexById(Long id);
}

class OnsDadosHidrologicosVolumeEsperaRecomendadoSearchRepositoryInternalImpl
    implements OnsDadosHidrologicosVolumeEsperaRecomendadoSearchRepositoryInternal {

    private final ElasticsearchTemplate elasticsearchTemplate;
    private final OnsDadosHidrologicosVolumeEsperaRecomendadoRepository repository;

    OnsDadosHidrologicosVolumeEsperaRecomendadoSearchRepositoryInternalImpl(
        ElasticsearchTemplate elasticsearchTemplate,
        OnsDadosHidrologicosVolumeEsperaRecomendadoRepository repository
    ) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Stream<OnsDadosHidrologicosVolumeEsperaRecomendadoEntity> search(String query) {
        NativeQuery nativeQuery = new NativeQuery(QueryStringQuery.of(qs -> qs.query(query))._toQuery());
        return search(nativeQuery);
    }

    @Override
    public Stream<OnsDadosHidrologicosVolumeEsperaRecomendadoEntity> search(Query query) {
        return elasticsearchTemplate
            .search(query, OnsDadosHidrologicosVolumeEsperaRecomendadoEntity.class)
            .map(SearchHit::getContent)
            .stream();
    }

    @Override
    public void index(OnsDadosHidrologicosVolumeEsperaRecomendadoEntity entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }

    @Override
    public void deleteFromIndexById(Long id) {
        elasticsearchTemplate.delete(String.valueOf(id), OnsDadosHidrologicosVolumeEsperaRecomendadoEntity.class);
    }
}
