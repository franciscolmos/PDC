$(document).ready(function(){
    JTable.addRows()
    $("#add").click(function(){
        aux = JTable.addRow()
        $("#table").append(aux)
    })
    $("#form").submit(function(event){
        existe = false
        backUp = JTable.backUp()
        elements = $("#table .row").toArray()
        for (let i = 0; i < elements.length - 1; i++) {
            p1 = JTable.createPersona(elements[i])
            for (let j = i + 1; j < elements.length; j++) {
                p2 = JTable.createPersona(elements[j])
                if(JTable.checkUnit(p1, p2)){
                    existe = true
                }
            }
        }
        if(existe) {
            alert("Elemento Existe")
            JTable.restore(backUp)
            event.preventDefault()
        }
        else{
            JTable.setStorage(elements)
        }
        $(this).attr("action", "mailto:pepe@gmail.com")
    })
    $("#clean").click(function(){
        localStorage.clear()
    })
})

var JTable = {
    addRow: function(){
        aux = $("#aux").clone()
        id = $("#table .row").length + 1
        aux.attr("id", id)
        aux.attr("hidden", false)
        aux.find("[name=apellido]").attr("name", "apellido"+id)
        aux.find("[name=name]").attr("name", "name"+id)
        aux.find("[name=parentesco]").attr("name", "parentesco"+id)
        aux.find("[name=vive]").attr("name", "vive"+id)
        aux.find("[name=nacimiento]").attr("name", "nacimiento"+id)
        aux.find("[name=ocupacion]").attr("name", "ocupacion"+id)
        aux.find("[name=mail]").attr("name", "mail"+id)
        JTable.checkColor(aux)
        return aux
    },
    addRows:function(){
        for (let i = 0; i < localStorage.length; i++) {
            aux = JTable.addRow()
            p = JSON.parse(localStorage.getItem(i))
            aux = JTable.setValues(aux, p)
            $("#table").append(aux)
        }
        $("#table").append(JTable.addRow())
    },
    setValues:function(aux, p){
        aux.find("#iapellido").val(p.apellido)
        aux.find("#iname").val(p.nombre)
        aux.find("#iparentesco").val(p.parentesco)
        aux.find("input:radio:checked").prop("checked", false)
        aux.find("input:radio[value="+p.vive+"]").prop("checked", true)
        aux.find("#inacimiento").val(p.nacimiento)
        if(p.ocupacion != ""){
            aux.find("#iocupacion").val(p.ocupacion)
            aux.find("#imail").val(p.mail)
        }
        else{
            JTable.alive(aux.find("input:radio").parent().parent())
        }
        return aux
    },
    alive: function(elem){
        radio = $(elem).find("input:radio:checked")
        if($(radio).val() == "no"){
            $(elem).parent().find("#iocupacion").val("")
            $(elem).parent().find("#iocupacion").attr("disabled", true)
            $(elem).parent().find("#imail").val("")
            $(elem).parent().find("#imail").attr("disabled", true)
        }
        else{
            $(elem).parent().find("#iocupacion").attr("disabled", false)
            $(elem).parent().find("#imail").attr("disabled", false)
        }
    },
    delete: function(elem){
        JTable.eraseElement(JTable.createPersona($(elem).parent().parent()))
        $(elem).parent().parent().remove()
        var i = 1
        $("#table .row").each(function(){
            id = $(this).attr("id")
            $(this).attr("id", i)
            $(this).find("[name=apellido"+id+"]").attr("name", "apellido"+i)
            $(this).find("[name=name"+id+"]").attr("name", "name"+i)
            $(this).find("[name=parentesco"+id+"]").attr("name", "parentesco"+i)
            $(this).find("[name=vive"+id+"]").attr("name", "vive"+i)
            $(this).find("[name=nacimiento"+id+"]").attr("name", "nacimiento"+i)
            $(this).find("[name=ocupacion"+id+"]").attr("name", "ocupacion"+i)
            $(this).find("[name=mail"+id+"]").attr("name", "mail"+i)
            JTable.checkColor($(this))
            i++
        })
    },
    checkColor:function(elem){
        if($(elem).attr("id") % 2 == 0 ){
            $(elem).css({'background-color': "red"})
        }
        else{
            $(elem).css({'background-color': "yellow"})
        }
    },
    createPersona: function(elem){
        var persona = {apellido: $(elem).find("#iapellido").val(),
            nombre: $(elem).find("#iname").val(),
            parentesco: $(elem).find("#iparentesco").val(),
            vive: $(elem).find("input:radio:checked").val(),
            nacimiento: $(elem).find("#inacimiento").val(),
            ocupacion: $(elem).find("#iocupacion").val(),
            mail: $(elem).find("#imail").val()}
        return persona
    },
    checkUnit:function(p1, p2){
        if(p1.nombre == p2.nombre && p1.apellido == p2.apellido && p1.parentesco == p2.parentesco){
            return true
        }
        return false
    },
    backUp:function(){
        aux = []
        for (let i = 0; i < localStorage.length; i++) {
            aux.push(JSON.parse(localStorage.getItem(i)))
        }
        localStorage.clear()
        return aux
    },
    restore:function(backUp){
        localStorage.clear()
        for (let i = 0; i < backUp.length; i++) {
            localStorage.setItem(i, JSON.stringify(backUp[i]))
        }
    },
    setStorage:function(list){
        localStorage.clear()
        for (let i = 0; i < list.length; i++) {
            p1 = JTable.createPersona(list[i])
            localStorage.setItem(i, JSON.stringify(p1))
        }
    },
    eraseElement:function(elem){
        for (let i = 0; i < localStorage.length; i++) {
            p1 = JSON.parse(localStorage.getItem(i))
            if(elem.apellido == p1.apellido && elem.nombre == p1.nombre && elem.parentesco == p1.parentesco && elem.vive == p1.vive && elem.nacimiento == p1.nacimiento && elem.ocupacion == p1.ocupacion && elem.mail == p1.mail){
                localStorage.removeItem(i)
            }
        }
    }
}