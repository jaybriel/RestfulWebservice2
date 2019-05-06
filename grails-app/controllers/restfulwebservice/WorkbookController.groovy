package restfulwebservice

import grails.converters.JSON
import grails.gorm.transactions.Transactional



@Transactional(readOnly = true)
class WorkbookController {


    def workbookService

    static responseFormats=['json','xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    def index()
    {
        if(workbookService.list().size() <= 0)
        {
            response.status = 404
            render([error:'workbooks not found'] as JSON)
        }
        else
        {
            println('success index')
            respond workbookService.list()

        }


    }

    def show(Workbook workbook) {
        if(workbook == null)
        {
            response.status = 404
            render([error:'workbook not found'] as JSON)
        }
        else
        {
            println('success show')
            respond workbook
        }

    }

    def delete (Workbook workbook){
        if(workbook == null)
        {
            response.status = 404
            render([error:'workbook not found in delete'] as JSON)
        }
        else
        {
            println('success delete')
            workbookService.delete(workbook)
            render([message:'workbook successfully deleted'] as JSON)
        }

    }
    @Transactional
    def save(Workbook workbook){
//        println(workbook.dateOfBirth)
        println(response)
        println(workbook.workplaces)

         if(workbook.validate())
        {
            workbookService.save(workbook)
            render([message:'workbook successfully saved'] as JSON)
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

        if(workbook == null)
        {
            response.status = 404
            render([error:'workbook not found'] as JSON)
        }
           else if(workbook.validate())
            {


                println('sucess save')
                workbookService.save(workbook)
                render([message:'workbook successfully updated'] as JSON)

            }
            else
            {
                respond workbook.org_grails_datastore_gorm_GormValidateable__errors
                println workbook.org_grails_datastore_gorm_GormValidateable__errors
            }
        }
    }

//}