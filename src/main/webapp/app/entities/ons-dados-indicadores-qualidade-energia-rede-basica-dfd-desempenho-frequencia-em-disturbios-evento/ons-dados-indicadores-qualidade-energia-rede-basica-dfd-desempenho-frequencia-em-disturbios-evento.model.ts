import dayjs from 'dayjs/esm';

export interface IOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEvento {
  id: number;
  dinReferencia?: dayjs.Dayjs | null;
  dinIniciodesviofreq?: dayjs.Dayjs | null;
  dinFimdesviofreq?: dayjs.Dayjs | null;
  idFaixafrequencia?: string | null;
  nomFaixafrequencia?: string | null;
  valDfd?: number | null;
  valFreqmaxmin?: number | null;
}

export type NewOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEvento = Omit<
  IOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEvento,
  'id'
> & { id: null };
