import dayjs from 'dayjs/esm';

import { IOnsEarDiarioBacia, NewOnsEarDiarioBacia } from './ons-ear-diario-bacia.model';

export const sampleWithRequiredData: IOnsEarDiarioBacia = {
  id: 4264,
};

export const sampleWithPartialData: IOnsEarDiarioBacia = {
  id: 26855,
  nomCurto: 'duh',
  earData: dayjs('2025-03-29'),
  earMaxBacia: 6887.26,
};

export const sampleWithFullData: IOnsEarDiarioBacia = {
  id: 11719,
  nomCurto: 'welcome',
  earData: dayjs('2025-03-29'),
  earMaxBacia: 11804.47,
  earVerifBaciaMwmes: 14467.4,
  earVerifBaciaPercentual: 7808.26,
};

export const sampleWithNewData: NewOnsEarDiarioBacia = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
