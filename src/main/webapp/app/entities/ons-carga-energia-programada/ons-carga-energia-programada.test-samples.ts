import dayjs from 'dayjs/esm';

import { IOnsCargaEnergiaProgramada, NewOnsCargaEnergiaProgramada } from './ons-carga-energia-programada.model';

export const sampleWithRequiredData: IOnsCargaEnergiaProgramada = {
  id: 11270,
};

export const sampleWithPartialData: IOnsCargaEnergiaProgramada = {
  id: 21903,
  datReferencia: dayjs('2025-03-29'),
  dinReferenciautc: dayjs('2025-03-29'),
  valCargaglobalprogramada: 12894.84,
};

export const sampleWithFullData: IOnsCargaEnergiaProgramada = {
  id: 30861,
  codAreacarga: 'funny cruelly refute',
  datReferencia: dayjs('2025-03-29'),
  dinReferenciautc: dayjs('2025-03-29'),
  valCargaglobalprogramada: 9804.19,
};

export const sampleWithNewData: NewOnsCargaEnergiaProgramada = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
