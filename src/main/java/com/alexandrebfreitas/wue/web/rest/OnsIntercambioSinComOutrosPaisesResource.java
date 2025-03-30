package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsIntercambioSinComOutrosPaisesEntity;
import com.alexandrebfreitas.wue.repository.OnsIntercambioSinComOutrosPaisesRepository;
import com.alexandrebfreitas.wue.repository.search.OnsIntercambioSinComOutrosPaisesSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsIntercambioSinComOutrosPaisesEntity}.
 */
@RestController
@RequestMapping("/api/ons-intercambio-sin-com-outros-paises")
@Transactional
public class OnsIntercambioSinComOutrosPaisesResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsIntercambioSinComOutrosPaisesResource.class);

    private static final String ENTITY_NAME = "onsIntercambioSinComOutrosPaises";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsIntercambioSinComOutrosPaisesRepository onsIntercambioSinComOutrosPaisesRepository;

    private final OnsIntercambioSinComOutrosPaisesSearchRepository onsIntercambioSinComOutrosPaisesSearchRepository;

    public OnsIntercambioSinComOutrosPaisesResource(
        OnsIntercambioSinComOutrosPaisesRepository onsIntercambioSinComOutrosPaisesRepository,
        OnsIntercambioSinComOutrosPaisesSearchRepository onsIntercambioSinComOutrosPaisesSearchRepository
    ) {
        this.onsIntercambioSinComOutrosPaisesRepository = onsIntercambioSinComOutrosPaisesRepository;
        this.onsIntercambioSinComOutrosPaisesSearchRepository = onsIntercambioSinComOutrosPaisesSearchRepository;
    }

    /**
     * {@code POST  /ons-intercambio-sin-com-outros-paises} : Create a new onsIntercambioSinComOutrosPaises.
     *
     * @param onsIntercambioSinComOutrosPaisesEntity the onsIntercambioSinComOutrosPaisesEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsIntercambioSinComOutrosPaisesEntity, or with status {@code 400 (Bad Request)} if the onsIntercambioSinComOutrosPaises has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OnsIntercambioSinComOutrosPaisesEntity> createOnsIntercambioSinComOutrosPaises(
        @RequestBody OnsIntercambioSinComOutrosPaisesEntity onsIntercambioSinComOutrosPaisesEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to save OnsIntercambioSinComOutrosPaises : {}", onsIntercambioSinComOutrosPaisesEntity);
        if (onsIntercambioSinComOutrosPaisesEntity.getId() != null) {
            throw new BadRequestAlertException("A new onsIntercambioSinComOutrosPaises cannot already have an ID", ENTITY_NAME, "idexists");
        }
        onsIntercambioSinComOutrosPaisesEntity = onsIntercambioSinComOutrosPaisesRepository.save(onsIntercambioSinComOutrosPaisesEntity);
        onsIntercambioSinComOutrosPaisesSearchRepository.index(onsIntercambioSinComOutrosPaisesEntity);
        return ResponseEntity.created(
            new URI("/api/ons-intercambio-sin-com-outros-paises/" + onsIntercambioSinComOutrosPaisesEntity.getId())
        )
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsIntercambioSinComOutrosPaisesEntity.getId().toString()
                )
            )
            .body(onsIntercambioSinComOutrosPaisesEntity);
    }

    /**
     * {@code PUT  /ons-intercambio-sin-com-outros-paises/:id} : Updates an existing onsIntercambioSinComOutrosPaises.
     *
     * @param id the id of the onsIntercambioSinComOutrosPaisesEntity to save.
     * @param onsIntercambioSinComOutrosPaisesEntity the onsIntercambioSinComOutrosPaisesEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsIntercambioSinComOutrosPaisesEntity,
     * or with status {@code 400 (Bad Request)} if the onsIntercambioSinComOutrosPaisesEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsIntercambioSinComOutrosPaisesEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OnsIntercambioSinComOutrosPaisesEntity> updateOnsIntercambioSinComOutrosPaises(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsIntercambioSinComOutrosPaisesEntity onsIntercambioSinComOutrosPaisesEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to update OnsIntercambioSinComOutrosPaises : {}, {}", id, onsIntercambioSinComOutrosPaisesEntity);
        if (onsIntercambioSinComOutrosPaisesEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsIntercambioSinComOutrosPaisesEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsIntercambioSinComOutrosPaisesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsIntercambioSinComOutrosPaisesEntity = onsIntercambioSinComOutrosPaisesRepository.save(onsIntercambioSinComOutrosPaisesEntity);
        onsIntercambioSinComOutrosPaisesSearchRepository.index(onsIntercambioSinComOutrosPaisesEntity);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsIntercambioSinComOutrosPaisesEntity.getId().toString()
                )
            )
            .body(onsIntercambioSinComOutrosPaisesEntity);
    }

    /**
     * {@code PATCH  /ons-intercambio-sin-com-outros-paises/:id} : Partial updates given fields of an existing onsIntercambioSinComOutrosPaises, field will ignore if it is null
     *
     * @param id the id of the onsIntercambioSinComOutrosPaisesEntity to save.
     * @param onsIntercambioSinComOutrosPaisesEntity the onsIntercambioSinComOutrosPaisesEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsIntercambioSinComOutrosPaisesEntity,
     * or with status {@code 400 (Bad Request)} if the onsIntercambioSinComOutrosPaisesEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsIntercambioSinComOutrosPaisesEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsIntercambioSinComOutrosPaisesEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OnsIntercambioSinComOutrosPaisesEntity> partialUpdateOnsIntercambioSinComOutrosPaises(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsIntercambioSinComOutrosPaisesEntity onsIntercambioSinComOutrosPaisesEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to partial update OnsIntercambioSinComOutrosPaises partially : {}, {}",
            id,
            onsIntercambioSinComOutrosPaisesEntity
        );
        if (onsIntercambioSinComOutrosPaisesEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsIntercambioSinComOutrosPaisesEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsIntercambioSinComOutrosPaisesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsIntercambioSinComOutrosPaisesEntity> result = onsIntercambioSinComOutrosPaisesRepository
            .findById(onsIntercambioSinComOutrosPaisesEntity.getId())
            .map(existingOnsIntercambioSinComOutrosPaises -> {
                if (onsIntercambioSinComOutrosPaisesEntity.getDinInstante() != null) {
                    existingOnsIntercambioSinComOutrosPaises.setDinInstante(onsIntercambioSinComOutrosPaisesEntity.getDinInstante());
                }
                if (onsIntercambioSinComOutrosPaisesEntity.getNomPaisdestino() != null) {
                    existingOnsIntercambioSinComOutrosPaises.setNomPaisdestino(onsIntercambioSinComOutrosPaisesEntity.getNomPaisdestino());
                }
                if (onsIntercambioSinComOutrosPaisesEntity.getValIntercambiomwmed() != null) {
                    existingOnsIntercambioSinComOutrosPaises.setValIntercambiomwmed(
                        onsIntercambioSinComOutrosPaisesEntity.getValIntercambiomwmed()
                    );
                }

                return existingOnsIntercambioSinComOutrosPaises;
            })
            .map(onsIntercambioSinComOutrosPaisesRepository::save)
            .map(savedOnsIntercambioSinComOutrosPaises -> {
                onsIntercambioSinComOutrosPaisesSearchRepository.index(savedOnsIntercambioSinComOutrosPaises);
                return savedOnsIntercambioSinComOutrosPaises;
            });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ENTITY_NAME,
                onsIntercambioSinComOutrosPaisesEntity.getId().toString()
            )
        );
    }

    /**
     * {@code GET  /ons-intercambio-sin-com-outros-paises} : get all the onsIntercambioSinComOutrosPaises.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsIntercambioSinComOutrosPaises in body.
     */
    @GetMapping("")
    public List<OnsIntercambioSinComOutrosPaisesEntity> getAllOnsIntercambioSinComOutrosPaises() {
        LOG.debug("REST request to get all OnsIntercambioSinComOutrosPaises");
        return onsIntercambioSinComOutrosPaisesRepository.findAll();
    }

    /**
     * {@code GET  /ons-intercambio-sin-com-outros-paises/:id} : get the "id" onsIntercambioSinComOutrosPaises.
     *
     * @param id the id of the onsIntercambioSinComOutrosPaisesEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsIntercambioSinComOutrosPaisesEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OnsIntercambioSinComOutrosPaisesEntity> getOnsIntercambioSinComOutrosPaises(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsIntercambioSinComOutrosPaises : {}", id);
        Optional<OnsIntercambioSinComOutrosPaisesEntity> onsIntercambioSinComOutrosPaisesEntity =
            onsIntercambioSinComOutrosPaisesRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsIntercambioSinComOutrosPaisesEntity);
    }

    /**
     * {@code DELETE  /ons-intercambio-sin-com-outros-paises/:id} : delete the "id" onsIntercambioSinComOutrosPaises.
     *
     * @param id the id of the onsIntercambioSinComOutrosPaisesEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsIntercambioSinComOutrosPaises(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsIntercambioSinComOutrosPaises : {}", id);
        onsIntercambioSinComOutrosPaisesRepository.deleteById(id);
        onsIntercambioSinComOutrosPaisesSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-intercambio-sin-com-outros-paises/_search?query=:query} : search for the onsIntercambioSinComOutrosPaises corresponding
     * to the query.
     *
     * @param query the query of the onsIntercambioSinComOutrosPaises search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsIntercambioSinComOutrosPaisesEntity> searchOnsIntercambioSinComOutrosPaises(@RequestParam("query") String query) {
        LOG.debug("REST request to search OnsIntercambioSinComOutrosPaises for query {}", query);
        try {
            return StreamSupport.stream(onsIntercambioSinComOutrosPaisesSearchRepository.search(query).spliterator(), false).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
