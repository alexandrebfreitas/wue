import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsDadosRelacionamentoEntreConjuntosEUsinasDetailComponent } from './ons-dados-relacionamento-entre-conjuntos-e-usinas-detail.component';

describe('OnsDadosRelacionamentoEntreConjuntosEUsinas Management Detail Component', () => {
  let comp: OnsDadosRelacionamentoEntreConjuntosEUsinasDetailComponent;
  let fixture: ComponentFixture<OnsDadosRelacionamentoEntreConjuntosEUsinasDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsDadosRelacionamentoEntreConjuntosEUsinasDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import('./ons-dados-relacionamento-entre-conjuntos-e-usinas-detail.component').then(
                  m => m.OnsDadosRelacionamentoEntreConjuntosEUsinasDetailComponent,
                ),
              resolve: { onsDadosRelacionamentoEntreConjuntosEUsinas: () => of({ id: 30752 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsDadosRelacionamentoEntreConjuntosEUsinasDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsDadosRelacionamentoEntreConjuntosEUsinasDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsDadosRelacionamentoEntreConjuntosEUsinas on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsDadosRelacionamentoEntreConjuntosEUsinasDetailComponent);

      // THEN
      expect(instance.onsDadosRelacionamentoEntreConjuntosEUsinas()).toEqual(expect.objectContaining({ id: 30752 }));
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
