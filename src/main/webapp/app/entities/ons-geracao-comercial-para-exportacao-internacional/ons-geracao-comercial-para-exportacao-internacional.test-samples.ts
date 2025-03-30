import dayjs from 'dayjs/esm';

import {
  IOnsGeracaoComercialParaExportacaoInternacional,
  NewOnsGeracaoComercialParaExportacaoInternacional,
} from './ons-geracao-comercial-para-exportacao-internacional.model';

export const sampleWithRequiredData: IOnsGeracaoComercialParaExportacaoInternacional = {
  id: 22407,
};

export const sampleWithPartialData: IOnsGeracaoComercialParaExportacaoInternacional = {
  id: 27905,
  dinInstante: dayjs('2025-03-29'),
  valModalidadecontratual: 29126.6,
  valModalidadeemergencial: 19086.06,
  valModalidadeoportunidade: 31868.05,
  valModalidadeexcepcional: 7166.8,
};

export const sampleWithFullData: IOnsGeracaoComercialParaExportacaoInternacional = {
  id: 1179,
  nomConversora: 'tray diversity vacantly',
  dinInstante: dayjs('2025-03-30'),
  valModalidadecontratual: 6619.59,
  valModalidadeemergencial: 28445.14,
  valModalidadeoportunidade: 29917.52,
  valModalidadeteste: 31532.73,
  valModalidadeexcepcional: 1097.27,
};

export const sampleWithNewData: NewOnsGeracaoComercialParaExportacaoInternacional = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
