import dayjs from 'dayjs/esm';

import {
  IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas,
  NewOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas,
} from './ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas-detalhamento-usinas.model';

export const sampleWithRequiredData: IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas = {
  id: 13553,
};

export const sampleWithPartialData: IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas = {
  id: 25410,
  idSubsistema: 'gadzooks underneath synthesise',
  nomUsina: 'the what fax',
  idOns: 'greedily hurtful',
  valGeracaoverificada: 7773.95,
};

export const sampleWithFullData: IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas = {
  id: 16072,
  idSubsistema: 'redress',
  nomModalidadeoperacao: 'serene once',
  idEstado: 'knife',
  nomConjuntousina: 'however',
  nomUsina: 'outside seagull',
  dinInstante: dayjs('2025-03-29'),
  idOns: 'lanky',
  ceg: 'back schematise',
  valIrradianciaverificado: 16169.85,
  flgDadoirradianciainvalido: 19668.97,
  valGeracaoestimada: 15512.05,
  valGeracaoverificada: 27884.13,
};

export const sampleWithNewData: NewOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
