package com.verallia

class HomeController {

    HomeService homeService

    def index()
    {
        respond homeService.getIndexData(), view: 'index'
    }

    def search(String query)
    {
        def str = query.trim()

        if (str)
        {
            def data = homeService.search(str)

            if (!data.models.isEmpty())
            {
                respond([data: data])
                return
            }
            else {
                flash.message = "Sin resultados para \"$str\""
                flash.alert = 'warning'
                redirect action: 'index'
                return
            }
        }
        else
        {
            redirect action: 'index'
            return
        }
    }
}
