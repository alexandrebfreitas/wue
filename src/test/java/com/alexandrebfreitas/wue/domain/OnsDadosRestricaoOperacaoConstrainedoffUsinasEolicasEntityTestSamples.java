package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntitySample1() {
        return new OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity()
            .id(1L)
            .idSubsistema("idSubsistema1")
            .nomSubsistema("nomSubsistema1")
            .idEstado("idEstado1")
            .nomEstado("nomEstado1")
            .nomUsina("nomUsina1")
            .idOns("idOns1")
            .ceg("ceg1")
            .codRazaorestricao("codRazaorestricao1")
            .codOrigemrestricao("codOrigemrestricao1");
    }

    public static OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntitySample2() {
        return new OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity()
            .id(2L)
            .idSubsistema("idSubsistema2")
            .nomSubsistema("nomSubsistema2")
            .idEstado("idEstado2")
            .nomEstado("nomEstado2")
            .nomUsina("nomUsina2")
            .idOns("idOns2")
            .ceg("ceg2")
            .codRazaorestricao("codRazaorestricao2")
            .codOrigemrestricao("codOrigemrestricao2");
    }

    public static OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntityRandomSampleGenerator() {
        return new OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity()
            .id(longCount.incrementAndGet())
            .idSubsistema(UUID.randomUUID().toString())
            .nomSubsistema(UUID.randomUUID().toString())
            .idEstado(UUID.randomUUID().toString())
            .nomEstado(UUID.randomUUID().toString())
            .nomUsina(UUID.randomUUID().toString())
            .idOns(UUID.randomUUID().toString())
            .ceg(UUID.randomUUID().toString())
            .codRazaorestricao(UUID.randomUUID().toString())
            .codOrigemrestricao(UUID.randomUUID().toString());
    }
}
