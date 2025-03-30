package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRepository;
import com.alexandrebfreitas.wue.repository.search.OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity}.
 */
@RestController
@RequestMapping("/api/ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas")
@Transactional
public class OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasResource.class);

    private static final String ENTITY_NAME = "onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRepository onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRepository;

    private final OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasSearchRepository onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasSearchRepository;

    public OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasResource(
        OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRepository onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRepository,
        OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasSearchRepository onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasSearchRepository
    ) {
        this.onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRepository =
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRepository;
        this.onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasSearchRepository =
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasSearchRepository;
    }

    /**
     * {@code POST  /ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas} : Create a new onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas.
     *
     * @param onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity the onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity, or with status {@code 400 (Bad Request)} if the onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<
        OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity
    > createOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas(
        @RequestBody OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to save OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas : {}",
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity
        );
        if (onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getId() != null) {
            throw new BadRequestAlertException(
                "A new onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity =
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRepository.save(
                onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity
            );
        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasSearchRepository.index(
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity
        );
        return ResponseEntity.created(
            new URI(
                "/api/ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas/" +
                onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getId()
            )
        )
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getId().toString()
                )
            )
            .body(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity);
    }

    /**
     * {@code PUT  /ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas/:id} : Updates an existing onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas.
     *
     * @param id the id of the onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity to save.
     * @param onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity the onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity,
     * or with status {@code 400 (Bad Request)} if the onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<
        OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity
    > updateOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to update OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas : {}, {}",
            id,
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity
        );
        if (onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity =
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRepository.save(
                onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity
            );
        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasSearchRepository.index(
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity
        );
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getId().toString()
                )
            )
            .body(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity);
    }

    /**
     * {@code PATCH  /ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas/:id} : Partial updates given fields of an existing onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas, field will ignore if it is null
     *
     * @param id the id of the onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity to save.
     * @param onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity the onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity,
     * or with status {@code 400 (Bad Request)} if the onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<
        OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity
    > partialUpdateOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to partial update OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas partially : {}, {}",
            id,
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity
        );
        if (onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity> result =
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRepository
                .findById(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getId())
                .map(existingOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas -> {
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getIdSubsistema() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas.setIdSubsistema(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getIdSubsistema()
                        );
                    }
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getNomSubsistema() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas.setNomSubsistema(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getNomSubsistema()
                        );
                    }
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getIdEstado() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas.setIdEstado(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getIdEstado()
                        );
                    }
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getNomEstado() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas.setNomEstado(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getNomEstado()
                        );
                    }
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getNomUsina() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas.setNomUsina(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getNomUsina()
                        );
                    }
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getIdOns() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas.setIdOns(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getIdOns()
                        );
                    }
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getCeg() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas.setCeg(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getCeg()
                        );
                    }
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getDinInstante() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas.setDinInstante(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getDinInstante()
                        );
                    }
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getValGeracao() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas.setValGeracao(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getValGeracao()
                        );
                    }
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getValGeracaolimitada() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas.setValGeracaolimitada(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getValGeracaolimitada()
                        );
                    }
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getValDisponibilidade() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas.setValDisponibilidade(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getValDisponibilidade()
                        );
                    }
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getValGeracaoreferencia() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas.setValGeracaoreferencia(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getValGeracaoreferencia()
                        );
                    }
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getValGeracaoreferenciafinal() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas.setValGeracaoreferenciafinal(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getValGeracaoreferenciafinal()
                        );
                    }
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getCodRazaorestricao() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas.setCodRazaorestricao(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getCodRazaorestricao()
                        );
                    }
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getCodOrigemrestricao() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas.setCodOrigemrestricao(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getCodOrigemrestricao()
                        );
                    }

                    return existingOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas;
                })
                .map(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRepository::save)
                .map(savedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas -> {
                    onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasSearchRepository.index(
                        savedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas
                    );
                    return savedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas;
                });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ENTITY_NAME,
                onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.getId().toString()
            )
        );
    }

    /**
     * {@code GET  /ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas} : get all the onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas in body.
     */
    @GetMapping("")
    public List<
        OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity
    > getAllOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas() {
        LOG.debug("REST request to get all OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas");
        return onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRepository.findAll();
    }

    /**
     * {@code GET  /ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas/:id} : get the "id" onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas.
     *
     * @param id the id of the onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<
        OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity
    > getOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas : {}", id);
        Optional<
            OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity
        > onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity =
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity);
    }

    /**
     * {@code DELETE  /ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas/:id} : delete the "id" onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas.
     *
     * @param id the id of the onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas : {}", id);
        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRepository.deleteById(id);
        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas/_search?query=:query} : search for the onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas corresponding
     * to the query.
     *
     * @param query the query of the onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<
        OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity
    > searchOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas(@RequestParam("query") String query) {
        LOG.debug("REST request to search OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas for query {}", query);
        try {
            return StreamSupport.stream(
                onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasSearchRepository.search(query).spliterator(),
                false
            ).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
