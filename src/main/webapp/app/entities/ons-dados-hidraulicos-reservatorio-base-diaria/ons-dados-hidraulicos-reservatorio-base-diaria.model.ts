export interface IOnsDadosHidraulicosReservatorioBaseDiaria {
  id: number;
  valNivelmontante?: number | null;
  valNiveljusante?: number | null;
  valVolumeutilcon?: number | null;
  valVazaoafluente?: number | null;
  valVazaoturbinada?: number | null;
  valVazaovertida?: number | null;
  valVazaooutrasestruturas?: number | null;
  valVazaodefluente?: number | null;
  valVazaotransferida?: number | null;
  valVazaonatural?: number | null;
  valVazaoartificial?: number | null;
  valVazaoincremental?: number | null;
  valVazaoevaporacaoliquida?: number | null;
  valVazaousoconsuntivo?: number | null;
}

export type NewOnsDadosHidraulicosReservatorioBaseDiaria = Omit<IOnsDadosHidraulicosReservatorioBaseDiaria, 'id'> & { id: null };
