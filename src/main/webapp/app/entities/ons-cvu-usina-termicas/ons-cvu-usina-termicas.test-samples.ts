import dayjs from 'dayjs/esm';

import { IOnsCvuUsinaTermicas, NewOnsCvuUsinaTermicas } from './ons-cvu-usina-termicas.model';

export const sampleWithRequiredData: IOnsCvuUsinaTermicas = {
  id: 11957,
};

export const sampleWithPartialData: IOnsCvuUsinaTermicas = {
  id: 8829,
  datIniciosemana: dayjs('2025-03-29'),
  datFimsemana: dayjs('2025-03-29'),
  anoReferencia: 15732,
  mesReferencia: 9490,
  numRevisao: 6424,
  nomSemanaoperativa: 'swelter',
  codModelos: 15866,
  nomSubsistema: 'reclassify',
};

export const sampleWithFullData: IOnsCvuUsinaTermicas = {
  id: 5431,
  datIniciosemana: dayjs('2025-03-29'),
  datFimsemana: dayjs('2025-03-29'),
  anoReferencia: 9510,
  mesReferencia: 7207,
  numRevisao: 32475,
  nomSemanaoperativa: 'whoever out daily',
  codModelos: 26889,
  idSubsistema: 'lazily ugh',
  nomSubsistema: 'yawningly bakeware',
  nomUsina: 'over obnoxiously persecute',
  valCvu: 27878.62,
};

export const sampleWithNewData: NewOnsCvuUsinaTermicas = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
