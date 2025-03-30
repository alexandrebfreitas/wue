import dayjs from 'dayjs/esm';

export interface IOnsDadosProgramadosElementosFluxoControlado {
  id: number;
  dinProgramacaodia?: dayjs.Dayjs | null;
  numPatamar?: number | null;
  nomElementofluxocontrolado?: string | null;
  dscElementofluxocontrolado?: string | null;
  tipTerminal?: number | null;
  codSubmercado?: string | null;
  valCarga?: number | null;
}

export type NewOnsDadosProgramadosElementosFluxoControlado = Omit<IOnsDadosProgramadosElementosFluxoControlado, 'id'> & { id: null };
