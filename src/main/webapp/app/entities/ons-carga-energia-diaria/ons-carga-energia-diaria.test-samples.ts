import dayjs from 'dayjs/esm';

import { IOnsCargaEnergiaDiaria, NewOnsCargaEnergiaDiaria } from './ons-carga-energia-diaria.model';

export const sampleWithRequiredData: IOnsCargaEnergiaDiaria = {
  id: 6937,
};

export const sampleWithPartialData: IOnsCargaEnergiaDiaria = {
  id: 21475,
  idSubsistema: 'woot deploy hmph',
  nomSubsistema: 'yowza',
  valCargaenergiamwmed: 17978.25,
};

export const sampleWithFullData: IOnsCargaEnergiaDiaria = {
  id: 18135,
  idSubsistema: 'alongside dock why',
  nomSubsistema: 'yahoo',
  dinInstante: dayjs('2025-03-29'),
  valCargaenergiamwmed: 26975.51,
};

export const sampleWithNewData: NewOnsCargaEnergiaDiaria = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
