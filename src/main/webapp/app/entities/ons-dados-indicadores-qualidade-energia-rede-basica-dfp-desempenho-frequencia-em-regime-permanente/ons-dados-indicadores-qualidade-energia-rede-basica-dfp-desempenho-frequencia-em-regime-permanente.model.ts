import dayjs from 'dayjs/esm';

export interface IOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente {
  id: number;
  idPeriodicidade?: string | null;
  dinReferencia?: dayjs.Dayjs | null;
  numDesvioPermSobre?: number | null;
  numDesvioPermSub?: number | null;
  numDesvioDistSobre?: number | null;
  numDesvioDistSub?: number | null;
  numMinutos?: number | null;
  numVioladodis?: number | null;
  numVioladoperm?: number | null;
  valDfp?: number | null;
}

export type NewOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente = Omit<
  IOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfpDesempenhoFrequenciaEmRegimePermanente,
  'id'
> & { id: null };
