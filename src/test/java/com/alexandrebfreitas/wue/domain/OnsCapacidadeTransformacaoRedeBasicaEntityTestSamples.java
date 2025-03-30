package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class OnsCapacidadeTransformacaoRedeBasicaEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static OnsCapacidadeTransformacaoRedeBasicaEntity getOnsCapacidadeTransformacaoRedeBasicaEntitySample1() {
        return new OnsCapacidadeTransformacaoRedeBasicaEntity()
            .id(1L)
            .idSubsistema("idSubsistema1")
            .nomSubsistema("nomSubsistema1")
            .idEstado("idEstado1")
            .nomEstado("nomEstado1")
            .nomTipotransformador("nomTipotransformador1")
            .nomAgenteproprietario("nomAgenteproprietario1")
            .nomSubestacao("nomSubestacao1")
            .nomTransformador("nomTransformador1")
            .codEquipamento("codEquipamento1")
            .nomTipoderede("nomTipoderede1")
            .numBarraPrimario(1)
            .numBarraSecundario(1);
    }

    public static OnsCapacidadeTransformacaoRedeBasicaEntity getOnsCapacidadeTransformacaoRedeBasicaEntitySample2() {
        return new OnsCapacidadeTransformacaoRedeBasicaEntity()
            .id(2L)
            .idSubsistema("idSubsistema2")
            .nomSubsistema("nomSubsistema2")
            .idEstado("idEstado2")
            .nomEstado("nomEstado2")
            .nomTipotransformador("nomTipotransformador2")
            .nomAgenteproprietario("nomAgenteproprietario2")
            .nomSubestacao("nomSubestacao2")
            .nomTransformador("nomTransformador2")
            .codEquipamento("codEquipamento2")
            .nomTipoderede("nomTipoderede2")
            .numBarraPrimario(2)
            .numBarraSecundario(2);
    }

    public static OnsCapacidadeTransformacaoRedeBasicaEntity getOnsCapacidadeTransformacaoRedeBasicaEntityRandomSampleGenerator() {
        return new OnsCapacidadeTransformacaoRedeBasicaEntity()
            .id(longCount.incrementAndGet())
            .idSubsistema(UUID.randomUUID().toString())
            .nomSubsistema(UUID.randomUUID().toString())
            .idEstado(UUID.randomUUID().toString())
            .nomEstado(UUID.randomUUID().toString())
            .nomTipotransformador(UUID.randomUUID().toString())
            .nomAgenteproprietario(UUID.randomUUID().toString())
            .nomSubestacao(UUID.randomUUID().toString())
            .nomTransformador(UUID.randomUUID().toString())
            .codEquipamento(UUID.randomUUID().toString())
            .nomTipoderede(UUID.randomUUID().toString())
            .numBarraPrimario(intCount.incrementAndGet())
            .numBarraSecundario(intCount.incrementAndGet());
    }
}
