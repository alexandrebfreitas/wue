package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsDadosGrandezasFluviometricasEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosGrandezasFluviometricasRepository;
import com.alexandrebfreitas.wue.repository.search.OnsDadosGrandezasFluviometricasSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsDadosGrandezasFluviometricasEntity}.
 */
@RestController
@RequestMapping("/api/ons-dados-grandezas-fluviometricas")
@Transactional
public class OnsDadosGrandezasFluviometricasResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsDadosGrandezasFluviometricasResource.class);

    private static final String ENTITY_NAME = "onsDadosGrandezasFluviometricas";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsDadosGrandezasFluviometricasRepository onsDadosGrandezasFluviometricasRepository;

    private final OnsDadosGrandezasFluviometricasSearchRepository onsDadosGrandezasFluviometricasSearchRepository;

    public OnsDadosGrandezasFluviometricasResource(
        OnsDadosGrandezasFluviometricasRepository onsDadosGrandezasFluviometricasRepository,
        OnsDadosGrandezasFluviometricasSearchRepository onsDadosGrandezasFluviometricasSearchRepository
    ) {
        this.onsDadosGrandezasFluviometricasRepository = onsDadosGrandezasFluviometricasRepository;
        this.onsDadosGrandezasFluviometricasSearchRepository = onsDadosGrandezasFluviometricasSearchRepository;
    }

    /**
     * {@code POST  /ons-dados-grandezas-fluviometricas} : Create a new onsDadosGrandezasFluviometricas.
     *
     * @param onsDadosGrandezasFluviometricasEntity the onsDadosGrandezasFluviometricasEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsDadosGrandezasFluviometricasEntity, or with status {@code 400 (Bad Request)} if the onsDadosGrandezasFluviometricas has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OnsDadosGrandezasFluviometricasEntity> createOnsDadosGrandezasFluviometricas(
        @RequestBody OnsDadosGrandezasFluviometricasEntity onsDadosGrandezasFluviometricasEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to save OnsDadosGrandezasFluviometricas : {}", onsDadosGrandezasFluviometricasEntity);
        if (onsDadosGrandezasFluviometricasEntity.getId() != null) {
            throw new BadRequestAlertException("A new onsDadosGrandezasFluviometricas cannot already have an ID", ENTITY_NAME, "idexists");
        }
        onsDadosGrandezasFluviometricasEntity = onsDadosGrandezasFluviometricasRepository.save(onsDadosGrandezasFluviometricasEntity);
        onsDadosGrandezasFluviometricasSearchRepository.index(onsDadosGrandezasFluviometricasEntity);
        return ResponseEntity.created(new URI("/api/ons-dados-grandezas-fluviometricas/" + onsDadosGrandezasFluviometricasEntity.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsDadosGrandezasFluviometricasEntity.getId().toString()
                )
            )
            .body(onsDadosGrandezasFluviometricasEntity);
    }

    /**
     * {@code PUT  /ons-dados-grandezas-fluviometricas/:id} : Updates an existing onsDadosGrandezasFluviometricas.
     *
     * @param id the id of the onsDadosGrandezasFluviometricasEntity to save.
     * @param onsDadosGrandezasFluviometricasEntity the onsDadosGrandezasFluviometricasEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsDadosGrandezasFluviometricasEntity,
     * or with status {@code 400 (Bad Request)} if the onsDadosGrandezasFluviometricasEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsDadosGrandezasFluviometricasEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OnsDadosGrandezasFluviometricasEntity> updateOnsDadosGrandezasFluviometricas(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsDadosGrandezasFluviometricasEntity onsDadosGrandezasFluviometricasEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to update OnsDadosGrandezasFluviometricas : {}, {}", id, onsDadosGrandezasFluviometricasEntity);
        if (onsDadosGrandezasFluviometricasEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsDadosGrandezasFluviometricasEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsDadosGrandezasFluviometricasRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsDadosGrandezasFluviometricasEntity = onsDadosGrandezasFluviometricasRepository.save(onsDadosGrandezasFluviometricasEntity);
        onsDadosGrandezasFluviometricasSearchRepository.index(onsDadosGrandezasFluviometricasEntity);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsDadosGrandezasFluviometricasEntity.getId().toString()
                )
            )
            .body(onsDadosGrandezasFluviometricasEntity);
    }

    /**
     * {@code PATCH  /ons-dados-grandezas-fluviometricas/:id} : Partial updates given fields of an existing onsDadosGrandezasFluviometricas, field will ignore if it is null
     *
     * @param id the id of the onsDadosGrandezasFluviometricasEntity to save.
     * @param onsDadosGrandezasFluviometricasEntity the onsDadosGrandezasFluviometricasEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsDadosGrandezasFluviometricasEntity,
     * or with status {@code 400 (Bad Request)} if the onsDadosGrandezasFluviometricasEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsDadosGrandezasFluviometricasEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsDadosGrandezasFluviometricasEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OnsDadosGrandezasFluviometricasEntity> partialUpdateOnsDadosGrandezasFluviometricas(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsDadosGrandezasFluviometricasEntity onsDadosGrandezasFluviometricasEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to partial update OnsDadosGrandezasFluviometricas partially : {}, {}",
            id,
            onsDadosGrandezasFluviometricasEntity
        );
        if (onsDadosGrandezasFluviometricasEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsDadosGrandezasFluviometricasEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsDadosGrandezasFluviometricasRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsDadosGrandezasFluviometricasEntity> result = onsDadosGrandezasFluviometricasRepository
            .findById(onsDadosGrandezasFluviometricasEntity.getId())
            .map(existingOnsDadosGrandezasFluviometricas -> {
                if (onsDadosGrandezasFluviometricasEntity.getIdPostofluv() != null) {
                    existingOnsDadosGrandezasFluviometricas.setIdPostofluv(onsDadosGrandezasFluviometricasEntity.getIdPostofluv());
                }
                if (onsDadosGrandezasFluviometricasEntity.getNomPostofluviometrico() != null) {
                    existingOnsDadosGrandezasFluviometricas.setNomPostofluviometrico(
                        onsDadosGrandezasFluviometricasEntity.getNomPostofluviometrico()
                    );
                }
                if (onsDadosGrandezasFluviometricasEntity.getValLatitude() != null) {
                    existingOnsDadosGrandezasFluviometricas.setValLatitude(onsDadosGrandezasFluviometricasEntity.getValLatitude());
                }
                if (onsDadosGrandezasFluviometricasEntity.getValLongitude() != null) {
                    existingOnsDadosGrandezasFluviometricas.setValLongitude(onsDadosGrandezasFluviometricasEntity.getValLongitude());
                }
                if (onsDadosGrandezasFluviometricasEntity.getNomRio() != null) {
                    existingOnsDadosGrandezasFluviometricas.setNomRio(onsDadosGrandezasFluviometricasEntity.getNomRio());
                }
                if (onsDadosGrandezasFluviometricasEntity.getNomBacia() != null) {
                    existingOnsDadosGrandezasFluviometricas.setNomBacia(onsDadosGrandezasFluviometricasEntity.getNomBacia());
                }
                if (onsDadosGrandezasFluviometricasEntity.getDinMedicao() != null) {
                    existingOnsDadosGrandezasFluviometricas.setDinMedicao(onsDadosGrandezasFluviometricasEntity.getDinMedicao());
                }
                if (onsDadosGrandezasFluviometricasEntity.getValVazaomedia() != null) {
                    existingOnsDadosGrandezasFluviometricas.setValVazaomedia(onsDadosGrandezasFluviometricasEntity.getValVazaomedia());
                }
                if (onsDadosGrandezasFluviometricasEntity.getValVazaomediaincr() != null) {
                    existingOnsDadosGrandezasFluviometricas.setValVazaomediaincr(
                        onsDadosGrandezasFluviometricasEntity.getValVazaomediaincr()
                    );
                }

                return existingOnsDadosGrandezasFluviometricas;
            })
            .map(onsDadosGrandezasFluviometricasRepository::save)
            .map(savedOnsDadosGrandezasFluviometricas -> {
                onsDadosGrandezasFluviometricasSearchRepository.index(savedOnsDadosGrandezasFluviometricas);
                return savedOnsDadosGrandezasFluviometricas;
            });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, onsDadosGrandezasFluviometricasEntity.getId().toString())
        );
    }

    /**
     * {@code GET  /ons-dados-grandezas-fluviometricas} : get all the onsDadosGrandezasFluviometricas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsDadosGrandezasFluviometricas in body.
     */
    @GetMapping("")
    public List<OnsDadosGrandezasFluviometricasEntity> getAllOnsDadosGrandezasFluviometricas() {
        LOG.debug("REST request to get all OnsDadosGrandezasFluviometricas");
        return onsDadosGrandezasFluviometricasRepository.findAll();
    }

    /**
     * {@code GET  /ons-dados-grandezas-fluviometricas/:id} : get the "id" onsDadosGrandezasFluviometricas.
     *
     * @param id the id of the onsDadosGrandezasFluviometricasEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsDadosGrandezasFluviometricasEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OnsDadosGrandezasFluviometricasEntity> getOnsDadosGrandezasFluviometricas(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsDadosGrandezasFluviometricas : {}", id);
        Optional<OnsDadosGrandezasFluviometricasEntity> onsDadosGrandezasFluviometricasEntity =
            onsDadosGrandezasFluviometricasRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsDadosGrandezasFluviometricasEntity);
    }

    /**
     * {@code DELETE  /ons-dados-grandezas-fluviometricas/:id} : delete the "id" onsDadosGrandezasFluviometricas.
     *
     * @param id the id of the onsDadosGrandezasFluviometricasEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsDadosGrandezasFluviometricas(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsDadosGrandezasFluviometricas : {}", id);
        onsDadosGrandezasFluviometricasRepository.deleteById(id);
        onsDadosGrandezasFluviometricasSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-dados-grandezas-fluviometricas/_search?query=:query} : search for the onsDadosGrandezasFluviometricas corresponding
     * to the query.
     *
     * @param query the query of the onsDadosGrandezasFluviometricas search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsDadosGrandezasFluviometricasEntity> searchOnsDadosGrandezasFluviometricas(@RequestParam("query") String query) {
        LOG.debug("REST request to search OnsDadosGrandezasFluviometricas for query {}", query);
        try {
            return StreamSupport.stream(onsDadosGrandezasFluviometricasSearchRepository.search(query).spliterator(), false).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
