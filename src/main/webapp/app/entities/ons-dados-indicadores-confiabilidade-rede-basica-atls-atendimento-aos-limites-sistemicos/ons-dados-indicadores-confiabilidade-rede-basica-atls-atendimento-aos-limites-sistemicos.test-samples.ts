import dayjs from 'dayjs/esm';

import {
  IOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos,
  NewOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos,
} from './ons-dados-indicadores-confiabilidade-rede-basica-atls-atendimento-aos-limites-sistemicos.model';

export const sampleWithRequiredData: IOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos = {
  id: 29085,
};

export const sampleWithPartialData: IOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos = {
  id: 31153,
  nomFluxo: 'drat lest',
  numHorasviolacao: 25024,
};

export const sampleWithFullData: IOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos = {
  id: 12598,
  nomFluxo: 'enrage',
  idPeriodicidade: 'waft term versus',
  dinReferencia: dayjs('2025-03-29'),
  valAtls: 16815.66,
  numHorasviolacao: 17719,
};

export const sampleWithNewData: NewOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
