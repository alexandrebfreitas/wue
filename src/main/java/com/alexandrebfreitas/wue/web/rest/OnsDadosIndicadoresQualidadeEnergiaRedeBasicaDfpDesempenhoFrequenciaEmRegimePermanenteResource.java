package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteRepository;
import com.alexandrebfreitas.wue.repository.search.OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity}.
 */
@RestController
@RequestMapping("/api/ons-dados-indicadores-qualidade-energia-rede-basica-dfp-desempenho-frequencia-em-regime-permanentes")
@Transactional
public class OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteResource {

    private static final Logger LOG = LoggerFactory.getLogger(
        OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteResource.class
    );

    private static final String ENTITY_NAME = "onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteRepository onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteRepository;

    private final OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteSearchRepository onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteSearchRepository;

    public OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteResource(
        OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteRepository onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteRepository,
        OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteSearchRepository onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteSearchRepository
    ) {
        this.onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteRepository =
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteRepository;
        this.onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteSearchRepository =
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteSearchRepository;
    }

    /**
     * {@code POST  /ons-dados-indicadores-qualidade-energia-rede-basica-dfp-desempenho-frequencia-em-regime-permanentes} : Create a new onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente.
     *
     * @param onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity the onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity, or with status {@code 400 (Bad Request)} if the onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<
        OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
    > createOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente(
        @RequestBody OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to save OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente : {}",
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
        );
        if (onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.getId() != null) {
            throw new BadRequestAlertException(
                "A new onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity =
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteRepository.save(
                onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
            );
        onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteSearchRepository.index(
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
        );
        return ResponseEntity.created(
            new URI(
                "/api/ons-dados-indicadores-qualidade-energia-rede-basica-dfp-desempenho-frequencia-em-regime-permanentes/" +
                onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.getId()
            )
        )
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.getId().toString()
                )
            )
            .body(onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity);
    }

    /**
     * {@code PUT  /ons-dados-indicadores-qualidade-energia-rede-basica-dfp-desempenho-frequencia-em-regime-permanentes/:id} : Updates an existing onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente.
     *
     * @param id the id of the onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity to save.
     * @param onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity the onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity,
     * or with status {@code 400 (Bad Request)} if the onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<
        OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
    > updateOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to update OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente : {}, {}",
            id,
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
        );
        if (onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity =
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteRepository.save(
                onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
            );
        onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteSearchRepository.index(
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
        );
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.getId().toString()
                )
            )
            .body(onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity);
    }

    /**
     * {@code PATCH  /ons-dados-indicadores-qualidade-energia-rede-basica-dfp-desempenho-frequencia-em-regime-permanentes/:id} : Partial updates given fields of an existing onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente, field will ignore if it is null
     *
     * @param id the id of the onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity to save.
     * @param onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity the onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity,
     * or with status {@code 400 (Bad Request)} if the onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<
        OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
    > partialUpdateOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to partial update OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente partially : {}, {}",
            id,
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
        );
        if (onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity> result =
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteRepository
                .findById(onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.getId())
                .map(existingOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente -> {
                    if (
                        onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.getIdPeriodicidade() !=
                        null
                    ) {
                        existingOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente.setIdPeriodicidade(
                            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.getIdPeriodicidade()
                        );
                    }
                    if (
                        onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.getDinReferencia() !=
                        null
                    ) {
                        existingOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente.setDinReferencia(
                            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.getDinReferencia()
                        );
                    }
                    if (
                        onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.getNumDesvioPermSobre() !=
                        null
                    ) {
                        existingOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente.setNumDesvioPermSobre(
                            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.getNumDesvioPermSobre()
                        );
                    }
                    if (
                        onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.getNumDesvioPermSub() !=
                        null
                    ) {
                        existingOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente.setNumDesvioPermSub(
                            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.getNumDesvioPermSub()
                        );
                    }
                    if (
                        onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.getNumDesvioDistSobre() !=
                        null
                    ) {
                        existingOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente.setNumDesvioDistSobre(
                            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.getNumDesvioDistSobre()
                        );
                    }
                    if (
                        onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.getNumDesvioDistSub() !=
                        null
                    ) {
                        existingOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente.setNumDesvioDistSub(
                            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.getNumDesvioDistSub()
                        );
                    }
                    if (
                        onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.getNumMinutos() != null
                    ) {
                        existingOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente.setNumMinutos(
                            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.getNumMinutos()
                        );
                    }
                    if (
                        onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.getNumVioladodis() !=
                        null
                    ) {
                        existingOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente.setNumVioladodis(
                            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.getNumVioladodis()
                        );
                    }
                    if (
                        onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.getNumVioladoperm() !=
                        null
                    ) {
                        existingOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente.setNumVioladoperm(
                            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.getNumVioladoperm()
                        );
                    }
                    if (onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.getValDfp() != null) {
                        existingOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente.setValDfp(
                            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.getValDfp()
                        );
                    }

                    return existingOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente;
                })
                .map(onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteRepository::save)
                .map(savedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente -> {
                    onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteSearchRepository.index(
                        savedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente
                    );
                    return savedOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente;
                });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ENTITY_NAME,
                onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.getId().toString()
            )
        );
    }

    /**
     * {@code GET  /ons-dados-indicadores-qualidade-energia-rede-basica-dfp-desempenho-frequencia-em-regime-permanentes} : get all the onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanentes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanentes in body.
     */
    @GetMapping("")
    public List<
        OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
    > getAllOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanentes() {
        LOG.debug("REST request to get all OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanentes");
        return onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteRepository.findAll();
    }

    /**
     * {@code GET  /ons-dados-indicadores-qualidade-energia-rede-basica-dfp-desempenho-frequencia-em-regime-permanentes/:id} : get the "id" onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente.
     *
     * @param id the id of the onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<
        OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
    > getOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente : {}", id);
        Optional<
            OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
        > onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity =
            onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity);
    }

    /**
     * {@code DELETE  /ons-dados-indicadores-qualidade-energia-rede-basica-dfp-desempenho-frequencia-em-regime-permanentes/:id} : delete the "id" onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente.
     *
     * @param id the id of the onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente(
        @PathVariable("id") Long id
    ) {
        LOG.debug("REST request to delete OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente : {}", id);
        onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteRepository.deleteById(id);
        onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-dados-indicadores-qualidade-energia-rede-basica-dfp-desempenho-frequencia-em-regime-permanentes/_search?query=:query} : search for the onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente corresponding
     * to the query.
     *
     * @param query the query of the onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<
        OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity
    > searchOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanentes(@RequestParam("query") String query) {
        LOG.debug(
            "REST request to search OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanentes for query {}",
            query
        );
        try {
            return StreamSupport.stream(
                onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteSearchRepository
                    .search(query)
                    .spliterator(),
                false
            ).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
