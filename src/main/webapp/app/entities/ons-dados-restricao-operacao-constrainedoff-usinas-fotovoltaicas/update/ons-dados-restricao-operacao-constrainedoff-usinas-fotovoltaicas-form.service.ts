import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import {
  IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas,
  NewOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas,
} from '../ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas for edit and NewOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasFormGroupInput for create.
 */
type OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasFormGroupInput =
  | IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas
  | PartialWithRequiredKeyOf<NewOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas>;

type OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasFormDefaults = Pick<
  NewOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas,
  'id'
>;

type OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasFormGroupContent = {
  id: FormControl<
    IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas['id'] | NewOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas['id']
  >;
  idSubsistema: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas['idSubsistema']>;
  nomSubsistema: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas['nomSubsistema']>;
  idEstado: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas['idEstado']>;
  nomEstado: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas['nomEstado']>;
  nomUsina: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas['nomUsina']>;
  idOns: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas['idOns']>;
  ceg: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas['ceg']>;
  dinInstante: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas['dinInstante']>;
  valGeracao: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas['valGeracao']>;
  valGeracaolimitada: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas['valGeracaolimitada']>;
  valDisponibilidade: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas['valDisponibilidade']>;
  valGeracaoreferencia: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas['valGeracaoreferencia']>;
  valGeracaoreferenciafinal: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas['valGeracaoreferenciafinal']>;
  codRazaorestricao: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas['codRazaorestricao']>;
  codOrigemrestricao: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas['codOrigemrestricao']>;
};

export type OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasFormGroup =
  FormGroup<OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasFormService {
  createOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasFormGroup(
    onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas: OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasFormGroupInput = {
      id: null,
    },
  ): OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasFormGroup {
    const onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRawValue = {
      ...this.getFormDefaults(),
      ...onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas,
    };
    return new FormGroup<OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasFormGroupContent>({
      id: new FormControl(
        { value: onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      idSubsistema: new FormControl(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRawValue.idSubsistema),
      nomSubsistema: new FormControl(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRawValue.nomSubsistema),
      idEstado: new FormControl(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRawValue.idEstado),
      nomEstado: new FormControl(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRawValue.nomEstado),
      nomUsina: new FormControl(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRawValue.nomUsina),
      idOns: new FormControl(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRawValue.idOns),
      ceg: new FormControl(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRawValue.ceg),
      dinInstante: new FormControl(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRawValue.dinInstante),
      valGeracao: new FormControl(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRawValue.valGeracao),
      valGeracaolimitada: new FormControl(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRawValue.valGeracaolimitada),
      valDisponibilidade: new FormControl(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRawValue.valDisponibilidade),
      valGeracaoreferencia: new FormControl(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRawValue.valGeracaoreferencia),
      valGeracaoreferenciafinal: new FormControl(
        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRawValue.valGeracaoreferenciafinal,
      ),
      codRazaorestricao: new FormControl(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRawValue.codRazaorestricao),
      codOrigemrestricao: new FormControl(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRawValue.codOrigemrestricao),
    });
  }

  getOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas(
    form: OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasFormGroup,
  ): IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas | NewOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas {
    return form.getRawValue() as
      | IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas
      | NewOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas;
  }

  resetForm(
    form: OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasFormGroup,
    onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas: OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasFormGroupInput,
  ): void {
    const onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRawValue = {
      ...this.getFormDefaults(),
      ...onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicas,
    };
    form.reset(
      {
        ...onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRawValue,
        id: { value: onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasFormDefaults {
    return {
      id: null,
    };
  }
}
