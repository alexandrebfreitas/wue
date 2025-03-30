jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, fakeAsync, inject, tick } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { OnsDadosHidrologicosVolumeEsperaRecomendadoService } from '../service/ons-dados-hidrologicos-volume-espera-recomendado.service';

import { OnsDadosHidrologicosVolumeEsperaRecomendadoDeleteDialogComponent } from './ons-dados-hidrologicos-volume-espera-recomendado-delete-dialog.component';

describe('OnsDadosHidrologicosVolumeEsperaRecomendado Management Delete Component', () => {
  let comp: OnsDadosHidrologicosVolumeEsperaRecomendadoDeleteDialogComponent;
  let fixture: ComponentFixture<OnsDadosHidrologicosVolumeEsperaRecomendadoDeleteDialogComponent>;
  let service: OnsDadosHidrologicosVolumeEsperaRecomendadoService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsDadosHidrologicosVolumeEsperaRecomendadoDeleteDialogComponent],
      providers: [provideHttpClient(), NgbActiveModal],
    })
      .overrideTemplate(OnsDadosHidrologicosVolumeEsperaRecomendadoDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(OnsDadosHidrologicosVolumeEsperaRecomendadoDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(OnsDadosHidrologicosVolumeEsperaRecomendadoService);
    mockActiveModal = TestBed.inject(NgbActiveModal);
  });

  describe('confirmDelete', () => {
    it('Should call delete service on confirmDelete', inject(
      [],
      fakeAsync(() => {
        // GIVEN
        jest.spyOn(service, 'delete').mockReturnValue(of(new HttpResponse({ body: {} })));

        // WHEN
        comp.confirmDelete(123);
        tick();

        // THEN
        expect(service.delete).toHaveBeenCalledWith(123);
        expect(mockActiveModal.close).toHaveBeenCalledWith('deleted');
      }),
    ));

    it('Should not call delete service on clear', () => {
      // GIVEN
      jest.spyOn(service, 'delete');

      // WHEN
      comp.cancel();

      // THEN
      expect(service.delete).not.toHaveBeenCalled();
      expect(mockActiveModal.close).not.toHaveBeenCalled();
      expect(mockActiveModal.dismiss).toHaveBeenCalled();
    });
  });
});
