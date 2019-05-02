package restfulwebservice

import grails.gorm.transactions.Transactional


//@CompileStatic
//class WorkbookController extends RestfulController {
//
//    static responseFormats=['json','xml']
//
//    WorkbookController() {
//        super(Workbook)
//    }
//}


@Transactional(readOnly = true)
class WorkbookController {


    def workbookService

    static responseFormats=['json','xml']
    def index()
    {
        println('success index')
        respond workbookService.list()
    }

    def show(Workbook workbook) {
        println('success show')
        respond workbook
    }

    def delete (Workbook workbook){
        println('success delete')
        workbookService.delete(workbook)
        withFormat {
            html {
//                    flash.message = message(code: 'default.created.message', args: [message(code: 'book.label', default: 'Book'), book.id])
                redirect workbook
            }
//                '*' { render status: CREATED }
        }
    }
    @Transactional
    def save(Workbook workbook){
//        println(workbook.dateOfBirth)
        println(workbook.workplaces)


        if(workbook.validate())
        {
//            respond workbook.errors,view:'/workbook/create'
//            respond workbook
            workbookService.save(workbook)
     }
        else{
            respond workbook.org_grails_datastore_gorm_GormValidateable__errors
            println workbook.org_grails_datastore_gorm_GormValidateable__errors

        }
    }

    @Transactional
    def update(Workbook workbook)
    {
        println('update here')
        println(workbook)

            if(workbook.validate())
            {


                println('sucess save')
                workbookService.save(workbook)
                withFormat {
                    html {
//                    flash.message = message(code: 'default.created.message', args: [message(code: 'book.label', default: 'Book'), book.id])
                        redirect workbook
                    }
//                '*' { render status: CREATED }
                }
            }
            else
            {
                respond workbook.org_grails_datastore_gorm_GormValidateable__errors
                println workbook.org_grails_datastore_gorm_GormValidateable__errors
            }
        }
    }

//}