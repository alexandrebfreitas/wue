import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsEquipamentosControleReativosDetailComponent } from './ons-equipamentos-controle-reativos-detail.component';

describe('OnsEquipamentosControleReativos Management Detail Component', () => {
  let comp: OnsEquipamentosControleReativosDetailComponent;
  let fixture: ComponentFixture<OnsEquipamentosControleReativosDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsEquipamentosControleReativosDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import('./ons-equipamentos-controle-reativos-detail.component').then(m => m.OnsEquipamentosControleReativosDetailComponent),
              resolve: { onsEquipamentosControleReativos: () => of({ id: 122 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsEquipamentosControleReativosDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsEquipamentosControleReativosDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsEquipamentosControleReativos on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsEquipamentosControleReativosDetailComponent);

      // THEN
      expect(instance.onsEquipamentosControleReativos()).toEqual(expect.objectContaining({ id: 122 }));
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
