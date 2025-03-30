package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OnsEquipamentosControleReativosEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OnsEquipamentosControleReativosEntity getOnsEquipamentosControleReativosEntitySample1() {
        return new OnsEquipamentosControleReativosEntity()
            .id(1L)
            .idSubsistema("idSubsistema1")
            .nomSubsistema("nomSubsistema1")
            .idEstado("idEstado1")
            .nomEstado("nomEstado1")
            .nomSubestacao("nomSubestacao1")
            .nomAgenteProprietario("nomAgenteProprietario1")
            .nomTipoderede("nomTipoderede1")
            .nomTipoequipamento("nomTipoequipamento1")
            .nomEquipamento("nomEquipamento1")
            .codEquipamento("codEquipamento1");
    }

    public static OnsEquipamentosControleReativosEntity getOnsEquipamentosControleReativosEntitySample2() {
        return new OnsEquipamentosControleReativosEntity()
            .id(2L)
            .idSubsistema("idSubsistema2")
            .nomSubsistema("nomSubsistema2")
            .idEstado("idEstado2")
            .nomEstado("nomEstado2")
            .nomSubestacao("nomSubestacao2")
            .nomAgenteProprietario("nomAgenteProprietario2")
            .nomTipoderede("nomTipoderede2")
            .nomTipoequipamento("nomTipoequipamento2")
            .nomEquipamento("nomEquipamento2")
            .codEquipamento("codEquipamento2");
    }

    public static OnsEquipamentosControleReativosEntity getOnsEquipamentosControleReativosEntityRandomSampleGenerator() {
        return new OnsEquipamentosControleReativosEntity()
            .id(longCount.incrementAndGet())
            .idSubsistema(UUID.randomUUID().toString())
            .nomSubsistema(UUID.randomUUID().toString())
            .idEstado(UUID.randomUUID().toString())
            .nomEstado(UUID.randomUUID().toString())
            .nomSubestacao(UUID.randomUUID().toString())
            .nomAgenteProprietario(UUID.randomUUID().toString())
            .nomTipoderede(UUID.randomUUID().toString())
            .nomTipoequipamento(UUID.randomUUID().toString())
            .nomEquipamento(UUID.randomUUID().toString())
            .codEquipamento(UUID.randomUUID().toString());
    }
}
