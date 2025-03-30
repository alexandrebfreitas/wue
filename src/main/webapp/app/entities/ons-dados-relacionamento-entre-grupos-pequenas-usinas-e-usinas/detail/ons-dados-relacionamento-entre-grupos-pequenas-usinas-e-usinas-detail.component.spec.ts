import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasDetailComponent } from './ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas-detail.component';

describe('OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas Management Detail Component', () => {
  let comp: OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasDetailComponent;
  let fixture: ComponentFixture<OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import('./ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas-detail.component').then(
                  m => m.OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasDetailComponent,
                ),
              resolve: { onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas: () => of({ id: 3963 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasDetailComponent);

      // THEN
      expect(instance.onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas()).toEqual(expect.objectContaining({ id: 3963 }));
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
