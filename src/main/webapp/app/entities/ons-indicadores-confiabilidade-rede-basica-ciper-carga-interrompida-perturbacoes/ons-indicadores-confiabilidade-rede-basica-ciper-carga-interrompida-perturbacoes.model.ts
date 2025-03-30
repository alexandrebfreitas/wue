import dayjs from 'dayjs/esm';

export interface IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes {
  id: number;
  dscAgregacao?: string | null;
  codCaracteristica?: string | null;
  dscCaracteristica?: string | null;
  idPeriodicidade?: string | null;
  dinReferencia?: dayjs.Dayjs | null;
  valCiper1?: number | null;
  valCiper2?: number | null;
  valCiper3?: number | null;
  valCiper4?: number | null;
  valCiper5?: number | null;
}

export type NewOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes = Omit<
  IOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes,
  'id'
> & { id: null };
