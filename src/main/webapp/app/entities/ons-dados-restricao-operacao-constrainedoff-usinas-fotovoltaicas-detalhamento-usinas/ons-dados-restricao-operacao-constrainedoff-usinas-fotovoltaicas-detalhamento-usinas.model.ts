import dayjs from 'dayjs/esm';

export interface IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas {
  id: number;
  idSubsistema?: string | null;
  nomModalidadeoperacao?: string | null;
  idEstado?: string | null;
  nomConjuntousina?: string | null;
  nomUsina?: string | null;
  dinInstante?: dayjs.Dayjs | null;
  idOns?: string | null;
  ceg?: string | null;
  valIrradianciaverificado?: number | null;
  flgDadoirradianciainvalido?: number | null;
  valGeracaoestimada?: number | null;
  valGeracaoverificada?: number | null;
}

export type NewOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas = Omit<
  IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas,
  'id'
> & { id: null };
