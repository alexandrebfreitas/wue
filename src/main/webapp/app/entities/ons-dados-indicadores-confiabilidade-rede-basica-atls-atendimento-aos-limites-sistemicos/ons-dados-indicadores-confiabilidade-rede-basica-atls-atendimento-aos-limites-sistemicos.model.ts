import dayjs from 'dayjs/esm';

export interface IOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos {
  id: number;
  nomFluxo?: string | null;
  idPeriodicidade?: string | null;
  dinReferencia?: dayjs.Dayjs | null;
  valAtls?: number | null;
  numHorasviolacao?: number | null;
}

export type NewOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos = Omit<
  IOnsDadosIndicadoresConfiabilidadeRedeBasicaAtlsAtendimentoAosLimitesSistemicos,
  'id'
> & { id: null };
