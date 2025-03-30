import dayjs from 'dayjs/esm';

import {
  IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq,
  NewOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq,
} from './ons-indicadores-confiabilidade-rede-basica-dreq-freq.model';

export const sampleWithRequiredData: IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq = {
  id: 18145,
};

export const sampleWithPartialData: IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq = {
  id: 395,
  codCaracteristica: 'brr honored',
  dinReferencia: dayjs('2025-03-29'),
  valDreq: 23245.45,
  valFreq: 22984.47,
};

export const sampleWithFullData: IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq = {
  id: 8434,
  dscAgregacao: 'scar meanwhile weary',
  codCaracteristica: 'unit meh about',
  dscCaracteristica: 'pile',
  idPeriodicidade: 'bleak whether',
  dinReferencia: dayjs('2025-03-29'),
  valDreq: 31994.25,
  valFreq: 24563.31,
};

export const sampleWithNewData: NewOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
