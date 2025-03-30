import dayjs from 'dayjs/esm';

import { IOnsGeracaoUsinaEmBaseHoraria, NewOnsGeracaoUsinaEmBaseHoraria } from './ons-geracao-usina-em-base-horaria.model';

export const sampleWithRequiredData: IOnsGeracaoUsinaEmBaseHoraria = {
  id: 23924,
};

export const sampleWithPartialData: IOnsGeracaoUsinaEmBaseHoraria = {
  id: 24760,
  idSubsistema: 'decent censor remarkable',
  nomEstado: 'fen debut',
  nomTipousina: 'instead hm whether',
  idOns: 'spiffy officially',
  ceg: 'duh roger',
  valGeracao: 26917.47,
};

export const sampleWithFullData: IOnsGeracaoUsinaEmBaseHoraria = {
  id: 20221,
  dinInstante: dayjs('2025-03-29'),
  idSubsistema: 'considering doubtfully',
  nomSubsistema: 'yum polarisation',
  idEstado: 'repossess yum',
  nomEstado: 'bah a',
  codModalidadeoperacao: 'reflate loosely',
  nomTipousina: 'around concerned',
  nomTipocombustivel: 'exasperation gadzooks wearily',
  nomUsina: 'menacing excepting',
  idOns: 'resort',
  ceg: 'federate',
  valGeracao: 3155.54,
};

export const sampleWithNewData: NewOnsGeracaoUsinaEmBaseHoraria = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
