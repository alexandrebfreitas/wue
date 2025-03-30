import dayjs from 'dayjs/esm';

import {
  IOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente,
  NewOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente,
} from './ons-dados-indicadores-qualidade-energia-rede-basica-dfp-desempenho-frequencia-em-regime-permanente.model';

export const sampleWithRequiredData: IOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente = {
  id: 18140,
};

export const sampleWithPartialData: IOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente = {
  id: 5647,
  idPeriodicidade: 'amid pfft circa',
  numDesvioPermSobre: 19398,
  numDesvioPermSub: 15489,
  numMinutos: 12196,
  numVioladoperm: 16316,
};

export const sampleWithFullData: IOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente = {
  id: 16704,
  idPeriodicidade: 'notwithstanding gadzooks',
  dinReferencia: dayjs('2025-03-29'),
  numDesvioPermSobre: 19777,
  numDesvioPermSub: 6562,
  numDesvioDistSobre: 4176,
  numDesvioDistSub: 14881,
  numMinutos: 425,
  numVioladodis: 338,
  numVioladoperm: 14745,
  valDfp: 9763.29,
};

export const sampleWithNewData: NewOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
