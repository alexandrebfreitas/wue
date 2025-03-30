package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsCvuUsinaTermicasEntity;
import com.alexandrebfreitas.wue.repository.OnsCvuUsinaTermicasRepository;
import com.alexandrebfreitas.wue.repository.search.OnsCvuUsinaTermicasSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsCvuUsinaTermicasEntity}.
 */
@RestController
@RequestMapping("/api/ons-cvu-usina-termicas")
@Transactional
public class OnsCvuUsinaTermicasResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsCvuUsinaTermicasResource.class);

    private static final String ENTITY_NAME = "onsCvuUsinaTermicas";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsCvuUsinaTermicasRepository onsCvuUsinaTermicasRepository;

    private final OnsCvuUsinaTermicasSearchRepository onsCvuUsinaTermicasSearchRepository;

    public OnsCvuUsinaTermicasResource(
        OnsCvuUsinaTermicasRepository onsCvuUsinaTermicasRepository,
        OnsCvuUsinaTermicasSearchRepository onsCvuUsinaTermicasSearchRepository
    ) {
        this.onsCvuUsinaTermicasRepository = onsCvuUsinaTermicasRepository;
        this.onsCvuUsinaTermicasSearchRepository = onsCvuUsinaTermicasSearchRepository;
    }

    /**
     * {@code POST  /ons-cvu-usina-termicas} : Create a new onsCvuUsinaTermicas.
     *
     * @param onsCvuUsinaTermicasEntity the onsCvuUsinaTermicasEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsCvuUsinaTermicasEntity, or with status {@code 400 (Bad Request)} if the onsCvuUsinaTermicas has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OnsCvuUsinaTermicasEntity> createOnsCvuUsinaTermicas(
        @RequestBody OnsCvuUsinaTermicasEntity onsCvuUsinaTermicasEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to save OnsCvuUsinaTermicas : {}", onsCvuUsinaTermicasEntity);
        if (onsCvuUsinaTermicasEntity.getId() != null) {
            throw new BadRequestAlertException("A new onsCvuUsinaTermicas cannot already have an ID", ENTITY_NAME, "idexists");
        }
        onsCvuUsinaTermicasEntity = onsCvuUsinaTermicasRepository.save(onsCvuUsinaTermicasEntity);
        onsCvuUsinaTermicasSearchRepository.index(onsCvuUsinaTermicasEntity);
        return ResponseEntity.created(new URI("/api/ons-cvu-usina-termicas/" + onsCvuUsinaTermicasEntity.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, onsCvuUsinaTermicasEntity.getId().toString()))
            .body(onsCvuUsinaTermicasEntity);
    }

    /**
     * {@code PUT  /ons-cvu-usina-termicas/:id} : Updates an existing onsCvuUsinaTermicas.
     *
     * @param id the id of the onsCvuUsinaTermicasEntity to save.
     * @param onsCvuUsinaTermicasEntity the onsCvuUsinaTermicasEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsCvuUsinaTermicasEntity,
     * or with status {@code 400 (Bad Request)} if the onsCvuUsinaTermicasEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsCvuUsinaTermicasEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OnsCvuUsinaTermicasEntity> updateOnsCvuUsinaTermicas(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsCvuUsinaTermicasEntity onsCvuUsinaTermicasEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to update OnsCvuUsinaTermicas : {}, {}", id, onsCvuUsinaTermicasEntity);
        if (onsCvuUsinaTermicasEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsCvuUsinaTermicasEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsCvuUsinaTermicasRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsCvuUsinaTermicasEntity = onsCvuUsinaTermicasRepository.save(onsCvuUsinaTermicasEntity);
        onsCvuUsinaTermicasSearchRepository.index(onsCvuUsinaTermicasEntity);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, onsCvuUsinaTermicasEntity.getId().toString()))
            .body(onsCvuUsinaTermicasEntity);
    }

    /**
     * {@code PATCH  /ons-cvu-usina-termicas/:id} : Partial updates given fields of an existing onsCvuUsinaTermicas, field will ignore if it is null
     *
     * @param id the id of the onsCvuUsinaTermicasEntity to save.
     * @param onsCvuUsinaTermicasEntity the onsCvuUsinaTermicasEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsCvuUsinaTermicasEntity,
     * or with status {@code 400 (Bad Request)} if the onsCvuUsinaTermicasEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsCvuUsinaTermicasEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsCvuUsinaTermicasEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OnsCvuUsinaTermicasEntity> partialUpdateOnsCvuUsinaTermicas(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsCvuUsinaTermicasEntity onsCvuUsinaTermicasEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update OnsCvuUsinaTermicas partially : {}, {}", id, onsCvuUsinaTermicasEntity);
        if (onsCvuUsinaTermicasEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsCvuUsinaTermicasEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsCvuUsinaTermicasRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsCvuUsinaTermicasEntity> result = onsCvuUsinaTermicasRepository
            .findById(onsCvuUsinaTermicasEntity.getId())
            .map(existingOnsCvuUsinaTermicas -> {
                if (onsCvuUsinaTermicasEntity.getDatIniciosemana() != null) {
                    existingOnsCvuUsinaTermicas.setDatIniciosemana(onsCvuUsinaTermicasEntity.getDatIniciosemana());
                }
                if (onsCvuUsinaTermicasEntity.getDatFimsemana() != null) {
                    existingOnsCvuUsinaTermicas.setDatFimsemana(onsCvuUsinaTermicasEntity.getDatFimsemana());
                }
                if (onsCvuUsinaTermicasEntity.getAnoReferencia() != null) {
                    existingOnsCvuUsinaTermicas.setAnoReferencia(onsCvuUsinaTermicasEntity.getAnoReferencia());
                }
                if (onsCvuUsinaTermicasEntity.getMesReferencia() != null) {
                    existingOnsCvuUsinaTermicas.setMesReferencia(onsCvuUsinaTermicasEntity.getMesReferencia());
                }
                if (onsCvuUsinaTermicasEntity.getNumRevisao() != null) {
                    existingOnsCvuUsinaTermicas.setNumRevisao(onsCvuUsinaTermicasEntity.getNumRevisao());
                }
                if (onsCvuUsinaTermicasEntity.getNomSemanaoperativa() != null) {
                    existingOnsCvuUsinaTermicas.setNomSemanaoperativa(onsCvuUsinaTermicasEntity.getNomSemanaoperativa());
                }
                if (onsCvuUsinaTermicasEntity.getCodModelos() != null) {
                    existingOnsCvuUsinaTermicas.setCodModelos(onsCvuUsinaTermicasEntity.getCodModelos());
                }
                if (onsCvuUsinaTermicasEntity.getIdSubsistema() != null) {
                    existingOnsCvuUsinaTermicas.setIdSubsistema(onsCvuUsinaTermicasEntity.getIdSubsistema());
                }
                if (onsCvuUsinaTermicasEntity.getNomSubsistema() != null) {
                    existingOnsCvuUsinaTermicas.setNomSubsistema(onsCvuUsinaTermicasEntity.getNomSubsistema());
                }
                if (onsCvuUsinaTermicasEntity.getNomUsina() != null) {
                    existingOnsCvuUsinaTermicas.setNomUsina(onsCvuUsinaTermicasEntity.getNomUsina());
                }
                if (onsCvuUsinaTermicasEntity.getValCvu() != null) {
                    existingOnsCvuUsinaTermicas.setValCvu(onsCvuUsinaTermicasEntity.getValCvu());
                }

                return existingOnsCvuUsinaTermicas;
            })
            .map(onsCvuUsinaTermicasRepository::save)
            .map(savedOnsCvuUsinaTermicas -> {
                onsCvuUsinaTermicasSearchRepository.index(savedOnsCvuUsinaTermicas);
                return savedOnsCvuUsinaTermicas;
            });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, onsCvuUsinaTermicasEntity.getId().toString())
        );
    }

    /**
     * {@code GET  /ons-cvu-usina-termicas} : get all the onsCvuUsinaTermicas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsCvuUsinaTermicas in body.
     */
    @GetMapping("")
    public List<OnsCvuUsinaTermicasEntity> getAllOnsCvuUsinaTermicas() {
        LOG.debug("REST request to get all OnsCvuUsinaTermicas");
        return onsCvuUsinaTermicasRepository.findAll();
    }

    /**
     * {@code GET  /ons-cvu-usina-termicas/:id} : get the "id" onsCvuUsinaTermicas.
     *
     * @param id the id of the onsCvuUsinaTermicasEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsCvuUsinaTermicasEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OnsCvuUsinaTermicasEntity> getOnsCvuUsinaTermicas(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsCvuUsinaTermicas : {}", id);
        Optional<OnsCvuUsinaTermicasEntity> onsCvuUsinaTermicasEntity = onsCvuUsinaTermicasRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsCvuUsinaTermicasEntity);
    }

    /**
     * {@code DELETE  /ons-cvu-usina-termicas/:id} : delete the "id" onsCvuUsinaTermicas.
     *
     * @param id the id of the onsCvuUsinaTermicasEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsCvuUsinaTermicas(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsCvuUsinaTermicas : {}", id);
        onsCvuUsinaTermicasRepository.deleteById(id);
        onsCvuUsinaTermicasSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-cvu-usina-termicas/_search?query=:query} : search for the onsCvuUsinaTermicas corresponding
     * to the query.
     *
     * @param query the query of the onsCvuUsinaTermicas search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsCvuUsinaTermicasEntity> searchOnsCvuUsinaTermicas(@RequestParam("query") String query) {
        LOG.debug("REST request to search OnsCvuUsinaTermicas for query {}", query);
        try {
            return StreamSupport.stream(onsCvuUsinaTermicasSearchRepository.search(query).spliterator(), false).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
