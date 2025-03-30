import dayjs from 'dayjs/esm';

export interface IOnsEquipamentosControleReativos {
  id: number;
  idSubsistema?: string | null;
  nomSubsistema?: string | null;
  idEstado?: string | null;
  nomEstado?: string | null;
  nomSubestacao?: string | null;
  nomAgenteProprietario?: string | null;
  nomTipoderede?: string | null;
  nomTipoequipamento?: string | null;
  nomEquipamento?: string | null;
  valPotreativanominalMvar?: number | null;
  valLimiteinferiorMvar?: number | null;
  valLimitesuperiorMvar?: number | null;
  datEntradaoperacao?: dayjs.Dayjs | null;
  datDesativacao?: dayjs.Dayjs | null;
  codEquipamento?: string | null;
}

export type NewOnsEquipamentosControleReativos = Omit<IOnsEquipamentosControleReativos, 'id'> & { id: null };
