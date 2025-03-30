package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsCapacidadeInstaladaGeracaoEntity;
import com.alexandrebfreitas.wue.repository.OnsCapacidadeInstaladaGeracaoRepository;
import com.alexandrebfreitas.wue.repository.search.OnsCapacidadeInstaladaGeracaoSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsCapacidadeInstaladaGeracaoEntity}.
 */
@RestController
@RequestMapping("/api/ons-capacidade-instalada-geracaos")
@Transactional
public class OnsCapacidadeInstaladaGeracaoResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsCapacidadeInstaladaGeracaoResource.class);

    private static final String ENTITY_NAME = "onsCapacidadeInstaladaGeracao";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsCapacidadeInstaladaGeracaoRepository onsCapacidadeInstaladaGeracaoRepository;

    private final OnsCapacidadeInstaladaGeracaoSearchRepository onsCapacidadeInstaladaGeracaoSearchRepository;

    public OnsCapacidadeInstaladaGeracaoResource(
        OnsCapacidadeInstaladaGeracaoRepository onsCapacidadeInstaladaGeracaoRepository,
        OnsCapacidadeInstaladaGeracaoSearchRepository onsCapacidadeInstaladaGeracaoSearchRepository
    ) {
        this.onsCapacidadeInstaladaGeracaoRepository = onsCapacidadeInstaladaGeracaoRepository;
        this.onsCapacidadeInstaladaGeracaoSearchRepository = onsCapacidadeInstaladaGeracaoSearchRepository;
    }

    /**
     * {@code POST  /ons-capacidade-instalada-geracaos} : Create a new onsCapacidadeInstaladaGeracao.
     *
     * @param onsCapacidadeInstaladaGeracaoEntity the onsCapacidadeInstaladaGeracaoEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsCapacidadeInstaladaGeracaoEntity, or with status {@code 400 (Bad Request)} if the onsCapacidadeInstaladaGeracao has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OnsCapacidadeInstaladaGeracaoEntity> createOnsCapacidadeInstaladaGeracao(
        @RequestBody OnsCapacidadeInstaladaGeracaoEntity onsCapacidadeInstaladaGeracaoEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to save OnsCapacidadeInstaladaGeracao : {}", onsCapacidadeInstaladaGeracaoEntity);
        if (onsCapacidadeInstaladaGeracaoEntity.getId() != null) {
            throw new BadRequestAlertException("A new onsCapacidadeInstaladaGeracao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        onsCapacidadeInstaladaGeracaoEntity = onsCapacidadeInstaladaGeracaoRepository.save(onsCapacidadeInstaladaGeracaoEntity);
        onsCapacidadeInstaladaGeracaoSearchRepository.index(onsCapacidadeInstaladaGeracaoEntity);
        return ResponseEntity.created(new URI("/api/ons-capacidade-instalada-geracaos/" + onsCapacidadeInstaladaGeracaoEntity.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsCapacidadeInstaladaGeracaoEntity.getId().toString()
                )
            )
            .body(onsCapacidadeInstaladaGeracaoEntity);
    }

    /**
     * {@code PUT  /ons-capacidade-instalada-geracaos/:id} : Updates an existing onsCapacidadeInstaladaGeracao.
     *
     * @param id the id of the onsCapacidadeInstaladaGeracaoEntity to save.
     * @param onsCapacidadeInstaladaGeracaoEntity the onsCapacidadeInstaladaGeracaoEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsCapacidadeInstaladaGeracaoEntity,
     * or with status {@code 400 (Bad Request)} if the onsCapacidadeInstaladaGeracaoEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsCapacidadeInstaladaGeracaoEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OnsCapacidadeInstaladaGeracaoEntity> updateOnsCapacidadeInstaladaGeracao(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsCapacidadeInstaladaGeracaoEntity onsCapacidadeInstaladaGeracaoEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to update OnsCapacidadeInstaladaGeracao : {}, {}", id, onsCapacidadeInstaladaGeracaoEntity);
        if (onsCapacidadeInstaladaGeracaoEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsCapacidadeInstaladaGeracaoEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsCapacidadeInstaladaGeracaoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsCapacidadeInstaladaGeracaoEntity = onsCapacidadeInstaladaGeracaoRepository.save(onsCapacidadeInstaladaGeracaoEntity);
        onsCapacidadeInstaladaGeracaoSearchRepository.index(onsCapacidadeInstaladaGeracaoEntity);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsCapacidadeInstaladaGeracaoEntity.getId().toString()
                )
            )
            .body(onsCapacidadeInstaladaGeracaoEntity);
    }

    /**
     * {@code PATCH  /ons-capacidade-instalada-geracaos/:id} : Partial updates given fields of an existing onsCapacidadeInstaladaGeracao, field will ignore if it is null
     *
     * @param id the id of the onsCapacidadeInstaladaGeracaoEntity to save.
     * @param onsCapacidadeInstaladaGeracaoEntity the onsCapacidadeInstaladaGeracaoEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsCapacidadeInstaladaGeracaoEntity,
     * or with status {@code 400 (Bad Request)} if the onsCapacidadeInstaladaGeracaoEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsCapacidadeInstaladaGeracaoEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsCapacidadeInstaladaGeracaoEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OnsCapacidadeInstaladaGeracaoEntity> partialUpdateOnsCapacidadeInstaladaGeracao(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsCapacidadeInstaladaGeracaoEntity onsCapacidadeInstaladaGeracaoEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to partial update OnsCapacidadeInstaladaGeracao partially : {}, {}",
            id,
            onsCapacidadeInstaladaGeracaoEntity
        );
        if (onsCapacidadeInstaladaGeracaoEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsCapacidadeInstaladaGeracaoEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsCapacidadeInstaladaGeracaoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsCapacidadeInstaladaGeracaoEntity> result = onsCapacidadeInstaladaGeracaoRepository
            .findById(onsCapacidadeInstaladaGeracaoEntity.getId())
            .map(existingOnsCapacidadeInstaladaGeracao -> {
                if (onsCapacidadeInstaladaGeracaoEntity.getIdSubsistema() != null) {
                    existingOnsCapacidadeInstaladaGeracao.setIdSubsistema(onsCapacidadeInstaladaGeracaoEntity.getIdSubsistema());
                }
                if (onsCapacidadeInstaladaGeracaoEntity.getNomSubsistema() != null) {
                    existingOnsCapacidadeInstaladaGeracao.setNomSubsistema(onsCapacidadeInstaladaGeracaoEntity.getNomSubsistema());
                }
                if (onsCapacidadeInstaladaGeracaoEntity.getIdEstado() != null) {
                    existingOnsCapacidadeInstaladaGeracao.setIdEstado(onsCapacidadeInstaladaGeracaoEntity.getIdEstado());
                }
                if (onsCapacidadeInstaladaGeracaoEntity.getNomEstado() != null) {
                    existingOnsCapacidadeInstaladaGeracao.setNomEstado(onsCapacidadeInstaladaGeracaoEntity.getNomEstado());
                }
                if (onsCapacidadeInstaladaGeracaoEntity.getNomModalidadeoperacao() != null) {
                    existingOnsCapacidadeInstaladaGeracao.setNomModalidadeoperacao(
                        onsCapacidadeInstaladaGeracaoEntity.getNomModalidadeoperacao()
                    );
                }
                if (onsCapacidadeInstaladaGeracaoEntity.getNomAgenteproprietario() != null) {
                    existingOnsCapacidadeInstaladaGeracao.setNomAgenteproprietario(
                        onsCapacidadeInstaladaGeracaoEntity.getNomAgenteproprietario()
                    );
                }
                if (onsCapacidadeInstaladaGeracaoEntity.getNomTipousina() != null) {
                    existingOnsCapacidadeInstaladaGeracao.setNomTipousina(onsCapacidadeInstaladaGeracaoEntity.getNomTipousina());
                }
                if (onsCapacidadeInstaladaGeracaoEntity.getNomUsina() != null) {
                    existingOnsCapacidadeInstaladaGeracao.setNomUsina(onsCapacidadeInstaladaGeracaoEntity.getNomUsina());
                }
                if (onsCapacidadeInstaladaGeracaoEntity.getCeg() != null) {
                    existingOnsCapacidadeInstaladaGeracao.setCeg(onsCapacidadeInstaladaGeracaoEntity.getCeg());
                }
                if (onsCapacidadeInstaladaGeracaoEntity.getNomUnidadegeradora() != null) {
                    existingOnsCapacidadeInstaladaGeracao.setNomUnidadegeradora(
                        onsCapacidadeInstaladaGeracaoEntity.getNomUnidadegeradora()
                    );
                }
                if (onsCapacidadeInstaladaGeracaoEntity.getCodEquipamento() != null) {
                    existingOnsCapacidadeInstaladaGeracao.setCodEquipamento(onsCapacidadeInstaladaGeracaoEntity.getCodEquipamento());
                }
                if (onsCapacidadeInstaladaGeracaoEntity.getNumUnidadegeradora() != null) {
                    existingOnsCapacidadeInstaladaGeracao.setNumUnidadegeradora(
                        onsCapacidadeInstaladaGeracaoEntity.getNumUnidadegeradora()
                    );
                }
                if (onsCapacidadeInstaladaGeracaoEntity.getNomCombustivel() != null) {
                    existingOnsCapacidadeInstaladaGeracao.setNomCombustivel(onsCapacidadeInstaladaGeracaoEntity.getNomCombustivel());
                }
                if (onsCapacidadeInstaladaGeracaoEntity.getDatEntradateste() != null) {
                    existingOnsCapacidadeInstaladaGeracao.setDatEntradateste(onsCapacidadeInstaladaGeracaoEntity.getDatEntradateste());
                }
                if (onsCapacidadeInstaladaGeracaoEntity.getDatEntradaoperacao() != null) {
                    existingOnsCapacidadeInstaladaGeracao.setDatEntradaoperacao(
                        onsCapacidadeInstaladaGeracaoEntity.getDatEntradaoperacao()
                    );
                }
                if (onsCapacidadeInstaladaGeracaoEntity.getDatDesativacao() != null) {
                    existingOnsCapacidadeInstaladaGeracao.setDatDesativacao(onsCapacidadeInstaladaGeracaoEntity.getDatDesativacao());
                }
                if (onsCapacidadeInstaladaGeracaoEntity.getValPotenciaefetiva() != null) {
                    existingOnsCapacidadeInstaladaGeracao.setValPotenciaefetiva(
                        onsCapacidadeInstaladaGeracaoEntity.getValPotenciaefetiva()
                    );
                }

                return existingOnsCapacidadeInstaladaGeracao;
            })
            .map(onsCapacidadeInstaladaGeracaoRepository::save)
            .map(savedOnsCapacidadeInstaladaGeracao -> {
                onsCapacidadeInstaladaGeracaoSearchRepository.index(savedOnsCapacidadeInstaladaGeracao);
                return savedOnsCapacidadeInstaladaGeracao;
            });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, onsCapacidadeInstaladaGeracaoEntity.getId().toString())
        );
    }

    /**
     * {@code GET  /ons-capacidade-instalada-geracaos} : get all the onsCapacidadeInstaladaGeracaos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsCapacidadeInstaladaGeracaos in body.
     */
    @GetMapping("")
    public List<OnsCapacidadeInstaladaGeracaoEntity> getAllOnsCapacidadeInstaladaGeracaos() {
        LOG.debug("REST request to get all OnsCapacidadeInstaladaGeracaos");
        return onsCapacidadeInstaladaGeracaoRepository.findAll();
    }

    /**
     * {@code GET  /ons-capacidade-instalada-geracaos/:id} : get the "id" onsCapacidadeInstaladaGeracao.
     *
     * @param id the id of the onsCapacidadeInstaladaGeracaoEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsCapacidadeInstaladaGeracaoEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OnsCapacidadeInstaladaGeracaoEntity> getOnsCapacidadeInstaladaGeracao(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsCapacidadeInstaladaGeracao : {}", id);
        Optional<OnsCapacidadeInstaladaGeracaoEntity> onsCapacidadeInstaladaGeracaoEntity =
            onsCapacidadeInstaladaGeracaoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsCapacidadeInstaladaGeracaoEntity);
    }

    /**
     * {@code DELETE  /ons-capacidade-instalada-geracaos/:id} : delete the "id" onsCapacidadeInstaladaGeracao.
     *
     * @param id the id of the onsCapacidadeInstaladaGeracaoEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsCapacidadeInstaladaGeracao(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsCapacidadeInstaladaGeracao : {}", id);
        onsCapacidadeInstaladaGeracaoRepository.deleteById(id);
        onsCapacidadeInstaladaGeracaoSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-capacidade-instalada-geracaos/_search?query=:query} : search for the onsCapacidadeInstaladaGeracao corresponding
     * to the query.
     *
     * @param query the query of the onsCapacidadeInstaladaGeracao search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsCapacidadeInstaladaGeracaoEntity> searchOnsCapacidadeInstaladaGeracaos(@RequestParam("query") String query) {
        LOG.debug("REST request to search OnsCapacidadeInstaladaGeracaos for query {}", query);
        try {
            return StreamSupport.stream(onsCapacidadeInstaladaGeracaoSearchRepository.search(query).spliterator(), false).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
