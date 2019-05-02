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

        get "/workplaces" (controller:"workplace",action:"index")
        get "/workplaces/create" (controller:"workplace",action: "create")
        post "/workplaces" (controller:"workplace", action:"save")
        get "/workplaces/$id" (controller:"workplace",action:"show")
        put "/workplaces/$id/edit" (controller:"workplace", action:"update")
        delete "/workplaces/$id" (controller:"workplace",action:"delete")

        "/"(controller: 'workbook', action:'index')

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
        }

    }

