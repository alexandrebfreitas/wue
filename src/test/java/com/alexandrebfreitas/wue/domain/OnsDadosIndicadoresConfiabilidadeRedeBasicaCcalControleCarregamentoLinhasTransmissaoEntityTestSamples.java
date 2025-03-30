package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class OnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static OnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoEntity getOnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoEntitySample1() {
        return new OnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoEntity()
            .id(1L)
            .codTipoagregacao("codTipoagregacao1")
            .idPeriodicidade("idPeriodicidade1")
            .nomAgregacao("nomAgregacao1")
            .numLinhasoperacao(1)
            .numLinhasvioladas(1);
    }

    public static OnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoEntity getOnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoEntitySample2() {
        return new OnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoEntity()
            .id(2L)
            .codTipoagregacao("codTipoagregacao2")
            .idPeriodicidade("idPeriodicidade2")
            .nomAgregacao("nomAgregacao2")
            .numLinhasoperacao(2)
            .numLinhasvioladas(2);
    }

    public static OnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoEntity getOnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoEntityRandomSampleGenerator() {
        return new OnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoEntity()
            .id(longCount.incrementAndGet())
            .codTipoagregacao(UUID.randomUUID().toString())
            .idPeriodicidade(UUID.randomUUID().toString())
            .nomAgregacao(UUID.randomUUID().toString())
            .numLinhasoperacao(intCount.incrementAndGet())
            .numLinhasvioladas(intCount.incrementAndGet());
    }
}
