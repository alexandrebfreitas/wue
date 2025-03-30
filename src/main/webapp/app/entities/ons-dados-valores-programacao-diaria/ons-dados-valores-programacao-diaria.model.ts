import dayjs from 'dayjs/esm';

export interface IOnsDadosValoresProgramacaoDiaria {
  id: number;
  dinProgramacaodia?: dayjs.Dayjs | null;
  numPatamar?: number | null;
  codExibicaousina?: string | null;
  nomUsina?: string | null;
  tipGeracao?: string | null;
  nomModalidadeoperacao?: string | null;
  idSubsistema?: string | null;
  nomSubsistema?: string | null;
  idEstado?: string | null;
  nomEstado?: string | null;
  valGeracaoprogramada?: number | null;
  valDisponibilidade?: number | null;
  valOrdemmerito?: number | null;
  valInflexibilidade?: number | null;
  valUc?: number | null;
  valRazaoeletrica?: number | null;
  valGeracaoenergetica?: number | null;
  valGesubgsub?: number | null;
  valExportacao?: number | null;
  valReposicaoexportacao?: number | null;
  valFaltacombustivel?: number | null;
}

export type NewOnsDadosValoresProgramacaoDiaria = Omit<IOnsDadosValoresProgramacaoDiaria, 'id'> & { id: null };
