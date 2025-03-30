package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity;
import com.alexandrebfreitas.wue.repository.OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRepository;
import com.alexandrebfreitas.wue.repository.search.OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity}.
 */
@RestController
@RequestMapping("/api/ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes")
@Transactional
public class OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesResource.class);

    private static final String ENTITY_NAME = "onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRepository onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRepository;

    private final OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesSearchRepository onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesSearchRepository;

    public OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesResource(
        OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRepository onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRepository,
        OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesSearchRepository onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesSearchRepository
    ) {
        this.onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRepository =
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRepository;
        this.onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesSearchRepository =
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesSearchRepository;
    }

    /**
     * {@code POST  /ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes} : Create a new onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes.
     *
     * @param onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity the onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity, or with status {@code 400 (Bad Request)} if the onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<
        OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity
    > createOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes(
        @RequestBody OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to save OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes : {}",
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity
        );
        if (onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.getId() != null) {
            throw new BadRequestAlertException(
                "A new onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity =
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRepository.save(
                onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity
            );
        onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesSearchRepository.index(
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity
        );
        return ResponseEntity.created(
            new URI(
                "/api/ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes/" +
                onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.getId()
            )
        )
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.getId().toString()
                )
            )
            .body(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity);
    }

    /**
     * {@code PUT  /ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes/:id} : Updates an existing onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes.
     *
     * @param id the id of the onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity to save.
     * @param onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity the onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity,
     * or with status {@code 400 (Bad Request)} if the onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<
        OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity
    > updateOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to update OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes : {}, {}",
            id,
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity
        );
        if (onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity =
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRepository.save(
                onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity
            );
        onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesSearchRepository.index(
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity
        );
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.getId().toString()
                )
            )
            .body(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity);
    }

    /**
     * {@code PATCH  /ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes/:id} : Partial updates given fields of an existing onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes, field will ignore if it is null
     *
     * @param id the id of the onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity to save.
     * @param onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity the onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity,
     * or with status {@code 400 (Bad Request)} if the onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<
        OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity
    > partialUpdateOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to partial update OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes partially : {}, {}",
            id,
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity
        );
        if (onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity> result =
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRepository
                .findById(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.getId())
                .map(existingOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes -> {
                    if (onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.getDscAgregacao() != null) {
                        existingOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes.setDscAgregacao(
                            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.getDscAgregacao()
                        );
                    }
                    if (onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.getCodCaracteristica() != null) {
                        existingOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes.setCodCaracteristica(
                            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.getCodCaracteristica()
                        );
                    }
                    if (onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.getDscCaracteristica() != null) {
                        existingOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes.setDscCaracteristica(
                            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.getDscCaracteristica()
                        );
                    }
                    if (onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.getIdPeriodicidade() != null) {
                        existingOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes.setIdPeriodicidade(
                            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.getIdPeriodicidade()
                        );
                    }
                    if (onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.getDinReferencia() != null) {
                        existingOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes.setDinReferencia(
                            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.getDinReferencia()
                        );
                    }
                    if (onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.getValSm1() != null) {
                        existingOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes.setValSm1(
                            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.getValSm1()
                        );
                    }
                    if (onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.getValSm2() != null) {
                        existingOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes.setValSm2(
                            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.getValSm2()
                        );
                    }
                    if (onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.getValSm3() != null) {
                        existingOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes.setValSm3(
                            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.getValSm3()
                        );
                    }
                    if (onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.getValSm4() != null) {
                        existingOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes.setValSm4(
                            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.getValSm4()
                        );
                    }
                    if (onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.getValSm5() != null) {
                        existingOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes.setValSm5(
                            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.getValSm5()
                        );
                    }

                    return existingOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes;
                })
                .map(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRepository::save)
                .map(savedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes -> {
                    onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesSearchRepository.index(
                        savedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes
                    );
                    return savedOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes;
                });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ENTITY_NAME,
                onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.getId().toString()
            )
        );
    }

    /**
     * {@code GET  /ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes} : get all the onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes in body.
     */
    @GetMapping("")
    public List<
        OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity
    > getAllOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes() {
        LOG.debug("REST request to get all OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes");
        return onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRepository.findAll();
    }

    /**
     * {@code GET  /ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes/:id} : get the "id" onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes.
     *
     * @param id the id of the onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<
        OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity
    > getOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes : {}", id);
        Optional<
            OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity
        > onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity =
            onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity);
    }

    /**
     * {@code DELETE  /ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes/:id} : delete the "id" onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes.
     *
     * @param id the id of the onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes : {}", id);
        onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesRepository.deleteById(id);
        onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes/_search?query=:query} : search for the onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes corresponding
     * to the query.
     *
     * @param query the query of the onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<
        OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity
    > searchOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes(@RequestParam("query") String query) {
        LOG.debug("REST request to search OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes for query {}", query);
        try {
            return StreamSupport.stream(
                onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesSearchRepository.search(query).spliterator(),
                false
            ).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
