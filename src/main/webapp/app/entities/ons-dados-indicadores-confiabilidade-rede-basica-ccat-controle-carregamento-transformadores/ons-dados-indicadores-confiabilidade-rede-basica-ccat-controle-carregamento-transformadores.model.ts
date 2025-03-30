import dayjs from 'dayjs/esm';

export interface IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores {
  id: number;
  codTipoagregacao?: string | null;
  idPeriodicidade?: string | null;
  nomAgregacao?: string | null;
  dinReferencia?: dayjs.Dayjs | null;
  numTransformadoresoperacao?: number | null;
  numTransformadoresviolados?: number | null;
  valCcat?: number | null;
}

export type NewOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores = Omit<
  IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores,
  'id'
> & { id: null };
