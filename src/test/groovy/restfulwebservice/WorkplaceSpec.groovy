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


    void 'test cmpCode cannot be blank then validate value'() {
        when:"cmpCode is blank"
        domain.cmpCode = ''

        then:"cmpCode is validated and expected result is false because cmpCode is blank"
        !domain.validate(['cmpCode'])
    }

    void 'test cmpCode cannot be null then validate value'() {
        when:"cmpCode is null"
        domain.cmpCode = null

        then:"cmpCode is validated and expected result is false because cmpCode is null"
        !domain.validate(['cmpCode'])
    }

    void 'test cmpName can be null'() {
        when:"cmpName is null"
        domain.cmpName = null

        then:"cmpName is validated and expected result is false because cmpName is null"
        domain.validate(['cmpName'])
    }

    void 'test cmpName can be blank'() {
        when:"cmpName is blank"
        domain.cmpName = ''

        then:"cmpName is validated and expected result is false because cmpName is blank"
        domain.validate(['cmpName'])
    }
    void 'test ctyCode cannot be blank'() {
        when:"ctyCode is blank"
        domain.ctyCode = ''

        then:"ctyCode is validated and expected result is false because ctyCode is blank"
        !domain.validate(['ctyCode'])
    }

    void 'test ctyCode cannot be null'() {
        when:"ctyCode is null"
        domain.ctyCode = null

        then:"ctyCode is validated and expected result is false because ctyCode is null"
        !domain.validate(['ctyCode'])
    }
    void 'test ctyDesc can be null'() {
        when:"ctyDesc is null"
        domain.ctyDesc = null

        then:"ctyDesc is validated and expected result is false because ctyDesc is blank"
        domain.validate(['ctyDesc'])
    }

    void 'test ctyDesc can be blank'() {
        when:"ctyDesc is blank"
        domain.ctyDesc = ''

        then:"ctyDesc is validated and expected result is false because ctyCode is blank"
        domain.validate(['ctyDesc'])
    }

}
