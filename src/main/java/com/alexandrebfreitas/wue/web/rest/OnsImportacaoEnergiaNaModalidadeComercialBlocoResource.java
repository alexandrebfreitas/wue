package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity;
import com.alexandrebfreitas.wue.repository.OnsImportacaoEnergiaNaModalidadeComercialBlocoRepository;
import com.alexandrebfreitas.wue.repository.search.OnsImportacaoEnergiaNaModalidadeComercialBlocoSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity}.
 */
@RestController
@RequestMapping("/api/ons-importacao-energia-na-modalidade-comercial-blocos")
@Transactional
public class OnsImportacaoEnergiaNaModalidadeComercialBlocoResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsImportacaoEnergiaNaModalidadeComercialBlocoResource.class);

    private static final String ENTITY_NAME = "onsImportacaoEnergiaNaModalidadeComercialBloco";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsImportacaoEnergiaNaModalidadeComercialBlocoRepository onsImportacaoEnergiaNaModalidadeComercialBlocoRepository;

    private final OnsImportacaoEnergiaNaModalidadeComercialBlocoSearchRepository onsImportacaoEnergiaNaModalidadeComercialBlocoSearchRepository;

    public OnsImportacaoEnergiaNaModalidadeComercialBlocoResource(
        OnsImportacaoEnergiaNaModalidadeComercialBlocoRepository onsImportacaoEnergiaNaModalidadeComercialBlocoRepository,
        OnsImportacaoEnergiaNaModalidadeComercialBlocoSearchRepository onsImportacaoEnergiaNaModalidadeComercialBlocoSearchRepository
    ) {
        this.onsImportacaoEnergiaNaModalidadeComercialBlocoRepository = onsImportacaoEnergiaNaModalidadeComercialBlocoRepository;
        this.onsImportacaoEnergiaNaModalidadeComercialBlocoSearchRepository =
            onsImportacaoEnergiaNaModalidadeComercialBlocoSearchRepository;
    }

    /**
     * {@code POST  /ons-importacao-energia-na-modalidade-comercial-blocos} : Create a new onsImportacaoEnergiaNaModalidadeComercialBloco.
     *
     * @param onsImportacaoEnergiaNaModalidadeComercialBlocoEntity the onsImportacaoEnergiaNaModalidadeComercialBlocoEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsImportacaoEnergiaNaModalidadeComercialBlocoEntity, or with status {@code 400 (Bad Request)} if the onsImportacaoEnergiaNaModalidadeComercialBloco has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity> createOnsImportacaoEnergiaNaModalidadeComercialBloco(
        @RequestBody OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity onsImportacaoEnergiaNaModalidadeComercialBlocoEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to save OnsImportacaoEnergiaNaModalidadeComercialBloco : {}",
            onsImportacaoEnergiaNaModalidadeComercialBlocoEntity
        );
        if (onsImportacaoEnergiaNaModalidadeComercialBlocoEntity.getId() != null) {
            throw new BadRequestAlertException(
                "A new onsImportacaoEnergiaNaModalidadeComercialBloco cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        onsImportacaoEnergiaNaModalidadeComercialBlocoEntity = onsImportacaoEnergiaNaModalidadeComercialBlocoRepository.save(
            onsImportacaoEnergiaNaModalidadeComercialBlocoEntity
        );
        onsImportacaoEnergiaNaModalidadeComercialBlocoSearchRepository.index(onsImportacaoEnergiaNaModalidadeComercialBlocoEntity);
        return ResponseEntity.created(
            new URI(
                "/api/ons-importacao-energia-na-modalidade-comercial-blocos/" + onsImportacaoEnergiaNaModalidadeComercialBlocoEntity.getId()
            )
        )
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsImportacaoEnergiaNaModalidadeComercialBlocoEntity.getId().toString()
                )
            )
            .body(onsImportacaoEnergiaNaModalidadeComercialBlocoEntity);
    }

    /**
     * {@code PUT  /ons-importacao-energia-na-modalidade-comercial-blocos/:id} : Updates an existing onsImportacaoEnergiaNaModalidadeComercialBloco.
     *
     * @param id the id of the onsImportacaoEnergiaNaModalidadeComercialBlocoEntity to save.
     * @param onsImportacaoEnergiaNaModalidadeComercialBlocoEntity the onsImportacaoEnergiaNaModalidadeComercialBlocoEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsImportacaoEnergiaNaModalidadeComercialBlocoEntity,
     * or with status {@code 400 (Bad Request)} if the onsImportacaoEnergiaNaModalidadeComercialBlocoEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsImportacaoEnergiaNaModalidadeComercialBlocoEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity> updateOnsImportacaoEnergiaNaModalidadeComercialBloco(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity onsImportacaoEnergiaNaModalidadeComercialBlocoEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to update OnsImportacaoEnergiaNaModalidadeComercialBloco : {}, {}",
            id,
            onsImportacaoEnergiaNaModalidadeComercialBlocoEntity
        );
        if (onsImportacaoEnergiaNaModalidadeComercialBlocoEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsImportacaoEnergiaNaModalidadeComercialBlocoEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsImportacaoEnergiaNaModalidadeComercialBlocoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsImportacaoEnergiaNaModalidadeComercialBlocoEntity = onsImportacaoEnergiaNaModalidadeComercialBlocoRepository.save(
            onsImportacaoEnergiaNaModalidadeComercialBlocoEntity
        );
        onsImportacaoEnergiaNaModalidadeComercialBlocoSearchRepository.index(onsImportacaoEnergiaNaModalidadeComercialBlocoEntity);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsImportacaoEnergiaNaModalidadeComercialBlocoEntity.getId().toString()
                )
            )
            .body(onsImportacaoEnergiaNaModalidadeComercialBlocoEntity);
    }

    /**
     * {@code PATCH  /ons-importacao-energia-na-modalidade-comercial-blocos/:id} : Partial updates given fields of an existing onsImportacaoEnergiaNaModalidadeComercialBloco, field will ignore if it is null
     *
     * @param id the id of the onsImportacaoEnergiaNaModalidadeComercialBlocoEntity to save.
     * @param onsImportacaoEnergiaNaModalidadeComercialBlocoEntity the onsImportacaoEnergiaNaModalidadeComercialBlocoEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsImportacaoEnergiaNaModalidadeComercialBlocoEntity,
     * or with status {@code 400 (Bad Request)} if the onsImportacaoEnergiaNaModalidadeComercialBlocoEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsImportacaoEnergiaNaModalidadeComercialBlocoEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsImportacaoEnergiaNaModalidadeComercialBlocoEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity> partialUpdateOnsImportacaoEnergiaNaModalidadeComercialBloco(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity onsImportacaoEnergiaNaModalidadeComercialBlocoEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to partial update OnsImportacaoEnergiaNaModalidadeComercialBloco partially : {}, {}",
            id,
            onsImportacaoEnergiaNaModalidadeComercialBlocoEntity
        );
        if (onsImportacaoEnergiaNaModalidadeComercialBlocoEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsImportacaoEnergiaNaModalidadeComercialBlocoEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsImportacaoEnergiaNaModalidadeComercialBlocoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity> result = onsImportacaoEnergiaNaModalidadeComercialBlocoRepository
            .findById(onsImportacaoEnergiaNaModalidadeComercialBlocoEntity.getId())
            .map(existingOnsImportacaoEnergiaNaModalidadeComercialBloco -> {
                if (onsImportacaoEnergiaNaModalidadeComercialBlocoEntity.getNomPais() != null) {
                    existingOnsImportacaoEnergiaNaModalidadeComercialBloco.setNomPais(
                        onsImportacaoEnergiaNaModalidadeComercialBlocoEntity.getNomPais()
                    );
                }
                if (onsImportacaoEnergiaNaModalidadeComercialBlocoEntity.getNomAgente() != null) {
                    existingOnsImportacaoEnergiaNaModalidadeComercialBloco.setNomAgente(
                        onsImportacaoEnergiaNaModalidadeComercialBlocoEntity.getNomAgente()
                    );
                }
                if (onsImportacaoEnergiaNaModalidadeComercialBlocoEntity.getNomBloco() != null) {
                    existingOnsImportacaoEnergiaNaModalidadeComercialBloco.setNomBloco(
                        onsImportacaoEnergiaNaModalidadeComercialBlocoEntity.getNomBloco()
                    );
                }
                if (onsImportacaoEnergiaNaModalidadeComercialBlocoEntity.getCodBloco() != null) {
                    existingOnsImportacaoEnergiaNaModalidadeComercialBloco.setCodBloco(
                        onsImportacaoEnergiaNaModalidadeComercialBlocoEntity.getCodBloco()
                    );
                }
                if (onsImportacaoEnergiaNaModalidadeComercialBlocoEntity.getDinInstante() != null) {
                    existingOnsImportacaoEnergiaNaModalidadeComercialBloco.setDinInstante(
                        onsImportacaoEnergiaNaModalidadeComercialBlocoEntity.getDinInstante()
                    );
                }
                if (onsImportacaoEnergiaNaModalidadeComercialBlocoEntity.getValImportacaoprogramada() != null) {
                    existingOnsImportacaoEnergiaNaModalidadeComercialBloco.setValImportacaoprogramada(
                        onsImportacaoEnergiaNaModalidadeComercialBlocoEntity.getValImportacaoprogramada()
                    );
                }
                if (onsImportacaoEnergiaNaModalidadeComercialBlocoEntity.getValImportacaodespachada() != null) {
                    existingOnsImportacaoEnergiaNaModalidadeComercialBloco.setValImportacaodespachada(
                        onsImportacaoEnergiaNaModalidadeComercialBlocoEntity.getValImportacaodespachada()
                    );
                }
                if (onsImportacaoEnergiaNaModalidadeComercialBlocoEntity.getValImportacaoverificada() != null) {
                    existingOnsImportacaoEnergiaNaModalidadeComercialBloco.setValImportacaoverificada(
                        onsImportacaoEnergiaNaModalidadeComercialBlocoEntity.getValImportacaoverificada()
                    );
                }
                if (onsImportacaoEnergiaNaModalidadeComercialBlocoEntity.getValPreco() != null) {
                    existingOnsImportacaoEnergiaNaModalidadeComercialBloco.setValPreco(
                        onsImportacaoEnergiaNaModalidadeComercialBlocoEntity.getValPreco()
                    );
                }

                return existingOnsImportacaoEnergiaNaModalidadeComercialBloco;
            })
            .map(onsImportacaoEnergiaNaModalidadeComercialBlocoRepository::save)
            .map(savedOnsImportacaoEnergiaNaModalidadeComercialBloco -> {
                onsImportacaoEnergiaNaModalidadeComercialBlocoSearchRepository.index(savedOnsImportacaoEnergiaNaModalidadeComercialBloco);
                return savedOnsImportacaoEnergiaNaModalidadeComercialBloco;
            });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ENTITY_NAME,
                onsImportacaoEnergiaNaModalidadeComercialBlocoEntity.getId().toString()
            )
        );
    }

    /**
     * {@code GET  /ons-importacao-energia-na-modalidade-comercial-blocos} : get all the onsImportacaoEnergiaNaModalidadeComercialBlocos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsImportacaoEnergiaNaModalidadeComercialBlocos in body.
     */
    @GetMapping("")
    public List<OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity> getAllOnsImportacaoEnergiaNaModalidadeComercialBlocos() {
        LOG.debug("REST request to get all OnsImportacaoEnergiaNaModalidadeComercialBlocos");
        return onsImportacaoEnergiaNaModalidadeComercialBlocoRepository.findAll();
    }

    /**
     * {@code GET  /ons-importacao-energia-na-modalidade-comercial-blocos/:id} : get the "id" onsImportacaoEnergiaNaModalidadeComercialBloco.
     *
     * @param id the id of the onsImportacaoEnergiaNaModalidadeComercialBlocoEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsImportacaoEnergiaNaModalidadeComercialBlocoEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity> getOnsImportacaoEnergiaNaModalidadeComercialBloco(
        @PathVariable("id") Long id
    ) {
        LOG.debug("REST request to get OnsImportacaoEnergiaNaModalidadeComercialBloco : {}", id);
        Optional<OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity> onsImportacaoEnergiaNaModalidadeComercialBlocoEntity =
            onsImportacaoEnergiaNaModalidadeComercialBlocoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsImportacaoEnergiaNaModalidadeComercialBlocoEntity);
    }

    /**
     * {@code DELETE  /ons-importacao-energia-na-modalidade-comercial-blocos/:id} : delete the "id" onsImportacaoEnergiaNaModalidadeComercialBloco.
     *
     * @param id the id of the onsImportacaoEnergiaNaModalidadeComercialBlocoEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsImportacaoEnergiaNaModalidadeComercialBloco(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsImportacaoEnergiaNaModalidadeComercialBloco : {}", id);
        onsImportacaoEnergiaNaModalidadeComercialBlocoRepository.deleteById(id);
        onsImportacaoEnergiaNaModalidadeComercialBlocoSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-importacao-energia-na-modalidade-comercial-blocos/_search?query=:query} : search for the onsImportacaoEnergiaNaModalidadeComercialBloco corresponding
     * to the query.
     *
     * @param query the query of the onsImportacaoEnergiaNaModalidadeComercialBloco search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity> searchOnsImportacaoEnergiaNaModalidadeComercialBlocos(
        @RequestParam("query") String query
    ) {
        LOG.debug("REST request to search OnsImportacaoEnergiaNaModalidadeComercialBlocos for query {}", query);
        try {
            return StreamSupport.stream(
                onsImportacaoEnergiaNaModalidadeComercialBlocoSearchRepository.search(query).spliterator(),
                false
            ).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
