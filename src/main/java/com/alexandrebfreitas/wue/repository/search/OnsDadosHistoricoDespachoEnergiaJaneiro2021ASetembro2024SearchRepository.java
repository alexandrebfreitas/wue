package com.alexandrebfreitas.wue.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.alexandrebfreitas.wue.domain.OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity;
import com.alexandrebfreitas.wue.repository.OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Repository;
import java.util.stream.Stream;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;

/**
 * Spring Data Elasticsearch repository for the {@link OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity} entity.
 */
public interface OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchRepository
    extends
        ElasticsearchRepository<OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity, Long>,
        OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchRepositoryInternal {}

interface OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchRepositoryInternal {
    Stream<OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity> search(String query);

    Stream<OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity> search(Query query);

    @Async
    void index(OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity entity);

    @Async
    void deleteFromIndexById(Long id);
}

class OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchRepositoryInternalImpl
    implements OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchRepositoryInternal {

    private final ElasticsearchTemplate elasticsearchTemplate;
    private final OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Repository repository;

    OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchRepositoryInternalImpl(
        ElasticsearchTemplate elasticsearchTemplate,
        OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Repository repository
    ) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Stream<OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity> search(String query) {
        NativeQuery nativeQuery = new NativeQuery(QueryStringQuery.of(qs -> qs.query(query))._toQuery());
        return search(nativeQuery);
    }

    @Override
    public Stream<OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity> search(Query query) {
        return elasticsearchTemplate
            .search(query, OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.class)
            .map(SearchHit::getContent)
            .stream();
    }

    @Override
    public void index(OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }

    @Override
    public void deleteFromIndexById(Long id) {
        elasticsearchTemplate.delete(String.valueOf(id), OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.class);
    }
}
