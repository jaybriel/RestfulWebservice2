package restfulwebservice

import grails.databinding.BindingFormat

import org.joda.time.LocalDate

class Workplace {
    long id
    String cmpCode
    String cmpName
    String ctyCode
    String ctyDesc

    LocalDate startDate

    LocalDate endDate
    static constraints = {
        cmpCode nullable: false
        cmpName nullable: true
        ctyCode nullable: false
        ctyDesc nullable: true
        startDate nullable: false,matches:"([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))"
        endDate matches:"([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))",nullable: false,validator: {val, obj ->
            if(val <= obj.startDate)
                return ['overlap']
        }
    }


    static belongsTo = [workbook: Workbook]

}
