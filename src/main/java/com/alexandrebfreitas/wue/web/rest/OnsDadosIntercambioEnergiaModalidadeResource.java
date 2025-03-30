package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsDadosIntercambioEnergiaModalidadeEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosIntercambioEnergiaModalidadeRepository;
import com.alexandrebfreitas.wue.repository.search.OnsDadosIntercambioEnergiaModalidadeSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsDadosIntercambioEnergiaModalidadeEntity}.
 */
@RestController
@RequestMapping("/api/ons-dados-intercambio-energia-modalidades")
@Transactional
public class OnsDadosIntercambioEnergiaModalidadeResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsDadosIntercambioEnergiaModalidadeResource.class);

    private static final String ENTITY_NAME = "onsDadosIntercambioEnergiaModalidade";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsDadosIntercambioEnergiaModalidadeRepository onsDadosIntercambioEnergiaModalidadeRepository;

    private final OnsDadosIntercambioEnergiaModalidadeSearchRepository onsDadosIntercambioEnergiaModalidadeSearchRepository;

    public OnsDadosIntercambioEnergiaModalidadeResource(
        OnsDadosIntercambioEnergiaModalidadeRepository onsDadosIntercambioEnergiaModalidadeRepository,
        OnsDadosIntercambioEnergiaModalidadeSearchRepository onsDadosIntercambioEnergiaModalidadeSearchRepository
    ) {
        this.onsDadosIntercambioEnergiaModalidadeRepository = onsDadosIntercambioEnergiaModalidadeRepository;
        this.onsDadosIntercambioEnergiaModalidadeSearchRepository = onsDadosIntercambioEnergiaModalidadeSearchRepository;
    }

    /**
     * {@code POST  /ons-dados-intercambio-energia-modalidades} : Create a new onsDadosIntercambioEnergiaModalidade.
     *
     * @param onsDadosIntercambioEnergiaModalidadeEntity the onsDadosIntercambioEnergiaModalidadeEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsDadosIntercambioEnergiaModalidadeEntity, or with status {@code 400 (Bad Request)} if the onsDadosIntercambioEnergiaModalidade has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OnsDadosIntercambioEnergiaModalidadeEntity> createOnsDadosIntercambioEnergiaModalidade(
        @RequestBody OnsDadosIntercambioEnergiaModalidadeEntity onsDadosIntercambioEnergiaModalidadeEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to save OnsDadosIntercambioEnergiaModalidade : {}", onsDadosIntercambioEnergiaModalidadeEntity);
        if (onsDadosIntercambioEnergiaModalidadeEntity.getId() != null) {
            throw new BadRequestAlertException(
                "A new onsDadosIntercambioEnergiaModalidade cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        onsDadosIntercambioEnergiaModalidadeEntity = onsDadosIntercambioEnergiaModalidadeRepository.save(
            onsDadosIntercambioEnergiaModalidadeEntity
        );
        onsDadosIntercambioEnergiaModalidadeSearchRepository.index(onsDadosIntercambioEnergiaModalidadeEntity);
        return ResponseEntity.created(
            new URI("/api/ons-dados-intercambio-energia-modalidades/" + onsDadosIntercambioEnergiaModalidadeEntity.getId())
        )
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsDadosIntercambioEnergiaModalidadeEntity.getId().toString()
                )
            )
            .body(onsDadosIntercambioEnergiaModalidadeEntity);
    }

    /**
     * {@code PUT  /ons-dados-intercambio-energia-modalidades/:id} : Updates an existing onsDadosIntercambioEnergiaModalidade.
     *
     * @param id the id of the onsDadosIntercambioEnergiaModalidadeEntity to save.
     * @param onsDadosIntercambioEnergiaModalidadeEntity the onsDadosIntercambioEnergiaModalidadeEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsDadosIntercambioEnergiaModalidadeEntity,
     * or with status {@code 400 (Bad Request)} if the onsDadosIntercambioEnergiaModalidadeEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsDadosIntercambioEnergiaModalidadeEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OnsDadosIntercambioEnergiaModalidadeEntity> updateOnsDadosIntercambioEnergiaModalidade(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsDadosIntercambioEnergiaModalidadeEntity onsDadosIntercambioEnergiaModalidadeEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to update OnsDadosIntercambioEnergiaModalidade : {}, {}", id, onsDadosIntercambioEnergiaModalidadeEntity);
        if (onsDadosIntercambioEnergiaModalidadeEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsDadosIntercambioEnergiaModalidadeEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsDadosIntercambioEnergiaModalidadeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsDadosIntercambioEnergiaModalidadeEntity = onsDadosIntercambioEnergiaModalidadeRepository.save(
            onsDadosIntercambioEnergiaModalidadeEntity
        );
        onsDadosIntercambioEnergiaModalidadeSearchRepository.index(onsDadosIntercambioEnergiaModalidadeEntity);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsDadosIntercambioEnergiaModalidadeEntity.getId().toString()
                )
            )
            .body(onsDadosIntercambioEnergiaModalidadeEntity);
    }

    /**
     * {@code PATCH  /ons-dados-intercambio-energia-modalidades/:id} : Partial updates given fields of an existing onsDadosIntercambioEnergiaModalidade, field will ignore if it is null
     *
     * @param id the id of the onsDadosIntercambioEnergiaModalidadeEntity to save.
     * @param onsDadosIntercambioEnergiaModalidadeEntity the onsDadosIntercambioEnergiaModalidadeEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsDadosIntercambioEnergiaModalidadeEntity,
     * or with status {@code 400 (Bad Request)} if the onsDadosIntercambioEnergiaModalidadeEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsDadosIntercambioEnergiaModalidadeEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsDadosIntercambioEnergiaModalidadeEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OnsDadosIntercambioEnergiaModalidadeEntity> partialUpdateOnsDadosIntercambioEnergiaModalidade(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsDadosIntercambioEnergiaModalidadeEntity onsDadosIntercambioEnergiaModalidadeEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to partial update OnsDadosIntercambioEnergiaModalidade partially : {}, {}",
            id,
            onsDadosIntercambioEnergiaModalidadeEntity
        );
        if (onsDadosIntercambioEnergiaModalidadeEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsDadosIntercambioEnergiaModalidadeEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsDadosIntercambioEnergiaModalidadeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsDadosIntercambioEnergiaModalidadeEntity> result = onsDadosIntercambioEnergiaModalidadeRepository
            .findById(onsDadosIntercambioEnergiaModalidadeEntity.getId())
            .map(existingOnsDadosIntercambioEnergiaModalidade -> {
                if (onsDadosIntercambioEnergiaModalidadeEntity.getNomConversora() != null) {
                    existingOnsDadosIntercambioEnergiaModalidade.setNomConversora(
                        onsDadosIntercambioEnergiaModalidadeEntity.getNomConversora()
                    );
                }
                if (onsDadosIntercambioEnergiaModalidadeEntity.getDinInstante() != null) {
                    existingOnsDadosIntercambioEnergiaModalidade.setDinInstante(
                        onsDadosIntercambioEnergiaModalidadeEntity.getDinInstante()
                    );
                }
                if (onsDadosIntercambioEnergiaModalidadeEntity.getValModalidadecontratual() != null) {
                    existingOnsDadosIntercambioEnergiaModalidade.setValModalidadecontratual(
                        onsDadosIntercambioEnergiaModalidadeEntity.getValModalidadecontratual()
                    );
                }
                if (onsDadosIntercambioEnergiaModalidadeEntity.getValModalidadeemergencial() != null) {
                    existingOnsDadosIntercambioEnergiaModalidade.setValModalidadeemergencial(
                        onsDadosIntercambioEnergiaModalidadeEntity.getValModalidadeemergencial()
                    );
                }
                if (onsDadosIntercambioEnergiaModalidadeEntity.getValModalidadeoportunidade() != null) {
                    existingOnsDadosIntercambioEnergiaModalidade.setValModalidadeoportunidade(
                        onsDadosIntercambioEnergiaModalidadeEntity.getValModalidadeoportunidade()
                    );
                }
                if (onsDadosIntercambioEnergiaModalidadeEntity.getValModalidadeteste() != null) {
                    existingOnsDadosIntercambioEnergiaModalidade.setValModalidadeteste(
                        onsDadosIntercambioEnergiaModalidadeEntity.getValModalidadeteste()
                    );
                }
                if (onsDadosIntercambioEnergiaModalidadeEntity.getValModalidadeexcepcional() != null) {
                    existingOnsDadosIntercambioEnergiaModalidade.setValModalidadeexcepcional(
                        onsDadosIntercambioEnergiaModalidadeEntity.getValModalidadeexcepcional()
                    );
                }

                return existingOnsDadosIntercambioEnergiaModalidade;
            })
            .map(onsDadosIntercambioEnergiaModalidadeRepository::save)
            .map(savedOnsDadosIntercambioEnergiaModalidade -> {
                onsDadosIntercambioEnergiaModalidadeSearchRepository.index(savedOnsDadosIntercambioEnergiaModalidade);
                return savedOnsDadosIntercambioEnergiaModalidade;
            });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ENTITY_NAME,
                onsDadosIntercambioEnergiaModalidadeEntity.getId().toString()
            )
        );
    }

    /**
     * {@code GET  /ons-dados-intercambio-energia-modalidades} : get all the onsDadosIntercambioEnergiaModalidades.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsDadosIntercambioEnergiaModalidades in body.
     */
    @GetMapping("")
    public List<OnsDadosIntercambioEnergiaModalidadeEntity> getAllOnsDadosIntercambioEnergiaModalidades() {
        LOG.debug("REST request to get all OnsDadosIntercambioEnergiaModalidades");
        return onsDadosIntercambioEnergiaModalidadeRepository.findAll();
    }

    /**
     * {@code GET  /ons-dados-intercambio-energia-modalidades/:id} : get the "id" onsDadosIntercambioEnergiaModalidade.
     *
     * @param id the id of the onsDadosIntercambioEnergiaModalidadeEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsDadosIntercambioEnergiaModalidadeEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OnsDadosIntercambioEnergiaModalidadeEntity> getOnsDadosIntercambioEnergiaModalidade(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsDadosIntercambioEnergiaModalidade : {}", id);
        Optional<OnsDadosIntercambioEnergiaModalidadeEntity> onsDadosIntercambioEnergiaModalidadeEntity =
            onsDadosIntercambioEnergiaModalidadeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsDadosIntercambioEnergiaModalidadeEntity);
    }

    /**
     * {@code DELETE  /ons-dados-intercambio-energia-modalidades/:id} : delete the "id" onsDadosIntercambioEnergiaModalidade.
     *
     * @param id the id of the onsDadosIntercambioEnergiaModalidadeEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsDadosIntercambioEnergiaModalidade(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsDadosIntercambioEnergiaModalidade : {}", id);
        onsDadosIntercambioEnergiaModalidadeRepository.deleteById(id);
        onsDadosIntercambioEnergiaModalidadeSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-dados-intercambio-energia-modalidades/_search?query=:query} : search for the onsDadosIntercambioEnergiaModalidade corresponding
     * to the query.
     *
     * @param query the query of the onsDadosIntercambioEnergiaModalidade search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsDadosIntercambioEnergiaModalidadeEntity> searchOnsDadosIntercambioEnergiaModalidades(
        @RequestParam("query") String query
    ) {
        LOG.debug("REST request to search OnsDadosIntercambioEnergiaModalidades for query {}", query);
        try {
            return StreamSupport.stream(onsDadosIntercambioEnergiaModalidadeSearchRepository.search(query).spliterator(), false).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
