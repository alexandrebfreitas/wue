package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsModalidadeOperacaoUsinasEntity;
import com.alexandrebfreitas.wue.repository.OnsModalidadeOperacaoUsinasRepository;
import com.alexandrebfreitas.wue.repository.search.OnsModalidadeOperacaoUsinasSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsModalidadeOperacaoUsinasEntity}.
 */
@RestController
@RequestMapping("/api/ons-modalidade-operacao-usinas")
@Transactional
public class OnsModalidadeOperacaoUsinasResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsModalidadeOperacaoUsinasResource.class);

    private static final String ENTITY_NAME = "onsModalidadeOperacaoUsinas";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsModalidadeOperacaoUsinasRepository onsModalidadeOperacaoUsinasRepository;

    private final OnsModalidadeOperacaoUsinasSearchRepository onsModalidadeOperacaoUsinasSearchRepository;

    public OnsModalidadeOperacaoUsinasResource(
        OnsModalidadeOperacaoUsinasRepository onsModalidadeOperacaoUsinasRepository,
        OnsModalidadeOperacaoUsinasSearchRepository onsModalidadeOperacaoUsinasSearchRepository
    ) {
        this.onsModalidadeOperacaoUsinasRepository = onsModalidadeOperacaoUsinasRepository;
        this.onsModalidadeOperacaoUsinasSearchRepository = onsModalidadeOperacaoUsinasSearchRepository;
    }

    /**
     * {@code POST  /ons-modalidade-operacao-usinas} : Create a new onsModalidadeOperacaoUsinas.
     *
     * @param onsModalidadeOperacaoUsinasEntity the onsModalidadeOperacaoUsinasEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsModalidadeOperacaoUsinasEntity, or with status {@code 400 (Bad Request)} if the onsModalidadeOperacaoUsinas has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OnsModalidadeOperacaoUsinasEntity> createOnsModalidadeOperacaoUsinas(
        @RequestBody OnsModalidadeOperacaoUsinasEntity onsModalidadeOperacaoUsinasEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to save OnsModalidadeOperacaoUsinas : {}", onsModalidadeOperacaoUsinasEntity);
        if (onsModalidadeOperacaoUsinasEntity.getId() != null) {
            throw new BadRequestAlertException("A new onsModalidadeOperacaoUsinas cannot already have an ID", ENTITY_NAME, "idexists");
        }
        onsModalidadeOperacaoUsinasEntity = onsModalidadeOperacaoUsinasRepository.save(onsModalidadeOperacaoUsinasEntity);
        onsModalidadeOperacaoUsinasSearchRepository.index(onsModalidadeOperacaoUsinasEntity);
        return ResponseEntity.created(new URI("/api/ons-modalidade-operacao-usinas/" + onsModalidadeOperacaoUsinasEntity.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsModalidadeOperacaoUsinasEntity.getId().toString()
                )
            )
            .body(onsModalidadeOperacaoUsinasEntity);
    }

    /**
     * {@code PUT  /ons-modalidade-operacao-usinas/:id} : Updates an existing onsModalidadeOperacaoUsinas.
     *
     * @param id the id of the onsModalidadeOperacaoUsinasEntity to save.
     * @param onsModalidadeOperacaoUsinasEntity the onsModalidadeOperacaoUsinasEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsModalidadeOperacaoUsinasEntity,
     * or with status {@code 400 (Bad Request)} if the onsModalidadeOperacaoUsinasEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsModalidadeOperacaoUsinasEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OnsModalidadeOperacaoUsinasEntity> updateOnsModalidadeOperacaoUsinas(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsModalidadeOperacaoUsinasEntity onsModalidadeOperacaoUsinasEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to update OnsModalidadeOperacaoUsinas : {}, {}", id, onsModalidadeOperacaoUsinasEntity);
        if (onsModalidadeOperacaoUsinasEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsModalidadeOperacaoUsinasEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsModalidadeOperacaoUsinasRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsModalidadeOperacaoUsinasEntity = onsModalidadeOperacaoUsinasRepository.save(onsModalidadeOperacaoUsinasEntity);
        onsModalidadeOperacaoUsinasSearchRepository.index(onsModalidadeOperacaoUsinasEntity);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, onsModalidadeOperacaoUsinasEntity.getId().toString())
            )
            .body(onsModalidadeOperacaoUsinasEntity);
    }

    /**
     * {@code PATCH  /ons-modalidade-operacao-usinas/:id} : Partial updates given fields of an existing onsModalidadeOperacaoUsinas, field will ignore if it is null
     *
     * @param id the id of the onsModalidadeOperacaoUsinasEntity to save.
     * @param onsModalidadeOperacaoUsinasEntity the onsModalidadeOperacaoUsinasEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsModalidadeOperacaoUsinasEntity,
     * or with status {@code 400 (Bad Request)} if the onsModalidadeOperacaoUsinasEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsModalidadeOperacaoUsinasEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsModalidadeOperacaoUsinasEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OnsModalidadeOperacaoUsinasEntity> partialUpdateOnsModalidadeOperacaoUsinas(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsModalidadeOperacaoUsinasEntity onsModalidadeOperacaoUsinasEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update OnsModalidadeOperacaoUsinas partially : {}, {}", id, onsModalidadeOperacaoUsinasEntity);
        if (onsModalidadeOperacaoUsinasEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsModalidadeOperacaoUsinasEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsModalidadeOperacaoUsinasRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsModalidadeOperacaoUsinasEntity> result = onsModalidadeOperacaoUsinasRepository
            .findById(onsModalidadeOperacaoUsinasEntity.getId())
            .map(existingOnsModalidadeOperacaoUsinas -> {
                if (onsModalidadeOperacaoUsinasEntity.getNomUsina() != null) {
                    existingOnsModalidadeOperacaoUsinas.setNomUsina(onsModalidadeOperacaoUsinasEntity.getNomUsina());
                }
                if (onsModalidadeOperacaoUsinasEntity.getCeg() != null) {
                    existingOnsModalidadeOperacaoUsinas.setCeg(onsModalidadeOperacaoUsinasEntity.getCeg());
                }
                if (onsModalidadeOperacaoUsinasEntity.getNomModalidadeoperacao() != null) {
                    existingOnsModalidadeOperacaoUsinas.setNomModalidadeoperacao(
                        onsModalidadeOperacaoUsinasEntity.getNomModalidadeoperacao()
                    );
                }
                if (onsModalidadeOperacaoUsinasEntity.getValPotenciaautorizada() != null) {
                    existingOnsModalidadeOperacaoUsinas.setValPotenciaautorizada(
                        onsModalidadeOperacaoUsinasEntity.getValPotenciaautorizada()
                    );
                }
                if (onsModalidadeOperacaoUsinasEntity.getSglCentrooperacao() != null) {
                    existingOnsModalidadeOperacaoUsinas.setSglCentrooperacao(onsModalidadeOperacaoUsinasEntity.getSglCentrooperacao());
                }
                if (onsModalidadeOperacaoUsinasEntity.getNomPontoconexao() != null) {
                    existingOnsModalidadeOperacaoUsinas.setNomPontoconexao(onsModalidadeOperacaoUsinasEntity.getNomPontoconexao());
                }
                if (onsModalidadeOperacaoUsinasEntity.getIdEstado() != null) {
                    existingOnsModalidadeOperacaoUsinas.setIdEstado(onsModalidadeOperacaoUsinasEntity.getIdEstado());
                }
                if (onsModalidadeOperacaoUsinasEntity.getNomEstado() != null) {
                    existingOnsModalidadeOperacaoUsinas.setNomEstado(onsModalidadeOperacaoUsinasEntity.getNomEstado());
                }
                if (onsModalidadeOperacaoUsinasEntity.getStsAneel() != null) {
                    existingOnsModalidadeOperacaoUsinas.setStsAneel(onsModalidadeOperacaoUsinasEntity.getStsAneel());
                }

                return existingOnsModalidadeOperacaoUsinas;
            })
            .map(onsModalidadeOperacaoUsinasRepository::save)
            .map(savedOnsModalidadeOperacaoUsinas -> {
                onsModalidadeOperacaoUsinasSearchRepository.index(savedOnsModalidadeOperacaoUsinas);
                return savedOnsModalidadeOperacaoUsinas;
            });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, onsModalidadeOperacaoUsinasEntity.getId().toString())
        );
    }

    /**
     * {@code GET  /ons-modalidade-operacao-usinas} : get all the onsModalidadeOperacaoUsinas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsModalidadeOperacaoUsinas in body.
     */
    @GetMapping("")
    public List<OnsModalidadeOperacaoUsinasEntity> getAllOnsModalidadeOperacaoUsinas() {
        LOG.debug("REST request to get all OnsModalidadeOperacaoUsinas");
        return onsModalidadeOperacaoUsinasRepository.findAll();
    }

    /**
     * {@code GET  /ons-modalidade-operacao-usinas/:id} : get the "id" onsModalidadeOperacaoUsinas.
     *
     * @param id the id of the onsModalidadeOperacaoUsinasEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsModalidadeOperacaoUsinasEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OnsModalidadeOperacaoUsinasEntity> getOnsModalidadeOperacaoUsinas(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsModalidadeOperacaoUsinas : {}", id);
        Optional<OnsModalidadeOperacaoUsinasEntity> onsModalidadeOperacaoUsinasEntity = onsModalidadeOperacaoUsinasRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsModalidadeOperacaoUsinasEntity);
    }

    /**
     * {@code DELETE  /ons-modalidade-operacao-usinas/:id} : delete the "id" onsModalidadeOperacaoUsinas.
     *
     * @param id the id of the onsModalidadeOperacaoUsinasEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsModalidadeOperacaoUsinas(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsModalidadeOperacaoUsinas : {}", id);
        onsModalidadeOperacaoUsinasRepository.deleteById(id);
        onsModalidadeOperacaoUsinasSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-modalidade-operacao-usinas/_search?query=:query} : search for the onsModalidadeOperacaoUsinas corresponding
     * to the query.
     *
     * @param query the query of the onsModalidadeOperacaoUsinas search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsModalidadeOperacaoUsinasEntity> searchOnsModalidadeOperacaoUsinas(@RequestParam("query") String query) {
        LOG.debug("REST request to search OnsModalidadeOperacaoUsinas for query {}", query);
        try {
            return StreamSupport.stream(onsModalidadeOperacaoUsinasSearchRepository.search(query).spliterator(), false).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
