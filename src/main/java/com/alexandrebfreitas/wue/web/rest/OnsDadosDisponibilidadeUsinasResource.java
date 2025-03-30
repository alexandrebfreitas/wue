package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsDadosDisponibilidadeUsinasEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosDisponibilidadeUsinasRepository;
import com.alexandrebfreitas.wue.repository.search.OnsDadosDisponibilidadeUsinasSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsDadosDisponibilidadeUsinasEntity}.
 */
@RestController
@RequestMapping("/api/ons-dados-disponibilidade-usinas")
@Transactional
public class OnsDadosDisponibilidadeUsinasResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsDadosDisponibilidadeUsinasResource.class);

    private static final String ENTITY_NAME = "onsDadosDisponibilidadeUsinas";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsDadosDisponibilidadeUsinasRepository onsDadosDisponibilidadeUsinasRepository;

    private final OnsDadosDisponibilidadeUsinasSearchRepository onsDadosDisponibilidadeUsinasSearchRepository;

    public OnsDadosDisponibilidadeUsinasResource(
        OnsDadosDisponibilidadeUsinasRepository onsDadosDisponibilidadeUsinasRepository,
        OnsDadosDisponibilidadeUsinasSearchRepository onsDadosDisponibilidadeUsinasSearchRepository
    ) {
        this.onsDadosDisponibilidadeUsinasRepository = onsDadosDisponibilidadeUsinasRepository;
        this.onsDadosDisponibilidadeUsinasSearchRepository = onsDadosDisponibilidadeUsinasSearchRepository;
    }

    /**
     * {@code POST  /ons-dados-disponibilidade-usinas} : Create a new onsDadosDisponibilidadeUsinas.
     *
     * @param onsDadosDisponibilidadeUsinasEntity the onsDadosDisponibilidadeUsinasEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsDadosDisponibilidadeUsinasEntity, or with status {@code 400 (Bad Request)} if the onsDadosDisponibilidadeUsinas has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OnsDadosDisponibilidadeUsinasEntity> createOnsDadosDisponibilidadeUsinas(
        @RequestBody OnsDadosDisponibilidadeUsinasEntity onsDadosDisponibilidadeUsinasEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to save OnsDadosDisponibilidadeUsinas : {}", onsDadosDisponibilidadeUsinasEntity);
        if (onsDadosDisponibilidadeUsinasEntity.getId() != null) {
            throw new BadRequestAlertException("A new onsDadosDisponibilidadeUsinas cannot already have an ID", ENTITY_NAME, "idexists");
        }
        onsDadosDisponibilidadeUsinasEntity = onsDadosDisponibilidadeUsinasRepository.save(onsDadosDisponibilidadeUsinasEntity);
        onsDadosDisponibilidadeUsinasSearchRepository.index(onsDadosDisponibilidadeUsinasEntity);
        return ResponseEntity.created(new URI("/api/ons-dados-disponibilidade-usinas/" + onsDadosDisponibilidadeUsinasEntity.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsDadosDisponibilidadeUsinasEntity.getId().toString()
                )
            )
            .body(onsDadosDisponibilidadeUsinasEntity);
    }

    /**
     * {@code PUT  /ons-dados-disponibilidade-usinas/:id} : Updates an existing onsDadosDisponibilidadeUsinas.
     *
     * @param id the id of the onsDadosDisponibilidadeUsinasEntity to save.
     * @param onsDadosDisponibilidadeUsinasEntity the onsDadosDisponibilidadeUsinasEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsDadosDisponibilidadeUsinasEntity,
     * or with status {@code 400 (Bad Request)} if the onsDadosDisponibilidadeUsinasEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsDadosDisponibilidadeUsinasEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OnsDadosDisponibilidadeUsinasEntity> updateOnsDadosDisponibilidadeUsinas(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsDadosDisponibilidadeUsinasEntity onsDadosDisponibilidadeUsinasEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to update OnsDadosDisponibilidadeUsinas : {}, {}", id, onsDadosDisponibilidadeUsinasEntity);
        if (onsDadosDisponibilidadeUsinasEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsDadosDisponibilidadeUsinasEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsDadosDisponibilidadeUsinasRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsDadosDisponibilidadeUsinasEntity = onsDadosDisponibilidadeUsinasRepository.save(onsDadosDisponibilidadeUsinasEntity);
        onsDadosDisponibilidadeUsinasSearchRepository.index(onsDadosDisponibilidadeUsinasEntity);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsDadosDisponibilidadeUsinasEntity.getId().toString()
                )
            )
            .body(onsDadosDisponibilidadeUsinasEntity);
    }

    /**
     * {@code PATCH  /ons-dados-disponibilidade-usinas/:id} : Partial updates given fields of an existing onsDadosDisponibilidadeUsinas, field will ignore if it is null
     *
     * @param id the id of the onsDadosDisponibilidadeUsinasEntity to save.
     * @param onsDadosDisponibilidadeUsinasEntity the onsDadosDisponibilidadeUsinasEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsDadosDisponibilidadeUsinasEntity,
     * or with status {@code 400 (Bad Request)} if the onsDadosDisponibilidadeUsinasEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsDadosDisponibilidadeUsinasEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsDadosDisponibilidadeUsinasEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OnsDadosDisponibilidadeUsinasEntity> partialUpdateOnsDadosDisponibilidadeUsinas(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsDadosDisponibilidadeUsinasEntity onsDadosDisponibilidadeUsinasEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to partial update OnsDadosDisponibilidadeUsinas partially : {}, {}",
            id,
            onsDadosDisponibilidadeUsinasEntity
        );
        if (onsDadosDisponibilidadeUsinasEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsDadosDisponibilidadeUsinasEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsDadosDisponibilidadeUsinasRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsDadosDisponibilidadeUsinasEntity> result = onsDadosDisponibilidadeUsinasRepository
            .findById(onsDadosDisponibilidadeUsinasEntity.getId())
            .map(existingOnsDadosDisponibilidadeUsinas -> {
                if (onsDadosDisponibilidadeUsinasEntity.getIdSubsistema() != null) {
                    existingOnsDadosDisponibilidadeUsinas.setIdSubsistema(onsDadosDisponibilidadeUsinasEntity.getIdSubsistema());
                }
                if (onsDadosDisponibilidadeUsinasEntity.getNomSubsistema() != null) {
                    existingOnsDadosDisponibilidadeUsinas.setNomSubsistema(onsDadosDisponibilidadeUsinasEntity.getNomSubsistema());
                }
                if (onsDadosDisponibilidadeUsinasEntity.getIdEstado() != null) {
                    existingOnsDadosDisponibilidadeUsinas.setIdEstado(onsDadosDisponibilidadeUsinasEntity.getIdEstado());
                }
                if (onsDadosDisponibilidadeUsinasEntity.getNomEstado() != null) {
                    existingOnsDadosDisponibilidadeUsinas.setNomEstado(onsDadosDisponibilidadeUsinasEntity.getNomEstado());
                }
                if (onsDadosDisponibilidadeUsinasEntity.getNomUsina() != null) {
                    existingOnsDadosDisponibilidadeUsinas.setNomUsina(onsDadosDisponibilidadeUsinasEntity.getNomUsina());
                }
                if (onsDadosDisponibilidadeUsinasEntity.getIdTipousina() != null) {
                    existingOnsDadosDisponibilidadeUsinas.setIdTipousina(onsDadosDisponibilidadeUsinasEntity.getIdTipousina());
                }
                if (onsDadosDisponibilidadeUsinasEntity.getNomTipocombustivel() != null) {
                    existingOnsDadosDisponibilidadeUsinas.setNomTipocombustivel(
                        onsDadosDisponibilidadeUsinasEntity.getNomTipocombustivel()
                    );
                }
                if (onsDadosDisponibilidadeUsinasEntity.getIdOns() != null) {
                    existingOnsDadosDisponibilidadeUsinas.setIdOns(onsDadosDisponibilidadeUsinasEntity.getIdOns());
                }
                if (onsDadosDisponibilidadeUsinasEntity.getCeg() != null) {
                    existingOnsDadosDisponibilidadeUsinas.setCeg(onsDadosDisponibilidadeUsinasEntity.getCeg());
                }
                if (onsDadosDisponibilidadeUsinasEntity.getDinInstante() != null) {
                    existingOnsDadosDisponibilidadeUsinas.setDinInstante(onsDadosDisponibilidadeUsinasEntity.getDinInstante());
                }
                if (onsDadosDisponibilidadeUsinasEntity.getValPotenciainstalada() != null) {
                    existingOnsDadosDisponibilidadeUsinas.setValPotenciainstalada(
                        onsDadosDisponibilidadeUsinasEntity.getValPotenciainstalada()
                    );
                }
                if (onsDadosDisponibilidadeUsinasEntity.getValDispoperacional() != null) {
                    existingOnsDadosDisponibilidadeUsinas.setValDispoperacional(
                        onsDadosDisponibilidadeUsinasEntity.getValDispoperacional()
                    );
                }
                if (onsDadosDisponibilidadeUsinasEntity.getValDispsincronizada() != null) {
                    existingOnsDadosDisponibilidadeUsinas.setValDispsincronizada(
                        onsDadosDisponibilidadeUsinasEntity.getValDispsincronizada()
                    );
                }

                return existingOnsDadosDisponibilidadeUsinas;
            })
            .map(onsDadosDisponibilidadeUsinasRepository::save)
            .map(savedOnsDadosDisponibilidadeUsinas -> {
                onsDadosDisponibilidadeUsinasSearchRepository.index(savedOnsDadosDisponibilidadeUsinas);
                return savedOnsDadosDisponibilidadeUsinas;
            });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, onsDadosDisponibilidadeUsinasEntity.getId().toString())
        );
    }

    /**
     * {@code GET  /ons-dados-disponibilidade-usinas} : get all the onsDadosDisponibilidadeUsinas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsDadosDisponibilidadeUsinas in body.
     */
    @GetMapping("")
    public List<OnsDadosDisponibilidadeUsinasEntity> getAllOnsDadosDisponibilidadeUsinas() {
        LOG.debug("REST request to get all OnsDadosDisponibilidadeUsinas");
        return onsDadosDisponibilidadeUsinasRepository.findAll();
    }

    /**
     * {@code GET  /ons-dados-disponibilidade-usinas/:id} : get the "id" onsDadosDisponibilidadeUsinas.
     *
     * @param id the id of the onsDadosDisponibilidadeUsinasEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsDadosDisponibilidadeUsinasEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OnsDadosDisponibilidadeUsinasEntity> getOnsDadosDisponibilidadeUsinas(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsDadosDisponibilidadeUsinas : {}", id);
        Optional<OnsDadosDisponibilidadeUsinasEntity> onsDadosDisponibilidadeUsinasEntity =
            onsDadosDisponibilidadeUsinasRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsDadosDisponibilidadeUsinasEntity);
    }

    /**
     * {@code DELETE  /ons-dados-disponibilidade-usinas/:id} : delete the "id" onsDadosDisponibilidadeUsinas.
     *
     * @param id the id of the onsDadosDisponibilidadeUsinasEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsDadosDisponibilidadeUsinas(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsDadosDisponibilidadeUsinas : {}", id);
        onsDadosDisponibilidadeUsinasRepository.deleteById(id);
        onsDadosDisponibilidadeUsinasSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-dados-disponibilidade-usinas/_search?query=:query} : search for the onsDadosDisponibilidadeUsinas corresponding
     * to the query.
     *
     * @param query the query of the onsDadosDisponibilidadeUsinas search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsDadosDisponibilidadeUsinasEntity> searchOnsDadosDisponibilidadeUsinas(@RequestParam("query") String query) {
        LOG.debug("REST request to search OnsDadosDisponibilidadeUsinas for query {}", query);
        try {
            return StreamSupport.stream(onsDadosDisponibilidadeUsinasSearchRepository.search(query).spliterator(), false).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
