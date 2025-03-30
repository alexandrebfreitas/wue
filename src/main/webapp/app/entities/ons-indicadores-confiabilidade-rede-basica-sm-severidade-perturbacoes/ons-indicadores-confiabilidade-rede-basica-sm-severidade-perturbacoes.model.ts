import dayjs from 'dayjs/esm';

export interface IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes {
  id: number;
  dscAgregacao?: string | null;
  codCaracteristica?: string | null;
  dscCaracteristica?: string | null;
  idPeriodicidade?: string | null;
  dinReferencia?: dayjs.Dayjs | null;
  valSm1?: number | null;
  valSm2?: number | null;
  valSm3?: number | null;
  valSm4?: number | null;
  valSm5?: number | null;
}

export type NewOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes = Omit<
  IOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes,
  'id'
> & { id: null };
