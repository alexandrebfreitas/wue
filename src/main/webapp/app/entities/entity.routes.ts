import { Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'authority',
    data: { pageTitle: 'wattsUpEnergyApp.adminAuthority.home.title' },
    loadChildren: () => import('./admin/authority/authority.routes'),
  },
  {
    path: 'ons-curva-carga-horaria',
    data: { pageTitle: 'wattsUpEnergyApp.onsCurvaCargaHoraria.home.title' },
    loadChildren: () => import('./ons-curva-carga-horaria/ons-curva-carga-horaria.routes'),
  },
  {
    path: 'ons-ena-diario-reservatorio',
    data: { pageTitle: 'wattsUpEnergyApp.onsEnaDiarioReservatorio.home.title' },
    loadChildren: () => import('./ons-ena-diario-reservatorio/ons-ena-diario-reservatorio.routes'),
  },
  {
    path: 'ons-capacidade-transformacao-rede-basica',
    data: { pageTitle: 'wattsUpEnergyApp.onsCapacidadeTransformacaoRedeBasica.home.title' },
    loadChildren: () => import('./ons-capacidade-transformacao-rede-basica/ons-capacidade-transformacao-rede-basica.routes'),
  },
  {
    path: 'ons-carga-energia-mensal',
    data: { pageTitle: 'wattsUpEnergyApp.onsCargaEnergiaMensal.home.title' },
    loadChildren: () => import('./ons-carga-energia-mensal/ons-carga-energia-mensal.routes'),
  },
  {
    path: 'ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas-detalhamento-usinas',
    data: { pageTitle: 'wattsUpEnergyApp.onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas.home.title' },
    loadChildren: () =>
      import(
        './ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas-detalhamento-usinas/ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas-detalhamento-usinas.routes'
      ),
  },
  {
    path: 'ons-geracao-termica-motivo-despacho',
    data: { pageTitle: 'wattsUpEnergyApp.onsGeracaoTermicaMotivoDespacho.home.title' },
    loadChildren: () => import('./ons-geracao-termica-motivo-despacho/ons-geracao-termica-motivo-despacho.routes'),
  },
  {
    path: 'ons-ear-diario-ree-reservatorio-equivalente-energia',
    data: { pageTitle: 'wattsUpEnergyApp.onsEarDiarioReeReservatorioEquivalenteEnergia.home.title' },
    loadChildren: () =>
      import('./ons-ear-diario-ree-reservatorio-equivalente-energia/ons-ear-diario-ree-reservatorio-equivalente-energia.routes'),
  },
  {
    path: 'ons-dados-indicadores-qualidade-energia-rede-basica-dfp-desempenho-frequencia-em-regime-permanente',
    data: {
      pageTitle: 'wattsUpEnergyApp.onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente.home.title',
    },
    loadChildren: () =>
      import(
        './ons-dados-indicadores-qualidade-energia-rede-basica-dfp-desempenho-frequencia-em-regime-permanente/ons-dados-indicadores-qualidade-energia-rede-basica-dfp-desempenho-frequencia-em-regime-permanente.routes'
      ),
  },
  {
    path: 'ons-fator-capacidade-geracao-eolica-e-solar',
    data: { pageTitle: 'wattsUpEnergyApp.onsFatorCapacidadeGeracaoEolicaESolar.home.title' },
    loadChildren: () => import('./ons-fator-capacidade-geracao-eolica-e-solar/ons-fator-capacidade-geracao-eolica-e-solar.routes'),
  },
  {
    path: 'ons-modalidade-operacao-usinas',
    data: { pageTitle: 'wattsUpEnergyApp.onsModalidadeOperacaoUsinas.home.title' },
    loadChildren: () => import('./ons-modalidade-operacao-usinas/ons-modalidade-operacao-usinas.routes'),
  },
  {
    path: 'ons-reservatorios',
    data: { pageTitle: 'wattsUpEnergyApp.onsReservatorios.home.title' },
    loadChildren: () => import('./ons-reservatorios/ons-reservatorios.routes'),
  },
  {
    path: 'ons-dados-indicadores-confiabilidade-rede-basica-atls-atendimento-aos-limites-sistemicos',
    data: { pageTitle: 'wattsUpEnergyApp.onsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos.home.title' },
    loadChildren: () =>
      import(
        './ons-dados-indicadores-confiabilidade-rede-basica-atls-atendimento-aos-limites-sistemicos/ons-dados-indicadores-confiabilidade-rede-basica-atls-atendimento-aos-limites-sistemicos.routes'
      ),
  },
  {
    path: 'ons-dados-disponibilidade-usinas',
    data: { pageTitle: 'wattsUpEnergyApp.onsDadosDisponibilidadeUsinas.home.title' },
    loadChildren: () => import('./ons-dados-disponibilidade-usinas/ons-dados-disponibilidade-usinas.routes'),
  },
  {
    path: 'ons-dados-hidrologicos-volume-espera-recomendado',
    data: { pageTitle: 'wattsUpEnergyApp.onsDadosHidrologicosVolumeEsperaRecomendado.home.title' },
    loadChildren: () =>
      import('./ons-dados-hidrologicos-volume-espera-recomendado/ons-dados-hidrologicos-volume-espera-recomendado.routes'),
  },
  {
    path: 'ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpa',
    data: { pageTitle: 'wattsUpEnergyApp.onsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa.home.title' },
    loadChildren: () =>
      import(
        './ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpa/ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpa.routes'
      ),
  },
  {
    path: 'ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores',
    data: { pageTitle: 'wattsUpEnergyApp.onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores.home.title' },
    loadChildren: () =>
      import(
        './ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores/ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores.routes'
      ),
  },
  {
    path: 'ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas',
    data: { pageTitle: 'wattsUpEnergyApp.onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas.home.title' },
    loadChildren: () =>
      import(
        './ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas/ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas.routes'
      ),
  },
  {
    path: 'ons-dados-indicadores-confiabilidade-rede-basica-ccal-controle-carregamento-linhas-transmissao',
    data: { pageTitle: 'wattsUpEnergyApp.onsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissao.home.title' },
    loadChildren: () =>
      import(
        './ons-dados-indicadores-confiabilidade-rede-basica-ccal-controle-carregamento-linhas-transmissao/ons-dados-indicadores-confiabilidade-rede-basica-ccal-controle-carregamento-linhas-transmissao.routes'
      ),
  },
  {
    path: 'ons-dados-relacionamento-entre-conjuntos-e-usinas',
    data: { pageTitle: 'wattsUpEnergyApp.onsDadosRelacionamentoEntreConjuntosEUsinas.home.title' },
    loadChildren: () =>
      import('./ons-dados-relacionamento-entre-conjuntos-e-usinas/ons-dados-relacionamento-entre-conjuntos-e-usinas.routes'),
  },
  {
    path: 'ons-dados-indicadores-qualidade-energia-rede-basica-dfd-desempenho-frequencia-em-disturbios-evento',
    data: {
      pageTitle: 'wattsUpEnergyApp.onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEvento.home.title',
    },
    loadChildren: () =>
      import(
        './ons-dados-indicadores-qualidade-energia-rede-basica-dfd-desempenho-frequencia-em-disturbios-evento/ons-dados-indicadores-qualidade-energia-rede-basica-dfd-desempenho-frequencia-em-disturbios-evento.routes'
      ),
  },
  {
    path: 'ons-dados-programados-elementos-fluxo-controlado',
    data: { pageTitle: 'wattsUpEnergyApp.onsDadosProgramadosElementosFluxoControlado.home.title' },
    loadChildren: () =>
      import('./ons-dados-programados-elementos-fluxo-controlado/ons-dados-programados-elementos-fluxo-controlado.routes'),
  },
  {
    path: 'ons-balanco-energia-nos-subsistemas',
    data: { pageTitle: 'wattsUpEnergyApp.onsBalancoEnergiaNosSubsistemas.home.title' },
    loadChildren: () => import('./ons-balanco-energia-nos-subsistemas/ons-balanco-energia-nos-subsistemas.routes'),
  },
  {
    path: 'ons-carga-energia-programada',
    data: { pageTitle: 'wattsUpEnergyApp.onsCargaEnergiaProgramada.home.title' },
    loadChildren: () => import('./ons-carga-energia-programada/ons-carga-energia-programada.routes'),
  },
  {
    path: 'ons-dados-hidraulicos-reservatorio-base-horaria',
    data: { pageTitle: 'wattsUpEnergyApp.onsDadosHidraulicosReservatorioBaseHoraria.home.title' },
    loadChildren: () => import('./ons-dados-hidraulicos-reservatorio-base-horaria/ons-dados-hidraulicos-reservatorio-base-horaria.routes'),
  },
  {
    path: 'ons-linhas-transmissao-rede-operacao',
    data: { pageTitle: 'wattsUpEnergyApp.onsLinhasTransmissaoRedeOperacao.home.title' },
    loadChildren: () => import('./ons-linhas-transmissao-rede-operacao/ons-linhas-transmissao-rede-operacao.routes'),
  },
  {
    path: 'ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes',
    data: { pageTitle: 'wattsUpEnergyApp.onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes.home.title' },
    loadChildren: () =>
      import(
        './ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes/ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes.routes'
      ),
  },
  {
    path: 'ons-dados-valores-programacao-diaria',
    data: { pageTitle: 'wattsUpEnergyApp.onsDadosValoresProgramacaoDiaria.home.title' },
    loadChildren: () => import('./ons-dados-valores-programacao-diaria/ons-dados-valores-programacao-diaria.routes'),
  },
  {
    path: 'ons-cvu-usina-termicas',
    data: { pageTitle: 'wattsUpEnergyApp.onsCvuUsinaTermicas.home.title' },
    loadChildren: () => import('./ons-cvu-usina-termicas/ons-cvu-usina-termicas.routes'),
  },
  {
    path: 'ons-geracao-comercial-para-exportacao-internacional',
    data: { pageTitle: 'wattsUpEnergyApp.onsGeracaoComercialParaExportacaoInternacional.home.title' },
    loadChildren: () =>
      import('./ons-geracao-comercial-para-exportacao-internacional/ons-geracao-comercial-para-exportacao-internacional.routes'),
  },
  {
    path: 'ons-cmo-semanal',
    data: { pageTitle: 'wattsUpEnergyApp.onsCmoSemanal.home.title' },
    loadChildren: () => import('./ons-cmo-semanal/ons-cmo-semanal.routes'),
  },
  {
    path: 'ons-ear-diario-subsistema',
    data: { pageTitle: 'wattsUpEnergyApp.onsEarDiarioSubsistema.home.title' },
    loadChildren: () => import('./ons-ear-diario-subsistema/ons-ear-diario-subsistema.routes'),
  },
  {
    path: 'ons-dados-hidraulicos-reservatorio-base-diaria',
    data: { pageTitle: 'wattsUpEnergyApp.onsDadosHidraulicosReservatorioBaseDiaria.home.title' },
    loadChildren: () => import('./ons-dados-hidraulicos-reservatorio-base-diaria/ons-dados-hidraulicos-reservatorio-base-diaria.routes'),
  },
  {
    path: 'ons-indicadores-confiabilidade-rede-basica-dreq-freq',
    data: { pageTitle: 'wattsUpEnergyApp.onsIndicadoresConfiabilidadeRedeBasicaDreqFreq.home.title' },
    loadChildren: () =>
      import('./ons-indicadores-confiabilidade-rede-basica-dreq-freq/ons-indicadores-confiabilidade-rede-basica-dreq-freq.routes'),
  },
  {
    path: 'ons-balanco-energia-dessem',
    data: { pageTitle: 'wattsUpEnergyApp.onsBalancoEnergiaDessem.home.title' },
    loadChildren: () => import('./ons-balanco-energia-dessem/ons-balanco-energia-dessem.routes'),
  },
  {
    path: 'ons-ena-diario-bacia',
    data: { pageTitle: 'wattsUpEnergyApp.onsEnaDiarioBacia.home.title' },
    loadChildren: () => import('./ons-ena-diario-bacia/ons-ena-diario-bacia.routes'),
  },
  {
    path: 'ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensal',
    data: { pageTitle: 'wattsUpEnergyApp.onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal.home.title' },
    loadChildren: () =>
      import(
        './ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensal/ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-mensal.routes'
      ),
  },
  {
    path: 'ons-ena-diario-subsistema',
    data: { pageTitle: 'wattsUpEnergyApp.onsEnaDiarioSubsistema.home.title' },
    loadChildren: () => import('./ons-ena-diario-subsistema/ons-ena-diario-subsistema.routes'),
  },
  {
    path: 'ons-ena-diario-ree-reservatorio-equivalente-energia',
    data: { pageTitle: 'wattsUpEnergyApp.onsEnaDiarioReeReservatorioEquivalenteEnergia.home.title' },
    loadChildren: () =>
      import('./ons-ena-diario-ree-reservatorio-equivalente-energia/ons-ena-diario-ree-reservatorio-equivalente-energia.routes'),
  },
  {
    path: 'ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anual',
    data: { pageTitle: 'wattsUpEnergyApp.onsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseAnual.home.title' },
    loadChildren: () =>
      import(
        './ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anual/ons-indicadores-desempenho-funcoes-geracao-unidade-geradora-em-base-anual.routes'
      ),
  },
  {
    path: 'ons-importacao-energia-na-modalidade-comercial-bloco',
    data: { pageTitle: 'wattsUpEnergyApp.onsImportacaoEnergiaNaModalidadeComercialBloco.home.title' },
    loadChildren: () =>
      import('./ons-importacao-energia-na-modalidade-comercial-bloco/ons-importacao-energia-na-modalidade-comercial-bloco.routes'),
  },
  {
    path: 'ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes',
    data: { pageTitle: 'wattsUpEnergyApp.onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes.home.title' },
    loadChildren: () =>
      import(
        './ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes/ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes.routes'
      ),
  },
  {
    path: 'ons-dados-intercambio-energia-modalidade',
    data: { pageTitle: 'wattsUpEnergyApp.onsDadosIntercambioEnergiaModalidade.home.title' },
    loadChildren: () => import('./ons-dados-intercambio-energia-modalidade/ons-dados-intercambio-energia-modalidade.routes'),
  },
  {
    path: 'ons-carga-energia-diaria',
    data: { pageTitle: 'wattsUpEnergyApp.onsCargaEnergiaDiaria.home.title' },
    loadChildren: () => import('./ons-carga-energia-diaria/ons-carga-energia-diaria.routes'),
  },
  {
    path: 'ons-cmo-semihorario',
    data: { pageTitle: 'wattsUpEnergyApp.onsCmoSemihorario.home.title' },
    loadChildren: () => import('./ons-cmo-semihorario/ons-cmo-semihorario.routes'),
  },
  {
    path: 'ons-carga-energia-verificada',
    data: { pageTitle: 'wattsUpEnergyApp.onsCargaEnergiaVerificada.home.title' },
    loadChildren: () => import('./ons-carga-energia-verificada/ons-carga-energia-verificada.routes'),
  },
  {
    path: 'ons-dados-restricao-operacao-constrainedoff-usinas-eolicas',
    data: { pageTitle: 'wattsUpEnergyApp.onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas.home.title' },
    loadChildren: () =>
      import(
        './ons-dados-restricao-operacao-constrainedoff-usinas-eolicas/ons-dados-restricao-operacao-constrainedoff-usinas-eolicas.routes'
      ),
  },
  {
    path: 'ons-dados-valores-previsao-versus-programado-eolicas-e-solares',
    data: { pageTitle: 'wattsUpEnergyApp.onsDadosValoresPrevisaoVersusProgramadoEolicasESolares.home.title' },
    loadChildren: () =>
      import(
        './ons-dados-valores-previsao-versus-programado-eolicas-e-solares/ons-dados-valores-previsao-versus-programado-eolicas-e-solares.routes'
      ),
  },
  {
    path: 'ons-dados-indicadores-qualidade-energia-rede-basica-dfd-desempenho-frequencia-em-disturbios-mensal-e-anual',
    data: {
      pageTitle: 'wattsUpEnergyApp.onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnual.home.title',
    },
    loadChildren: () =>
      import(
        './ons-dados-indicadores-qualidade-energia-rede-basica-dfd-desempenho-frequencia-em-disturbios-mensal-e-anual/ons-dados-indicadores-qualidade-energia-rede-basica-dfd-desempenho-frequencia-em-disturbios-mensal-e-anual.routes'
      ),
  },
  {
    path: 'ons-taxas-teifa-e-teip',
    data: { pageTitle: 'wattsUpEnergyApp.onsTaxasTeifaETeip.home.title' },
    loadChildren: () => import('./ons-taxas-teifa-e-teip/ons-taxas-teifa-e-teip.routes'),
  },
  {
    path: 'ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs',
    data: { pageTitle: 'wattsUpEnergyApp.onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs.home.title' },
    loadChildren: () =>
      import(
        './ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs/ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs.routes'
      ),
  },
  {
    path: 'ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas',
    data: { pageTitle: 'wattsUpEnergyApp.onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas.home.title' },
    loadChildren: () =>
      import(
        './ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas/ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas.routes'
      ),
  },
  {
    path: 'ons-geracao-usina-em-base-horaria',
    data: { pageTitle: 'wattsUpEnergyApp.onsGeracaoUsinaEmBaseHoraria.home.title' },
    loadChildren: () => import('./ons-geracao-usina-em-base-horaria/ons-geracao-usina-em-base-horaria.routes'),
  },
  {
    path: 'ons-ear-diario-reservatorio',
    data: { pageTitle: 'wattsUpEnergyApp.onsEarDiarioReservatorio.home.title' },
    loadChildren: () => import('./ons-ear-diario-reservatorio/ons-ear-diario-reservatorio.routes'),
  },
  {
    path: 'ons-intercambio-sin-com-outros-paises',
    data: { pageTitle: 'wattsUpEnergyApp.onsIntercambioSinComOutrosPaises.home.title' },
    loadChildren: () => import('./ons-intercambio-sin-com-outros-paises/ons-intercambio-sin-com-outros-paises.routes'),
  },
  {
    path: 'ons-equipamentos-controle-reativos',
    data: { pageTitle: 'wattsUpEnergyApp.onsEquipamentosControleReativos.home.title' },
    loadChildren: () => import('./ons-equipamentos-controle-reativos/ons-equipamentos-controle-reativos.routes'),
  },
  {
    path: 'ons-ear-diario-bacia',
    data: { pageTitle: 'wattsUpEnergyApp.onsEarDiarioBacia.home.title' },
    loadChildren: () => import('./ons-ear-diario-bacia/ons-ear-diario-bacia.routes'),
  },
  {
    path: 'ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024',
    data: { pageTitle: 'wattsUpEnergyApp.onsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024.home.title' },
    loadChildren: () =>
      import(
        './ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024/ons-dados-historico-despacho-energia-janeiro-2021-a-setembro-2024.routes'
      ),
  },
  {
    path: 'ons-dados-grandezas-fluviometricas',
    data: { pageTitle: 'wattsUpEnergyApp.onsDadosGrandezasFluviometricas.home.title' },
    loadChildren: () => import('./ons-dados-grandezas-fluviometricas/ons-dados-grandezas-fluviometricas.routes'),
  },
  {
    path: 'ons-intercambios-entre-subsistemas',
    data: { pageTitle: 'wattsUpEnergyApp.onsIntercambiosEntreSubsistemas.home.title' },
    loadChildren: () => import('./ons-intercambios-entre-subsistemas/ons-intercambios-entre-subsistemas.routes'),
  },
  {
    path: 'ons-ofertas-preco-para-importacao',
    data: { pageTitle: 'wattsUpEnergyApp.onsOfertasPrecoParaImportacao.home.title' },
    loadChildren: () => import('./ons-ofertas-preco-para-importacao/ons-ofertas-preco-para-importacao.routes'),
  },
  {
    path: 'ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas',
    data: { pageTitle: 'wattsUpEnergyApp.onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas.home.title' },
    loadChildren: () =>
      import(
        './ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas/ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas.routes'
      ),
  },
  {
    path: 'ons-capacidade-instalada-geracao',
    data: { pageTitle: 'wattsUpEnergyApp.onsCapacidadeInstaladaGeracao.home.title' },
    loadChildren: () => import('./ons-capacidade-instalada-geracao/ons-capacidade-instalada-geracao.routes'),
  },
  {
    path: 'ons-descontinuado-precipitacao-diaria-observada',
    data: { pageTitle: 'wattsUpEnergyApp.onsDescontinuadoPrecipitacaoDiariaObservada.home.title' },
    loadChildren: () => import('./ons-descontinuado-precipitacao-diaria-observada/ons-descontinuado-precipitacao-diaria-observada.routes'),
  },
  /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
];

export default routes;
