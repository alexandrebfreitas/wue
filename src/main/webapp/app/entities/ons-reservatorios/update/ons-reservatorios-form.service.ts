import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IOnsReservatorios, NewOnsReservatorios } from '../ons-reservatorios.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsReservatorios for edit and NewOnsReservatoriosFormGroupInput for create.
 */
type OnsReservatoriosFormGroupInput = IOnsReservatorios | PartialWithRequiredKeyOf<NewOnsReservatorios>;

type OnsReservatoriosFormDefaults = Pick<NewOnsReservatorios, 'id'>;

type OnsReservatoriosFormGroupContent = {
  id: FormControl<IOnsReservatorios['id'] | NewOnsReservatorios['id']>;
  nomRee: FormControl<IOnsReservatorios['nomRee']>;
  datEntrada: FormControl<IOnsReservatorios['datEntrada']>;
  valCotamaxima: FormControl<IOnsReservatorios['valCotamaxima']>;
  valCotaminima: FormControl<IOnsReservatorios['valCotaminima']>;
  valVolmax: FormControl<IOnsReservatorios['valVolmax']>;
  valVolmin: FormControl<IOnsReservatorios['valVolmin']>;
  valVolutiltot: FormControl<IOnsReservatorios['valVolutiltot']>;
  valProdutibilidadeespecifica: FormControl<IOnsReservatorios['valProdutibilidadeespecifica']>;
  valProdutividade65volutil: FormControl<IOnsReservatorios['valProdutividade65volutil']>;
  valTipoperda: FormControl<IOnsReservatorios['valTipoperda']>;
  valPerda: FormControl<IOnsReservatorios['valPerda']>;
  valLatitude: FormControl<IOnsReservatorios['valLatitude']>;
  valLongitude: FormControl<IOnsReservatorios['valLongitude']>;
  idReservatorio: FormControl<IOnsReservatorios['idReservatorio']>;
};

export type OnsReservatoriosFormGroup = FormGroup<OnsReservatoriosFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsReservatoriosFormService {
  createOnsReservatoriosFormGroup(onsReservatorios: OnsReservatoriosFormGroupInput = { id: null }): OnsReservatoriosFormGroup {
    const onsReservatoriosRawValue = {
      ...this.getFormDefaults(),
      ...onsReservatorios,
    };
    return new FormGroup<OnsReservatoriosFormGroupContent>({
      id: new FormControl(
        { value: onsReservatoriosRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      nomRee: new FormControl(onsReservatoriosRawValue.nomRee),
      datEntrada: new FormControl(onsReservatoriosRawValue.datEntrada),
      valCotamaxima: new FormControl(onsReservatoriosRawValue.valCotamaxima),
      valCotaminima: new FormControl(onsReservatoriosRawValue.valCotaminima),
      valVolmax: new FormControl(onsReservatoriosRawValue.valVolmax),
      valVolmin: new FormControl(onsReservatoriosRawValue.valVolmin),
      valVolutiltot: new FormControl(onsReservatoriosRawValue.valVolutiltot),
      valProdutibilidadeespecifica: new FormControl(onsReservatoriosRawValue.valProdutibilidadeespecifica),
      valProdutividade65volutil: new FormControl(onsReservatoriosRawValue.valProdutividade65volutil),
      valTipoperda: new FormControl(onsReservatoriosRawValue.valTipoperda),
      valPerda: new FormControl(onsReservatoriosRawValue.valPerda),
      valLatitude: new FormControl(onsReservatoriosRawValue.valLatitude),
      valLongitude: new FormControl(onsReservatoriosRawValue.valLongitude),
      idReservatorio: new FormControl(onsReservatoriosRawValue.idReservatorio),
    });
  }

  getOnsReservatorios(form: OnsReservatoriosFormGroup): IOnsReservatorios | NewOnsReservatorios {
    return form.getRawValue() as IOnsReservatorios | NewOnsReservatorios;
  }

  resetForm(form: OnsReservatoriosFormGroup, onsReservatorios: OnsReservatoriosFormGroupInput): void {
    const onsReservatoriosRawValue = { ...this.getFormDefaults(), ...onsReservatorios };
    form.reset(
      {
        ...onsReservatoriosRawValue,
        id: { value: onsReservatoriosRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsReservatoriosFormDefaults {
    return {
      id: null,
    };
  }
}
