package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity getOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntitySample1() {
        return new OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity()
            .id(1L)
            .idSubsistema("idSubsistema1")
            .nomSubsistema("nomSubsistema1")
            .estadId("estadId1")
            .nomEstado("nomEstado1")
            .idTipousina("idTipousina1")
            .idOnsPequenasusinas("idOnsPequenasusinas1")
            .idOnsUsina("idOnsUsina1")
            .nomPequenasusinas("nomPequenasusinas1")
            .nomUsina("nomUsina1")
            .ceg("ceg1");
    }

    public static OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity getOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntitySample2() {
        return new OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity()
            .id(2L)
            .idSubsistema("idSubsistema2")
            .nomSubsistema("nomSubsistema2")
            .estadId("estadId2")
            .nomEstado("nomEstado2")
            .idTipousina("idTipousina2")
            .idOnsPequenasusinas("idOnsPequenasusinas2")
            .idOnsUsina("idOnsUsina2")
            .nomPequenasusinas("nomPequenasusinas2")
            .nomUsina("nomUsina2")
            .ceg("ceg2");
    }

    public static OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity getOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntityRandomSampleGenerator() {
        return new OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity()
            .id(longCount.incrementAndGet())
            .idSubsistema(UUID.randomUUID().toString())
            .nomSubsistema(UUID.randomUUID().toString())
            .estadId(UUID.randomUUID().toString())
            .nomEstado(UUID.randomUUID().toString())
            .idTipousina(UUID.randomUUID().toString())
            .idOnsPequenasusinas(UUID.randomUUID().toString())
            .idOnsUsina(UUID.randomUUID().toString())
            .nomPequenasusinas(UUID.randomUUID().toString())
            .nomUsina(UUID.randomUUID().toString())
            .ceg(UUID.randomUUID().toString());
    }
}
