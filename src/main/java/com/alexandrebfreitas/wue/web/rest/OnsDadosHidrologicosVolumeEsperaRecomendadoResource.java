package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsDadosHidrologicosVolumeEsperaRecomendadoEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosHidrologicosVolumeEsperaRecomendadoRepository;
import com.alexandrebfreitas.wue.repository.search.OnsDadosHidrologicosVolumeEsperaRecomendadoSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsDadosHidrologicosVolumeEsperaRecomendadoEntity}.
 */
@RestController
@RequestMapping("/api/ons-dados-hidrologicos-volume-espera-recomendados")
@Transactional
public class OnsDadosHidrologicosVolumeEsperaRecomendadoResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsDadosHidrologicosVolumeEsperaRecomendadoResource.class);

    private static final String ENTITY_NAME = "onsDadosHidrologicosVolumeEsperaRecomendado";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsDadosHidrologicosVolumeEsperaRecomendadoRepository onsDadosHidrologicosVolumeEsperaRecomendadoRepository;

    private final OnsDadosHidrologicosVolumeEsperaRecomendadoSearchRepository onsDadosHidrologicosVolumeEsperaRecomendadoSearchRepository;

    public OnsDadosHidrologicosVolumeEsperaRecomendadoResource(
        OnsDadosHidrologicosVolumeEsperaRecomendadoRepository onsDadosHidrologicosVolumeEsperaRecomendadoRepository,
        OnsDadosHidrologicosVolumeEsperaRecomendadoSearchRepository onsDadosHidrologicosVolumeEsperaRecomendadoSearchRepository
    ) {
        this.onsDadosHidrologicosVolumeEsperaRecomendadoRepository = onsDadosHidrologicosVolumeEsperaRecomendadoRepository;
        this.onsDadosHidrologicosVolumeEsperaRecomendadoSearchRepository = onsDadosHidrologicosVolumeEsperaRecomendadoSearchRepository;
    }

    /**
     * {@code POST  /ons-dados-hidrologicos-volume-espera-recomendados} : Create a new onsDadosHidrologicosVolumeEsperaRecomendado.
     *
     * @param onsDadosHidrologicosVolumeEsperaRecomendadoEntity the onsDadosHidrologicosVolumeEsperaRecomendadoEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsDadosHidrologicosVolumeEsperaRecomendadoEntity, or with status {@code 400 (Bad Request)} if the onsDadosHidrologicosVolumeEsperaRecomendado has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OnsDadosHidrologicosVolumeEsperaRecomendadoEntity> createOnsDadosHidrologicosVolumeEsperaRecomendado(
        @RequestBody OnsDadosHidrologicosVolumeEsperaRecomendadoEntity onsDadosHidrologicosVolumeEsperaRecomendadoEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to save OnsDadosHidrologicosVolumeEsperaRecomendado : {}",
            onsDadosHidrologicosVolumeEsperaRecomendadoEntity
        );
        if (onsDadosHidrologicosVolumeEsperaRecomendadoEntity.getId() != null) {
            throw new BadRequestAlertException(
                "A new onsDadosHidrologicosVolumeEsperaRecomendado cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        onsDadosHidrologicosVolumeEsperaRecomendadoEntity = onsDadosHidrologicosVolumeEsperaRecomendadoRepository.save(
            onsDadosHidrologicosVolumeEsperaRecomendadoEntity
        );
        onsDadosHidrologicosVolumeEsperaRecomendadoSearchRepository.index(onsDadosHidrologicosVolumeEsperaRecomendadoEntity);
        return ResponseEntity.created(
            new URI("/api/ons-dados-hidrologicos-volume-espera-recomendados/" + onsDadosHidrologicosVolumeEsperaRecomendadoEntity.getId())
        )
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsDadosHidrologicosVolumeEsperaRecomendadoEntity.getId().toString()
                )
            )
            .body(onsDadosHidrologicosVolumeEsperaRecomendadoEntity);
    }

    /**
     * {@code PUT  /ons-dados-hidrologicos-volume-espera-recomendados/:id} : Updates an existing onsDadosHidrologicosVolumeEsperaRecomendado.
     *
     * @param id the id of the onsDadosHidrologicosVolumeEsperaRecomendadoEntity to save.
     * @param onsDadosHidrologicosVolumeEsperaRecomendadoEntity the onsDadosHidrologicosVolumeEsperaRecomendadoEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsDadosHidrologicosVolumeEsperaRecomendadoEntity,
     * or with status {@code 400 (Bad Request)} if the onsDadosHidrologicosVolumeEsperaRecomendadoEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsDadosHidrologicosVolumeEsperaRecomendadoEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OnsDadosHidrologicosVolumeEsperaRecomendadoEntity> updateOnsDadosHidrologicosVolumeEsperaRecomendado(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsDadosHidrologicosVolumeEsperaRecomendadoEntity onsDadosHidrologicosVolumeEsperaRecomendadoEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to update OnsDadosHidrologicosVolumeEsperaRecomendado : {}, {}",
            id,
            onsDadosHidrologicosVolumeEsperaRecomendadoEntity
        );
        if (onsDadosHidrologicosVolumeEsperaRecomendadoEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsDadosHidrologicosVolumeEsperaRecomendadoEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsDadosHidrologicosVolumeEsperaRecomendadoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsDadosHidrologicosVolumeEsperaRecomendadoEntity = onsDadosHidrologicosVolumeEsperaRecomendadoRepository.save(
            onsDadosHidrologicosVolumeEsperaRecomendadoEntity
        );
        onsDadosHidrologicosVolumeEsperaRecomendadoSearchRepository.index(onsDadosHidrologicosVolumeEsperaRecomendadoEntity);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsDadosHidrologicosVolumeEsperaRecomendadoEntity.getId().toString()
                )
            )
            .body(onsDadosHidrologicosVolumeEsperaRecomendadoEntity);
    }

    /**
     * {@code PATCH  /ons-dados-hidrologicos-volume-espera-recomendados/:id} : Partial updates given fields of an existing onsDadosHidrologicosVolumeEsperaRecomendado, field will ignore if it is null
     *
     * @param id the id of the onsDadosHidrologicosVolumeEsperaRecomendadoEntity to save.
     * @param onsDadosHidrologicosVolumeEsperaRecomendadoEntity the onsDadosHidrologicosVolumeEsperaRecomendadoEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsDadosHidrologicosVolumeEsperaRecomendadoEntity,
     * or with status {@code 400 (Bad Request)} if the onsDadosHidrologicosVolumeEsperaRecomendadoEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsDadosHidrologicosVolumeEsperaRecomendadoEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsDadosHidrologicosVolumeEsperaRecomendadoEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OnsDadosHidrologicosVolumeEsperaRecomendadoEntity> partialUpdateOnsDadosHidrologicosVolumeEsperaRecomendado(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsDadosHidrologicosVolumeEsperaRecomendadoEntity onsDadosHidrologicosVolumeEsperaRecomendadoEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to partial update OnsDadosHidrologicosVolumeEsperaRecomendado partially : {}, {}",
            id,
            onsDadosHidrologicosVolumeEsperaRecomendadoEntity
        );
        if (onsDadosHidrologicosVolumeEsperaRecomendadoEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsDadosHidrologicosVolumeEsperaRecomendadoEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsDadosHidrologicosVolumeEsperaRecomendadoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsDadosHidrologicosVolumeEsperaRecomendadoEntity> result = onsDadosHidrologicosVolumeEsperaRecomendadoRepository
            .findById(onsDadosHidrologicosVolumeEsperaRecomendadoEntity.getId())
            .map(existingOnsDadosHidrologicosVolumeEsperaRecomendado -> {
                if (onsDadosHidrologicosVolumeEsperaRecomendadoEntity.getDinInstante() != null) {
                    existingOnsDadosHidrologicosVolumeEsperaRecomendado.setDinInstante(
                        onsDadosHidrologicosVolumeEsperaRecomendadoEntity.getDinInstante()
                    );
                }
                if (onsDadosHidrologicosVolumeEsperaRecomendadoEntity.getValVolumeespera() != null) {
                    existingOnsDadosHidrologicosVolumeEsperaRecomendado.setValVolumeespera(
                        onsDadosHidrologicosVolumeEsperaRecomendadoEntity.getValVolumeespera()
                    );
                }

                return existingOnsDadosHidrologicosVolumeEsperaRecomendado;
            })
            .map(onsDadosHidrologicosVolumeEsperaRecomendadoRepository::save)
            .map(savedOnsDadosHidrologicosVolumeEsperaRecomendado -> {
                onsDadosHidrologicosVolumeEsperaRecomendadoSearchRepository.index(savedOnsDadosHidrologicosVolumeEsperaRecomendado);
                return savedOnsDadosHidrologicosVolumeEsperaRecomendado;
            });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ENTITY_NAME,
                onsDadosHidrologicosVolumeEsperaRecomendadoEntity.getId().toString()
            )
        );
    }

    /**
     * {@code GET  /ons-dados-hidrologicos-volume-espera-recomendados} : get all the onsDadosHidrologicosVolumeEsperaRecomendados.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsDadosHidrologicosVolumeEsperaRecomendados in body.
     */
    @GetMapping("")
    public List<OnsDadosHidrologicosVolumeEsperaRecomendadoEntity> getAllOnsDadosHidrologicosVolumeEsperaRecomendados() {
        LOG.debug("REST request to get all OnsDadosHidrologicosVolumeEsperaRecomendados");
        return onsDadosHidrologicosVolumeEsperaRecomendadoRepository.findAll();
    }

    /**
     * {@code GET  /ons-dados-hidrologicos-volume-espera-recomendados/:id} : get the "id" onsDadosHidrologicosVolumeEsperaRecomendado.
     *
     * @param id the id of the onsDadosHidrologicosVolumeEsperaRecomendadoEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsDadosHidrologicosVolumeEsperaRecomendadoEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OnsDadosHidrologicosVolumeEsperaRecomendadoEntity> getOnsDadosHidrologicosVolumeEsperaRecomendado(
        @PathVariable("id") Long id
    ) {
        LOG.debug("REST request to get OnsDadosHidrologicosVolumeEsperaRecomendado : {}", id);
        Optional<OnsDadosHidrologicosVolumeEsperaRecomendadoEntity> onsDadosHidrologicosVolumeEsperaRecomendadoEntity =
            onsDadosHidrologicosVolumeEsperaRecomendadoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsDadosHidrologicosVolumeEsperaRecomendadoEntity);
    }

    /**
     * {@code DELETE  /ons-dados-hidrologicos-volume-espera-recomendados/:id} : delete the "id" onsDadosHidrologicosVolumeEsperaRecomendado.
     *
     * @param id the id of the onsDadosHidrologicosVolumeEsperaRecomendadoEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsDadosHidrologicosVolumeEsperaRecomendado(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsDadosHidrologicosVolumeEsperaRecomendado : {}", id);
        onsDadosHidrologicosVolumeEsperaRecomendadoRepository.deleteById(id);
        onsDadosHidrologicosVolumeEsperaRecomendadoSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-dados-hidrologicos-volume-espera-recomendados/_search?query=:query} : search for the onsDadosHidrologicosVolumeEsperaRecomendado corresponding
     * to the query.
     *
     * @param query the query of the onsDadosHidrologicosVolumeEsperaRecomendado search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsDadosHidrologicosVolumeEsperaRecomendadoEntity> searchOnsDadosHidrologicosVolumeEsperaRecomendados(
        @RequestParam("query") String query
    ) {
        LOG.debug("REST request to search OnsDadosHidrologicosVolumeEsperaRecomendados for query {}", query);
        try {
            return StreamSupport.stream(
                onsDadosHidrologicosVolumeEsperaRecomendadoSearchRepository.search(query).spliterator(),
                false
            ).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
