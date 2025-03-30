import {
  IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas,
  NewOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas,
} from './ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas.model';

export const sampleWithRequiredData: IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas = {
  id: 12662,
};

export const sampleWithPartialData: IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas = {
  id: 6842,
  idSubsistema: 'yippee',
  nomSubsistema: 'tremendously new swathe',
  idTipousina: 'beyond eek from',
  idOnsPequenasusinas: 'electronics',
  nomPequenasusinas: 'pleasant',
  nomUsina: 'jaggedly',
};

export const sampleWithFullData: IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas = {
  id: 24574,
  idSubsistema: 'produce hmph',
  nomSubsistema: 'consequently ignorance yowza',
  estadId: 'toward',
  nomEstado: 'lazily scent best-seller',
  idTipousina: 'whereas recklessly recent',
  idOnsPequenasusinas: 'ew aware',
  idOnsUsina: 'pink ick geez',
  nomPequenasusinas: 'ugh quit',
  nomUsina: 'though utterly',
  ceg: 'even inasmuch',
};

export const sampleWithNewData: NewOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
