import dayjs from 'dayjs/esm';

import {
  IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissao,
  NewOnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissao,
} from './ons-dados-indicadores-confiabilidade-rede-basica-ccal-controle-carregamento-linhas-transmissao.model';

export const sampleWithRequiredData: IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissao = {
  id: 32289,
};

export const sampleWithPartialData: IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissao = {
  id: 31113,
  idPeriodicidade: 'consequently ugh',
  nomAgregacao: 'outrun creaking mortally',
  dinReferencia: dayjs('2025-03-29'),
  numLinhasvioladas: 12774,
};

export const sampleWithFullData: IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissao = {
  id: 27404,
  codTipoagregacao: 'the corporation besides',
  idPeriodicidade: 'cloudy',
  nomAgregacao: 'vivacious status whoa',
  dinReferencia: dayjs('2025-03-29'),
  numLinhasoperacao: 8574,
  numLinhasvioladas: 3780,
  valCcal: 24431.03,
};

export const sampleWithNewData: NewOnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissao = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
