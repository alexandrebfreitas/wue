import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsCapacidadeInstaladaGeracaoDetailComponent } from './ons-capacidade-instalada-geracao-detail.component';

describe('OnsCapacidadeInstaladaGeracao Management Detail Component', () => {
  let comp: OnsCapacidadeInstaladaGeracaoDetailComponent;
  let fixture: ComponentFixture<OnsCapacidadeInstaladaGeracaoDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsCapacidadeInstaladaGeracaoDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import('./ons-capacidade-instalada-geracao-detail.component').then(m => m.OnsCapacidadeInstaladaGeracaoDetailComponent),
              resolve: { onsCapacidadeInstaladaGeracao: () => of({ id: 5332 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsCapacidadeInstaladaGeracaoDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsCapacidadeInstaladaGeracaoDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsCapacidadeInstaladaGeracao on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsCapacidadeInstaladaGeracaoDetailComponent);

      // THEN
      expect(instance.onsCapacidadeInstaladaGeracao()).toEqual(expect.objectContaining({ id: 5332 }));
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
