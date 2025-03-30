package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity;
import com.alexandrebfreitas.wue.repository.OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRepository;
import com.alexandrebfreitas.wue.repository.search.OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity}.
 */
@RestController
@RequestMapping("/api/ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensals")
@Transactional
public class OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalResource {

    private static final Logger LOG = LoggerFactory.getLogger(
        OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalResource.class
    );

    private static final String ENTITY_NAME = "onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRepository onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRepository;

    private final OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchRepository onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchRepository;

    public OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalResource(
        OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRepository onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRepository,
        OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchRepository onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchRepository
    ) {
        this.onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRepository =
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRepository;
        this.onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchRepository =
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchRepository;
    }

    /**
     * {@code POST  /ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensals} : Create a new onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal.
     *
     * @param onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity, or with status {@code 400 (Bad Request)} if the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<
        OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity
    > createOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal(
        @RequestBody OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to save OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal : {}",
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity
        );
        if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getId() != null) {
            throw new BadRequestAlertException(
                "A new onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity =
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRepository.save(
                onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity
            );
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchRepository.index(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity
        );
        return ResponseEntity.created(
            new URI(
                "/api/ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensals/" +
                onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getId()
            )
        )
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getId().toString()
                )
            )
            .body(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity);
    }

    /**
     * {@code PUT  /ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensals/:id} : Updates an existing onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal.
     *
     * @param id the id of the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity to save.
     * @param onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity,
     * or with status {@code 400 (Bad Request)} if the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<
        OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity
    > updateOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to update OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal : {}, {}",
            id,
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity
        );
        if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity =
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRepository.save(
                onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity
            );
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchRepository.index(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity
        );
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getId().toString()
                )
            )
            .body(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity);
    }

    /**
     * {@code PATCH  /ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensals/:id} : Partial updates given fields of an existing onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal, field will ignore if it is null
     *
     * @param id the id of the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity to save.
     * @param onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity,
     * or with status {@code 400 (Bad Request)} if the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<
        OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity
    > partialUpdateOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to partial update OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal partially : {}, {}",
            id,
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity
        );
        if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity> result =
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRepository
                .findById(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getId())
                .map(existingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal -> {
                    if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getIdSubsistema() != null) {
                        existingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal.setIdSubsistema(
                            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getIdSubsistema()
                        );
                    }
                    if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getNomSubsistema() != null) {
                        existingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal.setNomSubsistema(
                            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getNomSubsistema()
                        );
                    }
                    if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getIdEstado() != null) {
                        existingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal.setIdEstado(
                            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getIdEstado()
                        );
                    }
                    if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getNomEstado() != null) {
                        existingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal.setNomEstado(
                            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getNomEstado()
                        );
                    }
                    if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getNomModalidadeoperacao() != null) {
                        existingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal.setNomModalidadeoperacao(
                            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getNomModalidadeoperacao()
                        );
                    }
                    if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getNomAgenteproprietario() != null) {
                        existingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal.setNomAgenteproprietario(
                            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getNomAgenteproprietario()
                        );
                    }
                    if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getIdTipousina() != null) {
                        existingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal.setIdTipousina(
                            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getIdTipousina()
                        );
                    }
                    if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getIdUsina() != null) {
                        existingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal.setIdUsina(
                            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getIdUsina()
                        );
                    }
                    if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getNomUsina() != null) {
                        existingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal.setNomUsina(
                            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getNomUsina()
                        );
                    }
                    if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getCeg() != null) {
                        existingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal.setCeg(
                            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getCeg()
                        );
                    }
                    if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getCodEquipamento() != null) {
                        existingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal.setCodEquipamento(
                            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getCodEquipamento()
                        );
                    }
                    if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getNumUnidadegeradora() != null) {
                        existingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal.setNumUnidadegeradora(
                            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getNumUnidadegeradora()
                        );
                    }
                    if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getNomUnidadegeradora() != null) {
                        existingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal.setNomUnidadegeradora(
                            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getNomUnidadegeradora()
                        );
                    }
                    if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getValPotencia() != null) {
                        existingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal.setValPotencia(
                            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getValPotencia()
                        );
                    }
                    if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getValDispf() != null) {
                        existingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal.setValDispf(
                            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getValDispf()
                        );
                    }
                    if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getValIndisppf() != null) {
                        existingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal.setValIndisppf(
                            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getValIndisppf()
                        );
                    }
                    if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getValIndispff() != null) {
                        existingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal.setValIndispff(
                            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getValIndispff()
                        );
                    }
                    if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getValDmdff() != null) {
                        existingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal.setValDmdff(
                            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getValDmdff()
                        );
                    }
                    if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getValFdff() != null) {
                        existingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal.setValFdff(
                            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getValFdff()
                        );
                    }
                    if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getValTdff() != null) {
                        existingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal.setValTdff(
                            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getValTdff()
                        );
                    }

                    return existingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal;
                })
                .map(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRepository::save)
                .map(savedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal -> {
                    onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchRepository.index(
                        savedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal
                    );
                    return savedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal;
                });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ENTITY_NAME,
                onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.getId().toString()
            )
        );
    }

    /**
     * {@code GET  /ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensals} : get all the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensals.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensals in body.
     */
    @GetMapping("")
    public List<
        OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity
    > getAllOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensals() {
        LOG.debug("REST request to get all OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensals");
        return onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRepository.findAll();
    }

    /**
     * {@code GET  /ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensals/:id} : get the "id" onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal.
     *
     * @param id the id of the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<
        OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity
    > getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal : {}", id);
        Optional<
            OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity
        > onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity =
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity);
    }

    /**
     * {@code DELETE  /ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensals/:id} : delete the "id" onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal.
     *
     * @param id the id of the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal : {}", id);
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalRepository.deleteById(id);
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensals/_search?query=:query} : search for the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal corresponding
     * to the query.
     *
     * @param query the query of the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<
        OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity
    > searchOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensals(@RequestParam("query") String query) {
        LOG.debug("REST request to search OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensals for query {}", query);
        try {
            return StreamSupport.stream(
                onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalSearchRepository.search(query).spliterator(),
                false
            ).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
