import dayjs from 'dayjs/esm';

import { IOnsCurvaCargaHoraria, NewOnsCurvaCargaHoraria } from './ons-curva-carga-horaria.model';

export const sampleWithRequiredData: IOnsCurvaCargaHoraria = {
  id: 5357,
};

export const sampleWithPartialData: IOnsCurvaCargaHoraria = {
  id: 11843,
  idSubsistema: 'drug',
  valCargaenergiahomwmed: 15720.34,
};

export const sampleWithFullData: IOnsCurvaCargaHoraria = {
  id: 30647,
  idSubsistema: 'openly delightfully ocelot',
  nomSubsistema: 'that astride quiet',
  dinInstante: dayjs('2025-03-29'),
  valCargaenergiahomwmed: 13019.21,
};

export const sampleWithNewData: NewOnsCurvaCargaHoraria = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
