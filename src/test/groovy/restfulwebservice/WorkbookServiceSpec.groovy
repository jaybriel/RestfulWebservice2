package restfulwebservice

import grails.plugins.jodatime.binding.JodaTimePropertyEditorRegistrar
import grails.testing.gorm.DataTest
import grails.testing.services.ServiceUnitTest
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import org.junit.Before
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification


class WorkbookServiceSpec extends Specification implements ServiceUnitTest<WorkbookService>, DataTest{

    @Autowired WorkbookService workbookservice

    def setup() {
        mockDomain Workbook
    }

    def cleanup() {
    }



    void "test retrieveId action of workbook "(){
        given:
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-mm-dd")
        def workbook = new Workbook(firstName:"jaybriel",lastName:"somcio",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 21,passportNumber: "E12345678",email: "jaybrielsomcio@gmail.com",phone: "09452665267").save(flush:true)
        def result

        when:"retrieveid action is executed and result is stored in a variable"
        result = service.retrieveId(1)

        then:"returns the workbook that matches the given id parameters"
        result.firstName =="jaybriel"
        result.lastName == "somcio"
        result.age == 21
        result.dateOfBirth == LocalDate.parse("1997-11-02",formatter)
        result.passportNumber == "E12345678"
        result.email == "jaybrielsomcio@gmail.com"
        result.phone == "09452665267"

    }


    void "test save method with existing workbook then returns the saved workbook"(){
        given:
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-mm-dd")
        def workbook = new Workbook(firstName:"test",lastName:"test",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 21,passportNumber: "E12345678",email: "jaybrielsomcio@gmail.com",phone: "09452665267")
                .save(flush:true)
        def result

        when:"save action is executed and result is stored in a variable"
        result = service.save(workbook)

        then:"result matches the workbook saved"
        result == workbook
    }

    void "test delete action with 1 existing workbook then should delete the existing workbook"(){
        given:
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-mm-dd")
        def workbook = new Workbook(firstName:"jaybriel",lastName:"somcio",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 21,passportNumber: "E12345678",email: "jaybrielsomcio@gmail.com",phone: "09452665267")
                .save(flush:true)
        def result

        when:"delete action is executed and result is stored in a variable"
        result = service.delete(workbook)

        then:"result is expected to be null for successfully deleting the workbook object"
        result ==  null
    }


    void "test save method with 1 existing workbook and workplace then returns the saved workbook"(){
        given:
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-mm-dd")
        def workbook = new Workbook(firstName:"test",lastName:"test",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 21,passportNumber: "E12345678",email: "jaybrielsomcio@gmail.com",phone: "09452665267").save(flush:true)
                .addToWorkplaces(new Workplace(cmpCode:"IBM",cmpName:"Webb Fontaine Group",ctyCode:"MNL",ctyDesc:"ManilaArea",startDate: LocalDate.parse("2019-01-02",formatter),endDate: LocalDate.parse("2019-01-30",formatter)))
                .save(flush:true)
        def result

        when:"save action is executed and result is stored in a variable"
        result = service.save(workbook)

        then:"result matches the workbook saved"
        result == workbook
    }

    void "test delete method with 1 existing workbook and workplace then should delete the existing workbook"(){
        given:
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-mm-dd")
        def workbook = new Workbook(firstName:"test",lastName:"test",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 21,passportNumber: "E12345678",email: "jaybrielsomcio@gmail.com",phone: "09452665267").save(flush:true)
                .addToWorkplaces(new Workplace(cmpCode:"IBM",cmpName:"Webb Fontaine Group",ctyCode:"MNL",ctyDesc:"ManilaArea",startDate: LocalDate.parse("2019-01-02",formatter),endDate: LocalDate.parse("2019-01-30",formatter)))
                .save(flush:true)
        def result

        when:"delete action is executed and result is stored in a variable"
        result = service.delete(workbook)

        then:"result is expected to be null for successfully deleting the workbook"
        result ==  null
    }

    void 'test the list action with 3 existing workbooks then returns the correct model with size of list'(){

        given:
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-mm-dd")
        def workbook1 = new Workbook(firstName:"test",lastName:"test",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 21,passportNumber: "E12345678",email: "jaybrielsomcio@gmail.com",phone: "09452665267")
        def workbook2 = new Workbook(firstName:"test",lastName:"test",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 21,passportNumber: "E12445678",email: "jaybrielsomcio@yahoo.com",phone: "09452665267")
        def workbook3 = new Workbook(firstName:"test",lastName:"test",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 21,passportNumber: "E22345678",email: "jaybrielsomcio@hotmail.com",phone: "09452665267")
        def result

        when:"the save action is executed 3 times for the 3 workbooks"
        service.save(workbook1)
        service.save(workbook2)
        service.save(workbook3)
        result = service.list()

        then:"expected size of the list should be 3 for finding 3 workbooks saved"
        result.size() == 3

    }
}
