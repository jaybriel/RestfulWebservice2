package restfulwebservice

import grails.gorm.transactions.Transactional

@Transactional
class WorkbookService {

    def list() {
        Workbook.list()
    }

    def retrieveId(id){
        def workbook = Workbook.findById(id)
        workbook
    }

    def save(Workbook workbook)
    {
        workbook.save(flush:true)
    }

    def delete(Workbook workbook)
    {
        workbook.delete(flush:true)
    }


}
