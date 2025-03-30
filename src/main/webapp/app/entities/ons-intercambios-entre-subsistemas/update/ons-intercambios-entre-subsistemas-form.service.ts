import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IOnsIntercambiosEntreSubsistemas, NewOnsIntercambiosEntreSubsistemas } from '../ons-intercambios-entre-subsistemas.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsIntercambiosEntreSubsistemas for edit and NewOnsIntercambiosEntreSubsistemasFormGroupInput for create.
 */
type OnsIntercambiosEntreSubsistemasFormGroupInput =
  | IOnsIntercambiosEntreSubsistemas
  | PartialWithRequiredKeyOf<NewOnsIntercambiosEntreSubsistemas>;

type OnsIntercambiosEntreSubsistemasFormDefaults = Pick<NewOnsIntercambiosEntreSubsistemas, 'id'>;

type OnsIntercambiosEntreSubsistemasFormGroupContent = {
  id: FormControl<IOnsIntercambiosEntreSubsistemas['id'] | NewOnsIntercambiosEntreSubsistemas['id']>;
  dinInstante: FormControl<IOnsIntercambiosEntreSubsistemas['dinInstante']>;
  idSubsistemaOrigem: FormControl<IOnsIntercambiosEntreSubsistemas['idSubsistemaOrigem']>;
  nomSubsistemaOrigem: FormControl<IOnsIntercambiosEntreSubsistemas['nomSubsistemaOrigem']>;
  idSubsistemaDestino: FormControl<IOnsIntercambiosEntreSubsistemas['idSubsistemaDestino']>;
  nomSubsistemaDestino: FormControl<IOnsIntercambiosEntreSubsistemas['nomSubsistemaDestino']>;
  valIntercambiomwmed: FormControl<IOnsIntercambiosEntreSubsistemas['valIntercambiomwmed']>;
};

export type OnsIntercambiosEntreSubsistemasFormGroup = FormGroup<OnsIntercambiosEntreSubsistemasFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsIntercambiosEntreSubsistemasFormService {
  createOnsIntercambiosEntreSubsistemasFormGroup(
    onsIntercambiosEntreSubsistemas: OnsIntercambiosEntreSubsistemasFormGroupInput = { id: null },
  ): OnsIntercambiosEntreSubsistemasFormGroup {
    const onsIntercambiosEntreSubsistemasRawValue = {
      ...this.getFormDefaults(),
      ...onsIntercambiosEntreSubsistemas,
    };
    return new FormGroup<OnsIntercambiosEntreSubsistemasFormGroupContent>({
      id: new FormControl(
        { value: onsIntercambiosEntreSubsistemasRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      dinInstante: new FormControl(onsIntercambiosEntreSubsistemasRawValue.dinInstante),
      idSubsistemaOrigem: new FormControl(onsIntercambiosEntreSubsistemasRawValue.idSubsistemaOrigem),
      nomSubsistemaOrigem: new FormControl(onsIntercambiosEntreSubsistemasRawValue.nomSubsistemaOrigem),
      idSubsistemaDestino: new FormControl(onsIntercambiosEntreSubsistemasRawValue.idSubsistemaDestino),
      nomSubsistemaDestino: new FormControl(onsIntercambiosEntreSubsistemasRawValue.nomSubsistemaDestino),
      valIntercambiomwmed: new FormControl(onsIntercambiosEntreSubsistemasRawValue.valIntercambiomwmed),
    });
  }

  getOnsIntercambiosEntreSubsistemas(
    form: OnsIntercambiosEntreSubsistemasFormGroup,
  ): IOnsIntercambiosEntreSubsistemas | NewOnsIntercambiosEntreSubsistemas {
    return form.getRawValue() as IOnsIntercambiosEntreSubsistemas | NewOnsIntercambiosEntreSubsistemas;
  }

  resetForm(
    form: OnsIntercambiosEntreSubsistemasFormGroup,
    onsIntercambiosEntreSubsistemas: OnsIntercambiosEntreSubsistemasFormGroupInput,
  ): void {
    const onsIntercambiosEntreSubsistemasRawValue = { ...this.getFormDefaults(), ...onsIntercambiosEntreSubsistemas };
    form.reset(
      {
        ...onsIntercambiosEntreSubsistemasRawValue,
        id: { value: onsIntercambiosEntreSubsistemasRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsIntercambiosEntreSubsistemasFormDefaults {
    return {
      id: null,
    };
  }
}
