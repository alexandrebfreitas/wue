import dayjs from 'dayjs/esm';

import {
  IOnsDadosHidrologicosVolumeEsperaRecomendado,
  NewOnsDadosHidrologicosVolumeEsperaRecomendado,
} from './ons-dados-hidrologicos-volume-espera-recomendado.model';

export const sampleWithRequiredData: IOnsDadosHidrologicosVolumeEsperaRecomendado = {
  id: 2056,
};

export const sampleWithPartialData: IOnsDadosHidrologicosVolumeEsperaRecomendado = {
  id: 18745,
  valVolumeespera: 25036.62,
};

export const sampleWithFullData: IOnsDadosHidrologicosVolumeEsperaRecomendado = {
  id: 13086,
  dinInstante: dayjs('2025-03-29'),
  valVolumeespera: 24502.68,
};

export const sampleWithNewData: NewOnsDadosHidrologicosVolumeEsperaRecomendado = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
