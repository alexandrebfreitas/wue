export interface IOnsDadosHidraulicosReservatorioBaseHoraria {
  id: number;
  valVolumeutil?: number | null;
  valVazaoafluente?: number | null;
  valVazaodefluente?: number | null;
  valVazaoturbinada?: number | null;
  valVazaovertida?: number | null;
  valVazaooutrasestruturas?: number | null;
  valVazaotransferida?: number | null;
  valVazaovertidanaoturbinavel?: number | null;
}

export type NewOnsDadosHidraulicosReservatorioBaseHoraria = Omit<IOnsDadosHidraulicosReservatorioBaseHoraria, 'id'> & { id: null };
