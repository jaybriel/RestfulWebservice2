package restfulwebservice

import grails.databinding.BindingFormat

import java.time.LocalDate

class Workplace {
    long id
    int rank
    String cmpCode
    String cmpName
    String ctyCode
    String ctyDesc

    LocalDate startDate

    LocalDate endDate
    static constraints = {
        cmpCode blank: false,nullable: false
        cmpName nullable: true
        id nullable: false
        ctyCode nullable: false
        ctyDesc nullable: true
        startDate nullable: false
        endDate nullable: false
        workbook nullable: true
    }


    static belongsTo = [workbook: Workbook]
}
