package restfulwebservice

import grails.gorm.transactions.Transactional
import grails.rest.RestfulController

class WorkplaceController extends RestfulController<Workplace> {

    static responseFormats = ['json', 'xml']
    WorkplaceController() {
        super(Workplace)
    }

    @Override
    def index() {
        println('success index')
        respond Workplace.list()
    }
    @Transactional
    def save(Workbook workbook,Workplace workplace){
        println(workplace)
        workbook.addToWorkplaces(workplace)
//            respond workbook.errors,view:'/workbook/create'
//            respond workbook
            workbook.save(flush:true)
            withFormat {
                html {
//                    flash.message = message(code: 'default.created.message', args: [message(code: 'book.label', default: 'Book'), book.id])
                    redirect workplace
                }
//                '*' { render status: CREATED }
            }

//        else{
//            respond workbook.org_grails_datastore_gorm_GormValidateable__errors
//            println workbook.org_grails_datastore_gorm_GormValidateable__errors
//
//        }
    }
}
