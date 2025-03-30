import dayjs from 'dayjs/esm';

import {
  IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa,
  NewOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa,
} from './ons-dados-indicadores-cumprimento-providencias-ecpa-e-pcpa.model';

export const sampleWithRequiredData: IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa = {
  id: 3224,
};

export const sampleWithPartialData: IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa = {
  id: 20100,
  dscCaracteristica: 'incidentally source inside',
  dinReferencia: dayjs('2025-03-29'),
  numNprcConcluidas: 13980,
  numNpraAntecipadas: 7282,
  valEcpa: 26248.95,
};

export const sampleWithFullData: IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa = {
  id: 32361,
  dscAgregacao: 'neck till',
  dscCaracteristica: 'captain',
  dinReferencia: dayjs('2025-03-29'),
  numNprcConcluidas: 24841,
  numNprpProgramadas: 27964,
  numNpratAtrasadas: 2894,
  numNpraAntecipadas: 25450,
  numNprcpConcluidasPrazo: 27900,
  valEcpa: 16275.65,
  valPcpa: 26484.15,
};

export const sampleWithNewData: NewOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
