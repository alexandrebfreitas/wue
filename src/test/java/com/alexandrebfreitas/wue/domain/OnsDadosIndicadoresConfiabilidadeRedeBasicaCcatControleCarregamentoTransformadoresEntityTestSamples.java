package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity getOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntitySample1() {
        return new OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity()
            .id(1L)
            .codTipoagregacao("codTipoagregacao1")
            .idPeriodicidade("idPeriodicidade1")
            .nomAgregacao("nomAgregacao1")
            .numTransformadoresoperacao(1)
            .numTransformadoresviolados(1);
    }

    public static OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity getOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntitySample2() {
        return new OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity()
            .id(2L)
            .codTipoagregacao("codTipoagregacao2")
            .idPeriodicidade("idPeriodicidade2")
            .nomAgregacao("nomAgregacao2")
            .numTransformadoresoperacao(2)
            .numTransformadoresviolados(2);
    }

    public static OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity getOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntityRandomSampleGenerator() {
        return new OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity()
            .id(longCount.incrementAndGet())
            .codTipoagregacao(UUID.randomUUID().toString())
            .idPeriodicidade(UUID.randomUUID().toString())
            .nomAgregacao(UUID.randomUUID().toString())
            .numTransformadoresoperacao(intCount.incrementAndGet())
            .numTransformadoresviolados(intCount.incrementAndGet());
    }
}
