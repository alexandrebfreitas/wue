import dayjs from 'dayjs/esm';

export interface IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas {
  id: number;
  idSubsistema?: string | null;
  nomModalidadeoperacao?: string | null;
  idEstado?: string | null;
  nomConjuntousina?: string | null;
  nomUsina?: string | null;
  dinInstante?: dayjs.Dayjs | null;
  idOns?: string | null;
  ceg?: string | null;
  valVentoverificado?: number | null;
  flgDadoventoinvalido?: number | null;
  valGeracaoestimada?: number | null;
  valGeracaoverificada?: number | null;
}

export type NewOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas = Omit<
  IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas,
  'id'
> & { id: null };
