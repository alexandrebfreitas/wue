package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsCmoSemihorarioEntity;
import com.alexandrebfreitas.wue.repository.OnsCmoSemihorarioRepository;
import com.alexandrebfreitas.wue.repository.search.OnsCmoSemihorarioSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsCmoSemihorarioEntity}.
 */
@RestController
@RequestMapping("/api/ons-cmo-semihorarios")
@Transactional
public class OnsCmoSemihorarioResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsCmoSemihorarioResource.class);

    private static final String ENTITY_NAME = "onsCmoSemihorario";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsCmoSemihorarioRepository onsCmoSemihorarioRepository;

    private final OnsCmoSemihorarioSearchRepository onsCmoSemihorarioSearchRepository;

    public OnsCmoSemihorarioResource(
        OnsCmoSemihorarioRepository onsCmoSemihorarioRepository,
        OnsCmoSemihorarioSearchRepository onsCmoSemihorarioSearchRepository
    ) {
        this.onsCmoSemihorarioRepository = onsCmoSemihorarioRepository;
        this.onsCmoSemihorarioSearchRepository = onsCmoSemihorarioSearchRepository;
    }

    /**
     * {@code POST  /ons-cmo-semihorarios} : Create a new onsCmoSemihorario.
     *
     * @param onsCmoSemihorarioEntity the onsCmoSemihorarioEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsCmoSemihorarioEntity, or with status {@code 400 (Bad Request)} if the onsCmoSemihorario has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OnsCmoSemihorarioEntity> createOnsCmoSemihorario(@RequestBody OnsCmoSemihorarioEntity onsCmoSemihorarioEntity)
        throws URISyntaxException {
        LOG.debug("REST request to save OnsCmoSemihorario : {}", onsCmoSemihorarioEntity);
        if (onsCmoSemihorarioEntity.getId() != null) {
            throw new BadRequestAlertException("A new onsCmoSemihorario cannot already have an ID", ENTITY_NAME, "idexists");
        }
        onsCmoSemihorarioEntity = onsCmoSemihorarioRepository.save(onsCmoSemihorarioEntity);
        onsCmoSemihorarioSearchRepository.index(onsCmoSemihorarioEntity);
        return ResponseEntity.created(new URI("/api/ons-cmo-semihorarios/" + onsCmoSemihorarioEntity.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, onsCmoSemihorarioEntity.getId().toString()))
            .body(onsCmoSemihorarioEntity);
    }

    /**
     * {@code PUT  /ons-cmo-semihorarios/:id} : Updates an existing onsCmoSemihorario.
     *
     * @param id the id of the onsCmoSemihorarioEntity to save.
     * @param onsCmoSemihorarioEntity the onsCmoSemihorarioEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsCmoSemihorarioEntity,
     * or with status {@code 400 (Bad Request)} if the onsCmoSemihorarioEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsCmoSemihorarioEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OnsCmoSemihorarioEntity> updateOnsCmoSemihorario(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsCmoSemihorarioEntity onsCmoSemihorarioEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to update OnsCmoSemihorario : {}, {}", id, onsCmoSemihorarioEntity);
        if (onsCmoSemihorarioEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsCmoSemihorarioEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsCmoSemihorarioRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsCmoSemihorarioEntity = onsCmoSemihorarioRepository.save(onsCmoSemihorarioEntity);
        onsCmoSemihorarioSearchRepository.index(onsCmoSemihorarioEntity);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, onsCmoSemihorarioEntity.getId().toString()))
            .body(onsCmoSemihorarioEntity);
    }

    /**
     * {@code PATCH  /ons-cmo-semihorarios/:id} : Partial updates given fields of an existing onsCmoSemihorario, field will ignore if it is null
     *
     * @param id the id of the onsCmoSemihorarioEntity to save.
     * @param onsCmoSemihorarioEntity the onsCmoSemihorarioEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsCmoSemihorarioEntity,
     * or with status {@code 400 (Bad Request)} if the onsCmoSemihorarioEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsCmoSemihorarioEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsCmoSemihorarioEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OnsCmoSemihorarioEntity> partialUpdateOnsCmoSemihorario(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsCmoSemihorarioEntity onsCmoSemihorarioEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update OnsCmoSemihorario partially : {}, {}", id, onsCmoSemihorarioEntity);
        if (onsCmoSemihorarioEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsCmoSemihorarioEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsCmoSemihorarioRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsCmoSemihorarioEntity> result = onsCmoSemihorarioRepository
            .findById(onsCmoSemihorarioEntity.getId())
            .map(existingOnsCmoSemihorario -> {
                if (onsCmoSemihorarioEntity.getIdSubsistema() != null) {
                    existingOnsCmoSemihorario.setIdSubsistema(onsCmoSemihorarioEntity.getIdSubsistema());
                }
                if (onsCmoSemihorarioEntity.getNomSubsistema() != null) {
                    existingOnsCmoSemihorario.setNomSubsistema(onsCmoSemihorarioEntity.getNomSubsistema());
                }
                if (onsCmoSemihorarioEntity.getDinInstante() != null) {
                    existingOnsCmoSemihorario.setDinInstante(onsCmoSemihorarioEntity.getDinInstante());
                }
                if (onsCmoSemihorarioEntity.getValCmo() != null) {
                    existingOnsCmoSemihorario.setValCmo(onsCmoSemihorarioEntity.getValCmo());
                }

                return existingOnsCmoSemihorario;
            })
            .map(onsCmoSemihorarioRepository::save)
            .map(savedOnsCmoSemihorario -> {
                onsCmoSemihorarioSearchRepository.index(savedOnsCmoSemihorario);
                return savedOnsCmoSemihorario;
            });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, onsCmoSemihorarioEntity.getId().toString())
        );
    }

    /**
     * {@code GET  /ons-cmo-semihorarios} : get all the onsCmoSemihorarios.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsCmoSemihorarios in body.
     */
    @GetMapping("")
    public List<OnsCmoSemihorarioEntity> getAllOnsCmoSemihorarios() {
        LOG.debug("REST request to get all OnsCmoSemihorarios");
        return onsCmoSemihorarioRepository.findAll();
    }

    /**
     * {@code GET  /ons-cmo-semihorarios/:id} : get the "id" onsCmoSemihorario.
     *
     * @param id the id of the onsCmoSemihorarioEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsCmoSemihorarioEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OnsCmoSemihorarioEntity> getOnsCmoSemihorario(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsCmoSemihorario : {}", id);
        Optional<OnsCmoSemihorarioEntity> onsCmoSemihorarioEntity = onsCmoSemihorarioRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsCmoSemihorarioEntity);
    }

    /**
     * {@code DELETE  /ons-cmo-semihorarios/:id} : delete the "id" onsCmoSemihorario.
     *
     * @param id the id of the onsCmoSemihorarioEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsCmoSemihorario(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsCmoSemihorario : {}", id);
        onsCmoSemihorarioRepository.deleteById(id);
        onsCmoSemihorarioSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-cmo-semihorarios/_search?query=:query} : search for the onsCmoSemihorario corresponding
     * to the query.
     *
     * @param query the query of the onsCmoSemihorario search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsCmoSemihorarioEntity> searchOnsCmoSemihorarios(@RequestParam("query") String query) {
        LOG.debug("REST request to search OnsCmoSemihorarios for query {}", query);
        try {
            return StreamSupport.stream(onsCmoSemihorarioSearchRepository.search(query).spliterator(), false).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
