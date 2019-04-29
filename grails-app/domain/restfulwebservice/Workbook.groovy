package restfulwebservice

import org.joda.time.LocalDate

class Workbook {
    Integer id
    String firstName
    String lastName
    LocalDate dateOfBirth
    int age
    String passportNumber
    String email
    String phone
//    String image

    static constraints = {
        firstName nullable: false,blank: false
        lastName nullable: false
        dateOfBirth nullable: false
        age range: 18..65,nullable: false
        passportNumber unique: true,nullable: false
        email email: true,nullable: false,unique: true
        phone nullable: false
//        image nullable:true,blank: true
    }

    static hasMany = [workplaces: Workplace]
}
