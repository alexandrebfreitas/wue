import dayjs from 'dayjs/esm';

import {
  IOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnual,
  NewOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnual,
} from './ons-dados-indicadores-qualidade-energia-rede-basica-dfd-desempenho-frequencia-em-disturbios-mensal-e-anual.model';

export const sampleWithRequiredData: IOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnual = {
  id: 7787,
};

export const sampleWithPartialData: IOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnual = {
  id: 25794,
  dinReferencia: dayjs('2025-03-29'),
  idFaixafrequencia: 'boo alongside contractor',
  nomFaixafrequencia: 'because',
};

export const sampleWithFullData: IOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnual = {
  id: 28615,
  idPeriodicidade: 'or ah who',
  dinReferencia: dayjs('2025-03-29'),
  idFaixafrequencia: 'boo',
  nomFaixafrequencia: 'travel per notwithstanding',
  valDfd: 25140.3,
};

export const sampleWithNewData: NewOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnual = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
