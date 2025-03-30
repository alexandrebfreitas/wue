package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsEnaDiarioReservatorioEntity;
import com.alexandrebfreitas.wue.repository.OnsEnaDiarioReservatorioRepository;
import com.alexandrebfreitas.wue.repository.search.OnsEnaDiarioReservatorioSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsEnaDiarioReservatorioEntity}.
 */
@RestController
@RequestMapping("/api/ons-ena-diario-reservatorios")
@Transactional
public class OnsEnaDiarioReservatorioResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsEnaDiarioReservatorioResource.class);

    private static final String ENTITY_NAME = "onsEnaDiarioReservatorio";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsEnaDiarioReservatorioRepository onsEnaDiarioReservatorioRepository;

    private final OnsEnaDiarioReservatorioSearchRepository onsEnaDiarioReservatorioSearchRepository;

    public OnsEnaDiarioReservatorioResource(
        OnsEnaDiarioReservatorioRepository onsEnaDiarioReservatorioRepository,
        OnsEnaDiarioReservatorioSearchRepository onsEnaDiarioReservatorioSearchRepository
    ) {
        this.onsEnaDiarioReservatorioRepository = onsEnaDiarioReservatorioRepository;
        this.onsEnaDiarioReservatorioSearchRepository = onsEnaDiarioReservatorioSearchRepository;
    }

    /**
     * {@code POST  /ons-ena-diario-reservatorios} : Create a new onsEnaDiarioReservatorio.
     *
     * @param onsEnaDiarioReservatorioEntity the onsEnaDiarioReservatorioEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsEnaDiarioReservatorioEntity, or with status {@code 400 (Bad Request)} if the onsEnaDiarioReservatorio has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OnsEnaDiarioReservatorioEntity> createOnsEnaDiarioReservatorio(
        @RequestBody OnsEnaDiarioReservatorioEntity onsEnaDiarioReservatorioEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to save OnsEnaDiarioReservatorio : {}", onsEnaDiarioReservatorioEntity);
        if (onsEnaDiarioReservatorioEntity.getId() != null) {
            throw new BadRequestAlertException("A new onsEnaDiarioReservatorio cannot already have an ID", ENTITY_NAME, "idexists");
        }
        onsEnaDiarioReservatorioEntity = onsEnaDiarioReservatorioRepository.save(onsEnaDiarioReservatorioEntity);
        onsEnaDiarioReservatorioSearchRepository.index(onsEnaDiarioReservatorioEntity);
        return ResponseEntity.created(new URI("/api/ons-ena-diario-reservatorios/" + onsEnaDiarioReservatorioEntity.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, onsEnaDiarioReservatorioEntity.getId().toString())
            )
            .body(onsEnaDiarioReservatorioEntity);
    }

    /**
     * {@code PUT  /ons-ena-diario-reservatorios/:id} : Updates an existing onsEnaDiarioReservatorio.
     *
     * @param id the id of the onsEnaDiarioReservatorioEntity to save.
     * @param onsEnaDiarioReservatorioEntity the onsEnaDiarioReservatorioEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsEnaDiarioReservatorioEntity,
     * or with status {@code 400 (Bad Request)} if the onsEnaDiarioReservatorioEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsEnaDiarioReservatorioEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OnsEnaDiarioReservatorioEntity> updateOnsEnaDiarioReservatorio(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsEnaDiarioReservatorioEntity onsEnaDiarioReservatorioEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to update OnsEnaDiarioReservatorio : {}, {}", id, onsEnaDiarioReservatorioEntity);
        if (onsEnaDiarioReservatorioEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsEnaDiarioReservatorioEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsEnaDiarioReservatorioRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsEnaDiarioReservatorioEntity = onsEnaDiarioReservatorioRepository.save(onsEnaDiarioReservatorioEntity);
        onsEnaDiarioReservatorioSearchRepository.index(onsEnaDiarioReservatorioEntity);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, onsEnaDiarioReservatorioEntity.getId().toString())
            )
            .body(onsEnaDiarioReservatorioEntity);
    }

    /**
     * {@code PATCH  /ons-ena-diario-reservatorios/:id} : Partial updates given fields of an existing onsEnaDiarioReservatorio, field will ignore if it is null
     *
     * @param id the id of the onsEnaDiarioReservatorioEntity to save.
     * @param onsEnaDiarioReservatorioEntity the onsEnaDiarioReservatorioEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsEnaDiarioReservatorioEntity,
     * or with status {@code 400 (Bad Request)} if the onsEnaDiarioReservatorioEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsEnaDiarioReservatorioEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsEnaDiarioReservatorioEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OnsEnaDiarioReservatorioEntity> partialUpdateOnsEnaDiarioReservatorio(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsEnaDiarioReservatorioEntity onsEnaDiarioReservatorioEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update OnsEnaDiarioReservatorio partially : {}, {}", id, onsEnaDiarioReservatorioEntity);
        if (onsEnaDiarioReservatorioEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsEnaDiarioReservatorioEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsEnaDiarioReservatorioRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsEnaDiarioReservatorioEntity> result = onsEnaDiarioReservatorioRepository
            .findById(onsEnaDiarioReservatorioEntity.getId())
            .map(existingOnsEnaDiarioReservatorio -> {
                if (onsEnaDiarioReservatorioEntity.getEnaBrutaResMwmed() != null) {
                    existingOnsEnaDiarioReservatorio.setEnaBrutaResMwmed(onsEnaDiarioReservatorioEntity.getEnaBrutaResMwmed());
                }
                if (onsEnaDiarioReservatorioEntity.getEnaBrutaResPercentualmlt() != null) {
                    existingOnsEnaDiarioReservatorio.setEnaBrutaResPercentualmlt(
                        onsEnaDiarioReservatorioEntity.getEnaBrutaResPercentualmlt()
                    );
                }
                if (onsEnaDiarioReservatorioEntity.getEnaArmazenavelResMwmed() != null) {
                    existingOnsEnaDiarioReservatorio.setEnaArmazenavelResMwmed(onsEnaDiarioReservatorioEntity.getEnaArmazenavelResMwmed());
                }
                if (onsEnaDiarioReservatorioEntity.getEnaArmazenavelResPercentualmlt() != null) {
                    existingOnsEnaDiarioReservatorio.setEnaArmazenavelResPercentualmlt(
                        onsEnaDiarioReservatorioEntity.getEnaArmazenavelResPercentualmlt()
                    );
                }
                if (onsEnaDiarioReservatorioEntity.getEnaQuedaBruta() != null) {
                    existingOnsEnaDiarioReservatorio.setEnaQuedaBruta(onsEnaDiarioReservatorioEntity.getEnaQuedaBruta());
                }
                if (onsEnaDiarioReservatorioEntity.getMltEna() != null) {
                    existingOnsEnaDiarioReservatorio.setMltEna(onsEnaDiarioReservatorioEntity.getMltEna());
                }

                return existingOnsEnaDiarioReservatorio;
            })
            .map(onsEnaDiarioReservatorioRepository::save)
            .map(savedOnsEnaDiarioReservatorio -> {
                onsEnaDiarioReservatorioSearchRepository.index(savedOnsEnaDiarioReservatorio);
                return savedOnsEnaDiarioReservatorio;
            });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, onsEnaDiarioReservatorioEntity.getId().toString())
        );
    }

    /**
     * {@code GET  /ons-ena-diario-reservatorios} : get all the onsEnaDiarioReservatorios.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsEnaDiarioReservatorios in body.
     */
    @GetMapping("")
    public List<OnsEnaDiarioReservatorioEntity> getAllOnsEnaDiarioReservatorios() {
        LOG.debug("REST request to get all OnsEnaDiarioReservatorios");
        return onsEnaDiarioReservatorioRepository.findAll();
    }

    /**
     * {@code GET  /ons-ena-diario-reservatorios/:id} : get the "id" onsEnaDiarioReservatorio.
     *
     * @param id the id of the onsEnaDiarioReservatorioEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsEnaDiarioReservatorioEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OnsEnaDiarioReservatorioEntity> getOnsEnaDiarioReservatorio(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsEnaDiarioReservatorio : {}", id);
        Optional<OnsEnaDiarioReservatorioEntity> onsEnaDiarioReservatorioEntity = onsEnaDiarioReservatorioRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsEnaDiarioReservatorioEntity);
    }

    /**
     * {@code DELETE  /ons-ena-diario-reservatorios/:id} : delete the "id" onsEnaDiarioReservatorio.
     *
     * @param id the id of the onsEnaDiarioReservatorioEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsEnaDiarioReservatorio(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsEnaDiarioReservatorio : {}", id);
        onsEnaDiarioReservatorioRepository.deleteById(id);
        onsEnaDiarioReservatorioSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-ena-diario-reservatorios/_search?query=:query} : search for the onsEnaDiarioReservatorio corresponding
     * to the query.
     *
     * @param query the query of the onsEnaDiarioReservatorio search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsEnaDiarioReservatorioEntity> searchOnsEnaDiarioReservatorios(@RequestParam("query") String query) {
        LOG.debug("REST request to search OnsEnaDiarioReservatorios for query {}", query);
        try {
            return StreamSupport.stream(onsEnaDiarioReservatorioSearchRepository.search(query).spliterator(), false).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
