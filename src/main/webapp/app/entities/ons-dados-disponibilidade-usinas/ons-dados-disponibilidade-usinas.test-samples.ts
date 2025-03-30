import dayjs from 'dayjs/esm';

import { IOnsDadosDisponibilidadeUsinas, NewOnsDadosDisponibilidadeUsinas } from './ons-dados-disponibilidade-usinas.model';

export const sampleWithRequiredData: IOnsDadosDisponibilidadeUsinas = {
  id: 2041,
};

export const sampleWithPartialData: IOnsDadosDisponibilidadeUsinas = {
  id: 25771,
  idSubsistema: 'tousle duh',
  idEstado: 'doubtfully breakable safe',
  nomUsina: 'delectable',
  idTipousina: 'whoa',
  idOns: 'capitalize',
  ceg: 'sheathe hateful readily',
  valPotenciainstalada: 20730.06,
  valDispoperacional: 10918.87,
  valDispsincronizada: 1736.42,
};

export const sampleWithFullData: IOnsDadosDisponibilidadeUsinas = {
  id: 12441,
  idSubsistema: 'onto why',
  nomSubsistema: 'gladly down',
  idEstado: 'um embody',
  nomEstado: 'afore',
  nomUsina: 'which shrill wrongly',
  idTipousina: 'soon bolster',
  nomTipocombustivel: 'next ill-fated',
  idOns: 'which aw',
  ceg: 'worthy serpentine throughout',
  dinInstante: dayjs('2025-03-29'),
  valPotenciainstalada: 288.68,
  valDispoperacional: 2524.42,
  valDispsincronizada: 17720.37,
};

export const sampleWithNewData: NewOnsDadosDisponibilidadeUsinas = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
