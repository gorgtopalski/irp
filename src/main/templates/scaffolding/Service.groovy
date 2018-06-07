<%=packageName ? "package ${packageName}" : ''%>

import grails.gorm.services.Service

@Service(${className})
abstract class ${className}Service {

    abstract ${className} get(Serializable id)

    abstract List<${className}> list(Map args)

    abstract Long count()

    abstract void delete(Serializable id)

    abstract ${className} save(${className} ${propertyName})

}