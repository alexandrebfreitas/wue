package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsGeracaoUsinaEmBaseHorariaEntity;
import com.alexandrebfreitas.wue.repository.OnsGeracaoUsinaEmBaseHorariaRepository;
import com.alexandrebfreitas.wue.repository.search.OnsGeracaoUsinaEmBaseHorariaSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsGeracaoUsinaEmBaseHorariaEntity}.
 */
@RestController
@RequestMapping("/api/ons-geracao-usina-em-base-horarias")
@Transactional
public class OnsGeracaoUsinaEmBaseHorariaResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsGeracaoUsinaEmBaseHorariaResource.class);

    private static final String ENTITY_NAME = "onsGeracaoUsinaEmBaseHoraria";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsGeracaoUsinaEmBaseHorariaRepository onsGeracaoUsinaEmBaseHorariaRepository;

    private final OnsGeracaoUsinaEmBaseHorariaSearchRepository onsGeracaoUsinaEmBaseHorariaSearchRepository;

    public OnsGeracaoUsinaEmBaseHorariaResource(
        OnsGeracaoUsinaEmBaseHorariaRepository onsGeracaoUsinaEmBaseHorariaRepository,
        OnsGeracaoUsinaEmBaseHorariaSearchRepository onsGeracaoUsinaEmBaseHorariaSearchRepository
    ) {
        this.onsGeracaoUsinaEmBaseHorariaRepository = onsGeracaoUsinaEmBaseHorariaRepository;
        this.onsGeracaoUsinaEmBaseHorariaSearchRepository = onsGeracaoUsinaEmBaseHorariaSearchRepository;
    }

    /**
     * {@code POST  /ons-geracao-usina-em-base-horarias} : Create a new onsGeracaoUsinaEmBaseHoraria.
     *
     * @param onsGeracaoUsinaEmBaseHorariaEntity the onsGeracaoUsinaEmBaseHorariaEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsGeracaoUsinaEmBaseHorariaEntity, or with status {@code 400 (Bad Request)} if the onsGeracaoUsinaEmBaseHoraria has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OnsGeracaoUsinaEmBaseHorariaEntity> createOnsGeracaoUsinaEmBaseHoraria(
        @RequestBody OnsGeracaoUsinaEmBaseHorariaEntity onsGeracaoUsinaEmBaseHorariaEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to save OnsGeracaoUsinaEmBaseHoraria : {}", onsGeracaoUsinaEmBaseHorariaEntity);
        if (onsGeracaoUsinaEmBaseHorariaEntity.getId() != null) {
            throw new BadRequestAlertException("A new onsGeracaoUsinaEmBaseHoraria cannot already have an ID", ENTITY_NAME, "idexists");
        }
        onsGeracaoUsinaEmBaseHorariaEntity = onsGeracaoUsinaEmBaseHorariaRepository.save(onsGeracaoUsinaEmBaseHorariaEntity);
        onsGeracaoUsinaEmBaseHorariaSearchRepository.index(onsGeracaoUsinaEmBaseHorariaEntity);
        return ResponseEntity.created(new URI("/api/ons-geracao-usina-em-base-horarias/" + onsGeracaoUsinaEmBaseHorariaEntity.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsGeracaoUsinaEmBaseHorariaEntity.getId().toString()
                )
            )
            .body(onsGeracaoUsinaEmBaseHorariaEntity);
    }

    /**
     * {@code PUT  /ons-geracao-usina-em-base-horarias/:id} : Updates an existing onsGeracaoUsinaEmBaseHoraria.
     *
     * @param id the id of the onsGeracaoUsinaEmBaseHorariaEntity to save.
     * @param onsGeracaoUsinaEmBaseHorariaEntity the onsGeracaoUsinaEmBaseHorariaEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsGeracaoUsinaEmBaseHorariaEntity,
     * or with status {@code 400 (Bad Request)} if the onsGeracaoUsinaEmBaseHorariaEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsGeracaoUsinaEmBaseHorariaEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OnsGeracaoUsinaEmBaseHorariaEntity> updateOnsGeracaoUsinaEmBaseHoraria(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsGeracaoUsinaEmBaseHorariaEntity onsGeracaoUsinaEmBaseHorariaEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to update OnsGeracaoUsinaEmBaseHoraria : {}, {}", id, onsGeracaoUsinaEmBaseHorariaEntity);
        if (onsGeracaoUsinaEmBaseHorariaEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsGeracaoUsinaEmBaseHorariaEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsGeracaoUsinaEmBaseHorariaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsGeracaoUsinaEmBaseHorariaEntity = onsGeracaoUsinaEmBaseHorariaRepository.save(onsGeracaoUsinaEmBaseHorariaEntity);
        onsGeracaoUsinaEmBaseHorariaSearchRepository.index(onsGeracaoUsinaEmBaseHorariaEntity);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsGeracaoUsinaEmBaseHorariaEntity.getId().toString()
                )
            )
            .body(onsGeracaoUsinaEmBaseHorariaEntity);
    }

    /**
     * {@code PATCH  /ons-geracao-usina-em-base-horarias/:id} : Partial updates given fields of an existing onsGeracaoUsinaEmBaseHoraria, field will ignore if it is null
     *
     * @param id the id of the onsGeracaoUsinaEmBaseHorariaEntity to save.
     * @param onsGeracaoUsinaEmBaseHorariaEntity the onsGeracaoUsinaEmBaseHorariaEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsGeracaoUsinaEmBaseHorariaEntity,
     * or with status {@code 400 (Bad Request)} if the onsGeracaoUsinaEmBaseHorariaEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsGeracaoUsinaEmBaseHorariaEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsGeracaoUsinaEmBaseHorariaEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OnsGeracaoUsinaEmBaseHorariaEntity> partialUpdateOnsGeracaoUsinaEmBaseHoraria(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsGeracaoUsinaEmBaseHorariaEntity onsGeracaoUsinaEmBaseHorariaEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update OnsGeracaoUsinaEmBaseHoraria partially : {}, {}", id, onsGeracaoUsinaEmBaseHorariaEntity);
        if (onsGeracaoUsinaEmBaseHorariaEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsGeracaoUsinaEmBaseHorariaEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsGeracaoUsinaEmBaseHorariaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsGeracaoUsinaEmBaseHorariaEntity> result = onsGeracaoUsinaEmBaseHorariaRepository
            .findById(onsGeracaoUsinaEmBaseHorariaEntity.getId())
            .map(existingOnsGeracaoUsinaEmBaseHoraria -> {
                if (onsGeracaoUsinaEmBaseHorariaEntity.getDinInstante() != null) {
                    existingOnsGeracaoUsinaEmBaseHoraria.setDinInstante(onsGeracaoUsinaEmBaseHorariaEntity.getDinInstante());
                }
                if (onsGeracaoUsinaEmBaseHorariaEntity.getIdSubsistema() != null) {
                    existingOnsGeracaoUsinaEmBaseHoraria.setIdSubsistema(onsGeracaoUsinaEmBaseHorariaEntity.getIdSubsistema());
                }
                if (onsGeracaoUsinaEmBaseHorariaEntity.getNomSubsistema() != null) {
                    existingOnsGeracaoUsinaEmBaseHoraria.setNomSubsistema(onsGeracaoUsinaEmBaseHorariaEntity.getNomSubsistema());
                }
                if (onsGeracaoUsinaEmBaseHorariaEntity.getIdEstado() != null) {
                    existingOnsGeracaoUsinaEmBaseHoraria.setIdEstado(onsGeracaoUsinaEmBaseHorariaEntity.getIdEstado());
                }
                if (onsGeracaoUsinaEmBaseHorariaEntity.getNomEstado() != null) {
                    existingOnsGeracaoUsinaEmBaseHoraria.setNomEstado(onsGeracaoUsinaEmBaseHorariaEntity.getNomEstado());
                }
                if (onsGeracaoUsinaEmBaseHorariaEntity.getCodModalidadeoperacao() != null) {
                    existingOnsGeracaoUsinaEmBaseHoraria.setCodModalidadeoperacao(
                        onsGeracaoUsinaEmBaseHorariaEntity.getCodModalidadeoperacao()
                    );
                }
                if (onsGeracaoUsinaEmBaseHorariaEntity.getNomTipousina() != null) {
                    existingOnsGeracaoUsinaEmBaseHoraria.setNomTipousina(onsGeracaoUsinaEmBaseHorariaEntity.getNomTipousina());
                }
                if (onsGeracaoUsinaEmBaseHorariaEntity.getNomTipocombustivel() != null) {
                    existingOnsGeracaoUsinaEmBaseHoraria.setNomTipocombustivel(onsGeracaoUsinaEmBaseHorariaEntity.getNomTipocombustivel());
                }
                if (onsGeracaoUsinaEmBaseHorariaEntity.getNomUsina() != null) {
                    existingOnsGeracaoUsinaEmBaseHoraria.setNomUsina(onsGeracaoUsinaEmBaseHorariaEntity.getNomUsina());
                }
                if (onsGeracaoUsinaEmBaseHorariaEntity.getIdOns() != null) {
                    existingOnsGeracaoUsinaEmBaseHoraria.setIdOns(onsGeracaoUsinaEmBaseHorariaEntity.getIdOns());
                }
                if (onsGeracaoUsinaEmBaseHorariaEntity.getCeg() != null) {
                    existingOnsGeracaoUsinaEmBaseHoraria.setCeg(onsGeracaoUsinaEmBaseHorariaEntity.getCeg());
                }
                if (onsGeracaoUsinaEmBaseHorariaEntity.getValGeracao() != null) {
                    existingOnsGeracaoUsinaEmBaseHoraria.setValGeracao(onsGeracaoUsinaEmBaseHorariaEntity.getValGeracao());
                }

                return existingOnsGeracaoUsinaEmBaseHoraria;
            })
            .map(onsGeracaoUsinaEmBaseHorariaRepository::save)
            .map(savedOnsGeracaoUsinaEmBaseHoraria -> {
                onsGeracaoUsinaEmBaseHorariaSearchRepository.index(savedOnsGeracaoUsinaEmBaseHoraria);
                return savedOnsGeracaoUsinaEmBaseHoraria;
            });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, onsGeracaoUsinaEmBaseHorariaEntity.getId().toString())
        );
    }

    /**
     * {@code GET  /ons-geracao-usina-em-base-horarias} : get all the onsGeracaoUsinaEmBaseHorarias.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsGeracaoUsinaEmBaseHorarias in body.
     */
    @GetMapping("")
    public List<OnsGeracaoUsinaEmBaseHorariaEntity> getAllOnsGeracaoUsinaEmBaseHorarias() {
        LOG.debug("REST request to get all OnsGeracaoUsinaEmBaseHorarias");
        return onsGeracaoUsinaEmBaseHorariaRepository.findAll();
    }

    /**
     * {@code GET  /ons-geracao-usina-em-base-horarias/:id} : get the "id" onsGeracaoUsinaEmBaseHoraria.
     *
     * @param id the id of the onsGeracaoUsinaEmBaseHorariaEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsGeracaoUsinaEmBaseHorariaEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OnsGeracaoUsinaEmBaseHorariaEntity> getOnsGeracaoUsinaEmBaseHoraria(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsGeracaoUsinaEmBaseHoraria : {}", id);
        Optional<OnsGeracaoUsinaEmBaseHorariaEntity> onsGeracaoUsinaEmBaseHorariaEntity = onsGeracaoUsinaEmBaseHorariaRepository.findById(
            id
        );
        return ResponseUtil.wrapOrNotFound(onsGeracaoUsinaEmBaseHorariaEntity);
    }

    /**
     * {@code DELETE  /ons-geracao-usina-em-base-horarias/:id} : delete the "id" onsGeracaoUsinaEmBaseHoraria.
     *
     * @param id the id of the onsGeracaoUsinaEmBaseHorariaEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsGeracaoUsinaEmBaseHoraria(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsGeracaoUsinaEmBaseHoraria : {}", id);
        onsGeracaoUsinaEmBaseHorariaRepository.deleteById(id);
        onsGeracaoUsinaEmBaseHorariaSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-geracao-usina-em-base-horarias/_search?query=:query} : search for the onsGeracaoUsinaEmBaseHoraria corresponding
     * to the query.
     *
     * @param query the query of the onsGeracaoUsinaEmBaseHoraria search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsGeracaoUsinaEmBaseHorariaEntity> searchOnsGeracaoUsinaEmBaseHorarias(@RequestParam("query") String query) {
        LOG.debug("REST request to search OnsGeracaoUsinaEmBaseHorarias for query {}", query);
        try {
            return StreamSupport.stream(onsGeracaoUsinaEmBaseHorariaSearchRepository.search(query).spliterator(), false).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
