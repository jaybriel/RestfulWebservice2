package restfulwebservice

import grails.gorm.transactions.Transactional

@Transactional
class WorkplaceService {



    def list()
    {
        Workplace.list()
    }

    def save(Workplace workplace)
    {
        workplace.save(flush:true)
    }

    def delete(Workplace workplace)
    {
        workplace.delete(flush:true)
    }

    def validateDate(Workbook workbook, Workplace workplace) {
        if (workplace.startDate != null && workplace.endDate != null) {
            def date1 = workplace.startDate
            def date2 = workplace.endDate
            if (date1.isAfter(date2)) {
                workplace.errors.rejectValue('startDate', 'workplace.startDate.overlap')
            }
            return !date1.isAfter(date2)
        } else {
            return false
        }

    }



    def checkOverlapDateWithPrevDate(Workbook workbook,Workplace workplace,int i) {

        if (workbook.workplaces.size() > 0) {
            def startDate = workplace.startDate
            def endDate = workplace.endDate
            def prevStartDate = workbook.workplaces[i].startDate
            def prevEndDate = workbook.workplaces[i].endDate

            if (startDate.isBefore(prevEndDate) && prevStartDate.isBefore(endDate)) {
                workplace.errors.rejectValue('endDate', 'workplace.startDate.overlap')
                return false
            } else {
                return true
            }

        } else {
            return true
        }

    }

    def validateWorkplace(Workbook workbook,Workplace workplace) {
        workplace.validate()
        if (workbook.workplaces.size() > 0) {
            for (int i = 0; workbook.workplaces.size(); i++) {
                if (workbook.workplaces.size() > 0) {
                    return checkOverlapDateWithPrevDate(workbook,workplace, i)
                }
            }
        }
        return validateDate(workbook, workplace)
    }

}
