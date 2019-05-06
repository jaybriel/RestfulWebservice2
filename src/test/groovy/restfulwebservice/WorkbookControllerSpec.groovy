package restfulwebservice

import grails.plugin.json.view.test.JsonViewTest
import grails.testing.gorm.DataTest
import grails.testing.web.controllers.ControllerUnitTest
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import spock.lang.Specification

class WorkbookControllerSpec extends Specification implements ControllerUnitTest<WorkbookController>, DataTest {

    def setup() {
        mockDomain Workbook
    }

    def cleanup() {
    }

    void "test index action with multiple saved workbooks then should return index view"()
    {
        given:
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-mm-dd")
        controller.workbookService = Stub(WorkbookService){
            list() >> [new Workbook(firstName:"test",lastName:"test",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 21,passportNumber: "E12345678",email: "jaybrielsomcio@gmail.com",phone: "09452665267"),new Workbook(firstName:"test",lastName:"test",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 21,passportNumber: "E12445678",email: "jaybrielsomcio@yahoo.com",phone: "09452665267")]
        }

        when:"index action is called"
        controller.index()

        then:"then should render index view"
        view.startsWith('/workbook')
    }



    void "test save action with new workbook "(){
        given:
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-mm-dd")
        def workbook = new Workbook(firstName:"test",lastName:"test",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 21,passportNumber: "E12345678",email: "jaybrielsomcio@gmail.com",phone: "09452665267")

        controller.workbookService = Stub(WorkbookService){
            save(workbook) >> new Workbook(firstName:"test",lastName:"test",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 21,passportNumber: "E12345678",email: "jaybrielsomcio@gmail.com",phone: "09452665267")
        }

        when:"The save action is executed"
        request.method = 'POST'
        controller.save(workbook)

        then:"should output a status 200 which means workbook is saved"
        response.getStatus() == 200
    }


    void "test show action with existing workbook"(){
        given:
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-mm-dd")
        def workbook = new Workbook(firstName:"test",lastName:"test",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 21,passportNumber: "E12345678",email: "jaybrielsomcio@gmail.com",phone: "09452665267")

        controller.workbookService = Stub(WorkbookService){
            save(workbook) >> new Workbook(firstName:"test",lastName:"test",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 21,passportNumber: "E12345678",email: "jaybrielsomcio@gmail.com",phone: "09452665267")
        }

        when:"The save action is executed"
        request.method = 'POST'
        controller.save(workbook)

        then:"should output a status 200 which means workbook is saved"
        response.getStatus() == 200

        when:"The show action is executed"
        request.method ='GET'
        controller.show(workbook)

        then:"should output a status 200 which means workbook is showed"
        response.getStatus() == 200
    }

    void "test update action with existing workbook and some values will be replaced"(){

        given:"First workbook to be saved"
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-mm-dd")
        def workbook = new Workbook(firstName:"test",lastName:"test",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 30,passportNumber: "E12345678",email: "jaybrielsomcio@gmail.com",phone: "09452665267")
        controller.workbookService = Stub(WorkbookService){
            save(workbook) >> new Workbook(id:1,firstName:"test",lastName:"test",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 21,passportNumber: "E12345678",email: "jaybrielsomcio@gmail.com",phone: "09452665267")
        }

        when:"The save action is executed"
        request.method = 'POST'
        controller.save(workbook)

        then:"should output a status 200 which means workbook is saved"
        response.getStatus() == 200


        "workbook values are replaced"
        controller.workbookService = Stub(WorkbookService){
            save(workbook) >> new Workbook(id:1,firstName:"jaybriel",lastName:"somcio",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 21,passportNumber: "E12345678",email: "jaybrielsomcio@gmail.com",phone: "09452665267")
        }

        when:"The save action is executed with PUT as method for update"
        request.method = 'PUT'
        controller.update(workbook)

        then:"should output a status 200 which means workbook is updated"
        response.getStatus() == 200

    }
    void "test delete action with existing workbook and should delete workbook"(){
        given:
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-mm-dd")
        def workbook = new Workbook(firstName:"test",lastName:"test",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 21,passportNumber: "E12345678",email: "jaybrielsomcio@gmail.com",phone: "09452665267")

        controller.workbookService = Stub(WorkbookService){
            save(workbook) >> new Workbook(firstName:"test",lastName:"test",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 21,passportNumber: "E12345678",email: "jaybrielsomcio@gmail.com",phone: "09452665267")
        }

        when:"The save action is executed"
        request.method = 'POST'
        controller.save(workbook)

        then:"should output a status 200 which means workbook is saved"
        response.getStatus() == 200

        when:"The delete action is executed"
        request.method ='DELETE'
        controller.delete(workbook)

        then:"should output a status 200 which means workbook is deleted"
        response.getStatus() == 200
    }
}
