package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity;
import com.alexandrebfreitas.wue.repository.OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRepository;
import com.alexandrebfreitas.wue.repository.search.OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity}.
 */
@RestController
@RequestMapping("/api/ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anuals")
@Transactional
public class OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualResource {

    private static final Logger LOG = LoggerFactory.getLogger(
        OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualResource.class
    );

    private static final String ENTITY_NAME = "onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRepository onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRepository;

    private final OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchRepository onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchRepository;

    public OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualResource(
        OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRepository onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRepository,
        OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchRepository onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchRepository
    ) {
        this.onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRepository =
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRepository;
        this.onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchRepository =
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchRepository;
    }

    /**
     * {@code POST  /ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anuals} : Create a new onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual.
     *
     * @param onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity, or with status {@code 400 (Bad Request)} if the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<
        OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity
    > createOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual(
        @RequestBody OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to save OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual : {}",
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity
        );
        if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getId() != null) {
            throw new BadRequestAlertException(
                "A new onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity =
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRepository.save(
                onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity
            );
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchRepository.index(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity
        );
        return ResponseEntity.created(
            new URI(
                "/api/ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anuals/" +
                onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getId()
            )
        )
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getId().toString()
                )
            )
            .body(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity);
    }

    /**
     * {@code PUT  /ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anuals/:id} : Updates an existing onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual.
     *
     * @param id the id of the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity to save.
     * @param onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity,
     * or with status {@code 400 (Bad Request)} if the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<
        OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity
    > updateOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to update OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual : {}, {}",
            id,
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity
        );
        if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity =
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRepository.save(
                onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity
            );
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchRepository.index(
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity
        );
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getId().toString()
                )
            )
            .body(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity);
    }

    /**
     * {@code PATCH  /ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anuals/:id} : Partial updates given fields of an existing onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual, field will ignore if it is null
     *
     * @param id the id of the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity to save.
     * @param onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity,
     * or with status {@code 400 (Bad Request)} if the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<
        OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity
    > partialUpdateOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to partial update OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual partially : {}, {}",
            id,
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity
        );
        if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity> result =
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRepository
                .findById(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getId())
                .map(existingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual -> {
                    if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getIdSubsistema() != null) {
                        existingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual.setIdSubsistema(
                            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getIdSubsistema()
                        );
                    }
                    if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getNomSubsistema() != null) {
                        existingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual.setNomSubsistema(
                            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getNomSubsistema()
                        );
                    }
                    if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getIdEstado() != null) {
                        existingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual.setIdEstado(
                            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getIdEstado()
                        );
                    }
                    if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getNomEstado() != null) {
                        existingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual.setNomEstado(
                            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getNomEstado()
                        );
                    }
                    if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getNomModalidadeoperacao() != null) {
                        existingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual.setNomModalidadeoperacao(
                            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getNomModalidadeoperacao()
                        );
                    }
                    if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getNomAgenteproprietario() != null) {
                        existingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual.setNomAgenteproprietario(
                            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getNomAgenteproprietario()
                        );
                    }
                    if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getIdTipousina() != null) {
                        existingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual.setIdTipousina(
                            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getIdTipousina()
                        );
                    }
                    if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getIdUsina() != null) {
                        existingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual.setIdUsina(
                            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getIdUsina()
                        );
                    }
                    if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getNomUsina() != null) {
                        existingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual.setNomUsina(
                            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getNomUsina()
                        );
                    }
                    if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getCeg() != null) {
                        existingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual.setCeg(
                            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getCeg()
                        );
                    }
                    if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getCodEquipamento() != null) {
                        existingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual.setCodEquipamento(
                            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getCodEquipamento()
                        );
                    }
                    if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getNumUnidadegeradora() != null) {
                        existingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual.setNumUnidadegeradora(
                            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getNumUnidadegeradora()
                        );
                    }
                    if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getNomUnidadegeradora() != null) {
                        existingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual.setNomUnidadegeradora(
                            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getNomUnidadegeradora()
                        );
                    }
                    if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getValPotencia() != null) {
                        existingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual.setValPotencia(
                            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getValPotencia()
                        );
                    }
                    if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getDinAno() != null) {
                        existingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual.setDinAno(
                            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getDinAno()
                        );
                    }
                    if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getValDispf() != null) {
                        existingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual.setValDispf(
                            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getValDispf()
                        );
                    }
                    if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getValIndisppf() != null) {
                        existingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual.setValIndisppf(
                            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getValIndisppf()
                        );
                    }
                    if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getValIndispff() != null) {
                        existingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual.setValIndispff(
                            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getValIndispff()
                        );
                    }
                    if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getValDmdff() != null) {
                        existingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual.setValDmdff(
                            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getValDmdff()
                        );
                    }
                    if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getValFdff() != null) {
                        existingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual.setValFdff(
                            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getValFdff()
                        );
                    }
                    if (onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getValTdff() != null) {
                        existingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual.setValTdff(
                            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getValTdff()
                        );
                    }

                    return existingOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual;
                })
                .map(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRepository::save)
                .map(savedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual -> {
                    onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchRepository.index(
                        savedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual
                    );
                    return savedOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual;
                });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ENTITY_NAME,
                onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.getId().toString()
            )
        );
    }

    /**
     * {@code GET  /ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anuals} : get all the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnuals.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnuals in body.
     */
    @GetMapping("")
    public List<
        OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity
    > getAllOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnuals() {
        LOG.debug("REST request to get all OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnuals");
        return onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRepository.findAll();
    }

    /**
     * {@code GET  /ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anuals/:id} : get the "id" onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual.
     *
     * @param id the id of the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<
        OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity
    > getOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual : {}", id);
        Optional<
            OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity
        > onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity =
            onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity);
    }

    /**
     * {@code DELETE  /ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anuals/:id} : delete the "id" onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual.
     *
     * @param id the id of the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual : {}", id);
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualRepository.deleteById(id);
        onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anuals/_search?query=:query} : search for the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual corresponding
     * to the query.
     *
     * @param query the query of the onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<
        OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity
    > searchOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnuals(@RequestParam("query") String query) {
        LOG.debug("REST request to search OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnuals for query {}", query);
        try {
            return StreamSupport.stream(
                onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualSearchRepository.search(query).spliterator(),
                false
            ).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
