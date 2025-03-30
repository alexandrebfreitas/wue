import dayjs from 'dayjs/esm';

export interface IOnsDadosDisponibilidadeUsinas {
  id: number;
  idSubsistema?: string | null;
  nomSubsistema?: string | null;
  idEstado?: string | null;
  nomEstado?: string | null;
  nomUsina?: string | null;
  idTipousina?: string | null;
  nomTipocombustivel?: string | null;
  idOns?: string | null;
  ceg?: string | null;
  dinInstante?: dayjs.Dayjs | null;
  valPotenciainstalada?: number | null;
  valDispoperacional?: number | null;
  valDispsincronizada?: number | null;
}

export type NewOnsDadosDisponibilidadeUsinas = Omit<IOnsDadosDisponibilidadeUsinas, 'id'> & { id: null };
