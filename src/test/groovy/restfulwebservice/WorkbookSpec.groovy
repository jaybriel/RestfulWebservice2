package restfulwebservice

import grails.testing.gorm.DomainUnitTest
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import spock.lang.Specification
import spock.lang.Unroll

class WorkbookSpec extends Specification implements DomainUnitTest<Workbook> {




    def setup() {
        mockDomain Workbook
        mockDomain Workplace
    }

    def cleanup() {
    }

    void "test email validation if accepts input without @ symbol and output expected results"(){
        when:
        domain.email = value

        then:"email value is being validated"
        expected == domain.validate(['email'])
        domain.errors['email']?.code == expectedErrorCode

        where:"data table is being defined to test diferrent inputs and returns expected results"
        value                   | expected | expectedErrorCode
         null                   | false    | 'nullable'
        ''                      | false    |  'blank'
        'contact@hilton.com'    | true     |   null
        'hilton'                | false    | 'email.invalid'
    }

    void "test firstName and lastName if accepts null values and numbers and output expected results "(){
        when:
        domain.firstName = value
        domain.lastName = value

        then:
        expected == domain.validate(['firstName'])
        domain.errors['firstName']?.code == expectedErrorCode

        expected == domain.validate(['lastName'])
        domain.errors['lastName']?.code == expectedErrorCode

        where:
        value                    | expected  | expectedErrorCode
        null                     | false     | 'nullable'
        'jaybriel somcio'        | true      |   null
        'hilton'                 | true      |   null
        '123123123'              | false     | 'matches.invalid'
        'jaybriel-somcio'        | true      |   null
    }

    void "test phone if matches the regex pattern(no letters and should be atleast 10-12 characters long and output expected result)"(){
        when:
        domain.phone = value

        then:
        expected == domain.validate(['phone'])
        domain.errors['phone']?.code == expectedErrorCode



        where:
        value                    | expected  | expectedErrorCode
        null                     | false     | 'nullable'
        'jaybriel somcio'        | false      | 'matches.invalid'
        'hilton'                 | false      | 'matches.invalid'
        '123123123'              | false      | 'matches.invalid'
        '09151324733'            | true      |   null
    }





    void "test age range validation with different test inputs and ouput expected result"(){
        when:
        domain.age = value

        then:"value of age is validated"
        expected == domain.validate(['age'])
        domain.errors['age']?.code == expectedErrorCode

        where:"data table is defined to test different inputs and returns expected results"
        value                  | expected | expectedErrorCode
        18                     | true     | null
        55                     | true     | null
        30                     | true     | null
        70                     | false    | 'range.toobig'
        23                     | true     | null
        15                     | false    | 'range.toosmall'
        90                     | false    | 'range.toobig'
    }

    void "test email unique constraint with 2 workbooks with the same email address"(){
        when: 'You instantiate a workbook with fields including email that has been never used before'
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-mm-dd")
        def workbook = new Workbook(firstName:"test",lastName:"test",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 40,passportNumber: "E12345678",email: "jaybrielsomcio@gmail.com",phone: "09452665267")


        then: 'workbook is valid instance'
        workbook.validate()

        and: 'we can save it, and we get back a not null GORM Entity'
        workbook.save()

        and: 'there is one additional Hotel'
        Workbook.count() == old(Workbook.count()) + 1

        when: 'instanting a different workbook with the same email address'
        def workbook2 = new Workbook(firstName:"jaybriel",lastName:"somcio",dateOfBirth: LocalDate.parse('1999-05-25',formatter),age: 21,passportNumber: "E12225678",email: "jaybrielsomcio@gmail.com",phone: "09151324733")

        then: 'the workbook instance is not valid'
        !workbook2.validate(['email'])

        and: 'unique error code is populated'
        workbook2.errors['email']?.code == 'unique'

        and: 'trying to save fails too'
        !workbook2.save()

        and: 'no workbook has been added'
        Workbook.count() == old(Workbook.count())
    }

    void "test passportNumber unique constraint with 2 workbooks with the same passportNumber"(){
        when: 'You instantiate a workbook with fields including email that has been never used before'
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-mm-dd")
        def workbook = new Workbook(firstName:"test",lastName:"test",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 21,passportNumber: "E12345678",email: "jaybrielsomcio@gmail.com",phone: "09452665267")


        then: 'workbook is valid instance'
        workbook.validate()

        and: 'we can save it, and we get back a not null GORM Entity'
        workbook.save()

        and: 'there is one additional Hotel'
        Workbook.count() == old(Workbook.count()) + 1

        when: 'instanting a different hotel with the same email address'
        def workbook2 = new Workbook(firstName:"test",lastName:"test",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 21,passportNumber: "E12345678",email: "jaybrielsomcio@yahoo.com",phone: "09452665267")

        then: 'the workbook instance is not valid'
        !workbook2.validate(['passportNumber'])

        and: 'unique error code is populated'
        workbook2.errors['passportNumber']?.code == 'unique'

        and: 'trying to save fails too'
        !workbook2.save()

        and: 'no workbook has been added'
        Workbook.count() == old(Workbook.count())
    }

    void "test passportNumber regex patterns with different test inputs and output expected results"(){
        when:
        domain.passportNumber = value

        then:"passportNumber is validated"
        expected == domain.validate(['passportNumber'])
        domain.errors['passportNumber']?.code == expectedErrorCode

        where:
        value                  | expected | expectedErrorCode
        null                   | false    | 'nullable'
        'E12345678'            | true     | null
        '123456789'            | true     | null
        'asdadadasd'           | false    | 'matches.invalid'

    }

    void "test phoneNumber regex patterns with different test inputs and output expected results"(){
        when:
        domain.phone = value

        then:"phone number is validated"
        expected == domain.validate(['phone'])
        domain.errors['phone']?.code == expectedErrorCode

        where:"data table is defined to test different inputs and outputs expected results"
        value                  | expected | expectedErrorCode
        '1234567890'           | true     | null
        '123456789'            | false     | 'matches.invalid'
        '09151324733'          | true      | null
        'asdasdasdasdaa'       | false     | 'matches.invalid'
    }


}
