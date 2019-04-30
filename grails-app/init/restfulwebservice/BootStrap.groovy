package restfulwebservice

class BootStrap {

    def init = { servletContext ->
//               def workbook = new Workbook(firstName: "jaybriel").addToWorkplaces(new Workplace(cmpCode:"myCode")).save(flush:true)
                 new Workbook(firstName:"jaybriel").save(flush:true)
//                new Workbook(firstName: "tim").save(flush:true)
    }
    def destroy = {
    }
}
