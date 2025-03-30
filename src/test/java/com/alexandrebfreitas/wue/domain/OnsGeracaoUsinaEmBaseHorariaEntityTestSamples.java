package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OnsGeracaoUsinaEmBaseHorariaEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OnsGeracaoUsinaEmBaseHorariaEntity getOnsGeracaoUsinaEmBaseHorariaEntitySample1() {
        return new OnsGeracaoUsinaEmBaseHorariaEntity()
            .id(1L)
            .idSubsistema("idSubsistema1")
            .nomSubsistema("nomSubsistema1")
            .idEstado("idEstado1")
            .nomEstado("nomEstado1")
            .codModalidadeoperacao("codModalidadeoperacao1")
            .nomTipousina("nomTipousina1")
            .nomTipocombustivel("nomTipocombustivel1")
            .nomUsina("nomUsina1")
            .idOns("idOns1")
            .ceg("ceg1");
    }

    public static OnsGeracaoUsinaEmBaseHorariaEntity getOnsGeracaoUsinaEmBaseHorariaEntitySample2() {
        return new OnsGeracaoUsinaEmBaseHorariaEntity()
            .id(2L)
            .idSubsistema("idSubsistema2")
            .nomSubsistema("nomSubsistema2")
            .idEstado("idEstado2")
            .nomEstado("nomEstado2")
            .codModalidadeoperacao("codModalidadeoperacao2")
            .nomTipousina("nomTipousina2")
            .nomTipocombustivel("nomTipocombustivel2")
            .nomUsina("nomUsina2")
            .idOns("idOns2")
            .ceg("ceg2");
    }

    public static OnsGeracaoUsinaEmBaseHorariaEntity getOnsGeracaoUsinaEmBaseHorariaEntityRandomSampleGenerator() {
        return new OnsGeracaoUsinaEmBaseHorariaEntity()
            .id(longCount.incrementAndGet())
            .idSubsistema(UUID.randomUUID().toString())
            .nomSubsistema(UUID.randomUUID().toString())
            .idEstado(UUID.randomUUID().toString())
            .nomEstado(UUID.randomUUID().toString())
            .codModalidadeoperacao(UUID.randomUUID().toString())
            .nomTipousina(UUID.randomUUID().toString())
            .nomTipocombustivel(UUID.randomUUID().toString())
            .nomUsina(UUID.randomUUID().toString())
            .idOns(UUID.randomUUID().toString())
            .ceg(UUID.randomUUID().toString());
    }
}
