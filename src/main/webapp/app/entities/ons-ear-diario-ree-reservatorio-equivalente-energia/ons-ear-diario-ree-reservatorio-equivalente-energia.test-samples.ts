import dayjs from 'dayjs/esm';

import {
  IOnsEarDiarioReeReservatorioEquivalenteEnergia,
  NewOnsEarDiarioReeReservatorioEquivalenteEnergia,
} from './ons-ear-diario-ree-reservatorio-equivalente-energia.model';

export const sampleWithRequiredData: IOnsEarDiarioReeReservatorioEquivalenteEnergia = {
  id: 26729,
};

export const sampleWithPartialData: IOnsEarDiarioReeReservatorioEquivalenteEnergia = {
  id: 28129,
  nomRee: 'innocent',
  earMaxRee: 16311.74,
  earVerifReeMwmes: 9110.11,
};

export const sampleWithFullData: IOnsEarDiarioReeReservatorioEquivalenteEnergia = {
  id: 26856,
  nomRee: 'and giving',
  earData: dayjs('2025-03-29'),
  earMaxRee: 9162.93,
  earVerifReeMwmes: 20667.86,
  earVerifReePercentual: 7183.88,
};

export const sampleWithNewData: NewOnsEarDiarioReeReservatorioEquivalenteEnergia = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
