import dayjs from 'dayjs/esm';

import { IOnsEarDiarioSubsistema, NewOnsEarDiarioSubsistema } from './ons-ear-diario-subsistema.model';

export const sampleWithRequiredData: IOnsEarDiarioSubsistema = {
  id: 13480,
};

export const sampleWithPartialData: IOnsEarDiarioSubsistema = {
  id: 25383,
  idSubsistema: 'excluding',
  earData: dayjs('2025-03-29'),
  earMaxSubsistema: 8991.17,
  earVerifSubsistemaMwmes: 14564.78,
};

export const sampleWithFullData: IOnsEarDiarioSubsistema = {
  id: 16287,
  idSubsistema: 'mmm',
  nomSubsistema: 'founder fledgling before',
  earData: dayjs('2025-03-29'),
  earMaxSubsistema: 12124.74,
  earVerifSubsistemaMwmes: 26091.37,
  earVerifSubsistemaPercentual: 30270.92,
};

export const sampleWithNewData: NewOnsEarDiarioSubsistema = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
