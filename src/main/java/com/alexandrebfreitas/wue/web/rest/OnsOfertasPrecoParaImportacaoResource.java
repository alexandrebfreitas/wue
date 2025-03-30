package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsOfertasPrecoParaImportacaoEntity;
import com.alexandrebfreitas.wue.repository.OnsOfertasPrecoParaImportacaoRepository;
import com.alexandrebfreitas.wue.repository.search.OnsOfertasPrecoParaImportacaoSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsOfertasPrecoParaImportacaoEntity}.
 */
@RestController
@RequestMapping("/api/ons-ofertas-preco-para-importacaos")
@Transactional
public class OnsOfertasPrecoParaImportacaoResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsOfertasPrecoParaImportacaoResource.class);

    private static final String ENTITY_NAME = "onsOfertasPrecoParaImportacao";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsOfertasPrecoParaImportacaoRepository onsOfertasPrecoParaImportacaoRepository;

    private final OnsOfertasPrecoParaImportacaoSearchRepository onsOfertasPrecoParaImportacaoSearchRepository;

    public OnsOfertasPrecoParaImportacaoResource(
        OnsOfertasPrecoParaImportacaoRepository onsOfertasPrecoParaImportacaoRepository,
        OnsOfertasPrecoParaImportacaoSearchRepository onsOfertasPrecoParaImportacaoSearchRepository
    ) {
        this.onsOfertasPrecoParaImportacaoRepository = onsOfertasPrecoParaImportacaoRepository;
        this.onsOfertasPrecoParaImportacaoSearchRepository = onsOfertasPrecoParaImportacaoSearchRepository;
    }

    /**
     * {@code POST  /ons-ofertas-preco-para-importacaos} : Create a new onsOfertasPrecoParaImportacao.
     *
     * @param onsOfertasPrecoParaImportacaoEntity the onsOfertasPrecoParaImportacaoEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsOfertasPrecoParaImportacaoEntity, or with status {@code 400 (Bad Request)} if the onsOfertasPrecoParaImportacao has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OnsOfertasPrecoParaImportacaoEntity> createOnsOfertasPrecoParaImportacao(
        @RequestBody OnsOfertasPrecoParaImportacaoEntity onsOfertasPrecoParaImportacaoEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to save OnsOfertasPrecoParaImportacao : {}", onsOfertasPrecoParaImportacaoEntity);
        if (onsOfertasPrecoParaImportacaoEntity.getId() != null) {
            throw new BadRequestAlertException("A new onsOfertasPrecoParaImportacao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        onsOfertasPrecoParaImportacaoEntity = onsOfertasPrecoParaImportacaoRepository.save(onsOfertasPrecoParaImportacaoEntity);
        onsOfertasPrecoParaImportacaoSearchRepository.index(onsOfertasPrecoParaImportacaoEntity);
        return ResponseEntity.created(new URI("/api/ons-ofertas-preco-para-importacaos/" + onsOfertasPrecoParaImportacaoEntity.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsOfertasPrecoParaImportacaoEntity.getId().toString()
                )
            )
            .body(onsOfertasPrecoParaImportacaoEntity);
    }

    /**
     * {@code PUT  /ons-ofertas-preco-para-importacaos/:id} : Updates an existing onsOfertasPrecoParaImportacao.
     *
     * @param id the id of the onsOfertasPrecoParaImportacaoEntity to save.
     * @param onsOfertasPrecoParaImportacaoEntity the onsOfertasPrecoParaImportacaoEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsOfertasPrecoParaImportacaoEntity,
     * or with status {@code 400 (Bad Request)} if the onsOfertasPrecoParaImportacaoEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsOfertasPrecoParaImportacaoEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OnsOfertasPrecoParaImportacaoEntity> updateOnsOfertasPrecoParaImportacao(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsOfertasPrecoParaImportacaoEntity onsOfertasPrecoParaImportacaoEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to update OnsOfertasPrecoParaImportacao : {}, {}", id, onsOfertasPrecoParaImportacaoEntity);
        if (onsOfertasPrecoParaImportacaoEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsOfertasPrecoParaImportacaoEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsOfertasPrecoParaImportacaoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsOfertasPrecoParaImportacaoEntity = onsOfertasPrecoParaImportacaoRepository.save(onsOfertasPrecoParaImportacaoEntity);
        onsOfertasPrecoParaImportacaoSearchRepository.index(onsOfertasPrecoParaImportacaoEntity);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsOfertasPrecoParaImportacaoEntity.getId().toString()
                )
            )
            .body(onsOfertasPrecoParaImportacaoEntity);
    }

    /**
     * {@code PATCH  /ons-ofertas-preco-para-importacaos/:id} : Partial updates given fields of an existing onsOfertasPrecoParaImportacao, field will ignore if it is null
     *
     * @param id the id of the onsOfertasPrecoParaImportacaoEntity to save.
     * @param onsOfertasPrecoParaImportacaoEntity the onsOfertasPrecoParaImportacaoEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsOfertasPrecoParaImportacaoEntity,
     * or with status {@code 400 (Bad Request)} if the onsOfertasPrecoParaImportacaoEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsOfertasPrecoParaImportacaoEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsOfertasPrecoParaImportacaoEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OnsOfertasPrecoParaImportacaoEntity> partialUpdateOnsOfertasPrecoParaImportacao(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsOfertasPrecoParaImportacaoEntity onsOfertasPrecoParaImportacaoEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to partial update OnsOfertasPrecoParaImportacao partially : {}, {}",
            id,
            onsOfertasPrecoParaImportacaoEntity
        );
        if (onsOfertasPrecoParaImportacaoEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsOfertasPrecoParaImportacaoEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsOfertasPrecoParaImportacaoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsOfertasPrecoParaImportacaoEntity> result = onsOfertasPrecoParaImportacaoRepository
            .findById(onsOfertasPrecoParaImportacaoEntity.getId())
            .map(existingOnsOfertasPrecoParaImportacao -> {
                if (onsOfertasPrecoParaImportacaoEntity.getNomPais() != null) {
                    existingOnsOfertasPrecoParaImportacao.setNomPais(onsOfertasPrecoParaImportacaoEntity.getNomPais());
                }
                if (onsOfertasPrecoParaImportacaoEntity.getNomAgente() != null) {
                    existingOnsOfertasPrecoParaImportacao.setNomAgente(onsOfertasPrecoParaImportacaoEntity.getNomAgente());
                }
                if (onsOfertasPrecoParaImportacaoEntity.getNomBloco() != null) {
                    existingOnsOfertasPrecoParaImportacao.setNomBloco(onsOfertasPrecoParaImportacaoEntity.getNomBloco());
                }
                if (onsOfertasPrecoParaImportacaoEntity.getDatIniciovalidade() != null) {
                    existingOnsOfertasPrecoParaImportacao.setDatIniciovalidade(onsOfertasPrecoParaImportacaoEntity.getDatIniciovalidade());
                }
                if (onsOfertasPrecoParaImportacaoEntity.getDatFimvalidade() != null) {
                    existingOnsOfertasPrecoParaImportacao.setDatFimvalidade(onsOfertasPrecoParaImportacaoEntity.getDatFimvalidade());
                }
                if (onsOfertasPrecoParaImportacaoEntity.getValPreco() != null) {
                    existingOnsOfertasPrecoParaImportacao.setValPreco(onsOfertasPrecoParaImportacaoEntity.getValPreco());
                }

                return existingOnsOfertasPrecoParaImportacao;
            })
            .map(onsOfertasPrecoParaImportacaoRepository::save)
            .map(savedOnsOfertasPrecoParaImportacao -> {
                onsOfertasPrecoParaImportacaoSearchRepository.index(savedOnsOfertasPrecoParaImportacao);
                return savedOnsOfertasPrecoParaImportacao;
            });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, onsOfertasPrecoParaImportacaoEntity.getId().toString())
        );
    }

    /**
     * {@code GET  /ons-ofertas-preco-para-importacaos} : get all the onsOfertasPrecoParaImportacaos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsOfertasPrecoParaImportacaos in body.
     */
    @GetMapping("")
    public List<OnsOfertasPrecoParaImportacaoEntity> getAllOnsOfertasPrecoParaImportacaos() {
        LOG.debug("REST request to get all OnsOfertasPrecoParaImportacaos");
        return onsOfertasPrecoParaImportacaoRepository.findAll();
    }

    /**
     * {@code GET  /ons-ofertas-preco-para-importacaos/:id} : get the "id" onsOfertasPrecoParaImportacao.
     *
     * @param id the id of the onsOfertasPrecoParaImportacaoEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsOfertasPrecoParaImportacaoEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OnsOfertasPrecoParaImportacaoEntity> getOnsOfertasPrecoParaImportacao(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsOfertasPrecoParaImportacao : {}", id);
        Optional<OnsOfertasPrecoParaImportacaoEntity> onsOfertasPrecoParaImportacaoEntity =
            onsOfertasPrecoParaImportacaoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsOfertasPrecoParaImportacaoEntity);
    }

    /**
     * {@code DELETE  /ons-ofertas-preco-para-importacaos/:id} : delete the "id" onsOfertasPrecoParaImportacao.
     *
     * @param id the id of the onsOfertasPrecoParaImportacaoEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsOfertasPrecoParaImportacao(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsOfertasPrecoParaImportacao : {}", id);
        onsOfertasPrecoParaImportacaoRepository.deleteById(id);
        onsOfertasPrecoParaImportacaoSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-ofertas-preco-para-importacaos/_search?query=:query} : search for the onsOfertasPrecoParaImportacao corresponding
     * to the query.
     *
     * @param query the query of the onsOfertasPrecoParaImportacao search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsOfertasPrecoParaImportacaoEntity> searchOnsOfertasPrecoParaImportacaos(@RequestParam("query") String query) {
        LOG.debug("REST request to search OnsOfertasPrecoParaImportacaos for query {}", query);
        try {
            return StreamSupport.stream(onsOfertasPrecoParaImportacaoSearchRepository.search(query).spliterator(), false).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
