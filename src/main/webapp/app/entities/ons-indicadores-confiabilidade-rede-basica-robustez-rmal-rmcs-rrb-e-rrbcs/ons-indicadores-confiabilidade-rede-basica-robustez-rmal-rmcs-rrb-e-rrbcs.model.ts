import dayjs from 'dayjs/esm';

export interface IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs {
  id: number;
  codIndicador?: string | null;
  dscAgregacao?: string | null;
  codCaracteristica?: string | null;
  dscCaracteristica?: string | null;
  idPeriodicidade?: string | null;
  dinReferencia?: dayjs.Dayjs | null;
  valIndicador?: number | null;
  numPerturbacoes?: number | null;
  numPerturbacoescortecarga?: number | null;
  numPerturbacoescortecarga0a50mw?: number | null;
  numPerturbacoescortecarga50a100mw?: number | null;
  numPerturbacoescortecargaMaior100mw?: number | null;
}

export type NewOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs = Omit<
  IOnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs,
  'id'
> & { id: null };
