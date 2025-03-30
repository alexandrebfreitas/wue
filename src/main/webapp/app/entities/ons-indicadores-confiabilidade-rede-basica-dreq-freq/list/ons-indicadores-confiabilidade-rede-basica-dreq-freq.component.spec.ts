import { ComponentFixture, TestBed, fakeAsync, inject, tick } from '@angular/core/testing';
import { HttpHeaders, HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subject, of } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { sampleWithRequiredData } from '../ons-indicadores-confiabilidade-rede-basica-dreq-freq.test-samples';
import { OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqService } from '../service/ons-indicadores-confiabilidade-rede-basica-dreq-freq.service';

import { OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqComponent } from './ons-indicadores-confiabilidade-rede-basica-dreq-freq.component';
import SpyInstance = jest.SpyInstance;

describe('OnsIndicadoresConfiabilidadeRedeBasicaDreqFreq Management Component', () => {
  let comp: OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqComponent;
  let fixture: ComponentFixture<OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqComponent>;
  let service: OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqService;
  let routerNavigateSpy: SpyInstance<Promise<boolean>>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqComponent],
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
      .overrideTemplate(OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqService);
    routerNavigateSpy = jest.spyOn(comp.router, 'navigate');

    jest
      .spyOn(service, 'query')
      .mockReturnValueOnce(
        of(
          new HttpResponse({
            body: [{ id: 4905 }],
            headers: new HttpHeaders({
              link: '<http://localhost/api/foo?page=1&size=20>; rel="next"',
            }),
          }),
        ),
      )
      .mockReturnValueOnce(
        of(
          new HttpResponse({
            body: [{ id: 13133 }],
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
    expect(comp.onsIndicadoresConfiabilidadeRedeBasicaDreqFreqs()[0]).toEqual(expect.objectContaining({ id: 4905 }));
  });

  describe('trackId', () => {
    it('Should forward to onsIndicadoresConfiabilidadeRedeBasicaDreqFreqService', () => {
      const entity = { id: 4905 };
      jest.spyOn(service, 'getOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqIdentifier');
      const id = comp.trackId(entity);
      expect(service.getOnsIndicadoresConfiabilidadeRedeBasicaDreqFreqIdentifier).toHaveBeenCalledWith(entity);
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
