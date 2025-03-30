import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsGeracaoComercialParaExportacaoInternacionalDetailComponent } from './ons-geracao-comercial-para-exportacao-internacional-detail.component';

describe('OnsGeracaoComercialParaExportacaoInternacional Management Detail Component', () => {
  let comp: OnsGeracaoComercialParaExportacaoInternacionalDetailComponent;
  let fixture: ComponentFixture<OnsGeracaoComercialParaExportacaoInternacionalDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsGeracaoComercialParaExportacaoInternacionalDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import('./ons-geracao-comercial-para-exportacao-internacional-detail.component').then(
                  m => m.OnsGeracaoComercialParaExportacaoInternacionalDetailComponent,
                ),
              resolve: { onsGeracaoComercialParaExportacaoInternacional: () => of({ id: 18041 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsGeracaoComercialParaExportacaoInternacionalDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsGeracaoComercialParaExportacaoInternacionalDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsGeracaoComercialParaExportacaoInternacional on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsGeracaoComercialParaExportacaoInternacionalDetailComponent);

      // THEN
      expect(instance.onsGeracaoComercialParaExportacaoInternacional()).toEqual(expect.objectContaining({ id: 18041 }));
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
