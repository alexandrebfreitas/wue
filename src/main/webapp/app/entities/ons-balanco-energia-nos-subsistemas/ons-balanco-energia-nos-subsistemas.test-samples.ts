import dayjs from 'dayjs/esm';

import { IOnsBalancoEnergiaNosSubsistemas, NewOnsBalancoEnergiaNosSubsistemas } from './ons-balanco-energia-nos-subsistemas.model';

export const sampleWithRequiredData: IOnsBalancoEnergiaNosSubsistemas = {
  id: 9165,
};

export const sampleWithPartialData: IOnsBalancoEnergiaNosSubsistemas = {
  id: 28664,
  dinInstante: dayjs('2025-03-29'),
  valGertermica: 2888.88,
  valCarga: 10494.72,
  valIntercambio: 31909.59,
};

export const sampleWithFullData: IOnsBalancoEnergiaNosSubsistemas = {
  id: 21993,
  idSubsistema: 'dirty',
  nomSubsistema: 'tender',
  dinInstante: dayjs('2025-03-29'),
  valGerhidraulica: 4140.02,
  valGertermica: 1300.86,
  valGereolica: 29565.69,
  valGersolar: 26693.72,
  valCarga: 27799.78,
  valIntercambio: 8949.5,
};

export const sampleWithNewData: NewOnsBalancoEnergiaNosSubsistemas = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
