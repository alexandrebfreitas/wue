package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity;
import com.alexandrebfreitas.wue.repository.OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Repository;
import com.alexandrebfreitas.wue.repository.search.OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity}.
 */
@RestController
@RequestMapping("/api/ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024-s")
@Transactional
public class OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Resource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Resource.class);

    private static final String ENTITY_NAME = "onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Repository onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Repository;

    private final OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchRepository onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchRepository;

    public OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Resource(
        OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Repository onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Repository,
        OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchRepository onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchRepository
    ) {
        this.onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Repository =
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Repository;
        this.onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchRepository =
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchRepository;
    }

    /**
     * {@code POST  /ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024-s} : Create a new onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024.
     *
     * @param onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity the onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity, or with status {@code 400 (Bad Request)} if the onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<
        OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity
    > createOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024(
        @RequestBody OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to save OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 : {}",
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity
        );
        if (onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.getId() != null) {
            throw new BadRequestAlertException(
                "A new onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity =
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Repository.save(
                onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity
            );
        onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchRepository.index(
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity
        );
        return ResponseEntity.created(
            new URI(
                "/api/ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024-s/" +
                onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.getId()
            )
        )
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.getId().toString()
                )
            )
            .body(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity);
    }

    /**
     * {@code PUT  /ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024-s/:id} : Updates an existing onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024.
     *
     * @param id the id of the onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity to save.
     * @param onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity the onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity,
     * or with status {@code 400 (Bad Request)} if the onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<
        OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity
    > updateOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to update OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 : {}, {}",
            id,
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity
        );
        if (onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity =
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Repository.save(
                onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity
            );
        onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchRepository.index(
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity
        );
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.getId().toString()
                )
            )
            .body(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity);
    }

    /**
     * {@code PATCH  /ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024-s/:id} : Partial updates given fields of an existing onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024, field will ignore if it is null
     *
     * @param id the id of the onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity to save.
     * @param onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity the onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity,
     * or with status {@code 400 (Bad Request)} if the onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity is not valid,
     * or with status {@code 404 (Not Found)} if the onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<
        OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity
    > partialUpdateOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to partial update OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 partially : {}, {}",
            id,
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity
        );
        if (onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity> result =
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Repository
                .findById(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.getId())
                .map(existingOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 -> {
                    if (onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.getDatPdp() != null) {
                        existingOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024.setDatPdp(
                            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.getDatPdp()
                        );
                    }
                    if (onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.getCodSubmercado() != null) {
                        existingOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024.setCodSubmercado(
                            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.getCodSubmercado()
                        );
                    }
                    if (onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.getSglTipousina() != null) {
                        existingOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024.setSglTipousina(
                            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.getSglTipousina()
                        );
                    }
                    if (onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.getCodUsinapdp() != null) {
                        existingOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024.setCodUsinapdp(
                            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.getCodUsinapdp()
                        );
                    }
                    if (onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.getNomUsinapdp() != null) {
                        existingOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024.setNomUsinapdp(
                            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.getNomUsinapdp()
                        );
                    }
                    if (onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.getNumIntdespa() != null) {
                        existingOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024.setNumIntdespa(
                            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.getNumIntdespa()
                        );
                    }
                    if (onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.getValDespasup() != null) {
                        existingOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024.setValDespasup(
                            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.getValDespasup()
                        );
                    }

                    return existingOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024;
                })
                .map(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Repository::save)
                .map(savedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 -> {
                    onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchRepository.index(
                        savedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024
                    );
                    return savedOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024;
                });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ENTITY_NAME,
                onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.getId().toString()
            )
        );
    }

    /**
     * {@code GET  /ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024-s} : get all the onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024s.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024s in body.
     */
    @GetMapping("")
    public List<
        OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity
    > getAllOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024s() {
        LOG.debug("REST request to get all OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024s");
        return onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Repository.findAll();
    }

    /**
     * {@code GET  /ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024-s/:id} : get the "id" onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024.
     *
     * @param id the id of the onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<
        OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity
    > getOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 : {}", id);
        Optional<
            OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity
        > onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity =
            onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Repository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity);
    }

    /**
     * {@code DELETE  /ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024-s/:id} : delete the "id" onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024.
     *
     * @param id the id of the onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 : {}", id);
        onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Repository.deleteById(id);
        onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024-s/_search?query=:query} : search for the onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 corresponding
     * to the query.
     *
     * @param query the query of the onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<
        OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity
    > searchOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024s(@RequestParam("query") String query) {
        LOG.debug("REST request to search OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024s for query {}", query);
        try {
            return StreamSupport.stream(
                onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024SearchRepository.search(query).spliterator(),
                false
            ).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
