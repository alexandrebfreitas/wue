package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsGeracaoTermicaMotivoDespachoEntity;
import com.alexandrebfreitas.wue.repository.OnsGeracaoTermicaMotivoDespachoRepository;
import com.alexandrebfreitas.wue.repository.search.OnsGeracaoTermicaMotivoDespachoSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsGeracaoTermicaMotivoDespachoEntity}.
 */
@RestController
@RequestMapping("/api/ons-geracao-termica-motivo-despachos")
@Transactional
public class OnsGeracaoTermicaMotivoDespachoResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsGeracaoTermicaMotivoDespachoResource.class);

    private static final String ENTITY_NAME = "onsGeracaoTermicaMotivoDespacho";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsGeracaoTermicaMotivoDespachoRepository onsGeracaoTermicaMotivoDespachoRepository;

    private final OnsGeracaoTermicaMotivoDespachoSearchRepository onsGeracaoTermicaMotivoDespachoSearchRepository;

    public OnsGeracaoTermicaMotivoDespachoResource(
        OnsGeracaoTermicaMotivoDespachoRepository onsGeracaoTermicaMotivoDespachoRepository,
        OnsGeracaoTermicaMotivoDespachoSearchRepository onsGeracaoTermicaMotivoDespachoSearchRepository
    ) {
        this.onsGeracaoTermicaMotivoDespachoRepository = onsGeracaoTermicaMotivoDespachoRepository;
        this.onsGeracaoTermicaMotivoDespachoSearchRepository = onsGeracaoTermicaMotivoDespachoSearchRepository;
    }

    /**
     * {@code POST  /ons-geracao-termica-motivo-despachos} : Create a new onsGeracaoTermicaMotivoDespacho.
     *
     * @param onsGeracaoTermicaMotivoDespachoEntity the onsGeracaoTermicaMotivoDespachoEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsGeracaoTermicaMotivoDespachoEntity, or with status {@code 400 (Bad Request)} if the onsGeracaoTermicaMotivoDespacho has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OnsGeracaoTermicaMotivoDespachoEntity> createOnsGeracaoTermicaMotivoDespacho(
        @RequestBody OnsGeracaoTermicaMotivoDespachoEntity onsGeracaoTermicaMotivoDespachoEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to save OnsGeracaoTermicaMotivoDespacho : {}", onsGeracaoTermicaMotivoDespachoEntity);
        if (onsGeracaoTermicaMotivoDespachoEntity.getId() != null) {
            throw new BadRequestAlertException("A new onsGeracaoTermicaMotivoDespacho cannot already have an ID", ENTITY_NAME, "idexists");
        }
        onsGeracaoTermicaMotivoDespachoEntity = onsGeracaoTermicaMotivoDespachoRepository.save(onsGeracaoTermicaMotivoDespachoEntity);
        onsGeracaoTermicaMotivoDespachoSearchRepository.index(onsGeracaoTermicaMotivoDespachoEntity);
        return ResponseEntity.created(new URI("/api/ons-geracao-termica-motivo-despachos/" + onsGeracaoTermicaMotivoDespachoEntity.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsGeracaoTermicaMotivoDespachoEntity.getId().toString()
                )
            )
            .body(onsGeracaoTermicaMotivoDespachoEntity);
    }

    /**
     * {@code PUT  /ons-geracao-termica-motivo-despachos/:id} : Updates an existing onsGeracaoTermicaMotivoDespacho.
     *
     * @param id the id of the onsGeracaoTermicaMotivoDespachoEntity to save.
     * @param onsGeracaoTermicaMotivoDespachoEntity the onsGeracaoTermicaMotivoDespachoEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsGeracaoTermicaMotivoDespachoEntity,
     * or with status {@code 400 (Bad Request)} if the onsGeracaoTermicaMotivoDespachoEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsGeracaoTermicaMotivoDespachoEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OnsGeracaoTermicaMotivoDespachoEntity> updateOnsGeracaoTermicaMotivoDespacho(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsGeracaoTermicaMotivoDespachoEntity onsGeracaoTermicaMotivoDespachoEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to update OnsGeracaoTermicaMotivoDespacho : {}, {}", id, onsGeracaoTermicaMotivoDespachoEntity);
        if (onsGeracaoTermicaMotivoDespachoEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsGeracaoTermicaMotivoDespachoEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsGeracaoTermicaMotivoDespachoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsGeracaoTermicaMotivoDespachoEntity = onsGeracaoTermicaMotivoDespachoRepository.save(onsGeracaoTermicaMotivoDespachoEntity);
        onsGeracaoTermicaMotivoDespachoSearchRepository.index(onsGeracaoTermicaMotivoDespachoEntity);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsGeracaoTermicaMotivoDespachoEntity.getId().toString()
                )
            )
            .body(onsGeracaoTermicaMotivoDespachoEntity);
    }

    /**
     * {@code PATCH  /ons-geracao-termica-motivo-despachos/:id} : Partial updates given fields of an existing onsGeracaoTermicaMotivoDespacho, field will ignore if it is null
     *
     * @param id the id of the onsGeracaoTermicaMotivoDespachoEntity to save.
     * @param onsGeracaoTermicaMotivoDespachoEntity the onsGeracaoTermicaMotivoDespachoEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsGeracaoTermicaMotivoDespachoEntity,
     * or with status {@code 400 (Bad Request)} if the onsGeracaoTermicaMotivoDespachoEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsGeracaoTermicaMotivoDespachoEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsGeracaoTermicaMotivoDespachoEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OnsGeracaoTermicaMotivoDespachoEntity> partialUpdateOnsGeracaoTermicaMotivoDespacho(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsGeracaoTermicaMotivoDespachoEntity onsGeracaoTermicaMotivoDespachoEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to partial update OnsGeracaoTermicaMotivoDespacho partially : {}, {}",
            id,
            onsGeracaoTermicaMotivoDespachoEntity
        );
        if (onsGeracaoTermicaMotivoDespachoEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsGeracaoTermicaMotivoDespachoEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsGeracaoTermicaMotivoDespachoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsGeracaoTermicaMotivoDespachoEntity> result = onsGeracaoTermicaMotivoDespachoRepository
            .findById(onsGeracaoTermicaMotivoDespachoEntity.getId())
            .map(existingOnsGeracaoTermicaMotivoDespacho -> {
                if (onsGeracaoTermicaMotivoDespachoEntity.getDinInstante() != null) {
                    existingOnsGeracaoTermicaMotivoDespacho.setDinInstante(onsGeracaoTermicaMotivoDespachoEntity.getDinInstante());
                }
                if (onsGeracaoTermicaMotivoDespachoEntity.getNomTipopatamar() != null) {
                    existingOnsGeracaoTermicaMotivoDespacho.setNomTipopatamar(onsGeracaoTermicaMotivoDespachoEntity.getNomTipopatamar());
                }
                if (onsGeracaoTermicaMotivoDespachoEntity.getIdSubsistema() != null) {
                    existingOnsGeracaoTermicaMotivoDespacho.setIdSubsistema(onsGeracaoTermicaMotivoDespachoEntity.getIdSubsistema());
                }
                if (onsGeracaoTermicaMotivoDespachoEntity.getNomSubsistema() != null) {
                    existingOnsGeracaoTermicaMotivoDespacho.setNomSubsistema(onsGeracaoTermicaMotivoDespachoEntity.getNomSubsistema());
                }
                if (onsGeracaoTermicaMotivoDespachoEntity.getNomUsina() != null) {
                    existingOnsGeracaoTermicaMotivoDespacho.setNomUsina(onsGeracaoTermicaMotivoDespachoEntity.getNomUsina());
                }
                if (onsGeracaoTermicaMotivoDespachoEntity.getCodUsinaplanejamento() != null) {
                    existingOnsGeracaoTermicaMotivoDespacho.setCodUsinaplanejamento(
                        onsGeracaoTermicaMotivoDespachoEntity.getCodUsinaplanejamento()
                    );
                }
                if (onsGeracaoTermicaMotivoDespachoEntity.getCeg() != null) {
                    existingOnsGeracaoTermicaMotivoDespacho.setCeg(onsGeracaoTermicaMotivoDespachoEntity.getCeg());
                }
                if (onsGeracaoTermicaMotivoDespachoEntity.getValProggeracao() != null) {
                    existingOnsGeracaoTermicaMotivoDespacho.setValProggeracao(onsGeracaoTermicaMotivoDespachoEntity.getValProggeracao());
                }
                if (onsGeracaoTermicaMotivoDespachoEntity.getValProgordemmerito() != null) {
                    existingOnsGeracaoTermicaMotivoDespacho.setValProgordemmerito(
                        onsGeracaoTermicaMotivoDespachoEntity.getValProgordemmerito()
                    );
                }
                if (onsGeracaoTermicaMotivoDespachoEntity.getValProgordemdemeritoref() != null) {
                    existingOnsGeracaoTermicaMotivoDespacho.setValProgordemdemeritoref(
                        onsGeracaoTermicaMotivoDespachoEntity.getValProgordemdemeritoref()
                    );
                }
                if (onsGeracaoTermicaMotivoDespachoEntity.getValProgordemdemeritoacimadainflex() != null) {
                    existingOnsGeracaoTermicaMotivoDespacho.setValProgordemdemeritoacimadainflex(
                        onsGeracaoTermicaMotivoDespachoEntity.getValProgordemdemeritoacimadainflex()
                    );
                }
                if (onsGeracaoTermicaMotivoDespachoEntity.getValProginflexibilidade() != null) {
                    existingOnsGeracaoTermicaMotivoDespacho.setValProginflexibilidade(
                        onsGeracaoTermicaMotivoDespachoEntity.getValProginflexibilidade()
                    );
                }
                if (onsGeracaoTermicaMotivoDespachoEntity.getValProginflexembutmerito() != null) {
                    existingOnsGeracaoTermicaMotivoDespacho.setValProginflexembutmerito(
                        onsGeracaoTermicaMotivoDespachoEntity.getValProginflexembutmerito()
                    );
                }
                if (onsGeracaoTermicaMotivoDespachoEntity.getValProginflexpura() != null) {
                    existingOnsGeracaoTermicaMotivoDespacho.setValProginflexpura(
                        onsGeracaoTermicaMotivoDespachoEntity.getValProginflexpura()
                    );
                }
                if (onsGeracaoTermicaMotivoDespachoEntity.getValPrograzaoeletrica() != null) {
                    existingOnsGeracaoTermicaMotivoDespacho.setValPrograzaoeletrica(
                        onsGeracaoTermicaMotivoDespachoEntity.getValPrograzaoeletrica()
                    );
                }
                if (onsGeracaoTermicaMotivoDespachoEntity.getValProggarantiaenergetica() != null) {
                    existingOnsGeracaoTermicaMotivoDespacho.setValProggarantiaenergetica(
                        onsGeracaoTermicaMotivoDespachoEntity.getValProggarantiaenergetica()
                    );
                }
                if (onsGeracaoTermicaMotivoDespachoEntity.getValProggfom() != null) {
                    existingOnsGeracaoTermicaMotivoDespacho.setValProggfom(onsGeracaoTermicaMotivoDespachoEntity.getValProggfom());
                }
                if (onsGeracaoTermicaMotivoDespachoEntity.getValProgreposicaoperdas() != null) {
                    existingOnsGeracaoTermicaMotivoDespacho.setValProgreposicaoperdas(
                        onsGeracaoTermicaMotivoDespachoEntity.getValProgreposicaoperdas()
                    );
                }
                if (onsGeracaoTermicaMotivoDespachoEntity.getValProgexportacao() != null) {
                    existingOnsGeracaoTermicaMotivoDespacho.setValProgexportacao(
                        onsGeracaoTermicaMotivoDespachoEntity.getValProgexportacao()
                    );
                }
                if (onsGeracaoTermicaMotivoDespachoEntity.getValProgreservapotencia() != null) {
                    existingOnsGeracaoTermicaMotivoDespacho.setValProgreservapotencia(
                        onsGeracaoTermicaMotivoDespachoEntity.getValProgreservapotencia()
                    );
                }
                if (onsGeracaoTermicaMotivoDespachoEntity.getValProggsub() != null) {
                    existingOnsGeracaoTermicaMotivoDespacho.setValProggsub(onsGeracaoTermicaMotivoDespachoEntity.getValProggsub());
                }
                if (onsGeracaoTermicaMotivoDespachoEntity.getValProgunitcommitment() != null) {
                    existingOnsGeracaoTermicaMotivoDespacho.setValProgunitcommitment(
                        onsGeracaoTermicaMotivoDespachoEntity.getValProgunitcommitment()
                    );
                }
                if (onsGeracaoTermicaMotivoDespachoEntity.getValProgconstrainedoff() != null) {
                    existingOnsGeracaoTermicaMotivoDespacho.setValProgconstrainedoff(
                        onsGeracaoTermicaMotivoDespachoEntity.getValProgconstrainedoff()
                    );
                }
                if (onsGeracaoTermicaMotivoDespachoEntity.getValProginflexibilidadedessem() != null) {
                    existingOnsGeracaoTermicaMotivoDespacho.setValProginflexibilidadedessem(
                        onsGeracaoTermicaMotivoDespachoEntity.getValProginflexibilidadedessem()
                    );
                }
                if (onsGeracaoTermicaMotivoDespachoEntity.getValVerifgeracao() != null) {
                    existingOnsGeracaoTermicaMotivoDespacho.setValVerifgeracao(onsGeracaoTermicaMotivoDespachoEntity.getValVerifgeracao());
                }
                if (onsGeracaoTermicaMotivoDespachoEntity.getValVerifordemmerito() != null) {
                    existingOnsGeracaoTermicaMotivoDespacho.setValVerifordemmerito(
                        onsGeracaoTermicaMotivoDespachoEntity.getValVerifordemmerito()
                    );
                }
                if (onsGeracaoTermicaMotivoDespachoEntity.getValVerifordemdemeritoacimadainflex() != null) {
                    existingOnsGeracaoTermicaMotivoDespacho.setValVerifordemdemeritoacimadainflex(
                        onsGeracaoTermicaMotivoDespachoEntity.getValVerifordemdemeritoacimadainflex()
                    );
                }
                if (onsGeracaoTermicaMotivoDespachoEntity.getValVerifinflexibilidade() != null) {
                    existingOnsGeracaoTermicaMotivoDespacho.setValVerifinflexibilidade(
                        onsGeracaoTermicaMotivoDespachoEntity.getValVerifinflexibilidade()
                    );
                }
                if (onsGeracaoTermicaMotivoDespachoEntity.getValVerifinflexembutmerito() != null) {
                    existingOnsGeracaoTermicaMotivoDespacho.setValVerifinflexembutmerito(
                        onsGeracaoTermicaMotivoDespachoEntity.getValVerifinflexembutmerito()
                    );
                }
                if (onsGeracaoTermicaMotivoDespachoEntity.getValVerifinflexpura() != null) {
                    existingOnsGeracaoTermicaMotivoDespacho.setValVerifinflexpura(
                        onsGeracaoTermicaMotivoDespachoEntity.getValVerifinflexpura()
                    );
                }
                if (onsGeracaoTermicaMotivoDespachoEntity.getValVerifrazaoeletrica() != null) {
                    existingOnsGeracaoTermicaMotivoDespacho.setValVerifrazaoeletrica(
                        onsGeracaoTermicaMotivoDespachoEntity.getValVerifrazaoeletrica()
                    );
                }
                if (onsGeracaoTermicaMotivoDespachoEntity.getValVerifgarantiaenergetica() != null) {
                    existingOnsGeracaoTermicaMotivoDespacho.setValVerifgarantiaenergetica(
                        onsGeracaoTermicaMotivoDespachoEntity.getValVerifgarantiaenergetica()
                    );
                }
                if (onsGeracaoTermicaMotivoDespachoEntity.getValVerifgfom() != null) {
                    existingOnsGeracaoTermicaMotivoDespacho.setValVerifgfom(onsGeracaoTermicaMotivoDespachoEntity.getValVerifgfom());
                }
                if (onsGeracaoTermicaMotivoDespachoEntity.getValVerifreposicaoperdas() != null) {
                    existingOnsGeracaoTermicaMotivoDespacho.setValVerifreposicaoperdas(
                        onsGeracaoTermicaMotivoDespachoEntity.getValVerifreposicaoperdas()
                    );
                }
                if (onsGeracaoTermicaMotivoDespachoEntity.getValVerifexportacao() != null) {
                    existingOnsGeracaoTermicaMotivoDespacho.setValVerifexportacao(
                        onsGeracaoTermicaMotivoDespachoEntity.getValVerifexportacao()
                    );
                }
                if (onsGeracaoTermicaMotivoDespachoEntity.getValFdexp() != null) {
                    existingOnsGeracaoTermicaMotivoDespacho.setValFdexp(onsGeracaoTermicaMotivoDespachoEntity.getValFdexp());
                }
                if (onsGeracaoTermicaMotivoDespachoEntity.getValVerifreservapotencia() != null) {
                    existingOnsGeracaoTermicaMotivoDespacho.setValVerifreservapotencia(
                        onsGeracaoTermicaMotivoDespachoEntity.getValVerifreservapotencia()
                    );
                }
                if (onsGeracaoTermicaMotivoDespachoEntity.getValAtendsatisfatoriorpo() != null) {
                    existingOnsGeracaoTermicaMotivoDespacho.setValAtendsatisfatoriorpo(
                        onsGeracaoTermicaMotivoDespachoEntity.getValAtendsatisfatoriorpo()
                    );
                }
                if (onsGeracaoTermicaMotivoDespachoEntity.getValVerifgsub() != null) {
                    existingOnsGeracaoTermicaMotivoDespacho.setValVerifgsub(onsGeracaoTermicaMotivoDespachoEntity.getValVerifgsub());
                }
                if (onsGeracaoTermicaMotivoDespachoEntity.getValVerifunitcommitment() != null) {
                    existingOnsGeracaoTermicaMotivoDespacho.setValVerifunitcommitment(
                        onsGeracaoTermicaMotivoDespachoEntity.getValVerifunitcommitment()
                    );
                }
                if (onsGeracaoTermicaMotivoDespachoEntity.getValVerifconstrainedoff() != null) {
                    existingOnsGeracaoTermicaMotivoDespacho.setValVerifconstrainedoff(
                        onsGeracaoTermicaMotivoDespachoEntity.getValVerifconstrainedoff()
                    );
                }
                if (onsGeracaoTermicaMotivoDespachoEntity.getTipRestricaoeletrica() != null) {
                    existingOnsGeracaoTermicaMotivoDespacho.setTipRestricaoeletrica(
                        onsGeracaoTermicaMotivoDespachoEntity.getTipRestricaoeletrica()
                    );
                }

                return existingOnsGeracaoTermicaMotivoDespacho;
            })
            .map(onsGeracaoTermicaMotivoDespachoRepository::save)
            .map(savedOnsGeracaoTermicaMotivoDespacho -> {
                onsGeracaoTermicaMotivoDespachoSearchRepository.index(savedOnsGeracaoTermicaMotivoDespacho);
                return savedOnsGeracaoTermicaMotivoDespacho;
            });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, onsGeracaoTermicaMotivoDespachoEntity.getId().toString())
        );
    }

    /**
     * {@code GET  /ons-geracao-termica-motivo-despachos} : get all the onsGeracaoTermicaMotivoDespachos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsGeracaoTermicaMotivoDespachos in body.
     */
    @GetMapping("")
    public List<OnsGeracaoTermicaMotivoDespachoEntity> getAllOnsGeracaoTermicaMotivoDespachos() {
        LOG.debug("REST request to get all OnsGeracaoTermicaMotivoDespachos");
        return onsGeracaoTermicaMotivoDespachoRepository.findAll();
    }

    /**
     * {@code GET  /ons-geracao-termica-motivo-despachos/:id} : get the "id" onsGeracaoTermicaMotivoDespacho.
     *
     * @param id the id of the onsGeracaoTermicaMotivoDespachoEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsGeracaoTermicaMotivoDespachoEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OnsGeracaoTermicaMotivoDespachoEntity> getOnsGeracaoTermicaMotivoDespacho(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsGeracaoTermicaMotivoDespacho : {}", id);
        Optional<OnsGeracaoTermicaMotivoDespachoEntity> onsGeracaoTermicaMotivoDespachoEntity =
            onsGeracaoTermicaMotivoDespachoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsGeracaoTermicaMotivoDespachoEntity);
    }

    /**
     * {@code DELETE  /ons-geracao-termica-motivo-despachos/:id} : delete the "id" onsGeracaoTermicaMotivoDespacho.
     *
     * @param id the id of the onsGeracaoTermicaMotivoDespachoEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsGeracaoTermicaMotivoDespacho(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsGeracaoTermicaMotivoDespacho : {}", id);
        onsGeracaoTermicaMotivoDespachoRepository.deleteById(id);
        onsGeracaoTermicaMotivoDespachoSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-geracao-termica-motivo-despachos/_search?query=:query} : search for the onsGeracaoTermicaMotivoDespacho corresponding
     * to the query.
     *
     * @param query the query of the onsGeracaoTermicaMotivoDespacho search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsGeracaoTermicaMotivoDespachoEntity> searchOnsGeracaoTermicaMotivoDespachos(@RequestParam("query") String query) {
        LOG.debug("REST request to search OnsGeracaoTermicaMotivoDespachos for query {}", query);
        try {
            return StreamSupport.stream(onsGeracaoTermicaMotivoDespachoSearchRepository.search(query).spliterator(), false).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
