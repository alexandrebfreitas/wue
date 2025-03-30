package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsDadosHidraulicosReservatorioBaseDiariaEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosHidraulicosReservatorioBaseDiariaRepository;
import com.alexandrebfreitas.wue.repository.search.OnsDadosHidraulicosReservatorioBaseDiariaSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsDadosHidraulicosReservatorioBaseDiariaEntity}.
 */
@RestController
@RequestMapping("/api/ons-dados-hidraulicos-reservatorio-base-diarias")
@Transactional
public class OnsDadosHidraulicosReservatorioBaseDiariaResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsDadosHidraulicosReservatorioBaseDiariaResource.class);

    private static final String ENTITY_NAME = "onsDadosHidraulicosReservatorioBaseDiaria";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsDadosHidraulicosReservatorioBaseDiariaRepository onsDadosHidraulicosReservatorioBaseDiariaRepository;

    private final OnsDadosHidraulicosReservatorioBaseDiariaSearchRepository onsDadosHidraulicosReservatorioBaseDiariaSearchRepository;

    public OnsDadosHidraulicosReservatorioBaseDiariaResource(
        OnsDadosHidraulicosReservatorioBaseDiariaRepository onsDadosHidraulicosReservatorioBaseDiariaRepository,
        OnsDadosHidraulicosReservatorioBaseDiariaSearchRepository onsDadosHidraulicosReservatorioBaseDiariaSearchRepository
    ) {
        this.onsDadosHidraulicosReservatorioBaseDiariaRepository = onsDadosHidraulicosReservatorioBaseDiariaRepository;
        this.onsDadosHidraulicosReservatorioBaseDiariaSearchRepository = onsDadosHidraulicosReservatorioBaseDiariaSearchRepository;
    }

    /**
     * {@code POST  /ons-dados-hidraulicos-reservatorio-base-diarias} : Create a new onsDadosHidraulicosReservatorioBaseDiaria.
     *
     * @param onsDadosHidraulicosReservatorioBaseDiariaEntity the onsDadosHidraulicosReservatorioBaseDiariaEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsDadosHidraulicosReservatorioBaseDiariaEntity, or with status {@code 400 (Bad Request)} if the onsDadosHidraulicosReservatorioBaseDiaria has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OnsDadosHidraulicosReservatorioBaseDiariaEntity> createOnsDadosHidraulicosReservatorioBaseDiaria(
        @RequestBody OnsDadosHidraulicosReservatorioBaseDiariaEntity onsDadosHidraulicosReservatorioBaseDiariaEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to save OnsDadosHidraulicosReservatorioBaseDiaria : {}", onsDadosHidraulicosReservatorioBaseDiariaEntity);
        if (onsDadosHidraulicosReservatorioBaseDiariaEntity.getId() != null) {
            throw new BadRequestAlertException(
                "A new onsDadosHidraulicosReservatorioBaseDiaria cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        onsDadosHidraulicosReservatorioBaseDiariaEntity = onsDadosHidraulicosReservatorioBaseDiariaRepository.save(
            onsDadosHidraulicosReservatorioBaseDiariaEntity
        );
        onsDadosHidraulicosReservatorioBaseDiariaSearchRepository.index(onsDadosHidraulicosReservatorioBaseDiariaEntity);
        return ResponseEntity.created(
            new URI("/api/ons-dados-hidraulicos-reservatorio-base-diarias/" + onsDadosHidraulicosReservatorioBaseDiariaEntity.getId())
        )
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsDadosHidraulicosReservatorioBaseDiariaEntity.getId().toString()
                )
            )
            .body(onsDadosHidraulicosReservatorioBaseDiariaEntity);
    }

    /**
     * {@code PUT  /ons-dados-hidraulicos-reservatorio-base-diarias/:id} : Updates an existing onsDadosHidraulicosReservatorioBaseDiaria.
     *
     * @param id the id of the onsDadosHidraulicosReservatorioBaseDiariaEntity to save.
     * @param onsDadosHidraulicosReservatorioBaseDiariaEntity the onsDadosHidraulicosReservatorioBaseDiariaEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsDadosHidraulicosReservatorioBaseDiariaEntity,
     * or with status {@code 400 (Bad Request)} if the onsDadosHidraulicosReservatorioBaseDiariaEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsDadosHidraulicosReservatorioBaseDiariaEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OnsDadosHidraulicosReservatorioBaseDiariaEntity> updateOnsDadosHidraulicosReservatorioBaseDiaria(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsDadosHidraulicosReservatorioBaseDiariaEntity onsDadosHidraulicosReservatorioBaseDiariaEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to update OnsDadosHidraulicosReservatorioBaseDiaria : {}, {}",
            id,
            onsDadosHidraulicosReservatorioBaseDiariaEntity
        );
        if (onsDadosHidraulicosReservatorioBaseDiariaEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsDadosHidraulicosReservatorioBaseDiariaEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsDadosHidraulicosReservatorioBaseDiariaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsDadosHidraulicosReservatorioBaseDiariaEntity = onsDadosHidraulicosReservatorioBaseDiariaRepository.save(
            onsDadosHidraulicosReservatorioBaseDiariaEntity
        );
        onsDadosHidraulicosReservatorioBaseDiariaSearchRepository.index(onsDadosHidraulicosReservatorioBaseDiariaEntity);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsDadosHidraulicosReservatorioBaseDiariaEntity.getId().toString()
                )
            )
            .body(onsDadosHidraulicosReservatorioBaseDiariaEntity);
    }

    /**
     * {@code PATCH  /ons-dados-hidraulicos-reservatorio-base-diarias/:id} : Partial updates given fields of an existing onsDadosHidraulicosReservatorioBaseDiaria, field will ignore if it is null
     *
     * @param id the id of the onsDadosHidraulicosReservatorioBaseDiariaEntity to save.
     * @param onsDadosHidraulicosReservatorioBaseDiariaEntity the onsDadosHidraulicosReservatorioBaseDiariaEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsDadosHidraulicosReservatorioBaseDiariaEntity,
     * or with status {@code 400 (Bad Request)} if the onsDadosHidraulicosReservatorioBaseDiariaEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsDadosHidraulicosReservatorioBaseDiariaEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsDadosHidraulicosReservatorioBaseDiariaEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OnsDadosHidraulicosReservatorioBaseDiariaEntity> partialUpdateOnsDadosHidraulicosReservatorioBaseDiaria(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsDadosHidraulicosReservatorioBaseDiariaEntity onsDadosHidraulicosReservatorioBaseDiariaEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to partial update OnsDadosHidraulicosReservatorioBaseDiaria partially : {}, {}",
            id,
            onsDadosHidraulicosReservatorioBaseDiariaEntity
        );
        if (onsDadosHidraulicosReservatorioBaseDiariaEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsDadosHidraulicosReservatorioBaseDiariaEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsDadosHidraulicosReservatorioBaseDiariaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsDadosHidraulicosReservatorioBaseDiariaEntity> result = onsDadosHidraulicosReservatorioBaseDiariaRepository
            .findById(onsDadosHidraulicosReservatorioBaseDiariaEntity.getId())
            .map(existingOnsDadosHidraulicosReservatorioBaseDiaria -> {
                if (onsDadosHidraulicosReservatorioBaseDiariaEntity.getValNivelmontante() != null) {
                    existingOnsDadosHidraulicosReservatorioBaseDiaria.setValNivelmontante(
                        onsDadosHidraulicosReservatorioBaseDiariaEntity.getValNivelmontante()
                    );
                }
                if (onsDadosHidraulicosReservatorioBaseDiariaEntity.getValNiveljusante() != null) {
                    existingOnsDadosHidraulicosReservatorioBaseDiaria.setValNiveljusante(
                        onsDadosHidraulicosReservatorioBaseDiariaEntity.getValNiveljusante()
                    );
                }
                if (onsDadosHidraulicosReservatorioBaseDiariaEntity.getValVolumeutilcon() != null) {
                    existingOnsDadosHidraulicosReservatorioBaseDiaria.setValVolumeutilcon(
                        onsDadosHidraulicosReservatorioBaseDiariaEntity.getValVolumeutilcon()
                    );
                }
                if (onsDadosHidraulicosReservatorioBaseDiariaEntity.getValVazaoafluente() != null) {
                    existingOnsDadosHidraulicosReservatorioBaseDiaria.setValVazaoafluente(
                        onsDadosHidraulicosReservatorioBaseDiariaEntity.getValVazaoafluente()
                    );
                }
                if (onsDadosHidraulicosReservatorioBaseDiariaEntity.getValVazaoturbinada() != null) {
                    existingOnsDadosHidraulicosReservatorioBaseDiaria.setValVazaoturbinada(
                        onsDadosHidraulicosReservatorioBaseDiariaEntity.getValVazaoturbinada()
                    );
                }
                if (onsDadosHidraulicosReservatorioBaseDiariaEntity.getValVazaovertida() != null) {
                    existingOnsDadosHidraulicosReservatorioBaseDiaria.setValVazaovertida(
                        onsDadosHidraulicosReservatorioBaseDiariaEntity.getValVazaovertida()
                    );
                }
                if (onsDadosHidraulicosReservatorioBaseDiariaEntity.getValVazaooutrasestruturas() != null) {
                    existingOnsDadosHidraulicosReservatorioBaseDiaria.setValVazaooutrasestruturas(
                        onsDadosHidraulicosReservatorioBaseDiariaEntity.getValVazaooutrasestruturas()
                    );
                }
                if (onsDadosHidraulicosReservatorioBaseDiariaEntity.getValVazaodefluente() != null) {
                    existingOnsDadosHidraulicosReservatorioBaseDiaria.setValVazaodefluente(
                        onsDadosHidraulicosReservatorioBaseDiariaEntity.getValVazaodefluente()
                    );
                }
                if (onsDadosHidraulicosReservatorioBaseDiariaEntity.getValVazaotransferida() != null) {
                    existingOnsDadosHidraulicosReservatorioBaseDiaria.setValVazaotransferida(
                        onsDadosHidraulicosReservatorioBaseDiariaEntity.getValVazaotransferida()
                    );
                }
                if (onsDadosHidraulicosReservatorioBaseDiariaEntity.getValVazaonatural() != null) {
                    existingOnsDadosHidraulicosReservatorioBaseDiaria.setValVazaonatural(
                        onsDadosHidraulicosReservatorioBaseDiariaEntity.getValVazaonatural()
                    );
                }
                if (onsDadosHidraulicosReservatorioBaseDiariaEntity.getValVazaoartificial() != null) {
                    existingOnsDadosHidraulicosReservatorioBaseDiaria.setValVazaoartificial(
                        onsDadosHidraulicosReservatorioBaseDiariaEntity.getValVazaoartificial()
                    );
                }
                if (onsDadosHidraulicosReservatorioBaseDiariaEntity.getValVazaoincremental() != null) {
                    existingOnsDadosHidraulicosReservatorioBaseDiaria.setValVazaoincremental(
                        onsDadosHidraulicosReservatorioBaseDiariaEntity.getValVazaoincremental()
                    );
                }
                if (onsDadosHidraulicosReservatorioBaseDiariaEntity.getValVazaoevaporacaoliquida() != null) {
                    existingOnsDadosHidraulicosReservatorioBaseDiaria.setValVazaoevaporacaoliquida(
                        onsDadosHidraulicosReservatorioBaseDiariaEntity.getValVazaoevaporacaoliquida()
                    );
                }
                if (onsDadosHidraulicosReservatorioBaseDiariaEntity.getValVazaousoconsuntivo() != null) {
                    existingOnsDadosHidraulicosReservatorioBaseDiaria.setValVazaousoconsuntivo(
                        onsDadosHidraulicosReservatorioBaseDiariaEntity.getValVazaousoconsuntivo()
                    );
                }

                return existingOnsDadosHidraulicosReservatorioBaseDiaria;
            })
            .map(onsDadosHidraulicosReservatorioBaseDiariaRepository::save)
            .map(savedOnsDadosHidraulicosReservatorioBaseDiaria -> {
                onsDadosHidraulicosReservatorioBaseDiariaSearchRepository.index(savedOnsDadosHidraulicosReservatorioBaseDiaria);
                return savedOnsDadosHidraulicosReservatorioBaseDiaria;
            });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ENTITY_NAME,
                onsDadosHidraulicosReservatorioBaseDiariaEntity.getId().toString()
            )
        );
    }

    /**
     * {@code GET  /ons-dados-hidraulicos-reservatorio-base-diarias} : get all the onsDadosHidraulicosReservatorioBaseDiarias.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsDadosHidraulicosReservatorioBaseDiarias in body.
     */
    @GetMapping("")
    public List<OnsDadosHidraulicosReservatorioBaseDiariaEntity> getAllOnsDadosHidraulicosReservatorioBaseDiarias() {
        LOG.debug("REST request to get all OnsDadosHidraulicosReservatorioBaseDiarias");
        return onsDadosHidraulicosReservatorioBaseDiariaRepository.findAll();
    }

    /**
     * {@code GET  /ons-dados-hidraulicos-reservatorio-base-diarias/:id} : get the "id" onsDadosHidraulicosReservatorioBaseDiaria.
     *
     * @param id the id of the onsDadosHidraulicosReservatorioBaseDiariaEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsDadosHidraulicosReservatorioBaseDiariaEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OnsDadosHidraulicosReservatorioBaseDiariaEntity> getOnsDadosHidraulicosReservatorioBaseDiaria(
        @PathVariable("id") Long id
    ) {
        LOG.debug("REST request to get OnsDadosHidraulicosReservatorioBaseDiaria : {}", id);
        Optional<OnsDadosHidraulicosReservatorioBaseDiariaEntity> onsDadosHidraulicosReservatorioBaseDiariaEntity =
            onsDadosHidraulicosReservatorioBaseDiariaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsDadosHidraulicosReservatorioBaseDiariaEntity);
    }

    /**
     * {@code DELETE  /ons-dados-hidraulicos-reservatorio-base-diarias/:id} : delete the "id" onsDadosHidraulicosReservatorioBaseDiaria.
     *
     * @param id the id of the onsDadosHidraulicosReservatorioBaseDiariaEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsDadosHidraulicosReservatorioBaseDiaria(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsDadosHidraulicosReservatorioBaseDiaria : {}", id);
        onsDadosHidraulicosReservatorioBaseDiariaRepository.deleteById(id);
        onsDadosHidraulicosReservatorioBaseDiariaSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-dados-hidraulicos-reservatorio-base-diarias/_search?query=:query} : search for the onsDadosHidraulicosReservatorioBaseDiaria corresponding
     * to the query.
     *
     * @param query the query of the onsDadosHidraulicosReservatorioBaseDiaria search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsDadosHidraulicosReservatorioBaseDiariaEntity> searchOnsDadosHidraulicosReservatorioBaseDiarias(
        @RequestParam("query") String query
    ) {
        LOG.debug("REST request to search OnsDadosHidraulicosReservatorioBaseDiarias for query {}", query);
        try {
            return StreamSupport.stream(
                onsDadosHidraulicosReservatorioBaseDiariaSearchRepository.search(query).spliterator(),
                false
            ).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
