import dayjs from 'dayjs/esm';

export interface IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa {
  id: number;
  dscAgregacao?: string | null;
  dscCaracteristica?: string | null;
  dinReferencia?: dayjs.Dayjs | null;
  numNprcConcluidas?: number | null;
  numNprpProgramadas?: number | null;
  numNpratAtrasadas?: number | null;
  numNpraAntecipadas?: number | null;
  numNprcpConcluidasPrazo?: number | null;
  valEcpa?: number | null;
  valPcpa?: number | null;
}

export type NewOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa = Omit<IOnsDadosIndicadoresCumprimentoProvidenciasEcpaEPcpa, 'id'> & {
  id: null;
};
