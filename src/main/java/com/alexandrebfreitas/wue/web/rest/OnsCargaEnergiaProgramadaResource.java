package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsCargaEnergiaProgramadaEntity;
import com.alexandrebfreitas.wue.repository.OnsCargaEnergiaProgramadaRepository;
import com.alexandrebfreitas.wue.repository.search.OnsCargaEnergiaProgramadaSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsCargaEnergiaProgramadaEntity}.
 */
@RestController
@RequestMapping("/api/ons-carga-energia-programadas")
@Transactional
public class OnsCargaEnergiaProgramadaResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsCargaEnergiaProgramadaResource.class);

    private static final String ENTITY_NAME = "onsCargaEnergiaProgramada";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsCargaEnergiaProgramadaRepository onsCargaEnergiaProgramadaRepository;

    private final OnsCargaEnergiaProgramadaSearchRepository onsCargaEnergiaProgramadaSearchRepository;

    public OnsCargaEnergiaProgramadaResource(
        OnsCargaEnergiaProgramadaRepository onsCargaEnergiaProgramadaRepository,
        OnsCargaEnergiaProgramadaSearchRepository onsCargaEnergiaProgramadaSearchRepository
    ) {
        this.onsCargaEnergiaProgramadaRepository = onsCargaEnergiaProgramadaRepository;
        this.onsCargaEnergiaProgramadaSearchRepository = onsCargaEnergiaProgramadaSearchRepository;
    }

    /**
     * {@code POST  /ons-carga-energia-programadas} : Create a new onsCargaEnergiaProgramada.
     *
     * @param onsCargaEnergiaProgramadaEntity the onsCargaEnergiaProgramadaEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsCargaEnergiaProgramadaEntity, or with status {@code 400 (Bad Request)} if the onsCargaEnergiaProgramada has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OnsCargaEnergiaProgramadaEntity> createOnsCargaEnergiaProgramada(
        @RequestBody OnsCargaEnergiaProgramadaEntity onsCargaEnergiaProgramadaEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to save OnsCargaEnergiaProgramada : {}", onsCargaEnergiaProgramadaEntity);
        if (onsCargaEnergiaProgramadaEntity.getId() != null) {
            throw new BadRequestAlertException("A new onsCargaEnergiaProgramada cannot already have an ID", ENTITY_NAME, "idexists");
        }
        onsCargaEnergiaProgramadaEntity = onsCargaEnergiaProgramadaRepository.save(onsCargaEnergiaProgramadaEntity);
        onsCargaEnergiaProgramadaSearchRepository.index(onsCargaEnergiaProgramadaEntity);
        return ResponseEntity.created(new URI("/api/ons-carga-energia-programadas/" + onsCargaEnergiaProgramadaEntity.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, onsCargaEnergiaProgramadaEntity.getId().toString())
            )
            .body(onsCargaEnergiaProgramadaEntity);
    }

    /**
     * {@code PUT  /ons-carga-energia-programadas/:id} : Updates an existing onsCargaEnergiaProgramada.
     *
     * @param id the id of the onsCargaEnergiaProgramadaEntity to save.
     * @param onsCargaEnergiaProgramadaEntity the onsCargaEnergiaProgramadaEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsCargaEnergiaProgramadaEntity,
     * or with status {@code 400 (Bad Request)} if the onsCargaEnergiaProgramadaEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsCargaEnergiaProgramadaEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OnsCargaEnergiaProgramadaEntity> updateOnsCargaEnergiaProgramada(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsCargaEnergiaProgramadaEntity onsCargaEnergiaProgramadaEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to update OnsCargaEnergiaProgramada : {}, {}", id, onsCargaEnergiaProgramadaEntity);
        if (onsCargaEnergiaProgramadaEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsCargaEnergiaProgramadaEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsCargaEnergiaProgramadaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsCargaEnergiaProgramadaEntity = onsCargaEnergiaProgramadaRepository.save(onsCargaEnergiaProgramadaEntity);
        onsCargaEnergiaProgramadaSearchRepository.index(onsCargaEnergiaProgramadaEntity);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, onsCargaEnergiaProgramadaEntity.getId().toString())
            )
            .body(onsCargaEnergiaProgramadaEntity);
    }

    /**
     * {@code PATCH  /ons-carga-energia-programadas/:id} : Partial updates given fields of an existing onsCargaEnergiaProgramada, field will ignore if it is null
     *
     * @param id the id of the onsCargaEnergiaProgramadaEntity to save.
     * @param onsCargaEnergiaProgramadaEntity the onsCargaEnergiaProgramadaEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsCargaEnergiaProgramadaEntity,
     * or with status {@code 400 (Bad Request)} if the onsCargaEnergiaProgramadaEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsCargaEnergiaProgramadaEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsCargaEnergiaProgramadaEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OnsCargaEnergiaProgramadaEntity> partialUpdateOnsCargaEnergiaProgramada(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsCargaEnergiaProgramadaEntity onsCargaEnergiaProgramadaEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update OnsCargaEnergiaProgramada partially : {}, {}", id, onsCargaEnergiaProgramadaEntity);
        if (onsCargaEnergiaProgramadaEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsCargaEnergiaProgramadaEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsCargaEnergiaProgramadaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsCargaEnergiaProgramadaEntity> result = onsCargaEnergiaProgramadaRepository
            .findById(onsCargaEnergiaProgramadaEntity.getId())
            .map(existingOnsCargaEnergiaProgramada -> {
                if (onsCargaEnergiaProgramadaEntity.getCodAreacarga() != null) {
                    existingOnsCargaEnergiaProgramada.setCodAreacarga(onsCargaEnergiaProgramadaEntity.getCodAreacarga());
                }
                if (onsCargaEnergiaProgramadaEntity.getDatReferencia() != null) {
                    existingOnsCargaEnergiaProgramada.setDatReferencia(onsCargaEnergiaProgramadaEntity.getDatReferencia());
                }
                if (onsCargaEnergiaProgramadaEntity.getDinReferenciautc() != null) {
                    existingOnsCargaEnergiaProgramada.setDinReferenciautc(onsCargaEnergiaProgramadaEntity.getDinReferenciautc());
                }
                if (onsCargaEnergiaProgramadaEntity.getValCargaglobalprogramada() != null) {
                    existingOnsCargaEnergiaProgramada.setValCargaglobalprogramada(
                        onsCargaEnergiaProgramadaEntity.getValCargaglobalprogramada()
                    );
                }

                return existingOnsCargaEnergiaProgramada;
            })
            .map(onsCargaEnergiaProgramadaRepository::save)
            .map(savedOnsCargaEnergiaProgramada -> {
                onsCargaEnergiaProgramadaSearchRepository.index(savedOnsCargaEnergiaProgramada);
                return savedOnsCargaEnergiaProgramada;
            });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, onsCargaEnergiaProgramadaEntity.getId().toString())
        );
    }

    /**
     * {@code GET  /ons-carga-energia-programadas} : get all the onsCargaEnergiaProgramadas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsCargaEnergiaProgramadas in body.
     */
    @GetMapping("")
    public List<OnsCargaEnergiaProgramadaEntity> getAllOnsCargaEnergiaProgramadas() {
        LOG.debug("REST request to get all OnsCargaEnergiaProgramadas");
        return onsCargaEnergiaProgramadaRepository.findAll();
    }

    /**
     * {@code GET  /ons-carga-energia-programadas/:id} : get the "id" onsCargaEnergiaProgramada.
     *
     * @param id the id of the onsCargaEnergiaProgramadaEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsCargaEnergiaProgramadaEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OnsCargaEnergiaProgramadaEntity> getOnsCargaEnergiaProgramada(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsCargaEnergiaProgramada : {}", id);
        Optional<OnsCargaEnergiaProgramadaEntity> onsCargaEnergiaProgramadaEntity = onsCargaEnergiaProgramadaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsCargaEnergiaProgramadaEntity);
    }

    /**
     * {@code DELETE  /ons-carga-energia-programadas/:id} : delete the "id" onsCargaEnergiaProgramada.
     *
     * @param id the id of the onsCargaEnergiaProgramadaEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsCargaEnergiaProgramada(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsCargaEnergiaProgramada : {}", id);
        onsCargaEnergiaProgramadaRepository.deleteById(id);
        onsCargaEnergiaProgramadaSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-carga-energia-programadas/_search?query=:query} : search for the onsCargaEnergiaProgramada corresponding
     * to the query.
     *
     * @param query the query of the onsCargaEnergiaProgramada search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsCargaEnergiaProgramadaEntity> searchOnsCargaEnergiaProgramadas(@RequestParam("query") String query) {
        LOG.debug("REST request to search OnsCargaEnergiaProgramadas for query {}", query);
        try {
            return StreamSupport.stream(onsCargaEnergiaProgramadaSearchRepository.search(query).spliterator(), false).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
