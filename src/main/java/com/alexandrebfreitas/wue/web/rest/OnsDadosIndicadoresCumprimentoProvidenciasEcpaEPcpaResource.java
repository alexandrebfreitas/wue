package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRepository;
import com.alexandrebfreitas.wue.repository.search.OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity}.
 */
@RestController
@RequestMapping("/api/ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpas")
@Transactional
public class OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaResource.class);

    private static final String ENTITY_NAME = "onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRepository onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRepository;

    private final OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaSearchRepository onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaSearchRepository;

    public OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaResource(
        OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRepository onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRepository,
        OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaSearchRepository onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaSearchRepository
    ) {
        this.onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRepository = onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRepository;
        this.onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaSearchRepository =
            onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaSearchRepository;
    }

    /**
     * {@code POST  /ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpas} : Create a new onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa.
     *
     * @param onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity the onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity, or with status {@code 400 (Bad Request)} if the onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<
        OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity
    > createOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa(
        @RequestBody OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to save OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa : {}",
            onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity
        );
        if (onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.getId() != null) {
            throw new BadRequestAlertException(
                "A new onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity = onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRepository.save(
            onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity
        );
        onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaSearchRepository.index(
            onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity
        );
        return ResponseEntity.created(
            new URI(
                "/api/ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpas/" +
                onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.getId()
            )
        )
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.getId().toString()
                )
            )
            .body(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity);
    }

    /**
     * {@code PUT  /ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpas/:id} : Updates an existing onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa.
     *
     * @param id the id of the onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity to save.
     * @param onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity the onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity,
     * or with status {@code 400 (Bad Request)} if the onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<
        OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity
    > updateOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to update OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa : {}, {}",
            id,
            onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity
        );
        if (onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity = onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRepository.save(
            onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity
        );
        onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaSearchRepository.index(
            onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity
        );
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.getId().toString()
                )
            )
            .body(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity);
    }

    /**
     * {@code PATCH  /ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpas/:id} : Partial updates given fields of an existing onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa, field will ignore if it is null
     *
     * @param id the id of the onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity to save.
     * @param onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity the onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity,
     * or with status {@code 400 (Bad Request)} if the onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<
        OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity
    > partialUpdateOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to partial update OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa partially : {}, {}",
            id,
            onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity
        );
        if (onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity> result =
            onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRepository
                .findById(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.getId())
                .map(existingOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa -> {
                    if (onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.getDscAgregacao() != null) {
                        existingOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa.setDscAgregacao(
                            onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.getDscAgregacao()
                        );
                    }
                    if (onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.getDscCaracteristica() != null) {
                        existingOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa.setDscCaracteristica(
                            onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.getDscCaracteristica()
                        );
                    }
                    if (onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.getDinReferencia() != null) {
                        existingOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa.setDinReferencia(
                            onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.getDinReferencia()
                        );
                    }
                    if (onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.getNumNprcConcluidas() != null) {
                        existingOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa.setNumNprcConcluidas(
                            onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.getNumNprcConcluidas()
                        );
                    }
                    if (onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.getNumNprpProgramadas() != null) {
                        existingOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa.setNumNprpProgramadas(
                            onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.getNumNprpProgramadas()
                        );
                    }
                    if (onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.getNumNpratAtrasadas() != null) {
                        existingOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa.setNumNpratAtrasadas(
                            onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.getNumNpratAtrasadas()
                        );
                    }
                    if (onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.getNumNpraAntecipadas() != null) {
                        existingOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa.setNumNpraAntecipadas(
                            onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.getNumNpraAntecipadas()
                        );
                    }
                    if (onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.getNumNprcpConcluidasPrazo() != null) {
                        existingOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa.setNumNprcpConcluidasPrazo(
                            onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.getNumNprcpConcluidasPrazo()
                        );
                    }
                    if (onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.getValEcpa() != null) {
                        existingOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa.setValEcpa(
                            onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.getValEcpa()
                        );
                    }
                    if (onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.getValPcpa() != null) {
                        existingOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa.setValPcpa(
                            onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.getValPcpa()
                        );
                    }

                    return existingOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa;
                })
                .map(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRepository::save)
                .map(savedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa -> {
                    onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaSearchRepository.index(
                        savedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa
                    );
                    return savedOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa;
                });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ENTITY_NAME,
                onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.getId().toString()
            )
        );
    }

    /**
     * {@code GET  /ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpas} : get all the onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpas in body.
     */
    @GetMapping("")
    public List<OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity> getAllOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpas() {
        LOG.debug("REST request to get all OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpas");
        return onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRepository.findAll();
    }

    /**
     * {@code GET  /ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpas/:id} : get the "id" onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa.
     *
     * @param id the id of the onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity> getOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa(
        @PathVariable("id") Long id
    ) {
        LOG.debug("REST request to get OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa : {}", id);
        Optional<OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity> onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity =
            onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity);
    }

    /**
     * {@code DELETE  /ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpas/:id} : delete the "id" onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa.
     *
     * @param id the id of the onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa : {}", id);
        onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaRepository.deleteById(id);
        onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpas/_search?query=:query} : search for the onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa corresponding
     * to the query.
     *
     * @param query the query of the onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity> searchOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpas(
        @RequestParam("query") String query
    ) {
        LOG.debug("REST request to search OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpas for query {}", query);
        try {
            return StreamSupport.stream(
                onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaSearchRepository.search(query).spliterator(),
                false
            ).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
