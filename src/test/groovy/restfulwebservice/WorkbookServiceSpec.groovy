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

    @Autowired
    WorkbookService workbookservice

    def setupSpec(){
        mockDomain Workbook
        mockDomain Workplace
    }


    def setup() {
        for(int i=0;i<3;i++)
        {
            new Workbook(firstName:"test",lastName:"test",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 21,passportNumber: "E1234567${i}",email: "jaybrielsomcio${i}@gmail.com",phone: "09452665267").save(flush:true)

        }
        new Workbook(firstName:"test",lastName:"test",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 21,passportNumber: "E123488678",email: "jaybrielsomcio@drive.com",phone: "09452665267")
                .addToWorkplaces(new Workplace(cmpCode:"IBM",cmpName:"Webb Fontaine Group",ctyCode:"MNL",ctyDesc:"ManilaArea",startDate: LocalDate.parse("2019-01-02",formatter),endDate: LocalDate.parse("2019-01-30",formatter)))
    }

    def cleanup() {
    }
    DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-mm-dd")

    def result


    void "test retrieveId action of workbook and test the values if the action returns the correct model"(){
        when:"retrieveid action is executed and result is stored in a variable"
        result = service.retrieveId(1)

        then:"returns the workbook that matches the given id parameters"
        result.firstName =="test"
        result.lastName == "test"
        result.age == 21
        result.dateOfBirth == LocalDate.parse("1997-11-02",formatter)
        result.passportNumber == "E12345670"
        result.email == "jaybrielsomcio0@gmail.com"
        result.phone == "09452665267"
    }


    void "test save method with existing workbook then returns the saved workbook"(){


        when:"save action is executed and result is stored in a variable"
        result = service.save(service.retrieveId(1))

        then:"result matches the workbook saved"
        result == service.retrieveId(1)
    }

    void "test delete action with 1 existing workbook then should delete the existing workbook"(){
        when:"delete action is executed and result is stored in a variable"
        result = service.delete(service.retrieveId(1))

        then:"result is expected to be null for successfully deleting the workbook object"
        result ==  service.retrieveId(null)
    }


    void "test save method with 1 existing workbook and workplace then returns the saved workbook"(){
        when:"save action is executed and result is stored in a variable"
        result = service.save(service.retrieveId(3))

        then:"result matches the workbook saved"
        result == service.retrieveId(3)
    }

    void "test delete method with 1 existing workbook and workplace then should delete the existing workbook"(){


        when:"delete action is executed and result is stored in a variable"
        result = service.delete(service.retrieveId(3))

        then:"result is expected to be null for successfully deleting the workbook"
        result ==  null
    }

    void 'test the list action with 3 existing workbooks then returns the correct model with size of list'(){
        when:"the save action is executed 3 times for the 3 workbooks"
        result = service.list()

        then:"expected size of the list should be 3 for finding 3 workbooks saved"
        result.size() == 3

    }
}
