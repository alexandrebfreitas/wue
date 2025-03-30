package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresRepository;
import com.alexandrebfreitas.wue.repository.search.OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity}.
 */
@RestController
@RequestMapping("/api/ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores")
@Transactional
public class OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresResource {

    private static final Logger LOG = LoggerFactory.getLogger(
        OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresResource.class
    );

    private static final String ENTITY_NAME = "onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresRepository onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresRepository;

    private final OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresSearchRepository onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresSearchRepository;

    public OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresResource(
        OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresRepository onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresRepository,
        OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresSearchRepository onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresSearchRepository
    ) {
        this.onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresRepository =
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresRepository;
        this.onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresSearchRepository =
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresSearchRepository;
    }

    /**
     * {@code POST  /ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores} : Create a new onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores.
     *
     * @param onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity the onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity, or with status {@code 400 (Bad Request)} if the onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<
        OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
    > createOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores(
        @RequestBody OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to save OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores : {}",
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
        );
        if (onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.getId() != null) {
            throw new BadRequestAlertException(
                "A new onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity =
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresRepository.save(
                onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
            );
        onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresSearchRepository.index(
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
        );
        return ResponseEntity.created(
            new URI(
                "/api/ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores/" +
                onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.getId()
            )
        )
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.getId().toString()
                )
            )
            .body(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity);
    }

    /**
     * {@code PUT  /ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores/:id} : Updates an existing onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores.
     *
     * @param id the id of the onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity to save.
     * @param onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity the onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity,
     * or with status {@code 400 (Bad Request)} if the onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<
        OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
    > updateOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to update OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores : {}, {}",
            id,
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
        );
        if (onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity =
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresRepository.save(
                onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
            );
        onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresSearchRepository.index(
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
        );
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.getId().toString()
                )
            )
            .body(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity);
    }

    /**
     * {@code PATCH  /ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores/:id} : Partial updates given fields of an existing onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores, field will ignore if it is null
     *
     * @param id the id of the onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity to save.
     * @param onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity the onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity,
     * or with status {@code 400 (Bad Request)} if the onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<
        OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
    > partialUpdateOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to partial update OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores partially : {}, {}",
            id,
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
        );
        if (onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity> result =
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresRepository
                .findById(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.getId())
                .map(existingOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores -> {
                    if (
                        onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.getCodTipoagregacao() !=
                        null
                    ) {
                        existingOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores.setCodTipoagregacao(
                            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.getCodTipoagregacao()
                        );
                    }
                    if (
                        onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.getIdPeriodicidade() !=
                        null
                    ) {
                        existingOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores.setIdPeriodicidade(
                            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.getIdPeriodicidade()
                        );
                    }
                    if (
                        onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.getNomAgregacao() != null
                    ) {
                        existingOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores.setNomAgregacao(
                            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.getNomAgregacao()
                        );
                    }
                    if (
                        onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.getDinReferencia() != null
                    ) {
                        existingOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores.setDinReferencia(
                            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.getDinReferencia()
                        );
                    }
                    if (
                        onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.getNumTransformadoresoperacao() !=
                        null
                    ) {
                        existingOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores.setNumTransformadoresoperacao(
                            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.getNumTransformadoresoperacao()
                        );
                    }
                    if (
                        onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.getNumTransformadoresviolados() !=
                        null
                    ) {
                        existingOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores.setNumTransformadoresviolados(
                            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.getNumTransformadoresviolados()
                        );
                    }
                    if (onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.getValCcat() != null) {
                        existingOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores.setValCcat(
                            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.getValCcat()
                        );
                    }

                    return existingOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores;
                })
                .map(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresRepository::save)
                .map(savedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores -> {
                    onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresSearchRepository.index(
                        savedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores
                    );
                    return savedOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores;
                });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ENTITY_NAME,
                onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.getId().toString()
            )
        );
    }

    /**
     * {@code GET  /ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores} : get all the onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores in body.
     */
    @GetMapping("")
    public List<
        OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
    > getAllOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores() {
        LOG.debug("REST request to get all OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores");
        return onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresRepository.findAll();
    }

    /**
     * {@code GET  /ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores/:id} : get the "id" onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores.
     *
     * @param id the id of the onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<
        OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
    > getOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores : {}", id);
        Optional<
            OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
        > onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity =
            onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity);
    }

    /**
     * {@code DELETE  /ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores/:id} : delete the "id" onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores.
     *
     * @param id the id of the onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores(
        @PathVariable("id") Long id
    ) {
        LOG.debug("REST request to delete OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores : {}", id);
        onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresRepository.deleteById(id);
        onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores/_search?query=:query} : search for the onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores corresponding
     * to the query.
     *
     * @param query the query of the onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<
        OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity
    > searchOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores(@RequestParam("query") String query) {
        LOG.debug(
            "REST request to search OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores for query {}",
            query
        );
        try {
            return StreamSupport.stream(
                onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresSearchRepository
                    .search(query)
                    .spliterator(),
                false
            ).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
