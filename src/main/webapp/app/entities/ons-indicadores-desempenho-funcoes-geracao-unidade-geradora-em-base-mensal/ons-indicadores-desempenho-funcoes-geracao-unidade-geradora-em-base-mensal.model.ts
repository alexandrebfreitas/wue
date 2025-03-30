export interface IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal {
  id: number;
  idSubsistema?: string | null;
  nomSubsistema?: string | null;
  idEstado?: string | null;
  nomEstado?: string | null;
  nomModalidadeoperacao?: string | null;
  nomAgenteproprietario?: string | null;
  idTipousina?: string | null;
  idUsina?: string | null;
  nomUsina?: string | null;
  ceg?: string | null;
  codEquipamento?: string | null;
  numUnidadegeradora?: string | null;
  nomUnidadegeradora?: string | null;
  valPotencia?: number | null;
  valDispf?: number | null;
  valIndisppf?: number | null;
  valIndispff?: number | null;
  valDmdff?: number | null;
  valFdff?: number | null;
  valTdff?: number | null;
}

export type NewOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal = Omit<
  IOnsIndicadoresDesempenhoFuncoesGeracaoUnidadeGeradoraEmBaseMensal,
  'id'
> & { id: null };
