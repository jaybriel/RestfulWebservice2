package restfulwebservice

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification
import spock.lang.Unroll

class WorkbookSpec extends Specification implements DomainUnitTest<Workbook> {

    def setup() {
        mockDomain Workbook
    }

    def cleanup() {
    }

    void "test email validation with different test inputs and output expected result"(){
        when:
        domain.email = value

        then:
        expected == domain.validate(['email'])
        domain.errors['email']?.code == expectedErrorCode

        where:
        value                   | expected | expectedErrorCode
         null                   | false    | 'nullable'
        ''                      | false    |  'blank'
        'contact@hilton.com'    | true     |   null
        'hilton'                | false    | 'email.invalid'
    }

}
