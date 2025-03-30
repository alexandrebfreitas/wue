import dayjs from 'dayjs/esm';

export interface IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas {
  id: number;
  idSubsistema?: string | null;
  nomSubsistema?: string | null;
  idEstado?: string | null;
  nomEstado?: string | null;
  nomUsina?: string | null;
  idOns?: string | null;
  ceg?: string | null;
  dinInstante?: dayjs.Dayjs | null;
  valGeracao?: number | null;
  valGeracaolimitada?: number | null;
  valDisponibilidade?: number | null;
  valGeracaoreferencia?: number | null;
  valGeracaoreferenciafinal?: number | null;
  codRazaorestricao?: string | null;
  codOrigemrestricao?: string | null;
}

export type NewOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas = Omit<
  IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas,
  'id'
> & { id: null };
