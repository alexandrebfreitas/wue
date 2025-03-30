package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity;
import com.alexandrebfreitas.wue.repository.OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRepository;
import com.alexandrebfreitas.wue.repository.search.OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity}.
 */
@RestController
@RequestMapping("/api/ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs")
@Transactional
public class OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsResource {

    private static final Logger LOG = LoggerFactory.getLogger(
        OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsResource.class
    );

    private static final String ENTITY_NAME = "onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRepository onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRepository;

    private final OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsSearchRepository onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsSearchRepository;

    public OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsResource(
        OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRepository onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRepository,
        OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsSearchRepository onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsSearchRepository
    ) {
        this.onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRepository =
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRepository;
        this.onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsSearchRepository =
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsSearchRepository;
    }

    /**
     * {@code POST  /ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs} : Create a new onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs.
     *
     * @param onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity the onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity, or with status {@code 400 (Bad Request)} if the onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<
        OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity
    > createOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs(
        @RequestBody OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to save OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs : {}",
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity
        );
        if (onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getId() != null) {
            throw new BadRequestAlertException(
                "A new onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity =
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRepository.save(
                onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity
            );
        onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsSearchRepository.index(
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity
        );
        return ResponseEntity.created(
            new URI(
                "/api/ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs/" +
                onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getId()
            )
        )
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getId().toString()
                )
            )
            .body(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity);
    }

    /**
     * {@code PUT  /ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs/:id} : Updates an existing onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs.
     *
     * @param id the id of the onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity to save.
     * @param onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity the onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity,
     * or with status {@code 400 (Bad Request)} if the onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<
        OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity
    > updateOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to update OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs : {}, {}",
            id,
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity
        );
        if (onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity =
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRepository.save(
                onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity
            );
        onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsSearchRepository.index(
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity
        );
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getId().toString()
                )
            )
            .body(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity);
    }

    /**
     * {@code PATCH  /ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs/:id} : Partial updates given fields of an existing onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs, field will ignore if it is null
     *
     * @param id the id of the onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity to save.
     * @param onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity the onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity,
     * or with status {@code 400 (Bad Request)} if the onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<
        OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity
    > partialUpdateOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to partial update OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs partially : {}, {}",
            id,
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity
        );
        if (onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity> result =
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRepository
                .findById(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getId())
                .map(existingOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs -> {
                    if (onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getCodIndicador() != null) {
                        existingOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs.setCodIndicador(
                            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getCodIndicador()
                        );
                    }
                    if (onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getDscAgregacao() != null) {
                        existingOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs.setDscAgregacao(
                            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getDscAgregacao()
                        );
                    }
                    if (onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getCodCaracteristica() != null) {
                        existingOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs.setCodCaracteristica(
                            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getCodCaracteristica()
                        );
                    }
                    if (onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getDscCaracteristica() != null) {
                        existingOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs.setDscCaracteristica(
                            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getDscCaracteristica()
                        );
                    }
                    if (onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getIdPeriodicidade() != null) {
                        existingOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs.setIdPeriodicidade(
                            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getIdPeriodicidade()
                        );
                    }
                    if (onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getDinReferencia() != null) {
                        existingOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs.setDinReferencia(
                            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getDinReferencia()
                        );
                    }
                    if (onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getValIndicador() != null) {
                        existingOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs.setValIndicador(
                            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getValIndicador()
                        );
                    }
                    if (onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getNumPerturbacoes() != null) {
                        existingOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs.setNumPerturbacoes(
                            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getNumPerturbacoes()
                        );
                    }
                    if (onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getNumPerturbacoescortecarga() != null) {
                        existingOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs.setNumPerturbacoescortecarga(
                            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getNumPerturbacoescortecarga()
                        );
                    }
                    if (
                        onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getNumPerturbacoescortecarga0a50mw() != null
                    ) {
                        existingOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs.setNumPerturbacoescortecarga0a50mw(
                            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getNumPerturbacoescortecarga0a50mw()
                        );
                    }
                    if (
                        onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getNumPerturbacoescortecarga50a100mw() != null
                    ) {
                        existingOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs.setNumPerturbacoescortecarga50a100mw(
                            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getNumPerturbacoescortecarga50a100mw()
                        );
                    }
                    if (
                        onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getNumPerturbacoescortecargaMaior100mw() !=
                        null
                    ) {
                        existingOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs.setNumPerturbacoescortecargaMaior100mw(
                            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getNumPerturbacoescortecargaMaior100mw()
                        );
                    }

                    return existingOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs;
                })
                .map(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRepository::save)
                .map(savedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs -> {
                    onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsSearchRepository.index(
                        savedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs
                    );
                    return savedOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs;
                });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ENTITY_NAME,
                onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.getId().toString()
            )
        );
    }

    /**
     * {@code GET  /ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs} : get all the onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs in body.
     */
    @GetMapping("")
    public List<
        OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity
    > getAllOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs() {
        LOG.debug("REST request to get all OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs");
        return onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRepository.findAll();
    }

    /**
     * {@code GET  /ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs/:id} : get the "id" onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs.
     *
     * @param id the id of the onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<
        OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity
    > getOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs : {}", id);
        Optional<
            OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity
        > onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity =
            onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity);
    }

    /**
     * {@code DELETE  /ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs/:id} : delete the "id" onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs.
     *
     * @param id the id of the onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs : {}", id);
        onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsRepository.deleteById(id);
        onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs/_search?query=:query} : search for the onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs corresponding
     * to the query.
     *
     * @param query the query of the onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<
        OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity
    > searchOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs(@RequestParam("query") String query) {
        LOG.debug("REST request to search OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs for query {}", query);
        try {
            return StreamSupport.stream(
                onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsSearchRepository.search(query).spliterator(),
                false
            ).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
