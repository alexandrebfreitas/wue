package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsEquipamentosControleReativosEntity;
import com.alexandrebfreitas.wue.repository.OnsEquipamentosControleReativosRepository;
import com.alexandrebfreitas.wue.repository.search.OnsEquipamentosControleReativosSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsEquipamentosControleReativosEntity}.
 */
@RestController
@RequestMapping("/api/ons-equipamentos-controle-reativos")
@Transactional
public class OnsEquipamentosControleReativosResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsEquipamentosControleReativosResource.class);

    private static final String ENTITY_NAME = "onsEquipamentosControleReativos";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsEquipamentosControleReativosRepository onsEquipamentosControleReativosRepository;

    private final OnsEquipamentosControleReativosSearchRepository onsEquipamentosControleReativosSearchRepository;

    public OnsEquipamentosControleReativosResource(
        OnsEquipamentosControleReativosRepository onsEquipamentosControleReativosRepository,
        OnsEquipamentosControleReativosSearchRepository onsEquipamentosControleReativosSearchRepository
    ) {
        this.onsEquipamentosControleReativosRepository = onsEquipamentosControleReativosRepository;
        this.onsEquipamentosControleReativosSearchRepository = onsEquipamentosControleReativosSearchRepository;
    }

    /**
     * {@code POST  /ons-equipamentos-controle-reativos} : Create a new onsEquipamentosControleReativos.
     *
     * @param onsEquipamentosControleReativosEntity the onsEquipamentosControleReativosEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsEquipamentosControleReativosEntity, or with status {@code 400 (Bad Request)} if the onsEquipamentosControleReativos has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OnsEquipamentosControleReativosEntity> createOnsEquipamentosControleReativos(
        @RequestBody OnsEquipamentosControleReativosEntity onsEquipamentosControleReativosEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to save OnsEquipamentosControleReativos : {}", onsEquipamentosControleReativosEntity);
        if (onsEquipamentosControleReativosEntity.getId() != null) {
            throw new BadRequestAlertException("A new onsEquipamentosControleReativos cannot already have an ID", ENTITY_NAME, "idexists");
        }
        onsEquipamentosControleReativosEntity = onsEquipamentosControleReativosRepository.save(onsEquipamentosControleReativosEntity);
        onsEquipamentosControleReativosSearchRepository.index(onsEquipamentosControleReativosEntity);
        return ResponseEntity.created(new URI("/api/ons-equipamentos-controle-reativos/" + onsEquipamentosControleReativosEntity.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsEquipamentosControleReativosEntity.getId().toString()
                )
            )
            .body(onsEquipamentosControleReativosEntity);
    }

    /**
     * {@code PUT  /ons-equipamentos-controle-reativos/:id} : Updates an existing onsEquipamentosControleReativos.
     *
     * @param id the id of the onsEquipamentosControleReativosEntity to save.
     * @param onsEquipamentosControleReativosEntity the onsEquipamentosControleReativosEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsEquipamentosControleReativosEntity,
     * or with status {@code 400 (Bad Request)} if the onsEquipamentosControleReativosEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsEquipamentosControleReativosEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OnsEquipamentosControleReativosEntity> updateOnsEquipamentosControleReativos(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsEquipamentosControleReativosEntity onsEquipamentosControleReativosEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to update OnsEquipamentosControleReativos : {}, {}", id, onsEquipamentosControleReativosEntity);
        if (onsEquipamentosControleReativosEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsEquipamentosControleReativosEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsEquipamentosControleReativosRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsEquipamentosControleReativosEntity = onsEquipamentosControleReativosRepository.save(onsEquipamentosControleReativosEntity);
        onsEquipamentosControleReativosSearchRepository.index(onsEquipamentosControleReativosEntity);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsEquipamentosControleReativosEntity.getId().toString()
                )
            )
            .body(onsEquipamentosControleReativosEntity);
    }

    /**
     * {@code PATCH  /ons-equipamentos-controle-reativos/:id} : Partial updates given fields of an existing onsEquipamentosControleReativos, field will ignore if it is null
     *
     * @param id the id of the onsEquipamentosControleReativosEntity to save.
     * @param onsEquipamentosControleReativosEntity the onsEquipamentosControleReativosEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsEquipamentosControleReativosEntity,
     * or with status {@code 400 (Bad Request)} if the onsEquipamentosControleReativosEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsEquipamentosControleReativosEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsEquipamentosControleReativosEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OnsEquipamentosControleReativosEntity> partialUpdateOnsEquipamentosControleReativos(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsEquipamentosControleReativosEntity onsEquipamentosControleReativosEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to partial update OnsEquipamentosControleReativos partially : {}, {}",
            id,
            onsEquipamentosControleReativosEntity
        );
        if (onsEquipamentosControleReativosEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsEquipamentosControleReativosEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsEquipamentosControleReativosRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsEquipamentosControleReativosEntity> result = onsEquipamentosControleReativosRepository
            .findById(onsEquipamentosControleReativosEntity.getId())
            .map(existingOnsEquipamentosControleReativos -> {
                if (onsEquipamentosControleReativosEntity.getIdSubsistema() != null) {
                    existingOnsEquipamentosControleReativos.setIdSubsistema(onsEquipamentosControleReativosEntity.getIdSubsistema());
                }
                if (onsEquipamentosControleReativosEntity.getNomSubsistema() != null) {
                    existingOnsEquipamentosControleReativos.setNomSubsistema(onsEquipamentosControleReativosEntity.getNomSubsistema());
                }
                if (onsEquipamentosControleReativosEntity.getIdEstado() != null) {
                    existingOnsEquipamentosControleReativos.setIdEstado(onsEquipamentosControleReativosEntity.getIdEstado());
                }
                if (onsEquipamentosControleReativosEntity.getNomEstado() != null) {
                    existingOnsEquipamentosControleReativos.setNomEstado(onsEquipamentosControleReativosEntity.getNomEstado());
                }
                if (onsEquipamentosControleReativosEntity.getNomSubestacao() != null) {
                    existingOnsEquipamentosControleReativos.setNomSubestacao(onsEquipamentosControleReativosEntity.getNomSubestacao());
                }
                if (onsEquipamentosControleReativosEntity.getNomAgenteProprietario() != null) {
                    existingOnsEquipamentosControleReativos.setNomAgenteProprietario(
                        onsEquipamentosControleReativosEntity.getNomAgenteProprietario()
                    );
                }
                if (onsEquipamentosControleReativosEntity.getNomTipoderede() != null) {
                    existingOnsEquipamentosControleReativos.setNomTipoderede(onsEquipamentosControleReativosEntity.getNomTipoderede());
                }
                if (onsEquipamentosControleReativosEntity.getNomTipoequipamento() != null) {
                    existingOnsEquipamentosControleReativos.setNomTipoequipamento(
                        onsEquipamentosControleReativosEntity.getNomTipoequipamento()
                    );
                }
                if (onsEquipamentosControleReativosEntity.getNomEquipamento() != null) {
                    existingOnsEquipamentosControleReativos.setNomEquipamento(onsEquipamentosControleReativosEntity.getNomEquipamento());
                }
                if (onsEquipamentosControleReativosEntity.getValPotreativanominalMvar() != null) {
                    existingOnsEquipamentosControleReativos.setValPotreativanominalMvar(
                        onsEquipamentosControleReativosEntity.getValPotreativanominalMvar()
                    );
                }
                if (onsEquipamentosControleReativosEntity.getValLimiteinferiorMvar() != null) {
                    existingOnsEquipamentosControleReativos.setValLimiteinferiorMvar(
                        onsEquipamentosControleReativosEntity.getValLimiteinferiorMvar()
                    );
                }
                if (onsEquipamentosControleReativosEntity.getValLimitesuperiorMvar() != null) {
                    existingOnsEquipamentosControleReativos.setValLimitesuperiorMvar(
                        onsEquipamentosControleReativosEntity.getValLimitesuperiorMvar()
                    );
                }
                if (onsEquipamentosControleReativosEntity.getDatEntradaoperacao() != null) {
                    existingOnsEquipamentosControleReativos.setDatEntradaoperacao(
                        onsEquipamentosControleReativosEntity.getDatEntradaoperacao()
                    );
                }
                if (onsEquipamentosControleReativosEntity.getDatDesativacao() != null) {
                    existingOnsEquipamentosControleReativos.setDatDesativacao(onsEquipamentosControleReativosEntity.getDatDesativacao());
                }
                if (onsEquipamentosControleReativosEntity.getCodEquipamento() != null) {
                    existingOnsEquipamentosControleReativos.setCodEquipamento(onsEquipamentosControleReativosEntity.getCodEquipamento());
                }

                return existingOnsEquipamentosControleReativos;
            })
            .map(onsEquipamentosControleReativosRepository::save)
            .map(savedOnsEquipamentosControleReativos -> {
                onsEquipamentosControleReativosSearchRepository.index(savedOnsEquipamentosControleReativos);
                return savedOnsEquipamentosControleReativos;
            });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, onsEquipamentosControleReativosEntity.getId().toString())
        );
    }

    /**
     * {@code GET  /ons-equipamentos-controle-reativos} : get all the onsEquipamentosControleReativos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsEquipamentosControleReativos in body.
     */
    @GetMapping("")
    public List<OnsEquipamentosControleReativosEntity> getAllOnsEquipamentosControleReativos() {
        LOG.debug("REST request to get all OnsEquipamentosControleReativos");
        return onsEquipamentosControleReativosRepository.findAll();
    }

    /**
     * {@code GET  /ons-equipamentos-controle-reativos/:id} : get the "id" onsEquipamentosControleReativos.
     *
     * @param id the id of the onsEquipamentosControleReativosEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsEquipamentosControleReativosEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OnsEquipamentosControleReativosEntity> getOnsEquipamentosControleReativos(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsEquipamentosControleReativos : {}", id);
        Optional<OnsEquipamentosControleReativosEntity> onsEquipamentosControleReativosEntity =
            onsEquipamentosControleReativosRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsEquipamentosControleReativosEntity);
    }

    /**
     * {@code DELETE  /ons-equipamentos-controle-reativos/:id} : delete the "id" onsEquipamentosControleReativos.
     *
     * @param id the id of the onsEquipamentosControleReativosEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsEquipamentosControleReativos(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsEquipamentosControleReativos : {}", id);
        onsEquipamentosControleReativosRepository.deleteById(id);
        onsEquipamentosControleReativosSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-equipamentos-controle-reativos/_search?query=:query} : search for the onsEquipamentosControleReativos corresponding
     * to the query.
     *
     * @param query the query of the onsEquipamentosControleReativos search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsEquipamentosControleReativosEntity> searchOnsEquipamentosControleReativos(@RequestParam("query") String query) {
        LOG.debug("REST request to search OnsEquipamentosControleReativos for query {}", query);
        try {
            return StreamSupport.stream(onsEquipamentosControleReativosSearchRepository.search(query).spliterator(), false).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
