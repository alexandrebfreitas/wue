package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsDadosHidraulicosReservatorioBaseHorariaEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosHidraulicosReservatorioBaseHorariaRepository;
import com.alexandrebfreitas.wue.repository.search.OnsDadosHidraulicosReservatorioBaseHorariaSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsDadosHidraulicosReservatorioBaseHorariaEntity}.
 */
@RestController
@RequestMapping("/api/ons-dados-hidraulicos-reservatorio-base-horarias")
@Transactional
public class OnsDadosHidraulicosReservatorioBaseHorariaResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsDadosHidraulicosReservatorioBaseHorariaResource.class);

    private static final String ENTITY_NAME = "onsDadosHidraulicosReservatorioBaseHoraria";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsDadosHidraulicosReservatorioBaseHorariaRepository onsDadosHidraulicosReservatorioBaseHorariaRepository;

    private final OnsDadosHidraulicosReservatorioBaseHorariaSearchRepository onsDadosHidraulicosReservatorioBaseHorariaSearchRepository;

    public OnsDadosHidraulicosReservatorioBaseHorariaResource(
        OnsDadosHidraulicosReservatorioBaseHorariaRepository onsDadosHidraulicosReservatorioBaseHorariaRepository,
        OnsDadosHidraulicosReservatorioBaseHorariaSearchRepository onsDadosHidraulicosReservatorioBaseHorariaSearchRepository
    ) {
        this.onsDadosHidraulicosReservatorioBaseHorariaRepository = onsDadosHidraulicosReservatorioBaseHorariaRepository;
        this.onsDadosHidraulicosReservatorioBaseHorariaSearchRepository = onsDadosHidraulicosReservatorioBaseHorariaSearchRepository;
    }

    /**
     * {@code POST  /ons-dados-hidraulicos-reservatorio-base-horarias} : Create a new onsDadosHidraulicosReservatorioBaseHoraria.
     *
     * @param onsDadosHidraulicosReservatorioBaseHorariaEntity the onsDadosHidraulicosReservatorioBaseHorariaEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsDadosHidraulicosReservatorioBaseHorariaEntity, or with status {@code 400 (Bad Request)} if the onsDadosHidraulicosReservatorioBaseHoraria has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OnsDadosHidraulicosReservatorioBaseHorariaEntity> createOnsDadosHidraulicosReservatorioBaseHoraria(
        @RequestBody OnsDadosHidraulicosReservatorioBaseHorariaEntity onsDadosHidraulicosReservatorioBaseHorariaEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to save OnsDadosHidraulicosReservatorioBaseHoraria : {}", onsDadosHidraulicosReservatorioBaseHorariaEntity);
        if (onsDadosHidraulicosReservatorioBaseHorariaEntity.getId() != null) {
            throw new BadRequestAlertException(
                "A new onsDadosHidraulicosReservatorioBaseHoraria cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        onsDadosHidraulicosReservatorioBaseHorariaEntity = onsDadosHidraulicosReservatorioBaseHorariaRepository.save(
            onsDadosHidraulicosReservatorioBaseHorariaEntity
        );
        onsDadosHidraulicosReservatorioBaseHorariaSearchRepository.index(onsDadosHidraulicosReservatorioBaseHorariaEntity);
        return ResponseEntity.created(
            new URI("/api/ons-dados-hidraulicos-reservatorio-base-horarias/" + onsDadosHidraulicosReservatorioBaseHorariaEntity.getId())
        )
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsDadosHidraulicosReservatorioBaseHorariaEntity.getId().toString()
                )
            )
            .body(onsDadosHidraulicosReservatorioBaseHorariaEntity);
    }

    /**
     * {@code PUT  /ons-dados-hidraulicos-reservatorio-base-horarias/:id} : Updates an existing onsDadosHidraulicosReservatorioBaseHoraria.
     *
     * @param id the id of the onsDadosHidraulicosReservatorioBaseHorariaEntity to save.
     * @param onsDadosHidraulicosReservatorioBaseHorariaEntity the onsDadosHidraulicosReservatorioBaseHorariaEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsDadosHidraulicosReservatorioBaseHorariaEntity,
     * or with status {@code 400 (Bad Request)} if the onsDadosHidraulicosReservatorioBaseHorariaEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsDadosHidraulicosReservatorioBaseHorariaEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OnsDadosHidraulicosReservatorioBaseHorariaEntity> updateOnsDadosHidraulicosReservatorioBaseHoraria(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsDadosHidraulicosReservatorioBaseHorariaEntity onsDadosHidraulicosReservatorioBaseHorariaEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to update OnsDadosHidraulicosReservatorioBaseHoraria : {}, {}",
            id,
            onsDadosHidraulicosReservatorioBaseHorariaEntity
        );
        if (onsDadosHidraulicosReservatorioBaseHorariaEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsDadosHidraulicosReservatorioBaseHorariaEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsDadosHidraulicosReservatorioBaseHorariaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsDadosHidraulicosReservatorioBaseHorariaEntity = onsDadosHidraulicosReservatorioBaseHorariaRepository.save(
            onsDadosHidraulicosReservatorioBaseHorariaEntity
        );
        onsDadosHidraulicosReservatorioBaseHorariaSearchRepository.index(onsDadosHidraulicosReservatorioBaseHorariaEntity);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsDadosHidraulicosReservatorioBaseHorariaEntity.getId().toString()
                )
            )
            .body(onsDadosHidraulicosReservatorioBaseHorariaEntity);
    }

    /**
     * {@code PATCH  /ons-dados-hidraulicos-reservatorio-base-horarias/:id} : Partial updates given fields of an existing onsDadosHidraulicosReservatorioBaseHoraria, field will ignore if it is null
     *
     * @param id the id of the onsDadosHidraulicosReservatorioBaseHorariaEntity to save.
     * @param onsDadosHidraulicosReservatorioBaseHorariaEntity the onsDadosHidraulicosReservatorioBaseHorariaEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsDadosHidraulicosReservatorioBaseHorariaEntity,
     * or with status {@code 400 (Bad Request)} if the onsDadosHidraulicosReservatorioBaseHorariaEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsDadosHidraulicosReservatorioBaseHorariaEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsDadosHidraulicosReservatorioBaseHorariaEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OnsDadosHidraulicosReservatorioBaseHorariaEntity> partialUpdateOnsDadosHidraulicosReservatorioBaseHoraria(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsDadosHidraulicosReservatorioBaseHorariaEntity onsDadosHidraulicosReservatorioBaseHorariaEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to partial update OnsDadosHidraulicosReservatorioBaseHoraria partially : {}, {}",
            id,
            onsDadosHidraulicosReservatorioBaseHorariaEntity
        );
        if (onsDadosHidraulicosReservatorioBaseHorariaEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsDadosHidraulicosReservatorioBaseHorariaEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsDadosHidraulicosReservatorioBaseHorariaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsDadosHidraulicosReservatorioBaseHorariaEntity> result = onsDadosHidraulicosReservatorioBaseHorariaRepository
            .findById(onsDadosHidraulicosReservatorioBaseHorariaEntity.getId())
            .map(existingOnsDadosHidraulicosReservatorioBaseHoraria -> {
                if (onsDadosHidraulicosReservatorioBaseHorariaEntity.getValVolumeutil() != null) {
                    existingOnsDadosHidraulicosReservatorioBaseHoraria.setValVolumeutil(
                        onsDadosHidraulicosReservatorioBaseHorariaEntity.getValVolumeutil()
                    );
                }
                if (onsDadosHidraulicosReservatorioBaseHorariaEntity.getValVazaoafluente() != null) {
                    existingOnsDadosHidraulicosReservatorioBaseHoraria.setValVazaoafluente(
                        onsDadosHidraulicosReservatorioBaseHorariaEntity.getValVazaoafluente()
                    );
                }
                if (onsDadosHidraulicosReservatorioBaseHorariaEntity.getValVazaodefluente() != null) {
                    existingOnsDadosHidraulicosReservatorioBaseHoraria.setValVazaodefluente(
                        onsDadosHidraulicosReservatorioBaseHorariaEntity.getValVazaodefluente()
                    );
                }
                if (onsDadosHidraulicosReservatorioBaseHorariaEntity.getValVazaoturbinada() != null) {
                    existingOnsDadosHidraulicosReservatorioBaseHoraria.setValVazaoturbinada(
                        onsDadosHidraulicosReservatorioBaseHorariaEntity.getValVazaoturbinada()
                    );
                }
                if (onsDadosHidraulicosReservatorioBaseHorariaEntity.getValVazaovertida() != null) {
                    existingOnsDadosHidraulicosReservatorioBaseHoraria.setValVazaovertida(
                        onsDadosHidraulicosReservatorioBaseHorariaEntity.getValVazaovertida()
                    );
                }
                if (onsDadosHidraulicosReservatorioBaseHorariaEntity.getValVazaooutrasestruturas() != null) {
                    existingOnsDadosHidraulicosReservatorioBaseHoraria.setValVazaooutrasestruturas(
                        onsDadosHidraulicosReservatorioBaseHorariaEntity.getValVazaooutrasestruturas()
                    );
                }
                if (onsDadosHidraulicosReservatorioBaseHorariaEntity.getValVazaotransferida() != null) {
                    existingOnsDadosHidraulicosReservatorioBaseHoraria.setValVazaotransferida(
                        onsDadosHidraulicosReservatorioBaseHorariaEntity.getValVazaotransferida()
                    );
                }
                if (onsDadosHidraulicosReservatorioBaseHorariaEntity.getValVazaovertidanaoturbinavel() != null) {
                    existingOnsDadosHidraulicosReservatorioBaseHoraria.setValVazaovertidanaoturbinavel(
                        onsDadosHidraulicosReservatorioBaseHorariaEntity.getValVazaovertidanaoturbinavel()
                    );
                }

                return existingOnsDadosHidraulicosReservatorioBaseHoraria;
            })
            .map(onsDadosHidraulicosReservatorioBaseHorariaRepository::save)
            .map(savedOnsDadosHidraulicosReservatorioBaseHoraria -> {
                onsDadosHidraulicosReservatorioBaseHorariaSearchRepository.index(savedOnsDadosHidraulicosReservatorioBaseHoraria);
                return savedOnsDadosHidraulicosReservatorioBaseHoraria;
            });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ENTITY_NAME,
                onsDadosHidraulicosReservatorioBaseHorariaEntity.getId().toString()
            )
        );
    }

    /**
     * {@code GET  /ons-dados-hidraulicos-reservatorio-base-horarias} : get all the onsDadosHidraulicosReservatorioBaseHorarias.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsDadosHidraulicosReservatorioBaseHorarias in body.
     */
    @GetMapping("")
    public List<OnsDadosHidraulicosReservatorioBaseHorariaEntity> getAllOnsDadosHidraulicosReservatorioBaseHorarias() {
        LOG.debug("REST request to get all OnsDadosHidraulicosReservatorioBaseHorarias");
        return onsDadosHidraulicosReservatorioBaseHorariaRepository.findAll();
    }

    /**
     * {@code GET  /ons-dados-hidraulicos-reservatorio-base-horarias/:id} : get the "id" onsDadosHidraulicosReservatorioBaseHoraria.
     *
     * @param id the id of the onsDadosHidraulicosReservatorioBaseHorariaEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsDadosHidraulicosReservatorioBaseHorariaEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OnsDadosHidraulicosReservatorioBaseHorariaEntity> getOnsDadosHidraulicosReservatorioBaseHoraria(
        @PathVariable("id") Long id
    ) {
        LOG.debug("REST request to get OnsDadosHidraulicosReservatorioBaseHoraria : {}", id);
        Optional<OnsDadosHidraulicosReservatorioBaseHorariaEntity> onsDadosHidraulicosReservatorioBaseHorariaEntity =
            onsDadosHidraulicosReservatorioBaseHorariaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsDadosHidraulicosReservatorioBaseHorariaEntity);
    }

    /**
     * {@code DELETE  /ons-dados-hidraulicos-reservatorio-base-horarias/:id} : delete the "id" onsDadosHidraulicosReservatorioBaseHoraria.
     *
     * @param id the id of the onsDadosHidraulicosReservatorioBaseHorariaEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsDadosHidraulicosReservatorioBaseHoraria(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsDadosHidraulicosReservatorioBaseHoraria : {}", id);
        onsDadosHidraulicosReservatorioBaseHorariaRepository.deleteById(id);
        onsDadosHidraulicosReservatorioBaseHorariaSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-dados-hidraulicos-reservatorio-base-horarias/_search?query=:query} : search for the onsDadosHidraulicosReservatorioBaseHoraria corresponding
     * to the query.
     *
     * @param query the query of the onsDadosHidraulicosReservatorioBaseHoraria search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsDadosHidraulicosReservatorioBaseHorariaEntity> searchOnsDadosHidraulicosReservatorioBaseHorarias(
        @RequestParam("query") String query
    ) {
        LOG.debug("REST request to search OnsDadosHidraulicosReservatorioBaseHorarias for query {}", query);
        try {
            return StreamSupport.stream(
                onsDadosHidraulicosReservatorioBaseHorariaSearchRepository.search(query).spliterator(),
                false
            ).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
