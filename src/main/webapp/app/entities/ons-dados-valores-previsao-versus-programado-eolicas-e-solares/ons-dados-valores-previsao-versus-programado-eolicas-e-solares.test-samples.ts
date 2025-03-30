import dayjs from 'dayjs/esm';

import {
  IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares,
  NewOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares,
} from './ons-dados-valores-previsao-versus-programado-eolicas-e-solares.model';

export const sampleWithRequiredData: IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares = {
  id: 16219,
};

export const sampleWithPartialData: IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares = {
  id: 7049,
  datProgramacao: dayjs('2025-03-29'),
  numPatamar: 23725,
  codUsinapdp: 'rule eek simple',
  nomUsinapdp: 'boldly plus',
};

export const sampleWithFullData: IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares = {
  id: 23641,
  datProgramacao: dayjs('2025-03-29'),
  numPatamar: 19312,
  codUsinapdp: 'asset beneath coincide',
  nomUsinapdp: 'motionless gah',
  valPrevisao: 17599.2,
  valProgramado: 17884.02,
};

export const sampleWithNewData: NewOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
