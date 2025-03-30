import dayjs from 'dayjs/esm';

export interface IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq {
  id: number;
  dscAgregacao?: string | null;
  codCaracteristica?: string | null;
  dscCaracteristica?: string | null;
  idPeriodicidade?: string | null;
  dinReferencia?: dayjs.Dayjs | null;
  valDreq?: number | null;
  valFreq?: number | null;
}

export type NewOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq = Omit<IOnsIndicadoresConfiabilidadeRedeBasicaDreqFreq, 'id'> & { id: null };
