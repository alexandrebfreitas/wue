import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IOnsCvuUsinaTermicas, NewOnsCvuUsinaTermicas } from '../ons-cvu-usina-termicas.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsCvuUsinaTermicas for edit and NewOnsCvuUsinaTermicasFormGroupInput for create.
 */
type OnsCvuUsinaTermicasFormGroupInput = IOnsCvuUsinaTermicas | PartialWithRequiredKeyOf<NewOnsCvuUsinaTermicas>;

type OnsCvuUsinaTermicasFormDefaults = Pick<NewOnsCvuUsinaTermicas, 'id'>;

type OnsCvuUsinaTermicasFormGroupContent = {
  id: FormControl<IOnsCvuUsinaTermicas['id'] | NewOnsCvuUsinaTermicas['id']>;
  datIniciosemana: FormControl<IOnsCvuUsinaTermicas['datIniciosemana']>;
  datFimsemana: FormControl<IOnsCvuUsinaTermicas['datFimsemana']>;
  anoReferencia: FormControl<IOnsCvuUsinaTermicas['anoReferencia']>;
  mesReferencia: FormControl<IOnsCvuUsinaTermicas['mesReferencia']>;
  numRevisao: FormControl<IOnsCvuUsinaTermicas['numRevisao']>;
  nomSemanaoperativa: FormControl<IOnsCvuUsinaTermicas['nomSemanaoperativa']>;
  codModelos: FormControl<IOnsCvuUsinaTermicas['codModelos']>;
  idSubsistema: FormControl<IOnsCvuUsinaTermicas['idSubsistema']>;
  nomSubsistema: FormControl<IOnsCvuUsinaTermicas['nomSubsistema']>;
  nomUsina: FormControl<IOnsCvuUsinaTermicas['nomUsina']>;
  valCvu: FormControl<IOnsCvuUsinaTermicas['valCvu']>;
};

export type OnsCvuUsinaTermicasFormGroup = FormGroup<OnsCvuUsinaTermicasFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsCvuUsinaTermicasFormService {
  createOnsCvuUsinaTermicasFormGroup(onsCvuUsinaTermicas: OnsCvuUsinaTermicasFormGroupInput = { id: null }): OnsCvuUsinaTermicasFormGroup {
    const onsCvuUsinaTermicasRawValue = {
      ...this.getFormDefaults(),
      ...onsCvuUsinaTermicas,
    };
    return new FormGroup<OnsCvuUsinaTermicasFormGroupContent>({
      id: new FormControl(
        { value: onsCvuUsinaTermicasRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      datIniciosemana: new FormControl(onsCvuUsinaTermicasRawValue.datIniciosemana),
      datFimsemana: new FormControl(onsCvuUsinaTermicasRawValue.datFimsemana),
      anoReferencia: new FormControl(onsCvuUsinaTermicasRawValue.anoReferencia),
      mesReferencia: new FormControl(onsCvuUsinaTermicasRawValue.mesReferencia),
      numRevisao: new FormControl(onsCvuUsinaTermicasRawValue.numRevisao),
      nomSemanaoperativa: new FormControl(onsCvuUsinaTermicasRawValue.nomSemanaoperativa),
      codModelos: new FormControl(onsCvuUsinaTermicasRawValue.codModelos),
      idSubsistema: new FormControl(onsCvuUsinaTermicasRawValue.idSubsistema),
      nomSubsistema: new FormControl(onsCvuUsinaTermicasRawValue.nomSubsistema),
      nomUsina: new FormControl(onsCvuUsinaTermicasRawValue.nomUsina),
      valCvu: new FormControl(onsCvuUsinaTermicasRawValue.valCvu),
    });
  }

  getOnsCvuUsinaTermicas(form: OnsCvuUsinaTermicasFormGroup): IOnsCvuUsinaTermicas | NewOnsCvuUsinaTermicas {
    return form.getRawValue() as IOnsCvuUsinaTermicas | NewOnsCvuUsinaTermicas;
  }

  resetForm(form: OnsCvuUsinaTermicasFormGroup, onsCvuUsinaTermicas: OnsCvuUsinaTermicasFormGroupInput): void {
    const onsCvuUsinaTermicasRawValue = { ...this.getFormDefaults(), ...onsCvuUsinaTermicas };
    form.reset(
      {
        ...onsCvuUsinaTermicasRawValue,
        id: { value: onsCvuUsinaTermicasRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsCvuUsinaTermicasFormDefaults {
    return {
      id: null,
    };
  }
}
