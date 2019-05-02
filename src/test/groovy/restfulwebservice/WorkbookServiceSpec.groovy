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


    void "test save method with existing workbook"(){
        given:
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-mm-dd")
        def workbook = new Workbook(firstName:"test",lastName:"test",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 21,passportNumber: "E12345678",email: "jaybrielsomcio@gmail.com",phone: "09452665267").save(flush:true)
        def result

        when:
        result = service.save(workbook)

        then:
        result == workbook
    }

    void "test retrieveWorkbook id"(){
        given:
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-mm-dd")
        def workbook = new Workbook(firstName:"jaybriel",lastName:"somcio",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 21,passportNumber: "E12345678",email: "jaybrielsomcio@gmail.com",phone: "09452665267").save(flush:true)
        def result

        when:
        result = service.retrieveId(1)

        then:
        result.firstName =="jaybriel"
        result.lastName == "somcio"
        result.age == 21
        result.passportNumber == "E12345678"
        result.email == "jaybrielsomcio@gmail.com"
        result.phone == "09452665267"

    }

    void "test delete action with existing workbook"(){
        given:
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-mm-dd")
        def workbook = new Workbook(firstName:"jaybriel",lastName:"somcio",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 21,passportNumber: "E12345678",email: "jaybrielsomcio@gmail.com",phone: "09452665267").save(flush:true)
        def result

        when:
        result = service.delete(workbook)

        then:
        result ==  null
    }
}
