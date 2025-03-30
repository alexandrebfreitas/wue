package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsReservatoriosEntity;
import com.alexandrebfreitas.wue.repository.OnsReservatoriosRepository;
import com.alexandrebfreitas.wue.repository.search.OnsReservatoriosSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsReservatoriosEntity}.
 */
@RestController
@RequestMapping("/api/ons-reservatorios")
@Transactional
public class OnsReservatoriosResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsReservatoriosResource.class);

    private static final String ENTITY_NAME = "onsReservatorios";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsReservatoriosRepository onsReservatoriosRepository;

    private final OnsReservatoriosSearchRepository onsReservatoriosSearchRepository;

    public OnsReservatoriosResource(
        OnsReservatoriosRepository onsReservatoriosRepository,
        OnsReservatoriosSearchRepository onsReservatoriosSearchRepository
    ) {
        this.onsReservatoriosRepository = onsReservatoriosRepository;
        this.onsReservatoriosSearchRepository = onsReservatoriosSearchRepository;
    }

    /**
     * {@code POST  /ons-reservatorios} : Create a new onsReservatorios.
     *
     * @param onsReservatoriosEntity the onsReservatoriosEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsReservatoriosEntity, or with status {@code 400 (Bad Request)} if the onsReservatorios has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OnsReservatoriosEntity> createOnsReservatorios(@RequestBody OnsReservatoriosEntity onsReservatoriosEntity)
        throws URISyntaxException {
        LOG.debug("REST request to save OnsReservatorios : {}", onsReservatoriosEntity);
        if (onsReservatoriosEntity.getId() != null) {
            throw new BadRequestAlertException("A new onsReservatorios cannot already have an ID", ENTITY_NAME, "idexists");
        }
        onsReservatoriosEntity = onsReservatoriosRepository.save(onsReservatoriosEntity);
        onsReservatoriosSearchRepository.index(onsReservatoriosEntity);
        return ResponseEntity.created(new URI("/api/ons-reservatorios/" + onsReservatoriosEntity.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, onsReservatoriosEntity.getId().toString()))
            .body(onsReservatoriosEntity);
    }

    /**
     * {@code PUT  /ons-reservatorios/:id} : Updates an existing onsReservatorios.
     *
     * @param id the id of the onsReservatoriosEntity to save.
     * @param onsReservatoriosEntity the onsReservatoriosEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsReservatoriosEntity,
     * or with status {@code 400 (Bad Request)} if the onsReservatoriosEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsReservatoriosEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OnsReservatoriosEntity> updateOnsReservatorios(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsReservatoriosEntity onsReservatoriosEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to update OnsReservatorios : {}, {}", id, onsReservatoriosEntity);
        if (onsReservatoriosEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsReservatoriosEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsReservatoriosRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsReservatoriosEntity = onsReservatoriosRepository.save(onsReservatoriosEntity);
        onsReservatoriosSearchRepository.index(onsReservatoriosEntity);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, onsReservatoriosEntity.getId().toString()))
            .body(onsReservatoriosEntity);
    }

    /**
     * {@code PATCH  /ons-reservatorios/:id} : Partial updates given fields of an existing onsReservatorios, field will ignore if it is null
     *
     * @param id the id of the onsReservatoriosEntity to save.
     * @param onsReservatoriosEntity the onsReservatoriosEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsReservatoriosEntity,
     * or with status {@code 400 (Bad Request)} if the onsReservatoriosEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsReservatoriosEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsReservatoriosEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OnsReservatoriosEntity> partialUpdateOnsReservatorios(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsReservatoriosEntity onsReservatoriosEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update OnsReservatorios partially : {}, {}", id, onsReservatoriosEntity);
        if (onsReservatoriosEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsReservatoriosEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsReservatoriosRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsReservatoriosEntity> result = onsReservatoriosRepository
            .findById(onsReservatoriosEntity.getId())
            .map(existingOnsReservatorios -> {
                if (onsReservatoriosEntity.getNomRee() != null) {
                    existingOnsReservatorios.setNomRee(onsReservatoriosEntity.getNomRee());
                }
                if (onsReservatoriosEntity.getDatEntrada() != null) {
                    existingOnsReservatorios.setDatEntrada(onsReservatoriosEntity.getDatEntrada());
                }
                if (onsReservatoriosEntity.getValCotamaxima() != null) {
                    existingOnsReservatorios.setValCotamaxima(onsReservatoriosEntity.getValCotamaxima());
                }
                if (onsReservatoriosEntity.getValCotaminima() != null) {
                    existingOnsReservatorios.setValCotaminima(onsReservatoriosEntity.getValCotaminima());
                }
                if (onsReservatoriosEntity.getValVolmax() != null) {
                    existingOnsReservatorios.setValVolmax(onsReservatoriosEntity.getValVolmax());
                }
                if (onsReservatoriosEntity.getValVolmin() != null) {
                    existingOnsReservatorios.setValVolmin(onsReservatoriosEntity.getValVolmin());
                }
                if (onsReservatoriosEntity.getValVolutiltot() != null) {
                    existingOnsReservatorios.setValVolutiltot(onsReservatoriosEntity.getValVolutiltot());
                }
                if (onsReservatoriosEntity.getValProdutibilidadeespecifica() != null) {
                    existingOnsReservatorios.setValProdutibilidadeespecifica(onsReservatoriosEntity.getValProdutibilidadeespecifica());
                }
                if (onsReservatoriosEntity.getValProdutividade65volutil() != null) {
                    existingOnsReservatorios.setValProdutividade65volutil(onsReservatoriosEntity.getValProdutividade65volutil());
                }
                if (onsReservatoriosEntity.getValTipoperda() != null) {
                    existingOnsReservatorios.setValTipoperda(onsReservatoriosEntity.getValTipoperda());
                }
                if (onsReservatoriosEntity.getValPerda() != null) {
                    existingOnsReservatorios.setValPerda(onsReservatoriosEntity.getValPerda());
                }
                if (onsReservatoriosEntity.getValLatitude() != null) {
                    existingOnsReservatorios.setValLatitude(onsReservatoriosEntity.getValLatitude());
                }
                if (onsReservatoriosEntity.getValLongitude() != null) {
                    existingOnsReservatorios.setValLongitude(onsReservatoriosEntity.getValLongitude());
                }
                if (onsReservatoriosEntity.getIdReservatorio() != null) {
                    existingOnsReservatorios.setIdReservatorio(onsReservatoriosEntity.getIdReservatorio());
                }

                return existingOnsReservatorios;
            })
            .map(onsReservatoriosRepository::save)
            .map(savedOnsReservatorios -> {
                onsReservatoriosSearchRepository.index(savedOnsReservatorios);
                return savedOnsReservatorios;
            });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, onsReservatoriosEntity.getId().toString())
        );
    }

    /**
     * {@code GET  /ons-reservatorios} : get all the onsReservatorios.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsReservatorios in body.
     */
    @GetMapping("")
    public List<OnsReservatoriosEntity> getAllOnsReservatorios() {
        LOG.debug("REST request to get all OnsReservatorios");
        return onsReservatoriosRepository.findAll();
    }

    /**
     * {@code GET  /ons-reservatorios/:id} : get the "id" onsReservatorios.
     *
     * @param id the id of the onsReservatoriosEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsReservatoriosEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OnsReservatoriosEntity> getOnsReservatorios(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsReservatorios : {}", id);
        Optional<OnsReservatoriosEntity> onsReservatoriosEntity = onsReservatoriosRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsReservatoriosEntity);
    }

    /**
     * {@code DELETE  /ons-reservatorios/:id} : delete the "id" onsReservatorios.
     *
     * @param id the id of the onsReservatoriosEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsReservatorios(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsReservatorios : {}", id);
        onsReservatoriosRepository.deleteById(id);
        onsReservatoriosSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-reservatorios/_search?query=:query} : search for the onsReservatorios corresponding
     * to the query.
     *
     * @param query the query of the onsReservatorios search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsReservatoriosEntity> searchOnsReservatorios(@RequestParam("query") String query) {
        LOG.debug("REST request to search OnsReservatorios for query {}", query);
        try {
            return StreamSupport.stream(onsReservatoriosSearchRepository.search(query).spliterator(), false).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
