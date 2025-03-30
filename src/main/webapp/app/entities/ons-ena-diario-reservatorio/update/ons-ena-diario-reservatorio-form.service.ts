import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IOnsEnaDiarioReservatorio, NewOnsEnaDiarioReservatorio } from '../ons-ena-diario-reservatorio.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsEnaDiarioReservatorio for edit and NewOnsEnaDiarioReservatorioFormGroupInput for create.
 */
type OnsEnaDiarioReservatorioFormGroupInput = IOnsEnaDiarioReservatorio | PartialWithRequiredKeyOf<NewOnsEnaDiarioReservatorio>;

type OnsEnaDiarioReservatorioFormDefaults = Pick<NewOnsEnaDiarioReservatorio, 'id'>;

type OnsEnaDiarioReservatorioFormGroupContent = {
  id: FormControl<IOnsEnaDiarioReservatorio['id'] | NewOnsEnaDiarioReservatorio['id']>;
  enaBrutaResMwmed: FormControl<IOnsEnaDiarioReservatorio['enaBrutaResMwmed']>;
  enaBrutaResPercentualmlt: FormControl<IOnsEnaDiarioReservatorio['enaBrutaResPercentualmlt']>;
  enaArmazenavelResMwmed: FormControl<IOnsEnaDiarioReservatorio['enaArmazenavelResMwmed']>;
  enaArmazenavelResPercentualmlt: FormControl<IOnsEnaDiarioReservatorio['enaArmazenavelResPercentualmlt']>;
  enaQuedaBruta: FormControl<IOnsEnaDiarioReservatorio['enaQuedaBruta']>;
  mltEna: FormControl<IOnsEnaDiarioReservatorio['mltEna']>;
};

export type OnsEnaDiarioReservatorioFormGroup = FormGroup<OnsEnaDiarioReservatorioFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsEnaDiarioReservatorioFormService {
  createOnsEnaDiarioReservatorioFormGroup(
    onsEnaDiarioReservatorio: OnsEnaDiarioReservatorioFormGroupInput = { id: null },
  ): OnsEnaDiarioReservatorioFormGroup {
    const onsEnaDiarioReservatorioRawValue = {
      ...this.getFormDefaults(),
      ...onsEnaDiarioReservatorio,
    };
    return new FormGroup<OnsEnaDiarioReservatorioFormGroupContent>({
      id: new FormControl(
        { value: onsEnaDiarioReservatorioRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      enaBrutaResMwmed: new FormControl(onsEnaDiarioReservatorioRawValue.enaBrutaResMwmed),
      enaBrutaResPercentualmlt: new FormControl(onsEnaDiarioReservatorioRawValue.enaBrutaResPercentualmlt),
      enaArmazenavelResMwmed: new FormControl(onsEnaDiarioReservatorioRawValue.enaArmazenavelResMwmed),
      enaArmazenavelResPercentualmlt: new FormControl(onsEnaDiarioReservatorioRawValue.enaArmazenavelResPercentualmlt),
      enaQuedaBruta: new FormControl(onsEnaDiarioReservatorioRawValue.enaQuedaBruta),
      mltEna: new FormControl(onsEnaDiarioReservatorioRawValue.mltEna),
    });
  }

  getOnsEnaDiarioReservatorio(form: OnsEnaDiarioReservatorioFormGroup): IOnsEnaDiarioReservatorio | NewOnsEnaDiarioReservatorio {
    return form.getRawValue() as IOnsEnaDiarioReservatorio | NewOnsEnaDiarioReservatorio;
  }

  resetForm(form: OnsEnaDiarioReservatorioFormGroup, onsEnaDiarioReservatorio: OnsEnaDiarioReservatorioFormGroupInput): void {
    const onsEnaDiarioReservatorioRawValue = { ...this.getFormDefaults(), ...onsEnaDiarioReservatorio };
    form.reset(
      {
        ...onsEnaDiarioReservatorioRawValue,
        id: { value: onsEnaDiarioReservatorioRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsEnaDiarioReservatorioFormDefaults {
    return {
      id: null,
    };
  }
}
