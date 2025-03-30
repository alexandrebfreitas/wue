import dayjs from 'dayjs/esm';

import {
  IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes,
  NewOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes,
} from './ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes.model';

export const sampleWithRequiredData: IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes = {
  id: 15562,
};

export const sampleWithPartialData: IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes = {
  id: 19215,
  idPeriodicidade: 'worth sticky concerning',
  dinReferencia: dayjs('2025-03-29'),
  valSm1: 17034.11,
  valSm4: 30922.17,
};

export const sampleWithFullData: IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes = {
  id: 25062,
  dscAgregacao: 'psst',
  codCaracteristica: 'whereas new quicker',
  dscCaracteristica: 'enhance skeletal clearly',
  idPeriodicidade: 'beside',
  dinReferencia: dayjs('2025-03-29'),
  valSm1: 458.46,
  valSm2: 17296.51,
  valSm3: 14295.98,
  valSm4: 29094.46,
  valSm5: 6223.23,
};

export const sampleWithNewData: NewOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
