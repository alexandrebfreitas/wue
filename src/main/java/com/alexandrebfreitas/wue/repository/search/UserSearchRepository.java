package com.alexandrebfreitas.wue.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.alexandrebfreitas.wue.domain.UserEntity;
import com.alexandrebfreitas.wue.repository.UserRepository;
import java.util.stream.Stream;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring Data Elasticsearch repository for the UserEntity entity.
 */
public interface UserSearchRepository extends ElasticsearchRepository<UserEntity, Long>, UserSearchRepositoryInternal {}

interface UserSearchRepositoryInternal {
    Stream<UserEntity> search(String query);

    @Async
    @Transactional
    void index(UserEntity entity);

    @Async
    @Transactional
    void deleteFromIndex(UserEntity entity);
}

class UserSearchRepositoryInternalImpl implements UserSearchRepositoryInternal {

    private final ElasticsearchTemplate elasticsearchTemplate;
    private final UserRepository repository;

    UserSearchRepositoryInternalImpl(ElasticsearchTemplate elasticsearchTemplate, UserRepository repository) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.repository = repository;
    }

    @Override
    public Stream<UserEntity> search(String query) {
        NativeQuery nativeQuery = new NativeQuery(QueryStringQuery.of(qs -> qs.query(query))._toQuery());
        return elasticsearchTemplate.search(nativeQuery, UserEntity.class).map(SearchHit::getContent).stream();
    }

    @Override
    public void index(UserEntity entity) {
        repository.findById(entity.getId()).ifPresent(elasticsearchTemplate::save);
    }

    @Override
    public void deleteFromIndex(UserEntity entity) {
        elasticsearchTemplate.delete(entity);
    }
}
