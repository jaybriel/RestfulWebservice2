package restfulwebservice

import grails.testing.gorm.DataTest
import grails.testing.services.ServiceUnitTest
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import spock.lang.Specification

class WorkplaceServiceSpec extends Specification implements ServiceUnitTest<WorkplaceService>, DataTest{

    def setup() {
        mockDomain Workplace
    }

    def cleanup() {
    }

    void "test save method with existing workplace and workbook"(){
        given:
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-mm-dd")
        def workbook = new Workbook(firstName:"test",lastName:"test",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 21,passportNumber: "E12345678",email: "jaybrielsomcio@gmail.com",phone: "09452665267").save(flush:true)
        def workplace = new Workplace(cmpCode:"IBM",cmpName:"Webb Fontaine Group",ctyCode:"MNL",ctyDesc:"ManilaArea",startDate: LocalDate.parse("2019-01-02",formatter),endDate: LocalDate.parse("2019-01-30",formatter)).save(flush:true)
        def result
    }


}
