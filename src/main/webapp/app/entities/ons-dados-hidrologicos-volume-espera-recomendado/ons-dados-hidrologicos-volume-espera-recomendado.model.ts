import dayjs from 'dayjs/esm';

export interface IOnsDadosHidrologicosVolumeEsperaRecomendado {
  id: number;
  dinInstante?: dayjs.Dayjs | null;
  valVolumeespera?: number | null;
}

export type NewOnsDadosHidrologicosVolumeEsperaRecomendado = Omit<IOnsDadosHidrologicosVolumeEsperaRecomendado, 'id'> & { id: null };
