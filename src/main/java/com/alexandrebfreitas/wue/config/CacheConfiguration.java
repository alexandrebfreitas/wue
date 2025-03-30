package com.alexandrebfreitas.wue.config;

import java.time.Duration;
import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;
import org.hibernate.cache.jcache.ConfigSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.*;
import tech.jhipster.config.JHipsterProperties;
import tech.jhipster.config.cache.PrefixedKeyGenerator;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(
                Object.class,
                Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries())
            )
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build()
        );
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.alexandrebfreitas.wue.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.alexandrebfreitas.wue.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.alexandrebfreitas.wue.domain.UserEntity.class.getName());
            createCache(cm, com.alexandrebfreitas.wue.domain.Authority.class.getName());
            createCache(cm, com.alexandrebfreitas.wue.domain.UserEntity.class.getName() + ".authorities");
            createCache(cm, com.alexandrebfreitas.wue.domain.PersistentToken.class.getName());
            createCache(cm, com.alexandrebfreitas.wue.domain.UserEntity.class.getName() + ".persistentTokens");
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsCurvaCargaHorariaEntity.class.getName());
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsEnaDiarioReservatorioEntity.class.getName());
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsCapacidadeTransformacaoRedeBasicaEntity.class.getName());
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsCargaEnergiaMensalEntity.class.getName());
            createCache(
                cm,
                com.alexandrebfreitas.wue.domain.OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasEntity.class.getName()
            );
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsGeracaoTermicaMotivoDespachoEntity.class.getName());
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsEarDiarioReeReservatorioEquivalenteEnergiaEntity.class.getName());
            createCache(
                cm,
                com.alexandrebfreitas.wue.domain.OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanenteEntity.class.getName()
            );
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsFatorCapacidadeGeracaoEolicaESolarEntity.class.getName());
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsModalidadeOperacaoUsinasEntity.class.getName());
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsReservatoriosEntity.class.getName());
            createCache(
                cm,
                com.alexandrebfreitas.wue.domain.OnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicosEntity.class.getName()
            );
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsDadosDisponibilidadeUsinasEntity.class.getName());
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsDadosHidrologicosVolumeEsperaRecomendadoEntity.class.getName());
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpaEntity.class.getName());
            createCache(
                cm,
                com.alexandrebfreitas.wue.domain.OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresEntity.class.getName()
            );
            createCache(
                cm,
                com.alexandrebfreitas.wue.domain.OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasEntity.class.getName()
            );
            createCache(
                cm,
                com.alexandrebfreitas.wue.domain.OnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissaoEntity.class.getName()
            );
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsDadosRelacionamentoEntreConjuntosEUsinasEntity.class.getName());
            createCache(
                cm,
                com.alexandrebfreitas.wue.domain.OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEventoEntity.class.getName()
            );
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsDadosProgramadosElementosFluxoControladoEntity.class.getName());
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsBalancoEnergiaNosSubsistemasEntity.class.getName());
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsCargaEnergiaProgramadaEntity.class.getName());
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsDadosHidraulicosReservatorioBaseHorariaEntity.class.getName());
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsLinhasTransmissaoRedeOperacaoEntity.class.getName());
            createCache(
                cm,
                com.alexandrebfreitas.wue.domain.OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesEntity.class.getName()
            );
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsDadosValoresProgramacaoDiariaEntity.class.getName());
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsCvuUsinaTermicasEntity.class.getName());
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsGeracaoComercialParaExportacaoInternacionalEntity.class.getName());
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsCmoSemanalEntity.class.getName());
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsEarDiarioSubsistemaEntity.class.getName());
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsDadosHidraulicosReservatorioBaseDiariaEntity.class.getName());
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqEntity.class.getName());
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsBalancoEnergiaDessemEntity.class.getName());
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsEnaDiarioBaciaEntity.class.getName());
            createCache(
                cm,
                com.alexandrebfreitas.wue.domain.OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensalEntity.class.getName()
            );
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsEnaDiarioSubsistemaEntity.class.getName());
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsEnaDiarioReeReservatorioEquivalenteEnergiaEntity.class.getName());
            createCache(
                cm,
                com.alexandrebfreitas.wue.domain.OnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnualEntity.class.getName()
            );
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity.class.getName());
            createCache(
                cm,
                com.alexandrebfreitas.wue.domain.OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesEntity.class.getName()
            );
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsDadosIntercambioEnergiaModalidadeEntity.class.getName());
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsCargaEnergiaDiariaEntity.class.getName());
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsCmoSemihorarioEntity.class.getName());
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsCargaEnergiaVerificadaEntity.class.getName());
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasEntity.class.getName());
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsDadosValoresPrevisaoVersusProgramadoEolicasESolaresEntity.class.getName());
            createCache(
                cm,
                com.alexandrebfreitas.wue.domain.OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnualEntity.class.getName()
            );
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsTaxasTeifaETeipEntity.class.getName());
            createCache(
                cm,
                com.alexandrebfreitas.wue.domain.OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsEntity.class.getName()
            );
            createCache(
                cm,
                com.alexandrebfreitas.wue.domain.OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasEntity.class.getName()
            );
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsGeracaoUsinaEmBaseHorariaEntity.class.getName());
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsEarDiarioReservatorioEntity.class.getName());
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsIntercambioSinComOutrosPaisesEntity.class.getName());
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsEquipamentosControleReativosEntity.class.getName());
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsEarDiarioBaciaEntity.class.getName());
            createCache(
                cm,
                com.alexandrebfreitas.wue.domain.OnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024Entity.class.getName()
            );
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsDadosGrandezasFluviometricasEntity.class.getName());
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsIntercambiosEntreSubsistemasEntity.class.getName());
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsOfertasPrecoParaImportacaoEntity.class.getName());
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasEntity.class.getName());
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsCapacidadeInstaladaGeracaoEntity.class.getName());
            createCache(cm, com.alexandrebfreitas.wue.domain.OnsDescontinuadoPrecipitacaoDiariaObservadaEntity.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cache.clear();
        } else {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}
