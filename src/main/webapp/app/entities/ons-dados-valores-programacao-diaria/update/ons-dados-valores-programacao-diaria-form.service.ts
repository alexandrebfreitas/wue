import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IOnsDadosValoresProgramacaoDiaria, NewOnsDadosValoresProgramacaoDiaria } from '../ons-dados-valores-programacao-diaria.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsDadosValoresProgramacaoDiaria for edit and NewOnsDadosValoresProgramacaoDiariaFormGroupInput for create.
 */
type OnsDadosValoresProgramacaoDiariaFormGroupInput =
  | IOnsDadosValoresProgramacaoDiaria
  | PartialWithRequiredKeyOf<NewOnsDadosValoresProgramacaoDiaria>;

type OnsDadosValoresProgramacaoDiariaFormDefaults = Pick<NewOnsDadosValoresProgramacaoDiaria, 'id'>;

type OnsDadosValoresProgramacaoDiariaFormGroupContent = {
  id: FormControl<IOnsDadosValoresProgramacaoDiaria['id'] | NewOnsDadosValoresProgramacaoDiaria['id']>;
  dinProgramacaodia: FormControl<IOnsDadosValoresProgramacaoDiaria['dinProgramacaodia']>;
  numPatamar: FormControl<IOnsDadosValoresProgramacaoDiaria['numPatamar']>;
  codExibicaousina: FormControl<IOnsDadosValoresProgramacaoDiaria['codExibicaousina']>;
  nomUsina: FormControl<IOnsDadosValoresProgramacaoDiaria['nomUsina']>;
  tipGeracao: FormControl<IOnsDadosValoresProgramacaoDiaria['tipGeracao']>;
  nomModalidadeoperacao: FormControl<IOnsDadosValoresProgramacaoDiaria['nomModalidadeoperacao']>;
  idSubsistema: FormControl<IOnsDadosValoresProgramacaoDiaria['idSubsistema']>;
  nomSubsistema: FormControl<IOnsDadosValoresProgramacaoDiaria['nomSubsistema']>;
  idEstado: FormControl<IOnsDadosValoresProgramacaoDiaria['idEstado']>;
  nomEstado: FormControl<IOnsDadosValoresProgramacaoDiaria['nomEstado']>;
  valGeracaoprogramada: FormControl<IOnsDadosValoresProgramacaoDiaria['valGeracaoprogramada']>;
  valDisponibilidade: FormControl<IOnsDadosValoresProgramacaoDiaria['valDisponibilidade']>;
  valOrdemmerito: FormControl<IOnsDadosValoresProgramacaoDiaria['valOrdemmerito']>;
  valInflexibilidade: FormControl<IOnsDadosValoresProgramacaoDiaria['valInflexibilidade']>;
  valUc: FormControl<IOnsDadosValoresProgramacaoDiaria['valUc']>;
  valRazaoeletrica: FormControl<IOnsDadosValoresProgramacaoDiaria['valRazaoeletrica']>;
  valGeracaoenergetica: FormControl<IOnsDadosValoresProgramacaoDiaria['valGeracaoenergetica']>;
  valGesubgsub: FormControl<IOnsDadosValoresProgramacaoDiaria['valGesubgsub']>;
  valExportacao: FormControl<IOnsDadosValoresProgramacaoDiaria['valExportacao']>;
  valReposicaoexportacao: FormControl<IOnsDadosValoresProgramacaoDiaria['valReposicaoexportacao']>;
  valFaltacombustivel: FormControl<IOnsDadosValoresProgramacaoDiaria['valFaltacombustivel']>;
};

export type OnsDadosValoresProgramacaoDiariaFormGroup = FormGroup<OnsDadosValoresProgramacaoDiariaFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsDadosValoresProgramacaoDiariaFormService {
  createOnsDadosValoresProgramacaoDiariaFormGroup(
    onsDadosValoresProgramacaoDiaria: OnsDadosValoresProgramacaoDiariaFormGroupInput = { id: null },
  ): OnsDadosValoresProgramacaoDiariaFormGroup {
    const onsDadosValoresProgramacaoDiariaRawValue = {
      ...this.getFormDefaults(),
      ...onsDadosValoresProgramacaoDiaria,
    };
    return new FormGroup<OnsDadosValoresProgramacaoDiariaFormGroupContent>({
      id: new FormControl(
        { value: onsDadosValoresProgramacaoDiariaRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      dinProgramacaodia: new FormControl(onsDadosValoresProgramacaoDiariaRawValue.dinProgramacaodia),
      numPatamar: new FormControl(onsDadosValoresProgramacaoDiariaRawValue.numPatamar),
      codExibicaousina: new FormControl(onsDadosValoresProgramacaoDiariaRawValue.codExibicaousina),
      nomUsina: new FormControl(onsDadosValoresProgramacaoDiariaRawValue.nomUsina),
      tipGeracao: new FormControl(onsDadosValoresProgramacaoDiariaRawValue.tipGeracao),
      nomModalidadeoperacao: new FormControl(onsDadosValoresProgramacaoDiariaRawValue.nomModalidadeoperacao),
      idSubsistema: new FormControl(onsDadosValoresProgramacaoDiariaRawValue.idSubsistema),
      nomSubsistema: new FormControl(onsDadosValoresProgramacaoDiariaRawValue.nomSubsistema),
      idEstado: new FormControl(onsDadosValoresProgramacaoDiariaRawValue.idEstado),
      nomEstado: new FormControl(onsDadosValoresProgramacaoDiariaRawValue.nomEstado),
      valGeracaoprogramada: new FormControl(onsDadosValoresProgramacaoDiariaRawValue.valGeracaoprogramada),
      valDisponibilidade: new FormControl(onsDadosValoresProgramacaoDiariaRawValue.valDisponibilidade),
      valOrdemmerito: new FormControl(onsDadosValoresProgramacaoDiariaRawValue.valOrdemmerito),
      valInflexibilidade: new FormControl(onsDadosValoresProgramacaoDiariaRawValue.valInflexibilidade),
      valUc: new FormControl(onsDadosValoresProgramacaoDiariaRawValue.valUc),
      valRazaoeletrica: new FormControl(onsDadosValoresProgramacaoDiariaRawValue.valRazaoeletrica),
      valGeracaoenergetica: new FormControl(onsDadosValoresProgramacaoDiariaRawValue.valGeracaoenergetica),
      valGesubgsub: new FormControl(onsDadosValoresProgramacaoDiariaRawValue.valGesubgsub),
      valExportacao: new FormControl(onsDadosValoresProgramacaoDiariaRawValue.valExportacao),
      valReposicaoexportacao: new FormControl(onsDadosValoresProgramacaoDiariaRawValue.valReposicaoexportacao),
      valFaltacombustivel: new FormControl(onsDadosValoresProgramacaoDiariaRawValue.valFaltacombustivel),
    });
  }

  getOnsDadosValoresProgramacaoDiaria(
    form: OnsDadosValoresProgramacaoDiariaFormGroup,
  ): IOnsDadosValoresProgramacaoDiaria | NewOnsDadosValoresProgramacaoDiaria {
    return form.getRawValue() as IOnsDadosValoresProgramacaoDiaria | NewOnsDadosValoresProgramacaoDiaria;
  }

  resetForm(
    form: OnsDadosValoresProgramacaoDiariaFormGroup,
    onsDadosValoresProgramacaoDiaria: OnsDadosValoresProgramacaoDiariaFormGroupInput,
  ): void {
    const onsDadosValoresProgramacaoDiariaRawValue = { ...this.getFormDefaults(), ...onsDadosValoresProgramacaoDiaria };
    form.reset(
      {
        ...onsDadosValoresProgramacaoDiariaRawValue,
        id: { value: onsDadosValoresProgramacaoDiariaRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsDadosValoresProgramacaoDiariaFormDefaults {
    return {
      id: null,
    };
  }
}
