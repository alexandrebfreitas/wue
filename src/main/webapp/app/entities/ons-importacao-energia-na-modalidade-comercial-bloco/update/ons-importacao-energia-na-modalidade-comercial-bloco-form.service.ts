import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import {
  IOnsImportacaoEnergiaNaModalidadeComercialBloco,
  NewOnsImportacaoEnergiaNaModalidadeComercialBloco,
} from '../ons-importacao-energia-na-modalidade-comercial-bloco.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsImportacaoEnergiaNaModalidadeComercialBloco for edit and NewOnsImportacaoEnergiaNaModalidadeComercialBlocoFormGroupInput for create.
 */
type OnsImportacaoEnergiaNaModalidadeComercialBlocoFormGroupInput =
  | IOnsImportacaoEnergiaNaModalidadeComercialBloco
  | PartialWithRequiredKeyOf<NewOnsImportacaoEnergiaNaModalidadeComercialBloco>;

type OnsImportacaoEnergiaNaModalidadeComercialBlocoFormDefaults = Pick<NewOnsImportacaoEnergiaNaModalidadeComercialBloco, 'id'>;

type OnsImportacaoEnergiaNaModalidadeComercialBlocoFormGroupContent = {
  id: FormControl<IOnsImportacaoEnergiaNaModalidadeComercialBloco['id'] | NewOnsImportacaoEnergiaNaModalidadeComercialBloco['id']>;
  nomPais: FormControl<IOnsImportacaoEnergiaNaModalidadeComercialBloco['nomPais']>;
  nomAgente: FormControl<IOnsImportacaoEnergiaNaModalidadeComercialBloco['nomAgente']>;
  nomBloco: FormControl<IOnsImportacaoEnergiaNaModalidadeComercialBloco['nomBloco']>;
  codBloco: FormControl<IOnsImportacaoEnergiaNaModalidadeComercialBloco['codBloco']>;
  dinInstante: FormControl<IOnsImportacaoEnergiaNaModalidadeComercialBloco['dinInstante']>;
  valImportacaoprogramada: FormControl<IOnsImportacaoEnergiaNaModalidadeComercialBloco['valImportacaoprogramada']>;
  valImportacaodespachada: FormControl<IOnsImportacaoEnergiaNaModalidadeComercialBloco['valImportacaodespachada']>;
  valImportacaoverificada: FormControl<IOnsImportacaoEnergiaNaModalidadeComercialBloco['valImportacaoverificada']>;
  valPreco: FormControl<IOnsImportacaoEnergiaNaModalidadeComercialBloco['valPreco']>;
};

export type OnsImportacaoEnergiaNaModalidadeComercialBlocoFormGroup =
  FormGroup<OnsImportacaoEnergiaNaModalidadeComercialBlocoFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsImportacaoEnergiaNaModalidadeComercialBlocoFormService {
  createOnsImportacaoEnergiaNaModalidadeComercialBlocoFormGroup(
    onsImportacaoEnergiaNaModalidadeComercialBloco: OnsImportacaoEnergiaNaModalidadeComercialBlocoFormGroupInput = { id: null },
  ): OnsImportacaoEnergiaNaModalidadeComercialBlocoFormGroup {
    const onsImportacaoEnergiaNaModalidadeComercialBlocoRawValue = {
      ...this.getFormDefaults(),
      ...onsImportacaoEnergiaNaModalidadeComercialBloco,
    };
    return new FormGroup<OnsImportacaoEnergiaNaModalidadeComercialBlocoFormGroupContent>({
      id: new FormControl(
        { value: onsImportacaoEnergiaNaModalidadeComercialBlocoRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      nomPais: new FormControl(onsImportacaoEnergiaNaModalidadeComercialBlocoRawValue.nomPais),
      nomAgente: new FormControl(onsImportacaoEnergiaNaModalidadeComercialBlocoRawValue.nomAgente),
      nomBloco: new FormControl(onsImportacaoEnergiaNaModalidadeComercialBlocoRawValue.nomBloco),
      codBloco: new FormControl(onsImportacaoEnergiaNaModalidadeComercialBlocoRawValue.codBloco),
      dinInstante: new FormControl(onsImportacaoEnergiaNaModalidadeComercialBlocoRawValue.dinInstante),
      valImportacaoprogramada: new FormControl(onsImportacaoEnergiaNaModalidadeComercialBlocoRawValue.valImportacaoprogramada),
      valImportacaodespachada: new FormControl(onsImportacaoEnergiaNaModalidadeComercialBlocoRawValue.valImportacaodespachada),
      valImportacaoverificada: new FormControl(onsImportacaoEnergiaNaModalidadeComercialBlocoRawValue.valImportacaoverificada),
      valPreco: new FormControl(onsImportacaoEnergiaNaModalidadeComercialBlocoRawValue.valPreco),
    });
  }

  getOnsImportacaoEnergiaNaModalidadeComercialBloco(
    form: OnsImportacaoEnergiaNaModalidadeComercialBlocoFormGroup,
  ): IOnsImportacaoEnergiaNaModalidadeComercialBloco | NewOnsImportacaoEnergiaNaModalidadeComercialBloco {
    return form.getRawValue() as IOnsImportacaoEnergiaNaModalidadeComercialBloco | NewOnsImportacaoEnergiaNaModalidadeComercialBloco;
  }

  resetForm(
    form: OnsImportacaoEnergiaNaModalidadeComercialBlocoFormGroup,
    onsImportacaoEnergiaNaModalidadeComercialBloco: OnsImportacaoEnergiaNaModalidadeComercialBlocoFormGroupInput,
  ): void {
    const onsImportacaoEnergiaNaModalidadeComercialBlocoRawValue = {
      ...this.getFormDefaults(),
      ...onsImportacaoEnergiaNaModalidadeComercialBloco,
    };
    form.reset(
      {
        ...onsImportacaoEnergiaNaModalidadeComercialBlocoRawValue,
        id: { value: onsImportacaoEnergiaNaModalidadeComercialBlocoRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsImportacaoEnergiaNaModalidadeComercialBlocoFormDefaults {
    return {
      id: null,
    };
  }
}
