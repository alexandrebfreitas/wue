import dayjs from 'dayjs/esm';

import { IOnsCargaEnergiaVerificada, NewOnsCargaEnergiaVerificada } from './ons-carga-energia-verificada.model';

export const sampleWithRequiredData: IOnsCargaEnergiaVerificada = {
  id: 26722,
};

export const sampleWithPartialData: IOnsCargaEnergiaVerificada = {
  id: 21402,
  codAreacarga: 'why somber accidentally',
  datReferencia: dayjs('2025-03-29'),
  dinReferenciautc: dayjs('2025-03-29'),
  valCargaglobalsmmg: 8385.45,
  valCargammgd: 32558.25,
  valCargaglobalcons: 17457.64,
  valCargasupervisionada: 8461.39,
};

export const sampleWithFullData: IOnsCargaEnergiaVerificada = {
  id: 8272,
  codAreacarga: 'ugh abnormally',
  datReferencia: dayjs('2025-03-29'),
  dinReferenciautc: dayjs('2025-03-30'),
  valCargaglobal: 31818.39,
  valCargaglobalsmmg: 32320.67,
  valCargammgd: 27940.05,
  valCargaglobalcons: 21183.87,
  valConsistencia: 25826.68,
  valCargasupervisionada: 31843.47,
  valCarganaosupervisionada: 29905.28,
};

export const sampleWithNewData: NewOnsCargaEnergiaVerificada = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
