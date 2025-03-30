import dayjs from 'dayjs/esm';

export interface IOnsDadosIntercambioEnergiaModalidade {
  id: number;
  nomConversora?: string | null;
  dinInstante?: dayjs.Dayjs | null;
  valModalidadecontratual?: number | null;
  valModalidadeemergencial?: number | null;
  valModalidadeoportunidade?: number | null;
  valModalidadeteste?: number | null;
  valModalidadeexcepcional?: number | null;
}

export type NewOnsDadosIntercambioEnergiaModalidade = Omit<IOnsDadosIntercambioEnergiaModalidade, 'id'> & { id: null };
