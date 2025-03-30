package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRepository;
import com.alexandrebfreitas.wue.repository.search.OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity}.
 */
@RestController
@RequestMapping("/api/ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas")
@Transactional
public class OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasResource {

    private static final Logger LOG = LoggerFactory.getLogger(
        OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasResource.class
    );

    private static final String ENTITY_NAME = "onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRepository onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRepository;

    private final OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchRepository onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchRepository;

    public OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasResource(
        OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRepository onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRepository,
        OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchRepository onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchRepository
    ) {
        this.onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRepository =
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRepository;
        this.onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchRepository =
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchRepository;
    }

    /**
     * {@code POST  /ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas} : Create a new onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas.
     *
     * @param onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity, or with status {@code 400 (Bad Request)} if the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<
        OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity
    > createOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas(
        @RequestBody OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to save OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas : {}",
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity
        );
        if (onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getId() != null) {
            throw new BadRequestAlertException(
                "A new onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity =
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRepository.save(
                onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity
            );
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchRepository.index(
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity
        );
        return ResponseEntity.created(
            new URI(
                "/api/ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas/" +
                onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getId()
            )
        )
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getId().toString()
                )
            )
            .body(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity);
    }

    /**
     * {@code PUT  /ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas/:id} : Updates an existing onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas.
     *
     * @param id the id of the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity to save.
     * @param onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity,
     * or with status {@code 400 (Bad Request)} if the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<
        OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity
    > updateOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to update OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas : {}, {}",
            id,
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity
        );
        if (onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity =
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRepository.save(
                onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity
            );
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchRepository.index(
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity
        );
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getId().toString()
                )
            )
            .body(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity);
    }

    /**
     * {@code PATCH  /ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas/:id} : Partial updates given fields of an existing onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas, field will ignore if it is null
     *
     * @param id the id of the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity to save.
     * @param onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity,
     * or with status {@code 400 (Bad Request)} if the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<
        OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity
    > partialUpdateOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to partial update OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas partially : {}, {}",
            id,
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity
        );
        if (onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity> result =
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRepository
                .findById(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getId())
                .map(existingOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas -> {
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getIdSubsistema() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas.setIdSubsistema(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getIdSubsistema()
                        );
                    }
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getNomModalidadeoperacao() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas.setNomModalidadeoperacao(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getNomModalidadeoperacao()
                        );
                    }
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getIdEstado() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas.setIdEstado(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getIdEstado()
                        );
                    }
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getNomConjuntousina() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas.setNomConjuntousina(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getNomConjuntousina()
                        );
                    }
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getNomUsina() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas.setNomUsina(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getNomUsina()
                        );
                    }
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getDinInstante() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas.setDinInstante(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getDinInstante()
                        );
                    }
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getIdOns() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas.setIdOns(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getIdOns()
                        );
                    }
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getCeg() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas.setCeg(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getCeg()
                        );
                    }
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getValVentoverificado() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas.setValVentoverificado(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getValVentoverificado()
                        );
                    }
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getFlgDadoventoinvalido() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas.setFlgDadoventoinvalido(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getFlgDadoventoinvalido()
                        );
                    }
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getValGeracaoestimada() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas.setValGeracaoestimada(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getValGeracaoestimada()
                        );
                    }
                    if (onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getValGeracaoverificada() != null) {
                        existingOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas.setValGeracaoverificada(
                            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getValGeracaoverificada()
                        );
                    }

                    return existingOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas;
                })
                .map(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRepository::save)
                .map(savedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas -> {
                    onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchRepository.index(
                        savedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas
                    );
                    return savedOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas;
                });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ENTITY_NAME,
                onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.getId().toString()
            )
        );
    }

    /**
     * {@code GET  /ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas} : get all the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas in body.
     */
    @GetMapping("")
    public List<
        OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity
    > getAllOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas() {
        LOG.debug("REST request to get all OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas");
        return onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRepository.findAll();
    }

    /**
     * {@code GET  /ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas/:id} : get the "id" onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas.
     *
     * @param id the id of the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<
        OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity
    > getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas : {}", id);
        Optional<
            OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity
        > onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity =
            onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity);
    }

    /**
     * {@code DELETE  /ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas/:id} : delete the "id" onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas.
     *
     * @param id the id of the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas : {}", id);
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRepository.deleteById(id);
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas/_search?query=:query} : search for the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas corresponding
     * to the query.
     *
     * @param query the query of the onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<
        OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity
    > searchOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas(@RequestParam("query") String query) {
        LOG.debug("REST request to search OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas for query {}", query);
        try {
            return StreamSupport.stream(
                onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasSearchRepository.search(query).spliterator(),
                false
            ).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
