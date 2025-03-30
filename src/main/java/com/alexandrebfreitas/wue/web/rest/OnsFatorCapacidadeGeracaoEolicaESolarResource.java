package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsFatorCapacidadeGeracaoEolicaESolarEntity;
import com.alexandrebfreitas.wue.repository.OnsFatorCapacidadeGeracaoEolicaESolarRepository;
import com.alexandrebfreitas.wue.repository.search.OnsFatorCapacidadeGeracaoEolicaESolarSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsFatorCapacidadeGeracaoEolicaESolarEntity}.
 */
@RestController
@RequestMapping("/api/ons-fator-capacidade-geracao-eolica-e-solars")
@Transactional
public class OnsFatorCapacidadeGeracaoEolicaESolarResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsFatorCapacidadeGeracaoEolicaESolarResource.class);

    private static final String ENTITY_NAME = "onsFatorCapacidadeGeracaoEolicaESolar";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsFatorCapacidadeGeracaoEolicaESolarRepository onsFatorCapacidadeGeracaoEolicaESolarRepository;

    private final OnsFatorCapacidadeGeracaoEolicaESolarSearchRepository onsFatorCapacidadeGeracaoEolicaESolarSearchRepository;

    public OnsFatorCapacidadeGeracaoEolicaESolarResource(
        OnsFatorCapacidadeGeracaoEolicaESolarRepository onsFatorCapacidadeGeracaoEolicaESolarRepository,
        OnsFatorCapacidadeGeracaoEolicaESolarSearchRepository onsFatorCapacidadeGeracaoEolicaESolarSearchRepository
    ) {
        this.onsFatorCapacidadeGeracaoEolicaESolarRepository = onsFatorCapacidadeGeracaoEolicaESolarRepository;
        this.onsFatorCapacidadeGeracaoEolicaESolarSearchRepository = onsFatorCapacidadeGeracaoEolicaESolarSearchRepository;
    }

    /**
     * {@code POST  /ons-fator-capacidade-geracao-eolica-e-solars} : Create a new onsFatorCapacidadeGeracaoEolicaESolar.
     *
     * @param onsFatorCapacidadeGeracaoEolicaESolarEntity the onsFatorCapacidadeGeracaoEolicaESolarEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsFatorCapacidadeGeracaoEolicaESolarEntity, or with status {@code 400 (Bad Request)} if the onsFatorCapacidadeGeracaoEolicaESolar has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OnsFatorCapacidadeGeracaoEolicaESolarEntity> createOnsFatorCapacidadeGeracaoEolicaESolar(
        @RequestBody OnsFatorCapacidadeGeracaoEolicaESolarEntity onsFatorCapacidadeGeracaoEolicaESolarEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to save OnsFatorCapacidadeGeracaoEolicaESolar : {}", onsFatorCapacidadeGeracaoEolicaESolarEntity);
        if (onsFatorCapacidadeGeracaoEolicaESolarEntity.getId() != null) {
            throw new BadRequestAlertException(
                "A new onsFatorCapacidadeGeracaoEolicaESolar cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        onsFatorCapacidadeGeracaoEolicaESolarEntity = onsFatorCapacidadeGeracaoEolicaESolarRepository.save(
            onsFatorCapacidadeGeracaoEolicaESolarEntity
        );
        onsFatorCapacidadeGeracaoEolicaESolarSearchRepository.index(onsFatorCapacidadeGeracaoEolicaESolarEntity);
        return ResponseEntity.created(
            new URI("/api/ons-fator-capacidade-geracao-eolica-e-solars/" + onsFatorCapacidadeGeracaoEolicaESolarEntity.getId())
        )
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsFatorCapacidadeGeracaoEolicaESolarEntity.getId().toString()
                )
            )
            .body(onsFatorCapacidadeGeracaoEolicaESolarEntity);
    }

    /**
     * {@code PUT  /ons-fator-capacidade-geracao-eolica-e-solars/:id} : Updates an existing onsFatorCapacidadeGeracaoEolicaESolar.
     *
     * @param id the id of the onsFatorCapacidadeGeracaoEolicaESolarEntity to save.
     * @param onsFatorCapacidadeGeracaoEolicaESolarEntity the onsFatorCapacidadeGeracaoEolicaESolarEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsFatorCapacidadeGeracaoEolicaESolarEntity,
     * or with status {@code 400 (Bad Request)} if the onsFatorCapacidadeGeracaoEolicaESolarEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsFatorCapacidadeGeracaoEolicaESolarEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OnsFatorCapacidadeGeracaoEolicaESolarEntity> updateOnsFatorCapacidadeGeracaoEolicaESolar(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsFatorCapacidadeGeracaoEolicaESolarEntity onsFatorCapacidadeGeracaoEolicaESolarEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to update OnsFatorCapacidadeGeracaoEolicaESolar : {}, {}", id, onsFatorCapacidadeGeracaoEolicaESolarEntity);
        if (onsFatorCapacidadeGeracaoEolicaESolarEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsFatorCapacidadeGeracaoEolicaESolarEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsFatorCapacidadeGeracaoEolicaESolarRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsFatorCapacidadeGeracaoEolicaESolarEntity = onsFatorCapacidadeGeracaoEolicaESolarRepository.save(
            onsFatorCapacidadeGeracaoEolicaESolarEntity
        );
        onsFatorCapacidadeGeracaoEolicaESolarSearchRepository.index(onsFatorCapacidadeGeracaoEolicaESolarEntity);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsFatorCapacidadeGeracaoEolicaESolarEntity.getId().toString()
                )
            )
            .body(onsFatorCapacidadeGeracaoEolicaESolarEntity);
    }

    /**
     * {@code PATCH  /ons-fator-capacidade-geracao-eolica-e-solars/:id} : Partial updates given fields of an existing onsFatorCapacidadeGeracaoEolicaESolar, field will ignore if it is null
     *
     * @param id the id of the onsFatorCapacidadeGeracaoEolicaESolarEntity to save.
     * @param onsFatorCapacidadeGeracaoEolicaESolarEntity the onsFatorCapacidadeGeracaoEolicaESolarEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsFatorCapacidadeGeracaoEolicaESolarEntity,
     * or with status {@code 400 (Bad Request)} if the onsFatorCapacidadeGeracaoEolicaESolarEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsFatorCapacidadeGeracaoEolicaESolarEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsFatorCapacidadeGeracaoEolicaESolarEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OnsFatorCapacidadeGeracaoEolicaESolarEntity> partialUpdateOnsFatorCapacidadeGeracaoEolicaESolar(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsFatorCapacidadeGeracaoEolicaESolarEntity onsFatorCapacidadeGeracaoEolicaESolarEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to partial update OnsFatorCapacidadeGeracaoEolicaESolar partially : {}, {}",
            id,
            onsFatorCapacidadeGeracaoEolicaESolarEntity
        );
        if (onsFatorCapacidadeGeracaoEolicaESolarEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsFatorCapacidadeGeracaoEolicaESolarEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsFatorCapacidadeGeracaoEolicaESolarRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsFatorCapacidadeGeracaoEolicaESolarEntity> result = onsFatorCapacidadeGeracaoEolicaESolarRepository
            .findById(onsFatorCapacidadeGeracaoEolicaESolarEntity.getId())
            .map(existingOnsFatorCapacidadeGeracaoEolicaESolar -> {
                if (onsFatorCapacidadeGeracaoEolicaESolarEntity.getIdSubsistema() != null) {
                    existingOnsFatorCapacidadeGeracaoEolicaESolar.setIdSubsistema(
                        onsFatorCapacidadeGeracaoEolicaESolarEntity.getIdSubsistema()
                    );
                }
                if (onsFatorCapacidadeGeracaoEolicaESolarEntity.getNomSubsistema() != null) {
                    existingOnsFatorCapacidadeGeracaoEolicaESolar.setNomSubsistema(
                        onsFatorCapacidadeGeracaoEolicaESolarEntity.getNomSubsistema()
                    );
                }
                if (onsFatorCapacidadeGeracaoEolicaESolarEntity.getIdEstado() != null) {
                    existingOnsFatorCapacidadeGeracaoEolicaESolar.setIdEstado(onsFatorCapacidadeGeracaoEolicaESolarEntity.getIdEstado());
                }
                if (onsFatorCapacidadeGeracaoEolicaESolarEntity.getNomEstado() != null) {
                    existingOnsFatorCapacidadeGeracaoEolicaESolar.setNomEstado(onsFatorCapacidadeGeracaoEolicaESolarEntity.getNomEstado());
                }
                if (onsFatorCapacidadeGeracaoEolicaESolarEntity.getCodPontoconexao() != null) {
                    existingOnsFatorCapacidadeGeracaoEolicaESolar.setCodPontoconexao(
                        onsFatorCapacidadeGeracaoEolicaESolarEntity.getCodPontoconexao()
                    );
                }
                if (onsFatorCapacidadeGeracaoEolicaESolarEntity.getNomPontoconexao() != null) {
                    existingOnsFatorCapacidadeGeracaoEolicaESolar.setNomPontoconexao(
                        onsFatorCapacidadeGeracaoEolicaESolarEntity.getNomPontoconexao()
                    );
                }
                if (onsFatorCapacidadeGeracaoEolicaESolarEntity.getNomLocalizacao() != null) {
                    existingOnsFatorCapacidadeGeracaoEolicaESolar.setNomLocalizacao(
                        onsFatorCapacidadeGeracaoEolicaESolarEntity.getNomLocalizacao()
                    );
                }
                if (onsFatorCapacidadeGeracaoEolicaESolarEntity.getValLatitudesecoletora() != null) {
                    existingOnsFatorCapacidadeGeracaoEolicaESolar.setValLatitudesecoletora(
                        onsFatorCapacidadeGeracaoEolicaESolarEntity.getValLatitudesecoletora()
                    );
                }
                if (onsFatorCapacidadeGeracaoEolicaESolarEntity.getValLongitudesecoletora() != null) {
                    existingOnsFatorCapacidadeGeracaoEolicaESolar.setValLongitudesecoletora(
                        onsFatorCapacidadeGeracaoEolicaESolarEntity.getValLongitudesecoletora()
                    );
                }
                if (onsFatorCapacidadeGeracaoEolicaESolarEntity.getValLatitudepontoconexao() != null) {
                    existingOnsFatorCapacidadeGeracaoEolicaESolar.setValLatitudepontoconexao(
                        onsFatorCapacidadeGeracaoEolicaESolarEntity.getValLatitudepontoconexao()
                    );
                }
                if (onsFatorCapacidadeGeracaoEolicaESolarEntity.getValLongitudepontoconexao() != null) {
                    existingOnsFatorCapacidadeGeracaoEolicaESolar.setValLongitudepontoconexao(
                        onsFatorCapacidadeGeracaoEolicaESolarEntity.getValLongitudepontoconexao()
                    );
                }
                if (onsFatorCapacidadeGeracaoEolicaESolarEntity.getNomModalidadeoperacao() != null) {
                    existingOnsFatorCapacidadeGeracaoEolicaESolar.setNomModalidadeoperacao(
                        onsFatorCapacidadeGeracaoEolicaESolarEntity.getNomModalidadeoperacao()
                    );
                }
                if (onsFatorCapacidadeGeracaoEolicaESolarEntity.getNomTipousina() != null) {
                    existingOnsFatorCapacidadeGeracaoEolicaESolar.setNomTipousina(
                        onsFatorCapacidadeGeracaoEolicaESolarEntity.getNomTipousina()
                    );
                }
                if (onsFatorCapacidadeGeracaoEolicaESolarEntity.getNomUsinaConjunto() != null) {
                    existingOnsFatorCapacidadeGeracaoEolicaESolar.setNomUsinaConjunto(
                        onsFatorCapacidadeGeracaoEolicaESolarEntity.getNomUsinaConjunto()
                    );
                }
                if (onsFatorCapacidadeGeracaoEolicaESolarEntity.getDinInstante() != null) {
                    existingOnsFatorCapacidadeGeracaoEolicaESolar.setDinInstante(
                        onsFatorCapacidadeGeracaoEolicaESolarEntity.getDinInstante()
                    );
                }
                if (onsFatorCapacidadeGeracaoEolicaESolarEntity.getIdOns() != null) {
                    existingOnsFatorCapacidadeGeracaoEolicaESolar.setIdOns(onsFatorCapacidadeGeracaoEolicaESolarEntity.getIdOns());
                }
                if (onsFatorCapacidadeGeracaoEolicaESolarEntity.getCeg() != null) {
                    existingOnsFatorCapacidadeGeracaoEolicaESolar.setCeg(onsFatorCapacidadeGeracaoEolicaESolarEntity.getCeg());
                }
                if (onsFatorCapacidadeGeracaoEolicaESolarEntity.getValGeracaoprogramada() != null) {
                    existingOnsFatorCapacidadeGeracaoEolicaESolar.setValGeracaoprogramada(
                        onsFatorCapacidadeGeracaoEolicaESolarEntity.getValGeracaoprogramada()
                    );
                }
                if (onsFatorCapacidadeGeracaoEolicaESolarEntity.getValGeracaoverificada() != null) {
                    existingOnsFatorCapacidadeGeracaoEolicaESolar.setValGeracaoverificada(
                        onsFatorCapacidadeGeracaoEolicaESolarEntity.getValGeracaoverificada()
                    );
                }
                if (onsFatorCapacidadeGeracaoEolicaESolarEntity.getValCapacidadeinstalada() != null) {
                    existingOnsFatorCapacidadeGeracaoEolicaESolar.setValCapacidadeinstalada(
                        onsFatorCapacidadeGeracaoEolicaESolarEntity.getValCapacidadeinstalada()
                    );
                }
                if (onsFatorCapacidadeGeracaoEolicaESolarEntity.getValFatorcapacidade() != null) {
                    existingOnsFatorCapacidadeGeracaoEolicaESolar.setValFatorcapacidade(
                        onsFatorCapacidadeGeracaoEolicaESolarEntity.getValFatorcapacidade()
                    );
                }

                return existingOnsFatorCapacidadeGeracaoEolicaESolar;
            })
            .map(onsFatorCapacidadeGeracaoEolicaESolarRepository::save)
            .map(savedOnsFatorCapacidadeGeracaoEolicaESolar -> {
                onsFatorCapacidadeGeracaoEolicaESolarSearchRepository.index(savedOnsFatorCapacidadeGeracaoEolicaESolar);
                return savedOnsFatorCapacidadeGeracaoEolicaESolar;
            });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ENTITY_NAME,
                onsFatorCapacidadeGeracaoEolicaESolarEntity.getId().toString()
            )
        );
    }

    /**
     * {@code GET  /ons-fator-capacidade-geracao-eolica-e-solars} : get all the onsFatorCapacidadeGeracaoEolicaESolars.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsFatorCapacidadeGeracaoEolicaESolars in body.
     */
    @GetMapping("")
    public List<OnsFatorCapacidadeGeracaoEolicaESolarEntity> getAllOnsFatorCapacidadeGeracaoEolicaESolars() {
        LOG.debug("REST request to get all OnsFatorCapacidadeGeracaoEolicaESolars");
        return onsFatorCapacidadeGeracaoEolicaESolarRepository.findAll();
    }

    /**
     * {@code GET  /ons-fator-capacidade-geracao-eolica-e-solars/:id} : get the "id" onsFatorCapacidadeGeracaoEolicaESolar.
     *
     * @param id the id of the onsFatorCapacidadeGeracaoEolicaESolarEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsFatorCapacidadeGeracaoEolicaESolarEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OnsFatorCapacidadeGeracaoEolicaESolarEntity> getOnsFatorCapacidadeGeracaoEolicaESolar(
        @PathVariable("id") Long id
    ) {
        LOG.debug("REST request to get OnsFatorCapacidadeGeracaoEolicaESolar : {}", id);
        Optional<OnsFatorCapacidadeGeracaoEolicaESolarEntity> onsFatorCapacidadeGeracaoEolicaESolarEntity =
            onsFatorCapacidadeGeracaoEolicaESolarRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsFatorCapacidadeGeracaoEolicaESolarEntity);
    }

    /**
     * {@code DELETE  /ons-fator-capacidade-geracao-eolica-e-solars/:id} : delete the "id" onsFatorCapacidadeGeracaoEolicaESolar.
     *
     * @param id the id of the onsFatorCapacidadeGeracaoEolicaESolarEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsFatorCapacidadeGeracaoEolicaESolar(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsFatorCapacidadeGeracaoEolicaESolar : {}", id);
        onsFatorCapacidadeGeracaoEolicaESolarRepository.deleteById(id);
        onsFatorCapacidadeGeracaoEolicaESolarSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-fator-capacidade-geracao-eolica-e-solars/_search?query=:query} : search for the onsFatorCapacidadeGeracaoEolicaESolar corresponding
     * to the query.
     *
     * @param query the query of the onsFatorCapacidadeGeracaoEolicaESolar search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsFatorCapacidadeGeracaoEolicaESolarEntity> searchOnsFatorCapacidadeGeracaoEolicaESolars(
        @RequestParam("query") String query
    ) {
        LOG.debug("REST request to search OnsFatorCapacidadeGeracaoEolicaESolars for query {}", query);
        try {
            return StreamSupport.stream(onsFatorCapacidadeGeracaoEolicaESolarSearchRepository.search(query).spliterator(), false).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
