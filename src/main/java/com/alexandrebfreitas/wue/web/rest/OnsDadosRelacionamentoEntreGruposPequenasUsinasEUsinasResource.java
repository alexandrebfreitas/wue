package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRepository;
import com.alexandrebfreitas.wue.repository.search.OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity}.
 */
@RestController
@RequestMapping("/api/ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas")
@Transactional
public class OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasResource.class);

    private static final String ENTITY_NAME = "onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRepository onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRepository;

    private final OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchRepository onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchRepository;

    public OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasResource(
        OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRepository onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRepository,
        OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchRepository onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchRepository
    ) {
        this.onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRepository =
            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRepository;
        this.onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchRepository =
            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchRepository;
    }

    /**
     * {@code POST  /ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas} : Create a new onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas.
     *
     * @param onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity the onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity, or with status {@code 400 (Bad Request)} if the onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<
        OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity
    > createOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas(
        @RequestBody OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to save OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas : {}",
            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity
        );
        if (onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.getId() != null) {
            throw new BadRequestAlertException(
                "A new onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity =
            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRepository.save(
                onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity
            );
        onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchRepository.index(
            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity
        );
        return ResponseEntity.created(
            new URI(
                "/api/ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas/" +
                onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.getId()
            )
        )
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.getId().toString()
                )
            )
            .body(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity);
    }

    /**
     * {@code PUT  /ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas/:id} : Updates an existing onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas.
     *
     * @param id the id of the onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity to save.
     * @param onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity the onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity,
     * or with status {@code 400 (Bad Request)} if the onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<
        OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity
    > updateOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to update OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas : {}, {}",
            id,
            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity
        );
        if (onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity =
            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRepository.save(
                onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity
            );
        onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchRepository.index(
            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity
        );
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.getId().toString()
                )
            )
            .body(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity);
    }

    /**
     * {@code PATCH  /ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas/:id} : Partial updates given fields of an existing onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas, field will ignore if it is null
     *
     * @param id the id of the onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity to save.
     * @param onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity the onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity,
     * or with status {@code 400 (Bad Request)} if the onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<
        OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity
    > partialUpdateOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to partial update OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas partially : {}, {}",
            id,
            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity
        );
        if (onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity> result =
            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRepository
                .findById(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.getId())
                .map(existingOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas -> {
                    if (onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.getIdSubsistema() != null) {
                        existingOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas.setIdSubsistema(
                            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.getIdSubsistema()
                        );
                    }
                    if (onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.getNomSubsistema() != null) {
                        existingOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas.setNomSubsistema(
                            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.getNomSubsistema()
                        );
                    }
                    if (onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.getEstadId() != null) {
                        existingOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas.setEstadId(
                            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.getEstadId()
                        );
                    }
                    if (onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.getNomEstado() != null) {
                        existingOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas.setNomEstado(
                            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.getNomEstado()
                        );
                    }
                    if (onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.getIdTipousina() != null) {
                        existingOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas.setIdTipousina(
                            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.getIdTipousina()
                        );
                    }
                    if (onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.getIdOnsPequenasusinas() != null) {
                        existingOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas.setIdOnsPequenasusinas(
                            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.getIdOnsPequenasusinas()
                        );
                    }
                    if (onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.getIdOnsUsina() != null) {
                        existingOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas.setIdOnsUsina(
                            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.getIdOnsUsina()
                        );
                    }
                    if (onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.getNomPequenasusinas() != null) {
                        existingOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas.setNomPequenasusinas(
                            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.getNomPequenasusinas()
                        );
                    }
                    if (onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.getNomUsina() != null) {
                        existingOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas.setNomUsina(
                            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.getNomUsina()
                        );
                    }
                    if (onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.getCeg() != null) {
                        existingOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas.setCeg(
                            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.getCeg()
                        );
                    }

                    return existingOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas;
                })
                .map(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRepository::save)
                .map(savedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas -> {
                    onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchRepository.index(
                        savedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas
                    );
                    return savedOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas;
                });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ENTITY_NAME,
                onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.getId().toString()
            )
        );
    }

    /**
     * {@code GET  /ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas} : get all the onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas in body.
     */
    @GetMapping("")
    public List<
        OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity
    > getAllOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas() {
        LOG.debug("REST request to get all OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas");
        return onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRepository.findAll();
    }

    /**
     * {@code GET  /ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas/:id} : get the "id" onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas.
     *
     * @param id the id of the onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<
        OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity
    > getOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas : {}", id);
        Optional<
            OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity
        > onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity =
            onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity);
    }

    /**
     * {@code DELETE  /ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas/:id} : delete the "id" onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas.
     *
     * @param id the id of the onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas : {}", id);
        onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRepository.deleteById(id);
        onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas/_search?query=:query} : search for the onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas corresponding
     * to the query.
     *
     * @param query the query of the onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity> searchOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas(
        @RequestParam("query") String query
    ) {
        LOG.debug("REST request to search OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas for query {}", query);
        try {
            return StreamSupport.stream(
                onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasSearchRepository.search(query).spliterator(),
                false
            ).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
