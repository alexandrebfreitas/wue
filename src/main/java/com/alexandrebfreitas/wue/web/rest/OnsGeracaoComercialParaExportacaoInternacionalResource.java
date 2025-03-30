package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsGeracaoComercialParaExportacaoInternacionalEntity;
import com.alexandrebfreitas.wue.repository.OnsGeracaoComercialParaExportacaoInternacionalRepository;
import com.alexandrebfreitas.wue.repository.search.OnsGeracaoComercialParaExportacaoInternacionalSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsGeracaoComercialParaExportacaoInternacionalEntity}.
 */
@RestController
@RequestMapping("/api/ons-geracao-comercial-para-exportacao-internacionals")
@Transactional
public class OnsGeracaoComercialParaExportacaoInternacionalResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsGeracaoComercialParaExportacaoInternacionalResource.class);

    private static final String ENTITY_NAME = "onsGeracaoComercialParaExportacaoInternacional";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsGeracaoComercialParaExportacaoInternacionalRepository onsGeracaoComercialParaExportacaoInternacionalRepository;

    private final OnsGeracaoComercialParaExportacaoInternacionalSearchRepository onsGeracaoComercialParaExportacaoInternacionalSearchRepository;

    public OnsGeracaoComercialParaExportacaoInternacionalResource(
        OnsGeracaoComercialParaExportacaoInternacionalRepository onsGeracaoComercialParaExportacaoInternacionalRepository,
        OnsGeracaoComercialParaExportacaoInternacionalSearchRepository onsGeracaoComercialParaExportacaoInternacionalSearchRepository
    ) {
        this.onsGeracaoComercialParaExportacaoInternacionalRepository = onsGeracaoComercialParaExportacaoInternacionalRepository;
        this.onsGeracaoComercialParaExportacaoInternacionalSearchRepository =
            onsGeracaoComercialParaExportacaoInternacionalSearchRepository;
    }

    /**
     * {@code POST  /ons-geracao-comercial-para-exportacao-internacionals} : Create a new onsGeracaoComercialParaExportacaoInternacional.
     *
     * @param onsGeracaoComercialParaExportacaoInternacionalEntity the onsGeracaoComercialParaExportacaoInternacionalEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsGeracaoComercialParaExportacaoInternacionalEntity, or with status {@code 400 (Bad Request)} if the onsGeracaoComercialParaExportacaoInternacional has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OnsGeracaoComercialParaExportacaoInternacionalEntity> createOnsGeracaoComercialParaExportacaoInternacional(
        @RequestBody OnsGeracaoComercialParaExportacaoInternacionalEntity onsGeracaoComercialParaExportacaoInternacionalEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to save OnsGeracaoComercialParaExportacaoInternacional : {}",
            onsGeracaoComercialParaExportacaoInternacionalEntity
        );
        if (onsGeracaoComercialParaExportacaoInternacionalEntity.getId() != null) {
            throw new BadRequestAlertException(
                "A new onsGeracaoComercialParaExportacaoInternacional cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        onsGeracaoComercialParaExportacaoInternacionalEntity = onsGeracaoComercialParaExportacaoInternacionalRepository.save(
            onsGeracaoComercialParaExportacaoInternacionalEntity
        );
        onsGeracaoComercialParaExportacaoInternacionalSearchRepository.index(onsGeracaoComercialParaExportacaoInternacionalEntity);
        return ResponseEntity.created(
            new URI(
                "/api/ons-geracao-comercial-para-exportacao-internacionals/" + onsGeracaoComercialParaExportacaoInternacionalEntity.getId()
            )
        )
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsGeracaoComercialParaExportacaoInternacionalEntity.getId().toString()
                )
            )
            .body(onsGeracaoComercialParaExportacaoInternacionalEntity);
    }

    /**
     * {@code PUT  /ons-geracao-comercial-para-exportacao-internacionals/:id} : Updates an existing onsGeracaoComercialParaExportacaoInternacional.
     *
     * @param id the id of the onsGeracaoComercialParaExportacaoInternacionalEntity to save.
     * @param onsGeracaoComercialParaExportacaoInternacionalEntity the onsGeracaoComercialParaExportacaoInternacionalEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsGeracaoComercialParaExportacaoInternacionalEntity,
     * or with status {@code 400 (Bad Request)} if the onsGeracaoComercialParaExportacaoInternacionalEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsGeracaoComercialParaExportacaoInternacionalEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OnsGeracaoComercialParaExportacaoInternacionalEntity> updateOnsGeracaoComercialParaExportacaoInternacional(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsGeracaoComercialParaExportacaoInternacionalEntity onsGeracaoComercialParaExportacaoInternacionalEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to update OnsGeracaoComercialParaExportacaoInternacional : {}, {}",
            id,
            onsGeracaoComercialParaExportacaoInternacionalEntity
        );
        if (onsGeracaoComercialParaExportacaoInternacionalEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsGeracaoComercialParaExportacaoInternacionalEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsGeracaoComercialParaExportacaoInternacionalRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsGeracaoComercialParaExportacaoInternacionalEntity = onsGeracaoComercialParaExportacaoInternacionalRepository.save(
            onsGeracaoComercialParaExportacaoInternacionalEntity
        );
        onsGeracaoComercialParaExportacaoInternacionalSearchRepository.index(onsGeracaoComercialParaExportacaoInternacionalEntity);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsGeracaoComercialParaExportacaoInternacionalEntity.getId().toString()
                )
            )
            .body(onsGeracaoComercialParaExportacaoInternacionalEntity);
    }

    /**
     * {@code PATCH  /ons-geracao-comercial-para-exportacao-internacionals/:id} : Partial updates given fields of an existing onsGeracaoComercialParaExportacaoInternacional, field will ignore if it is null
     *
     * @param id the id of the onsGeracaoComercialParaExportacaoInternacionalEntity to save.
     * @param onsGeracaoComercialParaExportacaoInternacionalEntity the onsGeracaoComercialParaExportacaoInternacionalEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsGeracaoComercialParaExportacaoInternacionalEntity,
     * or with status {@code 400 (Bad Request)} if the onsGeracaoComercialParaExportacaoInternacionalEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsGeracaoComercialParaExportacaoInternacionalEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsGeracaoComercialParaExportacaoInternacionalEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OnsGeracaoComercialParaExportacaoInternacionalEntity> partialUpdateOnsGeracaoComercialParaExportacaoInternacional(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsGeracaoComercialParaExportacaoInternacionalEntity onsGeracaoComercialParaExportacaoInternacionalEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to partial update OnsGeracaoComercialParaExportacaoInternacional partially : {}, {}",
            id,
            onsGeracaoComercialParaExportacaoInternacionalEntity
        );
        if (onsGeracaoComercialParaExportacaoInternacionalEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsGeracaoComercialParaExportacaoInternacionalEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsGeracaoComercialParaExportacaoInternacionalRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsGeracaoComercialParaExportacaoInternacionalEntity> result = onsGeracaoComercialParaExportacaoInternacionalRepository
            .findById(onsGeracaoComercialParaExportacaoInternacionalEntity.getId())
            .map(existingOnsGeracaoComercialParaExportacaoInternacional -> {
                if (onsGeracaoComercialParaExportacaoInternacionalEntity.getNomConversora() != null) {
                    existingOnsGeracaoComercialParaExportacaoInternacional.setNomConversora(
                        onsGeracaoComercialParaExportacaoInternacionalEntity.getNomConversora()
                    );
                }
                if (onsGeracaoComercialParaExportacaoInternacionalEntity.getDinInstante() != null) {
                    existingOnsGeracaoComercialParaExportacaoInternacional.setDinInstante(
                        onsGeracaoComercialParaExportacaoInternacionalEntity.getDinInstante()
                    );
                }
                if (onsGeracaoComercialParaExportacaoInternacionalEntity.getValModalidadecontratual() != null) {
                    existingOnsGeracaoComercialParaExportacaoInternacional.setValModalidadecontratual(
                        onsGeracaoComercialParaExportacaoInternacionalEntity.getValModalidadecontratual()
                    );
                }
                if (onsGeracaoComercialParaExportacaoInternacionalEntity.getValModalidadeemergencial() != null) {
                    existingOnsGeracaoComercialParaExportacaoInternacional.setValModalidadeemergencial(
                        onsGeracaoComercialParaExportacaoInternacionalEntity.getValModalidadeemergencial()
                    );
                }
                if (onsGeracaoComercialParaExportacaoInternacionalEntity.getValModalidadeoportunidade() != null) {
                    existingOnsGeracaoComercialParaExportacaoInternacional.setValModalidadeoportunidade(
                        onsGeracaoComercialParaExportacaoInternacionalEntity.getValModalidadeoportunidade()
                    );
                }
                if (onsGeracaoComercialParaExportacaoInternacionalEntity.getValModalidadeteste() != null) {
                    existingOnsGeracaoComercialParaExportacaoInternacional.setValModalidadeteste(
                        onsGeracaoComercialParaExportacaoInternacionalEntity.getValModalidadeteste()
                    );
                }
                if (onsGeracaoComercialParaExportacaoInternacionalEntity.getValModalidadeexcepcional() != null) {
                    existingOnsGeracaoComercialParaExportacaoInternacional.setValModalidadeexcepcional(
                        onsGeracaoComercialParaExportacaoInternacionalEntity.getValModalidadeexcepcional()
                    );
                }

                return existingOnsGeracaoComercialParaExportacaoInternacional;
            })
            .map(onsGeracaoComercialParaExportacaoInternacionalRepository::save)
            .map(savedOnsGeracaoComercialParaExportacaoInternacional -> {
                onsGeracaoComercialParaExportacaoInternacionalSearchRepository.index(savedOnsGeracaoComercialParaExportacaoInternacional);
                return savedOnsGeracaoComercialParaExportacaoInternacional;
            });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ENTITY_NAME,
                onsGeracaoComercialParaExportacaoInternacionalEntity.getId().toString()
            )
        );
    }

    /**
     * {@code GET  /ons-geracao-comercial-para-exportacao-internacionals} : get all the onsGeracaoComercialParaExportacaoInternacionals.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsGeracaoComercialParaExportacaoInternacionals in body.
     */
    @GetMapping("")
    public List<OnsGeracaoComercialParaExportacaoInternacionalEntity> getAllOnsGeracaoComercialParaExportacaoInternacionals() {
        LOG.debug("REST request to get all OnsGeracaoComercialParaExportacaoInternacionals");
        return onsGeracaoComercialParaExportacaoInternacionalRepository.findAll();
    }

    /**
     * {@code GET  /ons-geracao-comercial-para-exportacao-internacionals/:id} : get the "id" onsGeracaoComercialParaExportacaoInternacional.
     *
     * @param id the id of the onsGeracaoComercialParaExportacaoInternacionalEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsGeracaoComercialParaExportacaoInternacionalEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OnsGeracaoComercialParaExportacaoInternacionalEntity> getOnsGeracaoComercialParaExportacaoInternacional(
        @PathVariable("id") Long id
    ) {
        LOG.debug("REST request to get OnsGeracaoComercialParaExportacaoInternacional : {}", id);
        Optional<OnsGeracaoComercialParaExportacaoInternacionalEntity> onsGeracaoComercialParaExportacaoInternacionalEntity =
            onsGeracaoComercialParaExportacaoInternacionalRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsGeracaoComercialParaExportacaoInternacionalEntity);
    }

    /**
     * {@code DELETE  /ons-geracao-comercial-para-exportacao-internacionals/:id} : delete the "id" onsGeracaoComercialParaExportacaoInternacional.
     *
     * @param id the id of the onsGeracaoComercialParaExportacaoInternacionalEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsGeracaoComercialParaExportacaoInternacional(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsGeracaoComercialParaExportacaoInternacional : {}", id);
        onsGeracaoComercialParaExportacaoInternacionalRepository.deleteById(id);
        onsGeracaoComercialParaExportacaoInternacionalSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-geracao-comercial-para-exportacao-internacionals/_search?query=:query} : search for the onsGeracaoComercialParaExportacaoInternacional corresponding
     * to the query.
     *
     * @param query the query of the onsGeracaoComercialParaExportacaoInternacional search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsGeracaoComercialParaExportacaoInternacionalEntity> searchOnsGeracaoComercialParaExportacaoInternacionals(
        @RequestParam("query") String query
    ) {
        LOG.debug("REST request to search OnsGeracaoComercialParaExportacaoInternacionals for query {}", query);
        try {
            return StreamSupport.stream(
                onsGeracaoComercialParaExportacaoInternacionalSearchRepository.search(query).spliterator(),
                false
            ).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
