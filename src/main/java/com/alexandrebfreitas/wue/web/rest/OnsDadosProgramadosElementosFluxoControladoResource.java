package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsDadosProgramadosElementosFluxoControladoEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosProgramadosElementosFluxoControladoRepository;
import com.alexandrebfreitas.wue.repository.search.OnsDadosProgramadosElementosFluxoControladoSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsDadosProgramadosElementosFluxoControladoEntity}.
 */
@RestController
@RequestMapping("/api/ons-dados-programados-elementos-fluxo-controlados")
@Transactional
public class OnsDadosProgramadosElementosFluxoControladoResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsDadosProgramadosElementosFluxoControladoResource.class);

    private static final String ENTITY_NAME = "onsDadosProgramadosElementosFluxoControlado";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsDadosProgramadosElementosFluxoControladoRepository onsDadosProgramadosElementosFluxoControladoRepository;

    private final OnsDadosProgramadosElementosFluxoControladoSearchRepository onsDadosProgramadosElementosFluxoControladoSearchRepository;

    public OnsDadosProgramadosElementosFluxoControladoResource(
        OnsDadosProgramadosElementosFluxoControladoRepository onsDadosProgramadosElementosFluxoControladoRepository,
        OnsDadosProgramadosElementosFluxoControladoSearchRepository onsDadosProgramadosElementosFluxoControladoSearchRepository
    ) {
        this.onsDadosProgramadosElementosFluxoControladoRepository = onsDadosProgramadosElementosFluxoControladoRepository;
        this.onsDadosProgramadosElementosFluxoControladoSearchRepository = onsDadosProgramadosElementosFluxoControladoSearchRepository;
    }

    /**
     * {@code POST  /ons-dados-programados-elementos-fluxo-controlados} : Create a new onsDadosProgramadosElementosFluxoControlado.
     *
     * @param onsDadosProgramadosElementosFluxoControladoEntity the onsDadosProgramadosElementosFluxoControladoEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsDadosProgramadosElementosFluxoControladoEntity, or with status {@code 400 (Bad Request)} if the onsDadosProgramadosElementosFluxoControlado has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OnsDadosProgramadosElementosFluxoControladoEntity> createOnsDadosProgramadosElementosFluxoControlado(
        @RequestBody OnsDadosProgramadosElementosFluxoControladoEntity onsDadosProgramadosElementosFluxoControladoEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to save OnsDadosProgramadosElementosFluxoControlado : {}",
            onsDadosProgramadosElementosFluxoControladoEntity
        );
        if (onsDadosProgramadosElementosFluxoControladoEntity.getId() != null) {
            throw new BadRequestAlertException(
                "A new onsDadosProgramadosElementosFluxoControlado cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        onsDadosProgramadosElementosFluxoControladoEntity = onsDadosProgramadosElementosFluxoControladoRepository.save(
            onsDadosProgramadosElementosFluxoControladoEntity
        );
        onsDadosProgramadosElementosFluxoControladoSearchRepository.index(onsDadosProgramadosElementosFluxoControladoEntity);
        return ResponseEntity.created(
            new URI("/api/ons-dados-programados-elementos-fluxo-controlados/" + onsDadosProgramadosElementosFluxoControladoEntity.getId())
        )
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsDadosProgramadosElementosFluxoControladoEntity.getId().toString()
                )
            )
            .body(onsDadosProgramadosElementosFluxoControladoEntity);
    }

    /**
     * {@code PUT  /ons-dados-programados-elementos-fluxo-controlados/:id} : Updates an existing onsDadosProgramadosElementosFluxoControlado.
     *
     * @param id the id of the onsDadosProgramadosElementosFluxoControladoEntity to save.
     * @param onsDadosProgramadosElementosFluxoControladoEntity the onsDadosProgramadosElementosFluxoControladoEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsDadosProgramadosElementosFluxoControladoEntity,
     * or with status {@code 400 (Bad Request)} if the onsDadosProgramadosElementosFluxoControladoEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsDadosProgramadosElementosFluxoControladoEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OnsDadosProgramadosElementosFluxoControladoEntity> updateOnsDadosProgramadosElementosFluxoControlado(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsDadosProgramadosElementosFluxoControladoEntity onsDadosProgramadosElementosFluxoControladoEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to update OnsDadosProgramadosElementosFluxoControlado : {}, {}",
            id,
            onsDadosProgramadosElementosFluxoControladoEntity
        );
        if (onsDadosProgramadosElementosFluxoControladoEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsDadosProgramadosElementosFluxoControladoEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsDadosProgramadosElementosFluxoControladoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsDadosProgramadosElementosFluxoControladoEntity = onsDadosProgramadosElementosFluxoControladoRepository.save(
            onsDadosProgramadosElementosFluxoControladoEntity
        );
        onsDadosProgramadosElementosFluxoControladoSearchRepository.index(onsDadosProgramadosElementosFluxoControladoEntity);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsDadosProgramadosElementosFluxoControladoEntity.getId().toString()
                )
            )
            .body(onsDadosProgramadosElementosFluxoControladoEntity);
    }

    /**
     * {@code PATCH  /ons-dados-programados-elementos-fluxo-controlados/:id} : Partial updates given fields of an existing onsDadosProgramadosElementosFluxoControlado, field will ignore if it is null
     *
     * @param id the id of the onsDadosProgramadosElementosFluxoControladoEntity to save.
     * @param onsDadosProgramadosElementosFluxoControladoEntity the onsDadosProgramadosElementosFluxoControladoEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsDadosProgramadosElementosFluxoControladoEntity,
     * or with status {@code 400 (Bad Request)} if the onsDadosProgramadosElementosFluxoControladoEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsDadosProgramadosElementosFluxoControladoEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsDadosProgramadosElementosFluxoControladoEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OnsDadosProgramadosElementosFluxoControladoEntity> partialUpdateOnsDadosProgramadosElementosFluxoControlado(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsDadosProgramadosElementosFluxoControladoEntity onsDadosProgramadosElementosFluxoControladoEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to partial update OnsDadosProgramadosElementosFluxoControlado partially : {}, {}",
            id,
            onsDadosProgramadosElementosFluxoControladoEntity
        );
        if (onsDadosProgramadosElementosFluxoControladoEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsDadosProgramadosElementosFluxoControladoEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsDadosProgramadosElementosFluxoControladoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsDadosProgramadosElementosFluxoControladoEntity> result = onsDadosProgramadosElementosFluxoControladoRepository
            .findById(onsDadosProgramadosElementosFluxoControladoEntity.getId())
            .map(existingOnsDadosProgramadosElementosFluxoControlado -> {
                if (onsDadosProgramadosElementosFluxoControladoEntity.getDinProgramacaodia() != null) {
                    existingOnsDadosProgramadosElementosFluxoControlado.setDinProgramacaodia(
                        onsDadosProgramadosElementosFluxoControladoEntity.getDinProgramacaodia()
                    );
                }
                if (onsDadosProgramadosElementosFluxoControladoEntity.getNumPatamar() != null) {
                    existingOnsDadosProgramadosElementosFluxoControlado.setNumPatamar(
                        onsDadosProgramadosElementosFluxoControladoEntity.getNumPatamar()
                    );
                }
                if (onsDadosProgramadosElementosFluxoControladoEntity.getNomElementofluxocontrolado() != null) {
                    existingOnsDadosProgramadosElementosFluxoControlado.setNomElementofluxocontrolado(
                        onsDadosProgramadosElementosFluxoControladoEntity.getNomElementofluxocontrolado()
                    );
                }
                if (onsDadosProgramadosElementosFluxoControladoEntity.getDscElementofluxocontrolado() != null) {
                    existingOnsDadosProgramadosElementosFluxoControlado.setDscElementofluxocontrolado(
                        onsDadosProgramadosElementosFluxoControladoEntity.getDscElementofluxocontrolado()
                    );
                }
                if (onsDadosProgramadosElementosFluxoControladoEntity.getTipTerminal() != null) {
                    existingOnsDadosProgramadosElementosFluxoControlado.setTipTerminal(
                        onsDadosProgramadosElementosFluxoControladoEntity.getTipTerminal()
                    );
                }
                if (onsDadosProgramadosElementosFluxoControladoEntity.getCodSubmercado() != null) {
                    existingOnsDadosProgramadosElementosFluxoControlado.setCodSubmercado(
                        onsDadosProgramadosElementosFluxoControladoEntity.getCodSubmercado()
                    );
                }
                if (onsDadosProgramadosElementosFluxoControladoEntity.getValCarga() != null) {
                    existingOnsDadosProgramadosElementosFluxoControlado.setValCarga(
                        onsDadosProgramadosElementosFluxoControladoEntity.getValCarga()
                    );
                }

                return existingOnsDadosProgramadosElementosFluxoControlado;
            })
            .map(onsDadosProgramadosElementosFluxoControladoRepository::save)
            .map(savedOnsDadosProgramadosElementosFluxoControlado -> {
                onsDadosProgramadosElementosFluxoControladoSearchRepository.index(savedOnsDadosProgramadosElementosFluxoControlado);
                return savedOnsDadosProgramadosElementosFluxoControlado;
            });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ENTITY_NAME,
                onsDadosProgramadosElementosFluxoControladoEntity.getId().toString()
            )
        );
    }

    /**
     * {@code GET  /ons-dados-programados-elementos-fluxo-controlados} : get all the onsDadosProgramadosElementosFluxoControlados.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsDadosProgramadosElementosFluxoControlados in body.
     */
    @GetMapping("")
    public List<OnsDadosProgramadosElementosFluxoControladoEntity> getAllOnsDadosProgramadosElementosFluxoControlados() {
        LOG.debug("REST request to get all OnsDadosProgramadosElementosFluxoControlados");
        return onsDadosProgramadosElementosFluxoControladoRepository.findAll();
    }

    /**
     * {@code GET  /ons-dados-programados-elementos-fluxo-controlados/:id} : get the "id" onsDadosProgramadosElementosFluxoControlado.
     *
     * @param id the id of the onsDadosProgramadosElementosFluxoControladoEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsDadosProgramadosElementosFluxoControladoEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OnsDadosProgramadosElementosFluxoControladoEntity> getOnsDadosProgramadosElementosFluxoControlado(
        @PathVariable("id") Long id
    ) {
        LOG.debug("REST request to get OnsDadosProgramadosElementosFluxoControlado : {}", id);
        Optional<OnsDadosProgramadosElementosFluxoControladoEntity> onsDadosProgramadosElementosFluxoControladoEntity =
            onsDadosProgramadosElementosFluxoControladoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsDadosProgramadosElementosFluxoControladoEntity);
    }

    /**
     * {@code DELETE  /ons-dados-programados-elementos-fluxo-controlados/:id} : delete the "id" onsDadosProgramadosElementosFluxoControlado.
     *
     * @param id the id of the onsDadosProgramadosElementosFluxoControladoEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsDadosProgramadosElementosFluxoControlado(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsDadosProgramadosElementosFluxoControlado : {}", id);
        onsDadosProgramadosElementosFluxoControladoRepository.deleteById(id);
        onsDadosProgramadosElementosFluxoControladoSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-dados-programados-elementos-fluxo-controlados/_search?query=:query} : search for the onsDadosProgramadosElementosFluxoControlado corresponding
     * to the query.
     *
     * @param query the query of the onsDadosProgramadosElementosFluxoControlado search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsDadosProgramadosElementosFluxoControladoEntity> searchOnsDadosProgramadosElementosFluxoControlados(
        @RequestParam("query") String query
    ) {
        LOG.debug("REST request to search OnsDadosProgramadosElementosFluxoControlados for query {}", query);
        try {
            return StreamSupport.stream(
                onsDadosProgramadosElementosFluxoControladoSearchRepository.search(query).spliterator(),
                false
            ).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
