import grails.rest.render.hal.HalJsonRenderer
import restfulwebservice.Workbook

// Place your Spring DSL code here
beans = {
    halBookRenderer(HalJsonRenderer, Workbook)
}
