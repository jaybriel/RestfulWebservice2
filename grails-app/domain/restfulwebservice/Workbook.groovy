package restfulwebservice

import grails.databinding.BindingFormat
import grails.rest.Resource

@Resource
class Workbook {
    String firstName
    String lastName
    @BindingFormat('yyyy-MM-dd')
    Date dateOfBirth
    int age
    String passportNumber
    String email
    String phone
    String image

    static constraints = {
        lastName nullable: true
        dateOfBirth nullable: true
        age nullable: true
        passportNumber nullable: true
        email nullable: true
        phone nullable: true
        image nullable: true
    }
}
