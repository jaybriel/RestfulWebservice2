package restfulwebservice

import grails.plugins.jodatime.binding.JodaTimePropertyEditorRegistrar
import grails.testing.services.ServiceUnitTest
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import org.junit.Before
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

class WorkbookServiceSpec extends Specification implements ServiceUnitTest<WorkbookService>{

    @Autowired
    WorkbookService workbookservice
    @Before
    def setup() {

    }

    def cleanup() {
    }
//    void 'test if list() returns a json string'(){
//        given: 'a workbook'
//        def list = '{"id":1,"firstName":"jaybriel","lastName":"somcio","dateOfBirth":"1997-11-02","age":21,"passportNumber":"E12312312","email":"jaybrielsomcio@yahoo.com","phone":"09151324733"}'
//        def result
//
//
//        when:
//        result = service.list()
//
//        then:
//
//        result == list
//
//
//    }

    void "test save action with existing workbook"(){
        given:
        def workbook = new Workbook(firstName:"test",lastName:"test",dateOfBirth: LocalDate.parse('1997-11-02'),age: 21,passportNumber: "E12345678",email: "jaybrielsomcio@gmail.com",phone: "09452665267").save(flush:true)
        def result

        when:
        result = service.save(workbook)

        then:
        result == workbook
    }
}
