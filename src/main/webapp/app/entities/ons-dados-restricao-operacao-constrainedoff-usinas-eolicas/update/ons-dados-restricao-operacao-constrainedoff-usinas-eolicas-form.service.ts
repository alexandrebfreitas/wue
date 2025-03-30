import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import {
  IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas,
  NewOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas,
} from '../ons-dados-restricao-operacao-constrainedoff-usinas-eolicas.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas for edit and NewOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasFormGroupInput for create.
 */
type OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasFormGroupInput =
  | IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas
  | PartialWithRequiredKeyOf<NewOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas>;

type OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasFormDefaults = Pick<NewOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas, 'id'>;

type OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasFormGroupContent = {
  id: FormControl<
    IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas['id'] | NewOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas['id']
  >;
  idSubsistema: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas['idSubsistema']>;
  nomSubsistema: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas['nomSubsistema']>;
  idEstado: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas['idEstado']>;
  nomEstado: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas['nomEstado']>;
  nomUsina: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas['nomUsina']>;
  idOns: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas['idOns']>;
  ceg: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas['ceg']>;
  dinInstante: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas['dinInstante']>;
  valGeracao: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas['valGeracao']>;
  valGeracaolimitada: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas['valGeracaolimitada']>;
  valDisponibilidade: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas['valDisponibilidade']>;
  valGeracaoreferencia: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas['valGeracaoreferencia']>;
  valGeracaoreferenciafinal: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas['valGeracaoreferenciafinal']>;
  codRazaorestricao: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas['codRazaorestricao']>;
  codOrigemrestricao: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas['codOrigemrestricao']>;
};

export type OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasFormGroup =
  FormGroup<OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasFormService {
  createOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasFormGroup(
    onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas: OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasFormGroupInput = { id: null },
  ): OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasFormGroup {
    const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRawValue = {
      ...this.getFormDefaults(),
      ...onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas,
    };
    return new FormGroup<OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasFormGroupContent>({
      id: new FormControl(
        { value: onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      idSubsistema: new FormControl(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRawValue.idSubsistema),
      nomSubsistema: new FormControl(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRawValue.nomSubsistema),
      idEstado: new FormControl(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRawValue.idEstado),
      nomEstado: new FormControl(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRawValue.nomEstado),
      nomUsina: new FormControl(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRawValue.nomUsina),
      idOns: new FormControl(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRawValue.idOns),
      ceg: new FormControl(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRawValue.ceg),
      dinInstante: new FormControl(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRawValue.dinInstante),
      valGeracao: new FormControl(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRawValue.valGeracao),
      valGeracaolimitada: new FormControl(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRawValue.valGeracaolimitada),
      valDisponibilidade: new FormControl(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRawValue.valDisponibilidade),
      valGeracaoreferencia: new FormControl(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRawValue.valGeracaoreferencia),
      valGeracaoreferenciafinal: new FormControl(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRawValue.valGeracaoreferenciafinal),
      codRazaorestricao: new FormControl(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRawValue.codRazaorestricao),
      codOrigemrestricao: new FormControl(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRawValue.codOrigemrestricao),
    });
  }

  getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas(
    form: OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasFormGroup,
  ): IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas | NewOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas {
    return form.getRawValue() as
      | IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas
      | NewOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicas;
  }

  resetForm(
    form: OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasFormGroup,
    onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas: OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasFormGroupInput,
  ): void {
    const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRawValue = {
      ...this.getFormDefaults(),
      ...onsDadosRestricaoOperacaoConstrainedoffUsinasEolicas,
    };
    form.reset(
      {
        ...onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRawValue,
        id: { value: onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasFormDefaults {
    return {
      id: null,
    };
  }
}
