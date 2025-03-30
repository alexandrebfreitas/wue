export interface IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas {
  id: number;
  idSubsistema?: string | null;
  nomSubsistema?: string | null;
  estadId?: string | null;
  nomEstado?: string | null;
  idTipousina?: string | null;
  idOnsPequenasusinas?: string | null;
  idOnsUsina?: string | null;
  nomPequenasusinas?: string | null;
  nomUsina?: string | null;
  ceg?: string | null;
}

export type NewOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas = Omit<
  IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas,
  'id'
> & { id: null };
