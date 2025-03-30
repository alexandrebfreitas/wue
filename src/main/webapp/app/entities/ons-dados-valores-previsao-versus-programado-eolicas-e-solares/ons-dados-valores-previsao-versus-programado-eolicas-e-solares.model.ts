import dayjs from 'dayjs/esm';

export interface IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares {
  id: number;
  datProgramacao?: dayjs.Dayjs | null;
  numPatamar?: number | null;
  codUsinapdp?: string | null;
  nomUsinapdp?: string | null;
  valPrevisao?: number | null;
  valProgramado?: number | null;
}

export type NewOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares = Omit<
  IOnsDadosValoresPrevisaoVersusProgramadoEolicasESolares,
  'id'
> & { id: null };
