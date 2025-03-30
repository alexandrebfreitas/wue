package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsTaxasTeifaETeipEntity;
import com.alexandrebfreitas.wue.repository.OnsTaxasTeifaETeipRepository;
import com.alexandrebfreitas.wue.repository.search.OnsTaxasTeifaETeipSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsTaxasTeifaETeipEntity}.
 */
@RestController
@RequestMapping("/api/ons-taxas-teifa-e-teips")
@Transactional
public class OnsTaxasTeifaETeipResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsTaxasTeifaETeipResource.class);

    private static final String ENTITY_NAME = "onsTaxasTeifaETeip";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsTaxasTeifaETeipRepository onsTaxasTeifaETeipRepository;

    private final OnsTaxasTeifaETeipSearchRepository onsTaxasTeifaETeipSearchRepository;

    public OnsTaxasTeifaETeipResource(
        OnsTaxasTeifaETeipRepository onsTaxasTeifaETeipRepository,
        OnsTaxasTeifaETeipSearchRepository onsTaxasTeifaETeipSearchRepository
    ) {
        this.onsTaxasTeifaETeipRepository = onsTaxasTeifaETeipRepository;
        this.onsTaxasTeifaETeipSearchRepository = onsTaxasTeifaETeipSearchRepository;
    }

    /**
     * {@code POST  /ons-taxas-teifa-e-teips} : Create a new onsTaxasTeifaETeip.
     *
     * @param onsTaxasTeifaETeipEntity the onsTaxasTeifaETeipEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsTaxasTeifaETeipEntity, or with status {@code 400 (Bad Request)} if the onsTaxasTeifaETeip has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OnsTaxasTeifaETeipEntity> createOnsTaxasTeifaETeip(
        @RequestBody OnsTaxasTeifaETeipEntity onsTaxasTeifaETeipEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to save OnsTaxasTeifaETeip : {}", onsTaxasTeifaETeipEntity);
        if (onsTaxasTeifaETeipEntity.getId() != null) {
            throw new BadRequestAlertException("A new onsTaxasTeifaETeip cannot already have an ID", ENTITY_NAME, "idexists");
        }
        onsTaxasTeifaETeipEntity = onsTaxasTeifaETeipRepository.save(onsTaxasTeifaETeipEntity);
        onsTaxasTeifaETeipSearchRepository.index(onsTaxasTeifaETeipEntity);
        return ResponseEntity.created(new URI("/api/ons-taxas-teifa-e-teips/" + onsTaxasTeifaETeipEntity.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, onsTaxasTeifaETeipEntity.getId().toString()))
            .body(onsTaxasTeifaETeipEntity);
    }

    /**
     * {@code PUT  /ons-taxas-teifa-e-teips/:id} : Updates an existing onsTaxasTeifaETeip.
     *
     * @param id the id of the onsTaxasTeifaETeipEntity to save.
     * @param onsTaxasTeifaETeipEntity the onsTaxasTeifaETeipEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsTaxasTeifaETeipEntity,
     * or with status {@code 400 (Bad Request)} if the onsTaxasTeifaETeipEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsTaxasTeifaETeipEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OnsTaxasTeifaETeipEntity> updateOnsTaxasTeifaETeip(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsTaxasTeifaETeipEntity onsTaxasTeifaETeipEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to update OnsTaxasTeifaETeip : {}, {}", id, onsTaxasTeifaETeipEntity);
        if (onsTaxasTeifaETeipEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsTaxasTeifaETeipEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsTaxasTeifaETeipRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsTaxasTeifaETeipEntity = onsTaxasTeifaETeipRepository.save(onsTaxasTeifaETeipEntity);
        onsTaxasTeifaETeipSearchRepository.index(onsTaxasTeifaETeipEntity);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, onsTaxasTeifaETeipEntity.getId().toString()))
            .body(onsTaxasTeifaETeipEntity);
    }

    /**
     * {@code PATCH  /ons-taxas-teifa-e-teips/:id} : Partial updates given fields of an existing onsTaxasTeifaETeip, field will ignore if it is null
     *
     * @param id the id of the onsTaxasTeifaETeipEntity to save.
     * @param onsTaxasTeifaETeipEntity the onsTaxasTeifaETeipEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsTaxasTeifaETeipEntity,
     * or with status {@code 400 (Bad Request)} if the onsTaxasTeifaETeipEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsTaxasTeifaETeipEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsTaxasTeifaETeipEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OnsTaxasTeifaETeipEntity> partialUpdateOnsTaxasTeifaETeip(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsTaxasTeifaETeipEntity onsTaxasTeifaETeipEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update OnsTaxasTeifaETeip partially : {}, {}", id, onsTaxasTeifaETeipEntity);
        if (onsTaxasTeifaETeipEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsTaxasTeifaETeipEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsTaxasTeifaETeipRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsTaxasTeifaETeipEntity> result = onsTaxasTeifaETeipRepository
            .findById(onsTaxasTeifaETeipEntity.getId())
            .map(existingOnsTaxasTeifaETeip -> {
                if (onsTaxasTeifaETeipEntity.getNomUsina() != null) {
                    existingOnsTaxasTeifaETeip.setNomUsina(onsTaxasTeifaETeipEntity.getNomUsina());
                }
                if (onsTaxasTeifaETeipEntity.getCodCeg() != null) {
                    existingOnsTaxasTeifaETeip.setCodCeg(onsTaxasTeifaETeipEntity.getCodCeg());
                }
                if (onsTaxasTeifaETeipEntity.getIdTipousina() != null) {
                    existingOnsTaxasTeifaETeip.setIdTipousina(onsTaxasTeifaETeipEntity.getIdTipousina());
                }
                if (onsTaxasTeifaETeipEntity.getDinMes() != null) {
                    existingOnsTaxasTeifaETeip.setDinMes(onsTaxasTeifaETeipEntity.getDinMes());
                }
                if (onsTaxasTeifaETeipEntity.getNomTaxa() != null) {
                    existingOnsTaxasTeifaETeip.setNomTaxa(onsTaxasTeifaETeipEntity.getNomTaxa());
                }
                if (onsTaxasTeifaETeipEntity.getValTaxa() != null) {
                    existingOnsTaxasTeifaETeip.setValTaxa(onsTaxasTeifaETeipEntity.getValTaxa());
                }
                if (onsTaxasTeifaETeipEntity.getNumVersao() != null) {
                    existingOnsTaxasTeifaETeip.setNumVersao(onsTaxasTeifaETeipEntity.getNumVersao());
                }
                if (onsTaxasTeifaETeipEntity.getDinInstante() != null) {
                    existingOnsTaxasTeifaETeip.setDinInstante(onsTaxasTeifaETeipEntity.getDinInstante());
                }

                return existingOnsTaxasTeifaETeip;
            })
            .map(onsTaxasTeifaETeipRepository::save)
            .map(savedOnsTaxasTeifaETeip -> {
                onsTaxasTeifaETeipSearchRepository.index(savedOnsTaxasTeifaETeip);
                return savedOnsTaxasTeifaETeip;
            });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, onsTaxasTeifaETeipEntity.getId().toString())
        );
    }

    /**
     * {@code GET  /ons-taxas-teifa-e-teips} : get all the onsTaxasTeifaETeips.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsTaxasTeifaETeips in body.
     */
    @GetMapping("")
    public List<OnsTaxasTeifaETeipEntity> getAllOnsTaxasTeifaETeips() {
        LOG.debug("REST request to get all OnsTaxasTeifaETeips");
        return onsTaxasTeifaETeipRepository.findAll();
    }

    /**
     * {@code GET  /ons-taxas-teifa-e-teips/:id} : get the "id" onsTaxasTeifaETeip.
     *
     * @param id the id of the onsTaxasTeifaETeipEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsTaxasTeifaETeipEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OnsTaxasTeifaETeipEntity> getOnsTaxasTeifaETeip(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsTaxasTeifaETeip : {}", id);
        Optional<OnsTaxasTeifaETeipEntity> onsTaxasTeifaETeipEntity = onsTaxasTeifaETeipRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsTaxasTeifaETeipEntity);
    }

    /**
     * {@code DELETE  /ons-taxas-teifa-e-teips/:id} : delete the "id" onsTaxasTeifaETeip.
     *
     * @param id the id of the onsTaxasTeifaETeipEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsTaxasTeifaETeip(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsTaxasTeifaETeip : {}", id);
        onsTaxasTeifaETeipRepository.deleteById(id);
        onsTaxasTeifaETeipSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-taxas-teifa-e-teips/_search?query=:query} : search for the onsTaxasTeifaETeip corresponding
     * to the query.
     *
     * @param query the query of the onsTaxasTeifaETeip search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsTaxasTeifaETeipEntity> searchOnsTaxasTeifaETeips(@RequestParam("query") String query) {
        LOG.debug("REST request to search OnsTaxasTeifaETeips for query {}", query);
        try {
            return StreamSupport.stream(onsTaxasTeifaETeipSearchRepository.search(query).spliterator(), false).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
