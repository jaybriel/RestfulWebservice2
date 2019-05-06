package restfulwebservice

import grails.testing.gorm.DataTest
import grails.testing.web.controllers.ControllerUnitTest
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import spock.lang.Specification

class WorkplaceControllerSpec extends Specification implements ControllerUnitTest<WorkplaceController>, DataTest {

    def setup() {
        mockDomain Workplace
    }

    def cleanup() {
    }

    void "test index action with multiple saved workbooks with 1 workplace each then should return index view"()
    {
        given:
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-mm-dd")
        controller.workplaceService = Stub(WorkplaceService){
            list() >> [new Workbook(firstName:"test",lastName:"test",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 21,passportNumber: "E12345678",email: "jaybrielsomcio@gmail.com",phone: "09452665267").addToWorkplaces(new Workplace(cmpCode:"IBM",cmpName:"Webb Fontaine Group",ctyCode:"MNL",ctyDesc:"ManilaArea",startDate: LocalDate.parse("2019-01-02",formatter),endDate: LocalDate.parse("2019-01-30",formatter))), new Workbook(firstName:"test",lastName:"test",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 21,passportNumber: "E12445678",email: "jaybrielsomcio@yahoo.com",phone: "09452665267").addToWorkplaces(new Workplace(cmpCode:"IBM",cmpName:"Webb Fontaine Group",ctyCode:"MNL",ctyDesc:"ManilaArea",startDate: LocalDate.parse("2018-01-02",formatter),endDate: LocalDate.parse("2018-01-30",formatter)))]
        }

        when:"index action is called"
        controller.index()

        then:"then should render index view"
        view.startsWith('/workplace')
    }

    //not finished
    void "test save action with existing workbook with new workplace then save the new workplace "(){
        given:
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-mm-dd")
        def workbook = new Workbook(firstName:"test",lastName:"test",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 21,passportNumber: "E12345678",email: "jaybrielsomcio@gmail.com",phone: "09452665267").save(flush:true)
        def workplace = new Workplace(workbook:workbook,cmpCode:"IBM",cmpName:"Webb Fontaine Group",ctyCode:"MNL",ctyDesc:"ManilaArea",startDate: LocalDate.parse("2019-01-02",formatter),endDate: LocalDate.parse("2019-01-30",formatter))

        controller.workbookService = Stub(WorkbookService){
            retrieveId(_) >> workbook
        }
        controller.workplaceService = Stub(WorkplaceService){
            save(workplace) >> new Workplace(workbook:workbook,cmpCode:"IBM",cmpName:"Webb Fontaine Group",ctyCode:"MNL",ctyDesc:"ManilaArea",startDate: LocalDate.parse("2019-01-02",formatter),endDate: LocalDate.parse("2019-01-30",formatter))
        }

        when:"The save action is executed"
        controller.workplaceService.validateWorkplace(workbook,workplace)
        request.method = 'POST'
        controller.save(workplace)

        then:"should output a status 200 which means workplace is saved"
        response.getStatus() == 200
    }

    void "test delete action with existing workbook and workplace then delete workplace of workbook"(){
        given:
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-mm-dd")
        def workbook = new Workbook(firstName:"test",lastName:"test",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 21,passportNumber: "E12345678",email: "jaybrielsomcio@gmail.com",phone: "09452665267").save(flush:true)
        def workplace = new Workplace(workbook:workbook,cmpCode:"IBM",cmpName:"Webb Fontaine Group",ctyCode:"MNL",ctyDesc:"ManilaArea",startDate: LocalDate.parse("2019-01-02",formatter),endDate: LocalDate.parse("2019-01-30",formatter))


        controller.workbookService = Stub(WorkbookService){
            retrieveId(_) >> workbook
        }
        controller.workplaceService = Stub(WorkplaceService){
            save(workplace) >> new Workplace(workbook:workbook,cmpCode:"IBM",cmpName:"Webb Fontaine Group",ctyCode:"MNL",ctyDesc:"ManilaArea",startDate: LocalDate.parse("2019-01-02",formatter),endDate: LocalDate.parse("2019-01-30",formatter))
        }

        when:"The save action is executed"
        controller.workplaceService.validateWorkplace(workbook,workplace)
        request.method = 'POST'
        controller.save(workplace)

        then:"should output a status 200 which means workplace is saved"
        response.getStatus() == 200

        when:"The delete action is executed"
        request.method='DELETE'
        controller.delete(workplace)

        then:"should output a status 200 which means workplace is deleted"
        response.getStatus() == 200
    }

    void "test update action with existing workbook with new workplace then save the new workplace with different values"(){
        given:
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-mm-dd")
        def workbook = new Workbook(firstName:"test",lastName:"test",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 21,passportNumber: "E12345678",email: "jaybrielsomcio@gmail.com",phone: "09452665267").save(flush:true)
        def workplace = new Workplace(workbook:workbook,cmpCode:"IBM",cmpName:"Webb Fontaine Group",ctyCode:"MNL",ctyDesc:"ManilaArea",startDate: LocalDate.parse("2019-01-02",formatter),endDate: LocalDate.parse("2019-01-30",formatter))

        controller.workbookService = Stub(WorkbookService){
            retrieveId(_) >> workbook
        }
        controller.workplaceService = Stub(WorkplaceService){
            save(workplace) >> new Workplace(workbook:workbook,cmpCode:"IBM",cmpName:"Webb Fontaine Group",ctyCode:"MNL",ctyDesc:"ManilaArea",startDate: LocalDate.parse("2019-01-02",formatter),endDate: LocalDate.parse("2019-01-30",formatter))
        }

        when:"The save action is executed"
        controller.workplaceService.validateWorkplace(workbook,workplace)
        request.method = 'POST'
        controller.save(workplace)

        then:"should output a status 200 which means workplace is saved"
        response.getStatus() == 200

        controller.workplaceService = Stub(WorkplaceService){
            save(workplace) >> new Workplace(workbook:workbook,cmpCode:"NCS",cmpName:"National Computer Systems",ctyCode:"MNL",ctyDesc:"ManilaArea",startDate: LocalDate.parse("2019-01-02",formatter),endDate: LocalDate.parse("2019-01-30",formatter))
        }

        when:"The update action is executed"
        controller.workplaceService.validateWorkplace(workbook,workplace)
        request.method = 'PUT'
        controller.update(workplace)

        then:"should output a status 200 which means workplace is saved"
        response.getStatus() == 200
    }

}
