import { ComponentFixture, TestBed, fakeAsync, inject, tick } from '@angular/core/testing';
import { HttpHeaders, HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subject, of } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { sampleWithRequiredData } from '../ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes.test-samples';
import { OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesService } from '../service/ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes.service';

import { OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesComponent } from './ons-indicadores-confiabilidade-rede-basica-ciper-carga-interrompida-perturbacoes.component';
import SpyInstance = jest.SpyInstance;

describe('OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes Management Component', () => {
  let comp: OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesComponent;
  let fixture: ComponentFixture<OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesComponent>;
  let service: OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesService;
  let routerNavigateSpy: SpyInstance<Promise<boolean>>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesComponent],
      providers: [
        provideHttpClient(),
        {
          provide: ActivatedRoute,
          useValue: {
            data: of({
              defaultSort: 'id,asc',
            }),
            queryParamMap: of(
              jest.requireActual('@angular/router').convertToParamMap({
                page: '1',
                size: '1',
                sort: 'id,desc',
              }),
            ),
            snapshot: {
              queryParams: {},
              queryParamMap: jest.requireActual('@angular/router').convertToParamMap({
                page: '1',
                size: '1',
                sort: 'id,desc',
              }),
            },
          },
        },
      ],
    })
      .overrideTemplate(OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(OnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesService);
    routerNavigateSpy = jest.spyOn(comp.router, 'navigate');

    jest
      .spyOn(service, 'query')
      .mockReturnValueOnce(
        of(
          new HttpResponse({
            body: [{ id: 511 }],
            headers: new HttpHeaders({
              link: '<http://localhost/api/foo?page=1&size=20>; rel="next"',
            }),
          }),
        ),
      )
      .mockReturnValueOnce(
        of(
          new HttpResponse({
            body: [{ id: 30044 }],
            headers: new HttpHeaders({
              link: '<http://localhost/api/foo?page=0&size=20>; rel="prev",<http://localhost/api/foo?page=2&size=20>; rel="next"',
            }),
          }),
        ),
      );
  });

  it('Should call load all on init', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenCalled();
    expect(comp.onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoes()[0]).toEqual(
      expect.objectContaining({ id: 511 }),
    );
  });

  describe('trackId', () => {
    it('Should forward to onsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesService', () => {
      const entity = { id: 511 };
      jest.spyOn(service, 'getOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesIdentifier');
      const id = comp.trackId(entity);
      expect(service.getOnsIndicadoresConfiabilidadeRedeBasicaCiperCargaInterrompidaPerturbacoesIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });

  it('should calculate the sort attribute for a non-id attribute', () => {
    // WHEN
    comp.navigateToWithComponentValues({ predicate: 'non-existing-column', order: 'asc' });

    // THEN
    expect(routerNavigateSpy).toHaveBeenLastCalledWith(
      expect.anything(),
      expect.objectContaining({
        queryParams: expect.objectContaining({
          sort: ['non-existing-column,asc'],
        }),
      }),
    );
  });

  it('should calculate the sort attribute for an id', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenLastCalledWith(expect.objectContaining({ sort: ['id,desc'] }));
  });

  describe('delete', () => {
    let ngbModal: NgbModal;
    let deleteModalMock: any;

    beforeEach(() => {
      deleteModalMock = { componentInstance: {}, closed: new Subject() };
      // NgbModal is not a singleton using TestBed.inject.
      // ngbModal = TestBed.inject(NgbModal);
      ngbModal = (comp as any).modalService;
      jest.spyOn(ngbModal, 'open').mockReturnValue(deleteModalMock);
    });

    it('on confirm should call load', inject(
      [],
      fakeAsync(() => {
        // GIVEN
        jest.spyOn(comp, 'load');

        // WHEN
        comp.delete(sampleWithRequiredData);
        deleteModalMock.closed.next('deleted');
        tick();

        // THEN
        expect(ngbModal.open).toHaveBeenCalled();
        expect(comp.load).toHaveBeenCalled();
      }),
    ));

    it('on dismiss should call load', inject(
      [],
      fakeAsync(() => {
        // GIVEN
        jest.spyOn(comp, 'load');

        // WHEN
        comp.delete(sampleWithRequiredData);
        deleteModalMock.closed.next();
        tick();

        // THEN
        expect(ngbModal.open).toHaveBeenCalled();
        expect(comp.load).not.toHaveBeenCalled();
      }),
    ));
  });
});
