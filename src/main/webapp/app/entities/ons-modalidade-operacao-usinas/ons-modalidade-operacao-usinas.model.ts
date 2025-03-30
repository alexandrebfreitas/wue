export interface IOnsModalidadeOperacaoUsinas {
  id: number;
  nomUsina?: string | null;
  ceg?: string | null;
  nomModalidadeoperacao?: string | null;
  valPotenciaautorizada?: number | null;
  sglCentrooperacao?: string | null;
  nomPontoconexao?: string | null;
  idEstado?: string | null;
  nomEstado?: string | null;
  stsAneel?: string | null;
}

export type NewOnsModalidadeOperacaoUsinas = Omit<IOnsModalidadeOperacaoUsinas, 'id'> & { id: null };
