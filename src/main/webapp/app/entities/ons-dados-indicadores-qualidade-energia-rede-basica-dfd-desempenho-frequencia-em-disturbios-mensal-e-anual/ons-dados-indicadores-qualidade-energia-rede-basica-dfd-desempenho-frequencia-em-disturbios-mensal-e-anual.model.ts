import dayjs from 'dayjs/esm';

export interface IOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnual {
  id: number;
  idPeriodicidade?: string | null;
  dinReferencia?: dayjs.Dayjs | null;
  idFaixafrequencia?: string | null;
  nomFaixafrequencia?: string | null;
  valDfd?: number | null;
}

export type NewOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnual = Omit<
  IOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosMensalEAnual,
  'id'
> & { id: null };
