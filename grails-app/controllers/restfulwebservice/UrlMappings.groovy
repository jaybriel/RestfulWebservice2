package restfulwebservice

class UrlMappings {

    static mappings = {

        get "/index"(controller:"workbook", action:"index")
        get "/workbooks/create"(controller:"workbook", action:"create")
        post "/save"(controller:"workbook", action:"save")
        get "/show/$id"(controller:"workbook", action:"show")
        get "/workbooks/$id/edit"(controller:"workbook", action:"edit")
        put "/update/$id"(controller:"workbook", action:"update")
        delete "/delete/$id"(controller:"workbook", action:"delete")


//        delete "/$controller/$id(.$format)?"(action:"delete")
//        get "/$controller(.$format)?"(action: "index")
//        get "/$controller/$id(.$format)?"(action:"show")
//        post "/$controller(.$format)?"(action:"save")
//        put "/$controller/$id(.$format)?"(action:"update")
//        patch "/$controller/$id(.$format)?"(action:"patch")

//        "/workbooks"(resources: 'workbook', excludes: ['delete'])

        "/"(controller: 'workbook', action:'index')

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
        }

    }

