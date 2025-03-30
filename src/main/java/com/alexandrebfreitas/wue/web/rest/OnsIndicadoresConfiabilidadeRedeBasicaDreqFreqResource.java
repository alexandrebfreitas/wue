package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity;
import com.alexandrebfreitas.wue.repository.OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqRepository;
import com.alexandrebfreitas.wue.repository.search.OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity}.
 */
@RestController
@RequestMapping("/api/ons-indicadores-confiabilidade-rede-basica-dreq-freqs")
@Transactional
public class OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqResource.class);

    private static final String ENTITY_NAME = "onsIndicadoresConfiabilidadeRedeBasicaDreqFreq";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqRepository onsIndicadoresConfiabilidadeRedeBasicaDreqFreqRepository;

    private final OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchRepository onsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchRepository;

    public OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqResource(
        OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqRepository onsIndicadoresConfiabilidadeRedeBasicaDreqFreqRepository,
        OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchRepository onsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchRepository
    ) {
        this.onsIndicadoresConfiabilidadeRedeBasicaDreqFreqRepository = onsIndicadoresConfiabilidadeRedeBasicaDreqFreqRepository;
        this.onsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchRepository =
            onsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchRepository;
    }

    /**
     * {@code POST  /ons-indicadores-confiabilidade-rede-basica-dreq-freqs} : Create a new onsIndicadoresConfiabilidadeRedeBasicaDreqFreq.
     *
     * @param onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity the onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity, or with status {@code 400 (Bad Request)} if the onsIndicadoresConfiabilidadeRedeBasicaDreqFreq has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity> createOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq(
        @RequestBody OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to save OnsIndicadoresConfiabilidadeRedeBasicaDreqFreq : {}",
            onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity
        );
        if (onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.getId() != null) {
            throw new BadRequestAlertException(
                "A new onsIndicadoresConfiabilidadeRedeBasicaDreqFreq cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity = onsIndicadoresConfiabilidadeRedeBasicaDreqFreqRepository.save(
            onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity
        );
        onsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchRepository.index(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity);
        return ResponseEntity.created(
            new URI(
                "/api/ons-indicadores-confiabilidade-rede-basica-dreq-freqs/" + onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.getId()
            )
        )
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.getId().toString()
                )
            )
            .body(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity);
    }

    /**
     * {@code PUT  /ons-indicadores-confiabilidade-rede-basica-dreq-freqs/:id} : Updates an existing onsIndicadoresConfiabilidadeRedeBasicaDreqFreq.
     *
     * @param id the id of the onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity to save.
     * @param onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity the onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity,
     * or with status {@code 400 (Bad Request)} if the onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity> updateOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to update OnsIndicadoresConfiabilidadeRedeBasicaDreqFreq : {}, {}",
            id,
            onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity
        );
        if (onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsIndicadoresConfiabilidadeRedeBasicaDreqFreqRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity = onsIndicadoresConfiabilidadeRedeBasicaDreqFreqRepository.save(
            onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity
        );
        onsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchRepository.index(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.getId().toString()
                )
            )
            .body(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity);
    }

    /**
     * {@code PATCH  /ons-indicadores-confiabilidade-rede-basica-dreq-freqs/:id} : Partial updates given fields of an existing onsIndicadoresConfiabilidadeRedeBasicaDreqFreq, field will ignore if it is null
     *
     * @param id the id of the onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity to save.
     * @param onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity the onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity,
     * or with status {@code 400 (Bad Request)} if the onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity> partialUpdateOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to partial update OnsIndicadoresConfiabilidadeRedeBasicaDreqFreq partially : {}, {}",
            id,
            onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity
        );
        if (onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsIndicadoresConfiabilidadeRedeBasicaDreqFreqRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity> result = onsIndicadoresConfiabilidadeRedeBasicaDreqFreqRepository
            .findById(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.getId())
            .map(existingOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq -> {
                if (onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.getDscAgregacao() != null) {
                    existingOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq.setDscAgregacao(
                        onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.getDscAgregacao()
                    );
                }
                if (onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.getCodCaracteristica() != null) {
                    existingOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq.setCodCaracteristica(
                        onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.getCodCaracteristica()
                    );
                }
                if (onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.getDscCaracteristica() != null) {
                    existingOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq.setDscCaracteristica(
                        onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.getDscCaracteristica()
                    );
                }
                if (onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.getIdPeriodicidade() != null) {
                    existingOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq.setIdPeriodicidade(
                        onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.getIdPeriodicidade()
                    );
                }
                if (onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.getDinReferencia() != null) {
                    existingOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq.setDinReferencia(
                        onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.getDinReferencia()
                    );
                }
                if (onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.getValDreq() != null) {
                    existingOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq.setValDreq(
                        onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.getValDreq()
                    );
                }
                if (onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.getValFreq() != null) {
                    existingOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq.setValFreq(
                        onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.getValFreq()
                    );
                }

                return existingOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq;
            })
            .map(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqRepository::save)
            .map(savedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq -> {
                onsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchRepository.index(savedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq);
                return savedOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq;
            });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ENTITY_NAME,
                onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.getId().toString()
            )
        );
    }

    /**
     * {@code GET  /ons-indicadores-confiabilidade-rede-basica-dreq-freqs} : get all the onsIndicadoresConfiabilidadeRedeBasicaDreqFreqs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsIndicadoresConfiabilidadeRedeBasicaDreqFreqs in body.
     */
    @GetMapping("")
    public List<OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity> getAllOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqs() {
        LOG.debug("REST request to get all OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqs");
        return onsIndicadoresConfiabilidadeRedeBasicaDreqFreqRepository.findAll();
    }

    /**
     * {@code GET  /ons-indicadores-confiabilidade-rede-basica-dreq-freqs/:id} : get the "id" onsIndicadoresConfiabilidadeRedeBasicaDreqFreq.
     *
     * @param id the id of the onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity> getOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq(
        @PathVariable("id") Long id
    ) {
        LOG.debug("REST request to get OnsIndicadoresConfiabilidadeRedeBasicaDreqFreq : {}", id);
        Optional<OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity> onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity =
            onsIndicadoresConfiabilidadeRedeBasicaDreqFreqRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity);
    }

    /**
     * {@code DELETE  /ons-indicadores-confiabilidade-rede-basica-dreq-freqs/:id} : delete the "id" onsIndicadoresConfiabilidadeRedeBasicaDreqFreq.
     *
     * @param id the id of the onsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsIndicadoresConfiabilidadeRedeBasicaDreqFreq : {}", id);
        onsIndicadoresConfiabilidadeRedeBasicaDreqFreqRepository.deleteById(id);
        onsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-indicadores-confiabilidade-rede-basica-dreq-freqs/_search?query=:query} : search for the onsIndicadoresConfiabilidadeRedeBasicaDreqFreq corresponding
     * to the query.
     *
     * @param query the query of the onsIndicadoresConfiabilidadeRedeBasicaDreqFreq search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity> searchOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqs(
        @RequestParam("query") String query
    ) {
        LOG.debug("REST request to search OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqs for query {}", query);
        try {
            return StreamSupport.stream(
                onsIndicadoresConfiabilidadeRedeBasicaDreqFreqSearchRepository.search(query).spliterator(),
                false
            ).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
