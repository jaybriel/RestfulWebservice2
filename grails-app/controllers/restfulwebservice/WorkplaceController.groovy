package restfulwebservice

import grails.gorm.transactions.Transactional


class WorkplaceController {
    def workplaceService
    def workbookService
    static responseFormats = ['json', 'xml']
    static allowedMethods = [save:"POST",update:"PUT",delete:"DELETE"]


    def index() {
        println('success index')
        respond workplaceService.list()
    }

    def show(Workplace workplace) {
        println('success show')
        respond workplace
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

        }

    }

    @Transactional
    def update(Workplace workplace)
    {
        println('update here')
        println(workplace)

        if(workplace.validate())
        {


            println('sucess save')
            workplaceService.save(workplace)
            withFormat {
                html {
                    redirect workplace
                }

            }
        }
        else
        {
            respond workplace.org_grails_datastore_gorm_GormValidateable__errors
            println workplace.org_grails_datastore_gorm_GormValidateable__errors
        }
    }

    def delete (Workplace workplace){
        println('success delete')
//        workplace.delete(flush: true)
        workplaceService.delete(workplace)
        withFormat {
            html {
                redirect workplace
            }

        }
    }
}
