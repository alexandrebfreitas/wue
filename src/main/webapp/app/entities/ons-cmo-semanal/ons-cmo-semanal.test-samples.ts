import dayjs from 'dayjs/esm';

import { IOnsCmoSemanal, NewOnsCmoSemanal } from './ons-cmo-semanal.model';

export const sampleWithRequiredData: IOnsCmoSemanal = {
  id: 9501,
};

export const sampleWithPartialData: IOnsCmoSemanal = {
  id: 9150,
  valCmoleve: 30828.24,
  valCmomedia: 30280.02,
};

export const sampleWithFullData: IOnsCmoSemanal = {
  id: 31990,
  idSubsistema: 'dock apropos perfection',
  nomSubsistema: 'atop blah retract',
  dinInstante: dayjs('2025-03-29'),
  valCmomediasemanal: 14794.35,
  valCmoleve: 28308.81,
  valCmomedia: 13960.94,
  valCmopesada: 12323.22,
};

export const sampleWithNewData: NewOnsCmoSemanal = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
