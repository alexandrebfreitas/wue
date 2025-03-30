import dayjs from 'dayjs/esm';

export interface IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissao {
  id: number;
  codTipoagregacao?: string | null;
  idPeriodicidade?: string | null;
  nomAgregacao?: string | null;
  dinReferencia?: dayjs.Dayjs | null;
  numLinhasoperacao?: number | null;
  numLinhasvioladas?: number | null;
  valCcal?: number | null;
}

export type NewOnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissao = Omit<
  IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcalControleCarregamentoLinhasTransmissao,
  'id'
> & { id: null };
