import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IOnsDadosDisponibilidadeUsinas, NewOnsDadosDisponibilidadeUsinas } from '../ons-dados-disponibilidade-usinas.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsDadosDisponibilidadeUsinas for edit and NewOnsDadosDisponibilidadeUsinasFormGroupInput for create.
 */
type OnsDadosDisponibilidadeUsinasFormGroupInput =
  | IOnsDadosDisponibilidadeUsinas
  | PartialWithRequiredKeyOf<NewOnsDadosDisponibilidadeUsinas>;

type OnsDadosDisponibilidadeUsinasFormDefaults = Pick<NewOnsDadosDisponibilidadeUsinas, 'id'>;

type OnsDadosDisponibilidadeUsinasFormGroupContent = {
  id: FormControl<IOnsDadosDisponibilidadeUsinas['id'] | NewOnsDadosDisponibilidadeUsinas['id']>;
  idSubsistema: FormControl<IOnsDadosDisponibilidadeUsinas['idSubsistema']>;
  nomSubsistema: FormControl<IOnsDadosDisponibilidadeUsinas['nomSubsistema']>;
  idEstado: FormControl<IOnsDadosDisponibilidadeUsinas['idEstado']>;
  nomEstado: FormControl<IOnsDadosDisponibilidadeUsinas['nomEstado']>;
  nomUsina: FormControl<IOnsDadosDisponibilidadeUsinas['nomUsina']>;
  idTipousina: FormControl<IOnsDadosDisponibilidadeUsinas['idTipousina']>;
  nomTipocombustivel: FormControl<IOnsDadosDisponibilidadeUsinas['nomTipocombustivel']>;
  idOns: FormControl<IOnsDadosDisponibilidadeUsinas['idOns']>;
  ceg: FormControl<IOnsDadosDisponibilidadeUsinas['ceg']>;
  dinInstante: FormControl<IOnsDadosDisponibilidadeUsinas['dinInstante']>;
  valPotenciainstalada: FormControl<IOnsDadosDisponibilidadeUsinas['valPotenciainstalada']>;
  valDispoperacional: FormControl<IOnsDadosDisponibilidadeUsinas['valDispoperacional']>;
  valDispsincronizada: FormControl<IOnsDadosDisponibilidadeUsinas['valDispsincronizada']>;
};

export type OnsDadosDisponibilidadeUsinasFormGroup = FormGroup<OnsDadosDisponibilidadeUsinasFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsDadosDisponibilidadeUsinasFormService {
  createOnsDadosDisponibilidadeUsinasFormGroup(
    onsDadosDisponibilidadeUsinas: OnsDadosDisponibilidadeUsinasFormGroupInput = { id: null },
  ): OnsDadosDisponibilidadeUsinasFormGroup {
    const onsDadosDisponibilidadeUsinasRawValue = {
      ...this.getFormDefaults(),
      ...onsDadosDisponibilidadeUsinas,
    };
    return new FormGroup<OnsDadosDisponibilidadeUsinasFormGroupContent>({
      id: new FormControl(
        { value: onsDadosDisponibilidadeUsinasRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      idSubsistema: new FormControl(onsDadosDisponibilidadeUsinasRawValue.idSubsistema),
      nomSubsistema: new FormControl(onsDadosDisponibilidadeUsinasRawValue.nomSubsistema),
      idEstado: new FormControl(onsDadosDisponibilidadeUsinasRawValue.idEstado),
      nomEstado: new FormControl(onsDadosDisponibilidadeUsinasRawValue.nomEstado),
      nomUsina: new FormControl(onsDadosDisponibilidadeUsinasRawValue.nomUsina),
      idTipousina: new FormControl(onsDadosDisponibilidadeUsinasRawValue.idTipousina),
      nomTipocombustivel: new FormControl(onsDadosDisponibilidadeUsinasRawValue.nomTipocombustivel),
      idOns: new FormControl(onsDadosDisponibilidadeUsinasRawValue.idOns),
      ceg: new FormControl(onsDadosDisponibilidadeUsinasRawValue.ceg),
      dinInstante: new FormControl(onsDadosDisponibilidadeUsinasRawValue.dinInstante),
      valPotenciainstalada: new FormControl(onsDadosDisponibilidadeUsinasRawValue.valPotenciainstalada),
      valDispoperacional: new FormControl(onsDadosDisponibilidadeUsinasRawValue.valDispoperacional),
      valDispsincronizada: new FormControl(onsDadosDisponibilidadeUsinasRawValue.valDispsincronizada),
    });
  }

  getOnsDadosDisponibilidadeUsinas(
    form: OnsDadosDisponibilidadeUsinasFormGroup,
  ): IOnsDadosDisponibilidadeUsinas | NewOnsDadosDisponibilidadeUsinas {
    return form.getRawValue() as IOnsDadosDisponibilidadeUsinas | NewOnsDadosDisponibilidadeUsinas;
  }

  resetForm(
    form: OnsDadosDisponibilidadeUsinasFormGroup,
    onsDadosDisponibilidadeUsinas: OnsDadosDisponibilidadeUsinasFormGroupInput,
  ): void {
    const onsDadosDisponibilidadeUsinasRawValue = { ...this.getFormDefaults(), ...onsDadosDisponibilidadeUsinas };
    form.reset(
      {
        ...onsDadosDisponibilidadeUsinasRawValue,
        id: { value: onsDadosDisponibilidadeUsinasRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsDadosDisponibilidadeUsinasFormDefaults {
    return {
      id: null,
    };
  }
}
