package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRepository;
import com.alexandrebfreitas.wue.repository.search.OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchRepository;
import com.alexandrebfreitas.wue.web.rest.errors.BadRequestAlertException;
import com.alexandrebfreitas.wue.web.rest.errors.ElasticsearchExceptionMapper;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity}.
 */
@RestController
@RequestMapping("/api/ons-dados-valores-previsao-versus-programado-eolicas-e-solares")
@Transactional
public class OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresResource.class);

    private static final String ENTITY_NAME = "onsDadosValoresPrevisaoVersusProgramadoEolicasESolares";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRepository onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRepository;

    private final OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchRepository onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchRepository;

    public OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresResource(
        OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRepository onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRepository,
        OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchRepository onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchRepository
    ) {
        this.onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRepository =
            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRepository;
        this.onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchRepository =
            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchRepository;
    }

    /**
     * {@code POST  /ons-dados-valores-previsao-versus-programado-eolicas-e-solares} : Create a new onsDadosValoresPrevisaoVersusProgramadoEolicasESolares.
     *
     * @param onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity the onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity, or with status {@code 400 (Bad Request)} if the onsDadosValoresPrevisaoVersusProgramadoEolicasESolares has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<
        OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity
    > createOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares(
        @RequestBody OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to save OnsDadosValoresPrevisaoVersusProgramadoEolicasESolares : {}",
            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity
        );
        if (onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.getId() != null) {
            throw new BadRequestAlertException(
                "A new onsDadosValoresPrevisaoVersusProgramadoEolicasESolares cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity =
            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRepository.save(
                onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity
            );
        onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchRepository.index(
            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity
        );
        return ResponseEntity.created(
            new URI(
                "/api/ons-dados-valores-previsao-versus-programado-eolicas-e-solares/" +
                onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.getId()
            )
        )
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.getId().toString()
                )
            )
            .body(onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity);
    }

    /**
     * {@code PUT  /ons-dados-valores-previsao-versus-programado-eolicas-e-solares/:id} : Updates an existing onsDadosValoresPrevisaoVersusProgramadoEolicasESolares.
     *
     * @param id the id of the onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity to save.
     * @param onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity the onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity,
     * or with status {@code 400 (Bad Request)} if the onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<
        OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity
    > updateOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to update OnsDadosValoresPrevisaoVersusProgramadoEolicasESolares : {}, {}",
            id,
            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity
        );
        if (onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity =
            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRepository.save(
                onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity
            );
        onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchRepository.index(
            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity
        );
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.getId().toString()
                )
            )
            .body(onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity);
    }

    /**
     * {@code PATCH  /ons-dados-valores-previsao-versus-programado-eolicas-e-solares/:id} : Partial updates given fields of an existing onsDadosValoresPrevisaoVersusProgramadoEolicasESolares, field will ignore if it is null
     *
     * @param id the id of the onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity to save.
     * @param onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity the onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity,
     * or with status {@code 400 (Bad Request)} if the onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<
        OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity
    > partialUpdateOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to partial update OnsDadosValoresPrevisaoVersusProgramadoEolicasESolares partially : {}, {}",
            id,
            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity
        );
        if (onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity> result =
            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRepository
                .findById(onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.getId())
                .map(existingOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares -> {
                    if (onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.getDatProgramacao() != null) {
                        existingOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares.setDatProgramacao(
                            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.getDatProgramacao()
                        );
                    }
                    if (onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.getNumPatamar() != null) {
                        existingOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares.setNumPatamar(
                            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.getNumPatamar()
                        );
                    }
                    if (onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.getCodUsinapdp() != null) {
                        existingOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares.setCodUsinapdp(
                            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.getCodUsinapdp()
                        );
                    }
                    if (onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.getNomUsinapdp() != null) {
                        existingOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares.setNomUsinapdp(
                            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.getNomUsinapdp()
                        );
                    }
                    if (onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.getValPrevisao() != null) {
                        existingOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares.setValPrevisao(
                            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.getValPrevisao()
                        );
                    }
                    if (onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.getValProgramado() != null) {
                        existingOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares.setValProgramado(
                            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.getValProgramado()
                        );
                    }

                    return existingOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares;
                })
                .map(onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRepository::save)
                .map(savedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares -> {
                    onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchRepository.index(
                        savedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares
                    );
                    return savedOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares;
                });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ENTITY_NAME,
                onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.getId().toString()
            )
        );
    }

    /**
     * {@code GET  /ons-dados-valores-previsao-versus-programado-eolicas-e-solares} : get all the onsDadosValoresPrevisaoVersusProgramadoEolicasESolares.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsDadosValoresPrevisaoVersusProgramadoEolicasESolares in body.
     */
    @GetMapping("")
    public List<
        OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity
    > getAllOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares() {
        LOG.debug("REST request to get all OnsDadosValoresPrevisaoVersusProgramadoEolicasESolares");
        return onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRepository.findAll();
    }

    /**
     * {@code GET  /ons-dados-valores-previsao-versus-programado-eolicas-e-solares/:id} : get the "id" onsDadosValoresPrevisaoVersusProgramadoEolicasESolares.
     *
     * @param id the id of the onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<
        OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity
    > getOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsDadosValoresPrevisaoVersusProgramadoEolicasESolares : {}", id);
        Optional<
            OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity
        > onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity =
            onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity);
    }

    /**
     * {@code DELETE  /ons-dados-valores-previsao-versus-programado-eolicas-e-solares/:id} : delete the "id" onsDadosValoresPrevisaoVersusProgramadoEolicasESolares.
     *
     * @param id the id of the onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsDadosValoresPrevisaoVersusProgramadoEolicasESolares : {}", id);
        onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresRepository.deleteById(id);
        onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-dados-valores-previsao-versus-programado-eolicas-e-solares/_search?query=:query} : search for the onsDadosValoresPrevisaoVersusProgramadoEolicasESolares corresponding
     * to the query.
     *
     * @param query the query of the onsDadosValoresPrevisaoVersusProgramadoEolicasESolares search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity> searchOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares(
        @RequestParam("query") String query
    ) {
        LOG.debug("REST request to search OnsDadosValoresPrevisaoVersusProgramadoEolicasESolares for query {}", query);
        try {
            return StreamSupport.stream(
                onsDadosValoresPrevisaoVersusProgramadoEolicasESolaresSearchRepository.search(query).spliterator(),
                false
            ).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
