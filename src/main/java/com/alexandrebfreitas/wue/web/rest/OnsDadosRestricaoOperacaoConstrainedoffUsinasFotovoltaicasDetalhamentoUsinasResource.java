package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasRepository;
import com.alexandrebfreitas.wue.repository.search.OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity}.
 */
@RestController
@RequestMapping("/api/ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas-detalhamento-usinas")
@Transactional
public class OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasResource {

    private static final Logger LOG = LoggerFactory.getLogger(
        OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasResource.class
    );

    private static final String ENTITY_NAME = "onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasRepository onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasRepository;

    private final OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasSearchRepository onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasSearchRepository;

    public OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasResource(
        OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasRepository onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasRepository,
        OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasSearchRepository onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasSearchRepository
    ) {
        this.onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasRepository =
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasRepository;
        this.onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasSearchRepository =
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasSearchRepository;
    }

    /**
     * {@code POST  /ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas-detalhamento-usinas} : Create a new onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas.
     *
     * @param onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity the onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity, or with status {@code 400 (Bad Request)} if the onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<
        OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity
    > createOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas(
        @RequestBody OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to save OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas : {}",
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity
        );
        if (onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity.getId() != null) {
            throw new BadRequestAlertException(
                "A new onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity =
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasRepository.save(
                onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity
            );
        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasSearchRepository.index(
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity
        );
        return ResponseEntity.created(
            new URI(
                "/api/ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas-detalhamento-usinas/" +
                onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity.getId()
            )
        )
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity.getId().toString()
                )
            )
            .body(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity);
    }

    /**
     * {@code PUT  /ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas-detalhamento-usinas/:id} : Updates an existing onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas.
     *
     * @param id the id of the onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity to save.
     * @param onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity the onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity,
     * or with status {@code 400 (Bad Request)} if the onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<
        OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity
    > updateOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to update OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas : {}, {}",
            id,
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity
        );
        if (onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity =
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasRepository.save(
                onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity
            );
        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasSearchRepository.index(
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity
        );
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity.getId().toString()
                )
            )
            .body(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity);
    }

    /**
     * {@code PATCH  /ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas-detalhamento-usinas/:id} : Partial updates given fields of an existing onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas, field will ignore if it is null
     *
     * @param id the id of the onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity to save.
     * @param onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity the onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity,
     * or with status {@code 400 (Bad Request)} if the onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<
        OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity
    > partialUpdateOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to partial update OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas partially : {}, {}",
            id,
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity
        );
        if (onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity> result =
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasRepository
                .findById(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity.getId())
                .map(existingOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas -> {
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity.getIdSubsistema() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas.setIdSubsistema(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity.getIdSubsistema()
                        );
                    }
                    if (
                        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity.getNomModalidadeoperacao() !=
                        null
                    ) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas.setNomModalidadeoperacao(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity.getNomModalidadeoperacao()
                        );
                    }
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity.getIdEstado() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas.setIdEstado(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity.getIdEstado()
                        );
                    }
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity.getNomConjuntousina() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas.setNomConjuntousina(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity.getNomConjuntousina()
                        );
                    }
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity.getNomUsina() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas.setNomUsina(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity.getNomUsina()
                        );
                    }
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity.getDinInstante() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas.setDinInstante(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity.getDinInstante()
                        );
                    }
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity.getIdOns() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas.setIdOns(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity.getIdOns()
                        );
                    }
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity.getCeg() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas.setCeg(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity.getCeg()
                        );
                    }
                    if (
                        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity.getValIrradianciaverificado() !=
                        null
                    ) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas.setValIrradianciaverificado(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity.getValIrradianciaverificado()
                        );
                    }
                    if (
                        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity.getFlgDadoirradianciainvalido() !=
                        null
                    ) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas.setFlgDadoirradianciainvalido(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity.getFlgDadoirradianciainvalido()
                        );
                    }
                    if (
                        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity.getValGeracaoestimada() != null
                    ) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas.setValGeracaoestimada(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity.getValGeracaoestimada()
                        );
                    }
                    if (
                        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity.getValGeracaoverificada() != null
                    ) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas.setValGeracaoverificada(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity.getValGeracaoverificada()
                        );
                    }

                    return existingOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas;
                })
                .map(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasRepository::save)
                .map(savedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas -> {
                    onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasSearchRepository.index(
                        savedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas
                    );
                    return savedOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas;
                });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ENTITY_NAME,
                onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity.getId().toString()
            )
        );
    }

    /**
     * {@code GET  /ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas-detalhamento-usinas} : get all the onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas in body.
     */
    @GetMapping("")
    public List<
        OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity
    > getAllOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas() {
        LOG.debug("REST request to get all OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas");
        return onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasRepository.findAll();
    }

    /**
     * {@code GET  /ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas-detalhamento-usinas/:id} : get the "id" onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas.
     *
     * @param id the id of the onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<
        OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity
    > getOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas : {}", id);
        Optional<
            OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity
        > onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity =
            onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity);
    }

    /**
     * {@code DELETE  /ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas-detalhamento-usinas/:id} : delete the "id" onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas.
     *
     * @param id the id of the onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas(
        @PathVariable("id") Long id
    ) {
        LOG.debug("REST request to delete OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas : {}", id);
        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasRepository.deleteById(id);
        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas-detalhamento-usinas/_search?query=:query} : search for the onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas corresponding
     * to the query.
     *
     * @param query the query of the onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<
        OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity
    > searchOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas(@RequestParam("query") String query) {
        LOG.debug(
            "REST request to search OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas for query {}",
            query
        );
        try {
            return StreamSupport.stream(
                onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasSearchRepository.search(query).spliterator(),
                false
            ).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
