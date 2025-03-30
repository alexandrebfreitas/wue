import dayjs from 'dayjs/esm';

import { IOnsCargaEnergiaMensal, NewOnsCargaEnergiaMensal } from './ons-carga-energia-mensal.model';

export const sampleWithRequiredData: IOnsCargaEnergiaMensal = {
  id: 4268,
};

export const sampleWithPartialData: IOnsCargaEnergiaMensal = {
  id: 8800,
  valCargaenergiamwmed: 17902.08,
};

export const sampleWithFullData: IOnsCargaEnergiaMensal = {
  id: 8066,
  idSubsistema: 'affect tapioca',
  nomSubsistema: 'helpful gee yahoo',
  dinInstante: dayjs('2025-03-29'),
  valCargaenergiamwmed: 7983.5,
};

export const sampleWithNewData: NewOnsCargaEnergiaMensal = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
