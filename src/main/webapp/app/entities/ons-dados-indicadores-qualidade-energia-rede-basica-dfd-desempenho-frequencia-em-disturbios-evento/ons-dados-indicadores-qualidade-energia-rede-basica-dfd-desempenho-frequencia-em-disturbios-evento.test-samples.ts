import dayjs from 'dayjs/esm';

import {
  IOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEvento,
  NewOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEvento,
} from './ons-dados-indicadores-qualidade-energia-rede-basica-dfd-desempenho-frequencia-em-disturbios-evento.model';

export const sampleWithRequiredData: IOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEvento = {
  id: 28128,
};

export const sampleWithPartialData: IOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEvento = {
  id: 14527,
  dinReferencia: dayjs('2025-03-29'),
  dinIniciodesviofreq: dayjs('2025-03-29'),
  valFreqmaxmin: 8443.86,
};

export const sampleWithFullData: IOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEvento = {
  id: 30089,
  dinReferencia: dayjs('2025-03-29'),
  dinIniciodesviofreq: dayjs('2025-03-29'),
  dinFimdesviofreq: dayjs('2025-03-29'),
  idFaixafrequencia: 'boohoo atop',
  nomFaixafrequencia: 'drat omelet baggy',
  valDfd: 29503,
  valFreqmaxmin: 11692.78,
};

export const sampleWithNewData: NewOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEvento = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
