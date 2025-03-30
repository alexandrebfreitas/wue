package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRepository;
import com.alexandrebfreitas.wue.repository.search.OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity}.
 */
@RestController
@RequestMapping("/api/ons-dados-restricao-operacao-constrainedoff-usinas-eolicas")
@Transactional
public class OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasResource.class);

    private static final String ENTITY_NAME = "onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRepository onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRepository;

    private final OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasSearchRepository onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasSearchRepository;

    public OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasResource(
        OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRepository onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRepository,
        OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasSearchRepository onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasSearchRepository
    ) {
        this.onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRepository =
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRepository;
        this.onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasSearchRepository =
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasSearchRepository;
    }

    /**
     * {@code POST  /ons-dados-restricao-operacao-constrainedoff-usinas-eolicas} : Create a new onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas.
     *
     * @param onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity, or with status {@code 400 (Bad Request)} if the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<
        OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity
    > createOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas(
        @RequestBody OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to save OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas : {}",
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity
        );
        if (onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getId() != null) {
            throw new BadRequestAlertException(
                "A new onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity = onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRepository.save(
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity
        );
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasSearchRepository.index(
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity
        );
        return ResponseEntity.created(
            new URI(
                "/api/ons-dados-restricao-operacao-constrainedoff-usinas-eolicas/" +
                onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getId()
            )
        )
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getId().toString()
                )
            )
            .body(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity);
    }

    /**
     * {@code PUT  /ons-dados-restricao-operacao-constrainedoff-usinas-eolicas/:id} : Updates an existing onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas.
     *
     * @param id the id of the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity to save.
     * @param onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity,
     * or with status {@code 400 (Bad Request)} if the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<
        OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity
    > updateOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to update OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas : {}, {}",
            id,
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity
        );
        if (onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity = onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRepository.save(
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity
        );
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasSearchRepository.index(
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity
        );
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getId().toString()
                )
            )
            .body(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity);
    }

    /**
     * {@code PATCH  /ons-dados-restricao-operacao-constrainedoff-usinas-eolicas/:id} : Partial updates given fields of an existing onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas, field will ignore if it is null
     *
     * @param id the id of the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity to save.
     * @param onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity,
     * or with status {@code 400 (Bad Request)} if the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<
        OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity
    > partialUpdateOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to partial update OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas partially : {}, {}",
            id,
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity
        );
        if (onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity> result =
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRepository
                .findById(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getId())
                .map(existingOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas -> {
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getIdSubsistema() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas.setIdSubsistema(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getIdSubsistema()
                        );
                    }
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getNomSubsistema() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas.setNomSubsistema(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getNomSubsistema()
                        );
                    }
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getIdEstado() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas.setIdEstado(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getIdEstado()
                        );
                    }
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getNomEstado() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas.setNomEstado(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getNomEstado()
                        );
                    }
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getNomUsina() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas.setNomUsina(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getNomUsina()
                        );
                    }
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getIdOns() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas.setIdOns(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getIdOns()
                        );
                    }
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getCeg() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas.setCeg(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getCeg()
                        );
                    }
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getDinInstante() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas.setDinInstante(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getDinInstante()
                        );
                    }
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getValGeracao() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas.setValGeracao(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getValGeracao()
                        );
                    }
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getValGeracaolimitada() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas.setValGeracaolimitada(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getValGeracaolimitada()
                        );
                    }
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getValDisponibilidade() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas.setValDisponibilidade(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getValDisponibilidade()
                        );
                    }
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getValGeracaoreferencia() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas.setValGeracaoreferencia(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getValGeracaoreferencia()
                        );
                    }
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getValGeracaoreferenciafinal() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas.setValGeracaoreferenciafinal(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getValGeracaoreferenciafinal()
                        );
                    }
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getCodRazaorestricao() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas.setCodRazaorestricao(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getCodRazaorestricao()
                        );
                    }
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getCodOrigemrestricao() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas.setCodOrigemrestricao(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getCodOrigemrestricao()
                        );
                    }

                    return existingOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas;
                })
                .map(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRepository::save)
                .map(savedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas -> {
                    onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasSearchRepository.index(
                        savedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas
                    );
                    return savedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas;
                });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ENTITY_NAME,
                onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.getId().toString()
            )
        );
    }

    /**
     * {@code GET  /ons-dados-restricao-operacao-constrainedoff-usinas-eolicas} : get all the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas in body.
     */
    @GetMapping("")
    public List<OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity> getAllOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas() {
        LOG.debug("REST request to get all OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas");
        return onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRepository.findAll();
    }

    /**
     * {@code GET  /ons-dados-restricao-operacao-constrainedoff-usinas-eolicas/:id} : get the "id" onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas.
     *
     * @param id the id of the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<
        OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity
    > getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas : {}", id);
        Optional<OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity> onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity =
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity);
    }

    /**
     * {@code DELETE  /ons-dados-restricao-operacao-constrainedoff-usinas-eolicas/:id} : delete the "id" onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas.
     *
     * @param id the id of the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas : {}", id);
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRepository.deleteById(id);
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-dados-restricao-operacao-constrainedoff-usinas-eolicas/_search?query=:query} : search for the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas corresponding
     * to the query.
     *
     * @param query the query of the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity> searchOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas(
        @RequestParam("query") String query
    ) {
        LOG.debug("REST request to search OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas for query {}", query);
        try {
            return StreamSupport.stream(
                onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasSearchRepository.search(query).spliterator(),
                false
            ).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
