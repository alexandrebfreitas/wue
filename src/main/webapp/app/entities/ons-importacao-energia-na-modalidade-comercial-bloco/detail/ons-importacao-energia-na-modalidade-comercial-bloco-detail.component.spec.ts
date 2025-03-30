import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsImportacaoEnergiaNaModalidadeComercialBlocoDetailComponent } from './ons-importacao-energia-na-modalidade-comercial-bloco-detail.component';

describe('OnsImportacaoEnergiaNaModalidadeComercialBloco Management Detail Component', () => {
  let comp: OnsImportacaoEnergiaNaModalidadeComercialBlocoDetailComponent;
  let fixture: ComponentFixture<OnsImportacaoEnergiaNaModalidadeComercialBlocoDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsImportacaoEnergiaNaModalidadeComercialBlocoDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import('./ons-importacao-energia-na-modalidade-comercial-bloco-detail.component').then(
                  m => m.OnsImportacaoEnergiaNaModalidadeComercialBlocoDetailComponent,
                ),
              resolve: { onsImportacaoEnergiaNaModalidadeComercialBloco: () => of({ id: 20715 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsImportacaoEnergiaNaModalidadeComercialBlocoDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsImportacaoEnergiaNaModalidadeComercialBlocoDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsImportacaoEnergiaNaModalidadeComercialBloco on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsImportacaoEnergiaNaModalidadeComercialBlocoDetailComponent);

      // THEN
      expect(instance.onsImportacaoEnergiaNaModalidadeComercialBloco()).toEqual(expect.objectContaining({ id: 20715 }));
    });
  });

  describe('PreviousState', () => {
    it('Should navigate to previous state', () => {
      jest.spyOn(window.history, 'back');
      comp.previousState();
      expect(window.history.back).toHaveBeenCalled();
    });
  });
});
