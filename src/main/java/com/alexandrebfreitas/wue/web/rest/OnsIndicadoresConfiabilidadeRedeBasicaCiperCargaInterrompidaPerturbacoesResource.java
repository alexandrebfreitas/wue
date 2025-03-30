package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity;
import com.alexandrebfreitas.wue.repository.OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRepository;
import com.alexandrebfreitas.wue.repository.search.OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity}.
 */
@RestController
@RequestMapping("/api/ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes")
@Transactional
public class OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesResource {

    private static final Logger LOG = LoggerFactory.getLogger(
        OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesResource.class
    );

    private static final String ENTITY_NAME = "onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRepository onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRepository;

    private final OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesSearchRepository onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesSearchRepository;

    public OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesResource(
        OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRepository onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRepository,
        OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesSearchRepository onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesSearchRepository
    ) {
        this.onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRepository =
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRepository;
        this.onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesSearchRepository =
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesSearchRepository;
    }

    /**
     * {@code POST  /ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes} : Create a new onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes.
     *
     * @param onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity the onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity, or with status {@code 400 (Bad Request)} if the onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<
        OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity
    > createOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes(
        @RequestBody OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to save OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes : {}",
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity
        );
        if (onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.getId() != null) {
            throw new BadRequestAlertException(
                "A new onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity =
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRepository.save(
                onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity
            );
        onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesSearchRepository.index(
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity
        );
        return ResponseEntity.created(
            new URI(
                "/api/ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes/" +
                onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.getId()
            )
        )
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.getId().toString()
                )
            )
            .body(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity);
    }

    /**
     * {@code PUT  /ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes/:id} : Updates an existing onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes.
     *
     * @param id the id of the onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity to save.
     * @param onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity the onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity,
     * or with status {@code 400 (Bad Request)} if the onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<
        OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity
    > updateOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to update OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes : {}, {}",
            id,
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity
        );
        if (onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity =
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRepository.save(
                onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity
            );
        onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesSearchRepository.index(
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity
        );
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.getId().toString()
                )
            )
            .body(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity);
    }

    /**
     * {@code PATCH  /ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes/:id} : Partial updates given fields of an existing onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes, field will ignore if it is null
     *
     * @param id the id of the onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity to save.
     * @param onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity the onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity,
     * or with status {@code 400 (Bad Request)} if the onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<
        OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity
    > partialUpdateOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to partial update OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes partially : {}, {}",
            id,
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity
        );
        if (onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity> result =
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRepository
                .findById(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.getId())
                .map(existingOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes -> {
                    if (onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.getDscAgregacao() != null) {
                        existingOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes.setDscAgregacao(
                            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.getDscAgregacao()
                        );
                    }
                    if (onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.getCodCaracteristica() != null) {
                        existingOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes.setCodCaracteristica(
                            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.getCodCaracteristica()
                        );
                    }
                    if (onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.getDscCaracteristica() != null) {
                        existingOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes.setDscCaracteristica(
                            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.getDscCaracteristica()
                        );
                    }
                    if (onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.getIdPeriodicidade() != null) {
                        existingOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes.setIdPeriodicidade(
                            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.getIdPeriodicidade()
                        );
                    }
                    if (onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.getDinReferencia() != null) {
                        existingOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes.setDinReferencia(
                            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.getDinReferencia()
                        );
                    }
                    if (onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.getValCiper1() != null) {
                        existingOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes.setValCiper1(
                            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.getValCiper1()
                        );
                    }
                    if (onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.getValCiper2() != null) {
                        existingOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes.setValCiper2(
                            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.getValCiper2()
                        );
                    }
                    if (onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.getValCiper3() != null) {
                        existingOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes.setValCiper3(
                            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.getValCiper3()
                        );
                    }
                    if (onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.getValCiper4() != null) {
                        existingOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes.setValCiper4(
                            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.getValCiper4()
                        );
                    }
                    if (onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.getValCiper5() != null) {
                        existingOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes.setValCiper5(
                            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.getValCiper5()
                        );
                    }

                    return existingOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes;
                })
                .map(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRepository::save)
                .map(savedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes -> {
                    onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesSearchRepository.index(
                        savedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes
                    );
                    return savedOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes;
                });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ENTITY_NAME,
                onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.getId().toString()
            )
        );
    }

    /**
     * {@code GET  /ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes} : get all the onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes in body.
     */
    @GetMapping("")
    public List<
        OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity
    > getAllOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes() {
        LOG.debug("REST request to get all OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes");
        return onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRepository.findAll();
    }

    /**
     * {@code GET  /ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes/:id} : get the "id" onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes.
     *
     * @param id the id of the onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<
        OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity
    > getOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes : {}", id);
        Optional<
            OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity
        > onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity =
            onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity);
    }

    /**
     * {@code DELETE  /ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes/:id} : delete the "id" onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes.
     *
     * @param id the id of the onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes(
        @PathVariable("id") Long id
    ) {
        LOG.debug("REST request to delete OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes : {}", id);
        onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesRepository.deleteById(id);
        onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes/_search?query=:query} : search for the onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes corresponding
     * to the query.
     *
     * @param query the query of the onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<
        OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity
    > searchOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes(@RequestParam("query") String query) {
        LOG.debug("REST request to search OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes for query {}", query);
        try {
            return StreamSupport.stream(
                onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesSearchRepository.search(query).spliterator(),
                false
            ).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
