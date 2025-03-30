package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsCargaEnergiaVerificadaEntity;
import com.alexandrebfreitas.wue.repository.OnsCargaEnergiaVerificadaRepository;
import com.alexandrebfreitas.wue.repository.search.OnsCargaEnergiaVerificadaSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsCargaEnergiaVerificadaEntity}.
 */
@RestController
@RequestMapping("/api/ons-carga-energia-verificadas")
@Transactional
public class OnsCargaEnergiaVerificadaResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsCargaEnergiaVerificadaResource.class);

    private static final String ENTITY_NAME = "onsCargaEnergiaVerificada";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsCargaEnergiaVerificadaRepository onsCargaEnergiaVerificadaRepository;

    private final OnsCargaEnergiaVerificadaSearchRepository onsCargaEnergiaVerificadaSearchRepository;

    public OnsCargaEnergiaVerificadaResource(
        OnsCargaEnergiaVerificadaRepository onsCargaEnergiaVerificadaRepository,
        OnsCargaEnergiaVerificadaSearchRepository onsCargaEnergiaVerificadaSearchRepository
    ) {
        this.onsCargaEnergiaVerificadaRepository = onsCargaEnergiaVerificadaRepository;
        this.onsCargaEnergiaVerificadaSearchRepository = onsCargaEnergiaVerificadaSearchRepository;
    }

    /**
     * {@code POST  /ons-carga-energia-verificadas} : Create a new onsCargaEnergiaVerificada.
     *
     * @param onsCargaEnergiaVerificadaEntity the onsCargaEnergiaVerificadaEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsCargaEnergiaVerificadaEntity, or with status {@code 400 (Bad Request)} if the onsCargaEnergiaVerificada has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OnsCargaEnergiaVerificadaEntity> createOnsCargaEnergiaVerificada(
        @RequestBody OnsCargaEnergiaVerificadaEntity onsCargaEnergiaVerificadaEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to save OnsCargaEnergiaVerificada : {}", onsCargaEnergiaVerificadaEntity);
        if (onsCargaEnergiaVerificadaEntity.getId() != null) {
            throw new BadRequestAlertException("A new onsCargaEnergiaVerificada cannot already have an ID", ENTITY_NAME, "idexists");
        }
        onsCargaEnergiaVerificadaEntity = onsCargaEnergiaVerificadaRepository.save(onsCargaEnergiaVerificadaEntity);
        onsCargaEnergiaVerificadaSearchRepository.index(onsCargaEnergiaVerificadaEntity);
        return ResponseEntity.created(new URI("/api/ons-carga-energia-verificadas/" + onsCargaEnergiaVerificadaEntity.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, onsCargaEnergiaVerificadaEntity.getId().toString())
            )
            .body(onsCargaEnergiaVerificadaEntity);
    }

    /**
     * {@code PUT  /ons-carga-energia-verificadas/:id} : Updates an existing onsCargaEnergiaVerificada.
     *
     * @param id the id of the onsCargaEnergiaVerificadaEntity to save.
     * @param onsCargaEnergiaVerificadaEntity the onsCargaEnergiaVerificadaEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsCargaEnergiaVerificadaEntity,
     * or with status {@code 400 (Bad Request)} if the onsCargaEnergiaVerificadaEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsCargaEnergiaVerificadaEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OnsCargaEnergiaVerificadaEntity> updateOnsCargaEnergiaVerificada(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsCargaEnergiaVerificadaEntity onsCargaEnergiaVerificadaEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to update OnsCargaEnergiaVerificada : {}, {}", id, onsCargaEnergiaVerificadaEntity);
        if (onsCargaEnergiaVerificadaEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsCargaEnergiaVerificadaEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsCargaEnergiaVerificadaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsCargaEnergiaVerificadaEntity = onsCargaEnergiaVerificadaRepository.save(onsCargaEnergiaVerificadaEntity);
        onsCargaEnergiaVerificadaSearchRepository.index(onsCargaEnergiaVerificadaEntity);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, onsCargaEnergiaVerificadaEntity.getId().toString())
            )
            .body(onsCargaEnergiaVerificadaEntity);
    }

    /**
     * {@code PATCH  /ons-carga-energia-verificadas/:id} : Partial updates given fields of an existing onsCargaEnergiaVerificada, field will ignore if it is null
     *
     * @param id the id of the onsCargaEnergiaVerificadaEntity to save.
     * @param onsCargaEnergiaVerificadaEntity the onsCargaEnergiaVerificadaEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsCargaEnergiaVerificadaEntity,
     * or with status {@code 400 (Bad Request)} if the onsCargaEnergiaVerificadaEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsCargaEnergiaVerificadaEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsCargaEnergiaVerificadaEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OnsCargaEnergiaVerificadaEntity> partialUpdateOnsCargaEnergiaVerificada(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsCargaEnergiaVerificadaEntity onsCargaEnergiaVerificadaEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update OnsCargaEnergiaVerificada partially : {}, {}", id, onsCargaEnergiaVerificadaEntity);
        if (onsCargaEnergiaVerificadaEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsCargaEnergiaVerificadaEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsCargaEnergiaVerificadaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsCargaEnergiaVerificadaEntity> result = onsCargaEnergiaVerificadaRepository
            .findById(onsCargaEnergiaVerificadaEntity.getId())
            .map(existingOnsCargaEnergiaVerificada -> {
                if (onsCargaEnergiaVerificadaEntity.getCodAreacarga() != null) {
                    existingOnsCargaEnergiaVerificada.setCodAreacarga(onsCargaEnergiaVerificadaEntity.getCodAreacarga());
                }
                if (onsCargaEnergiaVerificadaEntity.getDatReferencia() != null) {
                    existingOnsCargaEnergiaVerificada.setDatReferencia(onsCargaEnergiaVerificadaEntity.getDatReferencia());
                }
                if (onsCargaEnergiaVerificadaEntity.getDinReferenciautc() != null) {
                    existingOnsCargaEnergiaVerificada.setDinReferenciautc(onsCargaEnergiaVerificadaEntity.getDinReferenciautc());
                }
                if (onsCargaEnergiaVerificadaEntity.getValCargaglobal() != null) {
                    existingOnsCargaEnergiaVerificada.setValCargaglobal(onsCargaEnergiaVerificadaEntity.getValCargaglobal());
                }
                if (onsCargaEnergiaVerificadaEntity.getValCargaglobalsmmg() != null) {
                    existingOnsCargaEnergiaVerificada.setValCargaglobalsmmg(onsCargaEnergiaVerificadaEntity.getValCargaglobalsmmg());
                }
                if (onsCargaEnergiaVerificadaEntity.getValCargammgd() != null) {
                    existingOnsCargaEnergiaVerificada.setValCargammgd(onsCargaEnergiaVerificadaEntity.getValCargammgd());
                }
                if (onsCargaEnergiaVerificadaEntity.getValCargaglobalcons() != null) {
                    existingOnsCargaEnergiaVerificada.setValCargaglobalcons(onsCargaEnergiaVerificadaEntity.getValCargaglobalcons());
                }
                if (onsCargaEnergiaVerificadaEntity.getValConsistencia() != null) {
                    existingOnsCargaEnergiaVerificada.setValConsistencia(onsCargaEnergiaVerificadaEntity.getValConsistencia());
                }
                if (onsCargaEnergiaVerificadaEntity.getValCargasupervisionada() != null) {
                    existingOnsCargaEnergiaVerificada.setValCargasupervisionada(
                        onsCargaEnergiaVerificadaEntity.getValCargasupervisionada()
                    );
                }
                if (onsCargaEnergiaVerificadaEntity.getValCarganaosupervisionada() != null) {
                    existingOnsCargaEnergiaVerificada.setValCarganaosupervisionada(
                        onsCargaEnergiaVerificadaEntity.getValCarganaosupervisionada()
                    );
                }

                return existingOnsCargaEnergiaVerificada;
            })
            .map(onsCargaEnergiaVerificadaRepository::save)
            .map(savedOnsCargaEnergiaVerificada -> {
                onsCargaEnergiaVerificadaSearchRepository.index(savedOnsCargaEnergiaVerificada);
                return savedOnsCargaEnergiaVerificada;
            });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, onsCargaEnergiaVerificadaEntity.getId().toString())
        );
    }

    /**
     * {@code GET  /ons-carga-energia-verificadas} : get all the onsCargaEnergiaVerificadas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsCargaEnergiaVerificadas in body.
     */
    @GetMapping("")
    public List<OnsCargaEnergiaVerificadaEntity> getAllOnsCargaEnergiaVerificadas() {
        LOG.debug("REST request to get all OnsCargaEnergiaVerificadas");
        return onsCargaEnergiaVerificadaRepository.findAll();
    }

    /**
     * {@code GET  /ons-carga-energia-verificadas/:id} : get the "id" onsCargaEnergiaVerificada.
     *
     * @param id the id of the onsCargaEnergiaVerificadaEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsCargaEnergiaVerificadaEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OnsCargaEnergiaVerificadaEntity> getOnsCargaEnergiaVerificada(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsCargaEnergiaVerificada : {}", id);
        Optional<OnsCargaEnergiaVerificadaEntity> onsCargaEnergiaVerificadaEntity = onsCargaEnergiaVerificadaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsCargaEnergiaVerificadaEntity);
    }

    /**
     * {@code DELETE  /ons-carga-energia-verificadas/:id} : delete the "id" onsCargaEnergiaVerificada.
     *
     * @param id the id of the onsCargaEnergiaVerificadaEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsCargaEnergiaVerificada(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsCargaEnergiaVerificada : {}", id);
        onsCargaEnergiaVerificadaRepository.deleteById(id);
        onsCargaEnergiaVerificadaSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-carga-energia-verificadas/_search?query=:query} : search for the onsCargaEnergiaVerificada corresponding
     * to the query.
     *
     * @param query the query of the onsCargaEnergiaVerificada search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsCargaEnergiaVerificadaEntity> searchOnsCargaEnergiaVerificadas(@RequestParam("query") String query) {
        LOG.debug("REST request to search OnsCargaEnergiaVerificadas for query {}", query);
        try {
            return StreamSupport.stream(onsCargaEnergiaVerificadaSearchRepository.search(query).spliterator(), false).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
