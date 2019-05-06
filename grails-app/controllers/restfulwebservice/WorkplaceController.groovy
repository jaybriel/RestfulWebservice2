package restfulwebservice

import grails.converters.JSON
import grails.gorm.transactions.Transactional


class WorkplaceController {
    def workplaceService
    def workbookService
    static responseFormats = ['json', 'xml']
    static allowedMethods = [save:"POST",update:"PUT",delete:"DELETE"]


    def index() {
        if(workplaceService.list().size() <= 0)
        {
            response.status = 404
            render([error:'workplaces not found'] as JSON)
        }
        else
        {
            println('success index')
            respond workplaceService.list()
        }



    }

    def show(Workplace workplace) {

        if(workplace == null)
        {
            response.status = 404
            render([error:'workplace not found'] as JSON)
        }
        else
        {
            println('success show')
            respond workplace
        }

    }
    @Transactional
    def save(Workplace workplace){
        println(workplace)
//        println(workbook)
        def workbook = workbookService.retrieveId(workplace.workbook.id)
        if(!workplaceService.validateWorkplace(workbook,workplace))
        {
            respond workplace.errors
        }
        else{

            workplaceService.save(workplace)
            render([message:'workplace successfully saved'] as JSON)
        }

    }

    @Transactional
    def update(Workplace workplace)
    {
        println('update here')
        println(workplace)
        def workbook = workbookService.retrieveId(workplace.workbook.id)
        if(!workplaceService.validateWorkplace(workbook,workplace))
        {

            respond workplace.org_grails_datastore_gorm_GormValidateable__errors
            println workplace.org_grails_datastore_gorm_GormValidateable__errors

        }
        else
        {
            println('sucess save')
            workplaceService.save(workplace)
            render([message:'workplace successfully updated'] as JSON)
        }
    }

    def delete (Workplace workplace){

        if(workplace == null)
        {
            response.status = 404
            render([error:'workplace not found'] as JSON)
        }
        else{
            println('success delete')
            workplaceService.delete(workplace)
            render([message:'workplace successfully deleted'] as JSON)
        }




    }
}
