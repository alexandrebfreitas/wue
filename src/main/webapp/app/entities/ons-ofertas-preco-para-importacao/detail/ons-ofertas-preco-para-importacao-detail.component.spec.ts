import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsOfertasPrecoParaImportacaoDetailComponent } from './ons-ofertas-preco-para-importacao-detail.component';

describe('OnsOfertasPrecoParaImportacao Management Detail Component', () => {
  let comp: OnsOfertasPrecoParaImportacaoDetailComponent;
  let fixture: ComponentFixture<OnsOfertasPrecoParaImportacaoDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsOfertasPrecoParaImportacaoDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import('./ons-ofertas-preco-para-importacao-detail.component').then(m => m.OnsOfertasPrecoParaImportacaoDetailComponent),
              resolve: { onsOfertasPrecoParaImportacao: () => of({ id: 1132 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsOfertasPrecoParaImportacaoDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsOfertasPrecoParaImportacaoDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsOfertasPrecoParaImportacao on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsOfertasPrecoParaImportacaoDetailComponent);

      // THEN
      expect(instance.onsOfertasPrecoParaImportacao()).toEqual(expect.objectContaining({ id: 1132 }));
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
