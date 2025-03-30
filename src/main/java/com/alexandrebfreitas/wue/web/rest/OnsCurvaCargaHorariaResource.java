package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsCurvaCargaHorariaEntity;
import com.alexandrebfreitas.wue.repository.OnsCurvaCargaHorariaRepository;
import com.alexandrebfreitas.wue.repository.search.OnsCurvaCargaHorariaSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsCurvaCargaHorariaEntity}.
 */
@RestController
@RequestMapping("/api/ons-curva-carga-horarias")
@Transactional
public class OnsCurvaCargaHorariaResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsCurvaCargaHorariaResource.class);

    private static final String ENTITY_NAME = "onsCurvaCargaHoraria";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsCurvaCargaHorariaRepository onsCurvaCargaHorariaRepository;

    private final OnsCurvaCargaHorariaSearchRepository onsCurvaCargaHorariaSearchRepository;

    public OnsCurvaCargaHorariaResource(
        OnsCurvaCargaHorariaRepository onsCurvaCargaHorariaRepository,
        OnsCurvaCargaHorariaSearchRepository onsCurvaCargaHorariaSearchRepository
    ) {
        this.onsCurvaCargaHorariaRepository = onsCurvaCargaHorariaRepository;
        this.onsCurvaCargaHorariaSearchRepository = onsCurvaCargaHorariaSearchRepository;
    }

    /**
     * {@code POST  /ons-curva-carga-horarias} : Create a new onsCurvaCargaHoraria.
     *
     * @param onsCurvaCargaHorariaEntity the onsCurvaCargaHorariaEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsCurvaCargaHorariaEntity, or with status {@code 400 (Bad Request)} if the onsCurvaCargaHoraria has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OnsCurvaCargaHorariaEntity> createOnsCurvaCargaHoraria(
        @RequestBody OnsCurvaCargaHorariaEntity onsCurvaCargaHorariaEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to save OnsCurvaCargaHoraria : {}", onsCurvaCargaHorariaEntity);
        if (onsCurvaCargaHorariaEntity.getId() != null) {
            throw new BadRequestAlertException("A new onsCurvaCargaHoraria cannot already have an ID", ENTITY_NAME, "idexists");
        }
        onsCurvaCargaHorariaEntity = onsCurvaCargaHorariaRepository.save(onsCurvaCargaHorariaEntity);
        onsCurvaCargaHorariaSearchRepository.index(onsCurvaCargaHorariaEntity);
        return ResponseEntity.created(new URI("/api/ons-curva-carga-horarias/" + onsCurvaCargaHorariaEntity.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, onsCurvaCargaHorariaEntity.getId().toString())
            )
            .body(onsCurvaCargaHorariaEntity);
    }

    /**
     * {@code PUT  /ons-curva-carga-horarias/:id} : Updates an existing onsCurvaCargaHoraria.
     *
     * @param id the id of the onsCurvaCargaHorariaEntity to save.
     * @param onsCurvaCargaHorariaEntity the onsCurvaCargaHorariaEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsCurvaCargaHorariaEntity,
     * or with status {@code 400 (Bad Request)} if the onsCurvaCargaHorariaEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsCurvaCargaHorariaEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OnsCurvaCargaHorariaEntity> updateOnsCurvaCargaHoraria(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsCurvaCargaHorariaEntity onsCurvaCargaHorariaEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to update OnsCurvaCargaHoraria : {}, {}", id, onsCurvaCargaHorariaEntity);
        if (onsCurvaCargaHorariaEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsCurvaCargaHorariaEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsCurvaCargaHorariaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsCurvaCargaHorariaEntity = onsCurvaCargaHorariaRepository.save(onsCurvaCargaHorariaEntity);
        onsCurvaCargaHorariaSearchRepository.index(onsCurvaCargaHorariaEntity);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, onsCurvaCargaHorariaEntity.getId().toString()))
            .body(onsCurvaCargaHorariaEntity);
    }

    /**
     * {@code PATCH  /ons-curva-carga-horarias/:id} : Partial updates given fields of an existing onsCurvaCargaHoraria, field will ignore if it is null
     *
     * @param id the id of the onsCurvaCargaHorariaEntity to save.
     * @param onsCurvaCargaHorariaEntity the onsCurvaCargaHorariaEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsCurvaCargaHorariaEntity,
     * or with status {@code 400 (Bad Request)} if the onsCurvaCargaHorariaEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsCurvaCargaHorariaEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsCurvaCargaHorariaEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OnsCurvaCargaHorariaEntity> partialUpdateOnsCurvaCargaHoraria(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsCurvaCargaHorariaEntity onsCurvaCargaHorariaEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update OnsCurvaCargaHoraria partially : {}, {}", id, onsCurvaCargaHorariaEntity);
        if (onsCurvaCargaHorariaEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsCurvaCargaHorariaEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsCurvaCargaHorariaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsCurvaCargaHorariaEntity> result = onsCurvaCargaHorariaRepository
            .findById(onsCurvaCargaHorariaEntity.getId())
            .map(existingOnsCurvaCargaHoraria -> {
                if (onsCurvaCargaHorariaEntity.getIdSubsistema() != null) {
                    existingOnsCurvaCargaHoraria.setIdSubsistema(onsCurvaCargaHorariaEntity.getIdSubsistema());
                }
                if (onsCurvaCargaHorariaEntity.getNomSubsistema() != null) {
                    existingOnsCurvaCargaHoraria.setNomSubsistema(onsCurvaCargaHorariaEntity.getNomSubsistema());
                }
                if (onsCurvaCargaHorariaEntity.getDinInstante() != null) {
                    existingOnsCurvaCargaHoraria.setDinInstante(onsCurvaCargaHorariaEntity.getDinInstante());
                }
                if (onsCurvaCargaHorariaEntity.getValCargaenergiahomwmed() != null) {
                    existingOnsCurvaCargaHoraria.setValCargaenergiahomwmed(onsCurvaCargaHorariaEntity.getValCargaenergiahomwmed());
                }

                return existingOnsCurvaCargaHoraria;
            })
            .map(onsCurvaCargaHorariaRepository::save)
            .map(savedOnsCurvaCargaHoraria -> {
                onsCurvaCargaHorariaSearchRepository.index(savedOnsCurvaCargaHoraria);
                return savedOnsCurvaCargaHoraria;
            });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, onsCurvaCargaHorariaEntity.getId().toString())
        );
    }

    /**
     * {@code GET  /ons-curva-carga-horarias} : get all the onsCurvaCargaHorarias.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsCurvaCargaHorarias in body.
     */
    @GetMapping("")
    public List<OnsCurvaCargaHorariaEntity> getAllOnsCurvaCargaHorarias() {
        LOG.debug("REST request to get all OnsCurvaCargaHorarias");
        return onsCurvaCargaHorariaRepository.findAll();
    }

    /**
     * {@code GET  /ons-curva-carga-horarias/:id} : get the "id" onsCurvaCargaHoraria.
     *
     * @param id the id of the onsCurvaCargaHorariaEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsCurvaCargaHorariaEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OnsCurvaCargaHorariaEntity> getOnsCurvaCargaHoraria(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsCurvaCargaHoraria : {}", id);
        Optional<OnsCurvaCargaHorariaEntity> onsCurvaCargaHorariaEntity = onsCurvaCargaHorariaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsCurvaCargaHorariaEntity);
    }

    /**
     * {@code DELETE  /ons-curva-carga-horarias/:id} : delete the "id" onsCurvaCargaHoraria.
     *
     * @param id the id of the onsCurvaCargaHorariaEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsCurvaCargaHoraria(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsCurvaCargaHoraria : {}", id);
        onsCurvaCargaHorariaRepository.deleteById(id);
        onsCurvaCargaHorariaSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-curva-carga-horarias/_search?query=:query} : search for the onsCurvaCargaHoraria corresponding
     * to the query.
     *
     * @param query the query of the onsCurvaCargaHoraria search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsCurvaCargaHorariaEntity> searchOnsCurvaCargaHorarias(@RequestParam("query") String query) {
        LOG.debug("REST request to search OnsCurvaCargaHorarias for query {}", query);
        try {
            return StreamSupport.stream(onsCurvaCargaHorariaSearchRepository.search(query).spliterator(), false).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
