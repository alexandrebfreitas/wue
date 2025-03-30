package com.alexandrebfreitas.wue.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntitySample1() {
        return new OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity()
            .id(1L)
            .idSubsistema("idSubsistema1")
            .nomModalidadeoperacao("nomModalidadeoperacao1")
            .idEstado("idEstado1")
            .nomConjuntousina("nomConjuntousina1")
            .nomUsina("nomUsina1")
            .idOns("idOns1")
            .ceg("ceg1");
    }

    public static OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntitySample2() {
        return new OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity()
            .id(2L)
            .idSubsistema("idSubsistema2")
            .nomModalidadeoperacao("nomModalidadeoperacao2")
            .idEstado("idEstado2")
            .nomConjuntousina("nomConjuntousina2")
            .nomUsina("nomUsina2")
            .idOns("idOns2")
            .ceg("ceg2");
    }

    public static OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntityRandomSampleGenerator() {
        return new OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity()
            .id(longCount.incrementAndGet())
            .idSubsistema(UUID.randomUUID().toString())
            .nomModalidadeoperacao(UUID.randomUUID().toString())
            .idEstado(UUID.randomUUID().toString())
            .nomConjuntousina(UUID.randomUUID().toString())
            .nomUsina(UUID.randomUUID().toString())
            .idOns(UUID.randomUUID().toString())
            .ceg(UUID.randomUUID().toString());
    }
}
