import dayjs from 'dayjs/esm';

import {
  IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes,
  NewOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes,
} from './ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes.model';

export const sampleWithRequiredData: IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes = {
  id: 25194,
};

export const sampleWithPartialData: IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes = {
  id: 29693,
  codCaracteristica: 'chase',
  dscCaracteristica: 'safe willfully',
  dinReferencia: dayjs('2025-03-29'),
  valCiper2: 7768.62,
};

export const sampleWithFullData: IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes = {
  id: 19484,
  dscAgregacao: 'other',
  codCaracteristica: 'formal vice',
  dscCaracteristica: 'quietly',
  idPeriodicidade: 'adventurously knowledgeably usefully',
  dinReferencia: dayjs('2025-03-29'),
  valCiper1: 11091.64,
  valCiper2: 22907.45,
  valCiper3: 28048.97,
  valCiper4: 22550.51,
  valCiper5: 5667.67,
};

export const sampleWithNewData: NewOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
