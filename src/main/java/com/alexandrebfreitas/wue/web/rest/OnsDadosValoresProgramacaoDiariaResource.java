package com.alexandrebfreitas.wue.web.rest;

import com.alexandrebfreitas.wue.domain.OnsDadosValoresProgramacaoDiariaEntity;
import com.alexandrebfreitas.wue.repository.OnsDadosValoresProgramacaoDiariaRepository;
import com.alexandrebfreitas.wue.repository.search.OnsDadosValoresProgramacaoDiariaSearchRepository;
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
 * REST controller for managing {@link com.alexandrebfreitas.wue.domain.OnsDadosValoresProgramacaoDiariaEntity}.
 */
@RestController
@RequestMapping("/api/ons-dados-valores-programacao-diarias")
@Transactional
public class OnsDadosValoresProgramacaoDiariaResource {

    private static final Logger LOG = LoggerFactory.getLogger(OnsDadosValoresProgramacaoDiariaResource.class);

    private static final String ENTITY_NAME = "onsDadosValoresProgramacaoDiaria";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OnsDadosValoresProgramacaoDiariaRepository onsDadosValoresProgramacaoDiariaRepository;

    private final OnsDadosValoresProgramacaoDiariaSearchRepository onsDadosValoresProgramacaoDiariaSearchRepository;

    public OnsDadosValoresProgramacaoDiariaResource(
        OnsDadosValoresProgramacaoDiariaRepository onsDadosValoresProgramacaoDiariaRepository,
        OnsDadosValoresProgramacaoDiariaSearchRepository onsDadosValoresProgramacaoDiariaSearchRepository
    ) {
        this.onsDadosValoresProgramacaoDiariaRepository = onsDadosValoresProgramacaoDiariaRepository;
        this.onsDadosValoresProgramacaoDiariaSearchRepository = onsDadosValoresProgramacaoDiariaSearchRepository;
    }

    /**
     * {@code POST  /ons-dados-valores-programacao-diarias} : Create a new onsDadosValoresProgramacaoDiaria.
     *
     * @param onsDadosValoresProgramacaoDiariaEntity the onsDadosValoresProgramacaoDiariaEntity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new onsDadosValoresProgramacaoDiariaEntity, or with status {@code 400 (Bad Request)} if the onsDadosValoresProgramacaoDiaria has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OnsDadosValoresProgramacaoDiariaEntity> createOnsDadosValoresProgramacaoDiaria(
        @RequestBody OnsDadosValoresProgramacaoDiariaEntity onsDadosValoresProgramacaoDiariaEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to save OnsDadosValoresProgramacaoDiaria : {}", onsDadosValoresProgramacaoDiariaEntity);
        if (onsDadosValoresProgramacaoDiariaEntity.getId() != null) {
            throw new BadRequestAlertException("A new onsDadosValoresProgramacaoDiaria cannot already have an ID", ENTITY_NAME, "idexists");
        }
        onsDadosValoresProgramacaoDiariaEntity = onsDadosValoresProgramacaoDiariaRepository.save(onsDadosValoresProgramacaoDiariaEntity);
        onsDadosValoresProgramacaoDiariaSearchRepository.index(onsDadosValoresProgramacaoDiariaEntity);
        return ResponseEntity.created(
            new URI("/api/ons-dados-valores-programacao-diarias/" + onsDadosValoresProgramacaoDiariaEntity.getId())
        )
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsDadosValoresProgramacaoDiariaEntity.getId().toString()
                )
            )
            .body(onsDadosValoresProgramacaoDiariaEntity);
    }

    /**
     * {@code PUT  /ons-dados-valores-programacao-diarias/:id} : Updates an existing onsDadosValoresProgramacaoDiaria.
     *
     * @param id the id of the onsDadosValoresProgramacaoDiariaEntity to save.
     * @param onsDadosValoresProgramacaoDiariaEntity the onsDadosValoresProgramacaoDiariaEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsDadosValoresProgramacaoDiariaEntity,
     * or with status {@code 400 (Bad Request)} if the onsDadosValoresProgramacaoDiariaEntity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the onsDadosValoresProgramacaoDiariaEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OnsDadosValoresProgramacaoDiariaEntity> updateOnsDadosValoresProgramacaoDiaria(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsDadosValoresProgramacaoDiariaEntity onsDadosValoresProgramacaoDiariaEntity
    ) throws URISyntaxException {
        LOG.debug("REST request to update OnsDadosValoresProgramacaoDiaria : {}, {}", id, onsDadosValoresProgramacaoDiariaEntity);
        if (onsDadosValoresProgramacaoDiariaEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsDadosValoresProgramacaoDiariaEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsDadosValoresProgramacaoDiariaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        onsDadosValoresProgramacaoDiariaEntity = onsDadosValoresProgramacaoDiariaRepository.save(onsDadosValoresProgramacaoDiariaEntity);
        onsDadosValoresProgramacaoDiariaSearchRepository.index(onsDadosValoresProgramacaoDiariaEntity);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    onsDadosValoresProgramacaoDiariaEntity.getId().toString()
                )
            )
            .body(onsDadosValoresProgramacaoDiariaEntity);
    }

    /**
     * {@code PATCH  /ons-dados-valores-programacao-diarias/:id} : Partial updates given fields of an existing onsDadosValoresProgramacaoDiaria, field will ignore if it is null
     *
     * @param id the id of the onsDadosValoresProgramacaoDiariaEntity to save.
     * @param onsDadosValoresProgramacaoDiariaEntity the onsDadosValoresProgramacaoDiariaEntity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated onsDadosValoresProgramacaoDiariaEntity,
     * or with status {@code 400 (Bad Request)} if the onsDadosValoresProgramacaoDiariaEntity is not valid,
     * or with status {@code 404 (Not Found)} if the onsDadosValoresProgramacaoDiariaEntity is not found,
     * or with status {@code 500 (Internal Server Error)} if the onsDadosValoresProgramacaoDiariaEntity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OnsDadosValoresProgramacaoDiariaEntity> partialUpdateOnsDadosValoresProgramacaoDiaria(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OnsDadosValoresProgramacaoDiariaEntity onsDadosValoresProgramacaoDiariaEntity
    ) throws URISyntaxException {
        LOG.debug(
            "REST request to partial update OnsDadosValoresProgramacaoDiaria partially : {}, {}",
            id,
            onsDadosValoresProgramacaoDiariaEntity
        );
        if (onsDadosValoresProgramacaoDiariaEntity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, onsDadosValoresProgramacaoDiariaEntity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!onsDadosValoresProgramacaoDiariaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OnsDadosValoresProgramacaoDiariaEntity> result = onsDadosValoresProgramacaoDiariaRepository
            .findById(onsDadosValoresProgramacaoDiariaEntity.getId())
            .map(existingOnsDadosValoresProgramacaoDiaria -> {
                if (onsDadosValoresProgramacaoDiariaEntity.getDinProgramacaodia() != null) {
                    existingOnsDadosValoresProgramacaoDiaria.setDinProgramacaodia(
                        onsDadosValoresProgramacaoDiariaEntity.getDinProgramacaodia()
                    );
                }
                if (onsDadosValoresProgramacaoDiariaEntity.getNumPatamar() != null) {
                    existingOnsDadosValoresProgramacaoDiaria.setNumPatamar(onsDadosValoresProgramacaoDiariaEntity.getNumPatamar());
                }
                if (onsDadosValoresProgramacaoDiariaEntity.getCodExibicaousina() != null) {
                    existingOnsDadosValoresProgramacaoDiaria.setCodExibicaousina(
                        onsDadosValoresProgramacaoDiariaEntity.getCodExibicaousina()
                    );
                }
                if (onsDadosValoresProgramacaoDiariaEntity.getNomUsina() != null) {
                    existingOnsDadosValoresProgramacaoDiaria.setNomUsina(onsDadosValoresProgramacaoDiariaEntity.getNomUsina());
                }
                if (onsDadosValoresProgramacaoDiariaEntity.getTipGeracao() != null) {
                    existingOnsDadosValoresProgramacaoDiaria.setTipGeracao(onsDadosValoresProgramacaoDiariaEntity.getTipGeracao());
                }
                if (onsDadosValoresProgramacaoDiariaEntity.getNomModalidadeoperacao() != null) {
                    existingOnsDadosValoresProgramacaoDiaria.setNomModalidadeoperacao(
                        onsDadosValoresProgramacaoDiariaEntity.getNomModalidadeoperacao()
                    );
                }
                if (onsDadosValoresProgramacaoDiariaEntity.getIdSubsistema() != null) {
                    existingOnsDadosValoresProgramacaoDiaria.setIdSubsistema(onsDadosValoresProgramacaoDiariaEntity.getIdSubsistema());
                }
                if (onsDadosValoresProgramacaoDiariaEntity.getNomSubsistema() != null) {
                    existingOnsDadosValoresProgramacaoDiaria.setNomSubsistema(onsDadosValoresProgramacaoDiariaEntity.getNomSubsistema());
                }
                if (onsDadosValoresProgramacaoDiariaEntity.getIdEstado() != null) {
                    existingOnsDadosValoresProgramacaoDiaria.setIdEstado(onsDadosValoresProgramacaoDiariaEntity.getIdEstado());
                }
                if (onsDadosValoresProgramacaoDiariaEntity.getNomEstado() != null) {
                    existingOnsDadosValoresProgramacaoDiaria.setNomEstado(onsDadosValoresProgramacaoDiariaEntity.getNomEstado());
                }
                if (onsDadosValoresProgramacaoDiariaEntity.getValGeracaoprogramada() != null) {
                    existingOnsDadosValoresProgramacaoDiaria.setValGeracaoprogramada(
                        onsDadosValoresProgramacaoDiariaEntity.getValGeracaoprogramada()
                    );
                }
                if (onsDadosValoresProgramacaoDiariaEntity.getValDisponibilidade() != null) {
                    existingOnsDadosValoresProgramacaoDiaria.setValDisponibilidade(
                        onsDadosValoresProgramacaoDiariaEntity.getValDisponibilidade()
                    );
                }
                if (onsDadosValoresProgramacaoDiariaEntity.getValOrdemmerito() != null) {
                    existingOnsDadosValoresProgramacaoDiaria.setValOrdemmerito(onsDadosValoresProgramacaoDiariaEntity.getValOrdemmerito());
                }
                if (onsDadosValoresProgramacaoDiariaEntity.getValInflexibilidade() != null) {
                    existingOnsDadosValoresProgramacaoDiaria.setValInflexibilidade(
                        onsDadosValoresProgramacaoDiariaEntity.getValInflexibilidade()
                    );
                }
                if (onsDadosValoresProgramacaoDiariaEntity.getValUc() != null) {
                    existingOnsDadosValoresProgramacaoDiaria.setValUc(onsDadosValoresProgramacaoDiariaEntity.getValUc());
                }
                if (onsDadosValoresProgramacaoDiariaEntity.getValRazaoeletrica() != null) {
                    existingOnsDadosValoresProgramacaoDiaria.setValRazaoeletrica(
                        onsDadosValoresProgramacaoDiariaEntity.getValRazaoeletrica()
                    );
                }
                if (onsDadosValoresProgramacaoDiariaEntity.getValGeracaoenergetica() != null) {
                    existingOnsDadosValoresProgramacaoDiaria.setValGeracaoenergetica(
                        onsDadosValoresProgramacaoDiariaEntity.getValGeracaoenergetica()
                    );
                }
                if (onsDadosValoresProgramacaoDiariaEntity.getValGesubgsub() != null) {
                    existingOnsDadosValoresProgramacaoDiaria.setValGesubgsub(onsDadosValoresProgramacaoDiariaEntity.getValGesubgsub());
                }
                if (onsDadosValoresProgramacaoDiariaEntity.getValExportacao() != null) {
                    existingOnsDadosValoresProgramacaoDiaria.setValExportacao(onsDadosValoresProgramacaoDiariaEntity.getValExportacao());
                }
                if (onsDadosValoresProgramacaoDiariaEntity.getValReposicaoexportacao() != null) {
                    existingOnsDadosValoresProgramacaoDiaria.setValReposicaoexportacao(
                        onsDadosValoresProgramacaoDiariaEntity.getValReposicaoexportacao()
                    );
                }
                if (onsDadosValoresProgramacaoDiariaEntity.getValFaltacombustivel() != null) {
                    existingOnsDadosValoresProgramacaoDiaria.setValFaltacombustivel(
                        onsDadosValoresProgramacaoDiariaEntity.getValFaltacombustivel()
                    );
                }

                return existingOnsDadosValoresProgramacaoDiaria;
            })
            .map(onsDadosValoresProgramacaoDiariaRepository::save)
            .map(savedOnsDadosValoresProgramacaoDiaria -> {
                onsDadosValoresProgramacaoDiariaSearchRepository.index(savedOnsDadosValoresProgramacaoDiaria);
                return savedOnsDadosValoresProgramacaoDiaria;
            });

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                true,
                ENTITY_NAME,
                onsDadosValoresProgramacaoDiariaEntity.getId().toString()
            )
        );
    }

    /**
     * {@code GET  /ons-dados-valores-programacao-diarias} : get all the onsDadosValoresProgramacaoDiarias.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of onsDadosValoresProgramacaoDiarias in body.
     */
    @GetMapping("")
    public List<OnsDadosValoresProgramacaoDiariaEntity> getAllOnsDadosValoresProgramacaoDiarias() {
        LOG.debug("REST request to get all OnsDadosValoresProgramacaoDiarias");
        return onsDadosValoresProgramacaoDiariaRepository.findAll();
    }

    /**
     * {@code GET  /ons-dados-valores-programacao-diarias/:id} : get the "id" onsDadosValoresProgramacaoDiaria.
     *
     * @param id the id of the onsDadosValoresProgramacaoDiariaEntity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the onsDadosValoresProgramacaoDiariaEntity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OnsDadosValoresProgramacaoDiariaEntity> getOnsDadosValoresProgramacaoDiaria(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OnsDadosValoresProgramacaoDiaria : {}", id);
        Optional<OnsDadosValoresProgramacaoDiariaEntity> onsDadosValoresProgramacaoDiariaEntity =
            onsDadosValoresProgramacaoDiariaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(onsDadosValoresProgramacaoDiariaEntity);
    }

    /**
     * {@code DELETE  /ons-dados-valores-programacao-diarias/:id} : delete the "id" onsDadosValoresProgramacaoDiaria.
     *
     * @param id the id of the onsDadosValoresProgramacaoDiariaEntity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnsDadosValoresProgramacaoDiaria(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OnsDadosValoresProgramacaoDiaria : {}", id);
        onsDadosValoresProgramacaoDiariaRepository.deleteById(id);
        onsDadosValoresProgramacaoDiariaSearchRepository.deleteFromIndexById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /ons-dados-valores-programacao-diarias/_search?query=:query} : search for the onsDadosValoresProgramacaoDiaria corresponding
     * to the query.
     *
     * @param query the query of the onsDadosValoresProgramacaoDiaria search.
     * @return the result of the search.
     */
    @GetMapping("/_search")
    public List<OnsDadosValoresProgramacaoDiariaEntity> searchOnsDadosValoresProgramacaoDiarias(@RequestParam("query") String query) {
        LOG.debug("REST request to search OnsDadosValoresProgramacaoDiarias for query {}", query);
        try {
            return StreamSupport.stream(onsDadosValoresProgramacaoDiariaSearchRepository.search(query).spliterator(), false).toList();
        } catch (RuntimeException e) {
            throw ElasticsearchExceptionMapper.mapException(e);
        }
    }
}
