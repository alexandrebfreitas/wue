package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosRepository;
import com.alexandrebfreitas.wue.repository.search.OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity}.
 */
@RestController
@RequestMapping("/api/ons-dados-indicadores-confiabilidade-rede-basica-atls-atendimento-aos-limites-sistemicos")
@Transactional
public class OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosResource {

    private static final Logger LOG = LoggerFactory.getLogger(
        OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosResource.class
    );

    private static final String ENTITY_NAME = "onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosRepository onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosRepository;

    private final OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosSearchRepository onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosSearchRepository;

    public OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosResource(
        OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosRepository onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosRepository,
        OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosSearchRepository onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosSearchRepository
    ) {
        this.onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosRepository =
            onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosRepository;
        this.onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosSearchRepository =
            onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosSearchRepository;
    }

    /**
     * {@code POST  /ons-dados-indicadores-confiabilidade-rede-basica-atls-atendimento-aos-limites-sistemicos} : Create a new onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos.
     *
     * @param onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity the onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity, or with status {@code 400 (Bad Request)} if the onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<
        OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity
    > createOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos(
        @RequestBody OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to save OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos : {}",
            onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity
        );
        if (onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity.getId() != null) {
            throw new BadRequestAlertException(
                "A new onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity =
            onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosRepository.save(
                onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity
            );
        onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosSearchRepository.index(
            onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity
        );
        return ResponseEntity.created(
            new URI(
                "/api/ons-dados-indicadores-confiabilidade-rede-basica-atls-atendimento-aos-limites-sistemicos/" +
                onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity.getId()
            )
        )
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity.getId().toString()
                )
            )
            .body(onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity);
    }

    /**
     * {@code PUT  /ons-dados-indicadores-confiabilidade-rede-basica-atls-atendimento-aos-limites-sistemicos/:id} : Updates an existing onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos.
     *
     * @param id the id of the onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity to save.
     * @param onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity the onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity,
     * or with status {@code 400 (Bad Request)} if the onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<
        OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity
    > updateOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to update OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos : {}, {}",
            id,
            onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity
        );
        if (onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity =
            onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosRepository.save(
                onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity
            );
        onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosSearchRepository.index(
            onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity
        );
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity.getId().toString()
                )
            )
            .body(onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity);
    }

    /**
     * {@code PATCH  /ons-dados-indicadores-confiabilidade-rede-basica-atls-atendimento-aos-limites-sistemicos/:id} : Partial updates given fields of an existing onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos, field will ignore if it is null
     *
     * @param id the id of the onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity to save.
     * @param onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity the onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity,
     * or with status {@code 400 (Bad Request)} if the onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<
        OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity
    > partialUpdateOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to partial update OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos partially : {}, {}",
            id,
            onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity
        );
        if (onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity> result =
            onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosRepository
                .findById(onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity.getId())
                .map(existingOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos -> {
                    if (onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity.getNomFluxo() != null) {
                        existingOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos.setNomFluxo(
                            onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity.getNomFluxo()
                        );
                    }
                    if (onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity.getIdPeriodicidade() != null) {
                        existingOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos.setIdPeriodicidade(
                            onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity.getIdPeriodicidade()
                        );
                    }
                    if (onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity.getDinReferencia() != null) {
                        existingOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos.setDinReferencia(
                            onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity.getDinReferencia()
                        );
                    }
                    if (onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity.getValAtls() != null) {
                        existingOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos.setValAtls(
                            onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity.getValAtls()
                        );
                    }
                    if (
                        onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity.getNumHorasviolacao() != null
                    ) {
                        existingOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos.setNumHorasviolacao(
                            onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity.getNumHorasviolacao()
                        );
                    }

                    return existingOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos;
                })
                .map(onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosRepository::save)
                .map(savedOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos -> {
                    onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosSearchRepository.index(
                        savedOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos
                    );
                    return savedOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos;
                });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ENTITY_NAME,
                onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity.getId().toString()
            )
        );
    }

    /**
     * {@code GET  /ons-dados-indicadores-confiabilidade-rede-basica-atls-atendimento-aos-limites-sistemicos} : get all the onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos in body.
     */
    @GetMapping("")
    public List<
        OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity
    > getAllOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos() {
        LOG.debug("REST request to get all OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos");
        return onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosRepository.findAll();
    }

    /**
     * {@code GET  /ons-dados-indicadores-confiabilidade-rede-basica-atls-atendimento-aos-limites-sistemicos/:id} : get the "id" onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos.
     *
     * @param id the id of the onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<
        OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity
    > getOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos : {}", id);
        Optional<
            OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity
        > onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity =
            onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity);
    }

    /**
     * {@code DELETE  /ons-dados-indicadores-confiabilidade-rede-basica-atls-atendimento-aos-limites-sistemicos/:id} : delete the "id" onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos.
     *
     * @param id the id of the onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos(
        @PathVariable("id") Long id
    ) {
        LOG.debug("REST request to delete OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos : {}", id);
        onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosRepository.deleteById(id);
        onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-dados-indicadores-confiabilidade-rede-basica-atls-atendimento-aos-limites-sistemicos/_search?query=:query} : search for the onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos corresponding
     * to the query.
     *
     * @param query the query of the onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<
        OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity
    > searchOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos(@RequestParam("query") String query) {
        LOG.debug(
            "REST request to search OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos for query {}",
            query
        );
        try {
            return StreamSupport.stream(
                onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosSearchRepository.search(query).spliterator(),
                false
            ).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
