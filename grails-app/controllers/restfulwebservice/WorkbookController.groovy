package restfulwebservice

import grails.gorm.transactions.Transactional
import grails.rest.RestfulController
import groovy.transform.CompileStatic
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

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
    static responseFormats=['json','xml']
    def index()
    {
        println('success index')
        respond Workbook.list()
    }

    def show(Workbook workbook) {
        println('success show')
        respond workbook
    }

    def delete (Workbook workbook){
        println('success delete')
        workbook.delete(flush: true)
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
        println(workbook.dateOfBirth)

        if(workbook.validate())
        {
//            respond workbook.errors,view:'/workbook/create'
//            respond workbook
            workbook.save(flush:true)
            withFormat {
                html {
//                    flash.message = message(code: 'default.created.message', args: [message(code: 'book.label', default: 'Book'), book.id])
                    redirect workbook
                }
//                '*' { render status: CREATED }
            }
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
//        if(workbook == null)
//        {
//            println('null workbook')
//        }
//        else
//        {
            if(workbook.hasErrors())
            {
                println('has errors')
                respond workbook.errors
            }
            else
            {
                println('sucess save')
                workbook.save(flush:true)
                withFormat {
                    html {
//                    flash.message = message(code: 'default.created.message', args: [message(code: 'book.label', default: 'Book'), book.id])
                        redirect workbook
                    }
//                '*' { render status: CREATED }
                }
            }
        }
    }

//}