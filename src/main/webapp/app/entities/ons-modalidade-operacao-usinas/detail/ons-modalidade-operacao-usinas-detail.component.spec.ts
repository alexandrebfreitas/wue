import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsModalidadeOperacaoUsinasDetailComponent } from './ons-modalidade-operacao-usinas-detail.component';

describe('OnsModalidadeOperacaoUsinas Management Detail Component', () => {
  let comp: OnsModalidadeOperacaoUsinasDetailComponent;
  let fixture: ComponentFixture<OnsModalidadeOperacaoUsinasDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsModalidadeOperacaoUsinasDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import('./ons-modalidade-operacao-usinas-detail.component').then(m => m.OnsModalidadeOperacaoUsinasDetailComponent),
              resolve: { onsModalidadeOperacaoUsinas: () => of({ id: 6450 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsModalidadeOperacaoUsinasDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsModalidadeOperacaoUsinasDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsModalidadeOperacaoUsinas on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsModalidadeOperacaoUsinasDetailComponent);

      // THEN
      expect(instance.onsModalidadeOperacaoUsinas()).toEqual(expect.objectContaining({ id: 6450 }));
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
