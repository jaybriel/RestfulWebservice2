package restfulwebservice

import grails.testing.gorm.DomainUnitTest
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import spock.lang.Specification

class WorkplaceSpec extends Specification implements DomainUnitTest<Workplace> {

    def setup() {
        mockDomain Workplace
    }

    def cleanup() {
    }

    void "test cmpCode and ctyCode to not accept null values and output expected results "(){
        when:
        domain.cmpCode = value
        domain.ctyCode = value

        then:
        expected == domain.validate(['cmpCode'])
        domain.errors['cmpCode']?.code == expectedErrorCode

        expected == domain.validate(['ctyCode'])
        domain.errors['ctyCode']?.code == expectedErrorCode


        where:
        value                    | expected  | expectedErrorCode
        null                     | false     | 'nullable'
        'jaybriel somcio'        | true      |   null
        'hilton'                 | true      |   null
        '123123123'              | true      |   null
        'jaybriel-somcio'        | true      |   null
    }

    void "test cmpName and ctyDesc if to accept null values and output expected results"(){
        when:
        domain.cmpName = value
        domain.ctyDesc = value

        then:
        expected == domain.validate(['cmpName'])
        domain.errors['cmpName']?.code == expectedErrorCode

        expected == domain.validate(['ctyDesc'])
        domain.errors['ctyDesc']?.code == expectedErrorCode


        where:
        value                    | expected  | expectedErrorCode
        null                     | true     |    null
        'jaybriel somcio'        | true      |   null
        'hilton'                 | true      |   null
        '123123123'              | true      |   null
        'jaybriel-somcio'        | true      |   null
    }

}
