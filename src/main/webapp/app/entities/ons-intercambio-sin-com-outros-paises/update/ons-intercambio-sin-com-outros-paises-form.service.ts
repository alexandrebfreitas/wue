import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IOnsIntercambioSinComOutrosPaises, NewOnsIntercambioSinComOutrosPaises } from '../ons-intercambio-sin-com-outros-paises.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsIntercambioSinComOutrosPaises for edit and NewOnsIntercambioSinComOutrosPaisesFormGroupInput for create.
 */
type OnsIntercambioSinComOutrosPaisesFormGroupInput =
  | IOnsIntercambioSinComOutrosPaises
  | PartialWithRequiredKeyOf<NewOnsIntercambioSinComOutrosPaises>;

type OnsIntercambioSinComOutrosPaisesFormDefaults = Pick<NewOnsIntercambioSinComOutrosPaises, 'id'>;

type OnsIntercambioSinComOutrosPaisesFormGroupContent = {
  id: FormControl<IOnsIntercambioSinComOutrosPaises['id'] | NewOnsIntercambioSinComOutrosPaises['id']>;
  dinInstante: FormControl<IOnsIntercambioSinComOutrosPaises['dinInstante']>;
  nomPaisdestino: FormControl<IOnsIntercambioSinComOutrosPaises['nomPaisdestino']>;
  valIntercambiomwmed: FormControl<IOnsIntercambioSinComOutrosPaises['valIntercambiomwmed']>;
};

export type OnsIntercambioSinComOutrosPaisesFormGroup = FormGroup<OnsIntercambioSinComOutrosPaisesFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsIntercambioSinComOutrosPaisesFormService {
  createOnsIntercambioSinComOutrosPaisesFormGroup(
    onsIntercambioSinComOutrosPaises: OnsIntercambioSinComOutrosPaisesFormGroupInput = { id: null },
  ): OnsIntercambioSinComOutrosPaisesFormGroup {
    const onsIntercambioSinComOutrosPaisesRawValue = {
      ...this.getFormDefaults(),
      ...onsIntercambioSinComOutrosPaises,
    };
    return new FormGroup<OnsIntercambioSinComOutrosPaisesFormGroupContent>({
      id: new FormControl(
        { value: onsIntercambioSinComOutrosPaisesRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      dinInstante: new FormControl(onsIntercambioSinComOutrosPaisesRawValue.dinInstante),
      nomPaisdestino: new FormControl(onsIntercambioSinComOutrosPaisesRawValue.nomPaisdestino),
      valIntercambiomwmed: new FormControl(onsIntercambioSinComOutrosPaisesRawValue.valIntercambiomwmed),
    });
  }

  getOnsIntercambioSinComOutrosPaises(
    form: OnsIntercambioSinComOutrosPaisesFormGroup,
  ): IOnsIntercambioSinComOutrosPaises | NewOnsIntercambioSinComOutrosPaises {
    return form.getRawValue() as IOnsIntercambioSinComOutrosPaises | NewOnsIntercambioSinComOutrosPaises;
  }

  resetForm(
    form: OnsIntercambioSinComOutrosPaisesFormGroup,
    onsIntercambioSinComOutrosPaises: OnsIntercambioSinComOutrosPaisesFormGroupInput,
  ): void {
    const onsIntercambioSinComOutrosPaisesRawValue = { ...this.getFormDefaults(), ...onsIntercambioSinComOutrosPaises };
    form.reset(
      {
        ...onsIntercambioSinComOutrosPaisesRawValue,
        id: { value: onsIntercambioSinComOutrosPaisesRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsIntercambioSinComOutrosPaisesFormDefaults {
    return {
      id: null,
    };
  }
}
